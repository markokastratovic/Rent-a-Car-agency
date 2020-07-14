/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.connection;

import config.Konfiguracija;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private Connection connection;
    public static ConnectionFactory instance;

    private ConnectionFactory() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/bazarentacar";
//        String username = "root";
//        String password = "";
        try {
            connection = DriverManager.getConnection(Konfiguracija.getInstance().getProperty("url"), Konfiguracija.getInstance().getProperty("username"), Konfiguracija.getInstance().getProperty("password"));
        } catch (SQLException ex) {
            throw new SQLException("Connection is not created!" + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }
}
