package repositories.interfaces;

import domain.models.Category;
import domain.models.Shoes;

import java.util.List;

public interface IShoesRepository<T> {

    List<Category> getCategories();

    List<Shoes> findCategoryByID(long id);

    List<Shoes> query(String sql);
}
