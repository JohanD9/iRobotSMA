package interfaces;

public interface ICreationEcosystem {
	public boolean createEspece(Integer posX, Integer posY, String couleur);
	public Integer getNextId();
	public void listerEspece();
}
