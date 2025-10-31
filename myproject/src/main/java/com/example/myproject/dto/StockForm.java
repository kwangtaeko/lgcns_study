package com.example.myproject.dto;

import com.example.myproject.entity.Stock;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockForm {
    private Long id;
    private String code;
    private String name;
    private Integer price;
    private Double rate;
    private Integer volume;
    private Integer market_cap;
    private String sector;

    public Stock toEntity() {
        Stock stock = new Stock();
        stock.setId(id);
        stock.setCode(code);
        stock.setName(name);
        stock.setPrice(price);
        stock.setVolume(volume);
        stock.setMarket_cap(market_cap);
        stock.setSector(sector);
        stock.setRate(rate);
        return stock;
    }
}
