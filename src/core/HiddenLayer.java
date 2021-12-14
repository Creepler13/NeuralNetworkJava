package core;

import java.util.Random;

public abstract class HiddenLayer extends Layer {

	private double[][] weights;

	private double minWeight, maxWeight;

	public HiddenLayer(int neurons) {
		super(neurons);
	}

	public void generate(double min, double max) {
		minWeight = min;
		maxWeight = max;
		Random r = new Random();
		int in = getParentLayer().getSize();
		int out = getSize();
		weights = new double[in][out];
		for (int i = 0; i < in; i++) {
			String s = "";
			for (int j = 0; j < out; j++) {
				weights[i][j] = (r.nextDouble() * (max - min)) + min;
				s = s + " " + weights[i][j];
			}
		}

	}

	public void net() {
		for (int i = 0; i < getSize(); i++) {
			double net = 0;
			for (int j = 0; j < getParentLayer().getSize(); j++) {
				net = net + netPart(getParentLayer().getValue(j), weights[j][i], j, i);
			}
			setValue(i, activation(net));
		}
	}

	public double netPart(double value, double weight, int inputNodeIndex, int outputNodeIndex) {
		return value * weight;
	}

	public void setWeight(int i, int o, double value) {
		weights[i][o] = value;
	}

	public double getWeight(int i, int o) {
		return weights[i][o];
	}

	public double[][] getWeights() {
		return weights;
	}

	public abstract double activation(double netValue);

}
