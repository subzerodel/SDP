package services;

import domain.AccessToken;
import domain.CustomerLoginData;
import domain.models.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import repositories.entities.CustomerRepository;
import repositories.interfaces.ICustomerRepository;
import services.interfaces.IAuthorizationService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class AuthorizationService implements IAuthorizationService {
    private final ICustomerRepository cs=new CustomerRepository();

    private Customer signIn(CustomerLoginData data) throws Exception {
    Customer customer=cs.findCustomerByID(data);
    if(customer==null){
        throw new Exception("Authentication failed");
    }
    return customer;
    }

    public Customer getCustomerByEmail(String email){
        return cs.getCustomerByEmail(email);
    }

    private String issueToken(Customer customer){
        Instant now=Instant.now();
        String secretWord="TheRealSlimShadyTheGreatestInLife";
        return Jwts.builder()
                .setIssuer(customer.getEmail())
                .setIssuedAt(Date.from(now))
                .claim("1d20", new Random().nextInt(20) + 1)
                .setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .compact();
    }

    @Override
    public AccessToken authenticateCustomer(CustomerLoginData data) throws Exception {
        Customer authenticatedCustomer=signIn(data);
        return new AccessToken(issueToken(authenticatedCustomer));
    }
}
