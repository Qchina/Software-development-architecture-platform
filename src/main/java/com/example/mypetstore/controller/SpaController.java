package com.example.mypetstore.controller;

import com.example.mypetstore.domain.OrderManage;
import com.example.mypetstore.persistence.OrderManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class SpaController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirectToIndex() {
        return "forward:/index.html";
    }
}