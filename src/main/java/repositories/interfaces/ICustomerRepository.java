package repositories.interfaces;

import domain.Customer;

public interface ICustomerRepository<T> {
    void add(T entity);
    void remove(T entity);
    void update(T entity);
     Iterable<T> query(String sql);
    Customer getCustomerByID(long id);
}
