/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> finById(Object id) {
        Query queryJPQL = getEntityManager().createNamedQuery("Usuario.findByIdUsuario");
        queryJPQL.setParameter("idUsuario", id);
        return queryJPQL.getResultList();
    }
    
    public List<Usuario> findByParteRol(String rolUsuBuscar) {
        Query queryJPQL = getEntityManager().createNamedQuery("Usuario.findByRol");
        queryJPQL.setParameter("rol", rolUsuBuscar);
        return queryJPQL.getResultList();
    }
    
    public List<Usuario> findByParteEstado(String estadoUsuBuscar) {
        Query queryJPQL = getEntityManager().createNamedQuery("Usuario.findByEstado");
        queryJPQL.setParameter("estado", estadoUsuBuscar);
        return queryJPQL.getResultList();
    }
    
    public List<Usuario> findByParteCorreo(String correoUsuBuscar) {
        String sqlQuery = "SELECT * FROM usuario usu where usu.CORREO like '%" + correoUsuBuscar + "%';";
        Query query1 = getEntityManager().createNativeQuery(sqlQuery, Usuario.class);
        return query1.getResultList();
    }
}
