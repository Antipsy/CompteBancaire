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
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Wassim
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    @EJB
  private GestionnaireCompte gestionnaireCompte;
  
  private List<CompteBancaire> listeComptes;
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    public List<CompteBancaire> getAllComptes() {
    if (listeComptes == null) {
      listeComptes = gestionnaireCompte.getAllComptes();
    }
    return listeComptes;
    }
    public boolean filterBySolde(Object valeurColonne, Object valeurFiltre, Locale locale) {
    String valeurFiltreString = (String) valeurFiltre;
    if (valeurFiltreString.equals("")) {
      return true;
    }
    try {
      return (Integer) valeurColonne >= Integer.valueOf(valeurFiltreString);
    } catch (NumberFormatException e) {
      // On ne fait pas de sélection si le filtre ne contient pas un nombre
      return true;
    }
  }
}
