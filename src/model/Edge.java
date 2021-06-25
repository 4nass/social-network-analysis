package model;

public class Edge {
	
	private Vertex sommet;
	private int poids;
	
	
	
	public Edge(Vertex sommet) {
		super();
		this.sommet = sommet; 
		this.poids = 1;
	}
	
	public Edge(Vertex sommet, int poids) {
		super();
		this.sommet = sommet; 
		this.poids = poids;
	}
	
	public Edge(int sommet,int poids){
		super();
		this.sommet = new Vertex(sommet); 
		this.poids = poids;
	}
	
	public Vertex getSommet() {
		return sommet;
	}

	public void setSommet(Vertex sommet) {
		this.sommet = sommet;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	@Override
	public String toString(){
		return "("+sommet.toString()+","+poids+")";
	}

}
