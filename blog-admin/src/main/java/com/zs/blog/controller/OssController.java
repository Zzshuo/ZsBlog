package com.zs.blog.controller;

import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.OssService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.response.OssCallbackVo;
import com.zs.blog.vo.response.OssPolicyVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/admin/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo policy() {
        OssPolicyVo result = ossService.policy();
        return ResponseUtil.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo callback(HttpServletRequest request) {
        OssCallbackVo ossCallbackVo = ossService.callback(request);
        return ResponseUtil.success(ossCallbackVo);
    }
}
