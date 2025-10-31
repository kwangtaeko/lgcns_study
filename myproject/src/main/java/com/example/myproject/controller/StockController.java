package com.example.myproject.controller;

import com.example.myproject.entity.Stock;
import com.example.myproject.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/stock/getStock")
    public String getStock(Model model) {
        List<Stock> stockList = (List<Stock>) stockService.getStockList();
        model.addAttribute("stockList", stockList);
        return "stock/main";
    }

    @GetMapping("/stock/getStock/{id}")
    public String getStockDetail(@PathVariable Long id, Model model) {
        Stock stockDetail = (Stock) stockService.getStockDetail(id);
        stockDetail.setIsRise(stockDetail.getRate() >= 0);
        model.addAttribute("stockDetail", stockDetail);
        return "stock/detail";
    }

    @GetMapping("/stock/deleteStock/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        String result = stockService.deleteStock(id);
        rttr.addFlashAttribute("msg", result);

        return "redirect:/stock/getStock";
    }

    @GetMapping("/help")
    public String getHelp() {
        return "stock/help";
    }

    @GetMapping("/report")
    public String getReport(Model model) throws Exception {
        List<Stock> stockList = stockService.getStockList();
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("stockListJson", mapper.writeValueAsString(stockList));
        return "stock/report";
    }
}
