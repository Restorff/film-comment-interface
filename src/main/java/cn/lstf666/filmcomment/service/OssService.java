package cn.lstf666.filmcomment.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli_parent
 * @description:
 * @author: Restorff
 * @create: 2020-11-30 17:36
 **/

public interface OssService {

    String uploadFileAvatar(MultipartFile file);
}
