package libreria.iei;

import libreria.modelo.Pedido;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;

public class ValidarPedido implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Pedido pedido = (Pedido) execution.getVariable("miPedido");

        List<String> nombrePizzas = pedido.getNombrePedidosString();
        List<String> pizzas = DBUtils.getNombrePizzas();

        boolean esPedidoValido = false;
        for (int i = 0; i < nombrePizzas.size(); i++) {
            if (!pizzas.contains(nombrePizzas.get(i))) pedido.removeLinea(i);
            else esPedidoValido = true;
        }
        execution.setVariable("miPedido", pedido);
        execution.setVariable("PedidoValido", esPedidoValido);
    }
}
