package services;

import domain.models.Customer;
import repositories.entities.CustomerRepository;
import repositories.interfaces.ICustomerRepository;

public class CustomerService {
    private ICustomerRepository csRepo;
    private Customer cs=new Customer();

    public CustomerService() {
        csRepo = CustomerRepository.getInstance();
    }

    public void addCustomer(String fname,String lname,String email,String password){
        Customer customer=new Customer(fname,lname,email,password);
        csRepo.add(customer);
    }

    public Customer getCustomerByID(long id){
        return csRepo.getCustomerByID(id);
    }

}
