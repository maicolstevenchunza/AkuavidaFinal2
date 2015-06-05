/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Categorias;
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
public class CategoriasFacade extends AbstractFacade<Categorias> {
    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasFacade() {
        super(Categorias.class);
    }
     @Override
    public void remove(Categorias entity) {
        getEntityManager().remove(getEntityManager().find(Categorias.class, entity.getIDCategoria()));
        
    }

    public List<Categorias> finById(Object id) {
        Query queryJPQL = getEntityManager().createNamedQuery("Categorias.findByIDCategoria");
        queryJPQL.setParameter("iDCategoria", id);
        return queryJPQL.getResultList();
    }

    public List<Categorias> findByNombreCateg(String nombreCatBuscar) {
        Query queryJPQL = getEntityManager().createNamedQuery("Categorias.findByNombre");
        queryJPQL.setParameter("nombre", nombreCatBuscar);
        return queryJPQL.getResultList();

    }

    public List<Categorias> findByEstado(boolean estado) {
        Query queryJPQL = getEntityManager().createNamedQuery("Categorias.findByActiva");
        queryJPQL.setParameter("activa", estado);
        return queryJPQL.getResultList();
    }
    
}
