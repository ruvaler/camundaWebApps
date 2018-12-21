package libreria.iei;

import libreria.modelo.Articulo;
import libreria.modelo.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static final String SOURCE_URL = "jdbc:mysql://localhost:3306/pizzeria";

    private static Connection conexion = null;

    private static Connection abrirConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conexion = DriverManager.getConnection(SOURCE_URL,
                    "root",
                    "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexion;
    }

    private static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión con la BD");
            }
        }
    }

    public static List<String> getNombrePizzas() throws SQLException {
        List<String> pizzas = new ArrayList<>();

        Connection con = DBUtils.abrirConexion();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PIZZAS");
        while (rs.next()) {
            pizzas.add(rs.getString("Nombre"));
        }


        DBUtils.cerrarConexion();
        return pizzas;
    }

    public static double getPrecioPizza(String nombre) throws SQLException {
        Connection con = DBUtils.abrirConexion();


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PIZZAS WHERE "
                + "NOMBRE ='" + nombre + "'");

        double precio;
        if (rs.next()) {
            precio = rs.getDouble("Precio");
            DBUtils.cerrarConexion();
            return precio;
        } else {
            throw new SQLException();
        }
    }

    public static void addPedido(Pedido pedido) throws SQLException {
        Connection con = DBUtils.abrirConexion();

        String SQL = "INSERT INTO pedido (`Fecha`,`Total`,`NombreCliente`,`Direccion`) VALUES(?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS);

        java.sql.Date fechaSQL = new java.sql.Date(pedido.getFecha().getTime());
        statement.setDate(1, fechaSQL);
        statement.setString(2, pedido.getNombreCliente());
        statement.setString(3, pedido.getDireccion());
        statement.setDouble(4, pedido.getTotal());
        statement.executeUpdate();

        DBUtils.cerrarConexion();
    }

    public static void addLineaPedido(Articulo articulo, int idPedido) throws SQLException {
        Connection con = DBUtils.abrirConexion();

        String SQL = "INSERT INTO LINEAPEDIDO (`idPedido`,`NombrePizza`,`Cantidad`,`ImportLinea`) VALUES (?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS);

        double importe = articulo.getCantidad() * getPrecioPizza(articulo.getNombrePizza());
        statement.setInt(1, idPedido);
        statement.setString(2, articulo.getNombrePizza());
        statement.setInt(3, articulo.getCantidad());
        statement.setDouble(4, importe);
        statement.executeUpdate();

        DBUtils.cerrarConexion();
    }

    public static int getIdPedido(Pedido pedido) throws SQLException {
        Connection con = DBUtils.abrirConexion();

        String query = "SELECT * FROM PEDIDO WHERE NOMBRECLIENTE='"
                + pedido.getNombreCliente() + "' AND FECHA='"
                + pedido.getFecha() + "'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        DBUtils.cerrarConexion();

        if (rs.next()) return rs.getInt("idPedido");
        else throw new SQLException();
    }
}
