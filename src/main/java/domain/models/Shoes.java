package domain.models;

public class Shoes {
    private long category_id;
    private String shoes_name;
    private String description;
    private int price;

    public Shoes(long categorie_id, String shoes_name, String description, int price) {
        this.category_id = categorie_id;
        this.shoes_name = shoes_name;
        this.description = description;
        this.price = price;
    }
    public Shoes(){

    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getShoes_name() {
        return shoes_name;
    }

    public void setShoes_name(String shoes_name) {
        this.shoes_name = shoes_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
