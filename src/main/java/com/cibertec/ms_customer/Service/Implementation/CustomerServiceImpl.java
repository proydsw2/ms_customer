package com.cibertec.ms_customer.Service.Implementation;

import com.cibertec.ms_customer.Model.Customer;
import com.cibertec.ms_customer.Repository.CustomerRepository;
import com.cibertec.ms_customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

   @PersistenceContext
   private EntityManager em;

   @Autowired
   CustomerRepository customerRepository;

   public ResponseEntity<Object> getAll() {
      try {
         List<Customer> customers = customerRepository.findAll();

         if (customers == null) {
            return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok().body(customers);
      } catch (Exception e) {
         return ResponseEntity.badRequest().build();
      }
   }

   public ResponseEntity<Object> getOne(Integer id) {
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

   public ResponseEntity<Object> insert(Customer customer) {
      try {
         Customer entity = customerRepository.save(customer);

         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
           .buildAndExpand(entity.getNum_customer_id()).toUri();

         return ResponseEntity.ok().body(entity);

      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().build();
      }
   }

   public ResponseEntity<Object> update(Integer id, Customer customer) {
      try {
         Optional<Customer> entity = customerRepository.findById(id);

         if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
         }

         customer.setNum_customer_id(id);
         Customer cust = customerRepository.save(customer);
         return ResponseEntity.ok().body(cust);
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().build();
      }
   }

   public ResponseEntity<Object> delete(Integer id) {

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
