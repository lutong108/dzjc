<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<title>政务服务管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<base href="<%=basePath%>">
<!--[if !IE]> -->
<!-- <![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 10]>
	<link rel="stylesheet" href="assets/compatible/dup/bootstrap.min.css"/>
	<link rel="stylesheet" href="assets/compatible/dup/messenger.css"/>
	<link rel="stylesheet" href="assets/compatible/dup/messenger-theme-flat.css"/>
<![endif]-->
<link rel="stylesheet" type="text/css" href="css/login_common.css">
<link rel="stylesheet" type="text/css" href="css/login_load.css">
</head>
<body>
	<div class="top_box clearfix">
		<div class="title">
			<a href="" class="a1">政务服务管理平台</a>
			<!-- <a href="" class="a2">用户登录</a> -->
		</div>
		<div class="top_intro">
		<!-- 
			<a href="">系统简介</a>
			<a href="">帮助中心</a>
		 -->	
		</div>
	</div>
	<div class="load_box">
		<div id="slider1_container" >
	        <div u="slides" class="slides" style="cursor: move; position: absolute; overflow: hidden; left: 0px; top: 0px; width:1920px; height:452px;">
	             <div><img u="image" src="images/zw5_01.png" /></div>
	             <div><img u="image" src="images/zw5_02.jpg" /></div>
	             <div><img u="image" src="images/zw5_03.png" /></div>
	             <div><img u="image" src="images/zw5_04.png" /></div>       
	        </div>
	        <div data-u="navigator" class="slidetip">                                      
	             <div data-u="prototype" style="width:11px; height:11px; border-radius:50%;border: 1px solid #fff;"></div>
	        </div>
	    </div>
	    <div class="load_container">
			<div class="user_k">
				<div class="user_bg"></div>
				<div class="user_box">
					<div class="l_title">用户登录</div>
					<div class="l_con">
						<form action="">
							<div class="in_box in_box1">
								<span class="s1"></span><input id="username" name="username" type="text" value="请输入用户名">
							</div>
							<div class="in_box">
								<span class="s2"></span><input id="passwordShow" name="passwordShow" type="text" value="请输入密码">
								<input id="password" name="password" style="display: none" type="password" value="">
							</div>
							<div id="errorText" class="tips" style="color: red"></div>
							<div class="remeber">
								<input type="checkbox" ng-model="rememberme"> 记住密码
							</div>
							<div class="l_btn clearfix">
								<a id="login-button" href="javascript:void()" onclick="login()" class="a1">登录</a>
								<!--<a href="" class="a2">CA登录</a>-->
							</div>
						</form>
					</div>
					<!--<div class="l_foot clearfix">
						<a href="">找回密码</a>
					</div>-->
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<p>温馨提示： 支持浏览器火狐、谷歌</p>
		<p>Copyright©2017&nbsp;&nbsp;&nbsp;技术支持：湖南科创信息技术股份有限公司</p>
	</div>

	<!-- basic scripts -->
	<script src="assets/jquery.min.js"></script>
	
	<script type="text/javascript">
	(function($) {
		$("#username").focus(function(event) {
			if ($("#username").val() == "请输入用户名")$("#username").val("");
		});
		$("#username").blur(function(event) {
			if ($("#username").val() == "")$("#username").val("请输入用户名");
		});
		
		$("#passwordShow").focus(function(event) {
			$("#passwordShow").hide();
			$("#password").show();
			$("#password").focus();
		});
		$("#password").blur(function(event) {
			if ($("#password").val() == ""){
				$("#passwordShow").show();
				$("#password").hide();
			}
		});
		
		$("#username").keyup(function(event) {
			if (event.keyCode == 13){
				$("#password").show();
				$("#passwordShow").hide();
				$("#password").focus().select();
			}
		});
		$("#password").keyup(function(event) {
			if (event.keyCode == 13)$("#login-button").click();
		});
	})(jQuery);
	

	//登陆处理逻辑
	var msgSetTimeoutObj=undefined;
	function login() {
		if($("#username").val()==""){
			showMessage("用户名不能为空!");
			$("#username").focus();
			return;
		}
		
		if($("#password").val()==""){
			showMessage("密码不能为空!");
			$("#password").focus();
			return;
		}
		
		$.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			'type' : 'POST',
			//如果默认密码强制跳转到密码修改页面，请调用【ws/loginToRedirectUri】服务（需要c2-sysmgr-api至1.0.7+或者升级系统管理到4.2.2+）
			'url' : 'ws/login',
			'data' :JSON.stringify({username : $("#username").val(),password : $("#password").val()}),
			'dataType' : 'json',
			'success' : function(data, type, request) {
				if (request.getResponseHeader('type') === 'error') {
					if(request.getResponseHeader('isShowMessage')==='true' ){
						var message = request.getResponseHeader('message')?decodeURI(request.getResponseHeader('message')):"登录异常，请联系管理员!";
						showMessage(message);
					}else{
						showMessage("登录异常，请联系管理员！");
					}
					$("#username").focus().select();
				} else {
					//后台重定向，调用ws/loginToRedirectUri服务时添加这段代码
// 					var redirectUri = data.redirectUri;
// 					if(null!=redirectUri){
// 						location.href = redirectUri;
// 						return;
// 					}
					var hrefStr=location.href;
					if(hrefStr.indexOf("backUrl")!=-1){
						//重定向到登陆页情况
						var backUrl=hrefStr.substr(hrefStr.indexOf("backUrl")+8,hrefStr.length);
						location.href = "./"+decodeURIComponent(backUrl);
					}else if(hrefStr.indexOf("login.jsp")!=-1||hrefStr.indexOf("login.html")!=-1){
						//打开login页登陆情况（如果项目登陆页为其它名字请重写此判断）
						location.href = "./";
					}else{
						//登陆页被嵌入情况
						window.location.reload();
					}
				}
			},
			'error' : function(xmlhttprequest, errorinfo) {
	            showMessage("登录异常，请联系管理员!");
			}
		});
	}
	
	//显示错误提示
	function showMessage(text){
        $("#errorText").text(text);
	}
	
	</script>
	<script src="js/jssor.slider-22.0.15.min.js"></script>
    <script type="text/javascript" src="js/base.js"></script>
</body>
</html>