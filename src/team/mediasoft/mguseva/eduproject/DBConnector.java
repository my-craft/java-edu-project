package team.mediasoft.mguseva.eduproject;

import java.sql.*;

public class DBConnector {
    private String dbDriver = "org.postgresql.Driver";
    private String dbUrl = "jdbc:postgresql://localhost:54328/postgres";
    private Connection connection = null;

    private String login = "postgres";
    private String pass = "postgres";

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void connect() throws Exception {
        try {
            Class.forName(this.dbDriver);
            this.connection = DriverManager.getConnection(this.dbUrl, this.login, this.pass);
        } catch (ClassNotFoundException cnfe) {
            throw new Exception("No driver for PostgreSQL found");
        } catch (SQLException sqle) {
            throw new Exception("Unable to connect to PostgreSQL with the given login and password");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() {
        if(this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception sqle) {
                this.connection = null;
            }
        }
    }
}
