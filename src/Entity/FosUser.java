/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author asus
 */
public class FosUser {
   int id;
   String nom;
   String prenom;
   String sexe;
   String datedenaissance;
   int telephone;
   String adresse;
    String email;
    String surnom;
    String motdepasse;
   String roles;
   String grade;

    public FosUser() {
    }

    public FosUser(int id, String nom, String prenom, String sexe, String datedenaissance, int telephone, String adresse, String email, String surnom, String motdepasse, String roles) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datedenaissance = datedenaissance;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.surnom = surnom;
        this.motdepasse = motdepasse;
        this.roles = roles;
    }

    public FosUser(int id, String nom, String adresse, String email, String surnom, String motdepasse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.surnom = surnom;
        this.motdepasse = motdepasse;
    }

    public FosUser(int id, String nom, int telephone, String adresse, String email, String surnom, String motdepasse, String role) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.surnom = surnom;
        this.motdepasse = motdepasse;
        this.roles = roles;
    }

    public FosUser(int id, String nom, int telephone, String adresse, String email, String surnom, String motdepasse) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.surnom = surnom;
        this.motdepasse = motdepasse;
    }

    public FosUser(int id, String nom, String adresse, String email, String surnom, String motdepasse, String grade) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.surnom = surnom;
        this.motdepasse = motdepasse;
        this.grade = grade;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(String datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getRoles() {
        return roles;
    }

    public void setRole(String roles) {
        this.roles = roles;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    

}