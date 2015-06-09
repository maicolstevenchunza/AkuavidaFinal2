/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

/**
 *
 * @author aprendiz
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

    private List<String> imagenes;

    @PostConstruct
    public void init() {
        imagenes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            imagenes.add("imagen" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return imagenes;
    }
}
