package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import model.core.Main;

public class EventScanner implements Runnable{
	   private Socket socket;
	   private Socket socketDistant;
	   private BufferedReader in = null;
	   private PrintWriter out = null;
	   private String id;
	   
	   
	   /**
	    * Constructeur du scanneur d'evenements
	    * Il permet, chaque seconde de récupérer les informations envoyées par le serveur
	    * et les interpreter.
	    * 
	    * @param s Socket serveur relié
	    * @param sd socket distant
	    * @throws IOException
	    */
	public EventScanner(Socket s, Socket sd) throws IOException{
		socket = s;
		socketDistant = sd;
		
		System.out.println("Scanneur initialisé");
		
	   
	}
	
	/**
	 * Thread s'executant 5 fois par seconde
	 * .
	 * 
	 */
	@Override
	public void run() {
        while (true) {
    		
    		try {
    			while(true) { //Boucle
			
    				
    			//Out: Trafic sortant
    			//In: Trafic entrant
				out = new PrintWriter(socket.getOutputStream());
				//Lecture de ce qui se trouve sur le socket distant
	    		in = new BufferedReader(new InputStreamReader(socketDistant.getInputStream()));
	    		
	    		
	    		String id = in.readLine();
		    	
	        	String event = in.readLine();
	        	int argument = 0;
	        	try {
	        		argument = Integer.parseInt(in.readLine());
	        	}
	        	catch(NumberFormatException e) {
	        		
	        	}
	        	
	        	String orientation = in.readLine();
	        	int distance = 0;
	        	try {
	        		distance = Integer.parseInt(in.readLine());
	         	}
	        	catch(NumberFormatException e) {
	        		
	        	}
	        	this.scan(id, event, argument, orientation, distance); //Scan des informations & interprétation
	        	
		    	System.out.println("-----------[RECEP]----------");
				System.out.println("Client:"+ id);
				System.out.println("Header:"+ event);
				System.out.println("args:"+ argument);
				System.out.println("Orientation:"+ orientation);
				System.out.println("Distance:"+ distance);
		    	System.out.println("-----------[RECEP]----------");

	        	out.flush();
				
				try {
					
					//Mise en veille du Thread
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();	
				}
				
    			}
    			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
			
			
        }
		
	}

	/**
	 * Envoi de packet spécifiques visant le déplacement des pièces
	 * 
	 * @param event Nom de l'événement
	 * @param args Nombre argument
	 * @param orientation Orientation du déplacement
	 */
	public void envoi(String event, String args, String orientation, String distance ) {
		System.out.println("Tentative d'envoi d'un ordre de déplacement");
		Main.verouille = true; //Verouillage du client expéditeur
		
		out.println(this.getId());
		out.println(event);
		out.println(args);
		out.println(orientation);	
		out.println(distance);
		
    	System.out.println("-----------[ENVOI]----------");
		System.out.println("Client:"+ this.getId());
		System.out.println("Header:"+ event);
		System.out.println("args:"+ args);
		System.out.println("Orientation:"+ orientation);
		System.out.println("Distance:"+ distance);
    	System.out.println("-----------[ENVOI]----------");
		
    	
    	//On envoie les informations au serveur
		out.flush();
	}
	
	/**
	 * Méthode client
	 * <br>
	 * Permet d'interpreter les informations serveur.
	 * 
	 * @param event Nom de l'événement
	 * @param id Id de l'expediteur
	 * @param args Nombre argument
	 * @param orientation Orientation du déplacement
	 */
	private void scan(String id, String event, int args, String orientation, int distance) {
		
		
		//Annulation d'un evenement provenant du même scanner
		if(id.equalsIgnoreCase(this.id) ) {
			System.out.println("Ordre recu provenant de ce client, annulation");
			out.flush();
			return;
		}
		
		
		//Le client appartient au joueur 2
		if(event.equalsIgnoreCase("Vous êtes assigné au joueur 2")) {
			
			Main.console.setModel(Main.partie.getJ2());
			Main.controller.setModel(Main.partie.getJ2());
			Main.f.setModel(Main.partie.getJ2());
			
			this.setId(Main.partie.getJ2().getNom());
			
			Main.partie.getJ2().setScanner(this);
			Main.joueurNonControlle = Main.partie.getJ1();
			Main.verouille = false;
		}
		//Le client appartient au joueur 1
		if(event.equalsIgnoreCase("Vous êtes assigné au joueur 1")) {
			
			Main.console.setModel(Main.partie.getJ1());
			Main.controller.setModel(Main.partie.getJ1());
			Main.f.setModel(Main.partie.getJ1());
			
			this.setId(Main.partie.getJ1().getNom());
			
			Main.partie.getJ1().setScanner(this);
			Main.joueurNonControlle = Main.partie.getJ2();
			Main.verouille = true;
		}
		//Ordre de déplacement recu dans le Thread
		if(event.equalsIgnoreCase("placement")) {
			System.out.println(id);
			System.out.println(this.getId());
        	out.flush();
			
        	//On fait bouger la pièce de l'autre joueur sur notre jeu
			Main.joueurNonControlle.getListePieces().get(args).bouge(orientation, true, distance);
			Main.verouille = false; //Déverouillage du client
			Main.f.rechargeCouleurCases(); //Rechargement de la gui
		}
		
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Socket getSocketDistant() {
		return socketDistant;
	}

	public void setSocketDistant(Socket socketDistant) {
		this.socketDistant = socketDistant;
	}

}
