package edu.co.sena.akuavidaversionfinal.model.entities;

import edu.co.sena.akuavidaversionfinal.model.entities.Factura;
import edu.co.sena.akuavidaversionfinal.model.entities.Item;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-04T18:30:06")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Float> total;
    public static volatile SingularAttribute<Pedido, Date> fecha;
    public static volatile SingularAttribute<Pedido, Factura> factura;
    public static volatile ListAttribute<Pedido, Item> itemList;
    public static volatile SingularAttribute<Pedido, Integer> facturaIDFactura;
    public static volatile SingularAttribute<Pedido, Float> subtotal;

}