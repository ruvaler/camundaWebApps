package libreria.iei;

import libreria.modelo.Pedido;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;

public class ValidarPedido implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Pedido pedido = (Pedido) execution.getVariable("miPedido");

        List<String> nombresPizzas = pedido.getNombrePedidosString();

        List<String> pizzas = DBUtils.getNombrePizzas();

        for (int i = 0; i < nombresPizzas.size(); i++) {
            if (!pizzas.contains(nombresPizzas.get(i))) pedido.removeLinea(i);
        }

        boolean isPedidoValido = (pizzas.contains(nombresPizzas.get(0))
                || pizzas.contains(nombresPizzas.get(1))
                || pizzas.contains(nombresPizzas.get(2))
                || pizzas.contains(nombresPizzas.get(3)));

        execution.setVariable("PedidoValido", isPedidoValido);
    }
}
