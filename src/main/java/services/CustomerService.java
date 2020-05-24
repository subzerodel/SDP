package services;

import domain.Customer;
import repositories.entities.CustomerRepository;
import repositories.interfaces.ICustomerRepository;

public class CustomerService {
    private ICustomerRepository csRepo;

    public CustomerService() {
        csRepo = new CustomerRepository();
    }

    public Customer getCustomer(long id){
        return csRepo.getCustomerByID(id);
    }
    public void addUser(String fname,String lname,String email,String password){
        Customer customer=new Customer(fname,lname,email,password);
        csRepo.add(customer);
    }
}
