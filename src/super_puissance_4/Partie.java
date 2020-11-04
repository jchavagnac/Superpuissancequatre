/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_puissance_4;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tboulest
 */
public class Partie {
   Joueur[] listeJoueurs= new Joueur[2];
    Grille grilleJeu=new Grille();
    Joueur joueurCourant;


    public void attribuerCouleursAuxJoueurs(){
        Random r =new Random();// chaque joueur possede une couleur jaune ou rouge
        boolean couleur;
        couleur=r.nextBoolean();
        if(couleur){
            listeJoueurs[0].couleur="Rouge";
            listeJoueurs[1].couleur="Jaune";
                    }
        else{
            listeJoueurs[0].couleur="Jaune";
            listeJoueurs[1].couleur="Rouge";


}
}

    public void initialiserPartie(){
        grilleJeu.viderGrille();

        Scanner sc=new Scanner(System.in);// on donne le nom des joueurs 
        System.out.println("Le pseudonyme du joueur 1 est :");
        Joueur J1=new Joueur(sc.nextLine());
        System.out.println("Le pseudonyme du joueur 2 est :");
        Joueur J2=new Joueur(sc.nextLine());
        listeJoueurs[0]=J1;
        listeJoueurs[1]=J2;

        attribuerCouleursAuxJoueurs();

        System.out.println(J1.nom+" à la couleur "+J1.couleur);
        System.out.println(J2.nom+" à la couleur "+J2.couleur);// on dit qui possède quelle couleur

        for (int i=0;i<21;i++){
            J1.ajouter_jeton(new Jeton(J1.couleur));
            J2.ajouter_jeton(new Jeton(J2.couleur));// on distribue les 21 jetons aux joueurs
        }

        Random r = new Random();
        boolean premierJoueur=r.nextBoolean();
        if (premierJoueur){ // on definit aleatoirement qui est le premier joueur
            joueurCourant=listeJoueurs[0];
        }
        else{
            joueurCourant=listeJoueurs[1];
        }
    }

    public void debuterPartie(){

    }
}
