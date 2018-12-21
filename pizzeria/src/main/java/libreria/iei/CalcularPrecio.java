package libreria.iei;

import libreria.modelo.Articulo;
import libreria.modelo.Pedido;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalcularPrecio implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Pedido pedido = (Pedido) execution.getVariable("miPedido");
        double precio = 0.0;

        for(Articulo articulo : pedido.getArticulos()) {
            double precioPizza = DBUtils.getPrecioPizza(articulo.getNombrePizza());
            precio += (precioPizza * articulo.getCantidad());
        }
        pedido.setTotal(precio);
        execution.setVariable("miPedido", pedido);
        execution.setVariable("IDPrecio", precio);

    }
}
