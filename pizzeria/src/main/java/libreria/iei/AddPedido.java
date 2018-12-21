package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import libreria.modelo.Articulo;
import libreria.modelo.Pedido;

import java.util.Date;


public class AddPedido implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        Pedido pedido = new Pedido();

        Date fecha = (Date) execution.getVariable("IDFecha");
        pedido.setNombreCliente((String) execution.getVariable("IDNombre"));
        pedido.setDireccion((String) execution.getVariable("IDDireccion"));
        pedido.setFecha(fecha);

        String pizza1 = (String) execution.getVariable("IDNombrePizza1");
        if (! pizza1.equals(""))
            pedido.addLinea(new Articulo(pizza1, ((Number) execution.getVariable("IDCantidad1")).intValue()));

        String pizza2 = (String) execution.getVariable("IDNombrePizza2");
        if (! pizza2.equals(""))
            pedido.addLinea(new Articulo(pizza2, ((Number) execution.getVariable("IDCantidad2")).intValue()));

        String pizza3 = (String) execution.getVariable("IDNombrePizza3");
            if (! pizza3.equals(""))
                pedido.addLinea(new Articulo(pizza3, ((Number) execution.getVariable("IDCantidad3")).intValue()));


        String pizza4 = (String) execution.getVariable("IDNombrePizza4");
            if (! pizza4.equals(""))
                pedido.addLinea(new Articulo(pizza4, ((Number) execution.getVariable("IDCantidad4")).intValue()));


        execution.setVariable("miPedido", pedido);
    }
}
