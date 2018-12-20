package model.core;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.Controller;
import model.coordonees.Coordonnees;
import model.joueur.Joueur;
import model.partie.Partie;
import model.piece.Pion;
import model.plateau.Plateau;
import sockets.EventScanner;
import view.View;
import view.console.ConsoleView;
import view.gui.GUI;

public class Main {
	public static Controller controller;
	public static View console;
	public static GUI f;
	public static Joueur joueurNonControlle;
	
	public static Long temps = 0L;
	
	public static boolean verouille;
	
	public static Partie partie;
	
	static BufferedReader in;
	static PrintWriter out;
	
	public static int nbreJoueursConnectes = 0;
	
	public static void main(String[] args) throws IOException {	
		
		Plateau p1 = new Plateau(); // Création du plateau
		Coordonnees.construireListeCoordonnees(); //Construction de la liste des coordonnées
		p1.construireListeCases(); //Construction de la liste des cases sur le plateau
		
		
		Joueur j1 = new Joueur("Joël", "Cyn"); //Création du joueur 1
		Joueur j2 = new Joueur("Nadia", "èmmbé"); //Création du joueur 2
		
		//1 c'est l'id
		partie = new Partie(1, p1, j1, j2);
		
		controller = new Controller(j1);
		
		console = new ConsoleView(j1, controller);
		
		
		controller.addView(console);
		
		
		
		//Ne pas oublier de dire au jeu sur quelles cases il faut poser les pièces
		Integer[] listeIndexBlanc = p1.getListeindexblanc();
		Integer[] listeIndexNoir = p1.getListeindexnoir();
		
		
		//Boucle ajoutant 20 pièces à chaque joueur
		for(int i = 0; i < 20; i++) {
			
			//Création d'un pion noir de couleur noire se situant dans la liste des index noirs
			Pion pionNoir = new Pion(j2, Color.BLACK, p1.getListeCases().get(listeIndexNoir[i]));
			
			//Création d'un pion blanc de couleur blanche se situant dans la liste des index blancs
			Pion pionBlanc = new Pion(j1, Color.WHITE, p1.getListeCases().get(listeIndexBlanc[i]));
			
			//Ajout des pions dans la liste des pions de chaque joueur
			j1.getListePieces().add(pionBlanc);
			j2.getListePieces().add(pionNoir);
			
			//Ne pas oublier de dire à la case qu'un pion se situe sur elle
			 p1.getListeCases().get(listeIndexNoir[i]).setOccupeePar(pionNoir);
			 p1.getListeCases().get(listeIndexBlanc[i]).setOccupeePar(pionBlanc);
			 
			 p1.getListeCases().get(listeIndexNoir[i]).setOccupee(true);
			 p1.getListeCases().get(listeIndexBlanc[i]).setOccupee(true);
			
		}
		
		
		/*
		 * Cette boucle permet de calculer les cases adjacentes de toutes les cases présente dans la liste.
		 * 
		 *      NO     N     NE
		 * 				
		 * 
		 * 		O	Centre   E
		 * 
		 * 	
		 * 		SO     S     SE
		 * 
		 */
		for(int i = 0; i < p1.getListeCases().size(); i++) {
			
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() < 9) {
				for(int boucle = 0; boucle < (9 - p1.getListeCases().get(i).getCoordonnees().getX()) ; boucle++) {
					
					p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + 10* (boucle+1)), "Nord");
					
				}
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() > 0) {
				for(int boucle = 0; boucle < p1.getListeCases().get(i).getCoordonnees().getX() ; boucle++) {
					
					p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - 10* (boucle+1)), "Sud");
				
				}
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getY() > 0) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - 1), "Ouest");
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getY() < 9) {
				p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + 1), "Est");		
			}
			
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() > 0 && p1.getListeCases().get(i).getCoordonnees().getY() > 0) {
				int x = p1.getListeCases().get(i).getCoordonnees().getX();
				int y = p1.getListeCases().get(i).getCoordonnees().getY();
				
				if(x > y || x == y) {
				
					for(int boucle = 1; boucle < p1.getListeCases().get(i).getCoordonnees().getY() ; boucle++) {
					
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - (11 * (boucle))), "Sud-Ouest");	
					
					}
				}
				if(x < y) {
					
					for(int boucle = 1; boucle < p1.getListeCases().get(i).getCoordonnees().getX() ; boucle++) {
					
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - (11 * (boucle))), "Sud-Ouest");	
					
					}
				}
				
			}
			if(p1.getListeCases().get(i).getCoordonnees().getX() > 0 && p1.getListeCases().get(i).getCoordonnees().getY() < 9) {
				int x = p1.getListeCases().get(i).getCoordonnees().getX();
				int y = p1.getListeCases().get(i).getCoordonnees().getY();
				
				if(x > y || x == y) {
				
					for(int boucle = 1; boucle < p1.getListeCases().get(i).getCoordonnees().getY() ; boucle++) {
					
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - (9 * (boucle))), "Sud-Est");	
					
					}
				}		
				if(x < y) {
					
					for(int boucle = 1; boucle < p1.getListeCases().get(i).getCoordonnees().getX() ; boucle++) {
					
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i - (9 * (boucle))), "Sud-Est");	
					
					}
				}
			}
			
			if(p1.getListeCases().get(i).getCoordonnees().getX() < 9 && p1.getListeCases().get(i).getCoordonnees().getY() > 0) {
				int x = p1.getListeCases().get(i).getCoordonnees().getX();
				int y = p1.getListeCases().get(i).getCoordonnees().getY();
				
				if(x > y || x == y) {
				
					for(int boucle = 1; boucle < p1.getListeCases().get(i).getCoordonnees().getY() ; boucle++) {
						System.out.println(p1.getListeCases().get(i).toString()+"   Boucle "+boucle+" Index:"+p1.getListeCases().indexOf(p1.getListeCases().get(i))+"/"+p1.getListeCases().indexOf(p1.getListeCases().get(i + (9 * (boucle)))));
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + (9 * (boucle))), "Nord-Ouest");	
					
					}	
				}
				if(x < y) {
					
					for(int boucle = 1; boucle < p1.getListeCases().get(i).getCoordonnees().getX() ; boucle++) {
					
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + (9 * (boucle))), "Nord-Ouest");	
					
					}	
				}
			}
			if(p1.getListeCases().get(i).getCoordonnees().getX() < 9 && p1.getListeCases().get(i).getCoordonnees().getY() < 9) {
				int x = p1.getListeCases().get(i).getCoordonnees().getX();
				int y = p1.getListeCases().get(i).getCoordonnees().getY();
				
				if(x < y || x == y) {
				
					for(int boucle = 1; boucle < 9 - p1.getListeCases().get(i).getCoordonnees().getY() ; boucle++) {
						System.out.println(p1.getListeCases().get(i).toString()+"   Boucle "+boucle+" Index:"+p1.getListeCases().indexOf(p1.getListeCases().get(i))+"/"+p1.getListeCases().indexOf(p1.getListeCases().get(i + (11 * (boucle)))));
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + (11 * (boucle))), "Nord-Est");	
					
					}	
				}	
				if(x > y) {
					
					for(int boucle = 1; boucle < 9 - p1.getListeCases().get(i).getCoordonnees().getX() ; boucle++) {
						System.out.println(p1.getListeCases().get(i).toString()+"   Boucle "+boucle+" Index:"+p1.getListeCases().indexOf(p1.getListeCases().get(i))+"/"+p1.getListeCases().indexOf(p1.getListeCases().get(i + (11 * (boucle)))));
						p1.getListeCases().get(i).construitCaseAdjacentes(p1.getListeCases().get(i + (11 * (boucle))), "Nord-Est");	
					
					}	
				}
			}
			
		}
	
		
		
			f = new GUI(Main.partie.getJ1(), controller, Main.partie.getJ1(), Main.partie.getJ2());
			f.setVisible(true);
			
			
			
			//Partie socket
			Thread eventScanner = null;

			try {
					//Socket client
					Socket socket = new Socket(InetAddress.getLocalHost(),4123);
					
					Main.partie.getJ1().socket = socket;
					
			        System.out.println("Demande de connexion joueur");

			        in = new BufferedReader (new InputStreamReader (Main.partie.getJ1().socket.getInputStream()));
			        
					out = new PrintWriter(Main.partie.getJ1().socket.getOutputStream());
					
					
					//Ajout du scanneur au client connecté
					Main.partie.getJ1().setScanner(new EventScanner(Main.partie.getJ1().socket, Main.partie.getJ1().socket));
					eventScanner = new Thread(Main.partie.getJ1().getScanner());
					eventScanner.start(); //Lancement du scanneur

					
			        
			        //socket.close();
			       
			}catch (UnknownHostException e) {
				
				e.printStackTrace();
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		
			

	}
	
	/*
	private static void runTimer() {
		Thread temps = new Thread(new Timer());
		temps.run();
	}
	*/

}
