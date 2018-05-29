/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.negocio;

/**
 *
 * @author Nolasco
 */
public class Porcion {
    private int id;
    private String codigo;
    private String nombre;
    private TipoDePorcion tipoDePorcion;
    private float precio;
    private boolean estado;

    public Porcion() {
        this.id = 0;
        this.codigo = "";
        this.nombre = "";
        this.tipoDePorcion = null;
        this.precio = 0;
        this.estado = false;
    }

    public Porcion(int id, String codigo, String nombre, TipoDePorcion tipoDePorcion, float precio, boolean estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoDePorcion = tipoDePorcion;
        this.precio = precio;
        this.estado = estado;
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

    public TipoDePorcion getTipoDePorcion() {
        return tipoDePorcion;
    }

    public void setTipoDePorcion(TipoDePorcion tipoDePorcion) {
        this.tipoDePorcion = tipoDePorcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
