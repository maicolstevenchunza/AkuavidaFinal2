/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.controlller.administrador.beans;

import edu.co.sena.akuavidaversionfinal.model.entities.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "edu.co.sena_AkuavidaVersionFinal_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> finById(Object id) {
        Query queryJPQL = getEntityManager().createNamedQuery("Producto.findByIDproducto");
        queryJPQL.setParameter("iDproducto", id);
        return queryJPQL.getResultList();
    }

    public List<Producto> findByNombre(String nombreProBuscar) {
        String sqlQuery = "SELECT * FROM producto pro where pro.Nombre like '%"
                + nombreProBuscar + "%';";
        Query query2 = getEntityManager().createNativeQuery(sqlQuery, Producto.class);
        return query2.getResultList();
    }

    public List<Producto> findByIdCategoria(int nombreCategoria) {
        String sqlQuery = "SELECT * FROM producto pro where pro.Categorias_ID_Categoria like "
                + "'%" + nombreCategoria + "%';";
        Query query3 = getEntityManager().createNativeQuery(sqlQuery, Producto.class);
        return query3.getResultList();
    }

    public List<Producto> findByActiva(boolean ActivoPro) {
        Query queryJPQL = getEntityManager().createNamedQuery("Producto.findByActivo");
        queryJPQL.setParameter("activo", ActivoPro);
        return queryJPQL.getResultList();
    }
    
}
