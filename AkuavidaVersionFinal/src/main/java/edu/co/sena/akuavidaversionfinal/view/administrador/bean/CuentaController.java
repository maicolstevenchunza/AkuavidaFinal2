package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.Cuenta;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.CuentaFacade;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.DepartamentoFacade;
import edu.co.sena.akuavidaversionfinal.model.entities.Departamento;
import edu.co.sena.akuavidaversionfinal.model.entities.Domicilio;
import edu.co.sena.akuavidaversionfinal.model.entities.Municipio;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("cuentaController")
@ViewScoped
public class CuentaController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.CuentaFacade ejbFacade;
    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.DepartamentoFacade ejbFacadeDep;
    private List<Cuenta> items = null;
    private Cuenta selected;
    private final List<String> listTipoDocumentos;
    private List<Cuenta> itemsBuscados = null;
    private Cuenta selectedBuscar;
    private String tipoBuscar;
    private String numeroBuscar;
    
    
    private List<Departamento> itemDep = null;
    private List<Municipio> itemsMunicipio = null;
    private String departamentoSeleccionado;
    private String municipioSeleccionado;

    public CuentaController() {
        this.listTipoDocumentos = Arrays.asList(ResourceBundle.getBundle("/Bundle").getString("SelectTipoCedula"),
                ResourceBundle.getBundle("/Bundle").getString("SelectTipoTarjetaIdentidad"),
                ResourceBundle.getBundle("/Bundle").getString("SelectTipoNIT"),
                ResourceBundle.getBundle("/Bundle").getString("SelectTipoCedulaExtranjeria"));
    }

    public Cuenta getSelected() {
        return selected;
    }

    public void setSelected(Cuenta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setCuentaPK(new edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK());
    }

    private CuentaFacade getFacade() {
        return ejbFacade;
    }

    private DepartamentoFacade getFacadeDep() {
        return ejbFacadeDep;
    }

    public Cuenta prepareCreate() {
        selected = new Cuenta();
        selected.setDomicilio(new Domicilio());
        initializeEmbeddableKey();
        obtenedorDepartamentos();
        return selected;
    }

    public void obtenedorDepartamentos() {
        if (itemDep == null) {
            itemDep =  getFacadeDep().findAll();
        }
    }

    public void obtenedorMunicipios() {
        Departamento dt = getFacadeDep().findByNombre(departamentoSeleccionado);
        itemsMunicipio = dt.getMunicipioList();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CuentaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CuentaUpdated"));

    }

    public void updateBuscar() {
        persist(PersistAction.UPDATEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("CuentaUpdated"));
        items = null;
        selected = null;

    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CuentaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void eliminarBuscado() {
        persist(PersistAction.DELETEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("CuentaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        itemsBuscados = null;
        selectedBuscar = null;

    }

    public List<Cuenta> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

//    public List<Departamento> getItemsDepa() {
//        if (itemDep == null) {
//            itemDep = getFacadeDep().findAll();
//        }
//        return itemDep;
//    }

    public List<Cuenta> buscarPorTipoDoc() {
        itemsBuscados = getFacade().finByTipoDocumento(tipoBuscar);
        items = null;
        return itemsBuscados;
    }

    public List<Cuenta> buscarPorNumeroDoc() {
        itemsBuscados = getFacade().finByNumeroDocumento(numeroBuscar);
        items = null;
        return itemsBuscados;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }

        if (selectedBuscar != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETEBUSCAR) {
                    getFacade().edit(selectedBuscar);
                } else {
                    getFacade().remove(selectedBuscar);
                    tipoBuscar = null;
                    numeroBuscar = null;
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Cuenta getCuenta(edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK id) {
        return getFacade().find(id);
    }

    public List<Cuenta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cuenta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<String> getListTipoDocumentos() {
        return listTipoDocumentos;
    }

    public String getTipoBuscar() {
        return tipoBuscar;
    }

    public void setTipoBuscar(String tipoBuscar) {
        this.tipoBuscar = tipoBuscar;
    }

    public String getNumeroBuscar() {
        return numeroBuscar;
    }

    public void setNumeroBuscar(String numeroBuscar) {
        this.numeroBuscar = numeroBuscar;
    }

    public Cuenta getSelectedBuscar() {
        return selectedBuscar;
    }

    public void setSelectedBuscar(Cuenta selectedBuscar) {
        this.selectedBuscar = selectedBuscar;
    }

    public List<Cuenta> getItemsBuscados() {
        return itemsBuscados;
    }

    public void setItemsBuscados(List<Cuenta> itemsBuscados) {
        this.itemsBuscados = itemsBuscados;
    }

    public List<Departamento> getItemDep() {
        return itemDep;
    }

    public void setItemDep(List<Departamento> itemDep) {
        this.itemDep = itemDep;
    }

    public List<Municipio> getItemsMunicipio() {
        return itemsMunicipio;
    }

    public void setItemsMunicipio(List<Municipio> itemsMunicipio) {
        this.itemsMunicipio = itemsMunicipio;
    }

    public String getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(String departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public String getMunicipioSeleccionado() {
        return municipioSeleccionado;
    }

    public void setMunicipioSeleccionado(String municipioSeleccionado) {
        this.municipioSeleccionado = municipioSeleccionado;
    }

    @FacesConverter(forClass = Cuenta.class)
    public static class CuentaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuentaController controller = (CuentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuentaController");
            return controller.getCuenta(getKey(value));
        }

        edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK getKey(String value) {
            edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK();
            key.setTipoDocumento(values[0]);
            key.setNumeroDocumento(values[1]);
            return key;
        }

        String getStringKey(edu.co.sena.akuavidaversionfinal.model.entities.CuentaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getTipoDocumento());
            sb.append(SEPARATOR);
            sb.append(value.getNumeroDocumento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cuenta) {
                Cuenta o = (Cuenta) object;
                return getStringKey(o.getCuentaPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cuenta.class.getName()});
                return null;
            }
        }

    }

}
