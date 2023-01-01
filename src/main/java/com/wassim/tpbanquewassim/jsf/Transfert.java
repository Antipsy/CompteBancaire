/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.wassim.tpbanquewassim.jsf;

import com.wassim.tpbanquewassim.ejb.GestionnaireCompte;
import com.wassim.tpbanquewassim.entities.CompteBancaire;
import jakarta.ejb.EJBTransactionRolledbackException;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;


/**
 *
 * @author Wassim
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    @EJB
  private GestionnaireCompte gestionnaireCompte;

  private long idSource;
  private long idDestination;
  private int montant;

  public long getIdSource() {
    return idSource;
  }

  public void setIdSource(long id) {
    this.idSource = id;
  }

  public long getIdDestination() {
    return idDestination;
  }

  public void setIdDestination(long id) {
    this.idDestination = id;
  }

  public int getMontant() {
    return montant;
  }

  public void setMontant(int montant) {
    this.montant = montant;
  }

  public String enregistrer() {
    boolean erreur = false;

    CompteBancaire source = gestionnaireCompte.findById(idSource);
    if (source == null) {
      
      Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
      erreur = true;
    }

    CompteBancaire destination = gestionnaireCompte.findById(idDestination);
    if (destination == null) {
      
      Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
      erreur = true;
    }

    if (erreur) {
      return null;
    }

    
    try {
      gestionnaireCompte.transferer(source, destination, montant);
      Util.addFlashInfoMessage("Transfert de " + source.getNom() + " vers "
              + destination.getNom()
              + " pour un montant de " + montant + " correctement effectué");
      return "listeComptes?faces-redirect=true";
    } catch (EJBTransactionRolledbackException ex) {
      Util.messageErreur(ex.getMessage(), ex.getMessage(), "form:montant");
      return null; 
    }
  }
}
