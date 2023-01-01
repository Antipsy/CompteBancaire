/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wassim.tpbanquewassim.entities;

import com.wassim.tpbanquewassim.ejb.CompteException;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author Wassim
 */
@NamedQuery(name = "CompteBancaire.findAll", query = "select c from CompteBancaire c")
@Entity
public class CompteBancaire implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String nom;
  private int solde;

  public CompteBancaire(String nom, int solde) {
    this.nom = nom;
    this.solde = solde;
  }

  public CompteBancaire() {
  }

  public Long getId() {
    return id;
  }

  /**
   * Get the value of nom
   *
   * @return the value of nom
   */
  public String getNom() {
    return nom;
  }

  /**
   * Set the value of nom
   *
   * @param nom new value of nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getSolde() {
    return solde;
  }

  public void setSolde(int solde) {
    this.solde = solde;
  }

  public void deposer(int montant) {
    solde += montant;
  }

  public void retirer(int montant) throws CompteException {
    if (montant <= solde) {
      solde -= montant;
    } else {
      throw new CompteException("Solde du compte de " + this.nom 
              + " est " + solde
              + " ; insuffisant pour un retrait de " + montant);
    }
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object
  ) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof CompteBancaire)) {
      return false;
    }
    CompteBancaire other = (CompteBancaire) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }
    @Override
    public String toString() {
        return "com.wassim.tpbanquewassim.entities.CompteBancaire[ id=" + id + " ]";
    }
  

}

    
    

