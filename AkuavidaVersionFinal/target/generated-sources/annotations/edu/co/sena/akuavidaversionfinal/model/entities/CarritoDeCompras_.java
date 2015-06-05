package edu.co.sena.akuavidaversionfinal.model.entities;

import edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarrito;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-04T18:30:06")
@StaticMetamodel(CarritoDeCompras.class)
public class CarritoDeCompras_ { 

    public static volatile SingularAttribute<CarritoDeCompras, Float> total;
    public static volatile SingularAttribute<CarritoDeCompras, String> iDCarrito;
    public static volatile ListAttribute<CarritoDeCompras, ItemsDelCarrito> itemsDelCarritoList;
    public static volatile SingularAttribute<CarritoDeCompras, Float> subtotal;

}