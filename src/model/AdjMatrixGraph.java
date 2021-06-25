package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdjMatrixGraph implements IGraph {
	
	//weighted matrix that represent the graph
	private int matrix[][];
        
		
	//Constructor 
	public AdjMatrixGraph(int n) {
		this.matrix = new int[n][n];
	}

	@Override
	public void addEdge(int source, int destination) {
		this.matrix[source][destination] = 1;

	}
	
	@Override
	public void addEdge(int source, int destination, int poids) {
		this.matrix[source][destination] = poids;

	}

	@Override
	public boolean isConnected(int source, int destination) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void displayMatrix(final int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
	
	@Override
	public String toString() {
        String result = "Graph: (Adjacency Matrix)\n";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix.length ; j++) {
            	result += matrix[i][j]+ " ";
            }
            result += "\n";
        }
        for (int i = 0; i < matrix.length; i++) {
        	result += "Vertex " + i + " is connected to:";
            for (int j = 0; j <matrix.length ; j++) {
                if(matrix[i][j]==1){
                	result += j + " ";
                }
            }
            result += "\n";
        }
        return result;
    }
	
	//import the weighted matrix from the StructuralAdjFrance.csv file (all the nodes)
	public void importStructuralAdjFrance() throws FileNotFoundException, IOException {
		String ligne = null;
    	final int n = 10000;
    	int[][] adjacencyMatrix;
    	
    	
    	
        try (BufferedReader br = new BufferedReader(new FileReader("./data/StructuralAdjFrance.csv"))) {
        	int i = 0;
        	adjacencyMatrix = new int[n][n];
        	while ( (ligne = br.readLine()) != null) {
            		
	            	// use , as separator
	                String[] data = ligne.split(",");
	                int j = 0;
	                for (String str : data) {
	                	adjacencyMatrix[i][j] = Integer.parseInt(str);
	                	j++;
	                }
	                i++;
            	
        	}
        } 
        this.matrix = adjacencyMatrix;
	}
	
	//import the weighted matrix from the StructuralAdjFrance1000.csv file (only 1000 nodes)
	public void importStructuralAdjFrance1000() throws FileNotFoundException, IOException {
		String ligne = null;
		final int n = 1000;
		int[][] adjacencyMatrix;
		
		
		
	    try (BufferedReader br = new BufferedReader(new FileReader("./data/StructuralAdjFrance1000.csv"))) {
	    	int i = 0;
	    	adjacencyMatrix = new int[n][n];
	    	while ( (ligne = br.readLine()) != null) {
	        		
	            	// use , as separator
	                String[] data = ligne.split(";");
	                int j = 0;
	                for (String str : data) {
	                	adjacencyMatrix[i][j] = Integer.parseInt(str);
	                	j++;
	                }
	                i++;
        	
	    	}
	    } 
	    this.matrix = adjacencyMatrix;
	}


	//import the matrix of the nodes names from the nodesFrance.csv file
	public String[][] importNodesFrance() throws FileNotFoundException, IOException {
    	String ligne = null;
    	final int n = 10000;
    	String[][] nodes;
    	
        try (BufferedReader br = new BufferedReader(new FileReader("./data/nodesFrance.csv"))) {
        	int i = 0;
        	nodes = new String[n][2];
        	while ( (ligne = br.readLine()) != null) {
            		
	            	// use , as separator
	                String[] data = ligne.split(",");
	                int j = 0;
	                for (String str : data) {
	                	nodes[i][j] = str;
	                	j++;
	                }
	                i++;
            	
        	}
        } 
        return nodes;
	}
	
	//getter matrix
    public int[][] getMatrix() {
        return this.matrix;
    }
    
    //change from the weighted matrix to adj matrix
    public boolean[][] changeToBool() {
        boolean r[][] = new boolean[matrix.length][matrix.length] ;
        
        for(int i=0 ; i <matrix.length ; i++ ) {
            for(int j=0; j <matrix.length ; j++ ) {
                if(matrix[i][j] >= 1) {
                    r[i][j] = true;
                } else r[i][j] = false;
            }
            
        }
        return r;
    }
    
    //get list with all the weights in order
    public int[] listOfWeight() {
    	int r[] = new int[201275];
        int k =0;
        for(int i=0 ; i <matrix.length ; i++ ) {
            for(int j=0; j <matrix.length ; j++ ) {
                if(matrix[i][j] >= 1) {
                    r[k]=matrix[i][j];
                    k++;
                } 
            }
            
        }
        return r;
    }
    
    //get the weight of the edge in the index
    public int getWeightIn(int index) {        	
    	return this.listOfWeight()[index] ;
    }
    
    public static int[] calculsom(boolean[][] m) {
		int soml = 0;
		int res[] = new int[m.length];
		for (int i = 0; i < m.length; i++) {
			soml = 0;
            for (int j = 0; j < m[i].length; j++) {
            		if (m[i][j] == true) {
            			soml = soml + 1;
            		}
        		res[i] = soml;
            	}
            }
		return res;
	}
	
	public static int moyenneListe(int[] list ) {
 		
	 	int all=0;
	 	for(int i = 0 ; i< list.length; i++) {
	 		all += list[i];
	 	}
	 	return all/list.length;
	 	
 	}

}
