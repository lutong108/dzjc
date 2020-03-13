directives.directive('c2Div', ['FormContainerFactory','$parse',function (FormContainerFactory,$parse) {
    return {
    	controller:function ($scope,$element,$attrs,$transclude){
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		
    		this.id = $attrs.id;
    		this.name = "div";
    		this.getDom = function(){
    			return $element;
    		}
    		this.show = function(){
    			$scope.$state[$attrs.id+"_hide"] = false;
    		}
    		this.hide = function(){
    			$scope.$state[$attrs.id+"_hide"] = true;
    		}
    		this.toggle = function(){
    			$scope.$state[$attrs.id+"_hide"] = !$scope.$state[$attrs.id+"_hide"];
    		}
    	},
        link: function (scope, element, attrs) {
        	if(attrs.hideVariable){
        		var hideValue = $parse(attrs.hideVariable)(scope);
        		scope.$watch(attrs.hideVariable,function(v){
        			if(angular.isUndefined(v))return ;
        			scope.$state[attrs.id+"_hide"] = v;
        		});
        	}
        }
    };
} ]);
directives.directive('c2Layout', ['FormContainerFactory','$ocLazyLoad','$timeout',function (FormContainerFactory,$ocLazyLoad,$timeout) {
    return {
    	scope:{beforeInit:"&"},
    	require:"?^c2LayoutPane",
    	controller: function ($scope,$element,$attrs,$transclude){
//    		ResInjector.addCss('ext/container/layout_border/lib/layout.css',$scope);
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id]=this;
            
    		$scope.layoutOptions = {
    			defaults:{
    				applyDefaultStyles: false,//应用默认样式  
        		    scrollToBookmarkOnLoad:true,//页加载时滚动到标签 
        		    showOverflowOnHover:false,//鼠标移过显示被隐藏的，只在禁用滚动条时用。
//        			contentSelector : ".ui-layout-content",
//        			contentIgnoreSelector : "ui-layout-ignore",//忽略的pane内容
//        			paneClass = "ui-layout-pane",//pane样式
//        			resizerClass = "ui-layout-resizer",
//        			togglerClass = "ui-layout-toggler",
        		    spacing_open:6,//展开时，边框宽度
        			spacing_closed:6,//收拢时，边框宽度
        			resizerTip : "可调整大小",
        			sliderTip : "显示",
        			togglerTip_open:"关闭",
        			togglerTip_closed :"打开",
        			sliderCursor : "pointer",
        			resizerDragOpacity :0.5,
        			maskIframesOnResize : true,
        			slideTrigger_open:"mouseover",//划开触发，("click", "dblclick", "mouseover")
        			slideTrigger_close : "mouseout",//划开收拢("click", "mouseout")
        			togglerLength_open : 50,//展开时的边框按钮长度
        			togglerLength_closed : 50,//关闭时的边框按钮长度
        			hideTogglerOnSlide : false,//
        			togglerAlign_open : "center",//展开时，触发关闭按钮位置
        			togglerAlign_closed : "center",//关闭时，触发展开按钮位置
        			togglerContent_open : "",//pane打开时，边框按钮中需要显示的内容可以是符号"<"等。需要加入默认css样式.ui-layout-toggler .content
        			togglerContent_closed:"",//pane关闭时，同上。
        			enableCursorHotkey : true//是否启用快捷键CTRL或shift + 上下左右
//        			fxName:"none",//打开关闭的动画效果("none", "slide", "drop", "scale", customer effect name)
//        			fxSpeed : "slow"//动画速度("fast", "normal", "slow", integer - ms)
//        			fxSettings = {},//自定义动画效果
    			}
    		}
    		this.setPane = function(paneName,options){
    			$scope.layoutOptions[paneName] = options;
    		}
    		
    		this.id = $attrs.id;
    		this.name = "layout";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getLayout = function(){
    			return $scope.layout;
    		}
    		this.getOptions = function(){
    			return $scope.layoutOptions;
    		}
    		//"north", "south", "east" or "west".
    		this.getLayoutPane = function(paneName){
    			return $scope.layout.panes[paneName];
    		}
    		this.getLayoutState = function(){
    			return $scope.layout.state;
    		}
    		this.toggle = function(paneName){
    			$scope.layout.toggle(paneName);
    		}
    		this.open = function(paneName){
    			$scope.layout.open(paneName);
    		}
    		this.close = function(paneName){
    			$scope.layout.close(paneName);
    		}
    		this.hide = function(paneName){
    			$scope.layout.hide(paneName);
    		}
    		this.show = function(paneName,openPane){
    			$scope.layout.show(paneName,openPane);
    		}
    		this.sizePane = function(paneName,size){
    			$scope.layout.sizePane(paneName,size);
    		}
    	},
        link: function (scope, element, attrs, controllers) {
        	if(controllers){//判断是否为嵌套布局
        		controllers.setChild(scope.layoutOptions);
        		element.parent().append(element.children());
        		element.remove();
        	}else{//最外层布局，开始初始化
//        		ResInjector.addJsSync("ext/container/layout_border/lib/jquery.layout.js");
        		$ocLazyLoad.load({
        			serie: true,
        			files:[
            			'ext/container/layout_border/lib/layout.css',
            			"assets/jquery-ui/ui/plugin.js",
    	                "assets/jquery-ui/ui/safe-active-element.js",
    	                "assets/jquery-ui/ui/safe-blur.js",
    	                "assets/jquery-ui/ui/widgets/mouse.js",
    	                "assets/jquery-ui/ui/widgets/draggable.js",
            			"ext/container/layout_border/lib/jquery.layout.js"]
        		}).then(function(){
        			scope.beforeInit({layoutOptions:scope.layoutOptions});
        			//布局在弹出框的页面内不初始化，所以加$timeout
    	        	$timeout(function(){
        				var layout = element.layout(scope.layoutOptions);
        				scope.layout = layout;
        			});
        		});
        	}
        }
    };
} ]);
directives.directive('c2LayoutPane', ['FormContainerFactory','$parse',function (FormContainerFactory,$parse) {
	return {
		scope:{
			minSize:"=",
			maxSize:"=",
			initHidden:"=",
			initClosed:"=",
			closable:"=",
			resizable:"=",
			slidable:"=",
			onopen:"&",
			onclose:"&",
			onshow:"&",
			onhide:"&",
			onresize:"&"
		},
		require:"^c2Layout",
		controller:function ($scope,$element,$attrs){
			$scope.paneOptions = {
					initHidden:$scope.initHidden,
	    			initClosed :$scope.initClosed,
	    			closable : $scope.closable,
	    			resizable : $scope.resizable,
	    			slidable : $scope.slidable,
	        		paneSelector : ".ui-layout-"+$attrs.pane,//指定pane
	    			size : $attrs.size,//auto;num;
	    			minSize : $scope.minSize,
	    			maxSize : $scope.maxSize,//0为无限大	
	    			showOverflowOnHover:false,
	    			onopen : function(paneName,paneElement,paneState,paneOptions,layoutName){
	    				$scope.onopen({paneName:paneName,paneState:paneState,paneOptions:paneOptions,layoutName:layoutName});
	    			},
	    			onclose : function(paneName,paneElement,paneState,paneOptions,layoutName){
	    				$scope.onclose({paneName:paneName,paneState:paneState,paneOptions:paneOptions,layoutName:layoutName});
	    			},
	    			onshow : function(paneName,paneElement,paneState,paneOptions,layoutName){
	    				$scope.onshow({paneName:paneName,paneState:paneState,paneOptions:paneOptions,layoutName:layoutName});
	    			},
	    			onhide : function(paneName,paneElement,paneState,paneOptions,layoutName){
	    				$scope.onhide({paneName:paneName,paneState:paneState,paneOptions:paneOptions,layoutName:layoutName});
	    			},
	    			onresize : function(paneName,paneElement,paneState,paneOptions,layoutName){
	    				$scope.onresize({paneName:paneName,paneState:paneState,paneOptions:paneOptions,layoutName:layoutName});
	    			}
				}
			
			this.id = $attrs.id;
    		this.name = "layoutPane";
			this.setChild = function(childOptions){
				$scope.paneOptions.childOptions = childOptions;
			}
		},
		link:function(scope,element,attrs,layoutController){
			if(attrs.pane=="top")layoutController.setPane("north",scope.paneOptions);
			if(attrs.pane=="left")layoutController.setPane("west",scope.paneOptions);
			if(attrs.pane=="right")layoutController.setPane("east",scope.paneOptions);
			if(attrs.pane=="bottom")layoutController.setPane("south",scope.paneOptions);
			if(attrs.pane=="center")layoutController.setPane("center",scope.paneOptions);
		}
	};
} ]);directives.directive('c2TabContent', [ '$compile', 'FormContainerFactory',
	function($compile, FormContainerFactory) {
		return {
//			scope : true,
//			require:["?^tab","c2TabContent"],
			controller:function ($scope,$element,$attrs,$transclude){
				var form = FormContainerFactory.findForm($scope);
				if(form&&$attrs.id)form[$attrs.id] = this;
	    		
	    		this.id = $attrs.id;
	    		this.name = "tabContent";
	    		this.getDom = function(){
	    			return $element;
	    		}
	    		this.getActiveState = function(){
	    			return $scope.$state[$attrs.id+"_actived"];
	    		}
	    		this.setActive = function(){
	    			$scope.$state[$attrs.id+"_actived"] = true;
	    		}
	    		this.getDisabledState = function(){
	    			return $scope.$state[$attrs.id+"_disabled"];
	    		}
	    		this.setDisabledState = function(b){
	    			$scope.$state[$attrs.id+"_disabled"] = b;
	    		}
			},
			link : function(scope, element, attrs) {
				scope.$watch(function() {
					return element.parent().attr('class');
				}, function(newValue) {
					if (newValue.indexOf('active') >= 0) {
						//主要是向下广播给表格控件，用于表格重置宽度
						scope.$broadcast('tabActived',attrs.id);
						//主要向上广播给权限判断容器，用于tab页签内容的权限判断
						scope.$emit('tabActived',attrs.id);
					}
				});
			}
		}
	} ]);
directives.directive('c2Jqgrid', ['FormContainerFactory','$timeout','$http','$parse','UIDirectiveService',
                                  'Messenger','Modal','$compile','$rootScope','$q','Upload','$ocLazyLoad',
    function (FormContainerFactory,$timeout,$http,$parse,UIDirectiveService,Messenger,Modal,$compile,$rootScope,$q,Upload,$ocLazyLoad) {
	return {
        scope: {
            id: "@",
            bindData: "=",
            paramData:"=",
            treeGrid:"=",
            expandColClick:"=",
            firstRequest:"=",
            multiselect: "=",
            multiSort: "=",
            autowidth: "=",
            width: "@tableWidth",
            shrinkToFit:"=",
            height: "@tableHeight",
            computeHeight: "=",
            computeHeightFix: "=",
            rownumbers: "=",
            pageSize: "=",
            scroll: "=",
            pagerView:"=",
            footerrow:"=",
            
            subGrid:"=",
            subGridModel:"=",
            subGridUrl:"@",
            subGridRowExpanded:"&",
            
            grouping:"=",
            groupField : "@",
			groupOrder:"@",
			groupText:"@",
			groupColumnShow:"=",
			groupSummary:"=",
            
            dbcEdit: "=",
            inlineEditAutoSave: "=",
            dnd: "=",//是否拖拽排序
            dragHandle:"@",
            onDrop:"&",
            
            topToolbar: "=",
            pageToolbar: "=",
            pageToolbarText: "=",
            
            beforeInit:"&",
            afterInit:"&",
            afterInsertRow:"&",
            beforeProcessing:"&",
            beforeRequest:"&",
            serializeGridData:"&",
            beforeSelectRow:"&",
            loadBeforeSend:"&",
            loadComplete:"&",
            gridComplete:"&",
            loadError:"&",
            onRightClickRow:"&",
            ondblClickRow:"&",
            onSelectAll:"&",
            onSelectRow:"&",
            onSortCol:"&",
            rowattr:"&",
            onPaging:"&",
            
            importSuccessCallback:"&",
            
            colDictUrl:"="
        },
        controller: function ($scope,$element,$attrs,$transclude){
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		var controller = this;
    		//处理表格ID重复（tab或者弹出框，如果有重复，则在id后面加数字）
        	function repetitionId(id,count){
        		if($("[id='"+id+count+"']").length>0){
        			return repetitionId(id,count+1);
        		}else{
        			return count;
        		}
        	}
        	var jqgridCount = $("[id='"+$attrs.id+"']").length;
        	if(jqgridCount>1)$element.attr("id",$attrs.id+repetitionId($attrs.id,1));
    		
    	    //表格所有数据主键的数组
    		$scope.colModel = new Array();
    		$scope.customTypeCol = new Array();
    		$scope.editVerify = new Array();
    		controller.hasCustomFormatter = false;
    		controller.tableNewRowIdPre = "table-new-row";
    		controller.tableNewRowIdNum = 0;
    		this.setColModel = function(colModel){
    			$scope.colModel.push(colModel);
    		}
    		this.setCustomTypeCol = function(col){
    			$scope.customTypeCol.push(col);
    		}
    		this.setEditVerify = function(col){
    			$scope.editVerify.push(col);
    		}
    		
    		this.id = $attrs.id;
    		this.name = "jqgrid";
    		this.getDom = function(){
    			return $element;
    		}
    		this.init = function(options){
    			if(options)$scope.options = options;
    			$scope.jqGridObj = $element.jqGrid(options);
    	    }
    		this.getOptions = function(){
    			return $scope.options;
    		}
    	    this.getObject = function () {
    	        return $scope.jqGridObj;
    	    }
    	    this.getTableData = function(){
    	    	return $scope.tableData;
    	    }
    	    /**
    	     * 刷新列表
    	     * param:表格查询传递的参数对象{xx:xx,xxx:xxx}
    	     */
    	    this.refresh = function (param) {
    	    	if(angular.isDefined(param)){
    	    		var pd = $element.jqGrid("getGridParam","postData");
    	        	for(p in pd){
    	        		delete pd[p];
    	        	}
    	        	$element.jqGrid("setGridParam", {postData:param});
    	        	$element.jqGrid("setGridParam", {page:1}).trigger("reloadGrid");
    	    	}else{
    	    		$element.jqGrid().trigger("reloadGrid");
    	    	}
    	    	controller.initDataOprations() ;
    	    }
    	    this.addParams = function(params,fresh){
    	    	var pd = $element.jqGrid("getGridParam","postData");
    	    	angular.forEach(params,function(value,key){
    	    		pd[key]=value;
    	    	});
    	    	if(angular.isUndefined(fresh))fresh=true;
    	    	if(fresh){
    	    		$element.jqGrid("setGridParam", {page:1}).trigger("reloadGrid");
    	    		controller.initDataOprations() ;
    	    	}
    	    }
    	    this.removeParam = function(paramName,fresh){
    	    	var pd = $element.jqGrid("getGridParam","postData");
    	    	for(p in pd){
    	    		if(p==paramName)delete pd[p];;
    	    	}
    	    	if(angular.isUndefined(fresh))fresh=true;
    	    	if(fresh){
    	    		$element.jqGrid("setGridParam", {page:1}).trigger("reloadGrid");
    	    		controller.initDataOprations() ;
    	    	}
    	    }
    	    this.setParams = function(params,fresh){
    	    	if(angular.isDefined(params)){
    	    		var pd = $element.jqGrid("getGridParam","postData");
    	        	for(p in pd){
    	        		delete pd[p];
    	        	}
    	        	$element.jqGrid("setGridParam", {postData:params});
    	    	}
    	    	if(angular.isUndefined(fresh))fresh=true;
    	    	if(fresh){
    	    		$element.jqGrid("setGridParam", {page:1}).trigger("reloadGrid");
    	    		controller.initDataOprations() ;
    	    	}
    	    }
    	    /**
    	     * 手动设置当前表格的主数据，当前表格必须是从表该API才有效
    	     */
    	    this.setPrimaryData = function(pkValue){
    	    	if(angular.isDefined($scope.bindData.$fkMap)){
    	    		//必须要是从表才有效
    	    		var fk = $scope.bindData.$fkMap.fk;//从表外键的key
    	         	//就算外键值还没有获取到，也要设置表格的参数，最好是一个无法查询出任何结果的值
    	         	//不然表格中可能会短暂的显示不应该看到的数据
    	         	var parentParamString = "{\""+fk+"\":\""+pkValue+"\"}";
    	         	this.setForeignKey(angular.fromJson(parentParamString));
    	    	}
    	    }
    	    this.removePrimaryData = function(){
    	    	this.setPrimaryData("___");
    	    }
    	    /**
    	     * 设置表格外键，{fkKey:fkValue}，内部接口，不推荐用户自己调用
    	     */
    	    this.setForeignKey = function(fk){
    	    	this.fk=fk;
    	    	this.addParams(fk);
    	    }
    	    //resetSelection(rowid) 取消选中，如果rowid取消一个，如果空，取消全部
    	    //setSortIcon : function(colname, position)
    	    //progressBar : function ( p )
    	    //setFrozenColumns : function ();destroyFrozenColumns :  function()
    	    //resizeColumn : function (iCol, newWidth)
    	    //getStyleUI : function( styleui, classui, notclasstag, gridclass)
    	    //resizeGrid : function (timeout)
    	    
    	    //getGridParam: totaltime:加载时间
    	    var jqGridMethods = ["getGridParam","getDataIDs",
    	        "setSelection","resetSelection","getRowData","setRowData","addRowData",
    	        "hideCol","showCol","remapColumns",
    	        "setLabel","setCell","getCell","getCol","clearGridData","getInd",
    	        "setFrozenColumns","destroyFrozenColumns","resizeColumn","resizeGrid",
    	        "footerData",
//    	        "delRowData","footerData","getStyleUI","setCaption",
    	        //grid.inlinedit.js
    	        //showAddEditButtons : function()
    	        "editRow","saveRow","restoreRow","addRow"
    	        ];
    	    
         	angular.forEach(jqGridMethods,function(methodName){
         		controller[methodName] = function(){
         			var fn = $.jgrid.getMethod(methodName);
         			var args = $.makeArray(arguments);
         			return fn.apply($element,arguments);
         		}
         	});
    		this.recomputeWidth = function(width,shrink){
    			$element.jqGrid('setGridWidth', width,shrink);
    	    }
    		this.recomputeHeight = function(height){
    	    	$element.jqGrid('setGridHeight', height);
    	    }
    		//设置第几页和每页显示行数
    	    this.setPageAndRowNum = function (page, rows) {
    	        $element.jqGrid("setGridParam", {page: page, rowNum: rows}).trigger("reloadGrid");
    	        controller.initDataOprations() ;
    	    }
    	    this.setGridParam = function(gridParam,isReloadGrid){
    	    	isReloadGrid?$element.jqGrid("setGridParam",gridParam).trigger("reloadGrid"):$element.jqGrid("setGridParam",gridParam);
    	    }
    	  //获取表格信息。param:"rowNum"-每页记录条数 ",page"-当前第几页,"records"-总共有多少条,"postData"-请求参数...
    	    //获取单个选中的行的主列值，如果选中多个这返回最后选中的值
    	    this.getSingleSelectRowId = function () {
    	        return $element.jqGrid("getGridParam", "selrow");
    	    }
    	    //获取所有选中的行的主列值，返回数组
    	    this.getSelectRowIds = function () {
    	        return $element.jqGrid("getGridParam", "selarrrow");
    	    }
    	    //返回单个选中的行数据
    	    this.getSingleSelectData = function () {
    	        var rowId = $element.jqGrid("getGridParam", "selrow");
    	        return $element.jqGrid("getRowData", rowId);
    	    }
    	    //返回所有选中行数据的数组
    	    this.getSelectDatas = function () {
    	        var rowIds = $element.jqGrid("getGridParam", "selarrrow");
    	        var selectDatas = new Array();
    	        angular.forEach(rowIds, function (value, key) {
    	            var rowData = $element.jqGrid("getRowData", value);
    	            selectDatas.push(rowData);
    	        });
    	        return selectDatas;
    	    }
        	//启用和禁用排序
            this.setSortRow = function(sortable,onDrop,dragHandle){
            	if(sortable){
            		//如果没定义事件，则使用表格配置的事件
            		var tableDndOptions = {onDragClass:"drag-tr",dragHandle:dragHandle};
            		if(typeof onDrop =="function")tableDndOptions.onDrop = onDrop;
            		else if(onDrop == "angularFun" && $attrs.onDrop){
            			tableDndOptions.onDrop = function(table,row){
            				eval("$scope.$parent."+$attrs.onDrop);
            			}
            		}
            		$element.tableDnD(tableDndOptions);
            	}else{
            		jQuery(".jqgrow").css("cursor","default");
            		jQuery(document).unbind('mousemove');
            		jQuery(document).unbind('mouseup');
            	}
            }
            this.editRow = function(rowid, editparameters){
            	editparameters = editparameters || {};
            	$element.jqGrid("editRow",rowid, editparameters);
            }
            this.delRowData = function(rowid){
            	var isSuccess = $element.jqGrid("delRowData", rowid);
            	if(isSuccess){//同步操作数据
            		angular.forEach(controller.dataOprations.add,function(v,i){
            			if(v._tableAddRowId == rowid)controller.dataOprations.add.splice(i,1);
            		});
            		angular.forEach(controller.dataOprations.update,function(v,i){
            			if(v[$scope.keyColumn] == rowid)controller.dataOprations.update.splice(i,1);
            		});
            		if(angular.isString(rowid)&&rowid.indexOf(controller.tableNewRowIdPre)!=0){
            			var o = new Object();
                		o[$scope.keyColumn] = rowid;
                		controller.dataOprations["delete"].push(o);
            		}
            	}
            	return isSuccess;
            }
            //表格操作数据
            this.initDataOprations = function(){
    			controller.dataOprations = {update:[],add:[],"delete":[]};
    		}
            this.getDataOprations = function(){
    			return controller.dataOprations;
    		}
            //获取表格工具栏中的按钮
            this.getTableNavButtons = function(){
            	return $scope.tableNavButtons;
            }
            this.getTableTopButtons = function(){
            	return $scope.tableTopButtons;
            }
            this.getTableButtonActions = function(){
            	return controller.tableButtonActions;
            }
            this.getTableNewRowId = function(){
            	var newId = controller.tableNewRowIdPre+controller.tableNewRowIdNum;
            	controller.tableNewRowIdNum++;
            	return newId;
            }
        },
        link: function (scope, element, attrs,controller) {
        	var realFormScope=FormContainerFactory.findFormScope(scope);
    		//表格高度
        	var tableHight = scope.height;
        	if(scope.computeHeight){
        		var containerHight = element.parents('.fixed-height-container').height();
//        		var tHight = $("#t_"+scope.id).height();
        		var tableExpHeight = 108;
        		if(scope.topToolbar)tableExpHeight=tableExpHeight+30;
//        		if(scope.filterToolbar)tableExpHeight=tableExpHeight+30;
            	tableHight = containerHight-tableExpHeight-scope.computeHeightFix;
        	}
        	
        	var pagerpos = "left";
        	if(scope.pageToolbar)pagerpos = "center";
        	//表格列如果没有定义主键列，默认的主键。属性默认值"id";$index表示按序号作为主键
        	var jsonReaderId = attrs.tableKey;
        	if(attrs.tableKey == "$index")jsonReaderId="";
        	
            var firstLoad=true;
            
            //数据字典数据
            scope.dictData = {};
            angular.forEach(scope.colModel,function(col){
            	if(col.dictType){
            		if(angular.isUndefined(scope.colDictUrl))scope.colDictUrl="ws/getSimpleDictDateMap";
            		$http.post(scope.colDictUrl,{dictTypeNames:col.dictType}).success(function(data){
    	    			scope.dictData[col.dictType] = data.result[col.dictType];
    	    		}) ;
            	}
            });
            
            //forceFit true:调整列宽不改变表格宽度
            //datatype function() 可以任意发送请求
            var options = {
            	/*请求*/
            	ajaxGridOptions : { contentType: 'application/json; charset=utf-8'},
            	mtype: attrs.mtype,
//            	url: jqgridUrl,
//            	data:scope.bindData,
//    			datatype: jqgridDatatype,//json,clientSide
    			postData:scope.paramData,
    			editurl: attrs.editurl,
    			cellEdit: false,
                cellsubmit: 'clientArray',//remote and clientArray
                cellurl: "",
//				datastr
    			/*表格定义*/
            	styleUI : 'Bootstrap',
//				caption: attrs.caption,//标题，不使用jqGrid的标题显示，显示到表头工具栏左边
				autowidth:scope.autowidth,
				height:tableHight,
				width:scope.width,
				rownumbers:scope.rownumbers&&!scope.treeGrid,//显示序号
				rownumWidth:28,
				hoverrows:true,//行鼠标悬浮（mouse hovering over）效果 (可以在bootstrap设置用table-hover代替)
				altRows: true, //条纹(可以在bootstrap设置用table-striped代替)
                altclass: "ui-priority-secondary",
                shrinkToFit: scope.shrinkToFit,//此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度。!!!冻结列时必须设置为false!!
                forceFit: false,//调整列宽度不会改变表格的宽度,设为true时，分组调整列宽有问题
                hiddengrid: false,//当为ture时，表格不会被显示，只显示表格的标题。只有当点击显示表格的那个按钮时才会去初始化表格数据。
                prmNames:{page:"page",rows:"rows", sort:"sidx", order:"sord", search:"_search", nd:"nd", id:"id", oper:"oper", editoper:"edit", addoper:"add", deloper:"del", subgridid:"id", npage:null, totalrows:"totalrows"},
                cellLayout:1,//This option determines the padding + border width of the cell. Usually this should not be changed, but if custom changes to the td element are made in the grid css file, this will need to be changed. The initial value of 5 means paddingLef(2) + paddingRight (2) + borderLeft (1) = 5
                autoencode:true,//防止代码注入
                gridview:false,//设为true后可以获得更快的表格加载速度，但是不能使用treeGrid, subGrid, or the afterInsertRow event.
//              hidegrid: true,//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
//				gridstate:"visible",//定义当前表格的状态：'visible' or 'hidden' 推荐div控制
//              idPrefix:"",//When set, this string is added as prefix to the id of the row when it is built.
                ignoreCase:false,// local search and sorting case-sensitive(区分大小写)
                loadonce:false,//If this flag is set to true, the grid loads the data from the server only once (using the appropriate datatype). After the first request, the datatype parameter is automatically changed to local and all further manipulations are done on the client side. The functions of the pager (if present) are disabled.
//              resizeclass:"",//为可改变宽度的列头添加一个样式名
                /*列定义*/
//				colNames:colNames,//定义列头名称，已在colModel里面设置
                colMenu : true,//是否开启表头菜单
                colModel:scope.colModel,
                headertitles:true,//表头是否title属性
                /*行定义*/
                /*多选*/
				multiselect: scope.multiselect,//是否能够多选
				multiboxonly: true,//需要点击checkbox才能多选
				multiselectWidth:28,//多选checkbox 宽度
//				multikey: "ctrlKey",
				/*排序*/
				sortable:false,//When set to true, this option allows reordering columns by dragging and dropping them with the mouse. Since this option uses the jQuery UI sortable widget, be sure to load this widget and its related files in the HTML head tag of the page. Also, be sure to select the jQuery UI Addons option under the jQuery UI Addon Methods section while downloading jqGrid if you want to use this facility.???没效果
				sortname:attrs.sortname,
				sortorder:attrs.sortorder,
				multiSort:scope.multiSort,//多列排序
				viewsortcols:[false,'vertical',true], //设置排序图标显示和排序点击方式 The first parameter determines if sorting icons should be visible on all the columns that have the sortable property set to true. Setting this value to true could be useful if you want to indicate to the user that (s)he can sort on that particular column. The default of false sets the icon to be visible only on the column on which that data has been last sorted. Setting this parameter to true causes all icons in all sortable columns to be visible.The second parameter determines how icons should be placed - vertical means that the sorting arrows are one under the other. 'horizontal' means that the arrows should be next to one another. The third parameter determines the click functionality. If set to true the data is sorted if the user clicks anywhere in the column's header, not only the icons. If set to false the data is sorted only when the sorting icons in the headers are clicked. 
				/*分页*/
				scroll:scope.scroll,//滚动条代替翻页,[height]属性不能为“100%”或者“auto”。发现横向滚动条的问题！！
				scrollrows:false,//设置选中行时，让选中行滚动到视野范围上
				scrollOffset:18,//滚动条宽度
				scrollTimeout:200,//This controls the timeout handler when scroll is set to 1.
				page:1,//初始第几页
				rowNum:scope.pageSize,
				rowList:[scope.pageSize,scope.pageSize*2,50,100],
//				pager : "#grid-pager-"+scope.id, //分页栏，后面动态定义
				pagerpos:pagerpos,//指定分页栏的位置
				pgbuttons:true,//是否显示翻页按钮
				pginput:true,//是否显示跳转页面的输入框
				viewrecords : true,//是否显示总记录数
//				pgtext:"第{0}页\u3000共{1}页",//在表格底部显示当前页信息
				toolbar:[scope.topToolbar,"top"],//表格的工具栏。数组中有两个值，第一个为是否启用，第二个指定工具栏位置（相对于body layer），如：[true,”both”] 。工具栏位置可选值：“top”,”bottom”, “both”. 如果工具栏在上面，则工具栏id为“t_”+表格id；如果在下面则为 “tb_”+表格id；如果只有一个工具栏则为 “t_”+表格id
				toppager:false,//分页栏是否放在顶部
				onPaging:function(pgButton){//分页按钮触发事件，返回"stop"终止分页。
					return scope.onPaging({pgButton:pgButton});
				},
                /*行编辑*/
				inlineData:{},//an array used to add content to the data posted to the server when we are in inline editing.
				/*分组*/
				grouping:scope.grouping,//分组+++
				groupingView : {
					groupField : [scope.groupField],
					groupOrder:[scope.groupOrder],
					groupText:[scope.groupText],
					groupColumnShow:[scope.groupColumnShow],
					groupSummary:[scope.groupSummary],
					groupSummaryPos:['footer'],
					hideFirstGroupCol:false,
					groupCollapse:false,
					showSummaryOnHide:false,
					plusicon:"glyphicon-chevron-right",
					minusicon:"glyphicon-chevron-down",
					groupDataSorted : true 
				}, 
				/*子表*/
				subGrid:scope.subGrid,//是否开启子表
				subGridOptions:{
					plusicon : "glyphicon-chevron-right",
					minusicon : "glyphicon-chevron-down",
					openicon : "glyphicon-indent-left",
					expandOnLoad:false, //是否默认全展开
					selectOnExpand :false,//是否展开就选中
					reloadOnExpand :false//是否展开重新加载
				},
				subGridModel:scope.subGridModel,
				subGridType:"json",
				subGridUrl:scope.subGridUrl,
				subGridWidth:28,
				subGridRowExpanded:function(parentRowID,parentRowKey){
					scope.subGridRowExpanded({parentRowID:parentRowID,parentRowKey:parentRowKey});
				},
                /*树表*/
				treeGrid: scope.treeGrid,
				treeGridModel:"adjacency",
				ExpandColClick:scope.expandColClick,//是否点击文本展开，如果为false,则只能点击图标展开
				ExpandColumn:attrs.expandColumn,//点击展开行
				treeIcons:{ 
					plus: "fa fa-caret-right bigger-140", // 折合状体 
					minus: "fa fa-caret-down bigger-140", // 展开状态 
					leaf: "fa fa-circle-o smaller-80" // 叶子 
				},
				tree_root_level:0,
				treeReader:{
				   level_field: "level",
				   parent_id_field: "pid",
				   leaf_field: "isLeaf",
				   expanded_field: "open"
				},
				footerrow:scope.footerrow,//是否在表格列最后添加一行，可以使用footerData("set",{})设置行数据
//				userData:,//从request中取得的一些用户信息
				userDataOnFooter:false,//当为true时把userData放到底部，用法：如果userData的值与colModel的值相同，那么此列就显示正确的值，如果不等那么此列就为空
				deepempty:false,//This option should be set to true if an event or a plugin is attached to the table cell. The option uses jQuery empty for the the row and all its children elements. This of course has speed overhead, but prevents memory leaks. This option should be set to true if a sortable rows and/or columns are activated.
//				deselectAfterSort:true,//只有当datatype为local时起作用。当排序时不选择当前行
//				direction:"ltr",//表格中文字的显示方向，从左向右（ltr）或者从右向左（rtr）
//		        cmTemplate:
//				emptyrecords:"没有记录！",//当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
//				selarrrow:[],//只读属性，用来存放当前选择的行
//				selrow://只读属性，最后选择行的id
//				xmlReader:
                //改传入数据格式
				jsonReader: {
					root:"contents",
					id:jsonReaderId,
					page:"pageIndex",
					records:"total",
					total:"totalPage",
					repeatitems: false,
					cell: "",
					userdata: "userdata",
					subgrid: {
					   root:"contents", 
				       repeatitems: false, 
				       cell:""
				    }
				},
				afterInsertRow:function(rowid,rowdata,rowelem){
					if(controller.hasCustomFormatter){
						var rowScope = realFormScope.$new(false);
						rowScope.rowObject = rowdata;
						var trElement = controller.getInd(rowid,true);
						rowScope.dictData = scope.dictData;
						$compile(trElement)(rowScope);
					}
					scope.afterInsertRow({rowid: rowid, rowdata: rowdata, rowelem: rowelem});
				},
				beforeProcessing:function(data,status,xhr){
					//替换表格内容中的换行。！！！有没有必要？？？
					angular.forEach(data.contents,function(row,n){
						angular.forEach(row,function(v,k){
							if(angular.isString(v)&&v.indexOf("\n")>0)row[k]=v.replace(/\n/g,' ');
						});
					});
					scope.beforeProcessing({data: data, status: status, xhr: xhr});
				},
				beforeRequest:function(){
					 //restURL参数替换,每次刷新改变参数也能生效
					var baseUrl = scope.bindData.$url;
					var gridPostData = controller.getGridParam("postData");
		            var restUrl = UIDirectiveService.replacePathParams(baseUrl,gridPostData);
		            if(restUrl != baseUrl)controller.setGridParam({url:restUrl});
					
                    var result= UIDirectiveService.invoke(attrs.beforeRequest, scope.beforeRequest, {},true);
                    //是否第一次加载，控制初始化请求
                    if(firstLoad){
                    	firstLoad = false;
                    	return scope.firstRequest&&result;
                    }else{
                    	return true&&result;
                    }
				},
				beforeSelectRow:function(rowid,e){
					var params = {rowid:rowid, e:e};
					return UIDirectiveService.invoke(attrs.beforeSelectRow, scope.beforeSelectRow, params, true);
				},
				loadBeforeSend:function(xhr,settings){
					scope.loadBeforeSend({xhr: xhr, settings: settings});
				},
				gridComplete:function(){
					if(scope.dnd){
						controller.setSortRow(true,"angularFun",scope.dragHandle);
					}
    				if(attrs.gridComplete){
                		scope.gridComplete();
    				}
				},
				loadComplete:function(data){
					scope.loadComplete({data: data});
					scope.tableData = data;
					//计算表格最后一行是否超出，已通过修改源码解决。
//					controller.fixGridWidth();
					//检查页面是否出现横向滚动条,如果超出resizeGrid
					//表格分组不会调用resizeGrid,调用后会出现列对不齐的情况。如果出现横向滚动条，可以通过在表格初始化前设置表格父容器"$('#'+id+'Wrapper')"高度解决。
              		var pw = element.parents("#"+attrs.id+"Wrapper");
              		if(pw.width()<element.width()&&!scope.isTableGroupHeader)controller.resizeGrid(0);
					//合并单元格
					function merger(mergerColName,compareColName) {
						if(!compareColName)compareColName = mergerColName;
					    //得到显示到界面的id集合
					    var mya = controller.getDataIDs();
					    //当前显示多少条
					    var length = mya.length;
					    var megerd=1;
					    for (var i = 0; i < length; i+=megerd) {
					    	var rowSpanTaxCount=1;
					        //从上到下获取一条信息
					        var before = controller.getRowData(mya[i]);
					        //定义合并行数
					        for (j = i + 1; j <= length; j++) {
					            //和上边的信息对比 如果值一样就合并行数+1 然后设置rowspan 让当前单元格隐藏
					            var end = controller.getRowData(mya[j]);
					            if (before[compareColName] == end[compareColName]) {
					                rowSpanTaxCount++;
					                megerd++;
					                $(controller.getDom()).jqGrid().setCell(mya[j], mergerColName, '', { display: 'none' });
					            } else {
					                rowSpanTaxCount = 1;
					                megerd = 1;
					                break;
					            }
					            $($("[aria-describedby="+scope.id+"_"+mergerColName+"]").get(i)).attr("rowspan", rowSpanTaxCount);
					        }
					    }
					}
					
					angular.forEach(scope.colModel,function(col,n){
						if(col.isMergerColumn){
							merger(col.name,col.compareColName);
						}
					});
					
					if(controller.hasCustomFormatter)scope.$apply();
				},
				loadError:function(xhr,status,error){
					scope.loadError({xhr: xhr, status: status, error: error});
				},
				onRightClickRow:function(rowid,iRow,iCol,e){
					scope.onRightClickRow({rowid: rowid, iRow: iRow, iCol: iCol, e:e});
				},
				onSelectAll:function(aRowids,status){
					scope.onSelectAll({aRowids: aRowids, status: status});
				},
				onSelectRow:function(rowid,status,e){
					scope.onSelectRow({rowid: rowid, status: status, e:e});
				},
				onSortCol:function(index,iCol,sortorder){
					scope.onSortCol({index: index, iCol: iCol, sortorder: sortorder});
				},
				//This event is called when the new table row is inserted. It can be used to set additional style and class attributes of the row dynamically. The event should return a object something like this {“style” : “somestyle”, “class”: “someclass”}.
				rowattr:function(rowData,currentObj,rowId){
					scope.rowattr({rowData: rowData, currentObj: currentObj, rowId: rowId});
				}
				//表格事件执行顺序:1.beforeRequest;2.loadBeforeSend;3.serializeGridData;4.loadError;5.beforeProcessing;6.gridComplete;7.loadComplete;
			};
            if(attrs.serializeGridData){
            	options.serializeGridData = function(postData){
            		scope.serializeGridData({postData:postData});
            	}
            }else{//更改传出数据格式
            	options.serializeGridData = function(postData){
            		//page转数字，如果转换失败默认为1
            		postData.page = parseInt(postData.page,10);
            		if (isNaN(postData.page)) postData.page = 1;
            		
            		var myPostData =  {nd:postData.nd,_search:postData._search,rows:postData.rows,page:postData.page,sidx:postData.sidx,sord:postData.sord,nodeid:postData.nodeid,n_level:postData.n_level};
    				delete postData.nd;
    				delete postData._search;
    				delete postData.rows;
    				delete postData.page;
    				delete postData.sidx;
    				delete postData.sord;
    				delete postData.nodeid;
    				delete postData.n_level;
    				for (pd in postData){
    					if($.trim(postData[pd])=="")delete postData[pd];
    				}
    				myPostData.cond = angular.toJson(postData);
    				return myPostData;
            	}
            }
            if(attrs.ondblClickRow){
            	options.ondblClickRow = function(rowid,iRow,iCol,e){
            		scope.ondblClickRow({rowid:rowid,iRow:iRow,iCol:iCol,e:e});
            	}
            }else{
            	options.ondblClickRow = function(rowid,iRow,iCol,e){
    				if(scope.dbcEdit && rowid && rowid!==controller.edittingRowId){
    					element.jqGrid('restoreRow',controller.edittingRowId);
    					//如果行内自动保存为false，双击编辑使用回车保存
    					element.jqGrid('editRow',rowid,!scope.inlineEditAutoSave);
    					controller.edittingRowId=rowid;
    					
    					if(scope.inlineEditAutoSave)scope.autoSave();
    				}
    			}
            }
            //是否显示分页栏
            if(scope.pagerView){
            	var pager = element.next('.grid-pager').attr("id","grid-pager-"+element.attr("id"));
            	options.pager = pager;
            }
            //提取表格按钮
            var gridButton = element.siblings(".grid-button");
            var toolButtons = new Array();
            var buttonsPerms = new Array();
        	scope.tableTopButtons = new Array();
        	var toolButtonElements = gridButton.children();
        	
        	//从表格工具栏模版中获取表格按钮数据后删除
        	angular.forEach(toolButtonElements,function(button){
        		if(button.tagName=="DIV"){
        			var buttonGroup = new Array();
        			angular.forEach(angular.element(button).children(),function(groupButton){
        				var buttonObj = getButtonObj(groupButton);
        				buttonGroup.push(buttonObj);
        				if(buttonObj.permRes)buttonsPerms.push(buttonObj.permRes);
        			});
        			toolButtons.push({type:"group",groupId:angular.element(button).attr("id"),value:buttonGroup});
        		}else if(button.tagName=="I"){
        			var buttonObj = getButtonObj(button);
        			if(buttonObj.permRes)buttonsPerms.push(buttonObj.permRes);
        			toolButtons.push({type:"single",value:buttonObj});
        		}
        	});
        	gridButton.remove();
            
            //如果是子表，在主表主键值还未加载时，想初始化主表外键为"___",避免在主表未加载时出现不该出现的数据。
            if(scope.bindData&&scope.bindData.$fkMap){
            	var fk = scope.bindData.$fkMap.fk;//从表外键的key
            	if(angular.isUndefined(options.postData))options.postData = {};
            	options.postData[fk] = "___";
            }
            
            $ocLazyLoad.load({
            	  insertBefore: 'link[href="css/custom.css"]',
            	  files: ['ext/container/table_jqgrid/lib/ui.jqgrid-bootstrap.css?v=1','ext/container/table_jqgrid/lib/c2.jqgrid.css?v=1']
            	});
            $ocLazyLoad.load([
	          'ext/container/table_jqgrid/lib/grid.locale-cn.js?v=1',
//	          'ext/container/table_jqgrid/lib/jquery.jqGrid.js',
	          'ext/container/table_jqgrid/lib/jquery.jqGrid.min.js?v=1',
//              'ext/container/table_jqgrid/lib/jqgrid.datefmatter.js',//已加到jquery.jqGrid.min.js后面
              'ext/container/table_jqgrid/lib/jquery.tablednd.js?v=1']).
            then(function(){
            	//覆盖jqgrid中表格分页按钮图标样式
//            	$.jgrid.styleUI.Bootstrap.common.icon_base = "fa";
            	$.jgrid.styleUI.Bootstrap.base.icon_first = "fa fa-angle-double-left bigger-140";
            	$.jgrid.styleUI.Bootstrap.base.icon_prev = "fa fa-angle-left bigger-140";
            	$.jgrid.styleUI.Bootstrap.base.icon_next = "fa fa-angle-right bigger-140";
            	$.jgrid.styleUI.Bootstrap.base.icon_end = "fa fa-angle-double-right bigger-140";
            	$.jgrid.styleUI.Bootstrap.base.icon_asc = "fa fa-sort-asc";
            	$.jgrid.styleUI.Bootstrap.base.icon_desc = "fa fa-sort-desc";
            	$.jgrid.styleUI.Bootstrap.colmenu.icon_menu = "glyphicon-th-large";
//            	$.jgrid.styleUI.Bootstrap.subgrid.icon_plus = "glyphicon-chevron-right";
//            	$.jgrid.styleUI.Bootstrap.subgrid.icon_minus = "glyphicon-chevron-down";
            	//紧凑型表格
//            	$.jgrid.styleUI.Bootstrap.base.rowTable = "table table-bordered table-condensed";
                //覆盖jqgrid中的方法，解决表格计算可能多一像素的问题 （配合修改源码 2）
                $.jgrid.cellWidth = function(){
                	return false;
                }
                
//                console.log("options",options);
              	var scopeWatch = scope.$watch("bindData",function(v){
              		if(angular.isUndefined(v)) return ;
              		
              		//表格在初始化前隐藏，解决初始化前闪现列字段的问题
                    element.removeClass("hide");
              		
              		//初始化jqGrid之前事件，自定义修改jqGrid配置
                    scope.beforeInit({jqGridOptions:options});
                    scope.options = options;
                    //数组对象时本地获取数据，$url存在时远程获取数据
              		if(angular.isArray(v)){
              			options.datatype = "clientSide";
              			options.url = "clientArray";
              			options.data = v;
              		}else if(v.$url){
              			options.datatype = "json";
              			options.url = scope.treeGrid?v.$url+"?c2TreeType=treeGrid":v.$url;
              		}
              		//如果表格初始化时没有竖向滚动条，初始化之后再出现的竖向滚动条，表格宽度按没有竖向滚动条的宽度计算自动宽度
              		//解决办法：外围定义一个div，页面加载完成事件初始化高度使页面出现滚动条，表格加载完成高度自适应。
              		scope.jqGridObj = element.jqGrid(options);
              		
              		//表格表头对齐表格列
              		tableDeal();
              		//监听各种事件了更新表格宽度
              		resizeListener();
              		//初始化表格操作数据
    	        	controller.initDataOprations();
    	        	//子表处理
    	        	if(scope.bindData.$fkMap)sonTableDeal();
    	        	
    	        	//按钮权限处理
    	        	if(buttonsPerms.length==0){
    	        		//顶部工具栏处理
	        			if(scope.topToolbar)topButtons();
	        			//分页栏按钮
	        			if(scope.pageToolbar)pageToolBarButtons();
    	        	}else{
    	        		$.get("ws/isPermitedByBatch",{permExpr:buttonsPerms}).success(function(data){
    	        			var permData = data.result;
    	        			if(angular.isDefined(permData)){
    	        				for (var i = toolButtons.length - 1; i >= 0; i--) {
    	        					var v = toolButtons[i];
    	        					if(v.type=="single"){
    	        						if(v.value.permRes && angular.isDefined(permData[v.value.permRes]) && permData[v.value.permRes] === false) toolButtons.splice(i, 1);
    	        					}else if(v.type=="group"){
    	        						for (var j = v.value.length - 1; j >= 0; j--) {
    	        							var v2 = v.value[j];
    	        							if(v2.permRes && angular.isDefined(permData[v2.permRes]) && permData[v2.permRes] === false) v.value.splice(j, 1);
    	        						}
    	        					}
    	        				}
    	        			}
    	        			//顶部工具栏处理
    	        			if(scope.topToolbar)topButtons();
    	        			//分页栏按钮
    	        			if(scope.pageToolbar)pageToolBarButtons();
    	        		});
    	        	}
    	        	
    	        	//搜索DIV
    	        	if(attrs.searchDiv)appendSearchHouse();
    	        	
    	        	scope.afterInit();
//    	        	element.jqGrid("setFrozenColumns");
    	        	//只监听一次
              		scopeWatch();
              	});
            });
            
            function resizeListener(){
            	//监听C2TableRecomputeWidth广播事件，更新表格宽度
	        	scope.$on("C2TableRecomputeWidth",function(event, msg){
	        		$timeout(function(){
	        			controller.resizeGrid(0);
	        		},0);
	        	});
	        	//接受Tab active发送的广播，表格在当前tab下时，并且是第一次触发active时（tab宽度和表格宽度相差很大）重新计算宽度
	        	scope.$on("tabActived",function(event,msg){
	        		var tabParents = $(element.parents("#"+msg));
	        		if(tabParents.length != 0 && Math.abs($(tabParents[0]).width() - element.width()) > 10){
	        			controller.resizeGrid(0);
	        		}
	        	});
	        	//接受Modal窗口发送的广播，表格在当前tab下时，重新计算宽度
	        	scope.$on("modalOpen",function(event,msg){
        			controller.resizeGrid(0);
	        	});
            }
            
            function tableDeal(){
            	//主键列
            	scope.keyColumn = attrs.tableKey;
            	var groupHeaders = new Array();
            	var beforeHead = {headName:""};
            	angular.forEach(options.colModel,function(v,n){
            		//列定义主键
            		if(v.key){
            			scope.keyColumn = v.name;
            		}
            		//表格表头对齐表格列
            		v.align = v.align || "left";
            		element.parents(".ui-jqgrid-view").find("#jqgh_"+attrs.id+"_"+v.name).addClass("jqgrid-head-"+v.align);
            		//表头分组
            		if(v.groupHeadName){
            			if(v.groupHeadName == beforeHead.headName){
            				beforeHead.numberOfColumns += 1;
            			}else{
            				var text = "<div>"+v.groupHeadName+"</div>";
            				beforeHead = {startColumnName:v.name,numberOfColumns:1,titleText:text,headName:v.groupHeadName};
            				groupHeaders.push(beforeHead);
            			}
            		}else{
            			beforeHead = {headName:""};
            		}
            	});
            	
            	//表头分组。表头分组后，更改表格宽度，更改列宽对表头无效！！！
            	scope.isTableGroupHeader = false;
            	if(groupHeaders.length>0){
            		scope.isTableGroupHeader = true;
            		element.jqGrid("setGroupHeaders", {
            			useColSpanStyle: true, 
            			groupHeaders: groupHeaders
            		});
            	}
            	//表头菜单<a>标签跳转问题
            	element.parents(".ui-jqgrid-view").find(".ui-jqgrid-hdiv .ui-jqgrid-htable a.colmenu").attr("href","javascript:void(0);");
            }
            
            /** 表格初始化完成之后，开始加功能 **/
        	function checkEntity(){
        		if(!scope.bindData.$entity){
        			Messenger.post({type:"error",message:"没有关联到实体"});
        			return false;
        		}
        		if(!scope.bindData.$sn){
        			Messenger.post({type:"error",message:"$sn不存在！"});
        			return false;
        		}
        		return true;
        	}
        	function checkSelectRow(selectData){
        		if(!selectData){
        			Messenger.post({type:"info",message:"没有选中行！"});
        			return false;
        		}
        		return true;
        	}
        	
        	function sonTableDeal(){
            	var pk = scope.bindData.$fkMap.pk;//主表主键的key
                var fk = scope.bindData.$fkMap.fk;//从表外键的key
            	
            	var parentDataString = attrs.bindData.substring(0,attrs.bindData.lastIndexOf("."));
                var parentBindData = $parse(parentDataString)(scope.$parent);
                
                if(angular.isFunction(parentBindData.$save)&&angular.isFunction(parentBindData.$execute)){
                	//如果父数据源是实体数据源
                	//把有$save和$execute的对象作为实体数据源处理，有没有缺陷
                	realFormScope.$watch(parentDataString+"."+pk,function(newValue,oldValue){
                     	//就算外键值还没有获取到，也要设置表格的参数，最好是一个无法查询出任何结果的值
                     	//不然表格中可能会短暂的显示不应该看到的数据
                		if(angular.isDefined(newValue)){
                			var parentParamString = "{\""+fk+"\":\""+newValue+"\"}";
                			controller.setForeignKey(angular.fromJson(parentParamString));
                		}
                     });
                }else if(angular.isDefined(parentBindData.$url)&&angular.isDefined(parentBindData.$fkMap)){
                	//如果父数据源是列表型数据源
                	//提供接口setPrimaryData()，支持用户主动的为当前表格设置主表数据
                	//这里应该还支持自动绑定主表，通过父数据源的表达式找到绑定的表格(如果是)，然后自动监听父表格的选中事件，触发setPrimaryData(selectedRow);
                }
        	}
        	
        	//预定义按钮事件
        	var buttonAction = {
        		addEven:function(params){
        			if(checkEntity()){
        				if(scope.bindData.$fkMap){//是否子表
        					var fk = scope.bindData.$fkMap.fk;
        					var dataFk = controller.getGridParam("postData")[fk];
        					if(angular.isUndefined(dataFk)||dataFk=="___"){
        						Messenger.post({type:"error",message:"没有父ID，不能添加"});
        						return ;
        					}
        				}
        				if(angular.isUndefined(params)){
        					params= controller.fk;
        				}else{
        					params = $parse(params)(realFormScope);
        					angular.extend(params, controller.fk);
        				}
        				Modal.open("ef/"+scope.bindData.$sn+"/add",params,function(){
        					controller.refresh();
        				});
        			}
        		},
        		editEven:function(params){
        			if(checkEntity()){
        				var selectData = controller.getSingleSelectRowId();
        				if(checkSelectRow(selectData)){
        					if(angular.isUndefined(params)){
        						params={id:selectData};
        					}else{
        						params = $parse(params)(realFormScope);
        						if(angular.isUndefined(params.id))params.id=selectData;
        					}
        					Modal.open("ef/"+scope.bindData.$sn+"/edit",params,function(){
        						controller.refresh();
        					});
        				}
        			}
        		},
        		viewEven:function(params){
        			if(checkEntity()){
        				var selectData = controller.getSingleSelectRowId();
        				if(checkSelectRow(selectData)){
//            					var paramJson=angular.fromJson("{\"id\":\""+selectData+"\"}");
        					if(angular.isUndefined(params)){
        						params={id:selectData};
        					}else{
        						params = $parse(params)(realFormScope);
        						if(angular.isUndefined(params.id))params.id=selectData;
        					}
        					Modal.open("ef/"+scope.bindData.$sn+"/view",params);
        				}
        			}
        		},
        		deleteEven:function(){
        			if(checkEntity()){
        				var selectData = [];
        				if(options.multiselect)selectData = controller.getSelectRowIds();
        				else selectData.push(controller.getSingleSelectRowId());
        				if(selectData.length==0||selectData[0]==null)selectData=null;
        				if(checkSelectRow(selectData)){
        					var paramJson=angular.fromJson("{\"id\":\""+selectData.join(",")+"\"}");
        					Modal.openDelConfirm({message:"确定删除?"},function(){
        						scope.bindData.$entity.$delete(paramJson,function(){
        							Messenger.post({type:'success',message:'删除成功'});
        							controller.refresh();
        						});
        					});
        				}
        			}
        		},
        		searchEven:function(){
        			element.jqGrid("searchGrid",{showQuery:true,multipleSearch: true,multipleGroup:true});
        		},
        		expendEven:function(){
        			if(scope.searchHouse){
        				scope.searchHouse.slideToggle();
        			}
        		},
        		refreshEven:function(){
        			element.jqGrid().trigger("reloadGrid");
        			controller.initDataOprations() ;
        			if(scope.pageToolbar)navButtonsState(false);
					if(scope.topToolbar)topToolbarButtonsState(false);
        		},
        		//行内编辑事件
        		inlineAddEven:function(params){
        			params = $parse(params)(realFormScope);
        			
        			params = params || {};
        			//自动生成表格ID
        			params.rowID = controller.getTableNewRowId();
                	controller.edittingRowId = params.rowID;
                	controller.edittingRowData = {thisIsNewRow:true}
                	controller.resetSelection();
					controller.setSelection(params.rowID,false);
        			
        			if(scope.bindData.$fkMap){//如果是子表，从父实体中获取主键值放到外键中。
        				var pk = scope.bindData.$fkMap.pk;//主表主键的key
                        var fk = scope.bindData.$fkMap.fk;//从表外键的key
                        var parentDataString = attrs.bindData.substring(0,attrs.bindData.lastIndexOf("."));
                        var parentBindData = $parse(parentDataString)(scope.$parent);
                        
                        params.initdata = params.initdata || {};
                        params.initdata[fk] = parentBindData[pk];
        			}
        			
        			controller.addRow(params);
        			if(scope.pageToolbar)navButtonsState(true);
        			if(scope.topToolbar)topToolbarButtonsState(true);
        			
        			if(scope.inlineEditAutoSave)scope.autoSave();
        		},
        		inlineEditEven:function(params){
        			var selectData = controller.getSingleSelectRowId();
    				if(checkSelectRow(selectData)&&selectData != controller.edittingRowId){
    					params = $parse(params)(realFormScope);
    					
    					controller.restoreRow(controller.edittingRowId);
    					controller.edittingRowId = selectData;
    					controller.edittingRowData = controller.getRowData(controller.edittingRowId);
    					controller.resetSelection();
    					controller.setSelection(selectData,false);
    					controller.editRow(selectData,params);
    					if(scope.pageToolbar)navButtonsState(true);
    					if(scope.topToolbar)topToolbarButtonsState(true);
    					
    					if(scope.inlineEditAutoSave)scope.autoSave();
    				}
        		},
        		inlineSaveEven:function(params){
        			params = $parse(params)(realFormScope);
        			//先保存，获取数据之后再自己验证
        			controller.saveRow(controller.edittingRowId,params);
        			var rowData = controller.getRowData(controller.edittingRowId);
        			var verify = true;
        			
        			//自己处理行内验证
        			angular.forEach(scope.editVerify,function(v){
        				if(v.required&&verify){
        					if(controller.edittingRowId&&rowData[v.colName].trim() == ""){
        						controller.editRow(controller.edittingRowId);
        						Messenger.post({type:"error",message:v.requiredMessage});
        						verify = false;
        					}
        				}
        				if(v.regExp&&verify){
        					var reg = new RegExp(v.regExp);
        					if(!reg.test(rowData[v.colName].trim())){
        						controller.editRow(controller.edittingRowId);
        						Messenger.post({type:"error",message:v.regExMessage});
        						verify = false;
        					}
        				}
        				if(angular.isDefined(v.custom)&&verify){
        					var customReturn = v.custom({verify:verify,rowData:rowData});
        					if(customReturn === false){
        						controller.editRow(controller.edittingRowId);
        						verify = false;
        					}
        				}
        			});
        			
        			if(verify){
        				setTableDataOpration(rowData);
        				controller.edittingRowId = null;
        				controller.edittingRowData = null;
            			if(scope.pageToolbar)navButtonsState(false);
            			if(scope.topToolbar)topToolbarButtonsState(false);
        			}else{//验证失败恢复编辑状态
        				controller.editRow(controller.edittingRowId);	
        				if(scope.inlineEditAutoSave)scope.autoSave();
        			}
        		},
        		inlineRestoreRow:function(params){
        			params = $parse(params)(realFormScope);
        			controller.restoreRow(controller.edittingRowId,params);
        			//自己处理取消事件
        			if(controller.edittingRowData){
        				if(controller.edittingRowData.thisIsNewRow)controller.delRowData(controller.edittingRowId);
        				else controller.setRowData(controller.edittingRowId,controller.edittingRowData);
        			}
        			controller.edittingRowId = null;
        			controller.edittingRowData = null;
        			if(scope.pageToolbar)navButtonsState(false);
        			if(scope.topToolbar)topToolbarButtonsState(false);
        		},
        		inlineDeleteRow:function(){
        			var selectData = controller.getSelectRowIds();
        			if(!angular.equals(selectData.length,0)){
        				angular.forEach(angular.copy(selectData),function(v){
            				controller.delRowData(v);
            			});
            			if(scope.pageToolbar)navButtonsState(false);
    					if(scope.topToolbar)topToolbarButtonsState(false);
        			}
        		},
        		dataImport:function(){
        			var inputEle;
        			if($("#jqGridTableUploadFileInput").length!=0)inputEle=$("#jqGridTableUploadFileInput").unbind("change");
        			else{
        				inputEle = $("<input id=jqGridTableUploadFileInput type=file style='display:none;'/>");
        				$("#view").append(inputEle);
        			}
        			inputEle.click();
        			inputEle.bind("change",function(data){
        				var file = data.target.files[0];
        				Upload.upload({
        					url:"import/"+scope.jqGridUrl,
        					data: {file: file}
        				}).then(function (response) {
        					inputEle.val("");
        					Modal.open("f/sys_table_import",{tableData:response.data.result,errorRecordNum:response.data.errorRecordNum,handler:attrs.importHandler,importSuccessCallback:scope.importSuccessCallback},function(){
        						controller.refresh();
        					});
        			    }, function (response) {
        			    	console.log("error",response);
        			    });
        			});
        		},
        		dataExport:function(){
        			var url = controller.getGridParam("url");
        			var rowNum = controller.getGridParam("rowNum");
        			var records = controller.getGridParam("records");
        			var cond = controller.getGridParam("postData");
        			var sidx = controller.getGridParam("sortname");
        			var sord = controller.getGridParam("sortorder");
        			var params = [];
        			params.push("sidx="+sidx);
        			params.push("sord="+sord);
        			params.push("rows="+records);
        			params.push("cond="+encodeURIComponent(JSON.stringify(cond)));
        			//二级表头
        			var groupHeader = controller.getGridParam("groupHeader");
        			if(groupHeader){
        				var groupHeaders = groupHeader[0].groupHeaders;
        				params.push("groupHeaders="+encodeURIComponent(JSON.stringify(groupHeaders)));
        			}
        			var urlParams = params.join('&');
        			/*Modal.open("f/sys_table_export",{rowNum:rowNum,records:records},function(rows){
        				var cond = controller.getGridParam("postData");
	        			var sidx = controller.getGridParam("sortname");
	        			var sord = controller.getGridParam("sortorder");
	        			var params = [];
	        			params.push("sidx="+sidx);
	        			params.push("sord="+sord);
	        			params.push("rows="+rows);
	        			params.push("cond="+encodeURIComponent(JSON.stringify(cond)));
	        			var urlParams = params.join('&');
	        			
	        			window.open(url+"/export?"+urlParams);
        			});*/
        			window.open(url+"/export?"+urlParams);
        		}
        	}
        	controller.tableButtonActions = buttonAction;
        	
        	function topToolbarButtonsState(rowEditting){
				angular.forEach(scope.tableTopButtons,function(v){
					if(v.params.type == "inlineAdd"){
						rowEditting?v.element.addClass("disabled"):v.element.removeClass("disabled");
					}
					if(v.params.type == "inlineSave" || v.params.type == "inlineRestore"){
						rowEditting?v.element.removeClass("disabled"):v.element.addClass("disabled");
					}
				})
			}
        	function checkFocus(handler){
				if(handler.relatedTarget == handler.currentTarget || $(handler.relatedTarget).parents(".jqgrow")[0] == handler.currentTarget){
					var tr = controller.getInd(controller.edittingRowId,true);
					angular.element(tr).one("focusout",checkFocus);
				}else buttonAction.inlineSaveEven();
			}
			scope.autoSave = function(){
				var tr = controller.getInd(controller.edittingRowId,true);
				angular.element(tr).one("focusout",checkFocus);
			}
			//行内保存时更新表格数据操作
			function setTableDataOpration(rowData){
//				var rowData = controller.getRowData(rowid);
			    //日期字符串变毫秒数
			    angular.forEach(scope.customTypeCol,function(v){
			    	if(v.type == "datetime")rowData[v.colName] = moment(rowData[v.colName]).toDate().getTime();
			    });
			    if(rowData[scope.keyColumn].indexOf(controller.tableNewRowIdPre)==0){
			    	rowData._tableAddRowId = rowData[scope.keyColumn];
			    	delete rowData[scope.keyColumn];
			    	var completelyNew = updateDataOprations("add","_tableAddRowId",rowData);
			    	if(completelyNew){
			    		controller.dataOprations.add.push(rowData);
			    	}
			    }else{
			    	var completelyNew = updateDataOprations("update",scope.keyColumn,rowData);
			    	if(completelyNew)controller.dataOprations.update.push(rowData);
			    }
			}
			function updateDataOprations(oprationType,rowIdName,rowData){
        		var completelyNew = true;
		    	angular.forEach(controller.dataOprations[oprationType],function(data,i){
		    		if(data[rowIdName]==rowData[rowIdName]){
		    			controller.dataOprations[oprationType][i] = rowData;
		    			completelyNew = false;
		    		}
		    	});
		    	return completelyNew;
        	}
        	
        	//自定义工具栏按钮对象
        	function getButtonObj(v){
        		var id = angular.element(v).attr("id");
        		var title = angular.element(v).attr("title");
        		var icon = angular.element(v).attr("class");
        		var type = angular.element(v).attr("click-type");
        		var iconParams = angular.element(v).attr("icon-params");
        		var beforeClickEven = angular.element(v).attr("before-click");
        		var permRes = angular.element(v).attr("perm-res");
        		
        		//按钮前事件,如果返回false则不执行预定义按钮事件
        		function checkBefore(){
        			var beforeValue = true;
            		if(angular.isDefined(beforeClickEven)){
            			beforeValue = eval("scope.$parent." + beforeClickEven);
            		}
            		return !beforeValue == false || angular.isUndefined(beforeValue);
        		}
        		var customButtonEven;
        		if(type=="add")customButtonEven=function(){if(checkBefore())buttonAction.addEven(iconParams);};
        		if(type=="edit")customButtonEven=function(){if(checkBefore())buttonAction.editEven(iconParams);};
        		if(type=="get")customButtonEven=function(){if(checkBefore())buttonAction.viewEven(iconParams);};
        		if(type=="delete")customButtonEven=function(){if(checkBefore())buttonAction.deleteEven();};
        		if(type=="search")customButtonEven=function(){if(checkBefore())buttonAction.searchEven();};
        		if(type=="refrash")customButtonEven=function(){if(checkBefore())buttonAction.refreshEven();};
        		if(type=="expend")customButtonEven=function(){if(checkBefore())buttonAction.expendEven();};
        		
        		if(type=="inlineAdd")customButtonEven=function(){if(checkBefore())buttonAction.inlineAddEven(iconParams);};
        		if(type=="inlineEdit")customButtonEven=function(){if(checkBefore())buttonAction.inlineEditEven(iconParams);};
        		if(type=="inlineSave")customButtonEven=function(){if(checkBefore())buttonAction.inlineSaveEven(iconParams);};
        		if(type=="inlineRestore")customButtonEven=function(){if(checkBefore())buttonAction.inlineRestoreRow(iconParams);};
        		if(type=="inlineDelete")customButtonEven=function(){if(checkBefore())buttonAction.inlineDeleteRow();};
        		
        		if(type=="export")customButtonEven=function(){if(checkBefore())buttonAction.dataExport();};
        		if(type=="import")customButtonEven=function(){if(checkBefore())buttonAction.dataImport();};
        		//自定义按钮事件
        		if(type=="custom"&&angular.element(v).attr("click")){
        			customButtonEven = function($event){
        				var rowId = controller.getSingleSelectRowId();
        				var rowData = controller.getSingleSelectData();
        				var tableData = controller.getRowData();
        				scope.$apply(function(){
        					eval("scope.$parent." + angular.element(v).attr("click"));
        				});
        			}
        		}
        		return {id:id,type:type,title:title,icon:icon,permRes:permRes,even:customButtonEven};
        	}
        	
        	function topButtons(){
        		var topTool = element.parents(".ui-jqgrid-view").find("#t_"+element.attr('id')).addClass("clearfix").css({height:"auto"});
    			var buttonFloat = "";
    			if(angular.isDefined(attrs.caption)){
    				topTool.append('<span class="table-title">'+attrs.caption+'</span>');
    				buttonFloat = " pull-right";
    			}
    			var buttonHouseId = attrs.id+"ButtonHouse";
    			var buttonHouse = $("<div id='"+buttonHouseId+"' class='button-house"+buttonFloat+"'></div>");
    			angular.forEach(toolButtons,function(v,n){
    				if(v.type=="group"){
    					//按钮组下拉
    					var groupUL = angular.element('<ul class="dropdown-menu dropdown-info dropdown-menu-right"></ul>');
    					var buttonGroupFirst;
    					angular.forEach(v.value,function(b,i){
    						if(i==0&&angular.isUndefined(b.even)){
    							buttonGroupFirst = b;
    						}else{
    							var buttonLi = angular.element('<li id='+b.id+'><a><i class="ace-icon fa '+b.icon+'"></i> '+b.title+'</a></li>')
        						if(angular.isDefined(b.permRes))buttonLi.attr("perm-res",b.permRes);
        						buttonLi.bind("click",b.even).appendTo(groupUL);
        						scope.tableTopButtons.push({params:b,element:buttonLi});
    						}
    					});
    					//按钮组DIV
    					var groupDiv = angular.element('<div class="btn-group"></div>');
    					var groupButtonTitle = "";
    					if(angular.isDefined(buttonGroupFirst))groupButtonTitle=buttonGroupFirst.title;
    					groupDiv.append('<button type="button" data-toggle="dropdown" class="btn btn-white btn-primary grid-btn dropdown-toggle">'+groupButtonTitle+'<span class="ace-icon fa fa-angle-down icon-on-right"></span></button>');
    					
    					groupUL.appendTo(groupDiv);
    					groupDiv.appendTo(buttonHouse);
    				}else{
    					var sigleButton = v.value;
    					var topToolButton = angular.element('<button type="button" class="btn btn-white btn-primary grid-btn" id='+sigleButton.id+'><i class="ace-icon fa '+sigleButton.icon+' bigger-120"></i> '+sigleButton.title+'</button>');
    					if(angular.isDefined(sigleButton.permRes))topToolButton.attr("perm-res",sigleButton.permRes);
    					topToolButton.bind("click",sigleButton.even).appendTo(buttonHouse);
    					scope.tableTopButtons.push({params:sigleButton,element:topToolButton});
    				}
    			});
    			topTool.append(buttonHouse);
    			var importButtonHouseId = attrs.id+"ImportButtonHouse";
    			var importButtonHouse = $("<div id='"+importButtonHouseId+"' class='button-house hide'>导入预览<button>保存</button><button>取消</button></div>");
    			topTool.append(importButtonHouse);
    			//初始化行内编辑按钮状态（不推荐将表格行内编辑按钮放到下拉菜单中）
    			topToolbarButtonsState(false);
        	}
        	
        	function pageToolBarButtons(){
        		var pagerDiv = "#grid-pager-"+element.attr("id");
        		//创建分页栏工具条
    			element.jqGrid('navGrid',pagerDiv,{
    				view: false,
    				edit: false,
    				add: false,
    				del: false,
    				search : false,
    				refresh : false
    			});
    			//向分页栏工具条添加按钮
    			var navButtons = [];
    			scope.tableNavButtons = new Array();
    			angular.forEach(toolButtons,function(v,n){
    				if(v.type=="single"){
    					navButtonAdd(v.value);
    					navButtons.push(v.value);
    				}else if(v.type=="group"){
    					angular.forEach(v.value,function(v2){
    						navButtonAdd(v2);
    						navButtons.push(v2);
    					});
    				}
    			});
    			
    			options.pager.find("#grid-pager-"+element.attr("id")+"_left .ui-pg-button").each(function(i,v){
    				scope.tableNavButtons.push({params:navButtons[i],element:$(v)});
    			});
    			//初始化行内编辑按钮状态
    			navButtonsState(false);
    			
    			//表格行内编辑按钮状态调整：编辑时，行内添加禁用；不编辑时，行内保存和取消编辑禁用。
    			function navButtonsState(rowEditting){
    				angular.forEach(scope.tableNavButtons,function(v){
    					if(v.params.type == "inlineAdd"){
    						rowEditting?v.element.addClass("ui-state-disabled"):v.element.removeClass("ui-state-disabled");
    					}
    					if(v.params.type == "inlineSave" || v.params.type == "inlineRestore"){
    						rowEditting?v.element.removeClass("ui-state-disabled"):v.element.addClass("ui-state-disabled");
    					}
    				})
    			}
    			
    			function navButtonAdd(button){
//    				if(angular.isUndefined(button.even)) return;
    				var caption=scope.pageToolbarText?button.title:"";
    				var navButtonAddOptions = {
						id:button.id,
						buttonicon:button.icon,
						caption:caption,
						title:button.title,
						position:"last",
						onClickButton:button.even
    				};
    				element.jqGrid('navButtonAdd',pagerDiv,navButtonAddOptions);
    			}
        	}
        	
        	function appendSearchHouse(){
        		var searchHouseDisplay = "none";
				if(attrs.searchDivShow=="true")searchHouseDisplay="block";
				scope.searchHouse = $("<div style='display:"+searchHouseDisplay+";' class='table-search-house'></div>");
				scope.searchHouse.append(element.parents("[name='mainForm']").find("#"+attrs.searchDiv));
				scope.searchHouse.append("<div style='clear:both;'></div>");
				var topTool = element.parents(".ui-jqgrid-view").find("#t_"+element.attr('id'));
    			if(topTool)topTool.after(scope.searchHouse);
        	}
        }
    }
}]);directives.directive('c2Accordion', ['FormContainerFactory','accordionConfig', function (FormContainerFactory,accordionConfig) {
    return {
//    	scope:{accordionCloseOthers:"="},
    	controller:function ($scope,$element,$attrs,$transclude){
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		
    		var closeParam = $attrs.id+"_accordion_hide";
    		
    		this.id = $attrs.id;
    		this.name = "accordion";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getCloseOthers = function(){
    			return $scope.$state[closeParam];
    		}
    		this.setCloseOthers = function(b){
    			$scope.$state[closeParam] = b;
    		}
    	},
    	link: function(scope, element, attrs, accordionCtrl){
//			accordionConfig.closeOthers=angular.isDefined(attrs.accordionCloseOthers) ? scope.accordionCloseOthers:true;
    	}
    }
}]);directives.directive('c2AccordionGroup', ['FormContainerFactory', function (FormContainerFactory) {
    return {
//    	require:'accordionGroup', 
    	controller:function ($scope,$element,$attrs,$transclude){
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		
    		var openParam = $attrs.id+"_accordionGroup_open";
    		var disabledParam = $attrs.id+"_accordionGroup_disabled";
    		
    		this.id = $attrs.id;
    		this.name = "accordionGroup";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getOpenState = function(){
    			return $scope.$state[openParam];
    		}
    		this.setOpenState = function(state){
    			$scope.$state[openParam] = state;
    		}
    		this.getDisabledState = function(){
    			return $scope.$state[disabledParam];
    		}
    		this.setDisabledState = function(state){
    			$scope.$state[disabledParam] = state;
    		}
    	}
    }
}]);directives.directive('c2Dropdown', ['$compile','FormContainerFactory',function ($compile,FormContainerFactory) {
	var angle="<i class=\"ace-icon fa fa-angle-down icon-only\"></i>";
	return {
		scope:true,
		controller:function($scope,$element,$attrs){
			 var form=FormContainerFactory.findForm($scope);
			 if(form&&$attrs.id)form[$attrs.id] = this;
	         
	         this.id = $attrs.id;
	    	 this.name = "dropdown";
	         this.setText=function(text){
	        	 $scope.$toggleBtn.text(text);
	        	 //设置text后小三角会消失
	        	 $scope.$toggleBtn.append(angle);
	         }
		},
		compile:function(div,attrs){
			//如果为表格下拉列表，则忽略
			if(div.parent().hasClass("grid-button")) return ;
			
			function buttonToLi(btn){
				var iconElement=btn.first('i').children().get(0);
				var iconHtml;
				if(iconElement){
					iconHtml=btn.first('i').children().get(0).outerHTML;
				}
				var text=btn.text();
				var ng_click=btn.attr("ng-click");
				var html='<li id="'+btn.attr("id")+'" ';
				if(btn.attr("perm-res")){
					html+=' perm-res="'+btn.attr("perm-res")+'"';
				}
				html +='><a';
				if(ng_click&&ng_click!=""){
					html+=' ng-click="'+ng_click+'"';
				}
				html+='>';
				if(iconHtml&&iconHtml!=""){
					html+=iconHtml;
				}
				html=html+text+'</a></li>';
				return html;
			}
			function createToggleBtn(button){
				var toggleBtn;
				if(button.is("button")){
					//按钮有事件，直接生成新的按钮作为toggle
						var toggleBtnHtml="<button type=\"button\" class=\""+button.attr("class")
						+"\">"+angle+"</button>";
						toggleBtn= $(toggleBtnHtml);
				}else{
					//在图标按钮工具条中，将<button>替换为<a>
					var toggleBtnHtml="<a>"+angle+"</a>";
					toggleBtn= $(toggleBtnHtml);
				}
				toggleBtn.attr("data-toggle","dropdown");
				toggleBtn.addClass("dropdown-toggle");
				return toggleBtn;
			}

			//compile start
			var ul=$("<ul class=\"dropdown-menu "+attrs.c2Dropdown+"\" role=\"menu\"></ul>");
			var firstButton;
			div.children().each(function(index,element){
				var button=$(element);
				if(index == 0){
					if(button.is("button")){
						firstButton=button;
						div.addClass("btn-group");
						if(button.attr("ng-click")){
							button.after(createToggleBtn(button));
							//有事件的第一个下拉按钮也要添加到ul中
							var li=buttonToLi(button);
							ul.append(li);
							button.remove();
						}else{
							button.addClass("dropdown-toggle");
							button.append(angle);
							button.attr("data-toggle","dropdown");
						}
					}else{
						div.addClass("widget-menu");
						button.addClass("dropdown-toggle");
						button.attr("data-toggle","dropdown");
						ul.addClass("dropdown-closer dropdown-caret");
					}
					return;
				}
				var li=buttonToLi(button);
				ul.append(li);
				button.remove();
			});
			div.append(ul);
			return function(scope){
				scope.$toggleBtn=firstButton;
			};
		}
};//return
	}]);

directives.directive('c2Form',['FormContainerFactory',function (FormContainerFactory) {
    return {
    	controller:function($scope, $element, $attrs){
    		var form = FormContainerFactory.findForm($scope);
    		form.mainForm = this;
    		var controller = this;
    		
    		this.name = "mainForm";
    		this.getDom = function(){
    			return $element;
    		}
    		this.removeTipMessage = function(){
    			angular.forEach(controller.getFormModules(),function(module, n){
    				module.removeTipMessage();
    			});
    		}
    		this.clean = function(){
    			angular.forEach(controller.getFormModules(),function(module, n){
    				module.clean();
    			});
    		}
    		this.check = function(){
    			var i = 0;
    			angular.forEach(controller.getFormModules(),function(module,n){
    				var c = module.check();
    				if(!c)i++;
        		});
    			if(i>0){
    				return false;
    			}else{
    				return true;
    			}
    		}
    		this.getFormModules = function(){
    			var formModules = [];
    			angular.forEach(form,function(module,i){
        			if(typeof module.check =="function"&&module.name!="mainForm"){
        				formModules.push(module);
        			};
        		});
    			return formModules;
    		}
    	}
    }
}]);


/*c2DirectiveInfoStart
@id c2-button
@name 普通按钮
@codeStart
<button c2-button id="btn" class="btn btn-success btn-sm no-border" ng-show="true" ng-disabled="false"><i class="fa fa-thumbs-o-up blue"></i>普通按钮</button>
@codeEnd
@table
@th 属性名;数据类型;是否必填;默认值;说明
@td id;String;false; ;ID。
@td class;String;false;c2-button btn;表格样式<br>可以通过`btn-default,btn-primary,btn-success,btn-info,btn-warning,btn-danger,btn-white`调整按钮颜色<br>通过`btn-link,no-border`调整按钮效果<br>通过`btn-xs,btn-sm,btn-nl,btn-lg`调整按钮大小
@endTable
c2DirectiveInfoEnd*/
directives.directive('c2Button', ['FormContainerFactory','$parse',function(FormContainerFactory,$parse){
	return {
		controller:function($scope,$element,$attrs){
			var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		
    		this.setDisabled = function(disabled){
    			$scope.$state[$attrs.id].disabled = disabled;
    		}
    		this.setVisible = function(visible){
    			$scope.$state[$attrs.id].visible = visible;
    		}
		},
		link:function(scope,element,attrs){
			if(angular.isUndefined(scope.$state[attrs.id])){
				scope.$state[attrs.id]={};
			}
    		
			if(attrs.showDefault){
//        		var hideValue = $parse(attrs.showDefault)(scope);
        		scope.$watch(attrs.showDefault,function(v){
        			if(angular.isUndefined(v))return ;
        			scope.$state[attrs.id].visible = v;
        		});
        	}
			if(attrs.disabledDefault){
//				var disabledValue = $parse(attrs.disabledDefault)(scope);
				scope.$watch(attrs.disabledDefault,function(v){
        			if(angular.isUndefined(v))return ;
        			scope.$state[attrs.id].disabled = v;
        		});
			}
			
    		if(angular.isUndefined(scope.$state[attrs.id].disabled))
    			scope.$state[attrs.id].disabled = false;
    		if(angular.isUndefined(scope.$state[attrs.id].visible))
    			scope.$state[attrs.id].visible = true;
		}
	};
}]);
directives.directive('c2CheckboxGroup', ['$parse','filterFilter','FormContainerFactory','UIDirectiveService','Messenger', '$timeout',function ($parse,filterFilter,FormContainerFactory,UIDirectiveService,Messenger,$timeout) {
    return{
    	scope:{bindData:"=",checkboxOptions:"=?",clickEvent:"&",checkboxDisabled:"=?",checkboxRequired:"=?"},
    	template:'<label class="checkbox-inline" ng-repeat="checkbox in options" ng-style="checkboxStyle"><input ng-model="checkbox.checked" type="checkbox" name="{{checkboxName}}" value="{{checkbox.value}}" class="ace" ng-disabled="checkbox.disabled" ng-click="checkbox.click(checkbox);" /><span class="lbl"> {{checkbox.text}}</span></label>',
    	controller:function ($scope,$element,$attrs,$transclude){
    		UIDirectiveService.checkUIAttrs("c2CheckboxGroup",$attrs);
    		UIDirectiveService.checkGroupShow($scope,$element,$parse);
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		var controller = this;
    		
    		var defaultParams = {
    				checkboxRequired:false,
    				checkboxDisabled:false,
    				objOptions:[],
    				options:[]
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
    		if(angular.isDefined($attrs.checkboxWidth))$scope.checkboxStyle = {width:$attrs.checkboxWidth+"px"};
    		$scope.checkboxName = $attrs.id;
    		
    		this.id = $attrs.id;
    		this.name = "checkboxGroup";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getOptions = function(){
    			return $scope.options;
    		}
    		this.getObjOptions = function(){
    			return $scope.objOptions;
    		}
    		this.getInitOptions = function(){
    			return $scope.initOptions;
    		}
    		this.getOptionObj = function(value){
    			if(!angular.isString(value))Messenger.error("getOptionObj方法的参数必须为字符串！");
    			var optionObj = new Array();
				angular.forEach($scope.objOptions,function(v,n){
					angular.forEach(value.split(","),function(s){
						if(v[$attrs.optionId]==s)optionObj.push(v);
    				});
				});
    			return optionObj;
    		}
    		this.getDefaultValue = function(){
				return $attrs.defaultValue;
    		}
    		this.setDefaultValue = function(){
    			var defaultValue = this.getDefaultValue();
    			if(angular.isDefined(defaultValue))$scope.bindData=defaultValue;
    		}
    		//清空checkbox选项
    		this.cleanOptions = function(){
    			$scope.options = [];
    			$scope.bindData = null;
    		}
    		this.addOptions = function(options){
    			if(angular.isArray(options)){
					angular.forEach(options,function(v,n){
						if(v[$attrs.optionName]=="") return;
						$scope.objOptions.push(v);
						var option = {"text":v[$attrs.optionName]+"","value":v[$attrs.optionId]+"",checked:false,disabled:$scope.checkboxDisabled};
						if(angular.isDefined(v.disabled)&&typeof v.disabled == "boolean")option.disabled=v.disabled;else option.disabled=$scope.checkboxDisabled;
						if(angular.isDefined($attrs.optionSelected))option.checked = v[$attrs.optionSelected];
						option.click = function(option){
							$timeout(function(){
								//返回的checkboxObj对象内的属性与用户设置保持一致
								$scope.clickEvent({value:option.value,checked:option.checked,checkboxObj:v});
							},0);
						}
						$scope.options.push(option);
					});
    			}else{
    				angular.forEach(options,function(v,k){
    					var option = {"text":v+"","value":k+"",checked:false,disabled:$scope.checkboxDisabled};
    					option.click = function(checkbox){
    						$timeout(function(){
    							//返回的checkboxObj对象内的属性与用户设置保持一致
    							$scope.clickEvent({value:checkbox.value,checked:checkbox.checked,checkboxObj:{text:v,value:k}});
    						},0);
						}
    					$scope.options.push(option);
					});
    			}
    		}
    		this.checkedAllCheckbox = function(isChecked){
    			if(angular.isUndefined(isChecked))isChecked=true;
    			angular.forEach($scope.options,function(o,n){
    				o.checked = isChecked;
    			});
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage(controller.getDom(),message);
    		}
    		this.removeTipMessage = function(){
    			UIDirectiveService.removeTipMessage(controller.getDom());
    		}
    		this.check = function(){
    			if($scope.checkboxRequired&&$scope.bindData==null){
    				UIDirectiveService.setTipMessage(controller.getDom(),"必填项.");
    				return false;
    			}else{
    				UIDirectiveService.removeTipMessage(controller.getDom());
    				return true;
    			}
    		}
    		this.clean = function(){
    			$scope.bindData = null;
    			controller.checkedAllCheckbox(false);
    			this.removeTipMessage();
    		}
    	},
    	link:function postLink(scope,element,attrs,controller){
    		function setCheckboxValue(v){
    			controller.checkedAllCheckbox(false);
    			if(angular.isString(v)){
    				angular.forEach(scope.options,function(o,n){
        				angular.forEach(v.split(","),function(s){
        					if(o.value==s)o.checked=true;
        				});
        			});
    			}else{
    				Messenger.error("checkbox组控件数据绑定的值必须为字符串！");
    			}
    		}
			scope.$watch("checkboxOptions",function(v,n){
				if(angular.isUndefined(v)) return ;
				scope.initOptions = v;
				controller.addOptions(v);
				controller.setDefaultValue();
				if(angular.isDefined(scope.bindData)&&scope.bindData!=null)setCheckboxValue(scope.bindData);
			});
			scope.$watch('bindData', function(v){
    			if(angular.isUndefined(v)||v==null||v=="") return ;
    			if(scope.options.length==0) return ;
    			setCheckboxValue(v);
    		});
			scope.$watch('options|filter:{checked:true}', function (nv) {
				if(nv.length==0){
					scope.bindData =null;
					return;
				}
				
//			    scope.bindData = nv.map(function (options) {
//			      return options.value;
//			    }).join(",");
				//兼容IE8写法
				var values = [];
				angular.forEach(nv,function(v){
					values.push(v.value);
				});
				scope.bindData = values.join(",");
			}, true);
    	}
    }
}]);
directives.directive('c2JqgridTh', ['$timeout','$http','$parse','UIDirectiveService','FormContainerFactory','Messenger','Modal','$compile','$rootScope','$timeout','$ocLazyLoad',function ($timeout,$http,$parse,UIDirectiveService,FormContainerFactory,Messenger,Modal,$compile,$rootScope,$timeout,$ocLazyLoad) {
    return {
        scope: {
        	title:"=colTitle",
        	key:"=",
        	fixed:"=",
        	colmenu:"=",
        	colmenuSorting:"=",
        	colmenuColumns:"=",
        	colmenuFiltering:"=",
        	colmenuGrouping:"=",
        	frozen:"=",
        	hidden:"=colHidden",
        	sortable:"=",
        	formatoptions:"=",
        	summaryCustom:"&",//自定义概要
        	editable:"=",
        	editoptions:"=",
        	edithidden:"=",
        	resizable:"=",
        	required:"=editverifyRequired",
        	number:"=editverifyNumber",
        	integer:"=editverifyInteger",
        	email:"=editverifyEmail",
        	url:"=editverifyUrl",
        	custom:"&editverifyCustom",
        	search:"=",
        	searchValue:"=",
        	cellattr:"&",
        	isMergerColumn:"=",
        	customFormatter:"&",
        	searchDataInit:"&",
        	editverifyCustomFun:"&"
        },
        require:"^c2Jqgrid",
        link:function(scope,element,attrs,controller){
        	var label = attrs.colLabel;
        	if(angular.isUndefined(attrs.colLabel))label=" ";
        	var column = {
                label: label,//如果属性 colNames 是空的，那么列头的名称就是用这个值。如果 colNames 和该值都是空的，那么就将 name 设为列头的名称。
                name: attrs.colName,//给表格中的每一列设置一个唯一的 name 值，通常这个 name 会拼进列头的 id 中，所以请务必页面唯一。这个值必须设置。 有这么几个保留字不能取用：subgrid, cb 和 rn 。
                title: scope.title,//是否设置单元格的 title 属性。
                width: attrs.colWidth,//该列的宽度
                align: attrs.colAlign, //对齐方式："left" "center" "right"
                classes: attrs.colClasses,//给单元格添加类。
                
                colmenu : scope.colmenu,//是否开启列头目录
                coloptions : {sorting:scope.colmenuSorting, columns: scope.colmenuColumns, filtering: scope.colmenuFiltering, seraching:false, grouping:scope.colmenuGrouping, freeze : false},
                fixed: scope.fixed,//设置为 true 时，该列的宽度固定，不会自动拉伸或者压缩。
                frozen: scope.frozen,//该列是否可以被冻结
                hidedlg: false,
                hidden: scope.hidden,//设为 true 时，初始化的时候隐藏该列。
                index: attrs.index,//点击表头排序时，会将这个值传到后台，从而标识是以该列排序。
                key: scope.key,//如果返回的数据中没有 id 值，那么可以设置这个做为每行的 id 。行 id 的生成当然会计数从而不重复。 只能有一列可设置该值
                resizable: scope.resizable,//该类是否可以拖动边界改变宽度。
                sortable: scope.sortable,//该列是否可以排序。
                
                formatter: attrs.formatter,
                formatoptions:scope.formatoptions,
                
                summaryType : attrs.summaryType,//概要类型
            	summaryTpl : attrs.summaryTpl,//概要模版

                editable: scope.editable,
                edittype: attrs.edittype,
				editoptions:scope.editoptions,
//                editrules: {
//                    edithidden: scope.edithidden,
//                    required: scope.required,
//                    number: scope.number,
//                    integer: scope.integer,
//                    minValue: attrs.editverifyMinValue,
//                    maxValue: attrs.editverifyMaxValue,
//                    email: scope.email,
//                    url: scope.url,
//                    custom: scope.custom
//                },
//                formoptions: {
//                    elempreifx: attrs.editelempreifx,
//                    elmsuffix: attrs.editelmsuffix,
//                    label: attrs.editlabel
//                },
                search: scope.search,
                stype: attrs.searchType,//该列在搜索过程中的类型，比如 "text" 就是字符串。
                searchoptions: {
                	buildSelect: function(data){
                		var optionId = attrs.searchSelectId;
                		var optionText = attrs.searchSelectText;
                		var optionSelected = attrs.searchSelectSelected;
                		var selectHtml;
                		var datajson = angular.fromJson(data);
                		if(datajson instanceof Array){
                			selectHtml = UIDirectiveService.getSelectHtml(datajson,optionId,optionText,optionSelected);
                		}else{
                			for(obj in datajson){
                				selectHtml = UIDirectiveService.getSelectHtml(datajson[obj],optionId,optionText,optionSelected);
                				break;
                			}
                		}
                		return selectHtml;
                	},
                    sopt: !!attrs.searchSopt?attrs.searchSopt.split(" "):null,
//            		defaultValue: attrs.searchDefaultvalue,
                    value: scope.searchValue
                },
                //给单元格添加合法的属性,必须返回字符串。
                cellattr: function (rowId, val, rawObject, cm, rdata) {
                    return scope.cellattr({rowId: rowId, val: val,rawObject:rawObject,cm:cm,rdata:rdata});
                },
                groupHeadName: attrs.groupHeadName,//表头分组名称
                //合并单元格
                isMergerColumn: scope.isMergerColumn,
                compareColName:attrs.compareColName
                
                
//                datefmt:"Y-m-d",//日期格式，排序和编辑时使用。
//                firstsortorder:null, //第一次单击列头排序时的排序方式。"asc" 或者 "dest"
//                jsonmap:"",//服务端返回的 json 字符串中该列的属性值。
//                sorttype:"",//当 datatype 为 local 时，该属性可用。 排序的类别比如： int/integer 整数排序 float/number/currency 小数排序 date 时间排序 text 字符串排序。函数：参数为需要排序的值，返回排序好的值。 
//                viewable:true,//调用 viewGridRow 方法时，该值有效。设为 false ，该列将不会在查看详情的对话框中显示。
            };
        	if (attrs.customFormatter) {
            	controller.hasCustomFormatter = true;
            	//如果列格式化类型是数据字典
                if(angular.equals(attrs.formatter,'dictionary')){
                	column.dictType = angular.fromJson(attrs.formatoptions) ;
            		column.formatter = function (cellvalue, options, rowObject) {
                		var dictValueExpression = "{{dictData['"+column.dictType+"'][rowObject['"+column.name+"']]}}";
                		return scope.customFormatter({cellvalue:cellvalue, options:options, rowObject:rowObject,dictValueExpression:dictValueExpression});
                    };
            	}else{
            		column.formatter = function (cellvalue, options, rowObject) {
                		var cellString = scope.customFormatter({cellvalue:cellvalue,options:options,rowObject:rowObject});
                    	return cellString;
                    };
            	}
                column.isCustomFormatterColumn = true;
            }else if(attrs.formatter){
            	//如果列格式化类型是数据字典
            	if(angular.equals(attrs.formatter,'dictionary')){
            		column.dictType = angular.fromJson(attrs.formatoptions) ;
            		column.formatter = function (cellvalue, options, rowObject) {
            			return "{{dictData['"+column.dictType+"'][rowObject['"+column.name+"']]}}";
                    };
                    controller.hasCustomFormatter = true;
            	}else{
            		column.formatter = attrs.formatter;
            	}
            }
        	
        	//手动处理日期编辑类型
        	if(attrs.edittype =="datetime"){
        		column.c2Edittype = "datetime";
        		column.edittype = "text";
        		var datetimepickerOptions = angular.copy(column.editoptions);
        		column.editoptions = {
        			readonly:true,
    				dataInit:function(dom){
    					$ocLazyLoad.load(["ext/widget/date_time_picker/lib/bootstrap-datetimepicker.min.css","ext/widget/date_time_picker/lib/bootstrap-datetimepicker.min.js"]).then(function(){
    						$(dom).datetimepicker(datetimepickerOptions);
    					});
    				}
        		};
        		controller.setCustomTypeCol({colName:column.name,type:"datetime",format:datetimepickerOptions.format});
        	}
        	
        	//手动处理编辑验证
    		var verify = {
    			colName:column.name,
    			label:column.label,
				required: scope.required,
				requiredMessage:column.label+"列验证失败:必填项",
				regExp: attrs.editverifyRegExp,
				regExMessage: column.label+"列验证失败:"+attrs.editverifyRegExpMessage,
                custom: scope.custom
            };
    		controller.setEditVerify(verify);
        	
        	if (attrs.searchDataInit) {
            	column.searchoptions.dataInit = function (dom) {
                    scope.searchDataInit({dom:dom});
                };
            }
            if (attrs.searchSelectUrl) {
            	column.searchoptions.dataUrl = attrs.searchSelectUrl;
            	column.searchoptions.optionId = attrs.searchSelectId;
            	column.searchoptions.optionText = attrs.searchSelectText;
            	column.searchoptions.optionSelected = attrs.searchSelectSelected;
            }

//            console.log(column);
            
            if(attrs.summaryCustom){
            	column.summaryType = function(value, name, record){
            		return scope.summaryCustom({value:value,name:name,record:record});
            	};
            }
            
        	controller.setColModel(column);
        }
    }
}]);directives.directive('c2Datepicker', ['$filter','$parse','FormContainerFactory','Messenger','UIDirectiveService',
		function($filter,$parse,FormContainerFactory,Messenger,UIDirectiveService) {
			return {
				controller:function($scope,$element,$attrs){
					UIDirectiveService.checkUIAttrs("c2Datepicker",$attrs);
					UIDirectiveService.checkGroupShow($scope,$element,$parse);
					var form = FormContainerFactory.findForm($scope);
					if(form&&$attrs.id)form[$attrs.id] = this;
					
					//默认值
		    		var defaultParams = {
	    				datepickReadonly:false,
	    				ngRequired:false,
	    				format:"YYYY-MM-DD"
		    		};
		    		angular.forEach(defaultParams,function(v,k){
		    			if(angular.isUndefined($scope[k]))$scope[k]=v;
		    		});
		    		//定义datepicker是否被移除，不能使用datepickReadonly属性，因为datepickReadonly不是变量，改值报错。
//					$scope.isDatepickrRemove = $scope.datepickReadonly;
		    		
					this.getDom = function(){
						return $element;
					}
					this.getOptions = function(){
						return $scope.datepickerOptions;
					}
					this.init = function(options){
						if(angular.isUndefined(options))options=$scope.datepickerOptions;
						$element.datepicker(options);
//						$scope.datepickReadonly = false;
//						$scope.isDatepickrRemove = false;
					}
					this.remove = function(){
						$element.datepicker('remove');
//						$scope.isDatepickrRemove = true;
					}
					this.show = function(){
						$element.datepicker("show");
					}
					this.hide = function(){
						$element.datepicker("hide");
					}
					this.update = function(date){
						$element.datepicker("update",date);
					}
					this.setDate = function(date){
						$element.datepicker("setDate",date);
					}
					this.getValue = function(){
						return $element.val();
					}
					this.getDate = function(){
						return $element.datepicker("getDate");
					}
					this.setStartDate = function(date){
						$element.datepicker("setStartDate",date);
					}
					this.setEndDate = function(date){
						$element.datepicker("setEndDate",date);
					}
					this.setDaysOfWeekDisabled = function(weeks){
						$element.datepicker("setDaysOfWeekDisabled",weeks);
					}
					
					this.setTipMessage = function(message){
		    			UIDirectiveService.setTipMessage($element,message);
		    		}
		    		this.removeTipMessage = function(message){
		    			UIDirectiveService.removeTipMessage($element);
		    		}
		    		this.check = function(){
		    			if(FormContainerFactory.findFormScope($scope)&&FormContainerFactory.findFormScope($scope).mainForm){
		    				if(angular.isUndefined($attrs.name)){
		    					console.error("日期选择控件的name属性未定义,check方法不生效。");
				    			return true;
		    				}else{
		    					var formEle = FormContainerFactory.findFormScope($scope).mainForm[$attrs.name];
		    					return UIDirectiveService.checkFormData(formEle,$element,$attrs);
		    				}
		    			}else{
		    				console.error("日期选择控件未被form元素包裹,check方法不生效。");
			    			return true;
		    			}
		    		}
		    		this.clean = function(){
		    			$scope.ngModel = null;
		    			UIDirectiveService.removeTipMessage($element);
		    		}
				},
				require : ['ngModel','c2Datepicker'],
				scope : {ngModel:"=",datepickReadonly:"=?",format:"@",ngRequired:"=?"},
				link : function(scope, element, attrs, ctrl) {
					var modelCtrl = ctrl[0];
					var datepickerCtrl = ctrl[1];
					// format从视图到模型的数据绑定
					modelCtrl.$parsers.push(function(data) {
						if(angular.isUndefined(data)||data==null||data=="") return null;
						if(moment(data,scope.format.toUpperCase()).isValid()){
							return moment(data,scope.format.toUpperCase()).toDate().getTime();
						}else{
							Messenger.error("日期字符串和格式不匹配！");
							return null;
						}
					});
					// convert data from model format to view format从模型到视图的数据绑定
					modelCtrl.$formatters.push(function(data) {
						if(angular.isUndefined(data))return ;
						if(data == null||data==""){
							if(!scope.datepickReadonly){
								element.datepicker("setDate",new Date());
								element.datepicker("setDate","");
							}
							return ;
						}
						if(moment(data).format(scope.format)=="Invalid date"){
							Messenger.error("Invalid date！");
						}else{
							var t = moment(data).format(scope.format);
							if(!scope.datepickReadonly)datepickerCtrl.setDate(t);
							return t;
						}
					});

					var options = {
						autoclose : true,
						clearBtn:false,
						startDate : attrs.startDate,
						endDate : attrs.endDate,
						language : "zh-CN",
						todayHighlight : true,
						todayBtn : "linked",
						forceParse : true,
						format : scope.format.toLowerCase(),
						keyboardNavigation : true,
						minViewMode : 0,
						orientation : "auto",
						rtl : false,
						startView : 0,
						daysOfWeekDisabled:attrs.daysOfWeekDisabled
					};
					
					scope.datepickerOptions = options;
					element.datepicker(options);
					
//					if(scope.datepickReadonly)datepickerCtrl.remove();
					//监控是否禁用
					scope.$watch("datepickReadonly",function(v){
						if(v)datepickerCtrl.remove();
						else datepickerCtrl.init();
					});
				}
			};
		}]);

directives.directive('c2DateRangePicker', [ '$parse', function($parse) {
	return {
		link : function(scope, element, attrs) {
			var binding = attrs.ngModel;
			if (binding) {
				var getter = $parse(binding);
				var setter = getter.assign;
			}
			var format='YYYY-MM-DD';
			element.daterangepicker({
				format:format,
				locale:{
	                applyLabel: '确认',
	                cancelLabel: '取消',
	                fromLabel: '开始',
	                toLabel: '结束',
	            },
	            showDropdowns:true
			}, function(start, end, label) {
				if (setter) {
					setter(scope, start.format(format) + " - " + end.format(format));
					scope.$apply();
				}
			}).prev().on(ace.click_event, function() {
				$(this).next().focus();
			});
		}
	};
} ]);directives.directive('c2DateTimePicker',['$filter','$parse','FormContainerFactory','Messenger','UIDirectiveService','$compile','$ocLazyLoad',
	function($filter,$parse,FormContainerFactory,Messenger,UIDirectiveService,$compile,$ocLazyLoad) {
		return {
			require : ['ngModel','c2DateTimePicker','?^c2DatetimeRangePicker'],
			scope : {ngModel:"=",ngRequired:"=?",datetimepickReadonly:"=?",format:"@",beforeInit:"&",loadComplete:"&",changeEvent:"&"},
			controller:function($scope,$element,$attrs){
				UIDirectiveService.checkUIAttrs("c2DateTimePicker",$attrs);
				UIDirectiveService.checkGroupShow($scope,$element,$parse);
				var form = FormContainerFactory.findForm($scope);
				if(form&&$attrs.id)form[$attrs.id] = this;
				var controller = this;
				
				//默认值
	    		var defaultParams = {
    				ngRequired:false,
    				datetimepickReadonly:false,
    				format:"YYYY-MM-DD HH:mm"
	    		};
	    		angular.forEach(defaultParams,function(v,k){
	    			if(angular.isUndefined($scope[k]))$scope[k]=v;
	    		});
	    		
	    		this.id = $attrs.id;
	    		this.name = "dateTimePicker";
	    		this.getDom = function(){
					return $element;
				}
	    		this.getDatetimePicker = function(){
	    			return $element.data("DateTimePicker");
	    		}
				this.getOptions = function(){
					return $element.data("DateTimePicker").options();
				}
				this.init = function(options){
					if(angular.isUndefined(options))options=controller.getOptions();
					$element.datetimepicker(options);
				}
				this.show = function(){
					$element.data("DateTimePicker").show();
				}
				this.hide = function(){
					$element.data("DateTimePicker").hide();
				}
				this.getDate = function(){
					return $element.data("DateTimePicker").date();
				}
				this.getValue = function(){
					return $element.val();
				}
				this.setDate = function(newDate){
					$element.data("DateTimePicker").date(newDate);
				}
				this.setStartDate = function(date){
					$element.data("DateTimePicker").minDate(date);
				}
				this.setEndDate = function(date){
					$element.data("DateTimePicker").maxDate(date);
				}
				this.enable = function(){
					$element.data("DateTimePicker").enable();
				}
				this.disable = function(){
					$element.data("DateTimePicker").disable();
				}
				this.setDisabledDates = function(dates){
					$element.data("DateTimePicker").disabledDates(dates);
				}
				this.setEnabledDates = function(dates){
					$element.data("DateTimePicker").enabledDates(dates);
				}
				this.setTipMessage = function(message){
	    			UIDirectiveService.setTipMessage($element,message);
	    		}
	    		this.removeTipMessage = function(message){
	    			UIDirectiveService.removeTipMessage($element);
	    		}
	    		this.check = function(){
	    			if(FormContainerFactory.findFormScope($scope)&&FormContainerFactory.findFormScope($scope).mainForm){
	    				if(angular.isUndefined($attrs.name)){
	    					console.error("时间日期选择控件的name属性未定义,check方法不生效。");
	    					return true;
	    				}else{
	    					var formEle = FormContainerFactory.findFormScope($scope).mainForm[$attrs.name];
	    					return UIDirectiveService.checkFormData(formEle,$element,$attrs);
	    				}
	    			}else{
	    				console.error("时间日期选择控件未被form元素包裹,check方法不生效。");
    					return true;
	    			}
	    		}
	    		this.clean = function(){
	    			$scope.ngModel = null;
	    			UIDirectiveService.removeTipMessage($element);
	    		}
			},
			link : function(scope, element, attrs, ctrl) {
				var modelCtrl = ctrl[0];
				var datetimepickerCtrl = ctrl[1];
				//属性参考：http://eonasdan.github.io/bootstrap-datetimepicker/Options/
				var options = {
					format:scope.format,
					dayViewHeaderFormat:"YYYY MMMM",
					extraFormats:false,
					stepping:1,
					minDate:attrs.startDate,
					maxDate:attrs.endDate,
					useCurrent:false,
					collapse:true,
					locale:"zh-cn",
					disabledDates:[],
					enabledDates:[],
					useStrict:true, 
					sideBySide:false,
					daysOfWeekDisabled:attrs.daysOfWeekDisabled?attrs.daysOfWeekDisabled.split(" "):[], 
					calendarWeeks:false,
					viewMode:"days",
					toolbarPlacement:"default",
				    showTodayButton: false,
				    showClear: false,
				    showClose: false,
				    keepOpen: false,
				    inline: false,
				    keepInvalid:false,
				    debug:false,
				    ignoreReadonly:true
				};
				scope.datetimepickerOptions = options;
				
				$ocLazyLoad.load(["ext/widget/date_time_picker/lib/bootstrap-datetimepicker.min.css","ext/widget/date_time_picker/lib/bootstrap-datetimepicker.min.js"]).then(function(){
//				console.log("options",options);
					scope.beforeInit({options:options});
					element.datetimepicker(options);
					
					//判断是否属于时间范围控件的子控件
					if(angular.isDefined(ctrl[2])){
						var datetimeRangeCtrl = ctrl[2];
						if(angular.isDefined(attrs.endRangtimeModel)){
							scope.$parent.$watch(attrs.endRangtimeModel,function(endDate){
								if(angular.isUndefined(endDate)) return ;
								if(endDate!=null&&attrs.format=="YYYY-MM-DD"){
									endDate = moment(endDate).hour(23).minute(59).second(59);
									scope.$parent[attrs.endRangtimeModel] = endDate.toDate().getTime();
								}
								datetimepickerCtrl.setEndDate(endDate==null?false:new Date(endDate));
							});
							datetimeRangeCtrl.startDatetimeCtrl = datetimepickerCtrl;
						}
						if(angular.isDefined(attrs.startRangtimeModel)){
							scope.$parent.$watch(attrs.startRangtimeModel,function(startDate){
								if(angular.isUndefined(startDate)) return ;
								datetimepickerCtrl.setStartDate(startDate==null?false:new Date(startDate));
							});
							datetimeRangeCtrl.endDatetimeCtrl = datetimepickerCtrl;
						}
					}else{
						var addon = angular.element('<span class="input-group-addon"><i class="fa" ng-click="addonEven()" ng-class="{\'fa-times\':ngModel,\'fa-calendar\':!ngModel}"></i></span>');
						element.after(addon);
						$compile(addon)(scope);
					}
					// format从视图到模型的数据绑定
					modelCtrl.$parsers.push(function(data) {
						if(angular.isUndefined(data)||data==null||data=="") return null;
//					if(scope.format=="YYYY-MM-DD")element.blur();
						if(moment(data,scope.format).isValid()){
							return moment(data,scope.format).toDate().getTime();
						}else{
							Messenger.error("日期字符串和格式不匹配！");
							return null;
						}
					});
					//从模型到视图的数据绑定
					modelCtrl.$formatters.push(function(data) {
						modelToView(data);
					});
					
					function modelToView(data){
						if(data==null||data==""){
//							scope.modelTrigger = true;
							datetimepickerCtrl.setDate(null);
							return ;
						}
						if(moment(data).format(scope.format)=="Invalid date"){
							Messenger.error("Invalid date!");
						}else{
							scope.modelTrigger = true;
							datetimepickerCtrl.setDate(moment(data));
							return datetimepickerCtrl.getValue();
						}
					}
					modelToView(scope.ngModel);
					
					//必须要定义一个这个，不定义模型直接赋值就有问题
					modelCtrl.$render = function(){};
					
					element.on("dp.change",function(data){
						//当日期发生改变，并且由日期控件触发，$setViewValue,触发模型到视图事件
						if(data.date&&!scope.modelTrigger){
							scope.$apply(function(){
								modelCtrl.$setViewValue(data.date.format(scope.format));
							});
						}else{
							scope.modelTrigger = false;
						}
						scope.changeEvent({date:data.date,oldDate:data.oldDate});
					});
					
					scope.addonEven = function(){
						if(scope.datetimepickReadonly) return ;
						if(scope.ngModel)datetimepickerCtrl.clean();
						else datetimepickerCtrl.show();
					};
					
					//监控是否禁用
					scope.$watch("datetimepickReadonly",function(v){
						if(v)datetimepickerCtrl.disable();
						else datetimepickerCtrl.enable();
					});
					
					scope.loadComplete();
				});
				
			}
		};
	}]);

directives.directive('c2DatetimeRangePicker', ['$parse','$filter','$timeout','UIDirectiveService','FormContainerFactory','Messenger', function($parse,$filter,$timeout,UIDirectiveService,FormContainerFactory,Messenger) {
	return {
//		require:"^c2DateTimePicker",
		scope:{startModel:"=startBind",endModel:"=endBind",ngRequired:"=?",inputReadonly:"=?"},
		controller:function($scope,$element,$attrs){
			UIDirectiveService.checkUIAttrs("c2DatetimeRangePicker",$attrs);
			UIDirectiveService.checkGroupShow($scope,$element,$parse);
			var form = FormContainerFactory.findForm($scope);
			if(form&&$attrs.id)form[$attrs.id] = this;
			var controller = this;
			
			var defaultParams = {
				id:$attrs.id,
				ngRequired:false,
				inputReadonly:true
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
	    		
			$scope.startDom = $element.children("[name='start']");
			$scope.endDom = $element.children("[name='end']");
			
			this.id = $attrs.id;
    		this.name = "datetimeRangePicker";
			this.getStartDom = function(){return $scope.startDom;}
			this.getEndDom = function(){return $scope.endDom;}
			
			this.getStartDatetimeObj = function(){
				return controller.startDatetimeCtrl;
			}
			this.getEndDatetimeObj = function(){
				return controller.endDatetimeCtrl;
			}
			
			this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage($element,message);
    		}
    		this.removeTipMessage = function(message){
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.check = function(){
    			if(!$scope.ngRequired) return true;
    			var startRequired = controller.getStartDatetimeObj().getValue() != "";
    			var endRequired = controller.getEndDatetimeObj().getValue() != "";
    			if(startRequired&&endRequired){
    				UIDirectiveService.removeTipMessage($element);
    			}else{
    				UIDirectiveService.setTipMessage($element,"必填项.");
    			}
    			return startRequired&&endRequired;
    		}
    		this.clean = function(){
    			UIDirectiveService.removeTipMessage($element);
    			controller.getStartDatetimeObj().clean();
    			controller.getEndDatetimeObj().clean();
    		}
		},
		link : function(scope, element, attrs, ctrl) {
		}
	};
} ]);directives.directive('c2FileInput', ['$rootScope','FormContainerFactory','Upload','Messenger','$timeout',
                                     function ($rootScope,FormContainerFactory,Upload,Messenger,$timeout) {
    return {
    	scope:{
    		onSuccess:'&',
    		onError:'&',
    		targetUrl:'@',
    		allow:'@',
    		deny:'@'
    	},
    	controller:function($scope,$element,$attrs){
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;

    		this.setUrl = function(_url){$scope.targetUrl = _url;};
    		$scope.showRemoveButton = angular.isDefined($attrs.showRemoveButton);
    		$scope.autoSubmit = angular.isDefined($attrs.autoSubmit);
    		
    		var fileUpload=undefined;
    		var messenger=undefined;
    		this.submit = function(){
    			for (var i = 0; i < $scope.fileList.length; i++) {
      		      var file = $scope.fileList[i];
      		      messenger=Messenger.post({
          		       message: "<span id='upload_msg_"+$attrs.id+"'>文件开始上传<span>",
          		     hideAfter:false,
          		       actions: {
          		        myCancel: {
          		          label: '取消',
          		          action: function(a,b) {
          		        	fileAbort();
          		          }
          		        }
          		      }
          		    });
      		      fileUpload=Upload.upload({
      		        url: $scope.targetUrl,
      		        file: file
      		      });
	      		  fileUpload.then(function (response){
	      		    	 messenger.update({type:'success',message:'文件上传成功!'});
	     		    	  $timeout(function(){
	     		    		  messenger.hide();
	     		    	  },2000);
	     		    	  if(angular.isFunction($scope.onSuccess)){
	     		    		$scope.onSuccess({data:response.data, status:response.status, headers:response.headers, config:response.config});
	     		    	  }
	      		  },function (response) {
	      			  if(response.status>0){
	      				 messenger.update({type:'error',message:'文件上传失败!'});
	      		    	  $timeout(function(){
	      		    		  messenger.hide();
	      		    	  },2000);
	      		    	  if(angular.isFunction($scope.onError)){
	      		    		$scope.onError();
	      		    	  }
	      			  }else{
	      				  messenger.hide();
	      			  }
	      		  },function(evt){
	        		    //换用此方式update text，解决取消铵钮点击无效问题
	        		  	$(messenger.el).find("#upload_msg_"+$attrs.id).text("文件上传中，进度:"+parseInt(100.0 * evt.loaded / evt.total)+"%");
	        			//messenger.update({
	           		    //   message: "文件上传中，进度:"+parseInt(100.0 * evt.loaded / evt.total)+"%"
	          		    //});
	      		  });
    			}
    		}
    		
    		var fileAbort=function(){
    			if(fileUpload){
    				fileUpload.abort();
    				if(messenger) messenger.hide();
    			}
    		}
    		
    		this.abort=fileAbort;
    	},
    	link:function(scope, element, attrs,controller){
            	var opts={
                        no_file: '未选中文件 ...',
                        btn_choose: '浏览...',
                        btn_change: '修改...',
                        droppable: false,
                        thumbnail: false,
                        icon_remove: scope.showRemoveButton?'fa fa-times':false
                    };
            	if(angular.isDefined(scope.allow)){
            		opts.allowExt=angular.fromJson(scope.allow);
            	}
            	if(angular.isDefined(scope.deny)){
            		opts.denyExt=angular.fromJson(scope.deny);
            	}
            	element.ace_file_input(opts).on('file.error.ace',function(){
            		Messenger.error("不支持上传该类型的文件，只支持: "+scope.allow);
            	});
            	
            	element.bind('change', function(evt) {
        			var files = [], fileList, i;
        			fileList = evt.target.files;
        			if (fileList != null) {
        				for (i = 0; i < fileList.length; i++) {
        					files.push(fileList.item(i));
        				}
        			}
        			scope.fileList=fileList;
        			if(scope.autoSubmit){
	        			$timeout(function() {
	        				controller.submit();
	        			});
        			}
        		});
        		if (('ontouchstart' in window) ||
        				(navigator.maxTouchPoints > 0) || (navigator.msMaxTouchPoints > 0)) {
        			element.bind('touchend', function(e) {
        				e.preventDefault();
        				e.target.click();
        			});
            };
    	}
    };
}]);var C2MultipleUploadFile = function (scope, element, attrs) {
    this.$dom = element;
    this.$scope = scope;
    this.attrs = attrs;
    this.params=undefined;
};
C2MultipleUploadFile.prototype = {
	submit: function (params,scEvent,erEvent){
		this.params=params;
        this.$scope.upload(scEvent,erEvent);
    },
    getValue:function(){
    	return this.$scope.fileList;
    },
    disable:function(flag){
    	this.$scope.disable(flag);
    },
    setTargetUrl:function(url){
    	var directiveId=this.attrs.id;
    	$("#upload_form_"+directiveId).attr("action",url);
    },
    setParams:function(params){
    	this.params=params;
    },
    reset:function(){
    	this.$scope.fileList=[];
    }
};

directives.directive('c2MultipleUploadFile', ['$rootScope','FormContainerFactory','Messenger','$timeout',
                                      function ($rootScope,FormContainerFactory,Messenger,$timeout) {
 return {
 	scope:{
 		onSuccess:'&',
 		onError:'&'
 	},
    controller: function($scope,$attrs){

    	var directiveId=$attrs.id;
    	
    	$scope.title=$attrs.title;
    	
    	$scope.uploading=false;
    	
    	$scope.fileList=[];
    	
    	$scope.disabled=($attrs.disabled=="true");
    	
    	$scope.disable=function(flag){
    		//#attr("ng-disabled='${param.disabled}'" $!{param.disabled})
    		if(true==flag){
    			$scope.disabled=true;
    			$(".file-input").css({display:"none"});
    			$(".glyphicon-remove").removeClass("red");
    		}else{
    			$(".file-input").css({display:"block"});
    			$(".glyphicon-remove").addClass("red");
    			$scope.disabled=false;
    		}
    	}
    	
    	$scope.addFile=function(){
    		
    		//创建临时文件选择控件
    		var fileObjId=directiveId+"_"+new Date().getTime()+"_"+Math.floor(Math.random()*100000);
    		var fileObj;
    		fileObj=$("<input id=\""+fileObjId+"\" name=\""+fileObjId+"\"class=\"file-input\" type=\"file\"></input>");
    		fileObj.on("change",function(evt) {
    			
    				//添加时验证
    				if($.isNumeric($attrs.maxNum)){
    					var fNum=$scope.formObj.find(".file-input").length;
    					if(parseInt($attrs.maxNum)>0&&fNum+1>parseInt($attrs.maxNum)){
  					      Messenger.post({type:'error',message:'您最多添加【'+$attrs.maxNum+'】个附件！'});
  					      return;
    					}
    				}
    			
        			var fileName=evt.target.value;
        			if(""==fileName){
        				return;
        			}
        			fileName=fileName.match(/[^\\]*$/)[0];
        			
        			//验证文件后缀
        			if(angular.isDefined($attrs.allow)||angular.isDefined($attrs.deny)){
        				var extStart = fileName.lastIndexOf(".");
                        var ext = fileName.substring(extStart+1,fileName.length).toUpperCase();
                        if(extStart==-1){
                        	if(angular.isDefined($attrs.allow)){
                        		Messenger.error("不支持上传该类型的文件，只支持: "+$attrs.allow);
                        		return;
                        	}
                        }
                        
                        var isInDeny=false;
                        if(angular.isDefined($attrs.deny)){
                        	var deny=angular.fromJson($attrs.deny);
                        	for (name in deny){
                            	if(deny[name].toUpperCase()==ext){
                            		isInDeny=true;
                            		break;
                            	}
                            }
                        	
                        	if(isInDeny){
                        		Messenger.error("不支持上传该类型的文件: "+$attrs.deny);
                        		return;
                        	}
                        }
                        
                        var isInAllow=false;
                        if(angular.isDefined($attrs.allow)){
                        	var allow=angular.fromJson($attrs.allow);
                        	for (name in allow){
                            	if(allow[name].toUpperCase()==ext){
                            		isInAllow=true;
                            		break;
                            	}
                            }
                        	
                        	if(!isInAllow){
                        		Messenger.error("不支持上传该类型的文件，只支持: "+$attrs.allow);
                        		return;
                        	}
                        }
        			}
        			
        			var fdata={
        					domId:fileObjId,
        					name:fileName,
        					isDown:false,
        					isDelete:false
        			}
        			
        			
        			if(!angular.isUndefined($attrs.isDown)&&$attrs.isDown=="true"){
        				fdata.isDown=true;
        			}
        			
        			if(!angular.isUndefined($attrs.isDelete)&&$attrs.isDelete=="true"){
        				fdata.isDelete=true;
        			}
        			$scope.fileList.push(fdata);
        			
        			//将input移动form下
        			$("#"+fileObjId).attr("style","display:none");
        			$scope.formObj.append($("#"+fileObjId));
        			
    			    if ($scope.$$phase != '$apply' &&  $scope.$$phase != '$digest') {
    				   $scope.$apply();
    			    }
    			    
    			    $scope.addFile();
        			
        		});
    		
    		//初始化input
    		var fbtn=$("#"+directiveId+" .fileinput-button");
    		if(fbtn.length<=0){
    			throw new Error("文件上传模板不正确");
    		}
    		
    		fbtn.append(fileObj);
    		//$scope.formObj.append(fileObj);
    	}
    	
    	$scope.deleteFile=function(domId){
    		
    		if($scope.disabled) return;
    		
    		$("#"+domId).remove();
    		for (var i=0;i<$scope.fileList.length;i++){
    			if($scope.fileList[i].domId==domId){
    				$scope.fileList.splice(i,1);
    			}
    		}
    	}
    	
    	$scope.successCallback=function(data){
    		
    		$scope.uploading=false;
    		
    		$("#"+directiveId).find(".message-loading-overlay").remove();
    		
    		if($scope.successEvent){
    			$scope.successEvent(data.data);
    		}
    		
    		if(!angular.isUndefined($scope.onSuccess)){
    			$scope.onSuccess(data);
    		}
    	}
    	
    	$scope.errorCallback=function(data){
    		
    		$scope.uploading=false;
    		
    		$("#"+directiveId).find(".message-loading-overlay").remove();
    		
    		if($scope.errorEvent){
    			$scope.errorEvent(data.data);
    		}
    		
    		if(!angular.isUndefined($scope.onError)){
    			$scope.onError(data);
    		}
    	}
    	
    	$scope.upload=function(scEvent,erEvent){
    		
    		if($scope.uploading){
    			Messenger.post({type:'error',message:'正在上传,请耐心等待!'});
    			return;
    		}
    		
    		//提交前验证
			if($.isNumeric($attrs.minNum)){
				var fNum=$scope.formObj.find(".file-input").length;
				if(parseInt($attrs.minNum)>0&&fNum<parseInt($attrs.minNum)){
				      Messenger.post({type:'error',message:'您至少添加【'+$attrs.minNum+'】个附件！'});
				      return;
				}
			}
			
	    	var form=FormContainerFactory.findForm($scope);
	    	var c2MultipleUploadFile;
	    	if(form&&$attrs.id)c2MultipleUploadFile=form[$attrs.id];
	    	
			if(c2MultipleUploadFile && c2MultipleUploadFile.params instanceof Object){
				for (name in c2MultipleUploadFile.params) {
					if(c2MultipleUploadFile.params.hasOwnProperty(name)){
						var f=$scope.formObj.find("#"+name);
						if(f.length>0){
							f.val(c2MultipleUploadFile.params[name]);	
						}else{
							$scope.formObj.append("<input type=\"hidden\" id=\""+name+"\" name=\""+name+"\" value=\""+c2MultipleUploadFile.params[name]+"\">");
						}
					}
				}
			}
		
			//js动态注册事件
    		if(scEvent!=undefined&&$.isFunction(scEvent)){
    			$scope.successEvent=scEvent;
    		}
    		if(erEvent!=undefined&&$.isFunction(erEvent)){
    			$scope.errorEvent=erEvent;
    		}
    		$("#"+directiveId).append("<div  style=\"filter: alpha(opacity=50); opacity: 0.5; background-color: #000;\" class=\"message-loading-overlay\"><i class=\"fa-spin ace-icon fa fa-spinner orange2 bigger-160\"></i></div>");
    		
    		$scope.uploading=true;
    		$scope.formObj.submit();
    	}
	},
	templateUrl:'widget_multiple_file_input',
 	compile:function($element,$attrs){
	 		return function($scope,$element,$attrs){
	 			var submitUrl=$attrs.url;
	 			var iframeObj;
	 			var formObj;
	 			
	 			var form=FormContainerFactory.findForm($scope);
	 			if(form&&$attrs.id)
	 				form[$attrs.id] = new C2MultipleUploadFile($scope, $element,$attrs);
	
	 			var iframeId="upload_iframe_"+$attrs.id;
	 			var formId="upload_form_"+$attrs.id;
	    		if($("#"+iframeId).length>0){
	    			iframeObj=$("#"+iframeId);
	    		}else{
	    			iframeObj=$("<iframe name=\""+iframeId+"\" id=\""+iframeId+"\" style=\"display:none\"></iframe>");
	    			$element.append(iframeObj);
	    		}
	    		
	    		if($("#"+formId).length>0){
	    			formObj=$("#"+formId);
	    			formObj.empty();
	    		}else{
	    			formObj=$("<form action=\""+submitUrl+"\" id=\""+formId+"\" name=\""+formId+"\" encType=\"multipart/form-data\"  method=\"post\" target=\""+iframeId+"\">" +
	    					"</form>");
	    			$element.append(formObj);
	    		}
				formObj.append("<input type=\"hidden\" name=\"controlId\" value=\""+$attrs.id+"\">");
				if(!angular.isUndefined($attrs.maxSize)){
					formObj.append("<input type=\"hidden\" name=\"maxSize\" value=\""+$attrs.maxSize+"\">");
				}
	    		$scope.iframeObj=iframeObj;
	    		$scope.formObj=formObj;
	    		
	    		$scope.addFile();
	    		
	    		if("true"==$attrs.disabled){
	    			$scope.disable(true);
	    		}
	 		}
 		}
 	}
 }]);directives.directive('c2RadioGroup', ['$parse','$http','$compile','FormContainerFactory','UIDirectiveService','Messenger', function ($parse,$http,$compile,FormContainerFactory,UIDirectiveService,Messenger) {
    return{
    	scope:{bindData:"=",radioOptions:"=?",clickEvent:"&",firstSelected:"=?",radioDisabled:"=?",radioRequired:"=?"},
    	template:'<label class="radio-inline" ng-repeat="radio in options" ng-style="radioStyle"><input ng-model="bindData" type="radio" name="{{radioName}}" ng-disabled="radio.disabled" ng-value="radio.value" ng-click="radio.click();"> {{radio.text}} &nbsp;</label>',
    	controller:function ($scope,$element,$attrs,$transclude){
    		UIDirectiveService.checkUIAttrs("c2RadioGroup",$attrs);
    		UIDirectiveService.checkGroupShow($scope,$element,$parse);
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		var controller = this;
    		
    		var defaultParams = {
    				radioRequired:false,
    				radioDisabled:false,
    				firstSelected:false,
    				objOptions:[],
    				options:[]
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
    		if(angular.isDefined($attrs.radioWidth))$scope.radioStyle = {width:$attrs.radioWidth+"px"};
    		
    		$scope.radioName = $attrs.id;
    		
    		this.id = $attrs.id;
    		this.name = "radioGroup";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getOptions = function(){
    			return $scope.options;
    		}
    		this.getObjOptions = function(){
    			return $scope.objOptions;
    		}
    		this.getInitOptions = function(){
    			return $scope.initOptions;
    		}
    		this.getOptionObj = function(value){
    			var optionObj;
				angular.forEach($scope.objOptions,function(v,n){
					if(v[$attrs.optionId]==value){
						optionObj = v;
					}
				});
    			return optionObj;
    		}
    		this.firstRadioChecked = function(){
    			if($scope.options.length>0)$scope.bindData=$scope.options[0].value;
    		}
    		this.getDefaultValue = function(){
    			if(angular.isDefined($attrs.defaultValue)){
    				return $attrs.defaultValue;
    			}else{
    				var dataDefaultValue = UIDirectiveService.getOpiontsSelectedValue($scope.options,"selected","value");
    				return dataDefaultValue
    			}
    		}
    		this.setDefaultValue = function(){
    			var defaultValue = this.getDefaultValue();
    			if(angular.isDefined(defaultValue))$scope.bindData=defaultValue;
    		}
    		//清空radio选项
    		this.cleanOptions = function(){
    			$scope.options = [];
    			$scope.bindData = null;
    		}
    		this.addOptions = function(options){
    			if(angular.isArray(options)){
					angular.forEach(options,function(v,n){
						if(v[$attrs.optionName]=="") return;
						$scope.objOptions.push(v);
						var option = {"text":v[$attrs.optionName]+"","value":v[$attrs.optionId]+""};
						if(angular.isDefined(v.disabled)&&typeof v.disabled == "boolean")option.disabled=v.disabled;else option.disabled=$scope.radioDisabled;
						if(angular.isDefined($attrs.optionSelected))option.selected = v[$attrs.optionSelected];
						option.click = function(){
							$scope.bindData = v[$attrs.optionId];
							$scope.clickEvent({value:v[$attrs.optionId]});
						}
						$scope.options.push(option);
					});
    			}else{
    				angular.forEach(options,function(v,k){
    					var option = {"text":v+"","value":k+"",disabled:$scope.radioDisabled};
    					option.click = function(){
							$scope.bindData = k;
							$scope.clickEvent({value:k});
						}
    					$scope.options.push(option);
					});
    			}
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage(controller.getDom(),message);
    		}
    		this.removeTipMessage = function(){
    			UIDirectiveService.removeTipMessage(controller.getDom());
    		}
    		this.check = function(){
    			if($scope.radioRequired&&(angular.isUndefined($scope.bindData)||$scope.bindData==null)){
    				UIDirectiveService.setTipMessage(controller.getDom(),"必填项.");
    				return false;
    			}else{
    				UIDirectiveService.removeTipMessage(controller.getDom());
    				return true;
    			}
    		}
    		this.clean = function(){
    			$scope.bindData = null;
    			this.removeTipMessage();
    		}
    	},
    	link:function postLink(scope,element,attrs,controller){
			scope.$watch("radioOptions",function(v,n){
				scope.initOptions = v;
				controller.addOptions(v);
				if(angular.isUndefined(scope.bindData))controller.setDefaultValue();
				if(angular.isUndefined(scope.bindData)&&scope.firstSelected)controller.firstRadioChecked();
			});
    	}
    }
}]);
directives.directive('c2UiSelect', ['FormContainerFactory','UIDirectiveService','$http','$compile','$ocLazyLoad','$timeout',function (FormContainerFactory,UIDirectiveService,$http,$compile,$ocLazyLoad,$timeout) {
    return {
//    	require:['uiSelect','c2UiSelect'],
    	scope:{
    		bindData:"=",
    		selectOptions:"=?",
    		isMultiple:"=",
    		cleanBtn:"=",
    		closeOnSelect:"=",
    		selectRequired:"=",
    		selectDisabled:"=",
    		searchEnabled:"=",
    		
    		isRemoteOptions:"=",
    		refreshOptions:"&",
    		onSelect:"&",
    		onRemove:"&"
    	},
    	controller:function ($scope,$element,$attrs,$transclude){
    		UIDirectiveService.checkUIAttrs("c2UiSelect",$attrs);
//    		UIDirectiveService.checkGroupShow($scope,$element,$parse);
    		var form = FormContainerFactory.findForm($scope);
    		var controller = this;
    		if(form&&$attrs.id)form[$attrs.id] = controller;
    		
    		$scope.$uiSelect = {groupBy:$attrs.optionGroup,optionTemplate:$attrs.optionTemplate,textTemplate:$attrs.textTemplate};
    		
    		this.id = $attrs.id;
    		this.name = "uiSelect";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getSelectModel = function(){
    			return $scope.$uiSelect.model;
    		}
    		this.getOptions = function(){
    			return $scope.$uiSelect.options;
    		}
    		this.clean = function(){
    			$scope.bindData = null;
    			controller.removeTipMessage();
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage($element,message);
    		}
    		this.removeTipMessage = function(){
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.check = function(){
    			if($scope.selectRequired){
    				if(angular.isUndefined($scope.bindData)||$scope.bindData==null||$scope.bindData==""){
    					controller.setTipMessage("必填项.");
    					return false;
    				}else{
    					controller.removeTipMessage();
    					return true;
    				}
    			}else{
    				controller.removeTipMessage();
    				return true;
    			}
    		}
    	},
    	link:function (scope,element,attrs,controller){
    		var uiSelectDom = $("<div>",{
    			"ui-select":"",
    			"multiple":scope.isMultiple,
    			"ng-model":"$uiSelect.model",
    			"close-on-select":scope.closeOnSelect,
    			"ng-disabled":"$uiSelect.disabled",
    			"search-enabled":scope.searchEnabled,
    			"reset-search-input":true,
    			"limit":attrs.limit,
    			"on-select":"$uiSelect.onSelect($item, $model)",
    			"on-remove":"$uiSelect.onRemove($item, $model)"
    		});
    		
    		var uiSelectMatchDom = $("<div>",{
    			"ui-select-match":"",
    			"placeholder":attrs.placeholder
    		});
    		var textTemplate;
    		if(attrs.textTemplate){
    			textTemplate = $("<div>",{
    				"ng-include":"$uiSelect.textTemplate"
//    				"ng-init":scope.isMultiple?"data=$item":"data={value:$select.selected}"
    			});
    		}else{
    			textTemplate = scope.isMultiple?"<span ng-bind='$item."+attrs.optionText+"'></span>":"<span ng-bind='$select.selected."+attrs.optionText+"'></span>";
    		}
    		uiSelectMatchDom.append(textTemplate);
    		
    		var filterString = scope.isRemoteOptions?" ":" | filter: $select.search ";
    		var repeatString = "item in $uiSelect.options"+filterString+"track by item."+attrs.optionId;
    		var uiSelectChoicesDom = $("<div>",{
    			"ui-select-choices":"",
    			"refresh":"$uiSelect.refreshOptions($select.search)",
    			"group-by":"$uiSelect.groupBy",
    			"repeat":repeatString
    		});
    		var optionTemplate;
    		if(attrs.optionTemplate){
    			optionTemplate = $("<div>",{
    				"ng-include":"$uiSelect.optionTemplate"
    			});
    		}else{
    			optionTemplate = "<span ng-bind-html='item."+attrs.optionText+" | highlight:$select.search'></span>";
    		}
    		uiSelectChoicesDom.append(optionTemplate);
    		
    		uiSelectDom.append(uiSelectMatchDom).append(uiSelectChoicesDom)
    		
    		var applyBindData,applySelectOptions;
    		$ocLazyLoad.load('ui.select').then(function(){
    			element.append(uiSelectDom);
    	    	$compile(uiSelectDom)(scope);
    	    });
    		
    		scope.$watch("bindData",function(v,o){
    			if(angular.isUndefined(v)) return ;
    			if(scope.uiTrigger){
    				scope.uiTrigger = false;
    				return ;
    			}
    			//清空已选数据
    			if(scope.isMultiple)scope.$uiSelect.model = [];
	    		applyBind();
	    	});
    		
    		scope.$watch("selectOptions",function(options){
    			if(angular.isUndefined(scope.selectOptions)) return ;
    			if(!angular.isArray(scope.selectOptions)){
    				console.error("搜索下拉框（id:"+attrs.id+"）选项不是数组！");
    				return ;
    			}
    			scope.$uiSelect.options = options;
    			applyBind();
	    	});
    		
    		//禁用
    		scope.$watch("selectDisabled",function(d){
    			if(angular.isUndefined(d)) d = false;
    			scope.$uiSelect.disabled = d;
    		});
    		
    		//清空按钮
    		if(scope.cleanBtn){
    			var inputGroupHtml = "<div class='input-group'></div>";
    			element.wrap(inputGroupHtml);
    			var addon = $('<span class="input-group-addon" ng-click="clean()"><i class="fa fa-trash-o"></i></span>');
    			element.after(addon);
    			$compile(addon)(scope);
    		}
    		
    		scope.clean = function(){
    			if(scope.$uiSelect.disabled) return;
    			else controller.clean();
    		}
    		
    		scope.$uiSelect.onSelect = function($item,$model){
    			applySelected();
    			scope.uiTrigger = true;
    			scope.onSelect({item:$item,selectModel:$model});
    			
//    			resetSearchInputWidth();
    		};
    		scope.$uiSelect.onRemove = function($item,$model){
    			applySelected();
    			scope.uiTrigger = true;
    			scope.onRemove({item:$item,selectModel:$model});
    			
//    			resetSearchInputWidth();
    		};
    		
    		if(scope.isRemoteOptions){
    			scope.$uiSelect.refreshOptions = function(search){
    				return scope.refreshOptions({search:search});
    			}
    		}
    		
    		//解决多选是选中或者取消时选择随机出现文本框宽度错误的问题
    		function resetSearchInputWidth(){
    			$timeout(function(){
	    			var searchInputEle = element.find("input.ui-select-search");
	    			searchInputEle.css('width', '10px');
	    			$timeout(function(){
	        			var containerWidth = searchInputEle.parent().parent()[0].clientWidth;
	    	            var inputWidth = containerWidth - searchInputEle[0].offsetLeft - 80;
	    	            if (inputWidth < 50) inputWidth = containerWidth;
	    	            searchInputEle.css('width', inputWidth+'px');
	    			},50);
    			},0);
    		}
    		
    		function applySelected(){
    			if(scope.isMultiple){
    				$timeout(function(){//多选后scope.$uiSelect.model还未生效的问题
    					var keys = [];
        	    		angular.forEach(scope.$uiSelect.model,function(option){
        	    			keys.push(option[attrs.optionId]);
        	    		})
        	    		scope.bindData = keys.join(',');
    				},300);
	    		}else{
	    			scope.bindData = scope.$uiSelect.model[attrs.optionId];
	    		}
    		}
    		
    		//当绑定值和选项值都准备好之后开始应用到下拉框
    		function applyBind(){
    			if(angular.isUndefined(scope.selectOptions)) return ;
//    			if(someDataRedo(scope.bindData,scope.selectOptions)) return;
    			if(scope.isMultiple){
    				var keys;
    				//绑定值时将值添加至scope.$uiSelect.model中
    				if(scope.bindData!=null&&angular.isDefined(scope.bindData)){
    					keys = scope.bindData.toString().split(",");
        	    		angular.forEach(keys,function(key){
        	    			angular.forEach(scope.selectOptions,function(option,i){
        	    				if(key == option[attrs.optionId]){
        	    					//判断选中项目是否已经在选项中，如果存在不用再加入到已选项目中。
        	    					var exist = false;
        	    					angular.forEach(scope.$uiSelect.model,function(selectItem){
        	    						if(key==selectItem[attrs.optionId])exist=true;
        	    					});
        	    					if(!exist)scope.$uiSelect.model.push(option);
        	    				}
        	    			});
        	    		})
    				}else{
    					//未绑定值时定义为空数组
    					scope.$uiSelect.model = [];
    				}
    			}else{
    				scope.$uiSelect.model = null;
    				angular.forEach(scope.selectOptions,function(option,i){
	    				if(scope.bindData == option[attrs.optionId]){
	    					scope.$uiSelect.model = option;
	    				}
	    			});
    			}
    			
    		}
    		//判断是否同数据重复执行(清空时有问题)
    		function someDataRedo(bindData,selectOptions){
    			if(applyBindData==bindData&&applySelectOptions==selectOptions) return true;
    			else{
    				applyBindData=bindData;
    				applySelectOptions=selectOptions;
    				return false;
    			}
    		}
    	}
    }
}]);
directives.directive('c2SimpleSelect', ['$parse','FormContainerFactory','UIDirectiveService', function ($parse,FormContainerFactory,UIDirectiveService) {
    return{
    	scope:{
    		ngModel:"=",
    		selectOptions:"=?",
    		firstSelected:"=?"
    	},
//    	require: ['c2SimpleSelect','select'],
//    	templateUrl:"widget_select_simple",
    	template:'<select ng-options="selectOption.value as selectOption.key group by selectOption.group '+
    			 'for selectOption in options"></select>',
    	replace:true,
    	controller:function ($scope,$element,$attrs,$transclude){
    		UIDirectiveService.checkUIAttrs("c2SimpleSelect",$attrs);
    		UIDirectiveService.checkGroupShow($scope,$element,$parse);
    		var form = FormContainerFactory.findForm($scope);
    		var controller = this;
    		if(form&&$attrs.id)form[$attrs.id] = controller;
    		
    		//默认值
    		var defaultParams = {
				firstSelected:false,
				objOptions:[],
				options:[]
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
    		
    		$element.bind("blur",function(){
    			controller.check();
    		});
    		
    		if($attrs.nullValueSelect=="undefined")$attrs.nullValueSelect=undefined;
    		//如果为空格的话，可以解决未定义选中空值选项的问题，但是check验证会有问题
    		if($attrs.nullValueSelect=="emptyString")$attrs.nullValueSelect="";
    		
    		this.id = $attrs.id;
    		this.name = "simpleSelect";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getOptions = function(){
    			return $scope.options;
    		}
    		this.getInitOptions = function(){
    			return $scope.initOptions;
    		}
    		this.getObjOptions = function(){
    			return $scope.objOptions;
    		}
    		this.getOptionObj = function(value){
    			var optionObj;
				angular.forEach($scope.objOptions,function(v,n){
					if(v[$attrs.optionId]==value){
						optionObj = v;
					}
				});
    			return optionObj;
    		}
    		this.getDefaultValue = function(){
    			if(angular.isDefined($attrs.defaultValue)){
    				return $attrs.defaultValue;
    			}else{
    				var dataDefaultValue = UIDirectiveService.getOpiontsSelectedValue($scope.options,"selected","value");
    				return dataDefaultValue
    			}
    		}
    		this.setDefaultValue = function(){
    			var defaultValue = this.getDefaultValue();
    			if(angular.isDefined(defaultValue))$scope.ngModel=defaultValue;
    			else if(angular.isDefined($attrs.nullValue))$scope.ngModel=$attrs.nullValueSelect;
    		}
    		this.firstOptionSelected = function(){
    			if($scope.options.length>0){
    				if((angular.isUndefined($scope.options[0].value)||$scope.options[0].value==$attrs.nullValueSelect)&&angular.isDefined($scope.options[1]))
    					$scope.ngModel=$scope.options[1].value;
    				else
    					$scope.ngModel=$scope.options[0].value;
    			}
    		}
    		//重置选项
    		this.setOptions = function(options){
    			$scope.options = [];
    			if(angular.isDefined($attrs.nullValue))$scope.options.push({key:$attrs.nullValue,value:$attrs.nullValueSelect});
    			this.addOptions(options);
    		}
    		//清空选项
    		this.cleanOptions = function(){
    			$scope.options = [];
    			$scope.ngModel = null;
    		}
    		//添加下拉选项
    		this.addOptions = function(options){
    			if(angular.isArray(options)){
    				$scope.objOptions = options;
					angular.forEach(options,function(v,n){
						if(v[$attrs.optionName]=="") return;
						var option = {"key":v[$attrs.optionName]+"","value":v[$attrs.optionId]+""};
						if(angular.isDefined($attrs.optionSelected))option.selected = v[$attrs.optionSelected];
						if(angular.isDefined($attrs.optionGroup))option.group = v[$attrs.optionGroup];
						$scope.options.push(option);
					});
    			}else{
    				angular.forEach(options,function(v,k){
    					var option = {"key":v+"","value":k+""};
    					$scope.options.push(option);
					});
    			}
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage($element,message);
    		}
    		this.removeTipMessage = function(){
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.check = function(){
    			if(FormContainerFactory.findFormScope($scope)&&FormContainerFactory.findFormScope($scope).mainForm){
    				if(angular.isUndefined($attrs.name)){
		    			console.error("简单下拉框控件的name属性未定义,check方法不生效。");
		    			return true;
		    		}else{
		    			var formEle = FormContainerFactory.findFormScope($scope).mainForm[$attrs.name];
		    			return UIDirectiveService.checkFormData(formEle,$element,$attrs);
		    		}
    			}else{
    				console.error("简单下拉框控件未被form元素包裹,check方法不生效。");
	    			return true;
    			}
    		}
    		this.clean = function(){
    			if(angular.isDefined($attrs.nullValue))$scope.ngModel = $attrs.nullValueSelect;
    			else $scope.ngModel=undefined;
    			this.removeTipMessage();
    		}
    	},
    	link:function (scope,element,attrs,controller){
			scope.$watch("selectOptions",function(n,o){
//				if(angular.isUndefined(n))return;
				scope.initOptions = n;
				controller.setOptions(n);
				//获取数据后判断是否需要设置默认值
				if(angular.isUndefined(scope.ngModel))controller.setDefaultValue();
				//选中第一个
				if((angular.isUndefined(scope.ngModel)||scope.ngModel==attrs.nullValueSelect)&&attrs.firstSelected=="true")controller.firstOptionSelected();
				//空选项（只有没有默认值才行）
				var defaultValue = controller.getDefaultValue();
			});
			
			if(angular.isDefined(attrs.nullValue))scope.ngModel = attrs.nullValueSelect;
			scope.$watch("ngModel",function(nv,ov){
//				if(nv==ov)return;
        		//将boolea,number类型的值转为字符串。
        		if(typeof nv=="boolean"||typeof nv=="number"){
        			scope.ngModel = nv+"";
        		}
        		if(nv==null){
        			scope.ngModel = undefined;
        		}
        		if(angular.isUndefined(nv)){
        			controller.setDefaultValue();
        		}
        	});
		}
    }
}]);
directives.directive('c2TreeSelect', ['FormContainerFactory', '$timeout','$ocLazyLoad','UIDirectiveService','$parse','$http','$q',function (FormContainerFactory,$timeout,$ocLazyLoad,UIDirectiveService,$parse,$http,$q) {
    return {
    	require : ['ngModel','c2TreeSelect'],
    	scope:{
    		ngModel:'=',/*文本框的显示值*/
    		bindData:'=',/*选中的真实值*/
    		bindAttr:"@",
    		multi:'=',
    		ngRequired:"=?",
    		ngDisabled:"=?",
    		valueChange:"&",
    		checkDataFilter:"&",
    		singleNodeQuery:"&"
    	},
    	controller:function($scope,$element,$attrs){
    		$ocLazyLoad.load("ext/widget/select_tree/lib/select.tree.css");
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id]=this;
    		
    		//保证没有写ng-show时不报错
    		if($element.parents(".form-group")&&$element.parents(".form-group").attr("ng-show")){
    			var ngShowModel = $parse($element.parents(".form-group").attr("ng-show"));
    			if(angular.isUndefined(ngShowModel($scope.$parent))){
					ngShowModel.assign($scope.$parent,true);
				}
    		}
    		
    		//默认值
    		var defaultParams = {
				ngRequired:false,
				ngDisabled:false,
				bindAttr:"id"
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
    		
    		var _this=this;
    		var opened=false;
    		var targetDom=$element;
    		var targetDisplayMode='block';
			if($element.parent().hasClass('input-group')){
				//如果外层有input-group(即有图标和清空时)，操作(显示、隐藏和宽度运算)的目标dom应该是整个input-group
				targetDom=$element.parent();
				targetDisplayMode='table';
			}
    		var treeId=$attrs.id+"_treeWapper";
    		function onBodyDown(event) {
    			if (!(event.target.id == treeId || $(event.target).parents('#'+treeId).length>0)) {
    				_this.hideTree();
    			}
    		}
    		
    		this.id = $attrs.id;
    		this.name = "treeSelect";
    		this.getDom = function(){
    			return $element;
    		}
    		this.getSelectNodeValues = function(){
    			return $scope.SelectNodeValues;
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage($element,message);
    		}
    		this.removeTipMessage = function(){
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.check = function(){
    			var c = true;
    			if($scope.ngRequired){
    				if(angular.isUndefined($scope.bindData)||$scope.bindData==null||$scope.bindData==""){
        				c = false;
        				UIDirectiveService.setTipMessage($element,"必填项");
        			}
    			}
    			if(c)UIDirectiveService.removeTipMessage($element);
    			return c;
    		}
    		this.clean = function(){
				$scope.ngModel = "";
				$scope.bindData = null;
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.init=function(){
    			$('#'+treeId).css({display:'none'});
    		};
    		this.getTree=function(){
    			return form[treeId];
    		};
    		this.showTree=function(){
    			var width=targetDom.outerWidth()>200?targetDom.outerWidth():200;
    			var offset = targetDom.offset();
    			//隐藏外部控件
    			if(targetDom.parent().hasClass('input-group')){
    				targetDom.parent().css('display','none');
    			}else{
    				targetDom.css('display','none');
    			}
    			//用树的搜索框替代外部控件
    			targetDom.next().css('display','block').focus();
    			
    			var treeCss={width:width+'px',position:'absolute',top:"34px"};
	    		$('#'+treeId).css(treeCss).slideDown("fast");
    			$("body").bind("mousedown", onBodyDown);
    			opened=true;
    		};
    		this.hideTree=function(){
    			$('#'+treeId).fadeOut(0);
    			$("body").unbind("mousedown", onBodyDown);
    			targetDom.next().css('display','none');
    			targetDom.css('display',targetDisplayMode);
    			if($scope.multi){
    				//将变动的node更新到SelectNodeValues
        			var checkedNodes = _this.getTree().getChangeCheckedNodes();
            		angular.forEach(checkedNodes,function(node,i){
            			if(node.checked){
            				var filterCheck = $scope.checkDataFilter({treeNode:node});
            				if(!filterCheck == false || angular.isUndefined(filterCheck))$scope.SelectNodeValues[node.id] = node.name;
            			}else delete $scope.SelectNodeValues[node.id];
            			node.checkedOld = node.checked;
            		});
            		_this.applySelectNodeValues();
    			}
    			_this.check();
    		};
    		this.applySelectNodeValues = function(){
    			var keyArray = [];
    			var valueArray = [];
    			angular.forEach($scope.SelectNodeValues,function(v,k){
    				keyArray.push(k);
    				valueArray.push(v);
    			});
				$scope.$apply(function(){
					if($scope.bindData != keyArray.join(",")){
        				$scope.bindData = keyArray.join(",");
            			$scope.ngModel = valueArray.join(",");
            			//$scope.hideApply = true;
					}
					$scope.$parent[$attrs.id+'_treeWapper_searchStr']="";
    			});
    		}
    	},
    	link:function(scope,element,attrs,ctrl){
    		var modelCtrl = ctrl[0];
			var controller = ctrl[1];
    		
    		$timeout(function(){
				controller.init();
    		},0);
        	element.on('focus',function(event){
        		controller.showTree();
        	});
        	scope.$watch('bindData',function(nv,ov){
        		/*if(scope.hideApply){
        			scope.hideApply = false;
        			return ;
        		}*/
        		applyBindData();
        		if(angular.isDefined(nv)){
        			scope.valueChange({newValue:nv,oldValue:ov});
        		}
        	});
        	
        	//禁用后移除清空事件
        	var cleanBtn = element.siblings(".input-group-cleanBtn");
        	scope.$watch("ngDisabled",function(d){
    			if(d){
    				cleanBtn.unbind("click");
    			}else{
    				cleanBtn.bind("click",function(){
    					scope.$apply(function(){controller.clean();});
    				});
    			}
    		});
        	
        	//接收树加载完成事件广播
        	scope.$on(attrs.id+"_treeWapperAsyncSuccessBroadcast",function(even,data){
        		scope.loadDone = true;
        		applyBindData();
        	});
        	//接受树节点点击事件广播，单选时使用
        	scope.$on(attrs.id+"_treeWapperNodeClickBoradcast",function(even,data){
        		if(!scope.multi){
        			scope.$apply(function(){
        				scope.bindData = data.treeNode[scope.bindAttr];
        				scope.ngModel = data.treeNode.name;
            			scope.SelectNodeValues = {};
            			scope.SelectNodeValues[data.treeNode[scope.bindAttr]] = data.treeNode.name;
            			scope.$parent[attrs.id+'_treeWapper_searchStr']="";
        			});
        			controller.hideTree();
        		}
        	});
        	
        	function applyBindData(){
        		if(!scope.loadDone) return ;
        		var ztree=controller.getTree();
        		scope.ngModel = scope.bindData;
        		scope.SelectNodeValues = {};
        		if(scope.multi){
        			var checkedNodes = ztree.getCheckedNodes(true);
            		angular.forEach(checkedNodes,function(node,i){
            			ztree.checkNode(node,false,false,false);
            			node.checkedOld = node.checked;
            		});
        		}else ztree.cancelSelectedNode();
        		if(angular.isUndefined(scope.bindData)||scope.bindData==null||scope.bindData=="") return ;
        		
        		if(!scope.multi){
        			var snode = ztree.getNodeByParam(scope.bindAttr,scope.bindData);
        			if(snode){
        				ztree.selectNode(snode);
        				scope.ngModel = snode.name;
        				scope.SelectNodeValues[scope.bindData] = snode.name;
        			}else{
        				scope.SelectNodeValues[scope.bindData] = scope.ngModel;
        				var q = getInputName(scope.bindData);
        				if(q){
        					q.then(function(response){
        						if(response.data.result){
        							var name = getNodeName(response.data.result) ; 
        							scope.ngModel = name;
        							scope.SelectNodeValues[scope.bindData] = name;
        						}else if(response.data){
        							var name = getNodeName(response.data) ; 
        							scope.ngModel = name;
        							scope.SelectNodeValues[scope.bindData] = name;
        						}else console.log("nodeId:"+scope.bindData+"没有查到数据");
        					});
        				}
        			}
        		}else multiApply(ztree);
        	}
        	
        	function multiApply(ztree){
        		var nodes=[];
				var remoteQ = [];
				var remoteIndex = [];
				var ids=scope.bindData.split(",");
				for(var i=0;i<ids.length;i++){
					if(ids[i].length>0){
						var snode = ztree.getNodeByParam(scope.bindAttr,ids[i]);
						if(snode){
							nodes.push(snode.name);
							scope.SelectNodeValues[snode[scope.bindAttr]] = snode.name;
							ztree.checkNode(snode,true,false,false);
							snode.checkedOld = snode.checked;
						}else{//如果没有在当前树找到节点名称，先保存id显示，然后等请求单节点数据返回值后再替换id
							nodes.push(ids[i]);
							scope.SelectNodeValues[ids[i]] = ids[i];
							var q = getInputName(ids[i]);
							if(q){
								remoteQ.push(q);
								remoteIndex.push(i);
							}
						} 
					}
    			}
				var all = $q.all(remoteQ);
				all.then(function(res){//替换id为节点显示名称
					for(var i=0;i<remoteIndex.length;i++){
						if(!res[i]) return;
						if(res[i].data.result){
							var name = getNodeName(res[i].data.result) ; 
							nodes[remoteIndex[i]] = name;
							scope.SelectNodeValues[res[i].data.result.id] = name;
						}else if(res[i].data){
							var name = getNodeName(res[i].data) ; 
							nodes[remoteIndex[i]] = name;
							scope.SelectNodeValues[res[i].data.id] = name;
						}else console.log("nodeId:"+remoteIndex[i]+"没有查到数据");
					}
					scope.ngModel = nodes.join(",");
				});
				
				scope.ngModel = nodes.join(",");
        	}
        	
        	function getNodeName(node){
        		if(angular.isUndefined(node[""+attrs.nodeName])){
        			console.info("单节点查询返回结果中"+attrs.nodeName+"的值为空,请确认树节点返回对象是否存在该属性!");
        		}else{
        			return node[""+attrs.nodeName] ;
        		}
        	}
        	
        	function getInputName(id){
    			if(angular.isUndefined(attrs.singleNodeService) && angular.isUndefined(scope.singleNodeQuery)){
    				console.info("没有配置节点查询服务或回调，不能显示没有加载的节点值");
    				return ;
    			}else if(angular.isDefined(attrs.singleNodeService)){
    				return $http.post(attrs.singleNodeService,{nodeId:id});
    			}else if(angular.isDefined(scope.singleNodeQuery)){
    				return scope.singleNodeQuery({nodeId:id}) ;
    			}
    		}
    	}
    };
}]);
/*c2DirectiveInfoStart
@id c2-input
@name 文本框
@codeStart
<input c2-input id="abc" name="abc" type="text" ng-model="bindData" ng-required="true" placeholder="请输入"/>
@codeEnd
@table
@th 属性名;数据类型;是否必填;默认值;说明
@td id;String;false; ;ID。
@td class;String;false;c2-button btn;表格样式<br>可以通过`btn-default,btn-primary,btn-success,btn-info,btn-warning,btn-danger,btn-white`调整按钮颜色<br>通过`btn-link,no-border`调整按钮效果<br>通过`btn-xs,btn-sm,btn-nl,btn-lg`调整按钮大小
@endTable
c2DirectiveInfoEnd*/
directives.directive('c2Input', ['$parse','FormContainerFactory','Modal','UIDirectiveService','$ocLazyLoad', function ($parse,FormContainerFactory,Modal,UIDirectiveService,$ocLazyLoad) {
    return {
    	scope:{
    		ngModel:"=",
    		ngRequired:"=?",
    		ngDisabled:"=?",
    		hiddenInput:"=?",
    		inputGroup:"=?",
    		customEven:"&",
    		checkAfter:"&"
    	},
    	controller:function($scope,$element,$attrs,$transclude){
    		//必填项
    		UIDirectiveService.checkUIAttrs("c2Input",$attrs);
    		
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		
    		//保证没有写ng-show时不报错
    		if($element.parents(".form-group")&&$element.parents(".form-group").attr("ng-show")){
    			var ngShowModel = $parse($element.parents(".form-group").attr("ng-show"));
    			if(angular.isUndefined(ngShowModel($scope.$parent))){
					ngShowModel.assign($scope.$parent,true);
				}
    		}
    		
    		//默认值
    		var defaultParams = {
    				ngRequired:false,
    				ngDisabled:false,
    				hiddenInput:false,
    				inputGroup:false
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
    		
    		this.id = $attrs.id;
    		this.name = "input";
    		this.getDom = function(){
    			return $element;
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage($element,message);
    		}
    		this.removeTipMessage = function(message){
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.check = function(){
    			if(FormContainerFactory.findFormScope($scope)&&FormContainerFactory.findFormScope($scope).mainForm){
    				if(angular.isUndefined($attrs.name)){
    	    			console.error("文本框控件的name属性未定义,check方法不生效。");
    	    			return true;
    	    		}else{
    	    			var formEle = FormContainerFactory.findFormScope($scope).mainForm[$attrs.name];
    	    			return UIDirectiveService.checkFormData(formEle,$element,$attrs);
    	    		}
    			}else{
    				console.error("文本框控件未被form元素包裹,check方法不生效。");
        			return true;
    			}
    		}
    		this.clean = function(){
    			$scope.ngModel = "";
    			UIDirectiveService.removeTipMessage($element);
    		}
    	},
    	link: function(scope, element, attrs, controller){
    		//验证
    		if(angular.isUndefined(attrs.ngBlur)){
    			element.bind("blur",function(){
        			var checkResult = controller.check();
        			scope.checkAfter({checkResult:checkResult});
        		});
    		}
    		
    		if(angular.isDefined(attrs.defaultValue)&&angular.isUndefined(scope.ngModel)){
    			if(attrs.type=="number"){
    				scope.ngModel = Number(attrs.defaultValue);
        		}else{
        			scope.ngModel = attrs.defaultValue;
        		}
    		}
    		scope.$watch("ngModel",function(ov,nv){
    			if(angular.isDefined(ov)&&attrs.type=="number"){
    				scope.ngModel = Number(ov);
        		}
    			if(scope.inputGroup&&attrs.groupType=="iconSelect"){
    				if(ov)element.next().children().attr("class","fa "+ov);
    			}
    		});
    		
    		//输入框组
    		if(scope.inputGroup){
    			//图标选择事件
    			var iconSelectFn = function(){
    				Modal.open("pages/icon.html",{},function(data){
    					scope.ngModel = data;
    				});
    			}
    			//颜色选择事件
    			var colorSelectFn = function(){
					element.colorpicker("show").on('changeColor', function(ev){
						element.next().css("background-color",ev.color.toHex());
					});
				}
    			//自定义事件
    			var iconEven = function(){
    				scope.customEven();
    			}
    			
    			var inputGroupHtml = "<div class='input-group'></div>";
    			element.wrap(inputGroupHtml);
    			if(attrs.groupType=="iconSelect"){//图标选择
    				var addon = $('<span class="input-group-addon"><i class="fa fa-question"></i></span>');
        			element.after(addon);
        			if(attrs.hiddenInput=="true"){//隐藏文本框
        				element.hide();
        				element.next().css("border-left","1px solid #ccc");
        			}
    			}
    			if(attrs.groupType=="colorSelect"){//颜色选择
    				$ocLazyLoad.load(["ext/widget/text/lib/colorpicker.css","ext/widget/text/lib/bootstrap-colorpicker.js"]);
    				//ResInjector.addCss('ext/widget/text/lib/colorpicker.css');
    				//ResInjector.addJs('ext/widget/text/lib/bootstrap-colorpicker.js');
    				var addon = $('<span class="input-group-addon" style="background-color:grey">&nbsp;&nbsp;</span>');
    				element.after(addon);
    			}
    			if(attrs.groupType=="customIcon"){//自定义图标
    				var addon = $('<span class="input-group-addon"><i class="fa '+attrs.customIcon+'"></i></span>');
        			element.after(addon);
    			}
    			if(attrs.groupType=="customText"){//自定义文字
    				var addon = $('<span class="input-group-addon">'+attrs.customText+'</span>');
    				element.after(addon);
    			}
    			//禁用时，同时移除事件
    			scope.$watch("ngDisabled",function(n,o){
    				if(angular.isUndefined(n))return ;
    				var addonDom = element.next("span");
    				if(n){
    					addonDom.unbind("click");
    				}else{
    					if(attrs.groupType=="iconSelect")addonDom.click(iconSelectFn);
    					if(attrs.groupType=="colorSelect")addonDom.click(colorSelectFn);
    					if(attrs.groupType=="customText")addonDom.click(iconEven);
    					if(attrs.groupType=="customIcon")addonDom.click(iconEven);
    				}
    				
    			});
    		}
    	}
    }
}]);


directives.directive('c2Textarea', ['$parse','FormContainerFactory','UIDirectiveService', function ($parse,FormContainerFactory,UIDirectiveService) {
    return {
    	scope:{
    		ngModel:"=",
    		ngRequired:"=?",
    		ngDisabled:"=?"
    	},
    	controller:function($scope,$element,$attrs,$transclude){
    		UIDirectiveService.checkUIAttrs("c2Textarea",$attrs);
    		
    		var form = FormContainerFactory.findForm($scope);
    		if(form&&$attrs.id)form[$attrs.id] = this;
    		
    		//保证没有写ng-show时不报错
    		if($element.parents(".form-group")&&$element.parents(".form-group").attr("ng-show")){
    			var ngShowModel = $parse($element.parents(".form-group").attr("ng-show"));
    			if(angular.isUndefined(ngShowModel($scope.$parent))){
					ngShowModel.assign($scope.$parent,true);
				}
    		}
    		
    		//默认值
    		var defaultParams = {
    				ngRequired:false,
    				ngDisabled:false
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
    		
    		this.id = $attrs.id;
    		this.name = "textarea";
    		this.getDom = function(){
    			return $element;
    		}
    		this.setTipMessage = function(message){
    			UIDirectiveService.setTipMessage($element,message);
    		}
    		this.removeTipMessage = function(message){
    			UIDirectiveService.removeTipMessage($element);
    		}
    		this.check = function(){
    			if(FormContainerFactory.findFormScope($scope)&&FormContainerFactory.findFormScope($scope).mainForm){
    				if(angular.isUndefined($attrs.name)){
    	    			console.error("文本域控件的name属性未定义,check方法不生效。");
    	    			return true;
    	    		}else{
    	    			var formEle = FormContainerFactory.findFormScope($scope).mainForm[$attrs.name];
    	    			return UIDirectiveService.checkFormData(formEle,$element,$attrs);
    	    		}
    			}else{
    				console.error("文本域控件未被form元素包裹,check方法不生效。");
        			return true;
    			}
    		}
    		this.clean = function(){
    			$scope.ngModel = "";
    			UIDirectiveService.removeTipMessage($element);
    		}
    	},
    	link: function(scope, element, attrs, controller){
    		if(angular.isUndefined(attrs.ngBlur)){
    			element.bind("blur",function(){
        			controller.check();
        		});
    		}
    		
    		if(angular.isDefined(attrs.defaultValue)&&angular.isUndefined(scope.ngModel)){
    			scope.ngModel = attrs.defaultValue;
    		}
    	}
    }
}]);

var C2Timepicker = function(element, opts) {
	this.element = element;
	this.opts = opts;
	this.start();
};
C2Timepicker.prototype = {
	start:function() {
		 this.obj = this.element.clockpicker(this.opts).next().on("click", function () {
            $(this).prev().focus();
		 });
	}

};

directives.directive('c2Timepicker', ['UIDirectiveService','$filter',
		function(UIDirectiveService,$filter) {
			return {
				require : "^?ngModel",
				scope : {
					id : "@",
					ngModel : "=",
					defaultValue : "@",
					autoclose : "="
					
				},
				link : function(scope, element, attrs, modelCtrl) {
		
					var options = {
						autoclose : scope.autoclose,
						defaultValue:scope.defaultValue
					};
					var timepicker = new C2Timepicker(element, options);
					scope.$parent.form[scope.id] = timepicker;

				}
			};
		}] );
directives.directive('c2Tree', ['$timeout','UIDirectiveService', 'FormContainerFactory','$ocLazyLoad','debounce',
  function ($timeout,UIDirectiveService, FormContainer, $ocLazyLoad,debounce) {
    return {
        scope: {
            bindData: "=",
            autoParam: "=",
            otherParam: "=?",
            simpleData:"=?",
            idKey:"@",
            pidKey:"@",
            expendLevelNumber: "=expendLevel",
            expendAllValue: "=expendAll",
            computeHeight:"=",
            computeHeightFix:"=",
            beforeAsync: "&",
            beforeCheck: "&",
            beforeClick: "&",
            beforeDblClick: "&",
            beforeDrag: "&",
            beforeDrop: "&",
            beforeRemove: "&",
            beforeRename: "&",
            beforeRightClick: "&",
            onAsyncError: "&",
            onAsyncSuccess: "&",
            nodeCheck: "&",
            nodeClick: "&",
            nodeDblClick: "&",
            nodeRightClick: "&",
            nodeDrag: "&",
            nodeDragMove: "&",
            nodeDrop: "&",
            nodeRemove: "&",
            nodeRename: "&",
            filter: "&",
            nameIsHtml:"=",
            selectedMulti: "=",
            showIcon: "=",
            showLine: "=",
            showTitle: "=",
            searchEnable: "=",
            remoteSearch:"=",
            editorEnable: "=",
            dragMove: "=",
            dragCopy: "=",
            dragInner: "&",
            dragMoveable: "&",
            showRemoveBtn: "=showRemoveBtn",
            showRenameBtn: "=showRenameBtn",
            checkEnable: "=checkEnable",
            chkboxType: "=",
            autoCheckTrigger: "=",
            searchHide:"=",
            beforeInit:"&"
        },
        controller:function ($scope,$element,$attrs,$transclude){
        	UIDirectiveService.checkUIAttrs("c2Tree",$attrs);
        	var form = FormContainer.findForm($scope);
            if(form&&$attrs.treeId)form[$attrs.treeId]=this; 
            var controller = this;
            
            //默认值
    		var defaultParams = {
    			otherParam:{},
    			simpleData:true,
    			idKey:"id",
    			pidKey:"pid"
    		};
    		angular.forEach(defaultParams,function(v,k){
    			if(angular.isUndefined($scope[k]))$scope[k]=v;
    		});
            
            //将树所有的方法给Controller，除了reAsyncChildNodes方法（重写了）。
	    	this.initController = function(ztree){
    			$scope.$ztree=ztree;
	         	angular.forEach(ztree,function(a,n){
	         		if(typeof a == "function"&&n!="reAsyncChildNodes"&&n!="refresh")controller[n] = function(){
	         			if(arguments.length==0)return ztree[n]();
	         			if(arguments.length==1)return ztree[n](arguments[0]);
	         			if(arguments.length==2)return ztree[n](arguments[0],arguments[1]);
	         			if(arguments.length==3)return ztree[n](arguments[0],arguments[1],arguments[2]);
	         			if(arguments.length==4)return ztree[n](arguments[0],arguments[1],arguments[2],arguments[3]);
	         			if(arguments.length==5)return ztree[n](arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);
	         		};
	         	});
	        }
	    	
	    	//（原生方法重写）强行异步加载父节点的子节点。parentNode:父节点对象,为空时加载根节点数据；reloadType："refresh" 表示清空后重新加载；其他表示追加子节点处理。isSilent：是否展开父节点，true为不展开，默认展开。
            this.reAsyncChildNodes = function (parentNode, reloadType, isSilent) {
            	if(!reloadType)reloadType="refresh";
            	controller.getZtree().reAsyncChildNodes(parentNode, reloadType, isSilent);
            }
            
            $scope.treeDom=$element.find(".ztree");
            
            this.id = $attrs.id;
    		this.name = "tree";
        	this.getDom = function(){
        		return $scope.treeDom;
        	}
        	this.getZtree = function(){
        	    return  $scope.$ztree;
        	}
        	this.getZtreeOptions = function(){
        		return $scope.options;
        	}
        	
        	function treeAsyncSuccess(ztree,openState,checkState,selectedState,openNodes,checkedNodes,selectedNodes){
            	var idKey = ztree.setting.data.simpleData.idKey;
            	if(openState){
        			angular.forEach(openNodes,function(v,n){
        				var theKey=v[idKey]?idKey:"name";
            			var nsn = ztree.getNodesByParam(theKey,v[theKey]);
            			if(nsn.length>0)ztree.expandNode(nsn[0],true,false,false);
            		});
        		}
            	if(checkState){
        			angular.forEach(checkedNodes,function(v,n){
        				var theKey=v[idKey]?idKey:"name";
        				var nsn = ztree.getNodesByParam(theKey,v[theKey]);
        				if(nsn.length>0)ztree.checkNode(nsn[0],true,false,false);
        			});
        		}
        		if(selectedState){
                	angular.forEach(selectedNodes,function(v,n){
                		var theKey=v[idKey]?idKey:"name";
                		var nsn = ztree.getNodesByParam(theKey,v[theKey]);
                		if(nsn.length>0)ztree.selectNode(nsn[0],true);
                	});
        		}
        	}
        	this.getParams = function(){
            	return controller.getZtree().setting.async.otherParam;
            }
        	
        	controller.treeStateKeep;
        	//刷新异步加载树。isSameState:是否刷新保持选中，勾选中，展开状态（执行效率好像不好）；url:更新树url;param：更新树参数；node：更新的树节点；
            this.refresh = function(isSameState,url,param,node){
            	var ztree = controller.getZtree();
            	if(url)ztree.setting.async.url = url;
            	if(param)ztree.setting.async.otherParam = param;
            	if(isSameState){
                	var idKey = ztree.setting.data.simpleData.idKey;
                	var openNodes = ztree.getNodesByParam("open",true);
                	var checkedNodes = ztree.getCheckedNodes(true);
                	var selectedNodes = ztree.getSelectedNodes();
                	controller.treeStateKeep = function(){
                		treeAsyncSuccess(ztree,true,true,true,openNodes,checkedNodes,selectedNodes);
                	}
            	}
            	ztree.reAsyncChildNodes(node,"refresh");
            }
            //刷新2
            this.refresh2 = function(params,openState,checkState,selectedState,node){
            	var ztree = controller.getZtree();
            	if(angular.isDefined(params)&&params!=null)ztree.setting.async.otherParam = params;
            	
            	var idKey = ztree.setting.data.simpleData.idKey;
            	var openNodes = ztree.getNodesByParam("open",true);
            	var checkedNodes = ztree.getCheckedNodes(true);
            	var selectedNodes = ztree.getSelectedNodes();
            	controller.treeStateKeep = function(){
            		treeAsyncSuccess(ztree,openState,checkState,selectedState,openNodes,checkedNodes,selectedNodes);
            	}
            	ztree.reAsyncChildNodes(node,"refresh");
            }
            
            this.deleteSelectedNode = function (callbackFlag) {
                var selectedNodes = controller.getZtree().getSelectedNodes();
                for (var i = 0, l = selectedNodes.length; i < l; i++) {
                	controller.getZtree().removeNode(selectedNodes[i], callbackFlag);
                }
                return selectedNodes;
            }
            this.setAsyncExpandNodeLevel = function (levelNum) {
                $scope.expendLevel = levelNum;
            }
            this.setAsyncExpandNodeAll = function(isExpand){
            	$scope.expendAll = isExpand;
            }
        },
        link: function (scope, element, attrs, controller) {
        	//树容器高度
        	$timeout(function(){
            	var treeHeight = attrs.treeHeight;
            	if(scope.computeHeight){
            		var containerHight = element.parents('.fixed-height-container').height();
            		treeHeight = containerHight-scope.computeHeightFix;
            	}
        		element.css("height",treeHeight);
        	},0);

            //初始展开所有
            scope.expendAll = scope.expendAllValue;
            //初始展开层级
            scope.expendLevel = scope.expendLevelNumber;
            
            var setting = {
                async: {
//                    enable: true,
//                    url: ztreeUrl,
                    autoParam: scope.autoParam,
                    otherParam: scope.otherParam,
                    type: 'post',
                    dataType:"text",
//                    contentType:"application/json",
                    dataFilter: function (treeId, parentNode, responseData) {
                    	//下拉树有chkDisabled属性时，去除勾选方法失效
                    	if(attrs.treeType=="selectTree"){
                    		angular.forEach(responseData,function(v,n){
                    			delete v.chkDisabled;
                    		});
                    	}
                        var params = {treeId: treeId, parentNode: parentNode, responseData: responseData};
                        return UIDirectiveService.invoke(attrs.filter, scope.filter, params, responseData);
                    }
                },
                callback: {
                    beforeAsync: function (treeId, treeNode) {
                        var params = {treeId: treeId, treeNode: treeNode};
                        return UIDirectiveService.invoke(attrs.beforeAsync, scope.beforeAsync, params, true);
                    },
                    beforeCheck: function (treeId, treeNode) {
                        var params = {treeId: treeId, treeNode: treeNode};
                        return UIDirectiveService.invoke(attrs.beforeCheck, scope.beforeCheck, params, true);
                    },
                    beforeClick: function (treeId, treeNode, clickFlag) {
                        var params = {treeId: treeId, treeNode: treeNode, clickFlag: clickFlag};
                        return UIDirectiveService.invoke(attrs.beforeClick, scope.beforeClick, params, true);
                    },
                    beforeDblClick: function (treeId, treeNode) {
                        var params = {treeId: treeId, treeNode: treeNode};
                        return UIDirectiveService.invoke(attrs.beforeDblClick, scope.beforeDblClick, params, true);
                    },
                    //用于捕获节点被拖拽之前的事件回调函数
                    beforeDrag: function (treeId, treeNodes) {
                        var params = {treeId: treeId, treeNodes: treeNodes};
                        return UIDirectiveService.invoke(attrs.beforeDrag, scope.beforeDrag, params, true);
                    },
                    //用于捕获节点拖拽操作结束之前的事件回调函数，并且根据返回值确定是否允许此拖拽操作
                    beforeDrop: function (treeId, treeNodes, targetNode, moveType, isCopy) {
                        var params = {treeId: treeId, treeNodes: treeNodes, targetNode: targetNode, moveType: moveType, isCopy: isCopy};
                        return UIDirectiveService.invoke(attrs.beforeDrop, scope.beforeDrop, params, true);
                    },
                    //用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
                    beforeRemove: function (treeId, treeNode) {
                        var params = {treeId: treeId, treeNode: treeNode};
                        return UIDirectiveService.invoke(attrs.beforeRemove, scope.beforeRemove, params, true);
                    },
                    beforeRename: function(treeId, treeNode, newName, isCancel){
                    	var params = {treeId: treeId, treeNode: treeNode,newName:newName,isCancel:isCancel};
                        return UIDirectiveService.invoke(attrs.beforeRename, scope.beforeRename, params, true);
                    },
                    //用于捕获 鼠标右键点击之前的事件回调函数，并且根据返回值确定触发 onRightClick 事件回调函数
                    beforeRightClick: function (treeId, treeNode) {
                        var params = {treeId: treeId, treeNode: treeNode};
                        return UIDirectiveService.invoke(attrs.beforeRightClick, scope.beforeRightClick, params, true);
                    },
                    onAsyncError: function (event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
                        scope.onAsyncError({event: event, treeId: treeId, treeNode: treeNode, XMLHttpRequest: XMLHttpRequest, textStatus: textStatus, errorThrown: errorThrown});
                    },
                	onAsyncSuccess:function(event, treeId, treeNode, msg){
                		var ztree = controller.getZtree();
                		if(scope.expendAll){
                			var expendTreeNodes = angular.isUndefined(treeNode)||treeNode==null?ztree.getNodes():treeNode.children;
                			angular.forEach(expendTreeNodes,function(node,n){
                				if(node.isParent)ztree.expandNode(node,true,false,false);
                			});
                		}else{
                			if (scope.expendLevel&&scope.expendLevel>0) {
                				var expendTreeNodes = angular.isUndefined(treeNode)||treeNode==null?ztree.getNodes():treeNode.children;
                				angular.forEach(expendTreeNodes,function(node,n){
                    				if(node.isParent&&node.level < scope.expendLevel)ztree.expandNode(node,true,false,false);
                    			});
                    		}
                		}
                		
                		if(angular.isDefined(controller.treeStateKeep))controller.treeStateKeep();
                		
                		scope.onAsyncSuccess({event:event,treeId:treeId,treeNode:treeNode,msg:msg});
                		
                		scope.$parent.$broadcast(attrs.treeId+"AsyncSuccessBroadcast",{event:event,treeId:treeId,treeNode:treeNode,msg:msg});
                	},
                    onCheck: function (event, treeId, treeNode) {
                        scope.nodeCheck({event: event, treeId: treeId, treeNode: treeNode});
                    },
                    onClick: function (event, treeId, treeNode, clickFlag) {
                    	scope.$parent.$broadcast(attrs.treeId+"NodeClickBoradcast",{event:event,treeId:treeId,treeNode:treeNode});
                        scope.nodeClick({event: event, treeId: treeId, treeNode: treeNode, clickFlag:clickFlag});
                    },
                    onDblClick: function (event, treeId, treeNode) {
                        scope.nodeDblClick({event: event, treeId: treeId, treeNode: treeNode});
                    },
                    onRightClick: function (event, treeId, treeNode) {
                        scope.nodeRightClick({event: event, treeId: treeId, treeNode: treeNode});
                    },
                    onDrag: function (event, treeId, treeNodes) {
                        scope.nodeDrag({event: event, treeId: treeId, treeNodes: treeNodes});
                    },
                    onDragMove: function (event, treeId, treeNodes) {
                        scope.nodeDragMove({event: event, treeId: treeId, treeNodes: treeNodes});
                    },
                    onDrop: function (event, treeId, treeNodes, targetNode, moveType, isCopy) {
                        scope.nodeDrop({event: event, treeId: treeId, treeNodes: treeNodes, targetNode: targetNode, moveType: moveType, isCopy: isCopy});
                    },
                    onRemove: function (event, treeId, treeNode) {
                        scope.nodeRemove({event: event, treeId: treeId, treeNode: treeNode});
                    },
                    onRename: function (event, treeId, treeNode, isCancel) {
                        scope.nodeRename({event: event, treeId: treeId, treeNode: treeNode, isCancel: isCancel});
                    }
                },
                data: {
                	keep:{
                		leaf:false,
                		parent:false
                	},
                    key: {
                        children: "children",
                        name: attrs.nodeName,
                        title: attrs.nodeTitle,
                        checked:"checked",
                        url:"url"
                    },
                    simpleData: {
                        enable: scope.simpleData,
                        idKey: scope.idKey,
                        pIdKey: scope.pidKey,
                        rootPId: null //用于修正根节点父节点数据，即 pIdKey 指定的属性值
                    }
                },
	                view: {
	                	nameIsHTML:scope.nameIsHtml,
	                    selectedMulti: scope.selectedMulti,
	                    showIcon: scope.showIcon,
	                    showLine: scope.showLine,
	                    showTitle: scope.showTitle,
	                    //搜索树的样式
	                    fontCss: function (treeId, treeNode) {
	                        return (!!treeNode.highlight) ? {color: "#A60000", "font-weight": "bold"} : {color: "#333", "font-weight": "normal","font-family":"微软雅黑, STHei, 华文黑体"};
	                    }
	                },
                check: {
                	autoCheckTrigger: scope.autoCheckTrigger,
                	chkboxType: scope.chkboxType,
                    enable: scope.checkEnable,
                    chkStyle: attrs.checkStyle,
                    radioType: "all",
                	nocheckInherit:false,
                	chkDisabledInherit:false
                },
                edit: {
                    enable: scope.editorEnable,
                    drag: {
                    	prev:function(treeId, treeNodes, targetNode){
                    		var params = {treeId: treeId, treeNodes: treeNodes, targetNode:targetNode};
                            return UIDirectiveService.invoke(attrs.dragMoveable, scope.dragMoveable, params, true);
                    	},
                    	next:function(treeId, treeNodes, targetNode){
                    		var params = {treeId: treeId, treeNodes: treeNodes, targetNode:targetNode};
                            return UIDirectiveService.invoke(attrs.dragMoveable, scope.dragMoveable, params, true);
                    	},
                    	inner:function(treeId, treeNodes, targetNode){
                    		var params = {treeId: treeId, treeNodes: treeNodes, targetNode:targetNode};
                            return UIDirectiveService.invoke(attrs.dragInner, scope.dragInner, params, true);
                    	},
                        isMove: scope.dragMove,
                        isCopy: scope.dragCopy
                    },
                    showRemoveBtn: scope.showRemoveBtn,
                    showRenameBtn: scope.showRenameBtn
                }
            };
            
            var resouces = ['ext/widget/tree/lib/jquery.ztree.all.min.js'];
            if(attrs.theme == "style2"){
            	resouces.push('ext/widget/tree/lib/zTreeStyle2.css');
            	element.find("ul.ztree").addClass("style2");
            }else if(attrs.theme == "style3"){
            	resouces.push('ext/widget/tree/lib/zTreeStyle2.css');
            	element.find("ul.ztree").addClass("style2");
            	element.find("ul.ztree").addClass("tree-transparent");
            }else resouces.push('ext/widget/tree/lib/zTreeStyle.css');
            
            $ocLazyLoad.load(resouces).then(function(){
            	
                //初始化zTree之前事件，自定义修改zTree配置
                scope.beforeInit({zTreeOptions:setting});
                scope.options = setting;
                
                var ztree=undefined;
                var scopeWatch = scope.$watch("bindData",function(v){
              		if(angular.isUndefined(v)) return ;
              		if(angular.isArray(v)){
              			setting.async.enable = false;
              			ztree=$.fn.zTree.init(scope.treeDom, setting, scope.bindData);
              			controller.initController(ztree);
              			scope.$parent.$broadcast(attrs.treeId+"AsyncSuccessBroadcast");
              		}else if(v&&v.$url){
              			setting.async.enable = true;
              			//restURL参数替换
              			var ztreeUrl =UIDirectiveService.replacePathParams(v.$url,scope.otherParam);
              			setting.async.url = ztreeUrl;
              			ztree=$.fn.zTree.init(scope.treeDom, setting);
              			controller.initController(ztree);
              		}
              		if (scope.searchEnable)treeSearch();
              		
              		//只监听一次
              		scopeWatch();
              	});
            });
            
            //树节点搜索功能
            function treeSearch(){
            	//远程搜索 ，参数treeRemoteSearch
        		var treeSearchRemoteDebounce = debounce(function(s){
        			var treeParam = controller.getParams();
        			treeParam.treeRemoteSearch = s;
        			controller.refresh2(treeParam,true,true,true);
        		},600);
        		
        		//本地加载的树数据搜索
        		var treeObj = controller.getZtree();
            	var searchHide = scope.searchHide;
            	if(angular.isUndefined(searchHide))searchHide=true;
                var nodeList = [];
        		var treeSearchLocalDebounce = debounce(function(value){
        			var allNodes = treeObj.transformToArray(treeObj.getNodes());
                    nodeList = treeObj.getNodesByParamFuzzy(attrs.nodeName, value);
                    lightNodes(allNodes, false);
                    if (value != ""){
                    	if(searchHide){
                    		treeObj.hideNodes(allNodes);
                    		var shouldShowNodes = [];
                    		angular.forEach(nodeList,function(node,n){
                    			expandNodeCascade(node);
                    			node.highlight = true;
                    			treeObj.updateNode(node,true);
                    			nodeParents(shouldShowNodes,node);
                    		});
                    		treeObj.showNodes(shouldShowNodes.concat(nodeList));
                    	}else{
                    		lightNodes(nodeList, true);
                    	}
                    }else{
                    	if(searchHide)treeObj.showNodes(allNodes);
                    }
        		},600);
        		
        		scope.$parent.$watch(attrs.treeId+"_searchStr", function (value,old) {
        			if (value === undefined)return;
        			scope.remoteSearch?treeSearchRemoteDebounce(value):treeSearchLocalDebounce(value);
        		});
            }
            
            //高亮显示节点
            function lightNodes(searchNodes, highlight) {
                for (var i = 0, l = searchNodes.length; i < l; i++) {
                    searchNodes[i].highlight = highlight;
                    controller.updateNode(searchNodes[i],highlight);
                    if(highlight){
                    	expandNodeCascade(searchNodes[i]);
                    }
                }
            }
            //展开节点
            function expandNodeCascade(node){
            	var pn = node.getParentNode();
                if(pn){
                	if(!pn.open){
                		controller.expandNode(pn,true,false,false);
                	}else{
                		expandNodeCascade(pn);
                	}
                }
            }
            //节点的所有父节点
            function nodeParents(nodeArray,node){
            	var parentNode = node.getParentNode();
            	if(parentNode!=null){
            		nodeArray.push(parentNode);
            		nodeParents(nodeArray,parentNode);
            	}
            }
        }
    };
}]);
 function showErrorAlert (reason, detail) {
        var msg='';
        if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
        else {
            console.log("error uploading file", reason, detail);
        }
        $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+
            '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
    }
 directives.directive('c2Wysiwyg', ['FormContainerFactory', function (FormContainerFactory) {
    return {
        link: function (scope, element, attrs) {
        	element.ace_wysiwyg({
                toolbar:
                    [
                        'font',
                        null,
                        'fontSize',
                        null,
                        {name:'bold', className:'btn-info'},
                        {name:'italic', className:'btn-info'},
                        {name:'strikethrough', className:'btn-info'},
                        {name:'underline', className:'btn-info'},
                        null,
                        {name:'insertunorderedlist', className:'btn-success'},
                        {name:'insertorderedlist', className:'btn-success'},
                        {name:'outdent', className:'btn-purple'},
                        {name:'indent', className:'btn-purple'},
                        null,
                        {name:'justifyleft', className:'btn-primary'},
                        {name:'justifycenter', className:'btn-primary'},
                        {name:'justifyright', className:'btn-primary'},
                        {name:'justifyfull', className:'btn-inverse'},
                        null,
                        {name:'createLink', className:'btn-pink'},
                        {name:'unlink', className:'btn-pink'},
                        null,
                        {name:'insertImage', className:'btn-success'},
                        null,
                        'foreColor',
                        null,
                        {name:'undo', className:'btn-grey'},
                        {name:'redo', className:'btn-grey'}
                    ],
                'wysiwyg': {
                    fileUploadError: showErrorAlert
                }
            }).prev().addClass('wysiwyg-style1');
        	//Add Image Resize Functionality to Chrome and Safari
            //webkit browsers don't have image resize functionality when content is editable
            //so let's add something using jQuery UI resizable
            //another option would be opening a dialog for user to enter dimensions.
            if ( typeof jQuery.ui !== 'undefined' && /applewebkit/.test(navigator.userAgent.toLowerCase()) ) {

                var lastResizableImg = null;
                function destroyResizable() {
                    if(lastResizableImg == null) return;
                    lastResizableImg.resizable( "destroy" );
                    lastResizableImg.removeData('resizable');
                    lastResizableImg = null;
                }

                var enableImageResize = function() {
                    $('.wysiwyg-editor')
                        .on('mousedown', function(e) {
                            var target = $(e.target);
                            if( e.target instanceof HTMLImageElement ) {
                                if( !target.data('resizable') ) {
                                    target.resizable({
                                        aspectRatio: e.target.width / e.target.height,
                                    });
                                    target.data('resizable', true);

                                    if( lastResizableImg != null ) {//disable previous resizable image
                                        lastResizableImg.resizable( "destroy" );
                                        lastResizableImg.removeData('resizable');
                                    }
                                    lastResizableImg = target;
                                }
                            }
                        })
                        .on('click', function(e) {
                            if( lastResizableImg != null && !(e.target instanceof HTMLImageElement) ) {
                                destroyResizable();
                            }
                        })
                        .on('keydown', function() {
                            destroyResizable();
                        });
                }

                enableImageResize();

                /**
                 //or we can load the jQuery UI dynamically only if needed
                 if (typeof jQuery.ui !== 'undefined') enableImageResize();
                 else {//load jQuery UI if not loaded
        			$.getScript($path_assets+"/js/jquery-ui-1.10.3.custom.min.js", function(data, textStatus, jqxhr) {
        				if('ontouchend' in document) {//also load touch-punch for touch devices
        					$.getScript($path_assets+"/js/jquery.ui.touch-punch.min.js", function(data, textStatus, jqxhr) {
        						enableImageResize();
        					});
        				} else	enableImageResize();
        			});
        		}
                 */
            }
        }
    };
}]); directives.directive('c2Ueditor', ['FormContainerFactory','$timeout', function (FormContainerFactory,$timeout) {
    return {
    	scope:{
    		ngModel:"="
    	},
    	controller:function($scope,$element,$attrs){
    		var form = FormContainerFactory.findForm($scope);
    		form[$attrs.id] = this;
    		var _this=this;
    		
    		this.setUE=function(ue){
    			_this.ue=ue;
    		};
    		
    		this.getUE=function(){
    			return _this.ue; 
    		}
    	},
        link: function (scope, element, attrs,ctrl) {
        	var ue = UE.getEditor(element[0],{
        		  autoHeightEnabled: true
        	});
        	
        	var contentChanged=false;
        
        	scope.$watch('ngModel',function(nv,ov){
        		ue.ready(function() {
	        		if(!contentChanged && angular.isDefined(nv)){
	        			//设置编辑器的内容
	            		ue.setContent(scope.ngModel);	
	        		}
        		});
        	});
        	
        	ue.addListener( 'contentChange', function( data ) {
        		contentChanged=true;
        		$timeout(function(){
        			scope.ngModel=ue.getContent();
        		});
        	 });
        	
        	ue.addListener('blur',function(editor){
        		contentChanged=true;
        		$timeout(function(){
        			scope.ngModel=ue.getContent();
        		});
        	});
        	
        	ctrl.setUE(ue);
        }
    };
}]);directives.directive('c2ImageInput', ['$rootScope','FormContainerFactory','$upload','Messenger','$timeout',
                                     function ($rootScope,FormContainerFactory,$upload,Messenger,$timeout) {
return {
	scope:{
		binding:'='
	},
	compile:function($element,$attrs){
		var maxSize=$attrs.maxSize;
		var submit_url=$attrs.targetUrl;
		var id=$attrs.id;
		
		return function($scope,$element,$attrs){
			var formScope=FormContainerFactory.findFormScope($scope);
			try {//ie8 throws some harmless exceptions, so let's catch'em
				//first let's add a fake appendChild method for Image element for browsers that have a problem with this
				//because editable plugin calls appendChild, and it causes errors on IE at unpredicted points
				try {
					document.createElement('IMG').appendChild(document.createElement('B'));
				} catch(e) {
					Image.prototype.appendChild = function(el){}
				}
				
				formScope.$state[id]={imgUrl:"ext/widget/image_input/img/noimage.gif"};
				$scope.$watch('binding',function(value){
					if(angular.isDefined(value)){
						if(value.indexOf('http://')==0){
							formScope.$state[id].imgUrl=value;
						}else{
							formScope.$state[id].imgUrl=submit_url+"/"+value;
						}
					}
				});
				var $img=$element.children("img").first();
				
				$img.editable({
					type: 'image',
					name: 'file',
					value: null,
					image: {
						//specify ace file input plugin's options here
						btn_choose: '选择图片',
						maxSize: maxSize, 

						//and a few extra ones here
						name: 'file',//put the field name here as well, will be used inside the custom plugin
						on_error : function(error_type) {//on_error function will be called when the selected file has a problem
							if(error_type == 1) {//file format error
								Messenger.error("文件不是图片，请选择jpg|gif|png格式的文件")
							} else if(error_type == 2) {//file size rror
								Messenger.error("图片文件太大，只支持上传10M一下的图片");
							} else {//other error
							}
						},
						on_success : function() {
							alert('文件上传成功')
						}
					},
				    url: function(params) {
						// ***UPDATE AVATAR HERE*** //
						//for a working upload example you can replace the contents of this function with 
						//examples/profile-avatar-update.js

						var deferred = null;

						//if value is empty (""), it means no valid files were selected
						//but it may still be submitted by x-editable plugin
						//because "" (empty string) is different from previous non-empty value whatever it was
						//so we return just here to prevent problems
						var value = $img.next().find('input[type=hidden]:eq(0)').val();
						if(!value || value.length == 0) {
							deferred = new $.Deferred
							deferred.resolve();
							return deferred.promise();
						}
						
						var $form = $img.next().find('.editableform:eq(0)')
						var file_input = $form.find('input[type=file]:eq(0)');
						var pk = $img.attr('data-pk');//primary key to be sent to server

						if( "FormData" in window ) {
							var formData_object = new FormData();//create empty FormData object
							
							//serialize our form (which excludes file inputs)
							$.each($form.serializeArray(), function(i, item) {
								//add them one by one to our FormData 
								formData_object.append(item.name, item.value);							
							});
							//and then add files
							$form.find('input[type=file]').each(function(){
								var field_name = $(this).attr('name');
								var files = $(this).data('ace_input_files');
								if(files && files.length > 0) {
									formData_object.append(field_name, files[0]);
								}
							});

							deferred = $.ajax({
										url: submit_url,
									   type: 'POST',
								processData: false,//important
								contentType: false,//important
								   dataType: 'text',//server response type
									   data: formData_object
							})
						}
						else {
							deferred = new $.Deferred

							var temporary_iframe_id = 'temporary-iframe-'+(new Date()).getTime()+'-'+(parseInt(Math.random()*1000));
							var temp_iframe = 
									$('<iframe id="'+temporary_iframe_id+'" name="'+temporary_iframe_id+'" \
									frameborder="0" width="0" height="0" src="about:blank"\
									style="position:absolute; z-index:-1; visibility: hidden;"></iframe>')
									.insertAfter($form);
									
							$form.append('<input type="hidden" name="temporary-iframe-id" value="'+temporary_iframe_id+'" />');
							
							temp_iframe.data('deferrer' , deferred);
							//we save the deferred object to the iframe and in our server side response
							//we use "temporary-iframe-id" to access iframe and its deferred object

							$form.attr({
									  action: submit_url,
									  method: 'POST',
									 enctype: 'multipart/form-data',
									  target: temporary_iframe_id //important
							});

							$form.get(0).submit();
						}

						deferred.done(function(result) {
							var reusltObj=undefined;
							try{
								reusltObj=$.parseJSON(result);
							}catch(e){}
							
							if(reusltObj){
								$scope.binding=reusltObj.path;
							}else{
								$scope.binding=result;
							}
							$scope.$apply();
						}).fail(function(result) {
							alert("There was an error");
						});

						return deferred.promise();
					},
					
					success: function(response, newValue) {
					}
				});
			}catch(e) {};
		};
	}
}

}]);
directives.directive('c2FileInputChunk', ['$rootScope','FormContainerFactory','Messenger','$timeout','$ocLazyLoad',
                                     function ($rootScope,FormContainerFactory,Messenger,$timeout,$ocLazyLoad) {
    return {
    	scope:{
    		onSuccess:'&',
    		onError:'&',
    		targetUrl:'@',
    		allow:'@',
    		deny:'@'
    	},
    	controller:function($scope,$element,$attrs){
    		
    		var form = FormContainerFactory.findForm($scope);
    		form[$attrs.id] = this;

    		this.setTargetUrl = function(_url){$scope.targetUrl = _url;};
    		$scope.showRemoveButton = angular.isDefined($attrs.showRemoveButton);
    		$scope.autoSubmit = angular.isDefined($attrs.autoSubmit);
    		
    		var fileUpload=undefined;
    		var messenger=undefined;
    		
    		this.getChunkUpLoader=function(){
    			return $scope.chunkUpLoader;
    		}
    		
    		this.submit = function(){
    			var stats=$scope.chunkUpLoader.getStats();
    			if(stats.progressNum>0){
    				return false;
    			}else if(stats.uploadFailNum>0){
    				$scope.chunkUpLoader.retry();
    			}else{
    				$scope.chunkUpLoader.upload();
    			}
    			
    			this.showProgress();
    			this.showLoadingDiv();
    			
    			return true;
    		}
    		
    		this.hideLoadingDiv=function(){
    			var groupDom=form.mainForm.getDom().find("#formGroup-"+$attrs.id);
    			groupDom.find(".message-loading-overlay").remove();
    		}
    		
    		this.showLoadingDiv=function(){
        		var groupDom=form.mainForm.getDom().find("#formGroup-"+$attrs.id);
        		groupDom.append("<div style=\"filter: alpha(opacity=50); opacity: 0.5; background-color: #000;\" class=\"message-loading-overlay\"><i class=\"fa-spin ace-icon fa fa-spinner orange2 bigger-160\"></i></div>");
    		}

    		this.updateProgress=function(percentage){
    			if(undefined==messenger) return;
    			$(messenger.el).find("#upload_msg_"+$attrs.id).text("文件上传中，进度:"+parseInt(percentage * 100) +"%");
    		}
    		
    		this.setCookie=function(name,value){
    			var exdate=new Date();
    			exdate.setDate(exdate.getDate() + 7);
    			document.cookie=name+ "=" + escape(value)+";expires="+exdate.toGMTString();
    		}
    		
    		this.getCookie=function(key){
    		　　　　if (document.cookie.length>0){
    		　　　　　　c_start=document.cookie.indexOf(key + "=")　　
    		　　　　　　if (c_start!=-1){ 
    		　　　　　　　　c_start=c_start + key.length+1
    		　　　　　　　　c_end=document.cookie.indexOf(";",c_start)
    		　　　　　　　　if (c_end==-1) c_end=document.cookie.length　　
    		　　　　　　　　return unescape(document.cookie.substring(c_start,c_end))
    		　　　　　　}
    		　　　　}
    		　　　　return undefined;
    		}
    		
    		this.updateMessager=function(options){
    			messenger.update(options);
    		}
    		
    		this.showProgress=function(){
    			
    			messenger=Messenger.post({
      		       message: "<span style=\"font-size: larger;\" id='upload_msg_"+$attrs.id+"'>文件准备开始上传，请稍等...</span>",
      		      hideAfter:false,
      		       actions: {
      		        btnStop: {
      		          label: "<span id='op'>暂停<span>",
      		          action: function(event,b){
      		        	var textSpan=$(event.currentTarget).find("span#op");
      		        	if("暂停"==textSpan.text()){
      		        		stop();
      		        		textSpan.text("继续");
      		        	}else if("继续"==textSpan.text()){
      		        		retry();
      		        		textSpan.text("暂停");
      		        	}
      		          }
      		        },
      		        btnCancel: {
    		          label: '取消',
    		          action: function(a,b){
    		        	 cancel();
    		          }
        		    }
      		      }
      		    });
    		}
    		
    		this.hideProgress=function(){
    			if(undefined!=messenger){
    				messenger.hide();
    			}
    			this.hideLoadingDiv();
    		}
    		
    		var stop=function(){    			
    			$scope.chunkUpLoader.stop(true);
    		}
    		this.stop=stop;
    		
    		var retry=function(){
    			$scope.chunkUpLoader.retry();
    		}
    		this.retry=retry;
    		
    		var cancel=function(){
    			$scope.chunkUpLoader.stop(true);
    			if(undefined!=messenger){
    				messenger.hide();
    			}
    			var groupDom=form.mainForm.getDom().find("#formGroup-"+$attrs.id);
    			groupDom.find(".message-loading-overlay").remove();
    		}
    		this.cancel=cancel;
    		
    		this.abort=function(){
    			$scope.chunkUpLoader.stop(true);
    			this.hideProgress();
    		};
    		
    		this.reset=function(){
    			$element.find(".ace-file-input .ace-file-container").removeClass("selected");
    			$($element.find(".ace-file-name .ace-icon")[0]).removeClass("fa-file");
    			$($element.find(".ace-file-name .ace-icon")[0]).addClass("fa-upload");
    			$($element.find(".ace-file-name")[0]).attr("data-title","未选中文件 ...");
    			$scope.chunkUpLoader.reset();
    		}
    		
    		this.getValue=function(){
    			return $scope.chunkUpLoader.getFiles()[0];
    		}
    		
    		
    		//自定义验证黑名单
    		this.validateDeny=function(file){
    			
    			var rExt = /\.\w+$/;
    			if(!$scope.deny){
    				return true;
    			}
    			
    			var accept=undefined;
    			try{
    				var denyArray=angular.fromJson($scope.deny);
    				 accept = '\\.' + denyArray.join(',')
                    .replace( /,/g, '$|\\.' )
                    .replace( /\*/g, '.*' ) + '$';
                    accept = new RegExp( accept, 'i' );
    			}catch(e){}
    			
                var invalid = !file || !file.size || accept &&
                rExt.exec( file.name ) && !accept.test( file.name );
                
                return invalid;
    		}
    		
    	},
    	link:function(scope, element, attrs,controller){
    		
    		var promise = $ocLazyLoad.load(['ext/widget/file_input_chunk/lib/webuploader.js', 'ext/widget/file_input_chunk/lib/webuploader.css' ]);
			promise.then(function() {
					

					$(function() {
						// 检测是否已经安装flash，检测flash的版本
						flashVersion = ( function() {
						    var version;
						
						    try {
						        version = navigator.plugins[ 'Shockwave Flash' ];
						        version = version.description;
						    } catch ( ex ) {
						        try {
						            version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
						                    .GetVariable('$version');
						        } catch ( ex2 ) {
						            version = '0.0';
						        }
						    }
						    version = version.match( /\d+/g );
						    return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
						} )();
						
						if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {
						
						    // flash 安装了但是版本过低。
						    if (flashVersion) {
						        (function(container) {
						            window['expressinstallcallback'] = function( state ) {
						                switch(state) {
						                    case 'Download.Cancelled':
						                        alert('您取消了更新！')
						                        break;
						
						                    case 'Download.Failed':
						                        alert('安装失败')
						                        break;
						
						                    default:
						                        alert('安装已成功，请刷新！');
						                        break;
						                }
						                delete window['expressinstallcallback'];
						            };
						
						            var swf = 'ext/widget/file_input_chunk/lib/expressInstall.swf';
						            // insert flash object
						            var html = '<object type="application/' +
						                    'x-shockwave-flash" data="' +  swf + '" ';
						
						            if (WebUploader.browser.ie) {
						                html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
						            }
						
						            html += 'width="100%" height="100%" style="outline:0">'  +
						                '<param name="movie" value="' + swf + '" />' +
						                '<param name="wmode" value="transparent" />' +
						                '<param name="allowscriptaccess" value="always" />' +
						            '</object>';
						
						            container.html(html);
						
						        })(element);
						
						    // 压根就没有安转。
						    } else {
						    	element.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
						    }
						
						    return;
						} else if (!WebUploader.Uploader.support()) {
						    alert( 'Web Uploader 不支持您的浏览器！');
						    return;
						}
						
						WebUploader.Uploader.unRegister("chunkBSF");
		    			WebUploader.Uploader.register({
		    			    'before-send-file': 'preupload',
		    			    'name':'chunkBSF'
		    			}, {
		    				preupload: function(file,arg1,arg2){
		    			        var me = this,
		    			            owner = this.owner,
		    			            server = me.options.server,
		    			            deferred = WebUploader.Deferred();
		    			        
		    		            var start =  +new Date();
		    		            
		    		            // 返回的是 promise 对象
		    		            owner.md5File(file, 0, 1 * 1024 * 1024).then(function(ret) {
		    		                    // console.log('md5:', ret);
		    		                    var end = +new Date();
		    		                    
		    		                    file.md5Str=ret;
		    		                    
		    		                    console.log('HTML5: md5 ' + file.name + ' cost ' + (end - start) + 'ms get value: ' + ret);
		    		                    
		    			    			//上传前cookie判断此文件是否有过断点记录
		    			    			var cookieUid=controller.getCookie(attrs.id+"_"+ret);
		    			    			if(!cookieUid){
		    			    				cookieUid=WebUploader.Base.guid();
		    			    			}
		    			    			
		    			    			owner.option("formData",{guid:cookieUid});
		    			    			
		    			    			//不分片，直接返回
		    		                    if("true"!=attrs.chunked){
		    		                    	deferred.resolve();
		    		                    	return;
		    		                    }
		    		                    
		    	    			        //与服务安验证
				    			        $.ajax("chunkfile/"+attrs.process+"/getChunksByGuidAndName",{
				    			            dataType: 'json',
				    			            data: {
				    			                guid:cookieUid,
				    			                fileName:file.name,
				    			                md5:ret,
				    			                _t:Math.random()
				    			            },
				    			            success: function( response ){
				    			            	scope.uploadeChunks=response.chunks;
				    			       			owner.chunks=response.chunks;
				    			                deferred.resolve();
				    			            },
				    			            error:function(response){
				    			            	deferred.reject();
				    			            }
				    			        });
		    		            });
		    		            
	
		    			        return deferred.promise();
		    			    }
		    			});
		
		    			WebUploader.Uploader.unRegister("chunkBS");
		    			WebUploader.Uploader.register({
		    			    'before-send': 'preupload',
		    			    'name':'chunkBS'
		    			}, {
		    				preupload: function( file ) {
		    			        var me = this,
		    			            owner = this.owner,
		    			            server = me.options.server,
		    			            deferred = WebUploader.Deferred();
		    					
		    					if($.inArray(file.chunk+"",scope.uploadeChunks)==-1){
		    						deferred.resolve();
		    					}else{
		    						//console.log("跳过分片:"+file.chunk);
		    						deferred.reject();
		    					}
		    			        return deferred.promise();
		    			    }
		    			});
		    			
		    			var options={
				    		    // swf文件路径
				    		    swf: 'ext/widget/file_input_chunk/lib/Uploader.swf',
				    		    // 文件接收服务端。
				    		    server: "chunkfile/"+attrs.process+"/upload",
				    		    //runtimeOrder:'flash',
				    		    chunkSize: 5*1024*1024,
				    		    threads:1,
				    		    chunkRetry:0,
				    		    fileNumLimit:1,
				    		    fileSingleSizeLimit:attrs.maxSize
				    	}
		    			
		    			if("true"==attrs.chunked){
		    				options.chunked=true;
		    			}
		    			
		    			if(scope.allow){
		    				try{
		    					var allowStr=angular.fromJson(scope.allow).join(",");
		    					options.accept={
		    							title:allowStr,
		    							extensions:allowStr
		    					};
		    				}catch(e){}
		    			}
		    			
		    			
			    		var chunkUpLoader = WebUploader.create(options);
			    					    		
			    		scope.chunkUpLoader=chunkUpLoader;
			    		
			    		var btnStr="<label class=\"ace-file-input\" style=\"display: block;\">"+
											"<span class=\"ace-file-container\" data-title=\"浏览...\">"+
											"<span class=\"ace-file-name\" data-title=\"未选中文件 ...\">"+
											"<i class=\"ace-icon fa fa-upload\"></i></span>"+
											"</span>"+
											"<a class=\"remove\" style=\"z-index:1;\" href=\"javascript:void(0);\">"+
												"<i class=\" ace-icon fa fa-times\"></i>"+
											"</a>"+
										"</label>";
			    		
			    		var btn=$("<div class=\"chunk_btn\" style=\"width:100%\"></div>");
			    		
			    		chunkUpLoader.addButton({
						    id: element,
						    button:btn,
						    innerHTML:btnStr,
						    style:false,
						    multiple:false
						});
					
			    		chunkUpLoader.on( 'beforeFileQueued', function( file,arg2,arg3){
			    			chunkUpLoader.reset();
			    		    return true;
			    		});
			    		
			    		chunkUpLoader.on('reset', function( file,arg2,arg3 ){
			    			
			    		});
			    		
			    		//当有文件被添加进队列的时候
			    		chunkUpLoader.on( 'fileQueued', function(file,arg1,arg2){
			    			
			    			controller.hideProgress();
			    			
			    			element.find(".ace-file-input .ace-file-container").addClass("selected");
			    			$(element.find(".ace-file-name")[0]).attr("data-title",file.name);
			    			$(element.find(".ace-file-name .ace-icon")[0]).removeClass("fa-upload");
			    			$(element.find(".ace-file-name .ace-icon")[0]).addClass("fa-file");
			    			$(element.find(".ace-file-input .remove")[0]).on("click",function(){
			    				controller.reset();
			    			});
			    			
		        			if(scope.autoSubmit){
			        			$timeout(function() {
			        				controller.submit();
			        			});
		        			}
			    		});
			    		
			    		chunkUpLoader.on('uploadSuccess', function (file,response) {
			    			
			    		    controller.updateProgress(1);
			    		    
			    		    //已经上传完成
			    		    if(response&&response.data&&response.data.url){

			    		    	controller.hideProgress();
			    		    	
    		     		    	if(angular.isFunction(scope.onSuccess)){
    		     		    		scope.onSuccess({data:response.data, file:file});
    				     		}
			    		    	return;
			    		    }
			    		    
			    		    controller.updateMessager({type:'success',message:'上传完成，等待后台保存处理...',actions:{}});
			    		    
	    			        //合并分片
	    			        $.ajax("chunkfile/"+attrs.process+"/mergeFiles",{
	    			            dataType: 'json',
	    			            data: {
	    			                guid:this.option("formData").guid,
	    			                fileName:file.name,
	    			                _t:Math.random()
	    			            },
	    			            success: function(response,arg1,arg2){
	    			            	controller.hideProgress();
	    		     		    	if(angular.isFunction(scope.onSuccess)){
	    		     		    		scope.onSuccess({data:response.data, file:file});
	    				     		}
	    			            },
	    			            error:function(response){
	    			            	controller.hideProgress();
	    		      		    	if(angular.isFunction(scope.onError)){
	    			      		    	scope.onError({file:file,errmsg:file.statusText});
	    			      		    }
	    			            }
	    			        });
			    		});
			    		
			    		chunkUpLoader.onUploadProgress = function( file, percentage ) {
			    			//console.log("percentage:"+percentage);
			    			controller.updateProgress(percentage);
			    		};
			    		
			    		
			    		chunkUpLoader.on( 'startUpload',function(){
			    			controller.showLoadingDiv();
			    		});
			    		
			    		
			    		chunkUpLoader.on( 'stopUpload',function(){
			    			
			    			controller.hideLoadingDiv();
			    			
			    			//将uuid写入cookie，以便断点续传
			    			var file=this.getFiles()[0];
			    			
			    			if(file.md5Str){
			    				controller.setCookie(attrs.id+"_"+file.md5Str,this.option("formData").guid);
			    			}
			    		});
			    		
			    		
			    		chunkUpLoader.on( 'uploadError',function(f,errmsg){
			    			
			    			controller.hideProgress();
			    			
			    			//将uuid写入cookie，以便断点续传
			    			if(f.md5Str){
			    				controller.setCookie(attrs.id+"_"+f.md5Str,this.option("formData").guid);
			    			}
			    			
		      		    	if(angular.isFunction(scope.onError)){
			      		    	scope.onError({file:f,errmsg:errmsg});
			      		    }
				    		return false;
			    		});
			    		
			    		
			    		chunkUpLoader.on( 'beforeFileQueued',function(file){
			    			
			    			var invalid=controller.validateDeny(file);
	                        
	                        if(!invalid){
		                        var msgStr="不支持上传该类型的文件";
		                        if(scope.allow) msgStr+="，允许"+scope.allow;
		                        if(scope.deny) msgStr+="，禁止"+scope.deny;
	                        	Messenger.error(msgStr);
	                        }
	                        
				    		return invalid;
			    		});
			    		
			    		chunkUpLoader.on('error',function(eType){
			    			
			    			controller.hideProgress();
			    			
			    			if("Q_TYPE_DENIED"==eType){
		                        var msgStr="不支持上传该类型的文件";
		                        if(scope.allow) msgStr+="，允许"+scope.allow;
		                        if(scope.deny) msgStr+="，禁止"+scope.deny;
	                        	Messenger.error(msgStr);
			    			}else if("F_EXCEED_SIZE"==eType){
			    				var maxSize=parseInt(attrs.maxSize);
		    					var sizeLabel=attrs.maxSize+"字节";
		    					if(maxSize>=1024&&maxSize<(1024*1024)){
		    						var sizeLabel=(maxSize/1024)+"KB";
		    					}else if(maxSize>=(1024*1024)){
		    						var sizeLabel=(maxSize/(1024*1024))+"MB";
		    					}
		    					Messenger.error("文件大小不能超过:"+sizeLabel);
			    			}
			    		});
					});
					
					
			});
	    		
    	}
    };
}]);var C2SingleUploadFileIframe = function (scope, element, attrs) {
    this.$dom = element;
    this.$scope = scope;
    this.attrs = attrs;
    this.params=undefined;
};

C2SingleUploadFileIframe.prototype = {
	getDom:function(){
		return this.$dom;
	},
    upload: function (params,scEvent,erEvent){
		this.params=params;
        this.$scope.upload(scEvent,erEvent);
    },
    getValue:function(){
    	return this.$scope.formObj.find("input[c2-single-upload-file-iframe]").val();
    },
    setProcess:function(process){
    	var submitUrl="iframefile/"+process+"/upload";
    	this.$scope.formObj.attr("action",submitUrl);
    },
    setTargetUrl:function(url){
    	this.$scope.formObj.attr("action",url);
    },
    setParams:function(params){
    	this.params=params;
    },
    reset:function(){
    	var fileObj=$(this.$scope.formObj).find(".ace-file-input input");
    	if(fileObj&&fileObj.length>0){
    		$(fileObj[0]).wrap("<form>").parent().get(0).reset()
			$(fileObj[0]).unwrap();
    		$(fileObj[0]).ace_file_input('reset_input_ui');
    	}
    },
	submit: function (params,scEvent,erEvent){
		this.params=params;
        this.$scope.upload(scEvent,erEvent);
    },
};

directives.directive('c2SingleUploadFileIframe', ['$rootScope','FormContainerFactory','Messenger','$timeout',
                                      function ($rootScope,FormContainerFactory,Messenger,$timeout) {
 return {
 	scope:{
 		onSuccess:'&',
 		onError:'&',
		allow:'@',
		deny:'@'
 	},
    controller: function($scope,$attrs){
    	
    	$scope.instanceId=$attrs.id+"_"+new Date().getTime()+"_"+Math.floor((Math.random()*10000000000));

    	$scope.showRemoveButton = angular.isDefined($attrs.showRemoveButton);
    	
    	$scope.uploading=false;
    	
    	$scope.successCallback=function(data){

    		$scope.uploading=false;
    		
    		$scope.widgetContainer.find(".message-loading-overlay").remove();
    		
    		if($scope.successEvent){
    			$scope.successEvent(data.data);
    		}
    		
    		if(!angular.isUndefined($scope.onSuccess)){
    			$scope.onSuccess(data);
    		}
    	}
    	
    	$scope.errorCallback=function(data){

    		$scope.uploading=false;
    		
    		$scope.widgetContainer.find(".message-loading-overlay").remove();
    		
    		if($scope.errorEvent){
    			$scope.errorEvent(data.data);
    		}
    		
    		if(!angular.isUndefined($scope.onError)){
    			$scope.onError(data);
    		}
    	}
    	
    	$scope.upload=function(scEvent,erEvent){
    		
    		var fileDom=$scope.widgetContainer.find("input[type='file']");
    		
    		//提交前验证
    		if(fileDom.length>1){
    			Messenger.post({type:'error',message:"【"+$attrs.id+"】控件ID冲突，请检查表单控件ID唯一性！"});
    			return;
    		}
    		
    		if($scope.uploading){
    			Messenger.post({type:'error',message:'正在上传,请耐心等待!'});
    			return;
    		}
    		
			if(fileDom.val()==undefined||fileDom.val()==""){
				Messenger.post({type:'error',message:"附件不能为空！"});
				return;
			}

			//准备动态参数
			if($scope.c2SingleUploadFileIframe.params instanceof Object){
				for (name in $scope.c2SingleUploadFileIframe.params) {
					if($scope.c2SingleUploadFileIframe.params.hasOwnProperty(name)){
						var f=$scope.formObj.find("#"+name);
						if(f.length>0){
							f.val($scope.c2SingleUploadFileIframe.params[name]);	
						}else{
							$scope.formObj.append("<input type=\"hidden\" id=\""+name+"\" name=\""+name+"\" value=\""+$scope.c2SingleUploadFileIframe.params[name]+"\">");
						}
					}
				}
			}
			
			//js动态注册事件
    		if(scEvent!=undefined&&$.isFunction(scEvent)){
    			$scope.successEvent=scEvent;
    		}
    		if(erEvent!=undefined&&$.isFunction(erEvent)){
    			$scope.errorEvent=erEvent;
    		}
    		
    		$scope.uploading=true;
    		
    		$scope.widgetContainer.append("<div style=\"filter: alpha(opacity=50); opacity: 0.5; background-color: #000;\" class=\"message-loading-overlay\"><i class=\"fa-spin ace-icon fa fa-spinner orange2 bigger-160\"></i></div>");
    		$scope.formObj.submit();
    	}
	},
	template:"<span id=\"content\"></span>",
 	compile:function($element,$attrs){
 		return function($scope,$element,$attrs){
 			
 			//为控件打上实例标记
 			$element.attr("data-instanceid",$scope.instanceId);
 			
 			var submitUrl="iframefile/"+$attrs.process+"/upload";
 			var iframeObj;
 			var formObj;
 			
 			
 			var form=FormContainerFactory.findForm($scope);
 			
 			//如果同一个表单中存在多个，[$view.控件id]变为数组类型
 			var widgetHandle=form[$attrs.id];
 			$scope.c2SingleUploadFileIframe=new C2SingleUploadFileIframe($scope, $element,$attrs);
 			
 			if(widgetHandle){
 				if($.isArray(widgetHandle)){
 					widgetHandle.push($scope.c2SingleUploadFileIframe);
 				}else{
 					var arr=new Array();
 					arr.push(widgetHandle);
 					arr.push($scope.c2SingleUploadFileIframe);
 					form[$attrs.id]=arr;
 				}
 			}else{
 				form[$attrs.id] =$scope.c2SingleUploadFileIframe;
 			}

            $scope.autoSubmit=$attrs.autoSubmit;
            
 			var opts={
                    no_file: '未选中文件 ...',
                    btn_choose: '浏览...',
                    btn_change: '修改...',
                    droppable: false,
                    thumbnail: false,
                    icon_remove: $scope.showRemoveButton?'fa fa-times':false
                };
        	if(angular.isDefined($scope.allow)){
        		opts.allowExt=angular.fromJson($scope.allow);
        	}
        	if(angular.isDefined($scope.deny)){
        		opts.denyExt=angular.fromJson($scope.deny);
        	}
        	
 			var iframeId="upload_iframe_"+$scope.instanceId;
 			var formId="upload_form_"+$scope.instanceId;
    		if($element.find("#content #"+iframeId).length>0){
    			iframeObj=$element.find("#content #"+iframeId);
    		}else{
    			iframeObj=$element.find("#content").append("<iframe name=\""+iframeId+"\" id=\""+iframeId+"\" style=\"display:none\"></iframe>");
    		}
    		
    		
    		if($element.find("#content #"+formId).length>0){
    			formObj=$element.find("#content #"+formId);
    			formObj.empty();
    		}else{
    			formObj=$("<form action=\""+submitUrl+"\" id=\""+formId+"\" name=\""+formId+"\" encType=\"multipart/form-data\"  method=\"post\" target=\""+iframeId+"\">" +
    					"</form>");
    			$element.find("#content").append(formObj);
    		}
    		
    		var fileElement=$("<input name=\"file_"+$scope.instanceId+"+\" id=\"file_"+$scope.instanceId+"+\" type=\"file\"/>");
    		formObj.append(fileElement);

    		fileElement.ace_file_input(opts).on('file.error.ace',function(){
        		Messenger.error("不支持上传该类型的文件，只支持: "+$scope.allow);
        	});
    		
			formObj.append("<input type=\"hidden\" name=\"controlId\" value=\""+$attrs.id+"\">");
			formObj.append("<input type=\"hidden\" name=\"instanceId\" value=\""+$scope.instanceId+"\">");
			
			if(!angular.isUndefined($attrs.maxSize)){
				formObj.append("<input type=\"hidden\" id=\"maxSize\" name=\"maxSize\" value=\""+$attrs.maxSize+"\">");
			}
    		$scope.iframeObj=iframeObj;
    		$scope.formObj=formObj;
    		
    		$scope.widgetContainer=$element;
    		
    		fileElement.bind('change', function(evt) {
        		
        		//提交前验证
    			if($(this).val()==undefined||$(this).val()==""){
    				$scope.formObj.parent().find(".remove").click();
    				return;
    			}
       			
    			//html5验证大小
    			if(this.files&&this.files.length>0){
    				$attrs.maxSize=parseInt($attrs.maxSize);
    				if(!angular.isNumber($attrs.maxSize)){
    					$attrs.maxSize=undefined;
    				}
    				
    				if(this.files[0].size<=0){
    					Messenger.error("文件大小不能为空");
    					$scope.formObj.parent().find(".remove").click();
    					return;
    				}
    				
    				if($attrs.maxSize&&this.files[0].size>$attrs.maxSize){
    					var sizeLabel=$attrs.maxSize+"字节";
    					if($attrs.maxSize>=1024&&$attrs.maxSize<(1024*1024)){
    						var sizeLabel=($attrs.maxSize/1024)+"KB";
    					}else if($attrs.maxSize>=(1024*1024)){
    						var sizeLabel=($attrs.maxSize/(1024*1024))+"MB";
    					}
    					Messenger.error("文件大小不能超过"+sizeLabel);
    					$scope.formObj.parent().find(".remove").click();
    					return;
    				}
    			}
    			
    			if($scope.autoSubmit!=undefined){
        			$timeout(function() {
        				$scope.upload();
        			});
    			}
    		});
    		if (('ontouchstart' in window) ||
    				(navigator.maxTouchPoints > 0) || (navigator.msMaxTouchPoints > 0)) {
    				fileElement.bind('touchend', function(e) {
        				e.preventDefault();
        				e.target.click();
        			});
    			}
            }
 		}
 	}
 }]);var C2MultipleUploadFileIframe = function (scope, element, attrs) {
    this.$dom = element;
    this.$scope = scope;
    this.attrs = attrs;
    this.params=undefined;
};
C2MultipleUploadFileIframe.prototype = {
	getDom:function(){
		return this.$dom;
	},
	submit: function (params,scEvent,erEvent){
		this.params=params;
        this.$scope.upload(scEvent,erEvent);
    },
    getValue:function(){
    	return this.$scope.fileList;
    },
    disable:function(flag){
    	this.$scope.disable(flag);
    },
    setTargetUrl:function(url){
    	var directiveId=this.attrs.id;
    	$("#upload_form_"+directiveId).attr("action",url);
    },
    setParams:function(params){
    	this.params=params;
    },
    reset:function(){
    	this.$scope.formObj.find(".file-input").remove();
    	this.$scope.fileList=[];
    }
};

directives.directive('c2MultipleUploadFileIframe', ['$rootScope','FormContainerFactory','Messenger','$timeout',
                                      function ($rootScope,FormContainerFactory,Messenger,$timeout) {
 return {
 	scope:{
 		onSuccess:'&',
 		onError:'&'
 	},
    controller: function($scope,$attrs){
    	$scope.instanceId=$attrs.id+"_"+new Date().getTime()+"_"+Math.floor((Math.random()*10000000000));
    	var directiveId=$attrs.id;
    	$scope.title=$attrs.title;
    	$scope.uploading=false;
    	$scope.fileList=[];
    	
    	$scope.disabled=($attrs.disabled=="true");
    	
    	$scope.disable=function(flag){
    		//#attr("ng-disabled='${param.disabled}'" $!{param.disabled})
    		if(true==flag){
    			$scope.disabled=true;
    			$scope.widgetContainer.find(".file-input").css({display:"none"});
    			$scope.widgetContainer.find(".glyphicon-remove").removeClass("red");
    		}else{
    			$scope.widgetContainer.find(".file-input").css({display:"block"});
    			$scope.widgetContainer.find(".glyphicon-remove").addClass("red");
    			$scope.disabled=false;
    		}
    	}
    	
    	$scope.addFile=function(){
    		
    		//创建临时文件选择控件
    		var fileObjId=$scope.instanceId+"_"+new Date().getTime();
    		var fileObj;
    		fileObj=$("<input id=\""+fileObjId+"\" name=\""+fileObjId+"\"class=\"file-input\" type=\"file\"></input>");
    		fileObj.on("change",function(evt) {

    				//添加时验证
    				if($.isNumeric($attrs.maxNum)){
    					var fNum=$scope.formObj.find(".file-input").length;
    					if(parseInt($attrs.maxNum)>0&&fNum+1>parseInt($attrs.maxNum)){
  					      Messenger.post({type:'error',message:'您最多添加【'+$attrs.maxNum+'】个附件！'});
  					      return;
    					}
    				}
    			
        			var fileName=evt.target.value;
        			if(""==fileName){
        				return;
        			}
        			fileName=fileName.match(/[^\\]*$/)[0];
        			
        			//验证文件后缀
        			if(angular.isDefined($attrs.allow)||angular.isDefined($attrs.deny)){
        				var extStart = fileName.lastIndexOf(".");
                        var ext = fileName.substring(extStart+1,fileName.length).toUpperCase();
                        if(extStart==-1){
                        	if(angular.isDefined($attrs.allow)){
                        		Messenger.error("不支持上传该类型的文件，只支持: "+$attrs.allow);
                        		return;
                        	}
                        }
                        
                        var isInDeny=false;
                        if(angular.isDefined($attrs.deny)){
                        	var deny=angular.fromJson($attrs.deny);
                        	for (name in deny){
                            	if(deny[name].toUpperCase()==ext){
                            		isInDeny=true;
                            		break;
                            	}
                            }
                        	
                        	if(isInDeny){
                        		Messenger.error("不支持上传该类型的文件: "+$attrs.deny);
                        		return;
                        	}
                        }
                        
                        var isInAllow=false;
                        if(angular.isDefined($attrs.allow)){
                        	var allow=angular.fromJson($attrs.allow);
                        	for (name in allow){
                            	if(allow[name].toUpperCase()==ext){
                            		isInAllow=true;
                            		break;
                            	}
                            }
                        	
                        	if(!isInAllow){
                        		Messenger.error("不支持上传该类型的文件，只支持: "+$attrs.allow);
                        		return;
                        	}
                        }
        			}
        			
        			//html5验证大小
        			if(this.files&&this.files.length>0){
        				$attrs.maxSize=parseInt($attrs.maxSize);
        				if(!angular.isNumber($attrs.maxSize)){
        					$attrs.maxSize=undefined;
        				}
        				
        				if(this.files[0].size<=0){
        					Messenger.error("文件大小不能为空");
        					$(this).wrap("<form>").parent().get(0).reset()
        					$(this).unwrap();
        					return;
        				}
        				
        				if($attrs.maxSize&&this.files[0].size>$attrs.maxSize){
        					var sizeLabel=$attrs.maxSize+"字节";
        					if($attrs.maxSize>=1024&&$attrs.maxSize<(1024*1024)){
        						var sizeLabel=($attrs.maxSize/1024)+"KB";
        					}else if($attrs.maxSize>=(1024*1024)){
        						var sizeLabel=($attrs.maxSize/(1024*1024))+"MB";
        					}
        					Messenger.error("文件大小不能超过"+sizeLabel);
        					$(this).wrap("<form>").parent().get(0).reset()
        					$(this).unwrap();
        					return;
        				}
        			}
        			
        			var fdata={
        					domId:fileObjId,
        					name:fileName,
        					isDown:false,
        					isDelete:false
        			}
        			
        			
        			if(!angular.isUndefined($attrs.isDown)&&$attrs.isDown=="true"){
        				fdata.isDown=true;
        			}
        			
        			if(!angular.isUndefined($attrs.isDelete)&&$attrs.isDelete=="true"){
        				fdata.isDelete=true;
        			}
        			$scope.fileList.push(fdata);
        			
        			//将input移动form下
        			$("#"+fileObjId).attr("style","display:none");
        			$scope.formObj.append($("#"+fileObjId));
        			
    			    if ($scope.$$phase != '$apply' &&  $scope.$$phase != '$digest') {
    				   $scope.$apply();
    			    }
    			    
    			    $scope.addFile();
        			
        		});
    		
    		//初始化input
    		var fbtn=$scope.widgetContainer.find(".fileinput-button");
    		if(fbtn.length<=0){
    			throw new Error("文件上传模板不正确");
    		}
    		
    		fbtn.append(fileObj);
    		//$scope.formObj.append(fileObj);
    	}
    	
    	$scope.deleteFile=function(domId){
    		
    		if($scope.disabled) return;
    		
    		$("#"+domId).remove();
    		for (var i=0;i<$scope.fileList.length;i++){
    			if($scope.fileList[i].domId==domId){
    				$scope.fileList.splice(i,1);
    			}
    		}
    	}
    	
    	$scope.successCallback=function(data){
    		
    		$scope.uploading=false;
    		
    		$scope.widgetContainer.find(".message-loading-overlay").remove();
    		
    		if($scope.successEvent){
    			$scope.successEvent(data.data);
    		}
    		
    		if(!angular.isUndefined($scope.onSuccess)){
    			$scope.onSuccess(data);
    		}
    	}
    	
    	$scope.errorCallback=function(data){
    		
    		$scope.uploading=false;
    		
    		$scope.widgetContainer.find(".message-loading-overlay").remove();
    		
    		if($scope.errorEvent){
    			$scope.errorEvent(data.data);
    		}
    		
    		if(!angular.isUndefined($scope.onError)){
    			$scope.onError(data);
    		}
    	}
    	
    	$scope.upload=function(scEvent,erEvent){
    		
    		if($scope.uploading){
    			Messenger.post({type:'error',message:'正在上传,请耐心等待!'});
    			return;
    		}
    		
    		//提交前验证
			if($.isNumeric($attrs.minNum)){
				var fNum=$scope.formObj.find(".file-input").length;
				if(parseInt($attrs.minNum)>0&&fNum<parseInt($attrs.minNum)){
				      Messenger.post({type:'error',message:'您至少添加【'+$attrs.minNum+'】个附件！'});
				      return;
				}
			}
			
	    	
			if($scope.c2MultipleUploadFileIframe.params instanceof Object){
				for (name in $scope.c2MultipleUploadFileIframe.params) {
					if($scope.c2MultipleUploadFileIframe.params.hasOwnProperty(name)){
						var f=$scope.formObj.find("#"+name);
						if(f.length>0){
							f.val($scope.c2MultipleUploadFileIframe.params[name]);	
						}else{
							$scope.formObj.append("<input type=\"hidden\" id=\""+name+"\" name=\""+name+"\" value=\""+$scope.c2MultipleUploadFileIframe.params[name]+"\">");
						}
					}
				}
			}
		
			//js动态注册事件
    		if(scEvent!=undefined&&$.isFunction(scEvent)){
    			$scope.successEvent=scEvent;
    		}
    		if(erEvent!=undefined&&$.isFunction(erEvent)){
    			$scope.errorEvent=erEvent;
    		}
    		
    		$scope.widgetContainer.append("<div  style=\"filter: alpha(opacity=50); opacity: 0.5; background-color: #000;\" class=\"message-loading-overlay\"><i class=\"fa-spin ace-icon fa fa-spinner orange2 bigger-160\"></i></div>");
    		
    		$scope.uploading=true;
    		$scope.formObj.submit();
    	}
	},
	templateUrl:'widget_multiple_file_input_iframe',
 	compile:function($element,$attrs){
	 		return function($scope,$element,$attrs){
	 			
	 			//为控件打上实例标记
	 			$element.attr("data-instanceid",$scope.instanceId);
	 			
	 			var submitUrl="iframefile/"+$attrs.process+"/upload";
	 			var iframeObj;
	 			var formObj;
	 			
	 			var form=FormContainerFactory.findForm($scope);
	 			
	 			//如果存在多个，[$view.控件id]变为数组类型
	 			var widgetHandle=form[$attrs.id];
	 			$scope.c2MultipleUploadFileIframe=new C2MultipleUploadFileIframe($scope,$element,$attrs)
	 			if(widgetHandle){
	 				if($.isArray(widgetHandle)){
	 					widgetHandle.push($scope.c2MultipleUploadFileIframe);
	 				}else{
	 					var arr=new Array();
	 					arr.push(widgetHandle);
	 					arr.push($scope.c2MultipleUploadFileIframe);
	 					form[$attrs.id]=arr;
	 				}
	 			}else{
	 				form[$attrs.id] = $scope.c2MultipleUploadFileIframe;
	 			}
	 			
	 			
	 			
	    		
	 			var iframeId="upload_iframe_"+$scope.instanceId;
	 			var formId="upload_form_"+$scope.instanceId;
	    		if($element.parent().find("#"+iframeId).length>0){
	    			iframeObj=$element.parent().find("#"+iframeId);
	    		}else{
	    			iframeObj=$("<iframe name=\""+iframeId+"\" id=\""+iframeId+"\" style=\"display:none\"></iframe>");
	    			$element.append(iframeObj);
	    		}
	    		
	    		if($element.parent().find("#"+formId).length>0){
	    			formObj=$element.parent().find("#"+formId);
	    			formObj.empty();
	    		}else{
	    			formObj=$("<form action=\""+submitUrl+"\" id=\""+formId+"\" name=\""+formId+"\" encType=\"multipart/form-data\"  method=\"post\" target=\""+iframeId+"\">" +
	    					"</form>");
	    			$element.append(formObj);
	    		}
	    		
				formObj.append("<input type=\"hidden\" name=\"controlId\" value=\""+$attrs.id+"\">");
				formObj.append("<input type=\"hidden\" name=\"instanceId\" value=\""+$scope.instanceId+"\">");
				
				if(!angular.isUndefined($attrs.maxSize)){
					formObj.append("<input type=\"hidden\" name=\"maxSize\" value=\""+$attrs.maxSize+"\">");
				}
	    		$scope.iframeObj=iframeObj;
	    		$scope.formObj=formObj;
	    		
	    		$scope.widgetContainer=FormContainerFactory.findForm($scope).mainForm.getDom().find("[data-instanceid='"+$scope.instanceId+"']");
	    		
	    		$scope.addFile();
	    		
	    		if("true"==$attrs.disabled){
	    			$scope.disable(true);
	    		}
	 		}
 		}
 	}
 }]);