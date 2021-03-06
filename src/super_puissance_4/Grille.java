package super_puissance_4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tboulest
 */
public class Grille {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;

 

    Cellule[][] Cellules=new Cellule[6][7]; // tableau de 0

    public Grille(){// constructeur grille qui crée les cellules
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                Cellules[i][j]=new Cellule();
            }
        }
    }
public boolean colonneRemplie(int indicecolonne){
    
    return (Cellules[5][indicecolonne].recupererJeton() != null);// recuperer le jeton si la colonne est remplie
    
}
    
    public boolean etreRemplie(){// si la grille est remplie il faut le verifier
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                if (Cellules[i][j].jetonCourant==null){
                    return false;
                }

                }
        }
        return true;
    }
    public void viderGrille(){
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                Cellules[i][j].jetonCourant=null;// vider la grille avant chaque partie
            }
    }
}
   public void afficherGrilleSurConsole(){// affichage
        for (int i=0; i <6; i++) { 
            for (int j =0;j < 7; j++){
                if (Cellules[i][j].jetonCourant != null) {                   
                    System.out.print(Cellules[i][j].jetonCourant);
                }
                else if(Cellules[i][j].desintegrateur==true){
                    System.out.print("\u001B[0m D ");// D pour desintegrateurs
                }
                else{
                    System.out.print("\u001B[0m N ");// N pour rien 
            }  
        }System.out.println();// afficher le tout
    }
    }
    public boolean ajouterJetonDansColonne(Joueur joueurCourant,Jeton jeton,int colonne){
            for (int i=1;i<7;i++){   
                if (Cellules[6-i][colonne].jetonCourant==null){
                    if (Cellules[6-i][colonne].presenceDesintegrateur()) {// si il ya un desintegrateur il faut l'ajouter dans les possessions du joueur
                        Cellules[6-i][colonne].recupererDesintegrateur();
                        joueurCourant.nombreDesintegrateurs++;
                    }
                    Cellules[6-i][colonne].jetonCourant=jeton;
                    return true;
                }   
                if (i==6){
                    return false;
                }
            } return false;
            }

    public boolean celluleOccupee(int i, int j){
        return Cellules[i][j].jetonCourant!=null; // renvoyer la cellule occupée

    }

    public String lireCouleurDuJeton(int i, int j){// regarder quelle est la couleur du jeton
        return Cellules[i][j].lirecouleurjeton();
    }

 public  boolean etreGagnantePourJoueur(Joueur joueur) {
     //Je teste donc l'existence d'une ligne pour tout jeton A compris dans la zone verte de la figure ci-dessous, 
     //avec 2 boucles imbriquées. Si j'en trouve au moins 1, je retourne vrai de suite. 
     //Sinon je ne retourne rien et continue jusqu'à couvrir entièrement la zone verte. Si je ne trouve rien je retourne faux.
     //Ensuite on le fait avec les colonnes et les diagonales.
     // l'annexe nous a aidée
       for (int i = 0; i < 6; i++) {// test ligne
            for (int j = 0; j < 4; j++) {
                if (Cellules[i][j] != null && Cellules[i][j].lirecouleurjeton().equals(joueur.couleur)
                       && Cellules[i][j + 1]!= null && Cellules[i][j + 1].lirecouleurjeton().equals(joueur.couleur)
                        && Cellules[i][j + 2]!= null && Cellules[i][j + 2].lirecouleurjeton().equals(joueur.couleur)
                        && Cellules[i][j + 3]!= null && Cellules[i][j + 3].lirecouleurjeton().equals(joueur.couleur)) {
                    return true;
                }
            }
        }
        for (int j = 0; j < 7; j++) {// test colonne
            for (int i = 0; i < 3; i++) {
                if (Cellules[i][j] != null && Cellules[i][j].lirecouleurjeton().equals(joueur.couleur)
                      &&Cellules[i + 1][j]!= null&& Cellules[i + 1][j].lirecouleurjeton().equals(joueur.couleur)
                        &&Cellules[i + 2][j]!= null&& Cellules[i + 2][j].lirecouleurjeton().equals(joueur.couleur)
                       &&Cellules[i + 3][j]!= null&& Cellules[i + 3][j].lirecouleurjeton().equals(joueur.couleur)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {// test diagonale 1
            for (int j = 0; j < 4; j++) {
                if (Cellules[i][j] != null && Cellules[i][j].lirecouleurjeton().equals(joueur.couleur)
                       &&Cellules[i + 1][j + 1]!= null && Cellules[i + 1][j + 1].lirecouleurjeton().equals(joueur.couleur)
                      &&Cellules[i + 2][j + 2]!= null && Cellules[i + 2][j + 2].lirecouleurjeton().equals(joueur.couleur)
                        &&Cellules[i + 3][j + 3]!= null && Cellules[i + 3][j + 3].lirecouleurjeton().equals(joueur.couleur)) {
                    return true;
                }
            }
        }
        for (int i = 3; i < 6; i++) { //test diagonale 2
            for (int j = 0; j < 4; j++) {
                if (Cellules[i][j] != null && Cellules[i][j].lirecouleurjeton().equals(joueur.couleur)
                       && Cellules[i - 1][j + 1]!= null && Cellules[i - 1][j + 1].lirecouleurjeton().equals(joueur.couleur)
                        && Cellules[i - 2][j + 2]!=null && Cellules[i - 2][j + 2].lirecouleurjeton().equals(joueur.couleur)
                      && Cellules[i - 3][j + 3]!= null && Cellules[i - 3][j + 3].lirecouleurjeton().equals(joueur.couleur)) {
                    return true;
                }
            }
        }
        return false;
 }
  public void tasserGrille(int ligne, int colonne){
        for (int i = ligne; i >=0 ; i--) {
            if (i == 0) {
                Cellules[i][colonne].jetonCourant = null;
            } else {
                Cellules[i][colonne].jetonCourant = Cellules[i - 1][colonne].jetonCourant;
            }

        }
    }
    
    public boolean placerDesintegrateur(int ligne,int colonne){
        if (Cellules[ligne][colonne].desintegrateur==true){
            return false;            // on place les desintegrateurs 
        }
        else{
            Cellules[ligne][colonne].desintegrateur=true;
            return true;
        }
}
    
    public Jeton recupererJeton(int ligne, int colonne){
        Jeton unJeton=Cellules[ligne][colonne].recupererJeton();
        return unJeton;// si on a un jeton supprimé il faudra le recuperer 
    }

}


