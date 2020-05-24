package repositories.entities;

import domain.Customer;
import repositories.db.PostgresRepository;
import repositories.interfaces.ICustomerRepository;
import repositories.interfaces.IDBRepository;

import javax.swing.plaf.nimbus.State;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class CustomerRepository implements ICustomerRepository<Customer>{
    private IDBRepository db;
    public CustomerRepository(){
        db=new PostgresRepository();
    }

    @Override
    public void add(Customer entity) {
        try{
            Statement stmt=db.getConnection().createStatement();
            String sql="INSERT INTO customers(fname,lname,email,password)" +
                    "VALUES('"+entity.getFname()+"','"+entity.getLname()+"','"+entity.getEmail()+"','"+entity.getPassword()+"')";
            stmt.execute(sql);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remove(Customer entity) {

    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public Iterable<Customer> query(String sql) {
        try{
            Statement stmt=db.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            LinkedList<Customer> customers=new LinkedList<>();
            while(rs.next()){
                Customer customer=new Customer(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                customers.add(customer);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByID(long id) {
        return null;
    }
}
