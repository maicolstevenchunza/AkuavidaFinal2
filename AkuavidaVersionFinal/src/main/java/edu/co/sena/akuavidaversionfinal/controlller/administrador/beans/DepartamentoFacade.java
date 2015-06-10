/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Departamento;
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
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    @Override
    public List<Departamento> findAll() {
        Query queryJPQL = getEntityManager().createNamedQuery("Departamento.findAll");
        return queryJPQL.getResultList();
    }

    public List<Departamento> finById(Object id) {
        Query queryJPQL = getEntityManager().createNamedQuery("Departamento.findByIdDepartamento");
        queryJPQL.setParameter("idDepartamento", id);
        return queryJPQL.getResultList();
    }

    public List<Departamento> findByParteNombre(String nombreDepBuscar) {
        String sqlQuery = "SELECT * FROM departamento dep where dep.NOMBRE_DEPARTAMENTO like '%" + nombreDepBuscar + "%';";
        Query query2 = getEntityManager().createNativeQuery(sqlQuery, Departamento.class);
        return query2.getResultList();
    }

    public Departamento findByNombre(Object nombre) {
        Query queryJPQL = getEntityManager().createNamedQuery("Departamento.findByNombreDepartamento");
        queryJPQL.setParameter("nombreDepartamento", nombre);
        return (Departamento) queryJPQL.getSingleResult();
    }

}
