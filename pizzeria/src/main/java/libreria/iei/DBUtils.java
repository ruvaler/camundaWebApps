package libreria.iei;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static List<String> getNombrePizzas() throws SQLException {
        List<String> pizzas = new ArrayList<>();

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pizzeria",
                "root",
                "root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PIZZAS");
        while (rs.next()) {
            pizzas.add(rs.getString("Nombre"));
        }
        con.close();

        return pizzas;
    }
}
