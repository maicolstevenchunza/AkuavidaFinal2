/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.general.util;

import edu.co.sena.akuavidaversionfinal.controlller.administrador.dto.CuentaDTO;
import edu.co.sena.akuavidaversionfinal.model.entities.Cuenta;
import edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK;
import edu.co.sena.akuavidaversionfinal.model.entities.Domicilio;
import edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK;
import edu.co.sena.akuavidaversionfinal.model.entities.Usuario;

/**
 *
 * @author aprendiz
 */
public class ConversorDTO {

    public static Cuenta conversorCuentaDTOACuenta(CuentaDTO recibida) {
        Cuenta ct = new Cuenta();
        ct.setCuentaPK(new CuentaPK(recibida.getTipoDocumento(), recibida.getNumeroDocumento()));
        ct.setPrimerNombre(recibida.getPrimerNombre());
        ct.setSegundoNombre(recibida.getSegundoApellido());
        ct.setPrimerApellido(recibida.getPrimerApellido());
        ct.setSegundoApellido(recibida.getSegundoApellido());

        Domicilio dt = new Domicilio();
        dt.setDomicilioPK(new DomicilioPK(recibida.getTipoDocumento(), recibida.getNumeroDocumento()));
        dt.setCiudad(recibida.getCuidad());
        dt.setDireccion(recibida.getDireccionAlterna());
        dt.setTelefono(recibida.getTelefonoAlterno());
        dt.setMunicipioidMunicipio(recibida.getMunicipio());
        ct.setDomicilio(dt);

        Usuario usu = new Usuario();
        usu.setIdUsuario(recibida.getIdUsuario());
        usu.setRol(recibida.getRol());
        usu.setEstado(recibida.getEstado());
        usu.setContrasena(recibida.getContrasena());
        usu.setCorreo(recibida.getCorreo());
        ct.setUsuarioidUsuario(usu);
        return ct;
    }
}
