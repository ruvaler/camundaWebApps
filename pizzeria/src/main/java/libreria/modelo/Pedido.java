package libreria.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {

    private Date fecha;
    private double total;
    private String nombreCliente;
    private String direccion;
    private List<Articulo> articulos;

    public Pedido() {
        this.articulos = new ArrayList<>();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void addLinea(Articulo articulo) {
        articulos.add(articulo);
    }

    public void removeLinea(int index) {
        articulos.remove(index);
    }

    public List<String> getNombrePedidosString() {
        List<String> nombres = new ArrayList<>();

        for (Articulo articulo : articulos) {
            nombres.add(articulo.getNombrePizza());
        }

        return nombres;
    }
}
