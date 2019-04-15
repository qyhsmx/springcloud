package com.qyy.search.repository;

import com.qyy.search.entity.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer,String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);


}
