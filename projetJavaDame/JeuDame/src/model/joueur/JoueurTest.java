package model.joueur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import model.coordonees.Coordonnees;
import model.piece.Pion;
import model.plateau.Plateau;

class JoueurTest {


	@Test
	void test() {
		Plateau p1 = new Plateau();
		Coordonnees.construireListeCoordonnees();
		p1.construireListeCases();
		
		Joueur j1 = new Joueur("Joueur", "1");
		Joueur j2 = new Joueur("Joueur", "2");
		
		Integer[] listeIndexBlanc = {0, 2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, 28, 31, 33, 35, 37, 39};
		Integer[] listeIndexNoir = {99, 97, 95, 93, 91, 88, 86, 84, 82, 80, 79, 77, 75, 73, 71, 68, 66, 64, 62, 60};
		
		for(int i = 0; i < 20; i++) {
			j1.getListePieces().add(new Pion(j1, Color.BLACK, p1.getListeCases().get(listeIndexNoir[i])));
			j2.getListePieces().add(new Pion(j2, Color.WHITE, p1.getListeCases().get(listeIndexBlanc[i])));
		}
		
		j1.prendPiece(j2, 3); //Le joueur 1 prend une pièce au joueur 2
		
		assertEquals(1 ,j1.getListePiecesPrises().size()); //Une pièce dans les pièces prises du joueur 1
		assertEquals(19 ,j2.getListePieces().size()); //Une pièce en moins dans la liste du joueur 2

	}

}
