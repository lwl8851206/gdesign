	
/*动态加载图片插件*/
$.fn.loadImages = function() {
		var $window = $(window);
		var $outThis = this;
		
		$window.bind("scroll", function() {
			var scrollY = $window.scrollTop();
			var winHeight = $window.height();
			load(scrollY, winHeight);
		});
		
		var load = function(scrollY, winHeight) {
			
			
			$outThis.each(function() {

				$this = $(this);
				if(scrollY + winHeight < $this.offset().top)
					return;		
				$imgWraps = $this.find(".img-wrap");
				$imgWraps.each(function(index, imgWrap) {
					
				

 					var imgItem = imgWrap.querySelector("img");
					var $item = jQuery(imgItem);
					
					imgItem.src = $item.data("src");
					imgItem.onload = function() {
						
						var expandWhich = imgItem.height > imgItem.width ? "height" : "width";
						if ($(imgWrap).height() < imgItem[expandWhich])
							imgItem["style"][expandWhich] = "100%";	
							imgWrap.style.background = "#fff";
							$item.fadeIn();
					} 
				});
				
			});				
		}
		
		//第一次载入页面需要触发加载图片的js插件
		$window.trigger("scroll");
		

	}
	
