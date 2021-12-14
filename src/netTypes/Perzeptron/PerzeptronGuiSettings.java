package netTypes.Perzeptron;

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

}
