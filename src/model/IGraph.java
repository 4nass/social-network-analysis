package model;

public interface IGraph {
	public void addEdge(int source, int destination);
	public void addEdge(int source,int destination,int poids);
	public boolean isConnected(int source, int destination);

}
