package libreria.modelo;

import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {

    private LocalDateTime fecha;
    private double total;
    private String nombreCliente;
    private String direccion;
    private List<Articulo> articulos;

    public Pedido() {
        this.articulos = new ArrayList<>();
    }

    public Pedido(LocalDateTime fecha, double total, String nombreCliente, String direccion) {
        this.fecha = fecha;
        this.total = total;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.articulos = new ArrayList<>();
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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
