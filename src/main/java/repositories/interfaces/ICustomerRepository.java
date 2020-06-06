package repositories.interfaces;

import domain.CustomerLoginData;
import domain.models.Customer;
import domain.models.Sales_history;

public interface ICustomerRepository<T> {
    void add(T entity);
     Iterable<T> query(String sql);
    Customer getCustomerByID(long id);
    Customer findCustomerByID(CustomerLoginData data);
    Customer getCustomerByUsername(String email);
    Customer queryOne(String sql);
}
