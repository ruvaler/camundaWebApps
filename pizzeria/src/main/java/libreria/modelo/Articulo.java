package libreria.modelo;

import java.io.Serializable;

public class Articulo implements Serializable {

    private String nombrePizza;
    private int cantidad;

    public Articulo(String nombrePizza, int cantidad) {
        this.nombrePizza = nombrePizza;
        this.cantidad = cantidad;
    }

    public String getNombrePizza() {
        return nombrePizza;
    }

    public void setNombrePizza(String nombrePizza) {
        this.nombrePizza = nombrePizza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
