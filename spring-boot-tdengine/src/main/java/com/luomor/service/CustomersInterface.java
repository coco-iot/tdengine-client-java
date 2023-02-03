package com.luomor.service;

import com.luomor.model.Customer;

import java.util.List;

public interface CustomersInterface {

    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);


}
