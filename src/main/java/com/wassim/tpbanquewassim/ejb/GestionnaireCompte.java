/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wassim.tpbanquewassim.ejb;

import com.wassim.tpbanquewassim.entities.CompteBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.TypedQuery;
import java.util.List;

@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              
    password="root", 
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class GestionnaireCompte {
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    public void creerCompte(CompteBancaire c){
        em.persist(c);
    }
    public List<CompteBancaire> getAllComptes() {
    TypedQuery query
            = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
    return query.getResultList();
  }

    public long nbComptes() {
    TypedQuery<Long> query
            = em.createQuery("select count(c) from CompteBancaire c", Long.class);
    return query.getSingleResult();
  }
     public void transferer(CompteBancaire source, CompteBancaire destination,int montant) {
    try {
      source.retirer(montant);
      destination.deposer(montant);
      update(source);
      update(destination);
    } catch (CompteException ex) {
      throw new EJBTransactionRolledbackException(ex.getLocalizedMessage(), ex);
    }
  }
     public CompteBancaire update(CompteBancaire compteBancaire) {
    return em.merge(compteBancaire);
    }
    public CompteBancaire findById(long id) {
    return em.find(CompteBancaire.class, id);
  }
    /**
   * Dépôt d'argent sur un compte bancaire.
   *
   * @param compteBancaire
   * @param montant
   */
  public void deposer(CompteBancaire compteBancaire, int montant) {
    compteBancaire.deposer(montant);
    update(compteBancaire);
  }

  /**
   * Retrait d'argent sur un compte bancaire.
   *
   * @param compteBancaire
   * @param montant
   */
  public void retirer(CompteBancaire compteBancaire, int montant) {
    try {
      compteBancaire.retirer(montant);
      update(compteBancaire);
    } catch (CompteException ex) {
      throw new EJBTransactionRolledbackException(ex.getLocalizedMessage(), ex);
    }
  }
}
