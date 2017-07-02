package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.UserCheckDao;
import com.asofdate.hauth.entity.UserLoginEntity;
import com.asofdate.hauth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/16.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserCheckDao userCheckDao;

    private UserLoginEntity setEmpty(UserLoginEntity userCheck, String userId) {
        userCheck.setFlag(false);
        userCheck.setUsername(userId);
        userCheck.setMessage("The user does not exist");
        userCheck.setPassword("");
        return userCheck;
    }

    @Override
    public UserLoginEntity loginValidator(String userId, String password) {
        List<UserLoginEntity> list = userCheckDao.findByUserId(userId);
        UserLoginEntity userCheck = new UserLoginEntity();
        if (list.size() == 0) {
            userCheck.setRetCode("401");
            return setEmpty(userCheck, userId);
        }

        userCheck = list.get(0);
        if (list.size() > 1) {
            userCheck.setRetCode("402");
            userCheck.setFlag(false);
            userCheck.setUsername(userId);
            userCheck.setMessage("There are multiple users, user management has abnormal situation, no login, please contact management");
            userCheck.setPassword("");
            return userCheck;
        }

        if (userCheck.getContinueErrorCnt() > 7) {
            userCheck.setRetCode("403");
            userCheck.setUsername(userId);
            userCheck.setMessage("The id has been logged more than 7 times and has been locked. Please contact the administrator");
            userCheck.setFlag(false);
            userCheck.setPassword("");
            return userCheck;
        }

        if (!userCheck.getStatusId().equals("0")) {
            userCheck.setRetCode("406");
            userCheck.setUsername(userId);
            userCheck.setMessage("The account is locked, please contact the administrator");
            userCheck.setPassword("");
            userCheck.setFlag(false);
            return userCheck;
        }

        if (password.equals(userCheck.getPassword())) {
            userCheck.setFlag(true);
        } else {
            userCheck.setRetCode("405");
            userCheck.setFlag(false);
            userCheck.setMessage("Password error, please re-enter");
        }
        userCheck.setUsername(userId);
        userCheck.setPassword("");
        return userCheck;
    }

    @Override
    public UserLoginEntity findByUserId(String userId) {
        List<UserLoginEntity> list = userCheckDao.findByUserId(userId);
        if (list.size() == 0) {
            UserLoginEntity userCheck = new UserLoginEntity();
            return setEmpty(userCheck, userId);
        }
        return list.get(0);
    }
}
