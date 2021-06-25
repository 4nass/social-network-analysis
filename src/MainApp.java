
import java.io.IOException;

import view.Fenetre;
 
public class MainApp {
  public static void main(String[] args){
	  try {
		view.Fenetre fen = new Fenetre();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  //ZFenetre zFen = new ZFenetre();
	  /*String[] sexe = {"masculin", "féminin", "indéterminé"};
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = (String)jop.showInputDialog(null, 
	      "Veuillez indiquer votre sexe !",
	      "Gendarmerie nationale !",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      sexe,
	      sexe[2]);
	    jop2.showMessageDialog(null, "Votre sexe est " + nom, "Etat civil", JOptionPane.INFORMATION_MESSAGE);*/
  }       
}