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
public class Jeton {
    String couleur;

    public Jeton(String uneCouleur){
        couleur=uneCouleur;
    }

    
    public String lire_Couleur(){
        return couleur;
}
    @Override
    public String toString(){// les couleurs des joueurs 
        if("Rouge".equals(couleur))// rouge pour le joueur 2
            return "\u001B[31m O "; // jaune pour le j1
        return "\u001B[33m O ";
    }

}
