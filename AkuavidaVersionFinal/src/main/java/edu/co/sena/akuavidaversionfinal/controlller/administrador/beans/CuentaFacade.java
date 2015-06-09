/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Cuenta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aprendiz
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }

    public List<Cuenta> finByTipoDocumento(String tipoDocumento) {
        Query queryJPQL = getEntityManager().createNamedQuery("Cuenta.findByTipoDocumento");
        queryJPQL.setParameter("tipoDocumento", tipoDocumento);
        return queryJPQL.getResultList();
    }
    
    public List<Cuenta> finByNumeroDocumento(String numeroDocumento) {
        Query queryJPQL = getEntityManager().createNamedQuery("Cuenta.findByNumeroDocumento");
        queryJPQL.setParameter("numeroDocumento", numeroDocumento);
        return queryJPQL.getResultList();
    }
}
