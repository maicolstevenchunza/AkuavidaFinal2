package edu.co.sena.akuavidaversionfinal.model.entities;

import edu.co.sena.akuavidaversionfinal.model.entities.Cuenta;
import edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK;
import edu.co.sena.akuavidaversionfinal.model.entities.Municipio;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-04T18:30:06")
@StaticMetamodel(Domicilio.class)
public class Domicilio_ { 

    public static volatile SingularAttribute<Domicilio, String> direccion;
    public static volatile SingularAttribute<Domicilio, DomicilioPK> domicilioPK;
    public static volatile SingularAttribute<Domicilio, String> ciudad;
    public static volatile SingularAttribute<Domicilio, String> telefono;
    public static volatile SingularAttribute<Domicilio, Cuenta> cuenta;
    public static volatile SingularAttribute<Domicilio, Municipio> municipioidMunicipio;

}