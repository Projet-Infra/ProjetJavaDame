package model.piece;

import java.awt.Color;

import model.joueur.Joueur;
import model.plateau.Case;

public abstract class Piece implements Bouge{
	private Color couleur;
	private Joueur proprietaire;
	private Case position;
	
		
	
	public Piece(Joueur proprietaire, Color couleur, Case position) {
		this.couleur = couleur;
		this.position = position;
		this.proprietaire = proprietaire;
	}
	
	



	public Color getCouleur() {
		return couleur;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public Case getPosition() {
		return position;
	}


	public void setPosition(Case position) {
		this.position = position;
	};
	
	
	
	public Joueur getProprietaire() {
		return proprietaire;
	}




	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}


	



	public String toString() {
		String s = "";
		
		String couleur = "";
		if(this.getCouleur().equals(Color.BLACK)) {
			couleur = "Noir";
		}
		else {
			couleur = "Blanc";
		}
		
		s+="Couleur: ("+couleur+") "+this.getProprietaire().getNomEntier()+" > "+this.getPosition();
		
		return s;
	}
	
	
}
