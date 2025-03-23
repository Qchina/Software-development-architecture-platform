package org.csu.petstore.controller;


import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Category;
import org.csu.petstore.entity.Order;
import org.csu.petstore.service.AccountService;
import org.csu.petstore.service.CatalogService;
import org.csu.petstore.service.impl.CatalogServiceImpl;
import org.csu.petstore.vo.AccountVO;
import org.csu.petstore.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/account")
@Validated
@SessionAttributes
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CatalogService catalogService;//这里的问题!

    @Autowired
    HttpSession session;

    @GetMapping("/signOnForm")
    public String signOn() {
        return "account/signOn";
    }

    @GetMapping("/newAccountForm")
    public String newAccountForm(Model model) {
        model.addAttribute("account", new AccountVO());
        return "/account/newAccount";
    }

    @GetMapping("/editForm")
    public String editAccount(Model model) {
        AccountVO account = (AccountVO) session.getAttribute("account");
        model.addAttribute("userinfo", account);
        return "/account/editAccount";
    }

    @PostMapping("/signOn")
    public String signOnSubmit(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("code") String code, Model model) {
        String captcha = (String) session.getAttribute("captcha");
        if (captcha != null && captcha.equalsIgnoreCase(code)) {
            int result = accountService.signonForm(username, password);
            if (result == 0) {
                LocalDate localDate = LocalDate.now();
                AccountVO accountVO = accountService.getAccount(username);
                model.addAttribute("account", accountVO);
                session.setAttribute("account", accountVO);
                CategoryVO categoryVO = catalogService.getCategory(accountVO.getFavoriteGory());

                model.addAttribute("myList", categoryVO.getProductList());
                session.setAttribute("myList", categoryVO.getProductList());

                Order order = new Order();
                order.setBillToFirstName(accountVO.getFirstName());
                order.setBillToLastName(accountVO.getLastName());
                order.setBillAddress1(accountVO.getAddress1());
                order.setBillAddress2(accountVO.getAddress2());
                order.setBillCity(accountVO.getCity());
                order.setBillState(accountVO.getState());
                order.setBillZip(accountVO.getZip());
                order.setBillCountry(accountVO.getCountry());
                order.setShipToFirstName(accountVO.getFirstName());
                order.setShipToLastName(accountVO.getLastName());
                order.setShipAddress1(accountVO.getAddress1());
                order.setShipAddress2(accountVO.getAddress2());
                order.setShipCity(accountVO.getCity());
                order.setShipState(accountVO.getState());
                order.setShipZip(accountVO.getZip());
                order.setShipCountry(accountVO.getCountry());
                order.setOrderDate(localDate);
                order.setCourier(captcha);
                order.setLocale("o");

                model.addAttribute("order", order);
                session.setAttribute("order", order);

                return "catalog/main";
            } else if (result == 2) {
                model.addAttribute("loginMsg", "密码错误");
                return "account/signOn";
            } else {
                model.addAttribute("loginMsg", "用户名不存在");
                return "account/signOn";
            }
        } else {
            model.addAttribute("loginMsg", "验证码错误");
            return "account/signOn";
        }
    }

    @GetMapping("/signOut")
    public String signOut() {
        session.invalidate();
        return "catalog/main";
    }

    @PostMapping("/newAccount")
    public String newAccount(@ModelAttribute("account") AccountVO account, @RequestParam("code") String code, Model model) {
        String captcha = (String) session.getAttribute("captcha");
        if (captcha != null && captcha.equalsIgnoreCase(code)) {
            model.addAttribute("account", account);
            System.out.println(account);
            session.setAttribute("account", account);
            accountService.insertAccountInformation(account);
            return "catalog/main";
        } else {
            model.addAttribute("loginMsg", "验证码错误");
            return "account/newAccount";
        }
    }

    @PostMapping("/edit")
    public String updateAccount(@ModelAttribute AccountVO account, @RequestParam("code") String code, Model model) {
        String captcha = (String) session.getAttribute("captcha");
        if (captcha != null && captcha.equalsIgnoreCase(code)) {
            String username = ((AccountVO) session.getAttribute("account")).getUsername();
            String password = ((AccountVO) session.getAttribute("account")).getPassword();
            account.setUsername(username);
            if (account.getPassword() == "") {
                account.setPassword(password);
            }
            accountService.updateAccountInformation(account);
            session.setAttribute("account", account);
            return "catalog/main";
        } else {
            model.addAttribute("loginMsg", "验证码错误");
            AccountVO accountRecover = (AccountVO) session.getAttribute("account");
            model.addAttribute("userinfo", accountRecover);
            return "account/editAccount";
        }
    }
}