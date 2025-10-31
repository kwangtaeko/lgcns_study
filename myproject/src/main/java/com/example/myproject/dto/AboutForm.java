package com.example.myproject.dto;

import com.example.myproject.entity.About;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AboutForm {
    private Long id;
    private String title;
    private String content;

    public About toEntity() {
        return new About(id, title, content);
    }
}
