// JavaScript Document



//tab
$(function(){
	var $tab1= $('.tab1');
	$tab1.mouseover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab1.index(this);
		$('.tab2').eq(i).show().siblings('.tab2').hide();
	});
});

$(function(){
	var $tab2= $('.div1>a');
	$tab2.mouseover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab2.index(this);
		$('.div2').eq(i).show().siblings('.div2').hide();
	});
});

$(function(){
	var $tab3= $('.div1>a');
	$tab3.mouseover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab3.index(this);
		$('.div12').eq(i).show().siblings('.div12').hide();
	});
});

/*
initDataAnalyze();//第一个tab图表初始化
	$(".tabToggle").click(function () {
		if ($(this).val() == 1) {
			
			initUserAnalyze();//第二个tab图表初始化
		}
	});

*/



$(function(){
	var $tab4= $('.div1-1>a');
	$tab4.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab4.index(this);
		$('.div14').eq(i).addClass('vs').removeClass('vd');
		$('.div14').eq(i).siblings('.div14').addClass('vd').removeClass('vs');
	});
});
$(function(){
	var $tab5= $('.div11>a');
	$tab5.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab5.index(this);
		$('.div13').eq(i).addClass('vs').removeClass('vd');
		$('.div13').eq(i).siblings('.div13').addClass('vd').removeClass('vs');
	});
});

$(function(){
	var $tab15= $('.ss1');
	$tab15.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab15.index(this);
		$('.ss2').eq(i).addClass('vs').removeClass('vd');
		$('.ss2').eq(i).siblings('.ss2').addClass('vd').removeClass('vs');
	});
});
$(function(){
	$('.carouselUl1') .cycle({ 
		fx:'scrollLeft',
		timeout: 2000,
		pager:'.page1',
		speed:700, 
		randomizeEffects:1,
		prev:'.prev1',
		next:'.next1',
		pagerEvent: 'mouseover',
		pause: true, 
		manualTrump: true, 
	});
});

$(function(){
	$('.carouselUl2') .cycle({ 
		fx:'scrollLeft',
		timeout: 2000,
		pager:'.page2',
		speed:700, 
		randomizeEffects:1,
		prev:'.prev2',
		next:'.next2',
		pagerEvent: 'mouseover', 
		pause: true,
		manualTrump: true,
	});
});
$(function(){
	$('.carouselUl3') .cycle({ 
		fx:'scrollLeft',
		timeout: 2000,
		pager:'.page3',
		speed:700, 
		randomizeEffects:1,
		prev:'.prev3',
		next:'.next3',
		pagerEvent: 'mouseover',
		pause: true, 
		manualTrump: true,
	});
});