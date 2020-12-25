package cn.lstf666.filmcomment.controller;

/**
 * @program: filmcomment
 * @description: oss控制器
 * @author: Restorff
 * @create: 2020-12-16 16:35
 **/


import cn.lstf666.filmcomment.result.R;
import cn.lstf666.filmcomment.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli_parent
 * @description: oss
 * @author: Restorff
 * @create: 2020-11-30 17:35
 **/
@RestController
@RequestMapping("/filmoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/uploadOssFile")
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
