package jeux;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MorpionTest {

	@Test
	void test() {
		// Contruction du Morpion
				int nblignes =  9;
				int nbcolonnes = 9;
				Morpion m = new Morpion(nblignes, nbcolonnes);
				
				//Le morpion est vide à sa construction
				for (int i = 0; i < nblignes; i++) {
					for (int j = 0; j < nbcolonnes; j++) {
						assertEquals (m.Grille[i][j], ' ');
					}
				}
				m.premierCoup("1-1");
				assertEquals(m.Grille[0][0], 'X');
				m.setCase(8, 8);
				assertEquals(m.Grille[7][7], 'O');
				// Un pion peut être placé à côté d'un autre
				assertTrue(m.analyseChoix("1-2"));
				// Un pion ne peut pas être placé si il n'est pas relié à un autre
				assertFalse(m.analyseChoix("4-4"));
				
				// --------------- TEST COLONNE ------------------
				
				//nouvelle partie
				
				Morpion m2 = new Morpion(4,4);
				
				// Aucun pion n'est placé, l'analyse de la partie renvoie faux
				assertFalse(m2.analyserJeu());
				
				//Placement des pions
				m2.setCase(1, 1); // X
				m2.setCase(1, 2); // O
				m2.setCase(2, 1); // X
				m2.setCase(2, 2); // O
				m2.setCase(3, 1); // X
				
				// Alignement de 3 pions X en colonne n°1
				assertTrue(m2.analyserColonne(0)); 
				// Alignement de 2 pions O en colonne n°2
				assertFalse(m2.analyserColonne(1));
				
				
				// --------------- TEST LIGNE -------------------
				
				//Placement des pions
				m2.setCase(1, 3); // O
				m2.setCase(4, 1); // X
				m2.setCase(1, 4); // O
				
				// Alignement de 3 pions O en ligne n°1
				assertTrue(m2.analyserLigne(0));
				// Pas d'alignement entre les 'x' et le 'X' récemment ajoutés
				assertFalse(m2.analyserColonne(0));
				
				//Placement des pions
				m2.setCase(4, 2); // X
				m2.setCase(3, 3); // O
				m2.setCase(3, 2); // X
				m2.setCase(4, 4); // O
				assertTrue(m2.analyserDiagonalesGD());
				
				//nouvelle partie
				
				// ------------- TEST DIAGONALE GAUCHE VERS DROITE  ---------------
				
				Morpion m3 = new Morpion(6,6);
				
				m3.setCase(1, 2); // X
				m3.setCase(1, 3); // O
				m3.setCase(2, 3); // X
				m3.setCase(1, 4); // O
				m3.setCase(3, 4); // X
				assertTrue(m3.analyserDiagonalesGD());
				
				//nouvelle partie
				
				// ------------- TEST DIAGONALE DROITE VERS GAUCHE ---------------
				
				Morpion m4 = new Morpion(6,6);
				m4.setCase(1, 6); // X
				m4.setCase(1, 5); // O
				m4.setCase(2, 5); // X
				m4.setCase(1, 4); // O
				m4.setCase(3, 4); // X
				assertTrue(m4.analyserDiagonalesDG());
				m4.analyserDiagonalesDG();
				assertFalse(m4.analyserDiagonalesDG());
				
				// La ligne n°1 et la colonne n°6 sont fermées avec un 'x'
				assertEquals (m4.Grille[0][5], 'x'); 

	}

}
