/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Municipio;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {
    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    public List<Municipio> finById(Object id) {
        Query queryJPQL = getEntityManager().createNamedQuery("Municipio.findByIdMunicipio");
        queryJPQL.setParameter("idMunicipio", id);
        return queryJPQL.getResultList();
    }
    
    public List<Municipio> findByParteNombre(String nombreMunBuscar) {
        String sqlQuery = "SELECT * FROM municipio mun where mun.NOMBRE_MUNICIPIO like '%" + nombreMunBuscar + "%';";
        Query query2 = getEntityManager().createNativeQuery(sqlQuery, Municipio.class);
        return query2.getResultList();
    }
    
}
