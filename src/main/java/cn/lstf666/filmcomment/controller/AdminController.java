package cn.lstf666.filmcomment.controller;

import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import cn.lstf666.filmcomment.result.R;
import cn.lstf666.filmcomment.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: filmcomment
 * @description: 管理员相关controller
 * @author: Restorff
 * @create: 2020-12-06 21:45
 **/
@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(description="管理员接口")
public class AdminController {

    @Autowired
    private AdminService adminService;
    /**
     * 功能描述: 测试controller
     *
     * @Param:
     * @return:
     * @Author: Re5t0rff
     * @Date: 2020/12/6 22:20
    */
    @GetMapping("test")
    @ApiOperation(value = "测试管理员接口controller")
    public R test(){
        return R.ok().data("msg","hello,admin");
    }

    @PostMapping("/login")
    public R login(@ApiParam(name = "username", value = "用户名", required = true) //在swagger中备注
                       @RequestBody User user
                   ){
        boolean login = adminService.login(user.getUsername(), user.getPassword());
        if (login)
            return R.ok().data("token","admin");

        return R.error().data("msg","登录失败,密码错误");

    }



    @PostMapping("/release")      //发布电影信息
    @ApiOperation(value = "管理员电影发布接口controller")
    public R release(@ApiParam(name = "film",value ="电影对象",required =true)
                     @RequestBody Film film
    ){
        System.out.println(film.toString());

        boolean release = adminService.releaseInfoOk(film);
        if(release==false){
            return R.error().data("msg","添加失败");
        }
//        int i = film.getFid();
        return R.ok().data("filmname",film.getFilmname());
    }


    //删除信息
    @DeleteMapping("/deleteInfo/{cid}")
    @ApiOperation(value = "管理员电影信息删除controller")
    public R deleteInfo(@ApiParam(name="cid",value="评论用户id",required= true)
                        @PathVariable Integer cid
                        )
    {

        if(true) {
            boolean deleteInfoOk = adminService.deleteInfoOk(cid);
            if (deleteInfoOk)
                return R.ok().data("msg", "删除成功");
            return R.error().data("msg", "删除失败");
        }
        else
            return R.error().data("msg","无权限");
    }


    @GetMapping("/info")
    public R info(){
        return R.ok()
                .data("roles","管理员组")
                .data("name","admin")
                .data("avatar","https://i.loli.net/2020/09/30/1eSxqEml83d4U9A.png");
    }
}
