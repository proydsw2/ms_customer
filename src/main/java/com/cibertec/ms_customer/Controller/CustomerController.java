package com.cibertec.ms_customer.Controller;

import com.cibertec.ms_customer.Model.Customer;
import com.cibertec.ms_customer.Repository.CustomerRepository;
import com.cibertec.ms_customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

   @Autowired
   private CustomerRepository customerRepository;

   @Autowired
   private CustomerService customerService;

   @GetMapping(path = "/customer")
   public ResponseEntity<Object> retriveAll() {
      return customerService.getAll();
   }

   @GetMapping(path = "/customer/{id}")
   public ResponseEntity<Object> retriveOne(@PathVariable Integer id) {
      return customerService.getOne(id);
   }

   @PostMapping(path = "/customer")
   public ResponseEntity<Object> create(@RequestBody Customer customer) {
      return customerService.insert(customer);
   }

   @PutMapping(path = "/customer/{id}")
   public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Customer customer) {
      return customerService.update(id, customer);
   }

   @DeleteMapping(path = "/customer/{id}")
   public ResponseEntity<Object> delete(@PathVariable Integer id) {
      return customerService.delete(id);
   }
}
