/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author milim
 */
public class Demande {
     private int id;
    private String etat;
    private int ide;
    private int idu;

    public Demande() {
    }

    public Demande(int ide, int idu) {
        this.ide = ide;
        this.idu = idu;
    }

    public Demande(int id, String etat, int ide, int idu) {
        this.id = id;
        this.etat = etat;
        this.ide = ide;
        this.idu = idu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
    
    
}
