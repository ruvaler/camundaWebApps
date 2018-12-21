package libreria.iei;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class EnviarTweet implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {


        String nombreCliente = (String) execution.getVariable("IDNombre");

        String pizza1 = (String) execution.getVariable("IDNombrePizza1");
        String pizza2 = (String) execution.getVariable("IDNombrePizza2");
        String pizza3 = (String) execution.getVariable("IDNombrePizza3");
        String pizza4 = (String) execution.getVariable("IDNombrePizza4");

        String tweet = "#El motor de Camunda ha creado un pedido de pizzas para "
                + nombreCliente + " y ha pedido:";

        double precio = (Double) execution.getVariable("IDPrecio");

        if (!pizza1.equals("")) {
            int cantidad = ((Number) execution.getVariable("IDCantidad1")).intValue();
            tweet += "\n- " + cantidad + pizza1;
        }
        if (!pizza2.equals("")) {
            int cantidad = ((Number) execution.getVariable("IDCantidad2")).intValue();
            tweet += "\n- " + cantidad + pizza2;
        }
        if (!pizza3.equals("")) {
            int cantidad = ((Number) execution.getVariable("IDCantidad3")).intValue();
            tweet += "\n- " + cantidad + pizza3;
        }
        if (!pizza4.equals("")) {
            int cantidad = ((Number) execution.getVariable("IDCantidad4")).intValue();
            tweet += "\n- " + cantidad + pizza4;
        }

        String entrega = (String) execution.getVariable("IDEntrega");

        String consumerKey = "thnhVrg0kTNIYg1VjOXKAvFl9";
        String consumerSecret = "jDu4bBrUI5B65FteLl7ymD2xkZqIJ49PwDcGQbfkTehAJIkKSK";
        String accessToken = "3927031924-L3KW0PeyV7oRtJlntCPFVkwwywL1Fkc9JbUk5to";
        String accessTokenSecret = "zdsag2UqUIZDJJgGHvtJfcvG9V9CM8z6FC2CGFICrYNBI";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            twitter.updateStatus(tweet);
            twitter.updateStatus("El importe es: " +
                    precio + " euro(s) y serán entregadas en " + entrega + " minutos");
        } catch (TwitterException e) { e.printStackTrace(); }

    }
}
