package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.Categorias;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.CategoriasFacade;

import java.io.Serializable;
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

@Named("categoriasController")
@ViewScoped
public class CategoriasController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.CategoriasFacade ejbFacade;
        private List<Categorias> items = null;
    private List<Categorias> itemsBuscados = null;
    private Categorias selected;
    private Categorias selectedBuscar;

    private Integer idBuscar;
    private String nombreBuscar;
    private boolean activoBuscado;



    public CategoriasController() {

    }

    public Categorias getSelected() {
        return selected;
    }

    public void setSelected(Categorias selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CategoriasFacade getFacade() {
        return ejbFacade;
    }

    public Categorias prepareCreate() {
        selected = new Categorias();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CategoriasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CategoriasUpdated"));
        selectedBuscar = null;
        itemsBuscados = null;
    }

    public void updateBuscar() {
        persist(PersistAction.UPDATEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("CategoriasUpdated"));
        items = null;
        selected = null;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CategoriasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void eliminarBuscado() {
        persist(PersistAction.DELETEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("CategoriasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        itemsBuscados = null;
        selectedBuscar = null;
    }

    public List<Categorias> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Categorias> buscarPorId() {
        itemsBuscados = getFacade().finById(idBuscar);
        nombreBuscar = null;
        items = null;
        return itemsBuscados;
    }

    public List<Categorias> buscarPorNombre() {
        itemsBuscados = getFacade().findByNombreCateg(nombreBuscar);
        items = null;
        idBuscar = null;
        return itemsBuscados;
    }

    public List<Categorias> buscarEstado() {
        itemsBuscados = getFacade().findByEstado(activoBuscado);
        items = null;
        nombreBuscar = null;
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

    public Categorias getCategorias(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Categorias> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Categorias> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Categorias> getItemsBuscados() {
        return itemsBuscados;
    }

    public void setItemsBuscados(List<Categorias> itemsBuscados) {
        this.itemsBuscados = itemsBuscados;
    }

    public Categorias getSelectedBuscar() {
        return selectedBuscar;
    }

    public void setSelectedBuscar(Categorias selectedBuscar) {
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

    public boolean getActivoBuscado() {
        return activoBuscado;
    }

    public void setActivoBuscado(boolean activoBuscado) {
        this.activoBuscado = activoBuscado;
    }

    @FacesConverter(forClass = Categorias.class)
    public static class CategoriasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriasController controller = (CategoriasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriasController");
            return controller.getCategorias(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Categorias) {
                Categorias o = (Categorias) object;
                return getStringKey(o.getIDCategoria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Categorias.class.getName()});
                return null;
            }
        }

    }

}
