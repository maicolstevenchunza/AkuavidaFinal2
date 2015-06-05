package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.Municipio;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.MunicipioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("municipioController")
@SessionScoped
public class MunicipioController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.MunicipioFacade ejbFacade;
    private List<Municipio> items = null;
    private List<Municipio> itemsBuscados = null;
    private Municipio selected;
    private Municipio selectedBuscar;

    private Integer idBuscar;
    private String nombreBuscar;

    public MunicipioController() {
    }

    public Municipio getSelected() {
        return selected;
    }

    public void setSelected(Municipio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MunicipioFacade getFacade() {
        return ejbFacade;
    }

    public Municipio prepareCreate() {
        selected = new Municipio();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioUpdated"));
        selectedBuscar = null;
        itemsBuscados = null;
    }

    public void updateBuscar() {
        persist(PersistAction.UPDATEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("MunicipioUpdated"));
        items = null;
        selected = null;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MunicipioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void eliminarBuscado() {
        persist(PersistAction.DELETEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("MunicipioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        itemsBuscados = null;
        selectedBuscar = null;
    }

    public List<Municipio> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Municipio> buscarPorId() {
        itemsBuscados = getFacade().finById(idBuscar);
        nombreBuscar = null;
        items = null;
        return itemsBuscados;
    }

    public List<Municipio> buscarPorNombre() {
        itemsBuscados = getFacade().findByParteNombre(nombreBuscar);
        items = null;
        idBuscar = null;
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
                    idBuscar = null;
                    nombreBuscar = null;
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

    public Municipio getMunicipio(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Municipio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Municipio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Municipio> getItemsBuscados() {
        return itemsBuscados;
    }

    public void setItemsBuscados(List<Municipio> itemsBuscados) {
        this.itemsBuscados = itemsBuscados;
    }

    public Municipio getSelectedBuscar() {
        return selectedBuscar;
    }

    public void setSelectedBuscar(Municipio selectedBuscar) {
        this.selectedBuscar = selectedBuscar;
    }

    public Integer getIdBuscar() {
        return idBuscar;
    }

    public void setIdBuscar(Integer idBuscar) {
        this.idBuscar = idBuscar;
    }

    public String getNombreBuscar() {
        return nombreBuscar;
    }

    public void setNombreBuscar(String nombreBuscar) {
        this.nombreBuscar = nombreBuscar;
    }

    @FacesConverter(forClass = Municipio.class)
    public static class MunicipioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipioController controller = (MunicipioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipioController");
            return controller.getMunicipio(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Municipio) {
                Municipio o = (Municipio) object;
                return getStringKey(o.getIdMunicipio().toString());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Municipio.class.getName()});
                return null;
            }
        }

    }

}
