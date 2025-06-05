/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author visitante
 */
public class Bodega {

String pkid; //atributos variables de mi base de datos (conservar)
String tipo_bodega; 
String nombre_bodega;
String direccion; 
String estado;

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getTipo_bodega() {
        return tipo_bodega;
    }

    public void setTipo_bodega(String tipo_bodega) {
        this.tipo_bodega = tipo_bodega;
    }

    public String getNombre_bodega() {
        return nombre_bodega;
    }

    public void setNombre_bodega(String nombre_bodega) {
        this.nombre_bodega = nombre_bodega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Bodega(String pkid, String tipo_bodega, String nombre_bodega, String direccion, String estado) {
        this.pkid = pkid;
        this.tipo_bodega = tipo_bodega;
        this.nombre_bodega = nombre_bodega;
        this.direccion = direccion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Bodega{" + "pkid=" + pkid + ", tipo_bodega=" + tipo_bodega + ", nombre_bodega=" + nombre_bodega + ", direccion=" + direccion + ", estado=" + estado + '}';
    }

    
    
    public Bodega() { //sin nada, sin parametros
    }
    
    

}
