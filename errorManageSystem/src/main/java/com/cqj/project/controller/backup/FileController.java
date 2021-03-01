package com.cqj.project.controller.backup;

import com.cqj.project.controller.response.ImgUploadResponseBean;
import com.cqj.project.util.FileUtil2;
import com.cqj.project.util.ResponseEntity;
import com.cqj.project.util.ResponseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SuppressWarnings("restriction")
@Api(description = "文件上传", produces = "application/json")
@RestController
@RequestMapping("/file")
public class FileController {

//     * 上传图片
//     *
//     * @param file
//     * @param request
//     * @return
//     * @throws IOException
//     */
    @ApiOperation(value = "富文本内插入图片", notes = "上传图片<br/>http://aligreen.alibaba.com/porn.html,在此检测rate超过80的为涉黄图片，会上传失败")
    @RequestMapping(value = "/uploadImageEdit", method = RequestMethod.POST)
    public ResponseEntity<ImgUploadResponseBean> uploadImageEdit(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName="";
        String filePath="";
        if(!file.isEmpty()) {
            // 获取文件名称,包含后缀
            fileName = file.getOriginalFilename();

            // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
            // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
            String path = "C:/Users/dell/Pictures/新建文件夹/";

            try {
                // 该方法是对文件写入的封装，在util类中，导入该包即可使用，后面会给出方法
                FileUtil2.fileupload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            filePath="http://localhost:8082/image/"+fileName;

        }
        System.out.println(filePath);
        return ResponseEntityUtil.success(new ImgUploadResponseBean(filePath));


        // 尺寸验证
//        String filePath = ossService.upload(file) + "?x-oss-process=image/resize,h_200";
//        String filePath = "http://localhost:8082/image/456.jpg";
//        return ResponseEntityUtil.success(new ImgUploadResponseBean(filePath));
    }

    @ApiOperation(value = "富文本内插入图片", notes = "上传图片<br/>http://aligreen.alibaba.com/porn.html,在此检测rate超过80的为涉黄图片，会上传失败")
    @RequestMapping(value = "/uploadImageEdit2", method = RequestMethod.POST)
    public ResponseEntity<ImgUploadResponseBean> uploadImageEdit2(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 尺寸验证
        String filePath = "http://localhost:8082/image/456.jpg";
        return ResponseEntityUtil.success(new ImgUploadResponseBean(filePath));
    }
}
