package cn.lstf666.filmcomment.mapper;


import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * **
  * <p>
 *  User相关接口
 * </p>
 *
 * @author Re5t0rff
 * @since 2020-12-06
 *
 */

@Repository
@Mapper
public interface UserMapper {
    Integer findUserByPhone(String phone);

    Integer save(User user);

    User login(String username);

    int updateFilmScore(@Param("fid")int fid, @Param("newscore")int newscore);

    Film getFilmById(@Param("fid")int fid);

    int userCommentOk(Comments comments);

    int userCommentLove(int cid);

    int getIdByPhone(String phone);

    User getUserByPhone(String phone);

    int updateUser(User user);
}
