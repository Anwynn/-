package jeux;

import java.util.Scanner;

public class TicTacToe {

	protected int nbLignes;
	protected int nbColonnes;
	protected char [][] Grille;
	protected char joueur;
	protected int cpt;
	
	public  TicTacToe(int nblignes, int nbcolonnes) {  
		this.joueur = 'X';
		this.nbLignes = nblignes;
		this.nbColonnes = nbcolonnes;
		this.Grille = new char[this.nbLignes][this.nbColonnes];
		
		for(int i = 0; i < nbLignes; i++) {
			for(int j = 0; j < nbColonnes; j++) {
				Grille[i][j] = ' ';
			}
		}
		this.cpt = 0;
	}
	
	/** méthode qui permet d'afficher la grille de jeu  */
	public void afficher() {   
		System.out.println();
		String s = "   1   2   3";
		System.out.println(s);
		int c = 1;
		for(int i = 0; i<this.nbLignes; i++) {
			System.out.print(c);
			for(int j = 0; j < nbColonnes; j++) {
				System.out.print(" ["+Grille[i][j]+"]");
			}
			System.out.println();
			c++;
		} 	
	}
	/**  méthode qui permet de placer un jeton dans la case choisie par le joueur courant.
	 * c'est l-1 et c-1 car pour un bon affichage pour le joueur il est necessaire de faire un décalage.  */
	public void setCase(int l, int c) {
		this.Grille[l-1][c-1] = this.joueur;
	}
	/**   Cette méthode permet de vérifier la bonne saisie du choix du joueur, tester si la case choisie est valide :
	 * ici elle doit être vide, puis enfin de placer le jeton. */
	@SuppressWarnings("resource")
	public  boolean analyseChoix(String choix){   // méthode pour savoir si la case choisie est valide
		if (choix.matches("\\d+\\s*-\\s*\\d+")) {
			Scanner sc = new Scanner(choix);
			sc.useDelimiter("\\s*-\\s*");
			int ligne = sc.nextInt();
			int colonne = sc.nextInt();
			System.out.println("ligne = "+ligne+" , colonne = "+colonne);
			if (ligne-1 >= nbLignes || colonne-1 >= nbColonnes || ligne-1 < 0 || colonne-1 < 0)
				return false;
			else if ((Grille[ligne-1][colonne-1] == ' ') &&
					(ligne-1 < nbLignes)&& (ligne -1 >= 0) &&
					(colonne -1 < nbColonnes) && (colonne -1 >= 0)) {
					setCase(ligne, colonne);
					sc.close();
					return true;
			}
			else if( (Grille[ligne-1][colonne-1] == 'X' )|| (Grille[ligne-1][colonne-1] == 'O'))
				return false;
			
			else
				return false;
			}
		return false;
	
	}
	/**méthode qui permet le déroulement du jeu, utilise plusieurs méthodes pour cela, détecte aussi la victoire ou le match nul   */
	public void Jouer() {  // méthode pour jouer un coup
		
		while (cpt < 9) {
			System.out.println();
			System.out.println("Joueur "+ this.joueur+ " veuillez entrer votre choix de Ligne-Colonne : ");
			Scanner sc = new Scanner(System.in);
			String s = sc.next();
			if (analyseChoix(s) == false) {
				System.out.println("Coup invalide");
			}	
				
			else {
			
				cpt ++;
				this.afficher();
				if(this.analyserJeu() == true) {
					System.out.println("Joueur "+this.joueur+" vous avez Gagné!!!");
					break;
				}
				else {
					if (this.joueur =='X') {
						this.joueur = 'O';
					}
					else {
						this.joueur = 'X';
					}
				}
			}
		}
		if(cpt == 9 && this.analyserJeu()==false ) {
			System.out.println("Match null !!!");
			
		}
		
	}
	
	/** ici figure une suite de méthodes pour tester les alignements, regroupées ensuite en une seule méthode 
	 * appelée dans Jouer  */
	
	protected boolean analyserLigne(int j) {
		assert (j <3 && j>= 0);
		String chaine = "";
		for(int i = 0; i < 3; i++)
			chaine = chaine+ Grille[j][i];
		if(chaine.equals("XXX")|| chaine.equals("OOO")) {
			return true;
		}
		else return false;
	}
	protected boolean analyserColonne(int j) {
		assert (j < 3 && j >=0);
		String chaine = "";
		for(int i = 0; i < 3; i++)
			chaine = chaine+ Grille[i][j];
		if(chaine.equals("XXX")|| chaine.equals("OOO")) {
			return true;
		}
		else return false;
	}
	protected boolean analyserDiagonales() {
		String chaine = "";
		for(int i = 0; i< 3; i++) {
			chaine = chaine + Grille[i][i];
		}
		if (chaine.equals("XXX")|| chaine.equals("OOO")) {
			return true;
		}
		chaine = "";
		for(int i = 0; i< 3; i++) {
			chaine = chaine + Grille[2-i][i];
		}
		if (chaine.equals("XXX")|| chaine.equals("OOO")) {
			return true;
		}
		return false;
	}
	
	
	public boolean analyserJeu() {      // méthode qui regroupe les méthodes des différents alignements
		for(int i =0 ; i< 3 ; i++) {
			if(analyserLigne(i) == true)
				return true;
		}
		for(int i =0 ; i< 3 ; i++) {
			if(analyserColonne(i) == true)
				return true;
		}
		if (analyserDiagonales() == true) {
			return true;
		}
		else 
			return false;		 
	}

	
}
