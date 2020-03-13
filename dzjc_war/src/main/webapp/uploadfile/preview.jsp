<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>    
<%    
        String path = request.getContextPath();    
        String basePath = request.getScheme() + "://"    
                + request.getServerName() + ":" + request.getServerPort()    
                + path + "/";    
        String attachId = request.getParameter("attachId");
        String pdfpath = request.getParameter("path");
    %>        
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">    
    <html>    
      <head>   
      	 <script type="text/javascript" src="<%=basePath%>assets/js/jquery-1.10.2.js"></script>    
        <script type="text/javascript">
        	function load(){
		        var pdfpath = '<%=pdfpath%>';
		       // pdfpath = decodeURIComponent(decodeURIComponent(pdfpath));
		        var src= "<%=basePath%>scripts/uploadify?opt=preview&attachId=<%=attachId%>&&path="+pdfpath;
		        $("#mainFrame").attr('src',src);
        	}
        </script>
        <title>文件预览</title>    
    </head>
    <body onload="load()">
    	<iframe  id="mainFrame" name="mainFrame" scrolling="yes" 
            frameborder="0" style="padding: 0px; width: 100%; height: 100%"></iframe>
    </body>  
      
    </html>