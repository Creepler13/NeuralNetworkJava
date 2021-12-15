package core;

import java.util.Random;

public abstract class Layer {
	protected double[] values;

	private Layer parentLayer, childLayer;

	public Layer(int nodes) {
		values = new double[nodes];
	}

	public double[] getValues() {
		return values;
	}

	public int getSize() {
		return values.length;
	}

	public double getValue(int index) {
		return values[index];
	}

	public void setValue(int index, double value) {
		values[index] = value;
	}

	public Layer getParentLayer() {
		return parentLayer;
	}

	void setParentLayer(Layer parentLayer) {
		this.parentLayer = parentLayer;
	}

	public Layer getChildLayer() {
		return childLayer;
	}

	void setChildLayer(Layer childLayer) {
		this.childLayer = childLayer;
	}

	private double[][] weights;

	private double minWeight, maxWeight;

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

	public void setValues(double[] input) {
		if (input.length <= values.length)
			for (int i = 0; i < input.length; i++) {
				values[i] = input[i];
			}
	}

}
