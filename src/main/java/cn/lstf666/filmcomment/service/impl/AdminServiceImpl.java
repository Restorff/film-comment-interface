package cn.lstf666.filmcomment.service.impl;

import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import cn.lstf666.filmcomment.mapper.AdminMapper;
import cn.lstf666.filmcomment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: filmcomment
 * @description: 管理员相关接口service
 * @author: Restorff
 * @create: 2020-12-06 21:50
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(String username, String password) {
        User user = adminMapper.login(username);
        if(password.equals(user.getPassword())){
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteInfoOk(Integer cid) {
        int i = adminMapper.deleteInfo(cid);
        if(i==1)
        return true;
        else
            return false;
    }

    @Override
    public boolean releaseInfoOk(Film film) {
        int i =adminMapper.addInfo(film);
        if(i==0){
            return false;
        }
        return true;

    }
}
