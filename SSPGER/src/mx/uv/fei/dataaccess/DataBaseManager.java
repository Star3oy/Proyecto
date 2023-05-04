package mx.uv.fei.dataaccess;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sue
 */

public class DataBaseManager {
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = connect();
            }
        } catch (SQLException sQLException) {
            throw new SQLException("Lo sentimos, no fue posible conectarse con la base de datos del sistema");
        }
        return connection;
    }
    
    private static Connection connect() throws SQLException {
        Connection newConnection = null;
        Properties properties = new DataBaseManager().getPropertiesFile();
        
        if (properties != null) {
            String direction = properties.getProperty("direction");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            newConnection = DriverManager.getConnection(direction, user, password);
        } else {
            throw new SQLDataException("No fue posible encotrar las credenciales de la base de datos");
        }
        return newConnection;
    }
    
    private Properties getPropertiesFile() {
        Properties properties = null;
        
        try (FileInputStream configurationFile = new FileInputStream(new File("src\\mx\\uv\\fei\\dataaccess\\dbconfig.txt"))) {
            if (configurationFile != null) {
               properties = new Properties();
               properties.load(configurationFile);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException iOException){
            System.out.println(iOException.getMessage());
        }
        return properties;
    }
    
    public static boolean closeConnection() {
        boolean isClosed = false;
        
        try {
            if (connection != null) {
                connection.close();
            }
            isClosed = true;
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return isClosed;
    }
    
    public static boolean rollback() throws SQLException {
        boolean isReversed = false;
        
        try {
            if (connection != null) {
                connection.rollback();
            }
            isReversed = true;
        } catch (SQLException sQLException) {
            throw sQLException;
        }
        return isReversed;
    }
}
