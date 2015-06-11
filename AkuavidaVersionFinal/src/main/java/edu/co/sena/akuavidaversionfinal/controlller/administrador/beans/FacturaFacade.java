/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Factura;
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
public class FacturaFacade extends AbstractFacade<Factura> {
    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    public List<Factura> finByIdFac(Object id) {
        Query queryJPQL = getEntityManager().createNamedQuery("Factura.findByIDFactura");
        queryJPQL.setParameter("iDFactura", id);
        return queryJPQL.getResultList();
    }
    public List<Factura> finByFechaFac(Object fecha) {
        Query queryJPQL = getEntityManager().createNamedQuery("Factura.findByFecha");
        queryJPQL.setParameter("fecha", fecha);
        return queryJPQL.getResultList();
    }
    public List<Factura> finByEstadoFac(Object estado) {
        Query queryJPQL = getEntityManager().createNamedQuery("Factura.findByEstado");
        queryJPQL.setParameter("estado", estado);
        return queryJPQL.getResultList();
    }
}
