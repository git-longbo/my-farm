package cn.jxufe.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.jxufe.bean.Message;

public class FileSaver {
	public static Message save(String path, HttpServletRequest request, MultipartFile uploadFile) {
		  Message message = new Message();
		   String realPath = request.getSession().getServletContext().getRealPath(path)+uploadFile.getOriginalFilename();
		  try {
			  uploadFile.transferTo(new File(realPath));
			  message.setCode(0);
			  message.setMsg("上传成功");
		  }catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("上传失败");
		}
		  return message;
	}

}
