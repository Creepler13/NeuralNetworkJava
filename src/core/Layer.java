package core;

public class Layer {
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

}
