/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tboulest
 */
public class Cellule {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;

    public Cellule(){
        jetonCourant=null;// on cree une cellule vide
    }

    public boolean affecterJeton(Jeton unJeton){
       if (jetonCourant!=null){
           System.out.println("Jeton déjà présent");
           return false;
       }
       else{
            jetonCourant=unJeton;
            return true;

       }
}

    public Jeton recupererJeton(Jeton jetonCourant){
        return jetonCourant; // renvoyé le jeton qui a été ou non mit
    }


}
