package libreria.iei;

import libreria.modelo.Articulo;
import libreria.modelo.Pedido;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SalvarEnBaseDeDatos implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Pedido pedido = (Pedido) execution.getVariable("miPedido");
        DBUtils.addPedido(pedido);

        int idPedido = DBUtils.getIdPedido(pedido);

        for (Articulo articulo : pedido.getArticulos()) {
            DBUtils.addLineaPedido(articulo, idPedido);
        }

    }
}
