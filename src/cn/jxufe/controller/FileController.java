package cn.jxufe.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.jxufe.bean.Message;
import cn.jxufe.utils.FileSaver;
/**
 * 用户头像上传
 * @author 86173
 *
 */
@Controller
@RequestMapping("file")
public class FileController {
	 @RequestMapping(value="saveHeadImg",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message saveHeadImg(HttpServletRequest request,@RequestParam("filePathName") MultipartFile uploadFile){

	        return FileSaver.save("images/headImages/", request, uploadFile);
	    }   
}
