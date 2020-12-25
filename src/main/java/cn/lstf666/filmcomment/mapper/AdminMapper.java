package cn.lstf666.filmcomment.mapper;

import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {
    User login(String username);

    int deleteInfo(int cid);

    User checkuid();

    int addInfo(Film film);
}
