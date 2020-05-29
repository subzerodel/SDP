package services;

import domain.models.Category;
import domain.models.Shoes;
import repositories.entities.ShoesRepository;
import repositories.interfaces.IShoesRepository;

import java.util.List;

public class ShoesService {
    IShoesRepository ish;

    public ShoesService() {
        ish = new ShoesRepository();
    }
    public void addShoesID(long category_id,String shoes_name, String description, int price){
        Shoes shoes=new Shoes(category_id,shoes_name,description,price);
        ish.addProduct(shoes);
    }

    public List<Category> categories(){
        List<Category> categories= ish.getCategories();
        return categories;
    }
    public List<Shoes> getCategory(long id){
        List<Shoes>shoes=ish.findCategoryByID(id);
        return shoes;
    }

}
