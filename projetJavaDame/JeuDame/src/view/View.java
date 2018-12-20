package view;

import java.util.Observer;

import javax.swing.JFrame;

import controller.Controller;
import model.joueur.Joueur;

public abstract class View extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Joueur model;

	protected Controller controller;
	
	protected View(Joueur model, Controller controller) {
		this.model = model;
		this.controller = controller;

		model.addObserver(this);
	}

	public abstract void affiche(String string);

	public Joueur getModel() {
		return model;
	}

	public void setModel(Joueur model) {
		this.model = model;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	
	
}
