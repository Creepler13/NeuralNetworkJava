package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import core.HiddenLayer;
import core.Layer;
import core.Network;

public class Window {

	Network net;

	JBackgroundPanel panel;

	JFrame frame;

	private NetWorkGuiSettings settings;

	public Window(Network net, NetWorkGuiSettings settings) {
		this.net = net;
		this.settings = settings;
		init(net);
		frame.setVisible(true);
	}

	private int width, height;

	private void init(Network net) {

		int maxneur = 0;
		for (Layer layer : net) {
			if (layer.getSize() > maxneur)
				maxneur = layer.getSize();
		}

		height = maxneur * (settings.getNeuronSize() + settings.getNeuronDistance());
		width = net.getSize()
				* (settings.getNeuronSize() + settings.getNeuronDistance() + settings.getWeightSpaceWidth());
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

					int px = (layerIndex - 1)
							* (settings.getNeuronSize() + settings.getNeuronDistance() + settings.getWeightSpaceWidth())
							+ settings.getNeuronSize() / 2;
					int py = parentNeuronIndex * (settings.getNeuronSize() + settings.getNeuronDistance())
							+ settings.getNeuronSize() / 2;

					int x = layerIndex
							* (settings.getNeuronSize() + settings.getNeuronDistance() + settings.getWeightSpaceWidth())
							+ settings.getNeuronSize() / 2;
					int y = neuronIndex * (settings.getNeuronSize() + settings.getNeuronDistance())
							+ settings.getNeuronSize() / 2;

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
				int x = layerIndex
						* (settings.getNeuronSize() + settings.getNeuronDistance() + settings.getWeightSpaceWidth());
				int y = neuronIndex * (settings.getNeuronSize() + settings.getNeuronDistance());

				double alpha = settings.getNeuronBrightness(layer.getValue(neuronIndex), layerIndex, neuronIndex);

				alpha = alpha > 1 ? 1 : alpha < 0 ? 0 : alpha;
				Color c = new Color(219, 9, 51, (int) (alpha * 255));

				g.setColor(Color.BLACK);
				g.fillOval(x, y, settings.getNeuronSize(), settings.getNeuronSize());

				g.setColor(c);
				g.fillOval(x, y, settings.getNeuronSize(), settings.getNeuronSize());
			}
		}
	}

}