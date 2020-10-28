package services;

import domain.models.Customer;
import domain.models.Sales_history;
import repositories.entities.CustomerRepository;
import repositories.interfaces.ICustomerRepository;

public class CustomerService {
    private ICustomerRepository csRepo;
    private Customer cs=new Customer();

    public CustomerService() {
        csRepo = new CustomerRepository();
    }

    public Customer getCustomer(long id) {
        return csRepo.getCustomerByID(id);
    }

    public void addCustomer(String fname,String lname,String email,String password){
        Customer customer=new Customer(fname,lname,email,password);
        csRepo.add(customer);
    }
    //Qiyn joly You need to extend from Application Talgat is god

    /*
    public void addCustomer(Customer cs) {
        csRepo.add(cs);
    }*/
    //Onai joly!!! You need to extend from ResourceConfig

    public Customer getCustomerByID(long id){
        return csRepo.getCustomerByID(id);
    }
    public Customer getCustomerByUsername(String username){
        return csRepo.getCustomerByUsername(username);
    }

}
