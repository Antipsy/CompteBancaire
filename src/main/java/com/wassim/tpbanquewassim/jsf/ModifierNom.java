/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.wassim.tpbanquewassim.jsf;

import com.wassim.tpbanquewassim.ejb.GestionnaireCompte;
import com.wassim.tpbanquewassim.entities.CompteBancaire;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author Wassim
 */
@Named(value = "modifierNom")
@ViewScoped
public class ModifierNom implements Serializable {

    /**
     * Creates a new instance of ModifierNom
     */
    @EJB
  private GestionnaireCompte gestionnaireCompte;

  private Long id;
  private CompteBancaire compte;
  private String nom;

  public String getNom() {
    return nom;
  }

  public void setMontant(String nom) {
    this.nom = nom;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CompteBancaire getCompte() {
    return compte;
  }

  public void loadCompte() {
    compte = gestionnaireCompte.findById(id);
  }

  public String enregistrer() {
    gestionnaireCompte.update(compte);
    Util.addFlashInfoMessage("Nouveau nom enregistré : " + compte.getNom());
    return "listeComptes?faces-redirect=true";
  }
    
}
