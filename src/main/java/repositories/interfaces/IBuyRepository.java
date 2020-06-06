package repositories.interfaces;

import domain.models.Sales_history;
import domain.models.ShoppingCard;

public interface IBuyRepository {
    Sales_history salesHistory(Sales_history sh);
    void buyingShoes(ShoppingCard sc);
}
