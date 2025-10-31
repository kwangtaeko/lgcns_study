package com.example.myproject.controller;

import com.example.myproject.dto.AboutForm;
import com.example.myproject.entity.About;
import com.example.myproject.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AboutController {
    @Autowired
    AboutService aboutService;

    @GetMapping("/about")
    public String getAbout(Model model) {
        About aboutEntity = aboutService.getAbout();
        if (aboutEntity != null) model.addAttribute("about", aboutEntity);
        else model.addAttribute("noAboutMessage", "등록된 회사정보가 없습니다.");

        return "stock/about";
    }

    @PostMapping("/about/save")
    public String saveAbout(AboutForm form, RedirectAttributes rttr) {
        String result = aboutService.saveAbout(form);
        rttr.addFlashAttribute("msg", result);
        return "redirect:/about";
    }
}
