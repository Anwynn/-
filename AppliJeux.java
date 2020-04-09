package jeux;

import java.util.Scanner;

public class AppliJeux {

	
	/** Comme il est demand� dans le sujet, nous avons "specialis� "args en ListeJeux,
	 *  et plac� dans l'onglet arguments de l'onglet run configuration les possibilit�s de choix
	 *  pour que lors du lancement de l'application les choix soient propos�s
	 *  directement. choix 0 pour TicTacToe, choix 1 pour Morpion.  */
	public static void main(String[] args) {
		
		for(String s : args) System.out.println(s);
		System.out.println("votre choix : ");
		Scanner sc = new Scanner(System.in);
		int choix =  sc.nextInt();
		if (choix == 0 ) {
			TicTacToe t = new TicTacToe(3,3);
			t.afficher();
			t.Jouer();
		}
		else if(choix == 1) {
			
			System.out.println("Choisissez le nombre de lignes : ");
			int nblignes =  sc.nextInt();
			if( nblignes <1) {
				System.out.println("Choisissez un nombre superieur � 0 : ");
				nblignes  = sc.nextInt();
			}
			System.out.println("Choisissez le nombre de colonnes : ");
			int nbcolonnes =  sc.nextInt();
			if( nbcolonnes <1) {
				System.out.println("Choisissez un nombre superieur � 0 : ");
				nbcolonnes  = sc.nextInt();
			}
			Morpion m = new Morpion(nblignes, nbcolonnes);
			m.afficher();
			m.Jouer();
		}

	}

}

