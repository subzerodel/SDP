package repositories.entities;

import domain.CustomerLoginData;
import domain.models.Customer;
import repositories.db.PostgresRepository;
import repositories.interfaces.ICustomerRepository;
import repositories.interfaces.IDBRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class CustomerRepository implements ICustomerRepository<Customer> {
    private IDBRepository db;

    public CustomerRepository() {
        db = new PostgresRepository();
    }

    @Override
    public void add(Customer entity) {
        try {
            Statement stmt = db.getConnection().createStatement();
            String sql = "INSERT INTO customers(fname,lname,email,password)" +
                    "VALUES('" + entity.getFname() + "','" + entity.getLname() + "','" + entity.getEmail() + "','" + entity.getPassword() + "')";
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Qiyn joly!!! To do this thing you need to extend from Application

   /* @Override
    public void add(Customer entity) {
        try {
            String sql = "INSERT INTO customers(fname,lname,email,password)" +
                    "VALUES(?,?,?,?)";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getFname());
            stmt.setString(2, entity.getLname());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, entity.getPassword());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    } //Onai joly!!! You need to extend from ResourceConfig*/


    @Override
    public void remove(Customer entity) {

    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public Iterable<Customer> query(String sql) {
        try {
            Statement stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            LinkedList<Customer> customers = new LinkedList<>();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                customers.add(customer);
            }
            return customers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByID(long id) {
        return null;
    }

    @Override
    public Customer findCustomerByID(CustomerLoginData data) {
        try {
            String sql = "SELECT * FROM customers WHERE email=? AND password=?";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, data.getEmail());
            stmt.setString(2, data.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("password"));
            }

        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }
}
