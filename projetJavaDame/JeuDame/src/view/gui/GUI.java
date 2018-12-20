package view.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.core.Main;
import model.joueur.Joueur;
import model.plateau.Case;
import view.View;

public class GUI extends View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final Color[] listeCouleurCases = {Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY,
			Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE,
			Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY,
			Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE,
			Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY,
			Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.white,
			Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY,
			Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE,
			Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY,
			Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE, Color.GRAY, Color.WHITE};
	
	private JPanel contentPane;
	private ArrayList<JPanel> listeCases = new ArrayList<JPanel>();
	private Joueur P1;
	private Joueur P2;
	//private Controller controller;
	
	/**
	 * Lancement de la GUI
	 * **/
	 
/*	public static void main(String[] args) throws NullPointerException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(Main.partie.getJ1(), Main.partie.getJ2());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Création de la fenêtre
	 * @throws IOException 
	 */
	public GUI(Joueur model, Controller controller, Joueur P1, Joueur P2) throws IOException {
		super(model, controller);
		this.P1 = P1;
		this.P2 = P2;
		
		//Icone
        Image i = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Icone.jpg"));
        
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 680, 680);
		contentPane = new JPanel();
		//contentPane.setOpaque(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		
		this.setTitle("Jeu de dame");
		this.setIconImage(i);
		
		setContentPane(contentPane);
		
		int positionX = 0;
		int positionY = 0;
		
		
		for(int x = 0; x < 10; x++) {
			
			for(int y = 0; y < 10; y++) {
				
				
		    	Case c = Main.partie.getPlateau().getListeCases().get(99 - (x * 10 + (9-y)));
		    	//Case c = Main.partie.getPlateau().getListeCases().get(x * 10 + y);
				JPanel cs = new JPanel();
				
				if(c.isOccupee() == false) {
					cs.setBackground(listeCouleurCases[x* 10 + y]);
				}
				else {
					if(c.getOccupeePar().getProprietaire().equals(P1)) {
						cs.setBackground(Color.BLUE);
					}
					if(c.getOccupeePar().getProprietaire().equals(P2)) {
						cs.setBackground(Color.RED);
					}
				}
				cs.setBounds(positionX, positionY, 64, 64);
				
				GUI.addCaseListener(this, x, y, cs);
				cs.setOpaque(true);
				contentPane.add(cs);
				this.listeCases.add(cs);
				
				positionX = positionX + 64;
				

			}
			
			positionY = positionY + 64;
			positionX = 0;
			
		}
		
		
	
	}
	
	public void rechargeCouleurCases() {
		
		for(int x = 0; x < 10; x++) {
			
			for(int y = 0; y < 10; y++) {
				
		    	Case c = Main.partie.getPlateau().getListeCases().get(99 - (x * 10 + (9-y)));
				JPanel cs = this.listeCases.get(x* 10 + y);
				
				if(c.isOccupee() == false) {
					cs.setBackground(listeCouleurCases[x* 10 + y]);
				}
				else {
					if(c.getOccupeePar().getProprietaire().equals(this.P1)) {
						cs.setBackground(Color.BLUE);
					}
					if(c.getOccupeePar().getProprietaire().equals(this.P2)) {
						cs.setBackground(Color.RED);
					}
				}
				cs.setOpaque(true);
				

			}
			
		}
	}
	

	
	public static void addCaseListener(GUI gui, int x, int y, JPanel cs) {
		
		cs.addMouseListener(new MouseAdapter() {
			
			
		    @Override
		    public void mousePressed(MouseEvent e) throws NullPointerException {
		    	if(Main.verouille == true) {
		    		System.out.println("C'est le tour de l'adversaire");
		    		return;
		    	}
		    	
		    	//Formule permettant de dire au programme quelle case se situe sur tel JPanel
		    	// Attention, il faut inverser les x et les y parce-que la gui se construit d'une manière différente
		    	Case c = Main.partie.getPlateau().getListeCases().get(99 - (x * 10 + (9-y)));

		        System.out.println("Case cliquée: "+c.getCoordonnees().toString());
		        try {
		        if(c.isOccupee() == true) {
		        	if(c.getOccupeePar().getProprietaire().equals(gui.model)) {
		        		System.out.println("Piéce séléctionnée: "+c.getOccupeePar().getPosition().toString());
		        		System.out.println("Choisissez une case où bouger votre pièce");
		        		gui.model.setPieceSelectionnee(c.getOccupeePar());
		        	}
		        	if(!c.getOccupeePar().getProprietaire().equals(gui.model)) {
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseNordEst().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseNordEst().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Nord-Est", false, distance);
		        		
		        		}
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseNordOuest().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseNordOuest().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Nord-Ouest", false, distance);
		        		
		        		}
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseSudEst().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseSudEst().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Sud-Est", false, distance);
		        		
		        		}
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseSudOuest().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseSudOuest().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Sud-Ouest", false, distance);
		        		
		        		}
		        		
		        		gui.rechargeCouleurCases();
		        	}
		        }
		        }
		        catch(NullPointerException exc) {
		        	
		        }
		        
		        try {
		        	if(c.isOccupee() == false){
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseNordEst().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseNordEst().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Nord-Est", false, distance + 1);
		        		
		        		}
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseNordOuest().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseNordOuest().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Nord-Ouest", false, distance + 1);
		        		
		        		}
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseSudEst().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseSudEst().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Sud-Est", false, distance + 1);
		        		
		        		}
		        		if(gui.model.getPieceSelectionnee().getPosition().getCaseSudOuest().contains(c)) {
		        			
		        			int distance = gui.model.getPieceSelectionnee().getPosition().getCaseSudOuest().indexOf(c);
		        			
		        			gui.model.getPieceSelectionnee().bouge("Sud-Ouest", false, distance + 1);
		        		
		        		
		        	}
		        		
		        		gui.rechargeCouleurCases();
		        }
		        }
		        catch(NullPointerException exc) {
		        	
		        }
		        
		        
		    }
		    
		    @Override
            public void mouseReleased(MouseEvent e) {
              //cs.setBackground(background);
            }
		});
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	}
}
