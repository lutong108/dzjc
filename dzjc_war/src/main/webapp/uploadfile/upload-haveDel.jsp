<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>    
<%    
        String path = request.getContextPath();    
        String basePath = request.getScheme() + "://"    
                + request.getServerName() + ":" + request.getServerPort()    
                + path + "/";    
        String businessId = request.getParameter("businessId");
        String materialId = request.getParameter("materialId");
        
        String modelName = request.getParameter("modelName");
        //操作类型，展示时传入download,  上传下载时传入 uploaddownload 也可以不传
        String optFlag = StringUtils.isBlank(request.getParameter("optFlag")) ? "" : request.getParameter("optFlag");
        
        //String businessId = "202";
        
    %>        
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">    
    <html>    
      <head>    
            
        <title>文件上传</title>    
        <style type="text/css">
        	#indexloading1 {
			    background: #FFF url(Resources/reference/loading.gif) no-repeat center center;
			    height: 100%;
			    width: 100%;
			    position: fixed;
			    z-index: 2000;
			    left: 0;
			    top: 0;
			    filter:alpha(opacity=50);  /*支持 IE 浏览器*/
				-moz-opacity:0.50; /*支持 FireFox 浏览器*/
				opacity:0.50;  /*支持 Chrome, Opera, Safari 等浏览器*/
			}
        
        </style>
     <link href="assets/accept/css/accept.css" rel="stylesheet" type="text/css" />         
     <link href="assets/js/uploadify.css" rel="stylesheet" type="text/css" />    
     <script type="text/javascript" src="assets/js/jquery.uploadify-3.1.js"></script>    
    <script type="text/javascript">   
  //判断是否安装了flash
  $(function () {
 		if(!chkFlash()){
 			 $("#tip_msg").html('<font color="red">您没有安装Flash!</font>');
 	    	 $("#tip_msg").show();
 		}
  });
  
    function chkFlash(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
          
        var isOpera = userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR/") > -1; //判断是否Opera浏览器  
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
        var isEdge = userAgent.indexOf("Edge") > -1 || userAgent.indexOf(") like Gecko")>-1; //判断是否IE的Edge浏览器  
        var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器  
        var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器  
        var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1 && userAgent.indexOf("OPR/") == -1 && userAgent.indexOf("Edge") == -1; //判断Chrome浏览器  
        //判断是否存在flash插件  
        var myFlash = (function(){  
          if(typeof window.ActiveXObject != "undefined"){  
            return new ActiveXObject("ShockwaveFlash.ShockwaveFlash");  
          }else{  
            return navigator.plugins['Shockwave Flash'];  
          }  
        })();  
        if(myFlash){  
            return true;  
        }else{  
            return false;
        }  
    
  	}
    
    
    var businessId = '<%=businessId%>';
    var modelName = '<%=modelName%>';
    var optFlag = '<%=optFlag%>';
    //如果只是页面用于展示信息时，只能下载，不能上传
    if(optFlag == "download"){
    	showApplyDiv(document.getElementById("downloadli"));
    	$("#uploadli").removeAttr("onclick");
    }
    loadAttach();
       $("#uploadify").uploadify({
             'auto'           : false,  
             'method'         : 'post',
             'swf'            : '<%=path%>/assets/js/uploadify.swf',    
             'uploader'       : '<%=path%>/scripts/uploadify?opt=upload&businessId='+businessId,    //后台处理的请求    
             'queueID'        : 'fileQueue',//与下面的id对应    
             'queueSizeLimit' : 10,    
             'fileSizeLimit'  : 8192,
             'fileTypeDesc'   : '请选择doc,.docx,pdf,xlsx,xls,jpg,jpeg,gif,png,bmp文件',    
             'fileTypeExts'   : '*.doc;*.docx;*.pdf;*.xlsx;*.xls;*.jpg;*.jpeg;*.gif;*.png;*.bmp;', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc    
             'multi'          : true,    
             'buttonText'     : '点击选择上传文件',
             'removeTimeout'  : 1,
             
             onQueueComplete : function(stats) {    //文件提交后调用的方法
           	  //得到ID="jclist"的所在的作用域，调用作用域中的方法，完成材料实例信息的修改
           	  if (modelName != null && modelName != "null" && modelName != "" && modelName != "undefined"){
           		angular.element("#jclist").scope().functions.setSubmitStateValue(modelName,businessId);
           		loadAttach();
           	  }
           }
            
       });  
    
    function showApplyDiv(obj){
		obj = $(obj);
		$(".shengqingrenLeft li").removeClass("sqxuanzhong");
		$("#uploaddiv").hide();
		$("#downloaddiv").hide();
		obj.addClass("sqxuanzhong");
		var id = obj.attr("id");
		if(id == "uploadli"){
			$("#uploaddiv").show();
		}else if(id == "downloadli"){
			loadAttach();
			$("#downloaddiv").show();
		}
	 }
    
    function loadAttach(){
    	$.ajax({
			  url: "<%=basePath%>accept/v1/attach/findListByBusinessId/"+businessId,
			  async: true,
			  success: function(data){
				  var objs = new Array();
				  $("#attachlist tbody").find("tr").remove();
				  for(var i=0; i<data.length; i++){
					  var obj = data[i];
					  var pdfPath = data[i].pdfPath;
					  var type = obj.attachType.substring(1);
					  type = type.toLowerCase();
					  //var img = '<img onerror="this.src=\'images/default.png\'" style="height:50px" src="/images/'+ type +'.png"/>';
					  var img = '<img onerror="this.src=\'images/default.png\'" style="height:30px" src="images/'+ type +'.png"/>';
				  	  var url = "<%=basePath%>scripts/uploadify?opt=download&attachId="+obj.attachId;
				  	  var preview = '';
				  	  if(type=='pdf' ||type=='doc' || type=='docx' || type=='xls' || type=='xlsx' || type=='jpg'|| 'jpeg'==type || 'gif'==type || 'png'==type || 'bmp'== type){
				  		preview = '<a onclick="preview(\'' + obj.attachId + '\',\''+ pdfPath + '\')" href="javascript:void(0)">预览</a>';
				  	  }
				  	  var delHtml =  '<a onclick="delFile(\'' + obj.attachId + '\',\''+ pdfPath + '\')" href="javascript:void(0)">删除</a>';
				  	  
				  	  var content = '<tr><td height="30" align="center">'+(i+1)+'</td><td>'+obj.attachName+'</td><td align="center">'+new Date(obj.createTime).Format("yyyy-MM-dd hh:mm:ss")+'</td><td align="center">'+img+'</td><td align="center" class="cailiaocaozuo">'+ delHtml+ '<a href=\''+url+'\'>下载</a>' + preview +'</td></tr>';
				  	  $("#attachlist tbody").append(content);
				  }
				  
				  $("#downloadli").html('已上传(<font style="font-weight:bold;color:#ff4646;">'+data.length+'</font>)');
				  $("#val_cnt").val(data.length);
			  }
		});
    }
    
    function downAttach(attachId){
    	$.ajax({
			  url: "<%=basePath%>scripts/uploadify?opt=download&attachId="+attachId,
			  async: true,
			  success: function(data){
				 alert(data);
			  }
		});
    	
    }
    function preview(attachId,pdfPath){
    	if(pdfPath != undefined && pdfPath != null &&pdfPath != '' && pdfPath != 'undefined'){
    		window.open("<%=basePath%>uploadfile/preview.jsp?attachId="+attachId+"&path="+encodeURIComponent(encodeURIComponent(pdfPath)), "_blank");
    	}else{
    		$("#indexloading1").show();
    		setTimeout(function() { 
		    	$.ajaxSetup({cache: false});//关闭AJAX相应的缓存
		    	$.ajax({
					  url: "<%=basePath%>accept/v1/pdfutil/"+attachId,
					  async: false,
					  success: function(data){
						  window.open("<%=basePath%>uploadfile/preview.jsp?attachId="+attachId+"&path="+encodeURIComponent(encodeURIComponent(data)), "_blank");
					  },error:function(){
						  $("#indexloading1").hide();
						  alert('转换失败,请联系管理员');
					  },
					  complete:function(data) { 
						 $("#indexloading1").hide();
					  }
				});
    		}, 1000);
    	}
    }
    function delFile(attachId,pdfPath){
    	 
	    	$.ajaxSetup({cache: false});//关闭AJAX相应的缓存
	    	$.ajax({
				  url: "<%=basePath%>accept/v1/pdfutil/del/"+attachId,
				  async: false,
				  success: function(data){
						  
						  loadAttach();
					 
				  }
			});
    }
    
    function previewClosed(){
    	$("#myModal1").hide();
    }
    </script>    
     </head>    
    <body>  
    <div id="indexloading1" style="display: none;"></div>
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:900px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  aria-hidden="true" onclick="previewClosed()">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel1" style="padding-top: 8px;">
					标题内容
				</h4>
			</div>
			<div id="divcontent1" class="modal-body">
				主体内容
			</div>
			<div class="modal-footer" style="height: 55px;">
				<button type="button" class="btn btn-default" style="height: 40px;" onclick="previewClosed()">关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 模态框（Modal）结束 -->
<div id="tip_msg"   style="display: none">判断flash</div>
    <div class="shengqingren">
    	<input type="hidden" id="val_cnt" value="0"/> 
    	<input type="hidden" id="val_businessId" value="<%=businessId%>"/> 
    	<input type="hidden" id="val_materialId" value="<%=materialId%>"/> 
    	
		<div class="shengqingrenLeft">
			<ul>
				<li id="uploadli" class="sqxuanzhong"  style="cursor: pointer;" onclick="showApplyDiv(this)">上传</li>
				<li id="downloadli" style="cursor: pointer;" onclick="showApplyDiv(this)">已上传(<font style="font-weight:bold;color:#ff4646;">0</font>)</li>
			</ul>
			<div style="clear: both"></div>
		</div>
	</div>
	
    <div id="uploaddiv">   
	    <div id="fileQueue"></div>    
	    <input type="file" name="uploadify" id="uploadify" />   
	    <div style="clear: both"></div>
	    <div style="float: left;"><a class="upload_btn upload_blue" href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a></div> 
	    <div style="float: left;margin-left: 10px;"><a class="upload_btn upload_green" href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a></div>
    </div> 
    
    <div id="downloaddiv" style="display: none;">   
	    <table id="attachlist" width="99%" border="0" cellspacing="0" cellpadding="0" align="center" class="cailiaobiaoge"> 
	    	<thead>
	    		<tr class="cailiaotittle">
				    <td width="5%">序号</td>
				    <td width="30%">材料名称</td>
				    <td width="35%">上传时间</td>
				    <td width="10%">文件类型</td>
				    <td width="20%">操作</td>
				</tr>
	    	</thead>
	    	<tbody>
	    	<!-- 
			  <tr>
			    <td height="30" align="center">1</td>
			    <td>煤炭生产许可证年检申请表.jpg</td>
			    <td>d:/upload/2017-06-05/页面.png</td>
			    <td align="center">1024</td>
			    <td align="center" class="cailiaocaozuo"><a href="">下载</a></td>
			  </tr>
			 -->
	    	</tbody>
		  
		  
		</table>
    </div>
    
    
    
     
      </body>    
    </html>