package netTypes.Perzeptron;

import java.awt.Color;

import gui.NetWorkGuiSettings;

public class PerzeptronGuiSettings implements NetWorkGuiSettings {

	@Override
	public double getNeuronBrightness(double value, int layerIndex, int nodeIndex) {
		return value;
	}

	@Override
	public double getWeightMin() {
		return -.5;
	}

	@Override
	public double getWeightMax() {
		return .5;
	}

	@Override
	public int getNeuronSize() {
		return 100;
	}

	@Override
	public int getNeuronDistance() {
		return 10;
	}

	@Override
	public int getWeightSpaceWidth() {
		return 200;
	}

	@Override
	public float getWeightScale() {
		return 30;
	}

	@Override
	public Color getNeuronONColor(int layerIndex, int neuronIndex) {
		return Color.RED;
	}

	@Override
	public Color getNeuronOFFColor(int layerIndex, int neuronIndex) {
		return Color.BLACK;
	}

	@Override
	public Color getWeightONColor(int parentLayerIndex, int layerIndex, int parentNeuronIndex, int neuronIndex) {
		return Color.GREEN;
	}

	@Override
	public Color getWeightOFFColor(int parentLayerIndex, int layerIndex, int parentNeuronIndex, int neuronIndex) {
		return Color.BLACK;
	}

}
