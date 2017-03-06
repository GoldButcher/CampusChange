var maincontent = ".maincontent";
$.ajax({
	url : rooturl + "getMyOrders",
	type : "GET",
	dataType : "JSON",
	data : {
		"userId" : sessionStorage.userId,
	},
	success : function(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].isChange == 1) {
				/* 购买 未付款 */
				if (data[i].orderStatus == 0) {
					$(maincontent).append(
							"<div class='jumbotron'>" + "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:尚未付款</span></div><div class='col-md-2'><div class='item' style='width:50%;'> <div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
									+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div> </div></div> </div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:" + data[i].sellgoodName + "</p><p>发布人:" + data[i].sellerName
									+ "</p><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div><div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>价格:" + data[i].sellprice + "</p> <!-- <p>下单日期:" + data[i].createDate
									+ "</p> --> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div><div class='col-sm-offset-8 col-md-2'><button type='button' class='btn btn-warning btn-lg btn-block' name='button' onclick='$.paygoods(" + data[i].orderId + "," + data[i].goodId
									+ ")'>支付</button> </div><div class='col-md-2'><button type='button' class='btn btn-danger btn-lg btn-block' name='button' onclick='$.canclepay(" + data[i].orderId + "," + data[i].goodId + ")' >取消订单</button></div></div>'");
					/* 购买 已付款 */
				} else if (data[i].orderStatus == 1) {
					$(maincontent).append(
							"<div class='jumbotron'>" + "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已付款</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
									+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:" + data[i].sellgoodName + "</p> <p>发布人:" + data[i].sellerName
									+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:" + data[i].sellprice
									+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
				} else {
					/* 订单取消 */
					$(maincontent).append(
							"<div class='jumbotron'>" + "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已取消购买</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
									+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:" + data[i].sellgoodName + "</p> <p>发布人:" + data[i].sellerName
									+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:" + data[i].sellprice
									+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
				}
				/* 交换未同意 */
			} else {
				if (data[i].orderStatus == 0) {
					/* 交换未同意 */
					$(maincontent).append(
							"<div class='jumbotron'>" + "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:等待卖家回复</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
									+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:" + data[i].sellgoodName + "</p><p>商品名:" + data[i].changegoodName + "(*我的商品)</p><p>发布人:" + data[i].sellerName
									+ "</p><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div>  <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>价格:" + data[i].sellprice
									+ "</p><p>价格:" + data[i].changeprice
									+ "</p><!-- <p>发布人:XXX</p> --><!-- <p> 价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --> </div></div></div></div><div class='col-sm-offset-10 col-md-2'><button type='button' onclick=$.buyercancelchange("+ data[i].orderId + "," + data[i].goodId +") class='btn btn-danger btn-lg btn-block' name='button'>取消交换</button></div></div>");
					/* 已交换 */
				} else if (data[i].orderStatus == 1) {
					$(maincontent).append(
							"<div class='jumbotron'>" + "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已交换</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
									+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:" + data[i].sellgoodName + "</p><p>商品名:" + data[i].changegoodName + "(*我的商品)</p> <p>发布人:" + data[i].sellerName
									+ "</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:" + data[i].sellprice
									+ "</p><p>价格:" + data[i].changeprice
									+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
				} else {
					/* 取消交换 */
					$(maincontent).append(
							"<div class='jumbotron'>" + "<div  class='container'><div class='col-md-offset-5 col-md-7'><span>订单状态:已取消交换</span></div><div class='col-md-2'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><a href='#'>"
									+ "<img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></a></div></div></div></div><div class='col-md-8'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:" + data[i].sellgoodName + "</p> <p>商品名:" + data[i].changegoodName
									+ "(*我的商品)</p> <!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div> <div class='col-md-2'><div class='item-extends' style='display:inline-block'><div class='item-info-price'> <p>价格:" + data[i].sellprice
									+ "</p><p>价格:" + data[i].changeprice
									+ "</p><!-- <p>发布人:XXX</p> --><!-- <p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>500</em></p> --></div></div></div></div></div>");
				}
			}
		}
	}
});

$.paygoods = function(orderId,goodId) {
	$.ajax({
		url:rooturl+"paygoods",
		type:"POST",
		dataType:"json",
		data:{
			orderId:orderId,
			goodId:goodId
		},
		success:function(data){
			if(data.result=="success")
			{
				swal("","付款成功","success");
			}
			$(".confirm").click(function(){
				mainNav.go('showMyOrders');
				$(this).off();
			});
		}
	});
}

$.canclepay = function(orderId,goodId) {
	$.ajax({
		url:rooturl+"canclepay",
		type:"POST",
		dataType:"json",
		data:{
			orderId:orderId,
			goodId:goodId
		},
		success:function(data){
			 if(data.result=="success")
				{
					swal("","取消成功","success");
				}
				$(".confirm").click(function(){
					mainNav.go('showMyOrders');
					$(this).off();
				});
		}
	});
}

$.buyercancelchange = function(orderId,goodId){
	$.ajax({
		url:rooturl+"buyercancelchange",
		type:"POST",
		dataType:"json",
		data:{"orderId":orderId,"goodId":goodId},
		success:function(data){
			if(data.result=="success")
			{
				swal("","取消成功","success");
			}
			$(".confirm").click(function(){
				mainNav.go('showMyOrders');
				$(this).off();
			});
		}
	})
}