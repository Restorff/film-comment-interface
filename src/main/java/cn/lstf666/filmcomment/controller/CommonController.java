package cn.lstf666.filmcomment.controller;


import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.User;
import cn.lstf666.filmcomment.result.R;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Re5t0rff
 * @since 2020-12-06
 */
@CrossOrigin
@RestController                             // @ResponserBody与@Controller的集合体，返回json
@RequestMapping("/common")                  //请求路径的注解
@Api(description="公共接口")                   //在swagger中备注
public class CommonController {
    @Autowired
    private CommonService commonService;

    @GetMapping("test")                     //请求路径的注解，加上上面的之后，访问路径就是/common/test
    @ApiOperation(value = "测试controller") //在swagger生成注释的注解
    public R test(){
        return R.ok().data("msg","hello,common");   //链式编程，统一了返回结果类
    }


    @Cacheable(value = "list",key = "'AllFilm'")
    @ApiOperation(value = "查询所有电影数目")
    @GetMapping("/getAllFilmsCount")
    public R getAllFilms(){
        Integer allFilms = commonService.getAllFilms();
        System.out.println(allFilms);
        return R.ok().data("allFilms",allFilms);
    }


//    @Cacheable(value = "list",key = "'commentByFilm'")
    @ApiOperation(value = "查询一个电影所有评论")
    @GetMapping("/getComments/{fid}")   //    fid通过路径传递
    public R getComments(@ApiParam(name = "fid", value = "电影ID", required = true) //在swagger中备注
                         @PathVariable Integer fid){
        List<Comments> comments = commonService.getComments(fid);
        return R.ok().data("comments",comments);
    }

//    @Cacheable(value = "list",key = "'FilmByPage'")
    @ApiOperation(value = "分页查询电影")
    @GetMapping("/getFilmByPage/{pageNum}/{pageSize}")   //    fid通过路径传递
    public R getFilmByPage(@ApiParam(name = "pageNum", value = "第几页", required = true) //在swagger中备注
                         @PathVariable Integer pageNum,
                        @ApiParam(name = "pageSize", value = "每页有几条数据", required = true) //在swagger中备注
                        @PathVariable Integer pageSize){
        List<Film> filmByPage = commonService.getFilmByPage(pageNum,pageSize);
        return R.ok().data("filmByPage",filmByPage);
    }

//    @Cacheable(value = "list",key = "'OneFilm'")
    @ApiOperation(value = "查询一个电影信息")
    @GetMapping("/getOneFilmById/{fid}")
    public R getOneFilmById(@ApiParam(name = "fid", value = "电影id", required = true) //在swagger中备注
                                @PathVariable Integer fid){
        Film film = commonService.getOneFilmById(fid);
        return R.ok().data("film",film);
    }


    @ApiOperation(value = "查询一个用户信息")
    @GetMapping("/getUserById/{uid}")
    public R getUserById(@ApiParam(name = "uid", value = "用户id", required = true) //在swagger中备注
                            @PathVariable Integer uid){
        User user = commonService.getUserById(uid);
        return R.ok().data("user",user);
    }

}

