package netTypes.Perzeptron;

import core.BackPropagationHandler;
import core.Network;
import core.OutputLayer;

public class PerzeptronBackPropagationHandler implements BackPropagationHandler {

	double learnRate = 0;

	public PerzeptronBackPropagationHandler(double learnRate) {
		this.learnRate = learnRate;
	}

	@Override
	public void backpropagation(Network net, double[] input, double[] output) {
		OutputLayer l = net.getOutputLayer();

		for (int i = 0; i < l.getParentLayer().getSize(); i++) {
			double w = l.getWeight(i, 0);
			w = w + (learnRate * (output[0] - l.getValue(0)) * input[i]);

			l.setWeight(i, 0, w);
		}

	}

}
