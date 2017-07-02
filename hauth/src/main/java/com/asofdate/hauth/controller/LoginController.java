package com.asofdate.hauth.controller;

import com.asofdate.utils.Hret;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hzwy23 on 2017/5/16.
 */
@Controller
public class LoginController {

    private final String HEADER_STRING = "Authorization";

    @RequestMapping(value = "/signout", produces = "application/json")
    @ResponseBody
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("Authorization", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return Hret.success(200, "success", null);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
