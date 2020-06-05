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
    public void addProduct(Shoes entity) {
        try {
            Statement stmt = db.getConnection().createStatement();
            String sql = "INSERT INTO shoes(categorie_id,shoes_name,description,price)" +
                    "VALUES(" + entity.getCategory_id() + ",'" + entity.getShoes_name() + "','" + entity.getDescription() + "'," + entity.getPrice() + ")";
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        return queryOne(sql);
    }

    @Override
    public List<Shoes> queryOne(String sql) {
        try {
            Statement stmt = db.getConnection().createStatement();
            LinkedList<Shoes> shoes = new LinkedList<>();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Shoes shoe = new Shoes(
                        rs.getLong("category_id"),
                        rs.getString("shoes_name"),
                        rs.getString("description"),
                        rs.getInt("price")
                );
                shoes.add(shoe);
            }
            return shoes;
        } catch (SQLException ex) {
            System.out.println("mwakDMKW");
        }
        return null;
    }

    //    @Override
//    public List getProduct() {
//        return null;
//    }
}
