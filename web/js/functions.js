//on page load
$(function() {
	jQuery.fn.extend({
		t1makeActive: function() {
			$(".acc_nav").children('li').each(function() {
				$(this).removeClass("active");
			});
			$(this).addClass("active");
		}
	});
	jQuery.fn.extend({
		t2makeActive: function() {
			$(".nav_pane").children('ul').each(function() {
				$(this).removeClass("active");
				$(this).children('li').each(function() {
					$(this).removeClass("active");
				});
			});
			$(this).addClass("active");
		}
	});
	jQuery.fn.extend({
		t3makeActive: function() {
			$(this).parent().children('li').each(function() {
				$(this).removeClass("active");
			});
			$(this).addClass("active");
		}
	});
	jQuery.fn.extend({
		switchContent: function() {
			var systemIndex = $(".nav_pane .active").index() + 1;
			var toolIndex = $(".nav_pane .active .active").index() + 1;
			$(".inner_frame .active").removeClass("active");
			$(".inner_frame section:nth-child(" + systemIndex + ") div:nth-child(" + toolIndex + ")").addClass("active");
		}
	});
	//tier 1 navigation
	$("#t1_1").click(function() {
		$("#t1_1").t1makeActive();
		$("#t2_1").t2makeActive();
		$("#res_1").t3makeActive();
		$("#t1").text("Reservations");
		$("#t2").text("Search for a flight");
		$("#page_title").text("Search for flight");
		$(".inner_frame").css("border-top-left-radius", "0px");
		$(".outer_frame").css("border-top-left-radius", "0px");
		$(".outer_frame").css("border-top-right-radius", "5px");
		$(window).switchContent();
	});
	$("#t1_2").click(function() {
		$("#t1_2").t1makeActive();
		$("#t2_2").t2makeActive();
		$("#pro_1").t3makeActive();
		$("#t1").text("Profile");
		$("#t2").text("Account Settings");
		$("#page_title").text("Account Settings");
		$(".inner_frame").css("border-top-left-radius", "0px");
		$(".outer_frame").css("border-top-left-radius", "5px");
		$(".outer_frame").css("border-top-right-radius", "5px");
		$(window).switchContent();
	});
	$("#t1_3").click(function() {
		$("#t1_3").t1makeActive();
		$("#t2_3").t2makeActive();
		$("#ser_1").t3makeActive();
		$("#t1").text("Services");
		$("#t2").text("Our Services");
		$("#page_title").text("Our Services");
		$(".inner_frame").css("border-top-left-radius", "0px");
		$(".outer_frame").css("border-top-left-radius", "5px");
		$(".outer_frame").css("border-top-right-radius", "5px");
		$(window).switchContent();
	});
	$("#t1_4").click(function() {
		$("#t1_4").t1makeActive();
		$("#t2_4").t2makeActive();
		$("#rep_1").t3makeActive();
		$("#t1").text("Reports");
		$("#t2").text("Travel Report");
		$("#page_title").text("Travel Report");
		$(".inner_frame").css("border-top-left-radius", "0px");
		$(".outer_frame").css("border-top-left-radius", "5px");
		$(".outer_frame").css("border-top-right-radius", "0px");
		$(window).switchContent();
	});
	//tier 3 navigation
	$(".nav_pane li").click(function() {
		$(this).t3makeActive();
		if ( $(this).is(":first-child") ) {
			$(".inner_frame").css("border-top-left-radius", "0px");
		} else {
			$(".inner_frame").css("border-top-left-radius", "5px");
		}
		var el_text = $(this).text();
		$("#t2").text(el_text);
		$("#page_title").text(el_text);
		$(window).switchContent();
	});
	//Search flight form
	$("#return").click(function() {
		if($(this).parent().find('input').is(':checked')) {
			$("#return_date").removeAttr("disabled");
		} else {
			$("#return_date").attr("disabled", "disabled");
		}
	});
});