package repositories.entities;

import domain.models.Category;
import domain.models.Shoes;
import repositories.db.PostgresRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IShoesRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ShoesRepository implements IShoesRepository<Shoes> {
    IDBRepository db;

    public ShoesRepository() {
        db = new PostgresRepository();
    }

    @Override
    public List<Category> getCategories() {
        try {
            Statement st = db.getConnection().createStatement();
            LinkedList<Category> categories = new LinkedList<>();
            ResultSet rs = st.executeQuery("select * from categories");
            while (rs.next()) {
                Category category = new Category(
                        rs.getLong("id"),
                        rs.getString("category_name")
                );
                categories.add(category);
            }
            return categories;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Shoes> findCategoryByID(long id) {
        String sql="Select * from shoes where category_id="+id;
        return query(sql);
    }

    @Override
    public List<Shoes> query(String sql) {
        try{
            Statement stmt=db.getConnection().createStatement();
            LinkedList<Shoes> shoes=new LinkedList<>();
            ResultSet res=stmt.executeQuery(sql);
            while (res.next()){
                Shoes shoe= new Shoes(
                        res.getLong("category_id"),
                        res.getString("shoes_name"),
                        res.getString("description"),
                        res.getInt("price")
                );
                shoes.add(shoe);
            }
            return  shoes;
        } catch (SQLException throwables) {
            System.out.println("Erroe");
        }
        return null;
    }

    //    @Override
//    public List getProduct() {
//        return null;
//    }
}
