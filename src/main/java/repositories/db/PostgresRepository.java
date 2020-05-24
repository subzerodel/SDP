package repositories.db;

import repositories.interfaces.IDBRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresRepository implements IDBRepository {
    @Override
    public Connection getConnection() {
        try {
            String getConn = "jdbc:postgresql://localhost:5432/Midka";
            Connection conn = DriverManager.getConnection(getConn, "postgres", "040916");
            return conn;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
