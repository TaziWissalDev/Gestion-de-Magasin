package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    String db = "gestion_magasin";
    String user = "root";
    String pwd = "";
    String url = "jdbc:mysql://localhost:3306/" + db;
    private static Connection con = null;

    public Connexion() {
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connected......");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public static Connection getConnexion() {
        if (con == null) {
            new Connexion();
        }

        return con;
    }

    public void closeConnexion() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
