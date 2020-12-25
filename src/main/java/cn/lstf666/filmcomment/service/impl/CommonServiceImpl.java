package cn.lstf666.filmcomment.service.impl;

import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import cn.lstf666.filmcomment.mapper.CommonMapper;
import cn.lstf666.filmcomment.service.CommonService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  公共接口实现类
 * </p>
 *
 * @author Re5t0rff
 * @since 2020-12-06
 */
@Service
public class CommonServiceImpl  implements CommonService {

    @Autowired
    private CommonMapper commonMapper;
    @Override
    public Integer getAllFilms() {
        return commonMapper.getAllFilms();
    }

    @Override
    public List<Comments> getComments(int fid) {
        return commonMapper.getComments(fid);
    }

    @Override
    public List<Film> getFilmByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return commonMapper.getFilmByPage();
    }

    @Override
    public Film getOneFilmById(int fid) {
        return commonMapper.getOneFilmById(fid);
    }

    @Override
    public User getUserById(Integer uid) {
        return commonMapper.getUserById(uid);
    }

}
