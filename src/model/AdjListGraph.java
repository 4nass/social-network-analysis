package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AdjListGraph implements IGraph {
	
	//adj list that represent the graph
    List<Edge> list[];
    
    //Constructor 
    public AdjListGraph(int n) {
        this.list = new LinkedList[n];
        for (int i = 0; i <list.length ; i++) {
            list[i] = new LinkedList<Edge>();
        }
        
    }
    
    @Override
	public void addEdge(int source, int destination) {
    	if(this.isConnected(source, destination)==false)
    		list[source].add(0,new Edge(new Vertex(destination))); 
    }
    
    @Override
    public void addEdge(int source,int destination,int poids){    	
    	if(this.isConnected(source, destination)==false)
    		list[source].add(0,new Edge(destination,poids));    	
	}
    
    //if two nodes are connected
    public boolean isConnected(int source, int destination) {
		for(Edge i: list[source])
			if(i.getSommet().getSommetID() == destination) return true;
		return false;
	}

    //import facebook csv to adj list (graph)
    public void CSVToGraph(String path) throws FileNotFoundException, IOException {
    	String ligne = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ( (ligne = br.readLine()) != null) {
                // use \space as separator
                String[] data = ligne.split(" ");
                //Parse String extraction to integer addArete(int,int)
                this.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[1]),1);
            }
        }   
	}
    
    //get adj matrix from adj list
    public boolean[][] listToMatrix() {
        boolean r[][] = new boolean[list.length][list.length];
        for (int i = 0; i < list.length ; i++) {
            for (int j = 0; j < list.length; j++) {
                    r[i][j] = false;
                }
        }
        for (int i = 0; i < list.length ; i++) {
            if(list[i].size()>0) {
                for (int j = 0; j < list[i].size(); j++) {
                    r[i][j] = true;
                }
            }
        }
        return r;
    }
    
    
    @Override
    public String toString(){
    	String text = "";
        for (int i = 0; i < list.length ; i++) {
            if(list[i].size()>0) {
                text += "Le sommet " + i + " est connect� au sommet (avec un poids de): ";
                for (int j = 0; j < list[i].size(); j++) {
                	text += list[i].get(j).getSommet() + "("+ list[i].get(j).getPoids() +")" + " ";
                }
                text += "\n";
            }
        }
		return text;
    }
    /*
     
*/
}