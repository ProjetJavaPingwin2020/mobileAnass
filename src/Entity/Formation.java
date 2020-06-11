/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;
import java.util.Date;


/**
 *
 * @author med
 */
public class Formation {
    private int id;
    private String nom;
    private String type;
    private Date date;
    private String lieu;
    private String description;
    private String heure;
    private int nbrplace;
    private int formateur;
//    private InputStream image;
    private String nomImage;

    public Formation() {
    }

    public Formation(int id, String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, int formateur, String nomImage) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
        this.nomImage = nomImage;
    }

    public Formation(String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, int formateur, String nomImage) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.formateur = formateur;
        this.nomImage = nomImage;
    }

    public Formation(String nom, String type, Date date, String lieu, String description, String heure, int nbrplace, String nomImage) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.heure = heure;
        this.nbrplace = nbrplace;
        this.nomImage = nomImage;
    }

    public Formation(String nom, String type, String lieu, String description, int nbrplace, String nomImage) {
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.description = description;
        this.nbrplace = nbrplace;
        this.nomImage = nomImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getFormateur() {
        return formateur;
    }

    public void setFormateur(int formateur) {
        this.formateur = formateur;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", date=" + date + ", lieu=" + lieu + ", description=" + description + ", heure=" + heure + ", nbrplace=" + nbrplace + ", formateur=" + formateur + ", nomImage=" + nomImage + '}';
    }

   
    
    
    
    
}
