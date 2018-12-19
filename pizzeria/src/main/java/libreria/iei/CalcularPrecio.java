package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalcularPrecio implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String NombrePizza = (String) execution.getVariable("IDNombrePizza");
        int cantidad = ((Number) execution.getVariable("IDCantidad")).intValue();

        System.out.println("Calculando el precio de " + cantidad + " " + NombrePizza + "pizzas");

        double precio = 0.0;
        if("margarita".equalsIgnoreCase(NombrePizza)) { precio = cantidad * 6.0; }
        else { precio = cantidad * 8.0; }

        System.out.println("El precio será " + precio);

        execution.setVariable("IDPrecio", precio);
    }
}
