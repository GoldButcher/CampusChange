deleteactive();
var maincontent = ".maincontent";
//var rooturl = "/campus2/";
//alert(rooturl);
$.ajax({
			url : rooturl + "getAllgoods",
			type : "POST",
			dataType : "JSON",
			data:{"userId":sessionStorage.userId},
			success : function(data) {
				if (data.length == 0) {

				} else {
					for (var i = 0; i < data.length; i++) {
						// $(".maincontent").append("<p>1</p>");
						$(maincontent)
								.append(
										"<div class='jumbotron'><div class='container'><div class='col-md-5'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='javascript:showGood("
												+ data[i].goodId
												+ ")'><img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-7'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
												+ data[i].goodName
												+ "</p><p>发布人:"
												+ data[i].userName
												+ "</p><p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>"
												+ data[i].price
												+ "</em></p></div></div></div></div></div>");
					}
				}

			}
		});

var showGood = function(id) {
	sessionStorage.goodId = id;
	mainNav.detail("/showdetailgoods");
}
