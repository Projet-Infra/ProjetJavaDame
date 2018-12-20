package controller;


import java.util.ArrayList;

import model.core.Main;
import model.joueur.Joueur;
import view.View;

public class Controller {
	Joueur model;  
	View vue;
	
	public Controller(Joueur model) {
		this.model = model;
	}
	
	public void addView(View vue) {
		this.vue = vue;
		
	}
	
	/**
	 * Méthode controlleur
	 * <hr>
	 * Permet d'intéragir avec la méthode bouge des pions.
	 * 
	 * @param index numéro de la pièce
	 * @param orientation vecteur de mouvement 
	 */
	public void bougeControle(int index, String orientation, int distance) {
		model.getListePieces().get(index).bouge(orientation, false, distance);
		Main.f.rechargeCouleurCases();
	}
	
	/**
	 * Méthode d'information
	 * <hr>
	 * Elle affiche les informations sur la liste des pièces du joueur modèle
	 * 
	 */
	public void informationSurPieces() {
		
		System.out.println("===========================");
		System.out.println("Vous avez encore "+model.getListePieces().size()+" pièces");
		System.out.println("");
		for(int i = 0; i < model.getListePieces().size(); i++) {
			System.out.println("Pièce n°"+i+" | Coordonnées >"+model.getListePieces().get(i).getPosition().getCoordonnees().toString());
		}
		System.out.println("");
		System.out.println("===========================");
		
	}
	
	/**
	 * Méthode d'information
	 * <hr>
	 * Elle affiche le plateau sous forme de caractères
	 * <b><br>- 0 pour les cases vides
	 * <b><br>- La première lettre du prénom pour les cases occupées
	 * 
	 */
	public void informationSurPlateau() {
		
		System.out.println("=============[Etat du plateau]=============");
		ArrayList<String> liste = new ArrayList<String>();
				
		for(int x = 0; x < 10; x++) {
			String s = "";
			
			for(int y = 0; y < 10; y++) {
				if(Main.partie.getPlateau().getListeCases().get(10 * x + y).isOccupee() == true) {
					char lettre = Main.partie.getPlateau().getListeCases().get(10 * x + y).getOccupeePar().getProprietaire().getNom().charAt(0);
					s+= "  "+lettre+"  ";
				}
				else if(Main.partie.getPlateau().getListeCases().get(10 * x + y).isOccupee() == false) {
					s+= "  0  ";
				};
			}
			
			liste.add(x, s);
		}
		
		
		for(int i = 9; i >= 0; i--) {
			System.out.println(liste.get(i));
		}
		
		System.out.println("=============[Etat du plateau]=============");
		
	}
	
	public Joueur getModel() {
		return model;
	}

	public void setModel(Joueur model) {
		this.model = model;
	}

	public View getVue() {
		return vue;
	}

	public void setVue(View vue) {
		this.vue = vue;
	}
	

}
