var content = "#content";
var Nav = {};
var mainNav = {};
var maincontent = ".maincontent";
var rooturl="/campus2/";
Nav.go = function(url){
	Pace.restart();
	$.ajax({
		"url" :rooturl+url,
		"type": "POST",
		"dataType":"html",
		success: function(data){
			$(content).html(data);
		}
	});
}

mainNav.go = function(url){
	Pace.restart();
	$.ajax({
		"url" :rooturl+url,
		"type": "POST",
		"dataType":"html",
		"success": function(data){
			$(maincontent).html(data);
			$(".detail").hide();
			$(maincontent).show();
		}
	});
}

mainNav.detail = function(url){
	Pace.restart();
	$(maincontent).hide();
	$.ajax({
		"url" :rooturl+url,
		"type": "POST",
		"dataType":"html",
		"success": function(data){
			$(".detail").html(data);
			$(".detail").show();
		}
	});
}

Nav.submit = function(url,data,gotourl){
	
	$.ajax({
		"url" : rooturl+url,
		"type":"POST",
		"data": data,
		"success":mainNav.go(gotourl)
	});
}

var deleteactive = function()
{
	var lis = $(".nav li");
	for(var i=0;i<lis.length;i++)
	{
		$($(lis).get(i)).removeClass("active");
	}
}

