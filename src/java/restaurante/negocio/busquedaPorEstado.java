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
public enum busquedaPorEstado {
    ACTIVO(1),
    INACTIVO(2),
    TODOS(3);
    
    private final int value;

    private busquedaPorEstado(int value) {
        this.value = value;
    }
    
    public int getValue(){
       return value;
    }
}
