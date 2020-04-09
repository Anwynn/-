package jeux;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTacToeTest {

	@Test
	void test() {

		//Construction du TicTacToe
		int nblignes =  3;
		int nbcolonnes = 3;	
		TicTacToe t = new TicTacToe(nblignes, nbcolonnes);
		
		// Le TicTacToe est vide lors de sa construction
		for (int i = 0; i < nblignes; i++) {
			for (int j = 0; j < nbcolonnes; j++) {
				assertEquals (t.Grille[i][j], ' ');
			}
		}
		
		// Placer un pion dans un coin de la grille ne pose aucun soucis
		assertTrue(t.analyseChoix("1-1"));
		assertTrue(t.analyseChoix("3-3"));
		
		
		// --------------- TEST COLONNE ------------------
		
		//Placement des pions
		t.setCase(1,1); // X
		t.setCase(1,2); // O
		t.setCase(2,1); // X
		t.setCase(2,2); // O
		t.setCase(3,1); // X
		
		// La partie est finie, le joueur X a gagné en colonne n°1
		assertTrue(t.analyserColonne(0));
		assertTrue(t.analyserJeu());
		
		//nouvelle partie
		
		// --------------- TEST LIGNE -------------------
		
		TicTacToe t2= new TicTacToe(nblignes, nbcolonnes);
		
		//Placement des pions
		t2.setCase(3, 1); // X
		t2.setCase(2, 1); // O
		t2.setCase(3, 2); // X
		
		// On peut placer un pion n'importe ou dans le TicTacToe
		assertTrue(t2.analyseChoix("1-3"));
		
		//Placement des pions
		t2.setCase(1, 3); // O
		t2.setCase(3, 3); // X
		
		// La partie est finie, le joueur X a gagné  en ligne n°3
		assertTrue(t2.analyserLigne(2));
		assertTrue(t2.analyserJeu());
		
		//nouvelle partie
		
		// ------------- TEST DIAGONALE ---------------
		
		TicTacToe t3= new TicTacToe(nblignes, nbcolonnes);
		
		//Placement des pions
		t3.setCase(1, 1); // X
		t3.setCase(1, 2); // O
		t3.setCase(2, 2); // X
		t3.setCase(1, 3); // O
		t3.setCase(3, 3); // X
		
		//// La partie est finie, le joueur X a gagné grâce à une diagonale
		assertTrue(t3.analyserDiagonales());
		assertTrue(t3.analyserJeu());
		
	}

}
