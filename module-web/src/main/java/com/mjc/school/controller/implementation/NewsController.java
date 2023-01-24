package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
public class NewsController implements BaseController <NewsDtoRequest, NewsDtoResponse, Long> {
    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
    }

    @Override
    public List readAll() {
        return newsService.readAll();
    }

    @Override
    public Object readById(Long id) {
        return null;
    }

    @Override
    public Object create(NewsDtoRequest createRequest) {
        return null;
    }

    @Override
    public Object update(NewsDtoRequest updateRequest) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}