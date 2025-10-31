package com.example.myproject.service;

import com.example.myproject.dto.AboutForm;
import com.example.myproject.entity.About;
import com.example.myproject.repository.AboutRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutService {
    @Autowired
    AboutRepository aboutRepository;

    public About getAbout() {
        List<About> aboutList = (List<About>) aboutRepository.findAll();
        if (aboutList.isEmpty()) {
            return null;
        }
        return aboutList.get(0);
    }

    @Transactional
    public String saveAbout(AboutForm dto) {
        About aboutEntity = dto.toEntity();

        aboutRepository.deleteAll();

        About result = aboutRepository.save(aboutEntity);
        if (result.getId() == null) return "회사정보 저장 오류";
        else return "회사정보 저장 완료";
    }
}
