import core.DataSet;
import core.InputLayer;
import core.Network;
import core.exceptions.NetworkLayoutException;
import netTypes.Perzeptron.PerzeptronBackPropagationHandler;
import netTypes.Perzeptron.PerzeptronGuiSettings;
import netTypes.Perzeptron.PerzeptronOutput;

public class Main {

	public static void main(String[] args) throws NetworkLayoutException {

		double[][] d1 = { { 0, 0, 1 }, { 0 } };
		double[][] d2 = { { 1, 0, 1 }, { 0 } };
		double[][] d3 = { { 0, 1, 1 }, { 0 } };
		double[][] d4 = { { 1, 1, 1 }, { 1 } };

		DataSet dataSet = new DataSet();

		dataSet.add(d1);
		dataSet.add(d2);
		dataSet.add(d3);
		dataSet.add(d4);

		Network n = new Network(new PerzeptronBackPropagationHandler(0.01), new InputLayer(3),new PerzeptronOutput(2), new PerzeptronOutput(1));

		n.generate(-0.5, 0.5);

		n.visualize(new PerzeptronGuiSettings());

		n.train(dataSet, 10000, true, 50);

		double[] da = n.logRun(d2[0]);

	}

}
