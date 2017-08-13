package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.Hret;
import io.swagger.annotations.Api;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzwy23 on 2017/5/16.
 */
@Controller
@Api("安全退出管理")
public class IdentifyController {
    private final Logger logger = LoggerFactory.getLogger(IdentifyController.class);
    private final String HEADER_STRING = "Authorization";

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("Authorization", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return Hret.success(200, "success", null);
    }


    @RequestMapping(value = "/v1/batch/identify")
    public String identify(HttpServletResponse response, HttpServletRequest request){
        String token = request.getParameter("token");
        if (token == null || token.isEmpty()){
            token = request.getHeader(HEADER_STRING);
            if (token == null || token.isEmpty()) {
                token = JwtService.getTokenFromCookis(request);
                if (token == null || token.isEmpty()) {
                    try {
                        response.getOutputStream().println("token is invalid");
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                    return "";
                }
            }
        }

        boolean flag = JwtService.identify(token);
        if (flag){
            logger.info("token验证通过，客户端地址：{}", request.getRemoteAddr());
            //response.sendRedirect("/HomePage");
            response.setHeader(HEADER_STRING, token);
            response.addCookie(new Cookie(HEADER_STRING, token));
            return "redirect";

        } else {
            logger.info("token无效");
            try {
                response.getOutputStream().println("token is invalid");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            return "";
        }
    }
}
