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
    public Joueur prochainJoueur(Joueur un_joueur) {
        if (listeJoueurs[0] == joueurCourant) {
            return listeJoueurs[1];
        }
        return listeJoueurs[0];
    }
    public void tourDeJeu(){
        while(grilleJeu.etreGagnantePourJoueur(joueurCourant)!=true && grilleJeu.etreRemplie()!=true){
                System.out.println(joueurCourant.nom+" Choisissez dans quelles colonne vous souhaittez placer un jeton");

                Scanner sc=new Scanner(System.in);
                int saisie =sc.nextInt();

                while (saisie<0&&saisie>7){
                    System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                    sc=new Scanner(System.in);
                    saisie=sc.nextInt();
                }

                if (joueurCourant.nombreJetonsRestants>0){
                    boolean jetonAposer;
                    jetonAposer=grilleJeu.ajouterJetonDansColonne(joueurCourant.listeJetons[joueurCourant.nombreJetonsRestants-1],saisie-1);

                    if (jetonAposer==true){
                        joueurCourant.nombreJetonsRestants--;                
                    }
                    else{
                        System.out.println("Cette colonne est remplie, veuillez saisir un autre numéro de colonne");
                        tourDeJeu();
                    }
                }joueurCourant=prochainJoueur(joueurCourant);

        }
    }
     
 public void debuterPartie(){
        initialiserPartie();
        this.grilleJeu.afficherGrilleSurConsole();
        System.out.println("Choisissez une action à effectuer");
        tourDeJeu();
        if (grilleJeu.etreGagnantePourJoueur(joueurCourant)==true){
            System.out.println("Le joueur "+joueurCourant.nom+" a gagné" );
        }

        if (grilleJeu.etreRemplie()==true && grilleJeu.etreGagnantePourJoueur(joueurCourant)!=true){
            System.out.println("La grille est pleine, personne n'a gagné");
 }


    }
}