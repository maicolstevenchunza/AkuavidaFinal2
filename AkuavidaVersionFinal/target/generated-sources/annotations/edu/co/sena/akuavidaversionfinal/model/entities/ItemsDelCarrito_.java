package edu.co.sena.akuavidaversionfinal.model.entities;

import edu.co.sena.akuavidaversionfinal.model.entities.CarritoDeCompras;
import edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK;
import edu.co.sena.akuavidaversionfinal.model.entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-04T18:30:06")
@StaticMetamodel(ItemsDelCarrito.class)
public class ItemsDelCarrito_ { 

    public static volatile SingularAttribute<ItemsDelCarrito, Float> costoTotal;
    public static volatile SingularAttribute<ItemsDelCarrito, Integer> cantidad;
    public static volatile SingularAttribute<ItemsDelCarrito, ItemsDelCarritoPK> itemsDelCarritoPK;
    public static volatile SingularAttribute<ItemsDelCarrito, CarritoDeCompras> carritoDeCompras;
    public static volatile SingularAttribute<ItemsDelCarrito, Producto> producto;
    public static volatile SingularAttribute<ItemsDelCarrito, Float> costoUnitario;

}