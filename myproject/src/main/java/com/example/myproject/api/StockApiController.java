package com.example.myproject.api;

import com.example.myproject.dto.StockForm;
import com.example.myproject.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockApiController {
    @Autowired
    StockService stockService;

    @PostMapping("/api/stock/save")
    public ResponseEntity<String> stockSave(@RequestBody StockForm dto) {
        String result = stockService.saveStock(dto);
        return result.equals("OK")
                ? ResponseEntity.status(HttpStatus.OK).body("저장이 완료되었습니다.")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 중 오류가 발생하였습니다.");
    }

    @PatchMapping("/api/stock/edit/{id}")
    public ResponseEntity<String> stockEdit(@PathVariable Long id, @RequestBody StockForm dto) {
        String result = stockService.editStock(id, dto);
        return result.equals("OK")
                ? ResponseEntity.status(HttpStatus.OK).body("수정이 완료되었습니다.")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 중 오류가 발생하였습니다.");
    }
}
