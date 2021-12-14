package core;

import java.util.ArrayList;

public class DataSet {

	private ArrayList<double[][]> data = new ArrayList<>();

	public void add(double[] input, double[] output) {
		double[][] set = { input, output };
		data.add(set);
	}

	public void add(double[][] data) {
		this.data.add(data);
	}

	public int size() {
		return data.size();
	}

	public double[][] get(int index) {
		return data.get(index);
	}
}
