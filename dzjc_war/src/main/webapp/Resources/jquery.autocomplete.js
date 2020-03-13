jQuery.fn.AutoComplete = function() {
	var $obj = jQuery(this);
    // 自动补全
    var maxcount = 0;// 表示他最大的值
    var thisCount =0;// 初始化他框的位置
    jQuery("body").prepend("<div style='width:220px; display:none; background:#FFFFFF; position: absolute;border:1px solid #7f9db9;height:100px;overflow-y:auto;' id='autoTxt'></div>");
 //  $("body").prepend("<select style='width:120px; display:none; background:#FFFFFF; position: absolute;' id='autoTxt'></select>")
    $obj.keyup(function(even) {
        var v = even.which;
        if (v == 38 || v == 40 || v == 13)// 当点击上下键或者确定键时阻止他传送数据
            {
            return;
            }
        var txt = $obj.val();//这里是取得他的输入框的值
        if (txt != "") {
            //拼装数据
            jQuery.ajax({
                url : "ccapp/szhy/jsp/jbrQueryDo.jsp",//从后台取得json数据
                type : "post",
                dataType : "json",
                data : {"searchText" : $obj.val()},
                success : function(ls) {
                	if(ls.length == 0) return;
                    var offset = $obj.offset();
                    jQuery("#autoTxt").show();
                    jQuery("#autoTxt").css("top", (offset.top + 20) + "px");
                    jQuery("#autoTxt").css("left", offset.left + "px");
                    var Candidate = "";
                     maxcount = 0;//再重新得值
                    jQuery.each(ls, function(k, v) {
                        Candidate += "<li id='" +maxcount+ "' style='cursor:pointer;height:20px;'>" + "<span style='color:red'>"+this.jbr_cardid+"</span><span style='display:none'>"+this.jbr_number+"</span><span style='padding-left:10px;'>" + this.jbr_name + "</span></li>";
                        maxcount++;
                    });
                    jQuery("#autoTxt").html(Candidate);
                    jQuery("#autoTxt li:eq(0)").css("background", "#A8A5A5");
                    //高亮对象
             //       jQuery('body').highLight();
              //      jQuery('body').highLight(jQuery("#sele").val());
               //       even.preventDefault();
                        //当单击某个ＬＩ时反映
                        jQuery("#autoTxt li").click(function(){
                        	var tt = jQuery("#autoTxt li:eq("+this.id+")").find("span");
                            //调用页面的填充方法
                    		autoCompleteInput(tt.last().text(),tt.first().text(),tt.first().next().text());
                            jQuery("#autoTxt").html("");
                            jQuery("#autoTxt").hide();
                        });
                        //移动对象
                        jQuery("#autoTxt li").hover(function(){
                            jQuery("#autoTxt li").css("background", "#FFFFFF");
                            jQuery("#autoTxt li:eq("+this.id+")").css("background", "#A8A5A5");
                            thisCount=$obj.id;},function(){
                                jQuery("#autoTxt li").css("background", "#FFFFFF");});
                },
                error : function() {
                    jQuery("#autoTxt").html("");
                    jQuery("#autoTxt").hide();
                    maxcount = 0;
                }
            });
        } else {
            jQuery("#autoTxt").hide();
            maxcount = 0;
            jQuery("#sestart").click();
        }
    });
    //当单击ＢＯＤＹ时则隐藏搜索值
    jQuery("body").click(function(){
        jQuery("#autoTxt").html("");
        jQuery("#autoTxt").hide();
        thisCount=0;
    });
    // 写移动事件//上键３８ 下键４０ 确定键 １３
    jQuery("body").keyup(function(even) {
        var v = even.which;
        if(!(jQuery("#autoTxt").is(":hidden"))) {
            if (v == 38)// 按上键时
            {
                if(thisCount!=0){//等于零时则证明不能上了。所以获得焦点
                    $obj.blur();
                    if(thisCount>0)
                        --thisCount;
                    else
                        thisCount=0;
                jQuery("#autoTxt li").css("background", "#FFFFFF");
                jQuery("#autoTxt li:eq("+thisCount+")").css("background", "#A8A5A5").attr("tabindex",0).focus();
                move(thisCount);
                }else{$obj.focus();}
            } else if (v == 40) {// 按下键时
                if(thisCount<maxcount-1)
                {
                    $obj.blur();
                    ++thisCount;
                    jQuery("#autoTxt li").css("background", "#FFFFFF");
                    jQuery("#autoTxt li:eq("+thisCount+")").css("background", "#A8A5A5");
                    move(thisCount);
                }
            } else if (v == 13) {// 按确认键时
                var tt=jQuery("#"+thisCount).find("span");
                if(tt!="")
                    {
                        //$obj.val(tt);
                		//调用页面的填充方法
                		autoCompleteInput(tt.last().text(),tt.first().text(),tt.first().next().text());
                        jQuery("#autoTxt").html("");
                        jQuery("#autoTxt").hide();
                    }else
                    {
                        if($obj.val()!="")
                        jQuery("#sestart").click();
                    }
            } else {
                if(jQuery("#autoTxt").html()!="")
                    {
                        $obj.focus();
                        thisCount=0;
                    }
            }
        }
    });
    
    var move = function(thisCount){
        var selected = jQuery("#autoTxt li:eq("+thisCount+")");
        var itemHeight = selected.outerHeight();
        var itemTop = selected.position().top;
        if(itemHeight + itemTop > jQuery("#autoTxt").height())
           jQuery("#autoTxt").scrollTop(jQuery("#autoTxt").scrollTop() + itemTop + itemHeight - jQuery("#autoTxt").height());
        else if(itemTop < 0)
           jQuery("#autoTxt").scrollTop(jQuery("#autoTxt").scrollTop() + itemTop);
       }
}