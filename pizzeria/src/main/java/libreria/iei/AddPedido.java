package libreria.iei;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import libreria.modelo.Articulo;
import libreria.modelo.Pedido;
import org.joda.time.LocalDateTime;

import java.util.List;

public class AddPedido implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String pizza1 = (String) execution.getVariable("IDNombrePizza1");
        String pizza2 = (String) execution.getVariable("IDNombrePizza2");
        String pizza3 = (String) execution.getVariable("IDNombrePizza3");
        String pizza4 = (String) execution.getVariable("IDNombrePizza4");

        Pedido pedido = new Pedido();

        pedido.setFecha((LocalDateTime) execution.getVariable("IDFecha"));
        pedido.setNombreCliente((String) execution.getVariable("IDNombre"));
        pedido.setDireccion((String) execution.getVariable("IDDireccion"));


        int cantidad1 = ((Number) execution.getVariable("IDCantidad1")).intValue();
        pedido.addLinea(new Articulo(pizza1, cantidad1));


        int cantidad2 = ((Number) execution.getVariable("IDCantidad2")).intValue();
        pedido.addLinea(new Articulo(pizza2, cantidad2));


        int cantidad3 = ((Number) execution.getVariable("IDCantidad3")).intValue();
        pedido.addLinea(new Articulo(pizza3, cantidad3));

        int cantidad4 = ((Number) execution.getVariable("IDCantidad4")).intValue();
        pedido.addLinea(new Articulo(pizza4, cantidad4));

        execution.setVariable("miPedido", pedido);
    }
}
