package cn.lstf666.filmcomment.service;

import cn.lstf666.filmcomment.entity.Film;

public interface AdminService {
    boolean login(String username, String password);

    boolean deleteInfoOk(Integer cid);

    boolean releaseInfoOk(Film film);
}
