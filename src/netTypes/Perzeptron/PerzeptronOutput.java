package netTypes.Perzeptron;

import core.OutputLayer;

public class PerzeptronOutput extends OutputLayer {

	public PerzeptronOutput(int nodes) {
		super(nodes);
	}

	@Override
	public double activation(double netValue) {
		return netValue > 0 ? 1 : 0;
	}

}
