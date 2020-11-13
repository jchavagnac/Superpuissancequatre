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
        listeJoueurs[0]=J1; // chacun sa liste de jetons
        listeJoueurs[1]=J2;

        attribuerCouleursAuxJoueurs();

        System.out.println(J1.nom+" à la couleur "+J1.couleur);
        System.out.println(J2.nom+" à la couleur "+J2.couleur);// on dit qui possède quelle couleur

        for (int i=0;i<21;i++){
            J1.ajouter_jeton(new Jeton(J1.couleur));
            J2.ajouter_jeton(new Jeton(J2.couleur));// on distribue les 21 jetons aux joueurs
        }
        J1.nombreDesintegrateurs=0;// chaque joueur demarre avec 0 desintegrateurs
        J2.nombreDesintegrateurs=0;

        Random r = new Random(); // 
        boolean premierJoueur=r.nextBoolean();
        if (premierJoueur){ // on definit aleatoirement qui est le premier joueur
            joueurCourant=listeJoueurs[0];
        }
        else{
            joueurCourant=listeJoueurs[1];
        }
        Random r2 = new Random();
        int ligne;
        int colonne;// le deuxieme random pour placer aleatoirement sur la grille des desintegrateurs
        for (int i = 0; i < 6; i++) {
            ligne = r2.nextInt(6);
            colonne = r2.nextInt(7);
            if (grilleJeu.Cellules[ligne][colonne].desintegrateur == false) {
                grilleJeu.placerDesintegrateur(ligne, colonne);
            }
        }
    }
    
    public Joueur prochainJoueur(Joueur un_joueur) {
        if (listeJoueurs[0] == joueurCourant) {
            return listeJoueurs[1];// lorsque le joueur 1 joue c'est à l'autre on retourne la liste du joueur adverse
        }
        return listeJoueurs[0];
    }
    public void tourDeJeu() {// créaation des tours de chaque joueurs
        while (grilleJeu.etreGagnantePourJoueur(joueurCourant) != true && grilleJeu.etreRemplie() != true) {

            int choix = choix_joueur();

            if (choix == 1) {// differents choix ici le placement de jeton

                System.out.println(joueurCourant.nom + " Dans quelle colonne voulez vous placer votre jeton ?");
                Scanner sc = new Scanner(System.in);
                int saisie = sc.nextInt();

                while (saisie < 0 || saisie > 7) {// eviter de sortir de la grille, il n'y a que 7 colonne 
                    System.out.println("Vous ne pouvez pas sortir de la grille !");
                    sc = new Scanner(System.in);
                    saisie = sc.nextInt()-1;
                }

                if (joueurCourant.nombreJetonsRestants > 0) {// tant que le joueur possede plus de 0 jetons il joue 
                    boolean jetonAposer; 
                    jetonAposer = grilleJeu.ajouterJetonDansColonne(joueurCourant,joueurCourant.listeJetons[joueurCourant.nombreJetonsRestants - 1], saisie - 1);
// a chaque tour il perd un jeton dans sa liste si il en place un 
                    if (jetonAposer == true) {
                        joueurCourant.nombreJetonsRestants--;
                    } else {// si le jeton n'est pas posable c'est que la colonne est remplie il faut recommencer le tour du joueur en question
                        System.out.println("Colonne pleine, veuillez placer votre jeton sur une autre colonne");
                        tourDeJeu();
                    }
                }
            }

            if (choix == 2) {// recuperation d'un jeton
                System.out.println("Saisissez le numéro de ligne du jeton que vous souhaitez récupérer");
                Scanner saisieLigne = new Scanner(System.in);
                int ligne = saisieLigne.nextInt()-1;

                while (ligne < 0 || ligne > 5) {// le joueur doit recuperer son jeton dans la grille
                    System.out.println("Mauvaise  saisie, saisissez un numéro de ligne valide");
                    saisieLigne = new Scanner(System.in);
                    ligne = saisieLigne.nextInt()-1;
                }

                System.out.println("Saisissez le numéro de colonne du jeton que vous souhaitez récupérer");
                Scanner saisieColonne = new Scanner(System.in);
                int colonne = saisieColonne.nextInt()-1;

                while (colonne < 0 || colonne > 6) {
                    System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                    saisieColonne = new Scanner(System.in);
                    colonne = saisieColonne.nextInt()-1;
                }
                
                if (grilleJeu.Cellules[ligne][colonne]!=null && grilleJeu.Cellules[ligne][colonne].lirecouleurjeton().equals(joueurCourant.couleur)){
                    grilleJeu.recupererJeton(ligne, colonne);//  le joueur recupere un jeton dans la grille de sa couleur 
                    grilleJeu.tasserGrille(ligne, colonne);// il faut ensuite tasser la grille 
                }    
                else{// si la condition du dessus est fausse c'est que le joueur n'a pas saisi un de ses jetons
                    System.out.println("Vous ne pouvez pas récupérer un jeton adverse ou un jeton sur une cellule vide");
                    tourDeJeu();       
                            }    
            }

            if (choix == 3) {// utiliser le desintegrateur
                
                if(joueurCourant.nombreDesintegrateurs==0){
                    System.out.println("Veuillez recuperer un désintégrateur afin de pouvoir vous en servir");
                    tourDeJeu();// il faudra recommencer le tour en cas de mauvaise saisie pour ne pas perdre un tour
                }
                else{// sinon le joueur choisi la ligne entre 1 et 5 et la colonne entre 1 et 6 pour desintegrer un jeton
                    System.out.println("Saisissez le numéro de ligne du jeton que vous souhaitez désintégrer");
                    Scanner saisieLigne = new Scanner(System.in);
                    int ligne = saisieLigne.nextInt()-1;

                    while (ligne < 0 || ligne > 5) {
                        System.out.println("Mauvaise  saisie, saisissez un numéro de ligne valide");
                        saisieLigne = new Scanner(System.in);
                        ligne = saisieLigne.nextInt()-1;
                    }
                    System.out.println("Saisissez le numéro de colonne du jeton que vous souhaitez désintégrer");
                    Scanner saisieColonne = new Scanner(System.in);
                    int colonne = saisieColonne.nextInt()-1;

                    while (colonne < 0 || colonne > 6) {
                        System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                        saisieColonne = new Scanner(System.in);
                        colonne = saisieColonne.nextInt()-1;
                    } 
                    
                    if (grilleJeu.Cellules[ligne][colonne]!=null && !grilleJeu.Cellules[ligne][colonne].lirecouleurjeton().equals(joueurCourant.couleur)){
                        grilleJeu.Cellules[ligne][colonne].supprimerJeton();
                        grilleJeu.tasserGrille(ligne, colonne);
                        joueurCourant.utiliserDesintegrateur();
                    }      
                        
                } 
                }

        this.grilleJeu.afficherGrilleSurConsole();
        joueurCourant = prochainJoueur(joueurCourant);// a la fin du tour reafficher la grille modifier et donner la main a l'autre joueur
        }
    }

 public int choix_joueur() {// menu du joueur
        System.out.println(joueurCourant.nom+" Que souhaitez vous faire ?");
        System.out.println("1) Placer un jeton");
        System.out.println("2) Récupérer un jeton");
        System.out.println("3) Utiliser un désintégrateur");

        Scanner sc = new Scanner(System.in);// en cas de mauvais choix
        int choix = sc.nextInt();
        while (choix != 1 && choix != 2 && choix != 3) {
            System.out.println("Vous n'avez que 3 actions possibles");
            choix = sc.nextInt();
        }
        return choix;// il faudra réafficher le menu
    }
     
 public void debuterPartie(){
        initialiserPartie();// il faut initialiser la partie 
        this.grilleJeu.afficherGrilleSurConsole();//afficher la grille
        System.out.println("Choisissez une action à effectuer");// debuter le tour du joueur
        tourDeJeu();
        if (grilleJeu.etreGagnantePourJoueur(joueurCourant)==true){// lorsqu'un joueur gagne a partie il faut afficher son nom
            System.out.println("Le joueur "+joueurCourant.nom+" à gagné" );
        }
// en cas d'égalité on precise
        if (grilleJeu.etreRemplie()==true && grilleJeu.etreGagnantePourJoueur(joueurCourant)!=true){
            System.out.println("La grille est pleine égalité, revanche ?");
 }


    }
}