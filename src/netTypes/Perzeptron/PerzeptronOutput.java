package netTypes.Perzeptron;

import core.Layer;

public class PerzeptronOutput extends Layer {

	public PerzeptronOutput(int nodes) {
		super(nodes);
	}

	@Override
	public double activation(double netValue) {
		return netValue > 0 ? 1 : 0;
	}

}
