package repositories.entities;

import domain.models.Sales_history;
import repositories.db.PostgresRepository;
import repositories.interfaces.IBuyRepository;
import repositories.interfaces.IDBRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuyRepository implements IBuyRepository  {
    IDBRepository db= new PostgresRepository();

    @Override
    public void salesHistory(Sales_history sh) {
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
    }

}
