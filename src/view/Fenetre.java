package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import static org.graphstream.algorithm.Toolkit.*;

import model.AdjListGraph;
import model.AdjMatrixGraph;
import model.IGraph;

public class Fenetre extends JFrame{
	
		private static final long serialVersionUID = 1L;
		private Graph g; 
		private IGraph graph;
		public static String name;
		public static double degreeMoyNoeuds;
		public static double densite;
		public static int diametre;
		public static double coefMoyClust;
		private Panneau pan = new Panneau();
		private JPanel pan2 = new JPanel(new GridLayout()){			
			private static final long serialVersionUID = 1L;	
			@Override
	        public Dimension getPreferredSize() {
	            return new Dimension(1640, 600);
	        }
	    };
		private JPanel container = new JPanel();
		private JSplitPane split;
	 
		//private Thread t;
	
		private JMenuBar menuBar = new JMenuBar();

	    private JMenu fichier = new JMenu("Fichier"),
	      ouvrir = new JMenu("Ouvrir"),
	      algo = new JMenu("Algorithmes"),
	      aPropos = new JMenu("À propos");
	
	    private JMenuItem algo1 = new JMenuItem("Détection de communauté"),
	      algo2 = new JMenuItem("Coloration"),
	      quitter = new JMenuItem("Quitter"),
	      aProposItem = new JMenuItem("?");
	
	    private JRadioButtonMenuItem facebook1 = new JRadioButtonMenuItem("Données Facebook 1"),
	      facebook2 = new JRadioButtonMenuItem("Données Facebook 2"),
	      twitter = new JRadioButtonMenuItem("Données Twitter");
	
	    private ButtonGroup bg = new ButtonGroup();
	
	    public Fenetre() throws FileNotFoundException, IOException{
	    
		this.setTitle("Super Social Network Analysis");
	    this.setSize(1640, 780);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    pan.setBackground(Color.LIGHT_GRAY);
	    
	    try {
	    	showTwitterGraph1000();
          } catch (IOException e) {
        	  JOptionPane.showMessageDialog(null, "Erreur import données Twitter!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
          }
	    	    
	    //container.add(pan, BorderLayout.CENTER);
	    split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pan2, pan);
	    split.setOneTouchExpandable(true);
	    split.setDividerLocation(0.9);
	    //On le passe ensuite au content pane de notre objet Fenetre
	    //placé au centre pour qu'il utilise tout l'espace disponible
	    container.add(split, BorderLayout.CENTER);
	
	    this.setContentPane(container);
	    this.initMenu();
	    this.setVisible(true);            
	  }
	  
	  //affichage graphe Facebook 1000 noeuds
	  private void showFacebook1Graph() throws FileNotFoundException, IOException {
		  this.graph = new AdjListGraph(1000);	
		  name = "Facebook: (1 000 Noeuds)";
		  System.out.println("Facebook Graph: (1 000 Nodes)");
		  ((AdjListGraph) graph).CSVToGraph("./data/facebook_1000.csv");
		  this.g = createGraph("Graph Facebook1", ((AdjListGraph) graph).listToMatrix());
		  this.g.addAttribute("ui.quality");
	      this.g.addAttribute("ui.antialias");
	      this.g.setAttribute("ui.stylesheet", "url(./data/Style.css);");
	      for (Node node : g.getNodeSet()) 
	            node.addAttribute( "ui.label", node.getId());
	        
	      //update averagedegree and diameter
	      degreeMoyNoeuds = averageDegree(this.g);
	      System.out.println("Le degrée moyen des noeuds: "+degreeMoyNoeuds);
	      densite = density(this.g);
	      System.out.println("La densité du graphe: "+densite);
	      diametre = (int) diameter(this.g);
	      System.out.println("Le diametre du graphe: "+diametre);
	      coefMoyClust = averageClusteringCoefficient(this.g);
	      System.out.println("Le coefficient moyen de clustering: "+coefMoyClust);
	      Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
	      ViewPanel viewPanel = viewer.addDefaultView(false);
	      viewer.enableAutoLayout();
	      viewPanel.getCamera().setViewPercent(0.4);
	      viewPanel.getCamera().setViewCenter(0, 0, 0);
	      viewPanel.getCamera().setAutoFitView(true);
	      viewPanel.requestFocusInWindow();
	      //For clear JPanel 
	      pan.removeAll();
	      pan.updateUI();
	      pan2.removeAll();
	      pan2.updateUI();
	      pan2.add((Component) viewPanel);
		  
	  }
	  
	  //affichage graphe Facebook 2000 noeuds
	  private void showFacebook2Graph() throws FileNotFoundException, IOException {
		  this.graph = new AdjListGraph(2000);	
		  ((AdjListGraph) graph).CSVToGraph("./data/facebook_2000.csv");
		  name = "Facebook: (2 000 Noeuds)";
		  System.out.println("Facebook Graph: (2 000 Nodes)");
		  this.g = createGraph("Graph Facebook1", ((AdjListGraph) graph).listToMatrix());
		  this.g.addAttribute("ui.quality");
	      this.g.addAttribute("ui.antialias");
	      this.g.setAttribute("ui.stylesheet", "url(./data/Style.css);");
	      for (Node node : g.getNodeSet()) 
	            node.addAttribute( "ui.label", node.getId());
	      
	      //update averagedegree and diameter
	      degreeMoyNoeuds = (int) averageDegree(this.g);
	      System.out.println("Le degrée moyen des noeuds: "+degreeMoyNoeuds);
	      densite = density(this.g);
	      System.out.println("La densité du graphe: "+densite);
	      diametre = (int) diameter(this.g);
	      System.out.println("Le diametre du graphe: "+diametre);
	      coefMoyClust = averageClusteringCoefficient(this.g);
	      System.out.println("Le coefficient moyen de clustering: "+coefMoyClust);
	      Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
	      ViewPanel viewPanel = viewer.addDefaultView(false);
	      viewer.enableAutoLayout();
	      viewPanel.getCamera().setViewPercent(0.4);
	      viewPanel.getCamera().setViewCenter(0, 0, 0);
	      viewPanel.getCamera().setAutoFitView(true);
	      viewPanel.requestFocusInWindow();
	      //For clear JPanel
	      pan.removeAll();
	      pan.updateUI();
	      pan2.removeAll();
	      pan2.updateUI();  
	      pan2.add((Component) viewPanel);
		  
	  }
	  
	//affichage graphe Twitter complet (trés lent 2h d'execution)
	  private void showTwitterGraph() throws FileNotFoundException, IOException {
		  	
		  	this.graph = new AdjMatrixGraph(10000);
	       	((AdjMatrixGraph) graph).importStructuralAdjFrance();
	        this.g = createGraph("Graphe Twitter", ((AdjMatrixGraph) graph).changeToBool());
	        name = "Twitter: (10 000 Noeuds)";
	        System.out.println("Twitter Graph: (10 000 Nodes)");
		    
	        this.g.addAttribute("ui.quality");
	        this.g.addAttribute("ui.antialias");
	        this.g.setAttribute("ui.stylesheet", "url(./data/TwitterStyle.css);");
	        int i=0;
	        for (Node node : g.getNodeSet()) {
	            node.addAttribute( "ui.label", (Object) ((AdjMatrixGraph) graph).importNodesFrance()[i][0]);
	            System.out.println("Loading Nodes: "+i+" /10000");
	            i++;
	        }
	            //node.addAttribute(importNodesFrance()[Integer.parseInt(node.getId())][0], node.getId());
	        int j=0;
	        for (Edge edge : g.getEdgeSet()) {
	        	
	        	// edge.addAttribute("ui.label", (Object) weight);
	        	int w = ((AdjMatrixGraph) graph).getWeightIn(j);
	        	edge.addAttribute("layout.weight", (Object) w);
	        	System.out.println("Loading Edges: "+j+" /201275");
	        	j++;
	        }
	        //update averagedegree and diameter
	        degreeMoyNoeuds = (int) averageDegree(this.g);
	        System.out.println("Le degrée moyen des noeuds: "+degreeMoyNoeuds);
	        densite = density(this.g);
		    System.out.println("La densité du graphe: "+densite);
	        //diametre = (int) diameter(this.g, "layout.weight", true ); too long to exec
	        System.out.println("Le diametre du graphe: "+diametre);
	        coefMoyClust = averageClusteringCoefficient(this.g);
		    System.out.println("Le coefficient moyen de clustering: "+coefMoyClust);
	        Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
	        ViewPanel viewPanel = viewer.addDefaultView(false);
	        viewer.enableAutoLayout();
	        viewPanel.getCamera().setViewPercent(0.5);
	        viewPanel.getCamera().setViewCenter(0, 0, 0);
	        viewPanel.getCamera().setAutoFitView(true);
	        viewPanel.requestFocusInWindow();
	        //For clear JPanel 
	        pan.removeAll();
		    pan.updateUI();
	        pan2.removeAll();
		    pan2.updateUI();
	        pan2.add((Component) viewPanel);
	  }
	  
	  //affichage graphe Twitter (avec seulement 1000 noeuds) 
	  private void showTwitterGraph1000() throws FileNotFoundException, IOException {
		  	
		  	this.graph = new AdjMatrixGraph(1000);
	       	((AdjMatrixGraph) graph).importStructuralAdjFrance1000();
	        this.g = createGraph("Graph Twitter", ((AdjMatrixGraph) graph).changeToBool());
	        name = "Twitter: (1 000 Noeuds)";
	        System.out.println("Twitter Graph: (1 000 Nodes)");
		    
	        this.g.addAttribute("ui.quality");
	        this.g.addAttribute("ui.antialias");
	        this.g.setAttribute("ui.stylesheet", "url(./data/TwitterStyle.css);");
	        int i=0;
	        for (Node node : g.getNodeSet()) {
	            node.addAttribute( "ui.label", (Object) ((AdjMatrixGraph) graph).importNodesFrance()[i][0]);
	            System.out.println("Loading Nodes: "+i+" /1000");
	            i++;
	        }
	        int j=0;
	        for (Edge edge : g.getEdgeSet()) {
	        	int w = ((AdjMatrixGraph) graph).getWeightIn(j);
	        	edge.addAttribute("layout.weight", (Object) w);
	        	System.out.println("Loading Edges: "+j+" /21362");
	        	j++;
	        }
	        //update averagedegree and diameter
	        degreeMoyNoeuds = (int) averageDegree(this.g);
	        System.out.println("Le degrée moyen des noeuds: "+degreeMoyNoeuds);
	        densite = density(this.g);
		    System.out.println("La densité du graphe: "+densite);
	        diametre = (int) diameter(this.g, "layout.weight", true );
	        System.out.println("Le diametre du graphe: "+diametre);
	        coefMoyClust = averageClusteringCoefficient(this.g);
		    System.out.println("Le coefficient moyen de clustering: "+coefMoyClust);
	        Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
	        ViewPanel viewPanel = viewer.addDefaultView(false);
	        viewer.enableAutoLayout();
	        viewPanel.getCamera().setViewPercent(0.5);
	        viewPanel.getCamera().setViewCenter(0, 0, 0);
	        viewPanel.getCamera().setAutoFitView(true);
	        viewPanel.requestFocusInWindow();
	        //For clear JPanel 
	        pan.removeAll();
		    pan.updateUI();
	        pan2.removeAll();
		    pan2.updateUI();
	        pan2.add((Component) viewPanel);
	  }
	  
	  //Detection de communauté avec LinLog Layout... 
	  private void detectionCom() throws IOException {
		  
	  }
	  
	  //coloration graphe avec les coeff de clusturing (4 couleurs CoefClust={vert=[ 0, 0.5];jaune=[ 0.5, 1];orange=[ 1, 1.5];rouge=[ 1.5, +Inf]})
	  private void coloration() throws IOException {
		  this.g.addAttribute("ui.quality");
	      this.g.addAttribute("ui.antialias");
	      this.g.setAttribute("ui.stylesheet", "url(./data/coloration.css);");
	      for (Node node : g.getNodeSet()) {
	    	  if(clusteringCoefficient(node)<0.5) {
	    		  node.addAttribute("ui.style", "fill-color: rgb(160,255,0);");//coloration en vert
	    		  node.addAttribute("ui.size", "2gu");
	    	  }else if(clusteringCoefficient(node)<1) { 
	    		  node.addAttribute("ui.style", "fill-color: rgb(255,255,0);");//coloration en jaune
	    		  node.addAttribute("ui.size", "3gu");
	    	  }else if(clusteringCoefficient(node)<1.5) { 
	    		  node.addAttribute("ui.style", "fill-color: rgb(255,160,0);");//coloration en orange
	    		  node.addAttribute("ui.size", "4gu");
	    	  }else {
	    		  node.addAttribute("ui.style", "fill-color: rgb(255,0,0);");//coloration en rouge
	    		  node.addAttribute("ui.size", "5gu");
	    	  }            
	      }
	      Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
	      ViewPanel viewPanel = viewer.addDefaultView(false);
	      viewer.enableAutoLayout();
	      viewPanel.getCamera().setAutoFitView(true);
	      viewPanel.requestFocusInWindow();
	      //For clear JPanel 
	      pan.removeAll();
		  pan.updateUI();
	      pan2.removeAll();
		  pan2.updateUI();
	      pan2.add((Component) viewPanel);
	  }
	  
	  private static String getId(int nodeId) {
	        return "Node " + nodeId;
	  }

	  private static String getId(int nodeId, int neighborId) {
	        return "Edge " + nodeId + " -> " + neighborId;
	  }
	  
	  //Creer la visuali graphe
	  public static SingleGraph createGraph(String graphId, boolean[][] adjacencyMatrix) {
	        SingleGraph graph = new SingleGraph(graphId);

	        for (int nodeId = 0; nodeId < adjacencyMatrix.length; nodeId++)
	            graph.addNode(getId(nodeId));

	        for (int nodeId = 0; nodeId < adjacencyMatrix.length; nodeId++)
	            for (int neighborId = 0; neighborId < adjacencyMatrix[nodeId].length; neighborId++)
	                if (adjacencyMatrix[nodeId][neighborId])
	                    graph.addEdge(getId(nodeId, neighborId), getId(nodeId), getId(neighborId), true);

	        return graph;
	    }
	
	  private void initMenu(){
		//Menu fichier
		fichier.add(ouvrir);
	    fichier.addSeparator();
	    //Pour quitter l'application
	    quitter.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	        System.exit(0);
	      }
	    });
	    fichier.add(quitter);
	
	    //Menu ouvrir
	    facebook1.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	        	try {
					showFacebook1Graph();
		          } catch (IOException e) {
		        	  JOptionPane.showMessageDialog(null, "Erreur import données Facebook!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		          }
		          String mess = "Données Facebook1 impotées (1.000 sommets) ! \n";       
		          JOptionPane.showMessageDialog(null, mess, "Import Facebook1", JOptionPane.INFORMATION_MESSAGE);        
		        }            
		      });
	    facebook2.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	        	try {
					showFacebook2Graph();
		          } catch (IOException e) {
		        	  JOptionPane.showMessageDialog(null, "Erreur import données Facebook!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		          }
		          String mess = "Données Facebook2 impotées (2.000 sommets) ! \n";       
		          JOptionPane.showMessageDialog(null, mess, "Import Facebook2", JOptionPane.INFORMATION_MESSAGE);        
		        }            
		      });
	    twitter.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	        	try {
					showTwitterGraph();
		          } catch (IOException e) {
		        	  JOptionPane.showMessageDialog(null, "Erreur import données Twitter!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		          }
	        	  String mess = "Données Twitter impotées (10.000 sommets) ! \n";       
		          JOptionPane.showMessageDialog(null, mess, "Import Twitter", JOptionPane.INFORMATION_MESSAGE);
		          
		        }            
		      });
	    bg.add(facebook1);
	    bg.add(facebook2);
	    bg.add(twitter);
	
	    ouvrir.add(facebook1);
	    ouvrir.add(facebook2);    
	    ouvrir.add(twitter);
	
	    //facebook1.setSelected(true);
	    
	    algo1.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	        	try {
					detectionCom();
		          } catch (IOException e) {
		        	  JOptionPane.showMessageDialog(null, "Erreur implementation détection de communauté!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		          }
		          String mess = "Détection de communauté en cours de dévelopement ! \n";       
		          JOptionPane.showMessageDialog(null, mess, "Algorithmes", JOptionPane.INFORMATION_MESSAGE);        
		        }            
		      });
	    
	    algo2.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	        	try {
					coloration();
		          } catch (IOException e) {
		        	  JOptionPane.showMessageDialog(null, "Erreur implementation coloration!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		          }
		          String mess = "Coloration ! \n";       
		          JOptionPane.showMessageDialog(null, mess, "Algorithmes", JOptionPane.INFORMATION_MESSAGE);        
		        }            
		      });
	
	    algo.add(algo1);
	    algo.add(algo2);
	
	    //Menu À propos
	    aProposItem.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent arg0) {
	          String mess = "Voici L'interface réalisé lors du projet Java, \nCe projet a été réalisé dans le cadre du master informatique de Lyon 2 par \n Anass Chahbouni, Ladislas Heude et Théo Ripoche \n";        
	          JOptionPane.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE);        
	        }            
	      });
	    aPropos.add(aProposItem);
	
	    //Ajout des menus dans la barre de menus
	    menuBar.add(fichier);
	    menuBar.add(algo);
	    menuBar.add(aPropos);
	
	    //Ajout de la barre de menus sur la fenêtre
	    this.setJMenuBar(menuBar);
	  }
  
}