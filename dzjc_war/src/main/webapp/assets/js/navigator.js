/**
 * menu-aim is a jQuery plugin for dropdown menus that can differentiate
 * between a user trying hover over a dropdown item vs trying to navigate into
 * a submenu's contents.
 *
 * menu-aim assumes that you have are using a menu with submenus that expand
 * to the menu's right. It will fire events when the user's mouse enters a new
 * dropdown item *and* when that item is being intentionally hovered over.
 *
 * __________________________
 * | Monkeys  >|   Gorilla  |
 * | Gorillas >|   Content  |
 * | Chimps   >|   Here     |
 * |___________|____________|
 *
 * In the above example, "Gorillas" is selected and its submenu content is
 * being shown on the right. Imagine that the user's cursor is hovering over
 * "Gorillas." When they move their mouse into the "Gorilla Content" area, they
 * may briefly hover over "Chimps." This shouldn't close the "Gorilla Content"
 * area.
 *
 * This problem is normally solved using timeouts and delays. menu-aim tries to
 * solve this by detecting the direction of the user's mouse movement. This can
 * make for quicker transitions when navigating up and down the menu. The
 * experience is hopefully similar to amazon.com/'s "Shop by Department"
 * dropdown.
 *
 * Use like so:
 *
 *      $("#menu").menuAim({
 *          activate: $.noop,  // fired on row activation
 *          deactivate: $.noop  // fired on row deactivation
 *      });
 *
 *  ...to receive events when a menu's row has been purposefully (de)activated.
 *
 * The following options can be passed to menuAim. All functions execute with
 * the relevant row's HTML element as the execution context ('this'):
 *
 *      .menuAim({
 *          // Function to call when a row is purposefully activated. Use this
 *          // to show a submenu's content for the activated row.
 *          activate: function() {},
 *
 *          // Function to call when a row is deactivated.
 *          deactivate: function() {},
 *
 *          // Function to call when mouse enters a menu row. Entering a row
 *          // does not mean the row has been activated, as the user may be
 *          // mousing over to a submenu.
 *          enter: function() {},
 *
 *          // Function to call when mouse exits a menu row.
 *          exit: function() {},
 *
 *          // Selector for identifying which elements in the menu are rows
 *          // that can trigger the above events. Defaults to "> li".
 *          rowSelector: "> li",
 *
 *          // You may have some menu rows that aren't submenus and therefore
 *          // shouldn't ever need to "activate." If so, filter submenu rows w/
 *          // this selector. Defaults to "*" (all elements).
 *          submenuSelector: "*",
 *
 *          // Direction the submenu opens relative to the main menu. Can be
 *          // left, right, above, or below. Defaults to "right".
 *          submenuDirection: "right"
 *      });
 *
 * https://github.com/kamens/jQuery-menu-aim
*/
(function($) {

    $.fn.menuAim = function(opts) {
        // Initialize menu-aim for all elements in jQuery collection
        this.each(function() {
            init.call(this, opts);
        });

        return this;
    };

    function init(opts) {
        var $menu = $(this),
            activeRow = null,
            mouseLocs = [],
            lastDelayLoc = null,
            timeoutId = null,
            options = $.extend({
                rowSelector: "> li",
                submenuSelector: "*",
                submenuDirection: "right",
                tolerance: 75,  // bigger = more forgivey when entering submenu
                enter: $.noop,
                exit: $.noop,
                activate: $.noop,
                deactivate: $.noop,
                exitMenu: $.noop
            }, opts);

        var MOUSE_LOCS_TRACKED = 3,  // number of past mouse locations to track
            DELAY = 300;  // ms delay when user appears to be entering submenu

        /**
         * Keep track of the last few locations of the mouse.
         */
        var mousemoveDocument = function(e) {
                mouseLocs.push({x: e.pageX, y: e.pageY});

                if (mouseLocs.length > MOUSE_LOCS_TRACKED) {
                    mouseLocs.shift();
                }
            };

        /**
         * Cancel possible row activations when leaving the menu entirely
         */
        var mouseleaveMenu = function() {
                if (timeoutId) {
                    clearTimeout(timeoutId);
                }

                // If exitMenu is supplied and returns true, deactivate the
                // currently active row on menu exit.
                if (options.exitMenu(this)) {
                    if (activeRow) {
                        options.deactivate(activeRow);
                    }

                    activeRow = null;
                }
            };

        /**
         * Trigger a possible row activation whenever entering a new row.
         */
        var mouseenterRow = function() {
                if (timeoutId) {
                    // Cancel any previous activation delays
                    clearTimeout(timeoutId);
                }

                options.enter(this);
                possiblyActivate(this);
            },
            mouseleaveRow = function() {
                options.exit(this);
            };

        /*
         * Immediately activate a row if the user clicks on it.
         */
        var clickRow = function() {
                activate(this);
            };

        /**
         * Activate a menu row.
         */
        var activate = function(row) {
                if (row == activeRow) {
                    return;
                }

                if (activeRow) {
                    options.deactivate(activeRow);
                }

                options.activate(row);
                activeRow = row;
            };

        /**
         * Possibly activate a menu row. If mouse movement indicates that we
         * shouldn't activate yet because user may be trying to enter
         * a submenu's content, then delay and check again later.
         */
        var possiblyActivate = function(row) {
                var delay = activationDelay();

                if (delay) {
                    timeoutId = setTimeout(function() {
                        possiblyActivate(row);
                    }, delay);
                } else {
                    activate(row);
                }
            };

        /**
         * Return the amount of time that should be used as a delay before the
         * currently hovered row is activated.
         *
         * Returns 0 if the activation should happen immediately. Otherwise,
         * returns the number of milliseconds that should be delayed before
         * checking again to see if the row should be activated.
         */
        var activationDelay = function() {
                if (!activeRow || !$(activeRow).is(options.submenuSelector)) {
                    // If there is no other submenu row already active, then
                    // go ahead and activate immediately.
                    return 0;
                }

                var offset = $menu.offset(),
                    upperLeft = {
                        x: offset.left,
                        y: offset.top - options.tolerance
                    },
                    upperRight = {
                        x: offset.left + $menu.outerWidth(),
                        y: upperLeft.y
                    },
                    lowerLeft = {
                        x: offset.left,
                        y: offset.top + $menu.outerHeight() + options.tolerance
                    },
                    lowerRight = {
                        x: offset.left + $menu.outerWidth(),
                        y: lowerLeft.y
                    },
                    loc = mouseLocs[mouseLocs.length - 1],
                    prevLoc = mouseLocs[0];

                if (!loc) {
                    return 0;
                }

                if (!prevLoc) {
                    prevLoc = loc;
                }

                if (prevLoc.x < offset.left || prevLoc.x > lowerRight.x ||
                    prevLoc.y < offset.top || prevLoc.y > lowerRight.y) {
                    // If the previous mouse location was outside of the entire
                    // menu's bounds, immediately activate.
                    return 0;
                }

                if (lastDelayLoc &&
                        loc.x == lastDelayLoc.x && loc.y == lastDelayLoc.y) {
                    // If the mouse hasn't moved since the last time we checked
                    // for activation status, immediately activate.
                    return 0;
                }

                // Detect if the user is moving towards the currently activated
                // submenu.
                //
                // If the mouse is heading relatively clearly towards
                // the submenu's content, we should wait and give the user more
                // time before activating a new row. If the mouse is heading
                // elsewhere, we can immediately activate a new row.
                //
                // We detect this by calculating the slope formed between the
                // current mouse location and the upper/lower right points of
                // the menu. We do the same for the previous mouse location.
                // If the current mouse location's slopes are
                // increasing/decreasing appropriately compared to the
                // previous's, we know the user is moving toward the submenu.
                //
                // Note that since the y-axis increases as the cursor moves
                // down the screen, we are looking for the slope between the
                // cursor and the upper right corner to decrease over time, not
                // increase (somewhat counterintuitively).
                function slope(a, b) {
                    return (b.y - a.y) / (b.x - a.x);
                };

                var decreasingCorner = upperRight,
                    increasingCorner = lowerRight;

                // Our expectations for decreasing or increasing slope values
                // depends on which direction the submenu opens relative to the
                // main menu. By default, if the menu opens on the right, we
                // expect the slope between the cursor and the upper right
                // corner to decrease over time, as explained above. If the
                // submenu opens in a different direction, we change our slope
                // expectations.
                if (options.submenuDirection == "left") {
                    decreasingCorner = lowerLeft;
                    increasingCorner = upperLeft;
                } else if (options.submenuDirection == "below") {
                    decreasingCorner = lowerRight;
                    increasingCorner = lowerLeft;
                } else if (options.submenuDirection == "above") {
                    decreasingCorner = upperLeft;
                    increasingCorner = upperRight;
                }

                var decreasingSlope = slope(loc, decreasingCorner),
                    increasingSlope = slope(loc, increasingCorner),
                    prevDecreasingSlope = slope(prevLoc, decreasingCorner),
                    prevIncreasingSlope = slope(prevLoc, increasingCorner);

                if (decreasingSlope < prevDecreasingSlope &&
                        increasingSlope > prevIncreasingSlope) {
                    // Mouse is moving from previous location towards the
                    // currently activated submenu. Delay before activating a
                    // new menu row, because user may be moving into submenu.
                    lastDelayLoc = loc;
                    return DELAY;
                }

                lastDelayLoc = null;
                return 0;
            };

        /**
         * Hook up initial menu events
         */
        $menu
            .mouseleave(mouseleaveMenu)
            .find(options.rowSelector)
                .mouseenter(mouseenterRow)
                .mouseleave(mouseleaveRow)
                .click(clickRow);

        $(document).mousemove(mousemoveDocument);

    };
})(jQuery);


// tipsy, facebook style tooltips for jquery
// version 1.0.0a
// (c) 2008-2010 jason frame [jason@onehackoranother.com]
// released under the MIT license

(function($) {
    
    function maybeCall(thing, ctx) {
        return (typeof thing == 'function') ? (thing.call(ctx)) : thing;
    };
    
    function isElementInDOM(ele) {
      while (ele = ele.parentNode) {
        if (ele == document) return true;
      }
      return false;
    };
    
    function Tipsy(element, options) {
        this.$element = $(element);
        this.options = options;
        this.enabled = true;
        this.fixTitle();
    };
    
    Tipsy.prototype = {
        show: function() {
            var title = this.getTitle();
            if (title && this.enabled) {
                var $tip = this.tip();
                
                $tip.find('.tipsy-inner')[this.options.html ? 'html' : 'text'](title);
                $tip[0].className = 'tipsy'; // reset classname in case of dynamic gravity
                $tip.remove().css({top: 0, left: 0, visibility: 'hidden', display: 'block'}).prependTo(document.body);
                
                var pos = $.extend({}, this.$element.offset(), {
                    width: this.$element[0].offsetWidth,
                    height: this.$element[0].offsetHeight
                });
                
                var actualWidth = $tip[0].offsetWidth,
                    actualHeight = $tip[0].offsetHeight,
                    gravity = maybeCall(this.options.gravity, this.$element[0]);
                
                var tp;
                switch (gravity.charAt(0)) {
                    case 'n':
                        tp = {top: pos.top + pos.height + this.options.offset, left: pos.left + pos.width / 2 - actualWidth / 2};
                        break;
                    case 's':
                        tp = {top: pos.top - actualHeight - this.options.offset, left: pos.left + pos.width / 2 - actualWidth / 2};
                        break;
                    case 'e':
                        tp = {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left - actualWidth - this.options.offset};
                        break;
                    case 'w':
                        tp = {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left + pos.width + this.options.offset};
                        break;
                }
                
                if (gravity.length == 2) {
                    if (gravity.charAt(1) == 'w') {
                        tp.left = pos.left + pos.width / 2 - 15;
                    } else {
                        tp.left = pos.left + pos.width / 2 - actualWidth + 15;
                    }
                }
                
                $tip.css(tp).addClass('tipsy-' + gravity);
                $tip.find('.tipsy-arrow')[0].className = 'tipsy-arrow tipsy-arrow-' + gravity.charAt(0);
                if (this.options.className) {
                    $tip.addClass(maybeCall(this.options.className, this.$element[0]));
                }
                
                if (this.options.fade) {
                    $tip.stop().css({opacity: 0, display: 'block', visibility: 'visible'}).animate({opacity: this.options.opacity});
                } else {
                    $tip.css({visibility: 'visible', opacity: this.options.opacity});
                }
            }
        },
        
        hide: function() {
            if (this.options.fade) {
                this.tip().stop().fadeOut(function() { $(this).remove(); });
            } else {
                this.tip().remove();
            }
        },
        
        fixTitle: function() {
            var $e = this.$element;
            if ($e.attr('title') || typeof($e.attr('original-title')) != 'string') {
                $e.attr('original-title', $e.attr('title') || '').removeAttr('title');
            }
        },
        
        getTitle: function() {
            var title, $e = this.$element, o = this.options;
            this.fixTitle();
            var title, o = this.options;
            if (typeof o.title == 'string') {
                title = $e.attr(o.title == 'title' ? 'original-title' : o.title);
            } else if (typeof o.title == 'function') {
                title = o.title.call($e[0]);
            }
            title = ('' + title).replace(/(^\s*|\s*$)/, "");
            return title || o.fallback;
        },
        
        tip: function() {
            if (!this.$tip) {
                this.$tip = $('<div class="tipsy"></div>').html('<div class="tipsy-arrow"></div><div class="tipsy-inner"></div>');
                this.$tip.data('tipsy-pointee', this.$element[0]);
            }
            return this.$tip;
        },
        
        validate: function() {
            if (!this.$element[0].parentNode) {
                this.hide();
                this.$element = null;
                this.options = null;
            }
        },
        
        enable: function() { this.enabled = true; },
        disable: function() { this.enabled = false; },
        toggleEnabled: function() { this.enabled = !this.enabled; }
    };
    
    $.fn.tipsy = function(options) {
        
        if (options === true) {
            return this.data('tipsy');
        } else if (typeof options == 'string') {
            var tipsy = this.data('tipsy');
            if (tipsy) tipsy[options]();
            return this;
        }
        
        options = $.extend({}, $.fn.tipsy.defaults, options);
        
        function get(ele) {
            var tipsy = $.data(ele, 'tipsy');
            if (!tipsy) {
                tipsy = new Tipsy(ele, $.fn.tipsy.elementOptions(ele, options));
                $.data(ele, 'tipsy', tipsy);
            }
            return tipsy;
        }
        
        function enter() {
            var tipsy = get(this);
            tipsy.hoverState = 'in';
            if (options.delayIn == 0) {
                tipsy.show();
            } else {
                tipsy.fixTitle();
                setTimeout(function() { if (tipsy.hoverState == 'in') tipsy.show(); }, options.delayIn);
            }
        };
        
        function leave() {
            var tipsy = get(this);
            tipsy.hoverState = 'out';
            if (options.delayOut == 0) {
                tipsy.hide();
            } else {
                setTimeout(function() { if (tipsy.hoverState == 'out') tipsy.hide(); }, options.delayOut);
            }
        };
        
        if (!options.live) this.each(function() { get(this); });
        
        if (options.trigger != 'manual') {
            var binder   = options.live ? 'live' : 'bind',
                eventIn  = options.trigger == 'hover' ? 'mouseenter' : 'focus',
                eventOut = options.trigger == 'hover' ? 'mouseleave' : 'blur';
            this[binder](eventIn, enter)[binder](eventOut, leave);
        }
        
        return this;
        
    };
    
    $.fn.tipsy.defaults = {
        className: null,
        delayIn: 0,
        delayOut: 0,
        fade: false,
        fallback: '',
        gravity: 'n',
        html: false,
        live: false,
        offset: 0,
        opacity: 0.8,
        title: 'title',
        trigger: 'hover'
    };
    
    $.fn.tipsy.revalidate = function() {
      $('.tipsy').each(function() {
        var pointee = $.data(this, 'tipsy-pointee');
        if (!pointee || !isElementInDOM(pointee)) {
          $(this).remove();
        }
      });
    };
    
    // Overwrite this method to provide options on a per-element basis.
    // For example, you could store the gravity in a 'tipsy-gravity' attribute:
    // return $.extend({}, options, {gravity: $(ele).attr('tipsy-gravity') || 'n' });
    // (remember - do not modify 'options' in place!)
    $.fn.tipsy.elementOptions = function(ele, options) {
        return $.metadata ? $.extend({}, options, $(ele).metadata()) : options;
    };
    
    $.fn.tipsy.autoNS = function() {
        return $(this).offset().top > ($(document).scrollTop() + $(window).height() / 2) ? 's' : 'n';
    };
    
    $.fn.tipsy.autoWE = function() {
        return $(this).offset().left > ($(document).scrollLeft() + $(window).width() / 2) ? 'e' : 'w';
    };
    
    /**
     * yields a closure of the supplied parameters, producing a function that takes
     * no arguments and is suitable for use as an autogravity function like so:
     *
     * @param margin (int) - distance from the viewable region edge that an
     *        element should be before setting its tooltip's gravity to be away
     *        from that edge.
     * @param prefer (string, e.g. 'n', 'sw', 'w') - the direction to prefer
     *        if there are no viewable region edges effecting the tooltip's
     *        gravity. It will try to vary from this minimally, for example,
     *        if 'sw' is preferred and an element is near the right viewable 
     *        region edge, but not the top edge, it will set the gravity for
     *        that element's tooltip to be 'se', preserving the southern
     *        component.
     */
     $.fn.tipsy.autoBounds = function(margin, prefer) {
		return function() {
			var dir = {ns: prefer[0], ew: (prefer.length > 1 ? prefer[1] : false)},
			    boundTop = $(document).scrollTop() + margin,
			    boundLeft = $(document).scrollLeft() + margin,
			    $this = $(this);

			if ($this.offset().top < boundTop) dir.ns = 'n';
			if ($this.offset().left < boundLeft) dir.ew = 'w';
			if ($(window).width() + $(document).scrollLeft() - $this.offset().left < margin) dir.ew = 'e';
			if ($(window).height() + $(document).scrollTop() - $this.offset().top < margin) dir.ns = 's';

			return dir.ns + (dir.ew ? dir.ew : '');
		}
	};
    
})(jQuery);

function logout(){
    $.post("ws/logout",{}).success(function(data) {
			if (!data || !data.result) {
				 location.reload();
			}else{
				 location.href = data.result;
			}						
		});
};

function showPopover(){
	$('#popup').css({left: ($(window).width() - $('#popup').width()) / 2});
	$('#mask_shadow').show().animate({opacity: 1});
	$('#popup').show().animate({opacity: 1, top: 100});
}

var CepNavigator = {
    defaultActivated: true,
    init: function(opts) {
    	$.get(opts.url,function(data){
        	var topLeft = getTopLeft(data); //顶部左边菜单
            var gloalMenu = getGloalMenu(data.applications);//顶部全部菜单
            var topRight = getTopRight(data);//顶部右边菜单
            $(".viewFramework-topbar").append(topLeft+gloalMenu+topRight+"</div></div>");
            var content = getSidebarContent(data.sidebar,data.appid);
            $(".viewFramework-sidebar").append(content);
            if(getCookie("sidebar-type")==null || getCookie("sidebar-type")==data.user.id+'-mini'){
            	$(".viewFramework-body").removeClass("viewFramework-sidebar-full").addClass("viewFramework-sidebar-mini");
            	$(".viewFramework-body a.sidebar-trans").each(function() {
                	var target = $(this);
                	var title = target.find("span.nav-title")[0].innerHTML;
                	target.attr("original-title",title);
                })
                $("div.sidebar-title.sidebar-trans").each(function() {
                	var target = $(this);
                	var title = target.find('span.sidebar-title-text')[0].innerHTML;
                	target.attr("original-title",title);
                })
                $(".viewFramework-body a.sidebar-trans").tipsy({gravity: 'w'});
                $('div.sidebar-title.sidebar-trans').tipsy({gravity: 'w'});
                $(".console-component-topbar .topbar-head .topbar-btn:first").removeClass("topbar-logo-expand");
                $(".console-component-topbar .topbar-head .topbar-btn:first").addClass("topbar-logo");
            }else{
            	$(".viewFramework-body").removeClass("viewFramework-sidebar-mini").addClass("viewFramework-sidebar-full");
            	$(".viewFramework-body a.sidebar-trans").removeAttr("original-title");
                $('div.sidebar-title.sidebar-trans').removeAttr("original-title");
                $(".console-component-topbar .topbar-head .topbar-btn:first").removeClass("topbar-logo");
                $(".console-component-topbar .topbar-head .topbar-btn:first").addClass("topbar-logo-expand");
            }
            if(data.menu.title){
            	$(".viewFramework-product").append(getNavBar(data.menu));
            	$(".viewFramework-product").addClass("viewFramework-product-col-1");
            }
            getPointPopover(data.applications);
            if(data.user!=null&&data.user.avatar!=null){
            	if(data.user.avatar==''){
            		$('#userImg').attr('src',"/images/0.jpg");
            	}else{
            		$('#userImg').attr('src',data.user.avatar);
            	}
            }
            CepNavigator.initEvents(data.user.id);
            CepNavigator.initMenuAim();
        },'json');
    	
    	function getPointPopover(data){
    		$('.viewFramework-topbar').before("<div id='mask_shadow'></div>");
    		var gloalMenu = "<div class='topbar-product-container topbar-clearfix topbar-product-container-simple'>";
	    	gloalMenu += "<div class='topbar-product-nav topbar-left'><div class='topbar-product-nav-inner topbar-nav-buoy-wrap'><ul class='topbar-product-nav-list'>";
	    	for(var i=0;i<data.length;i++){
	    		var clas = "";
	    		if(i==0){
	    			clas = "active";
	    		}
	    		gloalMenu += "<li data-for='category"+(i+1)+"' class='"+clas+"'><span>"+data[i].name+"</span></li>";
	    	}
	    	gloalMenu += "</div></div></ul><div class='topbar-product-content topbar-product-category-wrap'>";
	    	for(var i=0;i<data.length;i++){
	    		var display = "none";
	    		if(i==0){
	    			display = "block";
	    		}
	    		gloalMenu += "<div id='category"+(i+1)+"' class='topbar-product-category' style='display: "+display+";'><div style='overflow: hidden;'>";
	    		var count = data[i].items.length%6==0?data[i].items.length/6:(data[i].items.length/6+1);
	    		for(var k=0;k<count;k++){
	    			gloalMenu += "<ul class='topbar-product-category-col topbar-left'>";
	    			var sum = data[i].items.length;
	    			if(data[i].items.length/(6*(k+1))>1){
	    				sum = 6*(k+1);
	    			}
	    			for(var j=6*k;j<sum;j++){
	    				var clas="";
	    				if(data[i].items[j].star){
	    					clas = "star-active";
	    				}
						gloalMenu += "<li class='product-item collect-apps-flex apps-sketch'><a class=' topbar-transition'>"+
							"<div class='collect-apps-1'><div class='product-name'>"+data[i].items[j].name+"</div></div></a><span class='collect-apps-0 fa fa-star-o f-16 star-overflow "+clas+"'></span>"+
							"<input type='hidden' value='"+data[i].items[j].id+"'></input></li>";
					}
	    			gloalMenu += "</ul>";
	    		}
	    		gloalMenu += " </div></div>";
	    	}
	    	gloalMenu += "</div>";
	    	$('#mask_shadow').after("<div id='popup'><div class='title'><p>收藏您常用的应用</p><span class='fa fa-close'></span></div><div class='cont'>"+gloalMenu+"</div></div>");
    	}
    	
        function getSidebarContent(data,appid){
        	var content = "<div class='sidebar-content console-component-sidebar'><div><div class='sidebar-inner'><div class='sidebar-fold fa fa-bars'></div>";
        	//应用部署和应用管理固定在底部
        	var list1 = [],list2 = [];
        	for(var i=0;i<data.length;i++){
        		for(var j=0;j<data[i].items.length;j++){
        			if(data[i].items[j].name=='应用部署'||data[i].items[j].name=='应用管理'){
        				list1.push(data[i].items[j]);
        			}else{
        				list2.push(data[i].items[j]);
        			}
        		}
        	}
        	content += "<div class='sidebar-nav sidebar-nav-active bottom-nav'><ul class='sidebar-trans'>";
        	for(var i=0;i<list1.length;i++){
        		var clas = "";
    			if(list1[i].appid == appid){
    				clas = "active";
    			}
				content += "<li class='nav-item "+clas+"'><a href='"+(list1[i].url?list1[i].url:'javascript:void(0)')+"' target='"+(list1[i].target?list1[i].target:'_self')+"' class='sidebar-trans' original-title='"+list1[i].name+"'><div class='nav-icon sidebar-trans'>"+
							"<span class='fa "+(list1[i].icon?list1[i].icon:"fa-th")+"'></span></div><span class='nav-title'>"+list1[i].name+"</span></a></li>";
        	}
        	content += "</ul></div>";
        	var wh = $(window).height()-80-list1.length*40;
	        var tc = list2.length*40+32;
	        var heigth = tc;
	        if(tc>wh){
	           heigth = wh;
	        }
        	content += "<div class='sidebar-nav sidebar-nav-active'><ul class='sidebar-trans' style='max-height: "+heigth+"px;'>";
        	for(var i=0;i<list2.length;i++){
        		var clas = "";
    			if(list2[i].appid == appid){
    				clas = "active";
    			}
				content += "<li class='nav-item "+clas+"'><a href='"+(list2[i].url?list2[i].url:'javascript:void(0)')+"' target='"+(list2[i].target?list2[i].target:'_self')+"' class='sidebar-trans' original-title='"+list2[i].name+"'><div class='nav-icon sidebar-trans'>"+
							"<span class='fa "+(list2[i].icon?list2[i].icon:"fa-th")+"'></span></div><span class='nav-title'>"+list2[i].name+"</span></a></li>";
        	}
        	content += "<li class='nav-item border'><a href='javascript:void(0)' onClick='showPopover()' class='sidebar-trans' original-title='收藏应用'><div class='nav-icon sidebar-trans'>"+
			"<span class='fa fa-plus'></span></div><span class='nav-title' onClick='showPopover()'>收藏应用</span></a></li>";
        	content += "</ul></div>";
        	/*for(var i=0;i<data.length;i++){
        		//重新计算高度值
        		var clas = "";
        		var heigth = 0;
        		var flag = false;
                for(var j=0;j<data[i].items.length;j++){
                    if(data[i].items[j].appid == appid){
        				flag = true;
                        break;
        			}
                }
        		if(flag){
        			clas = "sidebar-nav-active";
        			var wh = $(window).height()-80;
        	        var count = data.length*40;
        	        var tc = data.length>0?data[i].items.length*40:0;
        	        var heigth = tc;
        	        if(tc>(wh-count)){
        	           heigth = wh-count;
        	        }
        		}
        		content += "<div class='sidebar-nav "+clas+"'><div class='sidebar-title sidebar-trans' original-title='"+data[i].name+"'><div class='sidebar-title-inner'>"+
				"<span class='sidebar-title-icon fa fa-chevron-right'></span><span class='sidebar-title-text'>"+data[i].name+"</span>"+
				"</div></div><ul class='sidebar-trans' style='max-height: "+heigth+"px;'>";
        		var heigth = 0;
        		var wh = $(window).height()-80;
    	        var tc = data.length>0?data[i].items.length*40:0;
    	        var heigth = tc;
    	        if(tc>(wh)){
    	           heigth = wh-count;
    	        }
        		content += "<div class='sidebar-nav sidebar-nav-active'><ul class='sidebar-trans' style='max-height: "+heigth+"px;'>";
        		for(var j=0;j<data[i].items.length;j++){
        			var clas = "";
        			if(data[i].items[j].appid == appid){
        				clas = "active";
        			}
    				content += "<li class='nav-item "+clas+"'><a href='"+(data[i].items[j].url?data[i].items[j].url:'javascript:void(0)')+"' target='"+(data[i].items[j].target?data[i].items[j].target:'_self')+"' class='sidebar-trans' original-title='"+data[i].items[j].name+"'><div class='nav-icon sidebar-trans'>"+
    							"<span class='fa "+(data[i].items[j].icon?data[i].items[j].icon:"fa-th")+"'></span></div><span class='nav-title'>"+data[i].items[j].name+"</span></a></li>";
        		}
        		content += "</ul></div>";
        	}*/
        	content += "</div></div></div>";
        	return content;
        };

        function getGloalMenu(data){
        	var gloalMenu = "<div id='gloalMenu' class='topbar-product topbar-left'><div id='gloalMenuToggle' class='topbar-btn topbar-product-btn'>"+
        		"<span><i class='fa fa-th' style='color: #aeb9c2;font-size: 12px;margin-right: 5px;'></i>我的应用</span></div><div class='topbar-product-dropdown'>"+
        		"<div class='topbar-product-container topbar-clearfix topbar-product-container-simple'>";
        	gloalMenu += "<div class='topbar-product-nav topbar-left'><div class='topbar-product-nav-inner topbar-nav-buoy-wrap'><ul class='topbar-product-nav-list'>";
        	for(var i=0;i<data.length;i++){
        		var clas = "";
        		if(i==0){
        			clas = "active";
        		}
        		gloalMenu += "<li data-for='category"+(i+1)+"' class='"+clas+"'><span>"+data[i].name+"</span></li>";
        	}
        	gloalMenu += "</div></div></ul><div class='topbar-product-content topbar-product-category-wrap'>";
        	for(var i=0;i<data.length;i++){
        		var display = "none";
        		if(i==0){
        			display = "block";
        		}
        		gloalMenu += "<div id='category"+(i+1)+"' class='topbar-product-category' style='display: "+display+";'><div style='overflow: hidden;'>";
        		var count = data[i].items.length%6==0?data[i].items.length/6:(data[i].items.length/6+1);
        		for(var k=0;k<count;k++){
        			gloalMenu += "<ul class='topbar-product-category-col topbar-left'>";
        			var sum = data[i].items.length;
        			if(data[i].items.length/(6*(k+1))>1){
        				sum = 6*(k+1);
        			}
        			for(var j=6*k;j<sum;j++){
        				var clas="";
        				if(data[i].items[j].star){
        					clas = "star-active";
        				}
    					gloalMenu += "<li class='product-item collect-apps-flex apps-sketch'><a target='"+(data[i].items[j].target?data[i].items[j].target:'_self')+"' class=' topbar-transition' href='"+(data[i].items[j].url?data[i].items[j].url:'javascript:void(0)')+"'>"+
    						"<div class='collect-apps-1'><div class='product-name'>"+data[i].items[j].name+"</div><div class='product-description'>"+
    						(data[i].items[j].description?data[i].items[j].description:"")+"</div></div></a><span class='collect-apps-0 fa fa-star-o f-16 star-overflow "+clas+"'></span>"+
    						"<input type='hidden' value='"+data[i].items[j].id+"'></input></li>";
    				}
        			gloalMenu += "</ul>";
        		}
        		gloalMenu += " </div></div>";
        	}
        	gloalMenu += "</div><div class='topbar-product-close'><span class='fa fa-sort-asc'></span></div></div></div></div>";
        	return gloalMenu;
        };

        function getTopLeft(data){
        	var topLeft = "<div class='console-component-topbar' role='menubar'><div class='topbar-wrap topbar-clearfix' style='display: block;'>"+
        		"<div class='topbar-head topbar-left'><span class='topbar-btn topbar-logo-expand topbar-left'><img class='portal-logo' src='/images/portal-logo.png'/></span>"+
        		"<span title='"+data.appname+"' class='topbar-btn topbar-left topbar-name'>"+data.appname+"</span></div>";
            return topLeft;
        };
        	
        function getTopRight(data){
        	var topRight = "<div class='topbar-info topbar-right topbar-clearfix'>";
        	if(data.notice && data.notice.url!=null){
                //消息
                topRight += "<div class='topbar-notice topbar-left topbar-info-dropdown topbar-info-item'>"+
                "<a href='"+(data.notice.url?data.notice.url:'javascript:void(0)')+"' class='topbar-btn topbar-btn-notice topbar-hover-dark'>"+
                "<span class='topbar-btn-notice-icon'><i class='fa fa-bell'></i></span><span class='topbar-btn-notice-num'>"+
                data.notice.num+"</span></a><div class='topbar-notice-panel topbar-info-dropdown-memu'><div class='topbar-notice-arrow'></div>"+
                "<div class='topbar-notice-head'><span>消息通知</span><a href='"+(data.notice.head.url?data.notice.head.url:'javascript:void(0)')+"' style='font-size: 13px;float: right;'>"+
                data.notice.head.name+"</a></div><div class='topbar-notice-body'><ul>";
                for(var i=0;i<data.notice.contents.length;i++){
                    topRight += "<li><a href='"+data.notice.contents[i].url+"' target='"+data.notice.contents[i].target+"' class='clearfix'>"+
                    "<span class='inline-block'><span class='topbar-notice-link'>"+data.notice.contents[i].title+"</span><span class='topbar-notice-time'>"+
                    data.notice.contents[i].time+"</span></span></a></li>";
                }
                topRight += "</ul></div><div class='topbar-notice-foot'><a class='topbar-notice-more' target='"+data.notice.more.target+
                "' href='"+(data.notice.more.url?data.notice.more.url:'javascript:void(0)')+"'>查看更多</a></div></div></div>";
            }
            /*for(var i=0;i<data.topbar.length;i++){
                topRight += "<div class='topbar-left topbar-info-item topbar-info-dropdown'>"+
                    "<a href='"+data.topbar[i].url+"' target='_blank' class='topbar-btn topbar-info-dropdown-toggle'> "+
                    "<span>"+data.topbar[i].name+"</span></a>"
                if(data.topbar[i].items!=null&&data.topbar[i].items.length>0){
                    topRight += "<ul class='topbar-info-dropdown-memu topbar-info-dropdown-memu-list'>";
                    for(var j=0;j<data.topbar[i].items.length;j++){
                        topRight += "<li class='topbar-info-btn'><a href='"+(data.topbar[i].items[j].url?data.topbar[i].items[j].url:'javascript:void(0)')+"' target='_blank'>"+
                            "<span>"+data.topbar[i].items[j].name+"</span></a></li>";
                    }
                    topRight += "</ul>";
                }
                topRight += "</div>";
            }*/
            if(data.user!=null){
            	topRight += "<div class='topbar-left topbar-user'><div class='topbar-info-dropdown topbar-info-item'>"+
            	"<a class='topbar-info-dropdown-toggle topbar-btn'><img id='userImg'/><span title='"+data.user.name+"'>"+
            	data.user.name+"</span></a><div class='topbar-info-dropdown-memu topbar-align-right'>"+
            	"<div class='user-btn-list'><a class='user-btn-link' href='#/f/person'><span>个人资料</span></a>"+
            	"<a class='user-btn-link' href='javascript:void(0)'  onclick='logout()'><span>退出登录</span></a>"+
            	"</div></div></div></div></div>";
            }
            return topRight;
        };
        
        function getNavBar(data){
        	var navbar = "<div class='viewFramework-product-navbar'><div class='product-nav-stage product-nav-stage-main'>"+
        		"<div class='product-nav-scene product-nav-main-scene'><div class='product-nav-title' text-length='20'>"+data.title+"</div>"+
        		"<div class='product-nav-list'><ul>";
        	for(var i=0;i<data.items.length;i++){
        		var clas = "";
        		if(data.items[i].items!=null&&data.items[i].items.length>0){
    				navbar += "<li class='nav-showchild "+clas+"'><div><a href='"+(data.items[i].url?data.items[i].url:'javascript:void(0)')+"' target='"+(data.items[i].target?data.items[i].target:'_self')+"'><div class='nav-icon'><span class='fa fa-caret-down'></span></div>"+
        			"<div class='nav-title' text-length='20'>"+data.items[i].name+"</div></a></div><ul>";
    				for(var j=0;j<data.items[i].items.length;j++){
    					if(window.location.href.indexOf(data.items[i].items[j].url)>-1){
                			clas = "active";
                		}
    					navbar += "<li class='"+clas+"'><div><a href='"+(data.items[i].items[j].url?data.items[i].items[j].url:'javascript:void(0)')+"' target='"+(data.items[i].items[j].target?data.items[i].items[j].target:'_self')+"'><div class='nav-icon'></div><div class='nav-title' text-length='20'>"+
    					data.items[i].items[j].name+"</div></a></div></li>";
    				}
    				navbar += "</ul></li>";
    			}else{
    				if(window.location.href.indexOf(data.items[i].url)>-1){
            			clas = "active";
            		}
    				navbar += "<li class='"+clas+"'><div><a href='"+(data.items[i].url?data.items[i].url:'javascript:void(0)')+"' target='"+(data.items[i].target?data.items[i].target:'_self')+"'><div class='nav-icon'></div>"+
        			"<div class='nav-title' text-length='20'>"+data.items[i].name+"</div></a></div></li>";
    			}
        	}
        	navbar += "</ul></div></div></div></div>";
        	return navbar;
        };
        
    },
    initMenuAim: function() {
        $(".topbar-product-nav-list").menuAim({
            activate: function(element) {
                if (CepNavigator.defaultActivated) {
                    $(".topbar-product-nav-list li").each(function() {
                        $(this).removeClass("active");
                    });
                    $(".topbar-product-category").each(function() {
                        $(this).css("display", "none");
                    });
                    CepNavigator.defaultActivated = false;
                }
                var row = $(element);
                row.addClass("active");
                var id = row.data("for");
                $(".topbar-product-category#" + id).css("display", "block");
            },
            deactivate: function(element) {
                var row = $(element);
                row.removeClass("active");
                var id = row.data("for");
                $(".topbar-product-category#" + id).css("display", "none");
            }
        });
    },
    initEvents: function(userId) {
        $("#gloalMenuToggle").click(function() {　
        	event.stopPropagation();　//阻止冒泡事件　　　　
            if ($("#gloalMenu").hasClass("open")) {
                $("#gloalMenu").removeClass("open");
                $(".topbar-product-dropdown").removeClass("topbar-show");
            } else {
                $("#gloalMenu").addClass("open");
                $(".topbar-product-dropdown").addClass("topbar-show");
            }　　
        });
        $(".sidebar-fold").click(function() {
            if ($(".viewFramework-body").hasClass("viewFramework-sidebar-mini")) {
            	setCookie("sidebar-type",userId+"-full");
                $(".viewFramework-body").removeClass("viewFramework-sidebar-mini");
                $(".viewFramework-body").addClass("viewFramework-sidebar-full");
                $(".viewFramework-body a.sidebar-trans").removeAttr("original-title");
                $('div.sidebar-title.sidebar-trans').removeAttr("original-title");
                $(".console-component-topbar .topbar-head .topbar-btn:first").removeClass("topbar-logo");
                $(".console-component-topbar .topbar-head .topbar-btn:first").addClass("topbar-logo-expand");
            } else {
            	setCookie("sidebar-type",userId+"-mini");
                $(".viewFramework-body").addClass("viewFramework-sidebar-mini");
                $(".viewFramework-body a.sidebar-trans").each(function() {
                	var target = $(this);
                	var title = target.find("span.nav-title")[0].innerHTML;
                	target.attr("original-title",title);
                })
                $("div.sidebar-title.sidebar-trans").each(function() {
                	var target = $(this);
                	var title = target.find('span.sidebar-title-text')[0].innerHTML;
                	target.attr("original-title",title);
                })
                $(".viewFramework-body a.sidebar-trans").tipsy({gravity: 'w'});
                $('div.sidebar-title.sidebar-trans').tipsy({gravity: 'w'});
                $(".viewFramework-body").removeClass("viewFramework-sidebar-full");
                $(".console-component-topbar .topbar-head .topbar-btn:first").removeClass("topbar-logo-expand");
                $(".console-component-topbar .topbar-head .topbar-btn:first").addClass("topbar-logo");
            }
        });
        $(".topbar-product-close").click(function() {
            $("#gloalMenu").removeClass("open");
            $(".topbar-product-dropdown").removeClass("topbar-show");
        });
        //点击空白处时自动隐藏
        $(".topbar-product-dropdown").mouseleave(function() {
        	$("body").click(function(){
        		$("#gloalMenu").removeClass("open");
                $(".topbar-product-dropdown").removeClass("topbar-show");
        	});
        });
       /* $(".sidebar-title").click(function() {
            var target = $(this);
            var _parent = target.parent(".sidebar-nav");
            if (_parent.hasClass("sidebar-nav-active")) {
            	_parent.removeClass("sidebar-nav-active");
                _parent.find("ul.sidebar-trans").css("max-height", "0px");
            } else {
                $(".sidebar-nav").each(function() {
                    $(this).removeClass("sidebar-nav-active");
                    $(this).find("ul.sidebar-trans").css("max-height", "0px");
                })
                _parent.addClass("sidebar-nav-active");
                //重新计算高度值
                var wh = $(window).height()-80;
                var count = $(".sidebar-nav").length*40;
                var tc = _parent.find("li.nav-item").length*40;
                if(tc>(wh-count)){
                	_parent.find("ul.sidebar-trans").css("max-height", (wh-count)+"px");
                }else{
                	_parent.find("ul.sidebar-trans").css("max-height", tc+"px");
                }
            }
        });*/
        $(".nav-showchild").click(function(){
        	var target = $(this);
        	if(target.find("span.fa").hasClass("fa-caret-down")){
        		target.find("span.fa").removeClass("fa-caret-down").addClass("fa-caret-right");
        		target.find("ul").css("display","none");
        	}else{
        		target.find("span.fa").removeClass("fa-caret-right").addClass("fa-caret-down");
        		target.find("ul").css("display","block");
        	}
        });
        $(".nav-title").click(function(){
        	event.stopPropagation();　//阻止冒泡事件　　
        	var target = $(this);
            var _parent = target.parent().parent().parent();
            if(_parent.hasClass("nav-showchild")){
            	if(_parent.find("span.fa").hasClass("fa-caret-down")){
            		_parent.find("span.fa").removeClass("fa-caret-down").addClass("fa-caret-right");
            		_parent.find("ul").css("display","none");
            	}else{
            		_parent.find("span.fa").removeClass("fa-caret-right").addClass("fa-caret-down");
            		_parent.find("ul").css("display","block");
            	}
            }else{
            	$(".product-nav-list li").each(function() {
                	$(this).removeClass("active");
                })
                if (_parent.hasClass("active")) {
                	_parent.removeClass("active");
                } else {
                	_parent.addClass("active");
                }
            }
        });
        $(".star-overflow").click(function(){//标星的点击事件
        	var target = $(this);
        	if(target.hasClass("star-active")){
        		target.removeClass("star-active");
        		$.ajax({url: 'aip/v1/entrypoints/favorites?eid='+target.next().val(),type: 'DELETE'});
        		//$.delete("aip/v1/entrypoints/favorites?eid="+target.next().val());
        	}else{
        		target.addClass("star-active");
        		$.post("aip/v1/entrypoints/favorites?eid="+target.next().val());
        	}
        });
        $('#popup').find('.title').find('span').click(function(){//关闭收藏弹出框
        	$('#mask_shadow').hide().animate({opacity: 0});
        	$('#popup').hide().animate({opacity: 0, top: 150});
        });
        $('#popup').find('.title').on('mousedown', function (ev) {//收藏弹出框拖拽事件
        	var self = this;
            var oEvent = ev || window.event;
            var disX = oEvent.clientX - $('#popup').offset().left;
            var disY = oEvent.clientY - $('#popup').offset().top;
            var _move = true;

            $(document).mousemove(function (ev) {
            	if (_move) {
	                var oEvent = ev || window.event;
	                var offset_l = oEvent.clientX - disX;
	                var offset_t = oEvent.clientY - disY;
	
	                if (offset_l <= 0) {
	                    offset_l = 0;
	                } else if (offset_l >= self.page_w - $('#popup').width()) {
	                    offset_l = self.page_w - $('#popup').width();
	                }
	
	                if (offset_t <= 0) {
	                    offset_t = 0;
	                } else if (offset_t >= self.page_h - $('#popup').height()) {
	                    offset_t = self.page_h - $('#popup').height();
	                }
	
	                $('#popup').css({left: offset_l, top: offset_t});
              }
            }).mouseup(function () {
            	_move = false;
            });
        });
    }
}

//写cookies
function setCookie(name,value){
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
};

function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
    	return unescape(arr[2]);
    else
    	return null;
}