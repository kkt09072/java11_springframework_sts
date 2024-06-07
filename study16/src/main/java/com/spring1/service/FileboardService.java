package com.spring1.service;

import com.spring1.domain.Fileboard;
import com.spring1.mapper.FileboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileboardService {

    @Autowired
    private FileboardMapper fileboardMapper;

    public List<Fileboard> getList(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return fileboardMapper.getList(pageSize, offset);
    }

    public int getTotalCount() {
        return fileboardMapper.getTotalCount();
    }

    public Fileboard getDetail(int no) {
        return fileboardMapper.getDetail(no);
    }

    public void saveFileboard(Fileboard fileboard, MultipartFile file) {
        if (!file.isEmpty()) {
            String filename = saveFile(file);
            fileboard.setFilename(filename);
        }
        fileboardMapper.insertFileboard(fileboard);
    }

    public void updateFileboard(Fileboard fileboard, MultipartFile file) {
        if (!file.isEmpty()) {
            String filename = saveFile(file);
            fileboard.setFilename(filename);
        }
        fileboardMapper.updateFileboard(fileboard);
    }

    public void deleteFileboard(int no) {
        fileboardMapper.deleteFileboard(no);
    }

    private String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + "_" + originalFilename;

        try {
            file.transferTo(new File("path/to/your/upload/directory/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }
}