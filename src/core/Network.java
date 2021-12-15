package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import gui.NetWorkGuiSettings;
import gui.Window;

public class Network implements Iterable<Layer> {

	private ArrayList<Layer> layers = new ArrayList<>();

	private BackPropagationHandler backHandler;

	public Network(BackPropagationHandler backHandler, Layer... layers)  {
		this.backHandler = backHandler;
		for (Layer layer : layers) {
			this.layers.add(layer);
		}
	}

	public Network(BackPropagationHandler backHandler, ArrayList<Layer> layers) {
		this.backHandler = backHandler;
		for (Layer layer : layers) {
			this.layers.add(layer);
		}
	}

	public void generate(double min, double max) {
		for (int i = 1; i < layers.size(); i++) {
			Layer layer = layers.get(i);
			layer.setParentLayer(layers.get(i - 1));
			layer.generate(min, max);
		}

	}

	Window w;

	public void train(DataSet dataSet, int count, Boolean randomize, int sleepTime) {
		Random r = new Random();

		for (int i = 0; i < count; i++) {
			if (w != null)
				if (w.isRunning())
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

			int dataindex = 0;
			if (randomize) {
				dataindex = (int) (r.nextDouble() * (dataSet.size()));
			} else {
				dataindex = (i % (dataSet.size() - 1));
			}

			double[][] data = dataSet.get(dataindex);

			train(data[0], data[1]);

		}

	}

	public double[] run(double[] input) {
		getInputLayer().setValues(input);
		for (int i = 1; i < getSize(); i++) {
			(getLayer(i)).net();
		}
		return getOutputLayer().getValues();
	}

	public void train(double[] input, double[] output) {
		run(input);
		backHandler.backpropagation(this, input, output);
	}

	public double[] logRun(double[] data) {

		double[] output = run(data);

		String s = "";
		for (double d : data) {
			s = s + d + " ";
		}
		s = s + "-> ";
		for (double d : output) {
			s = s + d + " ";
		}

		System.out.println(s);

		return output;
	}

	public void visualize(NetWorkGuiSettings settings) {
		w = new Window(this, settings);
		w.update();
	}

	public int getSize() {
		return layers.size();
	}

	public Layer getOutputLayer() {
		return getLayer(getSize() - 1);
	}

	public Layer getInputLayer() {
		return getLayer(0);
	}

	public Layer getLayer(int index) {
		return layers.get(index);
	}

	public Layer[] getLayers() {
		return (Layer[]) layers.toArray();
	}

	@Override
	public Iterator<Layer> iterator() {
		return layers.iterator();
	}

}
