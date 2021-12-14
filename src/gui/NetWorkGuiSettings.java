package gui;

import java.awt.Color;

public interface NetWorkGuiSettings {

	public double getNeuronBrightness(double value, int layerIndex, int nodeIndex);

	public double getWeightMin();

	public double getWeightMax();

	public int getNeuronSize();

	public int getNeuronDistance();

	public int getWeightSpaceWidth();

	public float getWeightScale();

	public Color getNeuronONColor(int layerIndex, int neuronIndex);

	public Color getNeuronOFFColor(int layerIndex, int neuronIndex);

	public Color getWeightONColor(int parentLayerIndex, int layerIndex, int parentNeuronIndex, int neuronIndex);

	public Color getWeightOFFColor(int parentLayerIndex, int layerIndex, int parentNeuronIndex, int neuronIndex);

}
