package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.hauth.service.UserService;
import com.asofdate.utils.CryptoAES;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hzwy23 on 2017/5/18.
 */
@Controller
@Api("主题配置管理")
public class SysThemeController {
    private final static Logger logger = LoggerFactory.getLogger(SystemPageController.class);
    @Autowired
    private UserService userDetailsService;

    @RequestMapping(value = "/v1/auth/theme/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String changeTheme(HttpServletResponse response, HttpServletRequest request) {
        String themeId = request.getParameter("theme_id");

        String username = JwtService.getConnUser(request).getUserId();
        logger.debug("request user id is:" + username);
        RetMsg retMsg = userDetailsService.changeTheme(themeId, username);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    @RequestMapping(value = "/v1/auth/passwd/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String changePasswd(HttpServletResponse response, HttpServletRequest request) {

        String username = JwtService.getConnUser(request).getUserId();
        String userId = request.getParameter("userid");
        if (!username.equals(userId)) {
            String msg = "forbin change other user's password";
            response.setStatus(421);
            logger.info(msg);
            return Hret.error(421, msg, null);
        }
        String oldPassword = request.getParameter("orapasswd");
        String newPassword = request.getParameter("newpasswd");
        String confirmPassword = request.getParameter("surepasswd");
        if (!newPassword.equals(confirmPassword)) {
            String msg = "new password and comfirm password is not same. please check your new passwod and confirm password";
            response.setStatus(421);
            logger.info(msg);
            return Hret.error(421, msg, null);
        }

        if (newPassword == null || newPassword.length() < 6) {
            String msg = "new password length must greater than 6 and can not be empty.";
            response.setStatus(421);
            logger.info(msg);
            return Hret.error(421, msg, null);
        }

        if (newPassword.length() > 30) {
            String msg = "new password length must less than 30";
            response.setStatus(421);
            logger.info(msg);
            return Hret.error(421, msg, null);
        }

        oldPassword = CryptoAES.getInstance().aesEncrypt(oldPassword);
        newPassword = CryptoAES.getInstance().aesEncrypt(newPassword);

        RetMsg retMsg = userDetailsService.changePassword(newPassword, userId, oldPassword);
        if (retMsg.checkCode()) {
            logger.info("modify user password successfully, user id is :" + username);
            return Hret.success(retMsg);
        }

        response.setStatus(retMsg.getCode());
        logger.debug("user id is not exists or old password is not right.");
        return Hret.error(retMsg);

    }
}
