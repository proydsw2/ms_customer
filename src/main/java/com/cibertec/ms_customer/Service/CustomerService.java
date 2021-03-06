package com.cibertec.ms_customer.Service;

import com.cibertec.ms_customer.Model.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

   ResponseEntity<Object> getAll();

   ResponseEntity<Object> getOne(Integer id);

   ResponseEntity<Object> insert(Customer customer);

   ResponseEntity<Object> update(Integer id, Customer customer);

   ResponseEntity<Object> delete(Integer id);


}
