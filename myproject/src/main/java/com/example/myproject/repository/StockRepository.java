package com.example.myproject.repository;

import com.example.myproject.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long>  {
}
