package super_puissance_4;


import super_puissance_4.Jeton;
import super_puissance_4.Joueur;
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

    public boolean ajouterJetonDansColonne(Jeton jeton,int colonne){
        for (int i=1;i<6;i++){
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
    public void afficherGrilleSurConsole(){
        System.out.println(Arrays.toString(Cellules));
}
    public boolean celluleOccupee(int i, int j){
        return Cellules[i][j]!=null; // renvoyer

    }

    public String lireCouleurDuJeton(int i, int j){
        String couleur_a_retourner;
        couleur_a_retourner=Cellules[i][j].jetonCourant.couleur;
        return couleur_a_retourner;
    }

    public boolean etreGagnantePourJoueur(Joueur unJoueur){// on veut determiner qui est le gagnant
        String couleurTest=unJoueur.couleur;
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                int ligne=i;
                int colonne=j;

                for (i=ligne;i<ligne+3&&i<6;i++){// pour tester les lignes 
                    if (Cellules[i][j].jetonCourant.couleur.equals(couleurTest)){// test si la couleur est la meme que celle dans la cellule
                        // ligne toute la ligne
                        return true;
                    }
                }

                for (j=colonne;j<colonne+3&&j<7;j++){// pour tester les colonnes
                    if (Cellules[i][j].jetonCourant.couleur.equals(couleurTest)){
                        return true;
                    }

                for (i=ligne;i<ligne+4;i++){ // tester sur la diagonale 
                    
                         if (Cellules[i][j].jetonCourant.couleur);
                         return  true;
                     }

                } 
                }
    }return false;
    }
    
    
}
%salut les collegues

