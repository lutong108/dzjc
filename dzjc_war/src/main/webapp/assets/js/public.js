// 判断字符串是否为空
function IsSpace(strMain) {
	var strComp = strMain;
	try {
		if (strComp == "  " || strComp == "" || strComp == " "
				|| strComp == null || strComp == "null" || strComp.length == 0
				|| typeof strMain == "undefined" || strMain == "undefined") {
			return true;
		} else {
			return false;
		}
	} catch (e) {
		return false;
	}
}
function isSpace(strMain) {
	IsSpace(strMain);
}
// 判断字符串不为空
function IsNotSpace(strMain) {
	var strComp = strMain;
	try {
		if (strComp == "  " || strComp == "" || strComp == " "
				|| strComp == null || strComp == "null" || strComp.length == 0
				|| typeof strMain == "undefined" || strMain == "undefined") {
			return false;
		} else {
			return true;
		}
	} catch (e) {
		return false;
	}
}
//清空字符串两边的空白
function trim(strMain) {
	if (strMain == null) {
		return "";
	}
	strMain = strMain + "";
	return strMain.replace(/(^\s*)|(\s*$)/g, "");
}


// 获取当前应用上下文路径

function getContextPath() { 
	var contextPath = document.location.pathname; 
	if(contextPath.substr(0,1) != "/"){
		contextPath="/"+contextPath;
	}
	var index =contextPath.substr(1).indexOf("/"); contextPath = contextPath.substr(0,index+1); 
	delete index;
	return contextPath; 
} 

/* 取指定长度的字符串 */
function getContentByLimit(str, iLimit){
	 if(IsSpace(str)){
	 	   return null;
	 }    
	 str = trim(str);
	 if(str.length <= iLimit){
	 	  return str;
	 }else{
	 	  return str.substr(0,iLimit);
	 }
}
//url特殊字符
function encodeURLKcapp(str){
	if(!IsSpace(str)){
		return encodeURIComponent(encodeURIComponent(str));
	}else{
		return str;
	}
}
// 解码
function decodeURLKcapp(str){
	if(!IsSpace(str)){
		return decodeURIComponent(decodeURIComponent(str));
	}else{
		return str;
	}
}
//获取一天的开始时间
function getBeginDate(varDate){
	//moment.js  平台自带
	return Date.parse(new Date(moment(varDate).format("YYYY-MM-DD")));
}

//获取一天的结束日期
function getEndDate(varDate){
	var dateTemp=moment(varDate).format("YYYY-MM-DD");
	dateTemp=Date.parse(dateTemp);
	return new Date().setTime((dateTemp/1000 + 24 * 60 * 60 - 1) * 1000);
}
//生成UUID
function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";
 
    var uuid = s.join("");
    return uuid;
}

//获取当前月的第一天和最后一天
function CurrentMonth(){
	var date = new Date();
	this.firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
	this.lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
}

function formatSecond(seconds){
	if(IsSpace(seconds)){
		return '0秒';
	}
	var theTime = parseInt(seconds);// 秒
    var theTime1 = 0;// 分
    var theTime2 = 0;// 小时
    if(theTime > 60) {
        theTime1 = parseInt(theTime/60);
        theTime = parseInt(theTime%60);
        if(theTime1 > 60) {
            theTime2 = parseInt(theTime1/60);
            theTime1 = parseInt(theTime1%60);
        }
    }
    var result = ""+parseInt(theTime)+"秒";
    if(theTime1 > 0) {
        result = ""+parseInt(theTime1)+"分"+result;
    }
    if(theTime2 > 0) {
        result = ""+parseInt(theTime2)+"小时"+result;
    }
    return result;
}

//插入"多规合一"数据
function insertDuoGuiRow(tableId,rowid,beginDate,endDate,isInvestmentSupervision,baseUrl){
	//1,单击"多规合一"数据，不处理
	if(rowid.substring(0,6) == "DuoGui"){
		return;
	}

	//2,"多规合一"数据存在
	//2.1,定义duoGuiRowId
	var duoGuiRowId = "DuoGui" + rowid;
	//2.2,"多规合一"数据已插入点击行的下边
	if($("#"+duoGuiRowId).length>0){
		//2.2.1, 已插入的数据处于隐藏状态,需显示
		if($("#"+duoGuiRowId).is(":hidden")){
			$("#"+duoGuiRowId).show();
			
		//2.2.2, 已插入的数据处于显示状态,需隐藏	
		}else if($("#"+duoGuiRowId).is(':visible')){
			$("#"+duoGuiRowId).hide();
		}
			
	}else{
	  //3,加载"多规合一"数据
	  $.ajax({
			"url" : baseUrl+"/"+rowid+"/"+beginDate+"/"+endDate+"/"+isInvestmentSupervision,
			"type" : "get",
			"dataType": "json",
			"async" : false,
			"success" : function(data){
				if(IsSpace(data)){
					alert("此机构暂无多规合一数据！");
				}else{
					var dataArr = new Array();
					dataArr.push(data);
					for(var i=0;i<dataArr.length;i++){
						//注意：此处orgId必须为页面主键id，详细参考平台文档
						jQuery("#"+tableId).addRowData(dataArr[i].orgId,dataArr[i],"after",rowid);
					}
				}
			},
			"error":function(data){
				alert("多规合一数据查询失败！");
			}
		});
		
	}		
}


