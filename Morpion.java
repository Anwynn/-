package jeux;


	import java.util.Scanner;

	public class Morpion extends TicTacToe {

		/** Classe représentant le jeu du Morpion qui hérite de la classe Tic-Tac-Toe 
		 * cptX et cptO sont respectivement les compteurs d'alignements pour chacun des joueurs 
		 * ligne et colonnes sont nécessaires pour tester l'alignement vertical et horizontal 
		 * dans les méthodes respectives. Voir plus bas.*/

		private int cptX;
		private int cptO;
		private int ligne;
		private int colonne;
		
		
		public Morpion(int nblignes, int nbcolonnes) {
			super(nblignes, nbcolonnes);	
			cptX = 0;
			cptO = 0;
			ligne = 0;
			colonne = 0;
		}
		
		/**  Méthode  héritée et adaptée notament pour l'affichage de la première ligne qui varie en fonction du choix
		 * des joueurs */
		@Override
		public void afficher() {
			System.out.println();
			int[] t = new int[nbColonnes];
			for(int i =0; i<nbColonnes; i++) {
				System.out.print("   "+(i+1));
			}
			System.out.println();
			
			int c = 1;
			for(int i = 0; i<nbLignes; i++) {
				System.out.print(c);
				for(int j = 0; j < nbColonnes; j++) {
					System.out.print(" ["+Grille[i][j]+"]");
				}
				System.out.println();
				c++;
			}
		}	
		/** les trois méthodes suivantes permettent de récupérer les valeurs de ligne et colonne de la dernière case jouée
		 * Ceci pour pouvoir tester un alignement uniquement cette ligne et cette  colonne pour éviter de balayer toute la grille. */
		public void getCase(int ligne, int colonne) {
			getLigne(ligne);
			getColonne(colonne);
		}
		
		public int getColonne(int colonne) {
			return colonne;
		}
		public int getLigne(int ligne) {
			return ligne;
		}
		
		/** méthode qui permet de tester le choix du joueur en cours
		 * dans un premier temps, la case choisie doit etre vide ET doit posséder au moins une case adjacente pleine(horizontalement
		 * verticalement, et/ou en diagonale) */
		@Override
		@SuppressWarnings("resource")
		public  boolean analyseChoix(String choix){ 
			int MINL = 0;
			int MINC = 0;
			int MAXL = this.nbLignes;
			int MAXC = this.nbColonnes;
			if (choix.matches("\\d+\\s*-\\s*\\d+")) {
				Scanner sc = new Scanner(choix);
				sc.useDelimiter("\\s*-\\s*");
				ligne = sc.nextInt();
				colonne = sc.nextInt();
				System.out.println("ligne = "+ligne+" , colonne = "+colonne);
				if (ligne-1 >= nbLignes || colonne-1 >= nbColonnes || ligne-1 < 0 || colonne-1 < 0) 
					return false;
				
				// Vérification pour la ligne [0]
				else if (Grille[ligne-1][colonne-1] == ' ' && ligne-1 == MINL && colonne-1 != MINC &&  colonne != MAXC && ligne != MAXL &&
						((Grille[ligne-1][colonne-2] != ' ') ||
						(Grille[ligne][colonne-2] != ' ') ||
						(Grille[ligne][colonne-1]!= ' ') ||
						(Grille[ligne][colonne]!= ' ') ||
						(Grille[ligne-1][colonne]!= ' ')) ) {
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				
				//Vérification pour la colonne [0]
				else if ((Grille[ligne-1][colonne-1] == ' ') && colonne-1 == MINC && ligne-1 != MINL && ligne != MAXL && colonne != MAXC &&
						((Grille[ligne][colonne]!= ' ') || 
						(Grille[ligne][colonne-1]!= ' ') ||
						(Grille[ligne-2][colonne-1]!= ' ') ||
						(Grille[ligne-2][colonne]!= ' ') ))  {
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				//Vérification pour la ligne[0] colonne[0]
				else if ((Grille[ligne-1][colonne-1] == ' ') && colonne-1 == MINC && ligne-1 == MINL && colonne != MAXC && ligne != MAXL &&
						((Grille[ligne][colonne]!= ' ') || 
						(Grille[ligne-1][colonne]!= ' ') || 
						(Grille[ligne][colonne-1]!= ' ') )) {
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
					
				//Vérification  ligne [0] colonne[MAX] 
				else if ((Grille[ligne-1][colonne-1] == ' ') && colonne == MAXC && ligne-1 == MINL && colonne-1 != MINC && ligne != MAXL &&
						((Grille[ligne][colonne-1]!= ' ') ||
						(Grille[ligne-1][colonne-2] != ' ')	||
						(Grille[ligne][colonne-2]!= ' ') )) {
					
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				//Vérification ligne[MAX] colonne[0]
				else if((Grille[ligne-1][colonne-1] == ' ') && colonne-1 == MINC && ligne-1 !=MINL && colonne != MAXC && ligne == MAXL &&(
						(Grille[ligne-2][colonne-1]!= ' ')||
						(Grille[ligne-2][colonne]!= ' ') ||
						(Grille[ligne-1][colonne]!= ' ')
						)){
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				// Vérification colonne[MAX]
				else if((Grille[ligne-1][colonne-1] == ' ') && colonne-1 != MINC && ligne-1 !=MINL && colonne == MAXC && ligne != MAXL &&(
					(Grille[ligne][colonne-1]!= ' ') ||
					(Grille[ligne-2][colonne-2]!= ' ') ||
					(Grille[ligne-1][colonne-2] != ' ')|| 	
					( Grille[ligne-2][colonne-1]!= ' ')||
					(Grille[ligne][colonne-2]!= ' ')) ) {
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				// Vérification ligne[MAX]
				else if((Grille[ligne-1][colonne-1] == ' ') && colonne-1 != MINC && ligne-1 !=MINL && colonne != MAXC && ligne == MAXL && (
						(Grille[ligne-2][colonne-2]!= ' ') ||
						(Grille[ligne-1][colonne-2] != ' ')|| 	
						(Grille[ligne-2][colonne-1]!= ' ')||
						(Grille[ligne-2][colonne]!= ' ')) ) {
							
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
						}
				
			
						
				//Vérification ligne[MAX] colonne[MAX]
				else if((Grille[ligne-1][colonne-1] == ' ') && colonne-1 != MINC && ligne-1 !=MINL && colonne == MAXC && ligne == MAXL &&(
						(Grille[ligne-2][colonne-2]!= ' ') ||
						(Grille[ligne-1][colonne-2]!= ' ')||
						(Grille[ligne-2][colonne-1]!= ' '))
						){
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				//Vérification interieur de la grille
				else if((Grille[ligne-1][colonne-1] == ' ') && colonne-1 != MINC && ligne-1 !=MINL && colonne != MAXC && ligne != MAXL &&(
						(Grille[ligne][colonne-1]!= ' ') ||(Grille[ligne][colonne]!= ' ') ||
						(Grille[ligne-2][colonne-2]!= ' ') ||(Grille[ligne-1][colonne-2] != ' ')||
						( Grille[ligne-2][colonne-1]!= ' ') ||(Grille[ligne-1][colonne]!= ' ') ||
						( Grille[ligne-2][colonne]!= ' ') ||(Grille[ligne][colonne-2]!= ' ')) ) {
					super.setCase(ligne, colonne);
					getCase(ligne, colonne);
					sc.close();
					return true;
				}
				
				}
			
			else {
				System.out.println("coup invalide : ");
				return false;
				}
			return false;
		}
		
		/** méthode pour valider le premier coup qui est placement libre dans ce jeu . Elle est appelée dans méthode Jouer ci dessous*/
		
		public void premierCoup(String choix) {   
			if (choix.matches("\\d+\\s*-\\s*\\d+")) {
				Scanner sc = new Scanner(choix);
				sc.useDelimiter("\\s*-\\s*");
				int ligne = sc.nextInt();
				int colonne = sc.nextInt();
				System.out.println("ligne = "+ligne+" , colonne = "+colonne);
				super.setCase(ligne, colonne);
				sc.close();
				cpt ++;
				this.joueur = 'O';
			}
		}

		
		/**  nouvelle méthode héritée et adaptée aux nouvelles règles de jeu : premier placement libre, detection de victoire en fonction 
		 * du nombre d'alignements effectués au cours de la partie pour chaque joueur, quand la grille est pleine.
		 *  "fermeture" des cases  : converties en minuscules quand il y a un alignement décelé.  */
		   @Override
		public void Jouer() {  
			   
		   
			    System.out.println();
				System.out.println("Joueur "+ this.joueur+ " veuillez entrer votre choix de Ligne-Colonne : ");
				Scanner sc = new Scanner(System.in);
				String s = sc.next();
			    premierCoup(s);
			    this.afficher();
				while (cpt < (nbLignes*nbColonnes)) {
					System.out.println();
					System.out.println("Joueur "+ this.joueur+ " veuillez entrer votre choix de Ligne-Colonne : ");
					String s1 = sc.next();
					if (analyseChoix(s1) == false) {
						System.out.println("Coup invalide");
					}	
					
					else {
						cpt ++;
						this.afficher();
						 if(analyserLigne(ligne-1) == false) {
							 if(analyserColonne(colonne-1)== false) {	
								 if(analyserDiagonalesGD()==false && analyserDiagonalesDG() == false) { 
									 if(this.joueur == 'X' || this.joueur == 'x') {
										 this.joueur = 'O';
									 }
									else if(this.joueur =='O' || this.joueur == 'o') {
										 this.joueur = 'X';
									 }
								 }
								 else {
									 if(this.joueur == 'X') {
										 cptX++;
										 this.joueur = 'O';
										 System.out.println("Joueur X vous marquez un point!"); 
									 }
									 else {
										 cptO++;
										 this.joueur = 'X';
										 System.out.println("Joueur O vous marquez un point!");
									 }
								 }
							}
							 
						 }
						 else{
							 if(this.joueur == 'X') {
								 cptX++;
								 this.joueur = 'O';
								 System.out.println("Joueur X vous marquez un point! Compteur = "+cptX);
							 }
							 else {
								 cptO++;
								 this.joueur = 'X';
								 System.out.println("Joueur O vous marquez un point! Compteur = "+cptO);
							 }
						 }
					}
						 
					
					if(cpt == (nbLignes*nbColonnes) && this.analyserDiagonalesGD() == false && analyserDiagonalesDG() == false && analyserColonne(colonne)==false && analyserLigne(ligne) == false) {
						if(cptX > cptO) {
						System.out.println("Joueur X vous avez gagné !!!");
						}
						else if(cptO > cptX) {
						System.out.println("Joueur O vous avez gagné !!!");
						}
						else {
							System.out.println("Match null !!!");
						}
					}
				}
			}
					
				
		   private char toLowerCaseX(char c) {
				return 'x';
			}
			private char toLowerCaseO(char c) {
				return 'o';
			}
					
			@Override
			protected boolean analyserLigne(int j) {
				//assert (j <nbLignes && j>= 0);
				String chaine = "";
				int i = 0;
				String s1 = "XXX";
				String s2 = "OOO";
				for(i = 0; i < nbColonnes; i++) 
					chaine = chaine+ Grille[j][i];
					if(chaine.contains("XXX")== true) {
						i = chaine.indexOf(s1);
						Grille[j][i]= toLowerCaseX(Grille[j][i]);
						Grille[j][i+1]= toLowerCaseX(Grille[j][i+1]);
						Grille[j][i+2]= toLowerCaseX(Grille[j][i+2]);
						return true;
					}
					
					else if (chaine.contains("OOO")== true) {
						i = chaine.indexOf(s2);
						Grille[j][i]= toLowerCaseO(Grille[j][i]);
						Grille[j][i+1]= toLowerCaseO(Grille[j][i+1]);
						Grille[j][i+2]= toLowerCaseO(Grille[j][i+2]);
						return true;
					}
					else return false;
				
			}
		
			
			@Override
			protected boolean analyserColonne(int i) {
				//assert (j < nbColonnes && j>= 0);
				String chaine = "";
				int j = 0;
				String s1 = "XXX";
				String s2 = "OOO";
				for(j = 0; j < nbLignes ; j++)
					chaine = chaine+ Grille[j][i];			
					if(chaine.contains("XXX")== true) {
						j = chaine.indexOf(s1);
						Grille[j][i]= toLowerCaseX(Grille[j][i]);
						Grille[j+1][i]= toLowerCaseX(Grille[j+1][i]);
						Grille[j+2][i]= toLowerCaseX(Grille[j+2][i]);
						return true;
					}
					else if (chaine.contains("OOO")== true) {
						j = chaine.indexOf(s2);
						Grille[j][i]= toLowerCaseO(Grille[j][i]);
						Grille[j+1][i]= toLowerCaseO(Grille[j+1][i]);
						Grille[j+2][i]= toLowerCaseO(Grille[j+2][i]);
						return true;
					}
				else return false;
			}
			
			public boolean checkdiagGD(char c, int ligne, int colonne) {
				if ( ligne+2 < nbLignes  && colonne+2 < nbColonnes ) {
				if ( Grille[ligne][colonne] == c)
					if (Grille[ligne+1][colonne+1] == c)
						if ( Grille[ligne+2][colonne+2] == c )
							return true;
						else return false;
				}
				return false;
			}
			
			protected boolean analyserDiagonalesGD() {
			
				for(int j=0 ; j< nbLignes ; j++) {
					for (int k=0; k<nbColonnes ; k++ ) {
						
						if( Grille[j][k] == 'X') {
							if (checkdiagGD('X', j, k)) {
								Grille[j][k]= toLowerCaseX(Grille[j][k]);
								Grille[j+1][k+1]= toLowerCaseX(Grille[j+1][k+1]);
								Grille[j+2][k+2]= toLowerCaseX(Grille[j+2][k+2]);
								return true;
							}
							else continue;
						}
						else if (Grille[j][k] =='O') {
							if (checkdiagGD ('O', j,k)) {
								Grille[j][k]= toLowerCaseO(Grille[j][k]);
								Grille[j+1][k+1]= toLowerCaseO(Grille[j+1][k+1]);
								Grille[j+2][k+2]= toLowerCaseO(Grille[j+2][k+2]);
								return true;
							}
							else continue;
						}
					}
				}
				 return false;
			}
			
			
			protected boolean analyserDiagonalesDG() {
				
				for(int j= nbLignes-1 ; j !=0 ; j-- ){
					for (int k= 0; k < nbColonnes ; k++ ) {
						
						if( Grille[j][k] == 'X') {
							if (checkdiagDG('X', j, k)) {
								Grille[j][k]= toLowerCaseX(Grille[j][k]);
								Grille[j-1][k+1]= toLowerCaseX(Grille[j-1][k+1]);
								Grille[j-2][k+2]= toLowerCaseX(Grille[j-2][k+2]);
								return true;
							}
						}
						else if (Grille[j][k] =='O') {
							if (checkdiagDG ('O', j,k)) {
								Grille[j][k]= toLowerCaseO(Grille[j][k]);
								Grille[j-1][k+1]= toLowerCaseO(Grille[j-1][k+1]);
								Grille[j-2][k+2]= toLowerCaseO(Grille[j-2][k+2]);
								return true;
							}
						}
					}
				}
				 return false;
			}
			
			private boolean checkdiagDG(char c, int ligne, int colonne) {
				if (ligne-2 >= 0 && colonne+2 < nbColonnes) {
					if ( Grille[ligne][colonne] == c && Grille[ligne-1][colonne+1] == c && Grille[ligne-2][colonne+2] == c )
						return true;
							else return false;
				}
					return false;
			}
		}
