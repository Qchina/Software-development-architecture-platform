package com.example.mypetstore.controller;

import com.example.mypetstore.domain.AccountManage;
import com.example.mypetstore.persistence.AccountManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
//充当get和post
@RequestMapping("/userManage")
public class UserManageController {
    @Autowired
    public AccountManageMapper accountMapper;

    @GetMapping("/accountManage")
    public String accountManage(Model model) {
        List<AccountManage> accounts= accountMapper.getAccountManageList();
        model.addAttribute("accounts", accounts);
        return "accountManage";
    }

    @PostMapping("/addAccountManage")
    public String addAccountManage(@RequestParam("userid") String userid,
                                   @RequestParam("email") String email,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("status") String status,
                                   @RequestParam("address1") String address1,
                                   @RequestParam("address2") String address2,
                                   @RequestParam("city") String city,
                                   @RequestParam("state") String state,
                                   @RequestParam("zip") String zip,
                                   @RequestParam("country") String country,
                                   @RequestParam("phone") String phone){
        AccountManage accountManage = new AccountManage();
        accountManage.setUserid(userid);
        accountManage.setEmail(email);
        accountManage.setFirstName(firstName);
        accountManage.setLastName(lastName);
        accountManage.setStatus(status);
        accountManage.setAddress1(address1);
        accountManage.setAddress2(address2);
        accountManage.setCity(city);
        accountManage.setState(state);
        accountManage.setZip(zip);
        accountManage.setCountry(country);
        accountManage.setPhone(phone);
        // 调用 Mapper 插入数据到数据库
        accountMapper.addAccountManage(accountManage);
        // 重定向到商品管理页面
        return "redirect:/userManage/accountManage";
    }

    @PostMapping("/editAccountManage")
    public String editAccountManage(@RequestParam("userid") String userid,
                                    @RequestParam("email") String email,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("status") String status,
                                    @RequestParam("address1") String address1,
                                    @RequestParam("address2") String address2,
                                    @RequestParam("city") String city,
                                    @RequestParam("state") String state,
                                    @RequestParam("zip") String zip,
                                    @RequestParam("country") String country,
                                    @RequestParam("phone") String phone){
        AccountManage accountManage = new AccountManage();
        accountManage.setUserid(userid);
        accountManage.setEmail(email);
        accountManage.setFirstName(firstName);
        accountManage.setLastName(lastName);
        accountManage.setStatus(status);
        accountManage.setAddress1(address1);
        accountManage.setAddress2(address2);
        accountManage.setCity(city);
        accountManage.setState(state);
        accountManage.setZip(zip);
        accountManage.setCountry(country);
        accountManage.setPhone(phone);
        // 调用 Mapper 插入数据到数据库
        accountMapper.updateAccountManage(accountManage);
        // 重定向到商品管理页面
        return "redirect:/userManage/accountManage";
    }

    @PostMapping("/deleteAccountManage")
    public String deleteAccountManage(@RequestParam("userid") String userid) {
        accountMapper.deleteAccountManage(userid);
        return "redirect:/userManage/accountManage"; // 重定向回分类管理页面
    }

    @GetMapping("/getAccountManageById")
    public String getAccountManageById(@RequestParam("userid") String userid,Model model)
    {
        List<AccountManage> accounts = accountMapper.getAccountManageById(userid);
        model.addAttribute("accounts", accounts);
        return "/accountManage";
    }
}
