package view;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;


public class Panneau extends JPanel {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){        
		g.drawString("Graphe: ", 50, 20);
		g.drawString(Fenetre.name+"", 100, 40);
		g.drawString("Le degrée moyen des noeuds: "+Fenetre.degreeMoyNoeuds, 500, 30);
		g.drawString("Le densité  du graphe: "+Fenetre.densite, 500, 50);
		g.drawString("Le diamètre  du graphe: "+Fenetre.diametre, 500, 70);
		g.drawString("Le coefficient moyen de clustering: "+Fenetre.coefMoyClust, 500, 90);
	}               
}
	

