package gui;

public interface NetWorkGuiSettings {

	public double getNeuronBrightness(double value, int layerIndex, int nodeIndex);

	public double getWeightMin();

	public double getWeightMax();
	
	public int getNeuronSize();
	
	public int getNeuronDistance();
	
	public int getWeightSpaceWidth();

	public float getWeightScale();

}
