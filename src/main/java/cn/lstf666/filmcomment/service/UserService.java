package cn.lstf666.filmcomment.service;


import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;

public interface UserService {
    boolean findUserByPhone(String phone);

    boolean save(User user);

    boolean login(String phone, String password);

    Film updateFilmScore(int fid, int newscore);

    boolean userCommentOk(Comments comments);

    boolean commentLove(int cid);

    int getIdByPhone(String phone);

    User getUserByPhone(String phone);

    int updateUser(User user);
}
