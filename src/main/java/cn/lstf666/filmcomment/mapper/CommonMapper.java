package cn.lstf666.filmcomment.mapper;

import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *公共接口mapper
 * @author Re5t0rff
 * @since 2020-12-06
 */
@Repository
@Mapper
public interface CommonMapper {
    /**
     * 功能描述: 得到所有电影信息
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/12/7 0:21
    */
//List<Film> getAllFilms();
    Integer getAllFilms();


/**
 * 功能描述: 得到一个电影的所有评论
 *
 * @Param:
 * @return:
 * @Author: Restorff
 * @Date: 2020/12/7 0:24
*/
List<Comments> getComments(int fid);


/**
 * 功能描述: 分页查询
 *
 * @Param:
 * @return:
 * @Author: Restorff
 * @Date: 2020/12/8 10:34
*/
    List<Film> getFilmByPage();

    /**
     * 功能描述: 得到一条电影信息
     *
     * @Param:
     * @return:
     * @Author: Restorff
     * @Date: 2020/12/8 10:29
     */
    Film getOneFilmById(int fid);

    User getUserById(Integer uid);
}
