package com.zs.blog.object;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zshuo
 * @date 2019-05-07
 **/
@Data
public class FileReqVo extends RequestVo {
    private MultipartFile file;
    private String token;
}
