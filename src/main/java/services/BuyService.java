package services;

import domain.models.Sales_history;
import domain.models.ShoppingCard;
import repositories.entities.BuyRepository;
import repositories.interfaces.IBuyRepository;

import java.sql.Date;

public class BuyService {
    private IBuyRepository buyRepository;
    public BuyService(){
        buyRepository= new BuyRepository();
    }
    public void buyingShoes(ShoppingCard sc){
        buyRepository.buyingShoes(sc);
    }
    /*public void buy(long id,long shoes_id, String date){
        Sales_history sh= new Sales_history(id, shoes_id, Date.valueOf(date));
        buyRepository.buy(sh);
    }*/

}
