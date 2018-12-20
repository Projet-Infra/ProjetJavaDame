package model.joueur;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import model.piece.Piece;
import model.plateau.Case;
import sockets.EventScanner;

public class Joueur extends Observable{
	private String nom;
	private String prenom;
	
	private ArrayList<Piece> listePieces = new ArrayList<Piece>();
	private ArrayList<Piece> listePiecesPrises = new ArrayList<Piece>();
	
	private final ArrayList<Case> transformateurDame = new ArrayList<Case>();
	
	private EventScanner scanner;
	private Socket socket;
	
	private Piece pieceSelectionnee = null;
	
	private int score;
	
	private Joueur ennemi;
	
	/**
	 * Constructeur du joueur
	 * 
	 * @param nom > Nom du joueur
	 * @param prenom > Prenom du joueur
	 */
	public Joueur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		
		
	}
	
	
	/**
	 * 
	 * Le joueur prend la pièce définie de la victime
	 * @param victime > joueur qui perd sa pièce.
	 * @param numero > Index de la pièce dans la liste du joueur qui perd sa pièce
	 * 
	 */
	public void prendPiece(Joueur victime, int numero) {
		this.getListePiecesPrises().add(victime.getListePieces().get(numero));
		
		victime.getListePieces().remove(numero);
		
		
		System.out.println(this.getNomEntier()+" prend la pièce de "+victime.getNomEntier());
	}
	

    
	
	
	/**
	 * Méthode permettant de renvoyer "Nom Prenom"
	 * 
	 * @return le nom + le prénom du joueur
	 */
	public String getNomEntier() {
		String s = this.getNom()+" "+this.getPrenom();
		return s;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Piece> getListePieces() {
		return listePieces;
	}

	public void setListePieces(ArrayList<Piece> listePieces) {
		this.listePieces = listePieces;
	}

	public ArrayList<Piece> getListePiecesPrises() {
		return listePiecesPrises;
	}

	public void setListePiecesPrises(ArrayList<Piece> listePiecesPrises) {
		this.listePiecesPrises = listePiecesPrises;
	}


	public Piece getPieceSelectionnee() {
		return pieceSelectionnee;
	}


	public void setPieceSelectionnee(Piece pieceSelectionnee) {
		this.pieceSelectionnee = pieceSelectionnee;
	}


	public EventScanner getScanner() {
		return scanner;
	}


	public void setScanner(EventScanner scanner) {
		this.scanner = scanner;
	}


	public ArrayList<Case> getTransformateurDame() {
		return transformateurDame;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public Joueur getEnnemi() {
		return ennemi;
	}


	public void setEnnemi(Joueur ennemi) {
		this.ennemi = ennemi;
	}


	
	
}
