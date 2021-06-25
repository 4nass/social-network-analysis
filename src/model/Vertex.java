package model;

public class Vertex {
	
	String data = null;
	int sommetID;
	
	public Vertex(int sommet) {
		super();
		this.data = ""+sommet;
		this.sommetID = sommet;
	}
	
	public Vertex(String data, int sommet) {
		super();
		this.data = data;
		this.sommetID = sommet;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getSommetID() {
		return sommetID;
	}

	public void setSommetID(int sommet) {
		this.sommetID = sommet;
	}

	@Override
	public String toString() {
		return "Vertex [data=" + data + ", sommetID=" + sommetID + "]";
	}
	
	
	
}
