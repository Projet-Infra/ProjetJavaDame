package model.plateau;

import java.util.ArrayList;

import model.coordonees.Coordonnees;
import model.piece.Piece;

public class Case {
	private Coordonnees coordonnees;
	private Piece occupeePar;
	private boolean occupee;
	
	
	private int x1 = 0;
	private int y1 = 0;
	private int x2 = 0;
	private int y2 = 0;
	private int longueur = x2 - x1;
	private int largeur = y2 - y1;
	
	private ArrayList<Case> caseNord = new ArrayList<Case>();
	private ArrayList<Case> caseSud = new ArrayList<Case>();
	private ArrayList<Case> caseEst = new ArrayList<Case>();
	private ArrayList<Case> caseOuest = new ArrayList<Case>();
	
	private ArrayList<Case> caseNordEst = new ArrayList<Case>();
	private ArrayList<Case> caseNordOuest = new ArrayList<Case>();
	private ArrayList<Case> caseSudEst = new ArrayList<Case>();
	private ArrayList<Case> caseSudOuest = new ArrayList<Case>();
	
	
	/**
	 * 
	 * Méthode d'initialisation
	 * <hr>
	 * Permet de créer toutes les cases adjacentes aux autres & permet
	 * également d'utiliser les vecteurs de mouvements
	 * 
	 * @param cible
	 * @param orientation
	 */
	public void construitCaseAdjacentes(Case cible, String orientation) {
		
		
		switch(orientation){
			case "Nord":
				this.getCaseNord().add(cible);
				break;
			case "Sud":
				this.getCaseSud().add(cible);
				break;
			case "Est":
				this.getCaseEst().add(cible);
				break;
			case "Ouest":
				this.getCaseOuest().add(cible);
				break;
			case "Nord-Ouest":
				this.getCaseNordOuest().add(cible);
				break;
			case "Nord-Est":
				this.getCaseNordEst().add(cible);
				break;
			case "Sud-Ouest":
				this.getCaseSudOuest().add(cible);
				break;
			case "Sud-Est":
				this.getCaseSudEst().add(cible);
				break;
		}
		
		
		
	}
	
	
	public String toString() {
		String s = "";
		
		s+="Case ("+this.getCoordonnees().getX()+", "+this.getCoordonnees().getY()+")";
		
		//if()
		
		
		 return s;
	}
	
	public Case(Coordonnees coo) {
		this.coordonnees = coo;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public Piece getOccupeePar() {
		return occupeePar;
	}

	public void setOccupeePar(Piece occupeePar) {
		this.occupeePar = occupeePar;
	}


	public ArrayList<Case> getCaseNord() {
		return caseNord;
	}


	public void setCaseNord(ArrayList<Case> caseNord) {
		this.caseNord = caseNord;
	}


	public ArrayList<Case> getCaseSud() {
		return caseSud;
	}


	public void setCaseSud(ArrayList<Case> caseSud) {
		this.caseSud = caseSud;
	}


	public ArrayList<Case> getCaseEst() {
		return caseEst;
	}


	public void setCaseEst(ArrayList<Case> caseEst) {
		this.caseEst = caseEst;
	}


	public ArrayList<Case> getCaseOuest() {
		return caseOuest;
	}


	public void setCaseOuest(ArrayList<Case> caseOuest) {
		this.caseOuest = caseOuest;
	}


	public ArrayList<Case> getCaseNordEst() {
		return caseNordEst;
	}


	public void setCaseNordEst(ArrayList<Case> caseNordEst) {
		this.caseNordEst = caseNordEst;
	}


	public ArrayList<Case> getCaseNordOuest() {
		return caseNordOuest;
	}


	public void setCaseNordOuest(ArrayList<Case> caseNordOuest) {
		this.caseNordOuest = caseNordOuest;
	}


	public ArrayList<Case> getCaseSudEst() {
		return caseSudEst;
	}


	public void setCaseSudEst(ArrayList<Case> caseSudEst) {
		this.caseSudEst = caseSudEst;
	}


	public ArrayList<Case> getCaseSudOuest() {
		return caseSudOuest;
	}


	public void setCaseSudOuest(ArrayList<Case> caseSudOuest) {
		this.caseSudOuest = caseSudOuest;
	}


	public boolean isOccupee() {
		return occupee;
	}


	public void setOccupee(boolean occupee) {
		this.occupee = occupee;
	}


	public int getX1() {
		return x1;
	}


	public void setX1(int x1) {
		this.x1 = x1;
	}


	public int getY1() {
		return y1;
	}


	public void setY1(int y1) {
		this.y1 = y1;
	}


	public int getX2() {
		return x2;
	}


	public void setX2(int x2) {
		this.x2 = x2;
	}


	public int getY2() {
		return y2;
	}


	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	
}
