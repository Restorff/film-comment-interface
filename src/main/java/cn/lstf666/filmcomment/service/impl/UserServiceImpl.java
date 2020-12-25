package cn.lstf666.filmcomment.service.impl;

import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import cn.lstf666.filmcomment.mapper.UserMapper;
import cn.lstf666.filmcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: filmcomment
 * @description: userservice实现类
 * @author: Restorff
 * @create: 2020-12-06 18:32
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean findUserByPhone(String phone) {
        Integer userByPhone = userMapper.findUserByPhone(phone);
        if (userByPhone==1)
        return true;
        else return false;
    }

    @Override
    public boolean save(User user) {
        if(user.getAvatar()==null || "".equals(user.getAvatar())){
            System.out.println("avatar:---"+user.getAvatar());
            user.setAvatar("https://filmcomment.oss-cn-chengdu.aliyuncs.com/QQ%E5%9B%BE%E7%89%8711.gif");
        }
        if(user.getGmtCreate()==null){
            user.setGmtCreate(new Date());
        }
        if (userMapper.save(user) ==1 )
            return true;
        return false;
    }

    public boolean login(String phone, String password) {
        User user = userMapper.login(phone);
        if(user != null && password.equals(user.getPassword())){
            return true;
        }else
            return false;
    }

    @Override
    public Film updateFilmScore(int fid, int newscore) {
        int i = userMapper.updateFilmScore(fid, newscore);
        if (i==1){
            Film filmById = userMapper.getFilmById(fid);
            return filmById;
        }else {
            return null;
        }
    }

    @Override
    public boolean userCommentOk(Comments comments) {
        int i = userMapper.userCommentOk(comments);
        if(i==1){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean commentLove(int cid) {
        int i = userMapper.userCommentLove(cid);
        if(i==1){
            return true;
        }
        else return false;
    }

    @Override
    public int getIdByPhone(String phone) {
        return userMapper.getIdByPhone(phone);
    }

    @Override
    public User getUserByPhone(String phone) {

        return userMapper.getUserByPhone(phone);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
