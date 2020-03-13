/**
 * Created by Vurt on 14-3-26.
 */
app.controller('AppController', ['$scope', '$rootScope', '$location','$http','SecurityFactory','$timeout','Modal',
    function ($scope, $rootScope, $location, $http,SecurityFactory,$timeout,Modal) {
		// 当前登录用户信息
		$scope.subject = SecurityFactory.getSubject();

		$scope.loadFinished=function(){
			 $('#indexloading').fadeOut(2000);
		};
		// 登出
		$scope.logout = function(){
			SecurityFactory.logout();
		};
		// 修改密码
		$scope.updatePassword = function(){
			Modal.open("f/userManage/updatePassword",function(data){
			});
		};
		$scope.menuClick = function(e,menu,ms){
			if(ms){
				$scope.breadcrumbArray[0] = menu.name;
				$scope.breadcrumbArray[1] = ms.name;
				e.stopPropagation();
			}else{
				if(menu.url){
					$scope.breadcrumbArray[0] = menu.name;
					$scope.breadcrumbArray.splice(1,1); 
				}
			}
		}
		$scope.sjjcIndex = 0;
		$scope.functions = {
    		showsjjc :function(){
	   	        $.ajax({
	   	            url:c2.base("service")+"dzjc_todoStatistics/v1/todoStatisticsList/selectRole",
	   	            type:"GET",
	   	            async: false,
	   	            dataType:"JSON",
	   	            success:function(data){
	   	              if(data=="1"){
	   	            	$http.post(c2.base("service")+"dzjc_tongji/v1/getSystemAreaCode").success(function(result){
							if(result.success){	
								//累计数据（不带时间）
								var SYSTEM_AREACODE_VALUE = result.SYSTEM_AREACODE_VALUE;
								if(IsNotSpace(SYSTEM_AREACODE_VALUE)&&'4300000000'==SYSTEM_AREACODE_VALUE){
									Modal.open('f/dzjc_tongji_shengsanjijiancha',{},function(){},function(){$scope.sjjcIndex--;});
								}else{
									Modal.open('f/dzjc_tongji_sanjijiancha',{},function(){},function(){$scope.sjjcIndex--;});
								}
							}else{
								Modal.open('f/dzjc_tongji_sanjijiancha',{},function(){},function(){$scope.sjjcIndex--;});
							}
						});
	   	            	$scope.sjjcIndex++;
	   	              }
	   	            }
	   	       });
    		},
			clickIndex :function(){
				$scope.breadcrumbArray=[];
			}
	    };
		
		$http.get("proxy/aip/v1/portal/web").success(function(data){
			//顶部应用数据
			/*不显示其他应用，2019-07-29注释掉*/
			/*$scope.allApps = [];
			angular.forEach(data.applications,function(group){
				angular.forEach(group.items,function(app){
					$scope.allApps.push(app);
				})
			})*/
			
			//左侧菜单数据
			var currentAppTitle = data.menu.title;
			if(IsNotSpace(data.menu.title) && data.menu.title.length > 8){
				currentAppTitle = currentAppTitle.substr(0,8);
			}
			$scope.currentMenuTitle = currentAppTitle;
			$scope.menus = data.menu.items;
	        
			//导航
			$scope.breadcrumbArray=[];
			var localHash = location.hash;
			$scope.menus.forEach(function(m){
				if(m.url === localHash){
					$scope.breadcrumbArray.push(m.name);
				}else if(m.items){
					m.items.forEach(function(sm){
						if(sm.url === localHash){
							m.open = true;
							$scope.breadcrumbArray.push(m.name);
							$scope.breadcrumbArray.push(sm.name);
							$timeout(function(){
								$("#"+m.name+"ul").show();
							})
						}
					})
				}
			});
	        $timeout(function(){
	        	$("#picScroll-left").slide({ titCell: ".hd ul", mainCell: ".bd ul", autoPlay: false, effect: "left", vis: 7, scroll: 1, autoPage: true, pnLoop: false, trigger: "click" });
	        })
	     });
		$("#sidebar-collapse i.ace-icon").click(function(){
			$("#sidebar").toggleClass("menu-min");
			if($('#systemname').is(':hidden')){//如果当前隐藏  
		        $('#systemname').show();//那么就显示div  
		    }else{//否则  
		        $('#systemname').hide();//就隐藏div  
		    } 
			//广播事件，通知列表重新计算宽度
			$scope.$broadcast("C2TableRecomputeWidth");
		})
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
		
		$rootScope.breadcrumbArray=[];
    }
]);