package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.UserDao;
import com.asofdate.hauth.dao.UserThemeDao;
import com.asofdate.hauth.dto.UserDTO;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.entity.UserEntity;
import com.asofdate.hauth.service.UserService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserThemeDao userThemeDao;

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<UserEntity> findAll(String orgId, String statusCd) {
        return userDao.findAll(orgId, statusCd);
    }

    @Override
    public int add(UserEntity userEntity) {
        return userDao.add(userEntity);
    }

    @Override
    public int delete(List<UserEntity> list) {
        return userDao.delete(list);
    }

    @Override
    public int update(UserEntity userEntity) {
        return userDao.update(userEntity);
    }

    @Override
    public int changePassword(UserDTO m) {
        return userDao.changePassword(m);
    }

    @Override
    public int changeStatus(String userId, String status) {
        return userDao.changeStatus(userId, status);
    }

    @Override
    public UserDetailsEntity findById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public RetMsg changeTheme(String themeId, String username) {
        try {
            int size = userThemeDao.changeTheme(themeId, username);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "切换主题失败，请联系管理员", null);
        } catch (DataAccessException e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "切换主题出现异常，请联系管理员", e.getMessage());
        }
    }

    @Override
    public RetMsg changePassword(String newPasswd, String userId, String oldPasswd) {
        try {
            int size = userDao.changePasswd(newPasswd, userId, oldPasswd);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "修改密码失败，请联系管理员", null);
        } catch (DataAccessException e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "修改密码出现异常，请联系管理员", e.getMessage());
        }
    }
}
