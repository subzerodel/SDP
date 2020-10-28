package services;

import domain.models.Category;
import domain.models.Shoes;
import repositories.entities.ShoesRepository;
import repositories.interfaces.IShoesRepository;

import java.util.List;

public class ShoesService {
    private  final IShoesRepository ish=new ShoesRepository();

    public List<Category> categories(){
        List<Category> categories= ish.getCategories();
        return categories;
    }

    public List<Shoes> getCategory(long id) {
    List<Shoes> shoes=ish.findCategoryByID(id);
        return shoes;
    }
}
