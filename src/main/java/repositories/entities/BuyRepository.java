package repositories.entities;

import domain.models.Sales_history;
import domain.models.Shoes;
import domain.models.ShoppingCard;
import repositories.db.PostgresRepository;
import repositories.interfaces.IBuyRepository;
import repositories.interfaces.IDBRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BuyRepository implements IBuyRepository  {
    IDBRepository db= new PostgresRepository();

    @Override
    public Sales_history salesHistory(Sales_history sh) {
        try{
            String sql="INSERT INTO sales_history(shoes_id,purchase_date) " +
                    "VALUES(?,?)";
            PreparedStatement stmt=db.getConnection().prepareStatement(sql);
            stmt.setLong(1,sh.getShoes_id());
            stmt.setDate(2,sh.getDate());
            stmt.execute();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return null;
    }

    @Override
    public void buyingShoes(ShoppingCard sc) {
        Shoes shoes=new Shoes();
        if(sc.getBalance()>=shoes.getPrice()){
            long value=sc.getBalance()-shoes.getPrice();
            String sql="Update shoppingcart" +
                    "SET balance="+value+"where customer_id="+sc.getCustomer_id();
            try{
            Statement stmt=db.getConnection().createStatement();
            stmt.execute(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("You don't have enough money");
        }
    }

}
