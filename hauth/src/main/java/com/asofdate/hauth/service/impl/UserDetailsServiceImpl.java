package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.UserDetailsDao;
import com.asofdate.hauth.dao.UserThemeDao;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.service.UserDetailsService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/18.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserDetailsDao userDetailsDao;
    @Autowired
    private UserThemeDao userThemeDao;

    @Override
    public UserDetailsEntity findById(String userId) {
        List<UserDetailsEntity> list = userDetailsDao.findById(userId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public RetMsg changeTheme(String themeId, String username) {
        try {
            int size = userThemeDao.changeTheme(themeId,username);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE,"success",null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE,"切换主题失败，请联系管理员",null);
        } catch (DataAccessException e){
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE,"切换主题出现异常，请联系管理员",e.getMessage());
        }
    }

    @Override
    public RetMsg changePassword(String newPasswd, String userId, String oldPasswd) {
        try {
            int size = userDetailsDao.changePasswd(newPasswd, userId, oldPasswd);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE,"success",null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE,"修改密码失败，请联系管理员",null);
        } catch (DataAccessException e){
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE,"修改密码出现异常，请联系管理员",e.getMessage());
        }
    }
}
