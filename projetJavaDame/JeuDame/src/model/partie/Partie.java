package model.partie;

import model.core.Main;
import model.joueur.Joueur;
import model.plateau.Plateau;

public class Partie{
	private int id;
	private Joueur j1;
	private Joueur j2;
	
	private Plateau plateau;
	
	/**<hr>
	 * Constructeur de la partie
	 * <b><br>(à utiliser)</b>
	 * <hr>
	 * @param id > Numéro de la partie en cours (Pour l'instant inutile)
	 * @param j1 > 
	 * @param j2
	 * 
	 */
	public Partie(int id, Plateau plateau, Joueur j1, Joueur j2) {
		this.id = id;
		this.j1 = j1;
		this.j2 = j2;
		this.plateau = plateau;
	}
	
	/**<hr>
	 * Méthode dépréciée
	 * <b><br>(à ne pas utiliser)</b>
	 * <hr>
	 * 
	 * @param j1 > Joueur 1
	 * @param j2 > Joueur 2
	 */
	@Deprecated
	public Partie(Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
	}
	
	/**
	 * Méthode d'arrêt
	 * <hr>
	 * Elle sera appelée à chaque déplacement pour vérifier qu'un joueur
	 * n'aie pas perdu tout ses pions.
	 * 
	 */
	public void stop() {
		if(this.getJ1().getListePieces().size() == 0) {
			
			System.out.println("======================");
			System.out.println("la partie est terminée !");
			System.out.println("Gagnant >"+j2.getNomEntier());
			System.out.println("======================");
			
		}
	}
	
	public void passe() {
		if(Main.console.model.equals(j1)) {
			
			System.out.println("");
			System.out.println("C'est au tour de "+j2.getNomEntier());
			Main.controller.setModel(j2);
			Main.console.setModel(j2);
			Main.f.setModel(j2);
		}
		else if(Main.console.model.equals(j2)) {
			
			System.out.println("");
			System.out.println("C'est au tour de "+j1.getNomEntier());
			Main.controller.setModel(j1);
			Main.console.setModel(j1);
			Main.f.setModel(j1);
		}
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	
}
