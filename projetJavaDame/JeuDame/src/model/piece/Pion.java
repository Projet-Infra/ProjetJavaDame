package model.piece;

import java.awt.Color;

import model.core.Main;
import model.joueur.Joueur;
import model.plateau.Case;

public class Pion extends Piece{

	public Pion(Joueur proprietaire, Color couleur, Case position) {
		super(proprietaire, couleur, position);
	}

	
	/**
	 * <b> Méthode interne </b>
	 * <hr>
	 * <i> Elle faut bouger la pièce en suivant l'orientation
	 * 
	 * 
	 * @param orientation Vecteur de mouvement
	 */
	@Override
	public void bougeInterne(String orientation, boolean depuisServeur, int nombreCases) {
		//Il faut faire attention à vérifier 2 choses avant d'executer le code
		//1> Le client actuel est verouillé par le serveur
		//2> La méthode bougeInterne ne provient pas du serveur
		//
		//Si ces deux conditions sont respectées, l'ordre est annulé
    	if(Main.verouille == true && depuisServeur == false) {
    		//System.out.println("C'est le tour de l'adversaire");
    		return;
    	}
    	
    	if(nombreCases > 1) {
    		System.out.println("Les pions ne peuvent bouger que d'une case");
    		return;
    	}
		/*
		 * Il doit également vérifier que la case ciblée n'est pas occupée par une pièce alliée.
		 * 
		 */
			
		//Récupère la case qui se trouve à l'orientation spécifiée.
		
		//Annulation d'un déplacement en ligne droite
		if(orientation.equals("Nord") || orientation.equals("Sud") || orientation.equals("Est") || orientation.equals("Ouest")) {
			return;
		}
		
		System.out.println("");
		
		Case cible = Pion.traduireStringVersCaseOrientation(this.getPosition(), orientation, nombreCases);
		System.out.println("Coordonnées actuelles >"+this.getPosition().getCoordonnees().toString());
		
		try {
			cible.equals(null);
		}
		catch(NullPointerException e) {
			System.out.println("Aucune case au "+orientation+" de notre position actuelle");
			return;
		}
			
			//Eviter le pointeur null lorsque la case est vide
			try {
				
				if(cible.isOccupee() == false) {
					//Changement de position
					this.getPosition().setOccupee(false);
					this.getPosition().setOccupeePar(null);
					this.setPosition(cible);
					
					cible.setOccupee(true);
					cible.setOccupeePar(this);
					
					System.out.println("("+this.getProprietaire().getNomEntier()+") | Pièce déplacée aux coordonnées "+this.getPosition().getCoordonnees().toString());
				
					if(depuisServeur == false) {
						this.getProprietaire().getScanner().envoi("placement", ""+this.getProprietaire().getListePieces().indexOf(this), orientation, ""+nombreCases);
					}
					//Main.partie.passe();
					
					this.testSiTransformation();
					
				}
				
				else if(cible.isOccupee() == true && cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occupée par une pièce alliée");
					return;
				}
				
				else if(cible.isOccupee() == true && !cible.getOccupeePar().getCouleur().equals(this.getCouleur())) {
					System.out.println("Case occupée par une pièce ennemie");
					System.out.println("Tentative de destruction");
					
					if(Pion.traduireStringVersCaseOrientation(cible, orientation, nombreCases).isOccupee() == true) {
						return;
					}
					else {
						System.out.println("La pièce a été détruite");
						cible.getOccupeePar().getProprietaire().getListePieces().remove(cible.getOccupeePar());
						this.getProprietaire().getListePiecesPrises().add(cible.getOccupeePar());
						
						cible.getOccupeePar().setPosition(null);
						cible.setOccupee(false);
						cible.setOccupeePar(null);
						
						
						//Changement de position
						this.getPosition().setOccupee(false);
						this.getPosition().setOccupeePar(null);					
			
						this.setPosition(Pion.traduireStringVersCaseOrientation(cible, orientation, nombreCases));
						
						this.getPosition().setOccupee(true);
						this.getPosition().setOccupeePar(this);
						System.out.println("("+this.getProprietaire().getNomEntier()+") | Pièce déplacée aux coordonnées "+this.getPosition().getCoordonnees().toString());
						System.out.println("");
						
						if(depuisServeur == false) {
							this.getProprietaire().getScanner().envoi("placement", ""+this.getProprietaire().getListePieces().indexOf(this), orientation, ""+nombreCases);
						}
						
						this.testSiTransformation();
						
					}
					
					return;
				}
				
				
			}
			catch(NullPointerException e) {
				System.out.println("Ordre de mouvement impossible");
			}
			
			
			
			
		
	}
	
	/**
	 * Méthode qui fait bouger la pièce vers une case.
	 *<hr>
	 * @param orientation vecteur de mouvement
	 */
	@Override
	public void bouge(String orientation, boolean depuisServeur, int nombreCases) {
		
			this.bougeInterne(orientation, depuisServeur, nombreCases);

		
		
	}
	

	@Override
	public void testSiTransformation() {
		if(this.getProprietaire().getTransformateurDame().contains(this.getPosition())) {
			if(this.getProprietaire().getListePiecesPrises().size() > 0) {
				Dame dame = new Dame(this.getProprietaire(), this.getCouleur(), this.getPosition());
				this.getPosition().setOccupeePar(dame);
				
				this.getProprietaire().getListePieces().remove(this);
				this.getProprietaire().getListePieces().add(dame);
				
				this.getProprietaire().getListePiecesPrises().remove(0);
				
			}
			else if(this.getProprietaire().getListePiecesPrises().size() == 0) {
				System.out.println("Transformation en dame impossible | Cause > Pas de pions ennemis en stock");
			}
		}
		
	}
	
	/**
	 * 
	 * Méthode utilisateur
	 * <hr>
	 * Permet de traduire un vecteur de mouvement en une case précise
	 * 
	 * @param caseSource case source où appliquer le déplacement
	 * @param orientation Vecteur de mouvement
	 * @return La case ciblée par le mouvement 
	 */
	public static Case traduireStringVersCaseOrientation(Case caseSource, String orientation, int distance) {
		Case c = null;
		
		switch(orientation){
		case "Nord":
			c = caseSource.getCaseNord().get(distance - 1);
			break;
		case "Sud":
			c = caseSource.getCaseSud().get(distance - 1);
			break;
		case "Est":
			c = caseSource.getCaseEst().get(distance - 1);
			break;
		case "Ouest":
			c = caseSource.getCaseOuest().get(distance - 1);
			break;
		case "Nord-Ouest":
			c = caseSource.getCaseNordOuest().get(distance - 1);
			break;
		case "Nord-Est":
			c = caseSource.getCaseNordEst().get(distance - 1);
			break;
		case "Sud-Ouest":
			c = caseSource.getCaseSudOuest().get(distance - 1);
			break;
		case "Sud-Est":
			c = caseSource.getCaseSudEst().get(distance - 1);
			break;
	}
		
		return c;
	}

}
