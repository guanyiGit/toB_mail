// JavaScript Document


$(function(){
	// var $ul003= $('.ul003>li');
	// $ul003.mouseover(function(){
	// 	$(this).addClass('active').siblings().removeClass('active');
	// 	var i= $ul003.index(this);
	// 	$('.ul0004>li').eq(i).show().siblings('.ul0004>li').hide();
	// });
});

$(function(){
	var $tp= $('.tp1>a');
	$tp.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tp.index(this);
		$('.tp2').eq(i).show().siblings('.tp2').hide();
	});
});


$(function(){
	var $tp2= $('.ul8>li');
	$tp2.click(function(){
		if($('.subDiv>div').is(':hidden')){
		$(this).addClass('active').siblings().removeClass('active');
			var i= $tp2.index(this);
			$('.subDiv>div').eq(i).slideToggle()
			$('.subDiv>div').eq(i).siblings().slideUp();
		}
	});
});


$(function(){
	var $tab1= $('.tab1>a');
	$tab1.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab1.index(this);
		$('.tab2').eq(i).show().siblings('.tab2').hide();
	});
});

$(function(){
	var $tab111= $('.btn-a5');
	$tab111.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab111.index(this);
		$('.oDiv1').eq(i).show().siblings('.oDiv1').hide();
	});
});

$(function(){
	var $tab112= $('.tab-div>a');
	$tab112.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tab112.index(this);
		$('.tab-div2').eq(i).show().siblings('.tab-div2').hide();
	});
});

$(function(){
	var $tb11= $('.tb11>a');
	$tb11.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var i= $tb11.index(this);
		$('.tb12').eq(i).show().siblings('.tb12').hide();
	});
});

$(function () {

	var speed = 800;
	var h=document.body.clientHeight;
	$(".aside6").click(function () {
		$('html,body').animate({
			scrollTop: '0px'
		},speed);
	});
});


