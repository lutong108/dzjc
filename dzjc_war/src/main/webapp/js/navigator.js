var CepNavigator = (function(){
  var cleanTemplate = "<div style='clear:both'></div>";
  var appCount = 0,appRowSize = 8,appIndex=0,appWidth=80;
  function getAppTemp(app){
    var appTemplate = "<div class=\"zonghesl\" title='"+app.name+"'>"+
      "<a href='"+app.url+"'>"+
        "<p class=\"slpic\"><img src='"+app.icon+"' width=\"60\" height=\"46\" /></p>"+
        "<p class='nowrap'>"+app.name+"</p>"+
      "</a>"+
    "</div>";
    return appTemplate;
  }
  function getMenuTemp(m){
	var url = m.url;
	var menuName = m.name;
	var menuClass = "gongzuolb";
	var zk = "";
	if(m.url)menuName = "<a href='"+m.url+"'>"+m.name+"</a>";
	if(m.expand)menuClass = "gongzuolb expand";
	if(m.items)zk = "<i class='fa zkicon'></i>";
    var menuTemplate = "<div class='"+menuClass+"'>"+
    "<div class='gongzuolbTOP'>"+
        "<i class='fa "+m.icon+"'></i>"+
        "<span class='gongzuolbTOPleft'>"+menuName+"</a></span>"+
        zk+
    "</div>";
    if(m.items){
      menuTemplate +="<div class='gzlblist'><div class='mini-title'>"+m.name+"</div><ul>";
      m.items.forEach(function(ms){
    	var classActive = ms.active?"active":"";
        menuTemplate+="<li menu='"+m.name +" / "+ ms.name+"' class="+classActive+"><a href='"+ms.url+"'><i class='fa "+ms.icon+" m-r-5'></i>"+ms.name+"</a></li>";
      });
      menuTemplate +="</ul></div>";
      menuTemplate += cleanTemplate;
    }
    menuTemplate += "</div>";
    return menuTemplate;
  }
  function init(config){
    if(config && config.url){
      $.get(config.url,function(data){
        //用户
        $("#cepNavigatorUser").text(data.user.name+"，欢迎您。");
        //入口
        data.applications.forEach(function(appGroup){
          appGroup.items.forEach(function(app){
            var at = getAppTemp(app);
            appCount++;
            $("#cepNavigatorApps").append(at).width(appWidth*appCount);
          })
        });
        $("#cepNavigatorApps").append(cleanTemplate);
        //当前应用
        $("#appName").text(data.appname);
        //左侧菜单
        var indexName = "<a href='/'>首页</a> / ";
        var localHash = location.hash;
        data.menu.items.forEach(function(m){
        	if(m.url === localHash)$(".weizhiLeft").html(indexName + m.name);
        	if(m.items){
        		m.items.forEach(function(sm){
        			if(sm.url === localHash){
        				m.expand = true;
        				sm.active = true;
        				$(".weizhiLeft").html(indexName + m.name +" / "+sm.name);
        			}
        		})
        	}
        });
        
        data.menu.items.forEach(function(m){
          var mt = getMenuTemp(m);
          $(".menu-subject").append(mt);
        });
        
        $(".zkicon").click(function(e){
          var m = $(e.target).parents('.gongzuolb');
          if(m.hasClass('expand')){
            m.removeClass('expand');
          }else{
            $(".gongzuolb").removeClass('expand');
            m.addClass('expand');
          }
        })

        $(".gongzuolb .gzlblist a").click(function(e){
          var liElement = $(e.target).parent();
          if(!liElement.hasClass('active')){
            $(".gongzuolb .gzlblist li").removeClass('active');
            liElement.addClass('active');
            var menu = liElement.attr("menu");
            $(".weizhiLeft").html(indexName + menu);
          }
        })
        
        $(".sousuoanniu").click(function(e){
        	$(".main").toggleClass("mini");
        })
      })
    }
    //应用翻页
    $("#cepNavigatorAppNext").click(function(){
      if(appIndex+appRowSize<appCount){
        appIndex++;
        $("#cepNavigatorApps").css("left","-"+appWidth*appIndex+"px");
      }
    });
    $("#cepNavigatorAppPre").click(function(){
      if(appIndex+appRowSize>appRowSize){
        appIndex--;
        $("#cepNavigatorApps").css("left","-"+appWidth*appIndex+"px");
      }
    });
  }
  return {
    init:init
  }
})();
