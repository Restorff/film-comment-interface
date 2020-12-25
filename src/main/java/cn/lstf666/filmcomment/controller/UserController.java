package cn.lstf666.filmcomment.controller;

import cn.lstf666.filmcomment.entity.Comments;
import cn.lstf666.filmcomment.entity.Film;
import cn.lstf666.filmcomment.entity.User;
import cn.lstf666.filmcomment.result.R;
import cn.lstf666.filmcomment.service.CommonService;
import cn.lstf666.filmcomment.service.MsmService;
import cn.lstf666.filmcomment.service.UserService;
import com.zhenzi.sms.ZhenziSmsClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: filmcomment
 * @description: 普通用户控制器
 * @author: Restorff
 * @create: 2020-12-06 18:28
 **/
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(description="普通用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("test")
    @ApiOperation(value = "测试普通用户接口controller")
    public R test(){
        return R.ok().data("msg","hello");
    }

    @PostMapping("updateUser")
    @ApiOperation(value = "通过电话获取id")
    public R updateUser(@ApiParam(name = "user", value = "用户对象", required = true)
                        @RequestBody User user){
        int i = userService.updateUser(user);
        if (i==1){
        return R.ok().data("msg","更新成功");
        }

        return R.error().data("msg","更新失败");
    }


    @GetMapping("getIdByPhone/{phone}")
    @ApiOperation(value = "通过电话获取user")
    public R getIdByPhone(@ApiParam(name = "phone", value = "电话号码", required = true) //在swagger中备注
                              @PathVariable String phone){
        int uid = userService.getIdByPhone(phone);
        return R.ok().data("uid",uid);
    }


    @GetMapping("getUserByPhone/{phone}")
    @ApiOperation(value = "通过电话获取id")
    public R getUserByPhone(@ApiParam(name = "phone", value = "电话号码", required = true) //在swagger中备注
                          @PathVariable String phone){
        User user = userService.getUserByPhone(phone);
        return R.ok().data("user",user);
    }


    @ApiOperation(value = "发送短信验证码")
    @PostMapping("/sendSms/{phone}")
    public R sendSms(@ApiParam(name = "phone", value = "电话号码", required = true) //在swagger中备注
                         @PathVariable String phone
                     ) throws Exception {

        /**
         *   榛子云短信

//        生成随机验证码4位
        String captcha = RandomStringUtils.random(4,false,true);
        //发送到注册手机
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "榛子云的id", "榛子云的密钥");
        String result = client.send(phone, "验证码: " + captcha + ",你正在注册影评系统账号，验证码在5分钟内有效");
        System.out.println(captcha);
        //验证码存入session
        request.getSession().setAttribute("captcha",captcha);
        //返回结果*/
        if(userService.findUserByPhone(phone) == true){
            return R.error().data("msg","电话号码已被注册");
        }
//1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            System.out.println(code);
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomStringUtils.random(6,false,true);
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,10, TimeUnit.MINUTES);
//            return R.ok();
        return R.ok().data("msg","发送成功");
        } else {
            return R.error().msg("短信发送失败");
        }
    }


    @ApiOperation(value = "注册用户")
    @PostMapping("/saveUser/{captcha}")
    public R save(@ApiParam(name = "user", value = "用户对象", required = true) //在swagger中备注
                  @RequestBody User user,
                  @ApiParam(name = "captcha", value = "验证码", required = true)
                   @PathVariable String captcha)
    {
        if(userService.findUserByPhone(user.getPhone()) == true){
            return R.error().data("msg","电话号码已被注册");
        }

        String right = redisTemplate.opsForValue().get(user.getPhone());
//        String right = (String)request.getSession().getAttribute("captcha");
//        right = "1111";
        if(right == null){
            return R.error().data("msg","验证码已失效，请重新发送");
        }
        if(right.equals(captcha)){
//            request.getSession().removeAttribute("phone");
            redisTemplate.delete(user.getPhone());
//            return R.ok().data("msg","验证成功");
        }else{
            System.out.println("right:"+right+"---captcha:"+captcha);
            return R.error().data("msg","验证失败");
        }

        return userService.save(user) ? R.ok().data("msg","注册成功"):R.error().data("msg","注册失败");
    }


    @PostMapping("/logout/{phone}")
    public R logout(@ApiParam(name = "uid", value = "用户名", required = true) //在swagger中备注
                        @PathVariable String phone){

        String right = redisTemplate.opsForValue().get(phone);
        if(right == null){
            return R.error().data("msg","当前未登录");
        }
            redisTemplate.delete(phone);
//            return R.ok().data("msg","验证成功");
            return R.ok().data("msg","登出成功");


    }




    @PostMapping("/login")
    public R login(@ApiParam(name = "user", value = "用户名", required = true) //在swagger中备注
                   @RequestBody User user){
        boolean login;
        try{
             login = userService.login(user.getPhone(), user.getPassword());
        }catch (Exception e){
            return R.error().data("msg","登录失败,请检查用户名或密码");
        }
            if (login) {
                redisTemplate.opsForValue().set(user.getPhone(),user.getPhone(),24*60, TimeUnit.MINUTES);
            return R.ok().data("token", user.getPhone()).data("msg", "登录成功");
            }
        return R.error().data("msg", "登录失败，请检查用户名或密码");
    }

    @ApiOperation(value = "电影评分更新")
    @PostMapping("/updateFilmScore/{fid}/{newscore}")
    public R updateFilmScore(
                             @ApiParam(name = "fid", value = "电影id", required = true)
                             @PathVariable Integer fid,
                             @ApiParam(name = "newscore", value = "用户评分", required = true)
                             @PathVariable Integer newscore)
    {
        Film film = userService.updateFilmScore(fid, newscore);
        if(film == null){
            return R.error().data("msg","更新失败");
        }
        return R.ok().data("film",film);
    }

    @PostMapping("/userComment")
    @ApiOperation(value = "用户评论发布接口controller")
    public R userComment(@ApiParam(name = "comments",value ="评论对象",required =true)
                     @RequestBody Comments comments
    ){
        comments.setGmtCreate(new Date());
        boolean userCommentOk = userService.userCommentOk(comments);
        if(userCommentOk==false){
            return R.error().data("msg","评论失败");
        }
        else
            return R.ok().data("main",comments.getMain());
    }
    @PostMapping("/commentLove/{uid}/{cid}")
    @ApiOperation(value = "评论点赞接口")
    public R CommentLove(
            @ApiParam(name = "uid", value = "用户id", required = true)
            @PathVariable Integer uid,
            @ApiParam(name = "cid", value = "评论id", required = true)
            @PathVariable Integer cid){

        boolean commentLove = userService.commentLove(cid);
        if(commentLove==false){
            return R.error().data("msg","喜欢失败");
        }else
            return R.ok().data("msg","喜爱成功");

    }


}
