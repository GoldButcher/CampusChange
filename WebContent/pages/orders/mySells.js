var maincontent = ".maincontent";
$
		.ajax({
			url : rooturl + "getSellOrders",
			type : "GET",
			dataType : "JSON",
			data : {
				"userId" : sessionStorage.userId,
			},
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					/* 购买订单 */
					if (data[i].isChange == 0) {
						/* 买家未付款 */
						if (data[i].orderStatus == 0) {
							$(maincontent)
									.append(
											"<div class='jumbotron'>"
													+ "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:买家未付款</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
													+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
													+ data[i].sellgoodName
													+ "</p> <p>购买者:"
													+ data[i].buyllerName
													+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:"
													+ data[i].sellprice
													+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
						}
						/* 买家已付款 */
						else if (data[i].orderStatus == 1) {
							$(maincontent)
									.append(
											"<div class='jumbotron'>"
													+ "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:买家已付款</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
													+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
													+ data[i].sellgoodName
													+ "</p> <p>购买者:"
													+ data[i].buyllerName
													+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:"
													+ data[i].sellprice
													+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
						}// 订单被取消
						else {
							$(maincontent)
									.append(
											"<div class='jumbotron'>"
													+ "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已取消购买</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
													+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
													+ data[i].sellgoodName
													+ "</p> <p>购买者:"
													+ data[i].buyllerName
													+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:"
													+ data[i].sellprice
													+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
						}
					}
					/* 交换订单 */
					else {
						if (data[i].orderStatus == 1) {
							$(maincontent)
									.append(
											"<div class='jumbotron'>"
													+ "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已交换</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
													+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
													+ data[i].sellgoodName
													+ "(*我的商品)</p><p>商品名:"
													+ data[i].changegoodName
													+ "</p> <p>购买者:"
													+ data[i].buyllerName
													+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:"
													+ data[i].sellprice
													+ "</p><p>价格:"
													+ data[i].changeprice
													+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
						} else if (data[i].orderStatus == 0) {
							$(maincontent)
									.append(
											"<div class='jumbotron'>"
													+ "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:卖家未同意</span> </div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'><img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'>"
													+ "</a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
													+ data[i].sellgoodName
													+ "(*我的商品)</p><p>商品名:"
													+ data[i].changegoodName
													+ "</p><!-- <p>购买者:"
													+ data[i].buyllerName
													+ "</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div>  <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>价格:"
													+ data[i].sellprice
													+ "</p><p>价格:"
													+ data[i].changeprice
													+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div><div class='col-sm-offset-8 col-md-2'><button type='button' class='btn btn-warning btn-lg btn-block' name='button' onclick='$.agreechange("
													+ data[i].orderId
													+ ","
													+ data[i].goodId
													+ ")'>同意交换</button></div><div class='col-md-2'><button type='button' class='btn btn-danger btn-lg btn-block' name='button' onclick='$.disagreechange("
													+ data[i].orderId
													+ ","
													+ data[i].goodId
													+ ")'>拒绝交换</button></div></div>");
						} else {// 订单被取消
							$(maincontent)
									.append(
											"<div class='jumbotron'>"
													+ "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已取消交换</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
													+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
													+ data[i].sellgoodName
													+ "(*我的商品)</p><p>商品名:"
													+ data[i].changegoodName
													+ "</p> <p>购买者:"
													+ data[i].buyllerName
													+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:"
													+ data[i].sellprice
													+ "</p><p>价格:"
													+ data[i].changeprice
													+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
						}
					}
				}
			}
		});

$.disagreechange = function(orderId, goodId) {
	$.ajax({
		url : rooturl + "sellerdisagreechange",
		type : "POST",
		dataType : "JSON",
		data : {
			"orderId" : orderId,
			"goodId" : goodId
		},
		success : function(data) {
			if (data.result == "success") {
				swal("OK", "取消成功!", "success");
				$(".confirm").click(function() {
					mainNav.go("showMysells");
					$(this).off();
				});
			}
		}
	})
}

$.agreechange = function(orderId, goodId) {
	$.ajax({
		url : rooturl + "agreechange",
		type : "POST",
		dataType : "JSON",
		data : {
			"orderId" : orderId,
			"goodId" : goodId
		},
		success : function(data) {
			if (data.result == "success") {
				swal("OK", "交易成功!", "success");
				$(".confirm").click(function() {
					mainNav.go("showMysells");
					$(this).off();
				});
			}
		}
	})
}