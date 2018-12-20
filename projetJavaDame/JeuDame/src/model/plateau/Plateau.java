package model.plateau;

import java.util.ArrayList;

import model.coordonees.Coordonnees;

public class Plateau {
	private ArrayList<Case> listeCases = new ArrayList<Case>();
	
	private final Integer[] listeIndexNoir = {99, 97, 95, 93, 91, 88, 86, 84, 82, 80, 79, 77, 75, 73, 71, 68, 66, 64, 62, 60};
	private final Integer[] listeIndexBlanc = {0, 2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, 28, 31, 33, 35, 37, 39};
	
	
	
	
	public Plateau() {
		
	}
	
	
	/**
	 * Méthode qui construit la liste des cases grace à la liste des coordonnées.
	 * 
	 */
	public void construireListeCases() {
		for(int i = 0; i < Coordonnees.getListeCoordonnees().size(); i++) {
			this.listeCases.add(new Case(Coordonnees.getListeCoordonnees().get(i)));
		}
	}


	public ArrayList<Case> getListeCases() {
		return listeCases;
	}


	public void setListeCases(ArrayList<Case> listeCases) {
		this.listeCases = listeCases;
	}


	public Integer[] getListeindexnoir() {
		return listeIndexNoir;
	}


	public Integer[] getListeindexblanc() {
		return listeIndexBlanc;
	}
	
}
