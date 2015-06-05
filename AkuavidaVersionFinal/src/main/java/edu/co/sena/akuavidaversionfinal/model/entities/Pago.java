/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aprendiz
 */
@Entity
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findByFacturaIDFactura", query = "SELECT p FROM Pago p WHERE p.facturaIDFactura = :facturaIDFactura"),
    @NamedQuery(name = "Pago.findByTipoPago", query = "SELECT p FROM Pago p WHERE p.tipoPago = :tipoPago"),
    @NamedQuery(name = "Pago.findByNumeroTarjeta", query = "SELECT p FROM Pago p WHERE p.numeroTarjeta = :numeroTarjeta")})
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Factura_ID_Factura")
    private Integer facturaIDFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tipo_Pago")
    private String tipoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Numero_Tarjeta")
    private String numeroTarjeta;
    @JoinColumn(name = "Factura_ID_Factura", referencedColumnName = "ID_Factura", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Factura factura;

    public Pago() {
    }

    public Pago(Integer facturaIDFactura) {
        this.facturaIDFactura = facturaIDFactura;
    }

    public Pago(Integer facturaIDFactura, String tipoPago, String numeroTarjeta) {
        this.facturaIDFactura = facturaIDFactura;
        this.tipoPago = tipoPago;
        this.numeroTarjeta = numeroTarjeta;
    }

    public Integer getFacturaIDFactura() {
        return facturaIDFactura;
    }

    public void setFacturaIDFactura(Integer facturaIDFactura) {
        this.facturaIDFactura = facturaIDFactura;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaIDFactura != null ? facturaIDFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.facturaIDFactura == null && other.facturaIDFactura != null) || (this.facturaIDFactura != null && !this.facturaIDFactura.equals(other.facturaIDFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.co.sena.akuavidaversionfinal.model.entities.Pago[ facturaIDFactura=" + facturaIDFactura + " ]";
    }
    
}
