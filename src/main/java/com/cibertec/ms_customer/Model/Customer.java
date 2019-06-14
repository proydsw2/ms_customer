package com.cibertec.ms_customer.Model;

import lombok.*;
import javax.persistence.*;

@SequenceGenerator(name="seq_customer_id", initialValue=1, allocationSize=1)
@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer_id")
    @Column(name = "num_customer_id")
    private Integer num_customer_id;

    @Column(name = "num_document_number")
    private String num_document_number;

    @Column(name = "str_customer_name")
    private String str_customer_name ;

    @Column(name = "str_customer_lastname")
    private String str_customer_lastname ;

    @Column(name = "str_email")
    private String str_email;

    @Column(name = "num_phone")
    private String num_phone;

}
