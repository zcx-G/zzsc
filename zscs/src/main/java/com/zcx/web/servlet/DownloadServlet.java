package com.zcx.web.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/common/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置ContentType字段值
		response.setContentType("image/jpeg");
		//设置相应消息编码
		response.setCharacterEncoding("utf-8");
		//设置请求消息编码
		request.setCharacterEncoding("utf-8"); 				
		//获取所要下载的文件名称
		String filename = request.getParameter("name");

		//对文件名称编码
		filename = new String(filename.trim().getBytes("iso8859-1"),"UTF-8");  
        //下载文件所在目录
        String folder = "D:\\file\\";
		FileInputStream infile = new FileInputStream(new File(folder+filename));
		ServletOutputStream outfile =response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = infile.read(buffer)) != -1) {
			outfile.write(buffer, 0, len);
			outfile.flush();
		}
		infile.close();
		outfile.close();

	}
  	public void doPost(HttpServletRequest request, HttpServletResponse 
  		response) throws ServletException, IOException {
		doGet(request, response);
	}
}