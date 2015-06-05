package edu.co.sena.akuavidaversionfinal.model.entities;

import edu.co.sena.akuavidaversionfinal.model.entities.Categorias;
import edu.co.sena.akuavidaversionfinal.model.entities.Inventario;
import edu.co.sena.akuavidaversionfinal.model.entities.Item;
import edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarrito;
import edu.co.sena.akuavidaversionfinal.model.entities.Promociones;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-04T18:30:06")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile ListAttribute<Producto, Inventario> inventarioList;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile SingularAttribute<Producto, String> iDproducto;
    public static volatile SingularAttribute<Producto, byte[]> imagen;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile ListAttribute<Producto, Promociones> promocionesList;
    public static volatile ListAttribute<Producto, ItemsDelCarrito> itemsDelCarritoList;
    public static volatile SingularAttribute<Producto, Integer> cantidad;
    public static volatile ListAttribute<Producto, Item> itemList;
    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Categorias> categoriasIDCategoria;
    public static volatile SingularAttribute<Producto, Float> impuesto;
    public static volatile SingularAttribute<Producto, Boolean> activo;

}