package edu.co.sena.akuavidaversionfinal.model.entities;

import edu.co.sena.akuavidaversionfinal.model.entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-04T18:30:06")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile SingularAttribute<Categorias, Integer> iDCategoria;
    public static volatile SingularAttribute<Categorias, String> nombre;
    public static volatile ListAttribute<Categorias, Producto> productoList;
    public static volatile SingularAttribute<Categorias, Boolean> activa;
    public static volatile SingularAttribute<Categorias, Integer> pariente;

}