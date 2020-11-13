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
public class Cellule {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;

    public Cellule(){
        jetonCourant=null;// on cree une cellule vide
    }

    public boolean affecterJeton(Jeton unJeton){
       if (jetonCourant!=null){
           System.out.println("Jeton déjà présent");// si il y a un jeton il ne peut pas en mettre
           return false;
       }
       else{
            jetonCourant=unJeton;// sinon le jeton placé passe dans la cellule
            return true;

       }
}

    public Jeton recupererJeton(){
        Jeton unJeton= jetonCourant; 
        jetonCourant=null;
        return unJeton;// renvoyé le jeton qui a été ou non placer
    }
 public String lirecouleurjeton(){
     if(jetonCourant == null){
            return "vide";// pas de couleur encoyer nul sinon renvoyer la couleur
        }
     return jetonCourant.couleur;
 }
  public boolean supprimerJeton(){
        if(jetonCourant == null){
            return false;
        }
        else{
            jetonCourant = null;
            return true;
        }
    }
    
    public boolean placerDesintegrateur(){
        if (this.desintegrateur==true){
            return false;
        }
        else{
            this.desintegrateur=true;
            return true;
        }
    }
    
    public boolean presenceDesintegrateur(){
        return this.desintegrateur;
}
    
    public boolean recupererDesintegrateur(){
        if(presenceDesintegrateur()==true){
            this.desintegrateur=false;
            return true;            
        }
        else{
            return false;
        }
    }   
}

