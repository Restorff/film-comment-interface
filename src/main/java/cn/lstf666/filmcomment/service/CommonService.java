package cn.lstf666.filmcomment.service;

import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Re5t0rff
 * @since 2020-12-06
 */

public interface CommonService {

    /**
     * 功能描述: 得到所有电影
     *
     * @Param: 无
     * @return: List<Comments>
     * @Author: Restorff
     * @Date: 2020/12/7 0:27
    */
//    List<Film> getAllFilms();
    Integer getAllFilms();

    /**
     * 功能描述: 得到一个电影的所有评论
     *
     * @Param: fid
     * @return:List<Comments>
     * @Author: Restorff
     * @Date: 2020/12/7 0:26
    */
    List<Comments> getComments(int fid);


    List<Film> getFilmByPage(Integer pageNum,Integer pageSize);

    Film getOneFilmById(int fid);

    User getUserById(Integer uid);
}
