package core;

public class InputLayer extends Layer{

	public InputLayer(int nodes) {
		super(nodes);
	}
	
	public void setValues(double[] values) {
		this.values=values;
	}

}
