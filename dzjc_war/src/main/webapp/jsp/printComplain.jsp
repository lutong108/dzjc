<%@page import="com.chinacreator.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String message = StringUtil.deNull(request.getParameter("message"));
	Map map = JSON.parseObject(message);
	String acceptTime="";
	String caseno="";
	String complainUserName="";
	String complainUserPhone="";
	String complainUserAddress="";
	String bycomplainedOrg="";
	String bycomplainedUserOrg="";
	String bycomplainedUserPhone="";
	String appeptUser="";
	String appeptUserFz="";
	String complainContent="";
	String mainappeal="";
	String handleIdeaSL="";
	String handleIdeaSH="";
	System.out.println(message);
	if(map!=null){
		 acceptTime=map.get("acceptTime")==null?"":StringUtil.deNull((String)map.get("acceptTime"));
		 caseno=map.get("caseno")==null?"":StringUtil.deNull((String)map.get("caseno"));
		 complainUserName=map.get("complainUserName")==null?"":StringUtil.deNull((String)map.get("complainUserName"));
		 complainUserPhone=map.get("complainUserPhone")==null?"":StringUtil.deNull((String)map.get("complainUserPhone"));
		 complainUserAddress=map.get("complainUserAddress")==null?"":StringUtil.deNull((String)map.get("complainUserAddress"));
		 bycomplainedOrg=map.get("bycomplainedOrg")==null?"":StringUtil.deNull((String)map.get("bycomplainedOrg"));
		 bycomplainedUserOrg=map.get("bycomplainedUserOrg")==null?"":StringUtil.deNull((String)map.get("bycomplainedUserOrg"));
		 bycomplainedUserPhone=map.get("bycomplainedUserPhone")==null?"":StringUtil.deNull((String)map.get("bycomplainedUserPhone"));
		 appeptUser=map.get("appeptUser")==null?"":StringUtil.deNull((String)map.get("appeptUser"));
		 appeptUserFz=map.get("appeptUserFz")==null?"":StringUtil.deNull((String)map.get("appeptUserFz"));
		 complainContent=map.get("complainContent")==null?"":StringUtil.deNull((String)map.get("complainContent"));
		 mainappeal=map.get("mainappeal")==null?"":StringUtil.deNull((String)map.get("mainappeal"));
		 handleIdeaSL=map.get("handleIdeaSL")==null?"":StringUtil.deNull((String)map.get("handleIdeaSL"));
		 handleIdeaSH=map.get("handleIdeaSH")==null?"":StringUtil.deNull((String)map.get("handleIdeaSH"));
	}
%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="<%=basePath%>js/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lodop/6.2.2.2/LodopFuncs.js?v=1.1.6"
	type="text/javascript"></script>
 <object id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0></embed>
</object>

<style type="text/css">
</style>
</head>
<body>
	<div id="div" align="center" style="page-break-before: always;">
		<div style="padding-right: 20px; padding-left: 20px;">
			<div align="center" style="padding-top: 10px;">
				<font face="宋体" style="font-size: 40px; font-weight: bold">湖南省优化经济发展环境办公室</font>
				<br/>
				<font face="宋体" style="font-size: 40px; font-weight: bold">
					投&nbsp;诉&nbsp;受&nbsp;理&nbsp;单
				</font>
			</div>
			<div>
				<table class="table"  width="95%" border="1" cellspacing="0" cellpadding="0">
					<tr height="50px">
					<!-- 宽度和居中用style设置,打印就没有效果 -->
						<td width="12%"  align="center" colspan="2">受理时间</td>
						<td width="24%"  align="center"><%=acceptTime %></td>
						<td width="12%"  align="center" colspan="2">受理编号</td>
						<td width="24%" align="center"><%=caseno %></td>
					</tr>
					<tr height="50px">
						<td width="6%" align="center" rowspan="3">投诉人</td>
						<td width="6%" align="center">姓名</td>
						<td align="center"><%=complainUserName %></td>
						<td width="6%" align="center" rowspan="3">被投诉人</td>
						<td width="6%" align="center">单位名称</td>
						<td align="center"><%=bycomplainedOrg %></td>
					</tr>
					<tr height="50px">
						<td width="6%" align="center">电话</td>
						<td align="center"><%=complainUserPhone %></td>
						<td width="6%" align="center">联系人</td>
						<td align="center"> <%=bycomplainedUserOrg %></td>
					</tr>
					<tr height="50px">
						<td width="6%" align="center">地址</td>
						<td  align="center"><%=complainUserAddress %></td>
						<td width="6%" align="center">电话</td>
						<td align="center"><%=bycomplainedUserPhone %></td>
					</tr>
					<tr height="50px">
						<td width="12%" align="center" colspan="2">受理人</td>
						<td align="center"><%=appeptUser %></td>
						<td align="center" width="12%" colspan="2">受理负责人</td>
						<td align="center"><%=appeptUserFz %></td>
					</tr>
					<tr height="50px">
						<td align="center" width="6%" rowspan="2">投诉内容</td>
						<td align="left" colspan="5" style="text-align:left;">&nbsp;投诉人反映：<%=complainContent %></td>
					</tr>
					<tr height="50px">
						<td align="left" colspan="5" style="text-align:left;">&nbsp;投诉人诉求：<%=mainappeal %></td>
					</tr>
					<tr height="130px">
						<td align="center" width="6%">受理人意见</td>
						<td align="left" colspan="5"><%=handleIdeaSL %></td>
					</tr>
					<tr height="130px">
						<td  align="center"width="6%">审核意见</td>
						<td  align="left"colspan="5"><%=handleIdeaSH %></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		 var LODOP;
		setTimeout("prn1_preview()", 2500);
		function prn1_preview() {
			LODOP = getLodop(document.getElementById('LODOP_OB'), document
					.getElementById('LODOP_EM'));
			LODOP.ADD_PRINT_HTM("40", "0%", "100%", "100%", document
					.getElementById("div").innerHTML);
			LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "A4");
			LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);

			//LODOP.ADD_PRINT_BARCODE(60,940,144,135,"QRCode","http://www.kaifu.gov.cn:9080/xndt/virtualhall/instance/searchinfoysl.jsp");
			LODOP.SET_PRINT_STYLEA(0, "GroundColor", "#FFFFFF");
			LODOP.SET_PRINT_STYLEA(0, "QRCodeVersion", 7);
			LODOP.NewPage();
			LODOP.PREVIEW();
			parent.layer.closeAll();
		}
	</script>
</body>
</html>