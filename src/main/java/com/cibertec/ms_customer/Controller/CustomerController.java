package com.cibertec.ms_customer.Controller;

import com.cibertec.ms_customer.Model.Customer;
import com.cibertec.ms_customer.Repository.CustomerRepository;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CustomerController {
   @Autowired
   private CustomerRepository customerRepository;

   @GetMapping(path = "/customer")
   public ResponseEntity<Object> retriveAll() {
      try {
         List<Customer> customers = customerRepository.findAll();

         if(customers == null){
            return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok().body(customers);
      } catch (Exception e) {
         return ResponseEntity.badRequest().build();
      }
   }

   @GetMapping(path = "/customer/{id}")
   public ResponseEntity<Object> retriveOne(@PathVariable Integer id) {
      try {
         Optional<Customer> customer = customerRepository.findById(id);

         if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok().body(customer);
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().build();
      }

   }

   @PostMapping(path = "/customer")
   public ResponseEntity<Object> create(@RequestBody Customer custog) {

      try {
         Customer entity = customerRepository.save(custog);

         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
           .buildAndExpand(entity.getNum_customer_id()).toUri();

         return ResponseEntity.ok().body(entity);

      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().build();
      }
   }

   @PutMapping(path = "/customer/{id}")
   public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Customer custog) {
      try {
         Optional<Customer> entity = customerRepository.findById(id);

         if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
         }

         custog.setNum_customer_id(id);
         Customer cust = customerRepository.save(custog);
         return ResponseEntity.ok().body(cust);
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().build();
      }
   }

   @DeleteMapping(path = "/customer/{id}")
   public ResponseEntity<Object> delete(@PathVariable Integer id) {

      try {
         Optional<Customer> entity = customerRepository.findById(id);
         if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
         }
         customerRepository.deleteById(id);
         return ResponseEntity.ok().build();
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().build();
      }
   }
}
