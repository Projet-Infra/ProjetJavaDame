package model.coordonees;

import java.util.ArrayList;

public class Coordonnees {
	
	private int x;
	private int y;
	
	private static ArrayList<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();
	
	
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;

	}
	
	public String toString() {
		String s = "";
		
		s+= "("+this.getX()+", "+this.getY()+")"; 
		
		return s;
	}
	
	
	/**
	 * Méthode qui permet de construire la liste de coordonnées.
	 * Liste accessible grace à Coordonnees.getListeCoordonnees();
	 * 
	 */
	public static void construireListeCoordonnees() {
		for(int x = 0; x < 10; x++) {
			
			for(int y = 0; y < 10; y++) {
				Coordonnees.listeCoordonnees.add(new Coordonnees(x, y));
			}
			
		}
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public static ArrayList<Coordonnees> getListeCoordonnees() {
		return listeCoordonnees;
	}


	public static void setListeCoordonnees(ArrayList<Coordonnees> listeCoordonnees) {
		Coordonnees.listeCoordonnees = listeCoordonnees;
	}
	
	
}
