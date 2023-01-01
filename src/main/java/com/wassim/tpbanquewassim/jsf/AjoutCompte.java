/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.wassim.tpbanquewassim.jsf;

import com.wassim.tpbanquewassim.ejb.GestionnaireCompte;
import com.wassim.tpbanquewassim.entities.CompteBancaire;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Wassim
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    @EJB
  private GestionnaireCompte gestionnaireCompte;
  
  private String nom;
  private int solde;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getSolde() {
    return solde;
  }

  public void setSolde(int solde) {
    this.solde = solde;
  }

  public String creer() {
    gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
    return "listeComptes?faces-redirect=true";
  }
    
}
