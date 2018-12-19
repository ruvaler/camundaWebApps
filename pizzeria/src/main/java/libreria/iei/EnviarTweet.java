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
        int cantidad = ((Number) execution.getVariable("IDCantidad")).intValue();
        String pizza = (String) execution.getVariable("IDNombrePizza");
        double precio = (Double) execution.getVariable("IDPrecio");
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
            twitter.updateStatus("#El motor de Camunda ha creado un pedido de pizza: " +
                    nombreCliente + " pedidas " + cantidad + " de " + pizza );
            twitter.updateStatus("La pizza es/son de " + pizza + " y el importe es: " +
                    precio + " euro(s) y serán entregadas en " + entrega + " minutos");
        } catch (TwitterException e) { e.printStackTrace(); }

    }
}
