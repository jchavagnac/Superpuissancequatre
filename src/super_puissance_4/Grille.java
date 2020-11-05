package super_puissance_4;



import java.util.Arrays;

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
    
    return (Cellules[5][indicecolonne].recupererJeton() != null);
    
}
    public boolean ajouterJetonDansColonne(Jeton jeton,int colonne){
        for (int i=1;i<7;i++){
            if (Cellules[5-i][colonne]==null){
                Cellules[5-i][colonne].jetonCourant=jeton;
                return true;
            }
            if (i==5){
                return false;
            }

        }
        return false;
    }

    public boolean etreRemplie(){
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                if (Cellules[i][j]==null){
                    return false;
                }

                }
        }
        return true;
    }
    public void viderGrille(){
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                Cellules[i][j]=null;
            }
    }
}
    public void afficherGrilleSurConsole(){// afficher la grille
        System.out.println(Arrays.toString(Cellules));
}
    public boolean celluleOccupee(int i, int j){
        return Cellules[i][j]!=null; // renvoyer la cellule

    }

    public String lireCouleurDuJeton(int i, int j){// regarder quelle est la couleur du jeton
        String couleur_a_retourner;
        couleur_a_retourner=Cellules[i][j].lirecouleurjeton();
        return couleur_a_retourner;
    }

public boolean etreGagnantePourJoueur(Joueur unJoueur){
        String couleurTest=unJoueur.couleur;
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                int ligne=i;
                int colonne=j;
                
                for (i=ligne;i<ligne+4&&i<6;i++){    //Test de l'alignement de 4 jetons sur une colonne 
                    int compteur1=0;
                    if (Cellules[i][j].lirecouleurjeton().equals(couleurTest)){
                        compteur1++;
                    }
                    if (compteur1==4){
                        return true;
                    }
                }
                
                for (j=colonne;j<colonne+4&&j<7;j++){  //Test de l'alignement de 4 jetons sur une ligne
                    int compteur2=0;
                    if (Cellules[i][j].lirecouleurjeton().equals(couleurTest)){
                        compteur2++;
                    }
                    if (compteur2==4){
                        return true;
                    }
                }
                for (i=ligne, j=colonne;i<ligne+4 && i<6 && j<colonne+4 && j<7;i++,j++){   //Test de l'alignement de 4 jetons sur une diagonale (haut droite vers bas gauche)
                    int compteur3=0;
                    if (Cellules[i][j].lirecouleurjeton().equals(couleurTest)){
                        compteur3++;
                        }
                    if (compteur3==4){
                        return true;
                    }
                }
                
                for (i=ligne, j=colonne;i<ligne-4 && i<6 && j<colonne+4 && j<7;i--,j++){  //Test de l'alignement de 4 jetons sur une diagonale (bas droite vers haut gauche)
                    int compteur4=0;
                    if (Cellules[i][j].lirecouleurjeton().equals(couleurTest)){
                        compteur4++;
                         }                    
                    if (compteur4==4){
                        return true;
                    }
                } 
    }return false;
    }return false;
}
}


