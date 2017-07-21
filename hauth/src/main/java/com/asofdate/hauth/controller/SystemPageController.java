package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.hauth.service.HomeMenuService;
import com.asofdate.hauth.sql.SqlText;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/17.
 */
@Controller
@Api("系统首页管理")
public class SystemPageController {
    private final static Logger logger = LoggerFactory.getLogger(SystemPageController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HomeMenuService homeMenuService;
    @Autowired
    private SqlText sqlText;

    @RequestMapping(value = "/HomePage", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, Map<String, Object> map) {

        String username = JwtService.getConnUser(request).getUserId();
        logger.debug("username is :" + username);

        String url = homeMenuService.getHomeUrl(username);
        url = url.replaceFirst("^./views/", "").replaceFirst(".tpl$", "");
        logger.debug("url is :" + url);
        map.put("userId", username);
        return url;
    }

    @RequestMapping(value = "/v1/auth/index/entry", method = RequestMethod.GET)
    public String subSystemPage(HttpServletRequest request) {

        String username = JwtService.getConnUser(request).getUserId();
        logger.debug("username is:" + username);

        String resId = request.getParameter("Id");
        logger.debug("resource id is :" + resId);

        String url = homeMenuService.getSubSystemUrl(username, resId);
        url = url.replaceFirst("^./views/", "").replaceFirst(".tpl$", "").replaceFirst("^./apps/", "");
        logger.debug("url is :" + url);
        return url;
    }
}
