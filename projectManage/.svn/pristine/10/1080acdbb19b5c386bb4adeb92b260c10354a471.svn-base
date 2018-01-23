package com.linkGap.projectManage.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * 进行文件的下载操作
 */
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DownLoadServlet.class);

	 public DownLoadServlet() {
	        super();
	    }
	 
	 
	public void autoDownLoad(HttpServletRequest request, HttpServletResponse response,String filePath,String rootPath,String flag){
		try {
			if("1".equals(flag)){
				//表示是寻找导出的订单（来输入物流单号）
				request.setAttribute("flagOrder", flag);
			}
			request.setAttribute("filePath", filePath);
			request.setAttribute("rootPath", rootPath);
			doGet(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String flagOrder =(String) request.getAttribute("flagOrder");//存放输入订单编号的标识
		String filePath = (String) request.getAttribute("filePath");//实现自动下载的文件名
		String rootPath =(String) request.getAttribute("rootPath");
		logger.error("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzctxPath"+rootPath);
        logger.error("sssssssssssssssssssssssssssssssssstoreFileNamePath"+filePath);
		
    	String storeFileNamePath = request.getParameter("path");//文件的存储路径
    	if(storeFileNamePath!=null && !"".equals(storeFileNamePath)){
    		//storeFileNamePath =new String(storeFileNamePath.getBytes("iso-8859-1"),"UTF-8");//进行转码
    	}
    	
    	String orginalName = request.getParameter("name");
    	if(orginalName!=null && !"".equals(orginalName)){
    		//orginalName =new String(orginalName.getBytes("iso-8859-1"),"UTF-8");//进行转码
    	}
    	
    	String flag= request.getParameter("flag");//如果falg=1表示是下载评价的静态模板
    	if(filePath!=null && !"".equals(filePath)){
    		flag="2";
    		storeFileNamePath = filePath;
    	}
    	
		//设置下载响应头
		response.setContentType("application/x-msdownload");

        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        
        String ctxPath="";
        String downLoadPath="";
        if("1".equals(flag)){//表示访问评价导入的静态模板
        	ctxPath = request.getSession().getServletContext().getRealPath("/")+"downloadTemp/";//在项目下的指定路径的存放位置  
            
        }else if("2".equals(flag)){
        	//ctxPath = request.getSession().getServletContext().getRealPath("/")+"commentTemp/";
        	if("1".equals(flagOrder)){
        		ctxPath=rootPath+File.separator; 
        	}else if("0".equals(flagOrder)){
        		ctxPath=""+File.separator; //linux上
        	}	
        	
        }else{//表示从服务器上下载其他文件
        	ctxPath = request.getSession().getServletContext().getRealPath("/"); 
        }
        
        
        downLoadPath = ctxPath + storeFileNamePath;  //文件真实的存放位置
        
        File file = new File(downLoadPath);
        long fileLength = file.length();  
       // response.setContentType("application/octet-stream");  
        String agent = request.getHeader("User-Agent");
		boolean isFireFox = (agent != null && agent.indexOf("Firefox") != -1);
		if (isFireFox) {//火狐
			if(filePath!=null && !"".equals(filePath)){//表示是浏览器端的文件自动下载
				response.setHeader("Content-Disposition", "attachment;filename="+new String(filePath.getBytes("UTF-8"), "ISO-8859-1"));
			}else{//普通文件下载
				response.setHeader("Content-Disposition", "attachment;filename="+new String(orginalName.getBytes("UTF-8"), "ISO-8859-1"));
			}
			
			
		}else if(agent != null && agent.indexOf("AppleWebKit") != -1){//谷歌
			if(filePath!=null && !"".equals(filePath)){
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filePath, "UTF-8"));
			}else{
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(orginalName, "UTF-8"));
			}
	
		} else {//默认ie
			if(filePath!=null && !"".equals(filePath)){
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filePath, "UTF-8"));
			}else{
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(orginalName, "UTF-8"));
			}
		}
        response.setHeader("Content-Length", String.valueOf(fileLength));  //返回文件的大小
  
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close();    
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
