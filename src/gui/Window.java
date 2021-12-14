package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import core.HiddenLayer;
import core.Layer;
import core.Network;

public class Window {

	Network net;

	JBackgroundPanel panel;

	JFrame frame;

	private Boolean running = true;

	private NetWorkGuiSettings settings;

	private int realNeuronSize;

	public Window(Network net, NetWorkGuiSettings settings) {
		this.net = net;
		this.settings = settings;
		realNeuronSize = settings.getNeuronSize() + settings.getNeuronDistance();

		init(net);

		
		frame.setVisible(true);

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				running = false;
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (running) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					update();

				}
			}
		}).start();
	}

	private int width, height;

	public Boolean isRunning() {
		return running;
	}

	private int maxneur;

	private void init(Network net) {

		maxneur = 0;
		for (Layer layer : net) {
			if (layer.getSize() > maxneur)
				maxneur = layer.getSize();
		}

		height = maxneur * (realNeuronSize);
		width = net.getSize()
				* (realNeuronSize + settings.getWeightSpaceWidth());
		width = width - settings.getWeightSpaceWidth();

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setBounds(0, 0, width, height);
		panel = new JBackgroundPanel();
		panel.setBounds(0, 0, width, height);
		frame.add(panel);

	}

	public void update() {
		BufferedImage i = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = i.createGraphics();

		drawWeights(g);
		drawNeurons(g);

		panel.setBackground(i);

	};

	private void drawWeights(Graphics2D g) {
		for (int layerIndex = 1; layerIndex < net.getSize(); layerIndex++) {
			HiddenLayer layer = (HiddenLayer) net.getLayer(layerIndex);

			for (int neuronIndex = 0; neuronIndex < layer.getSize(); neuronIndex++) {

				Layer parentLayer = layer.getParentLayer();

				for (int parentNeuronIndex = 0; parentNeuronIndex < parentLayer.getSize(); parentNeuronIndex++) {

					int px = calculateWeightX(layerIndex - 1);
					int py = calculateWeightY(parentNeuronIndex, parentLayer.getSize());

					int x = calculateWeightX(layerIndex);
					int y = calculateWeightY(neuronIndex, layer.getSize());

					g.setColor(Color.BLACK);

					double weight = layer.getWeight(parentNeuronIndex, neuronIndex);

					weight = weight - settings.getWeightMin();

					weight = weight > settings.getWeightMax() ? settings.getWeightMax() : weight < 0 ? 0 : weight;

					g.setStroke(new BasicStroke((float) weight * settings.getWeightScale()));

					g.drawLine(px, py, x, y);

				}

			}
		}
	}

	private void drawNeurons(Graphics2D g) {
		for (int layerIndex = 0; layerIndex < net.getSize(); layerIndex++) {
			Layer layer = net.getLayer(layerIndex);

			for (int neuronIndex = 0; neuronIndex < layer.getSize(); neuronIndex++) {

				double alpha = settings.getNeuronBrightness(layer.getValue(neuronIndex), layerIndex, neuronIndex);

				int x = calculateNeuronX(layerIndex);

				int y = calculateNeuronY(neuronIndex, layer.getSize());

				alpha = alpha > 1 ? 1 : alpha < 0 ? 0 : alpha;
				Color c = new Color(219, 9, 51, (int) (alpha * 255));

				g.setColor(Color.BLACK);
				g.fillOval(x, y, settings.getNeuronSize(), settings.getNeuronSize());

				g.setColor(c);
				g.fillOval(x, y, settings.getNeuronSize(), settings.getNeuronSize());
			}
		}
	}

	private int calculateNeuronX(int layerIndex) {
		int x = layerIndex * (realNeuronSize + settings.getWeightSpaceWidth());
		return x;
	}

	private int calculateNeuronY(int neuronIndex, int layerSize) {
		int y = neuronIndex * (realNeuronSize);

		y = y + (maxneur * (realNeuronSize)) / 2
				- (layerSize * (realNeuronSize)) / 2;

		return y;
	}

	private int calculateWeightX(int layerIndex) {
		return calculateNeuronX(layerIndex) + (realNeuronSize) / 2;
	}

	private int calculateWeightY(int neuronIndex, int layerSize) {
		return calculateNeuronY(neuronIndex, layerSize) + (realNeuronSize) / 2;
	}

}
