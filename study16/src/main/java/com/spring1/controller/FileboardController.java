package com.spring1.controller;

import com.spring1.domain.Fileboard;
import com.spring1.service.FileboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileboardController {

    @Autowired
    private FileboardService fileboardService;

    @GetMapping("/fileboard/list")
    public String getList(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        List<Fileboard> list = fileboardService.getList(page, pageSize);
        int totalCount = fileboardService.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "fileboard/list";
    }

    @GetMapping("/fileboard/detail/{no}")
    public String getDetail(@PathVariable int no, Model model) {
        Fileboard fileboard = fileboardService.getDetail(no);
        model.addAttribute("fileboard", fileboard);
        return "fileboard/detail";
    }

    @GetMapping("/fileboard/post")
    public String postForm() {
        return "fileboard/post";
    }

    @PostMapping("/fileboard/postPro")
    public String postPro(@RequestParam("file") MultipartFile file, Fileboard fileboard) {
        fileboardService.saveFileboard(fileboard, file);
        return "redirect:/fileboard/list";
    }

    @GetMapping("/fileboard/modify/{no}")
    public String modifyForm(@PathVariable int no, Model model) {
        Fileboard fileboard = fileboardService.getDetail(no);
        model.addAttribute("fileboard", fileboard);
        return "fileboard/modify";
    }

    @PostMapping("/fileboard/modifyPro")
    public String modifyPro(@RequestParam("file") MultipartFile file, Fileboard fileboard) {
        fileboardService.updateFileboard(fileboard, file);
        return "redirect:/fileboard/list";
    }

    @PostMapping("/fileboard/deletePro/{no}")
    public String deletePro(@PathVariable int no) {
        fileboardService.deleteFileboard(no);
        return "redirect:/fileboard/list";
    }
}