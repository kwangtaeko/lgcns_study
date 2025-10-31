package com.example.myproject.service;

import com.example.myproject.dto.StockForm;
import com.example.myproject.entity.Stock;
import com.example.myproject.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;

    public List<Stock> getStockList() {
        return (List<Stock>) stockRepository.findAll();
    }

    public Stock getStockDetail(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Transactional
    public String saveStock(StockForm dto) {
        Stock stockEntity = dto.toEntity();

        if (stockEntity.getCode() == null) {
            throw new RuntimeException("코드 null값 오류 발생");
        }

        Stock result = stockRepository.save(stockEntity);
        if (result.getId() == null) return "ERROR";
        else return "OK";
    }

    @Transactional
    public String editStock(Long id, StockForm dto) {
        Stock stockEntity = dto.toEntity();

        Stock target = stockRepository.findById(id).orElse(null);
        if (target == null || !id.equals(stockEntity.getId())) {
            return "ERROR";
        }

        target.patch(stockEntity);

        Stock result = stockRepository.save(target);
        if (result.getId() == null) return "ERROR";
        else return "OK";
    }

    @Transactional
    public String deleteStock(Long id) {
        Stock target = stockRepository.findById(id).orElse(null);
        if (target == null) {
            return "삭제 중 오류가 발생하였습니다.";
        }

        stockRepository.deleteById(id);
        return "삭제가 완료되었습니다.";
    }
}
