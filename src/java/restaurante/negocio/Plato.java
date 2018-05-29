/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.negocio;

import java.util.List;
import restaurante.entidades.Tipodeproducto;

/**
 *
 * @author Nolasco
 */
public class Plato {

    private int id;
    private String codigo;
    private String nombre;
    private Tipodeproducto tipoDePlato;
    private float precio;
    private String descripcion;
    private List<Porcion> porciones;
    private boolean estado;

    public Plato() {
        this.id = 0;
        this.codigo = "";
        this.nombre = "";
        this.tipoDePlato = null;
        this.precio = 0;
        this.descripcion = "";
        this.porciones = null;
        this.estado=false;
    }

    public Plato(int id, String codigo, String nombre, Tipodeproducto tipoDePlato, float precio, String descripcion, List<Porcion> porciones, boolean estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoDePlato = tipoDePlato;
        this.precio = precio;
        this.descripcion = descripcion;
        this.porciones = porciones;
        this.estado=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipodeproducto getTipoDePlato() {
        return tipoDePlato;
    }

    public void setTipoDePlato(Tipodeproducto tipoDePlato) {
        this.tipoDePlato = tipoDePlato;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Porcion> getPorciones() {
        return porciones;
    }

    public void setPorciones(List<Porcion> porciones) {
        this.porciones = porciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
