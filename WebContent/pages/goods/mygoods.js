deleteactive();
var maincontent = ".maincontent";
//var rooturl = "/campus2/";
$.ajax({
			url : rooturl + "getmygoods",
			type : "POST",
			dataType : "JSON",
			data : {
				"userId" : sessionStorage.userId
			},
			success : function(data) {
				if (data.length == 0) {

				} else {
					for (var i = 0; i < data.length; i++) {
						// $(".maincontent").append("<p>1</p>");
						$(maincontent).append(
										"<div class='jumbotron'><div class='container'><div class='col-md-4'><div class='item' style='width:50%;'><div class='item-info' style='display:inline-block'><div class='item-info-pic'><img src='img/picture-loading.gif' alt='' class='img-circle' style='width:140px;height:140px'></div></div></div></div><div class='col-md-4'><div class='item-extends' style='display:inline-block'><div class='item-info-price'><p>商品名:"
												+ data[i].goodName
												+ "</p><p>发布人:"
												+ data[i].userName
												+ "</p><p>价格:<b>¥</b><em style='color:#f40;font-weight:700;font-style:normal'>"
												+ data[i].price
												+ "</em></p></div></div></div><div class='col-md-2'><button type='button' data-id="
												+ data[i].goodId
												+ " class='editgoods btn btn-success btn-lg btn-block' onclick='editgood("
												+ data[i].goodId
												+ ")'>修改</button><button type='button' data-id="
												+ data[i].goodId
												+ " class='deletegoods btn btn-danger btn-lg btn-block' onclick='delgood("
												+ data[i].goodId
												+ ")'>删除</button></div></div></div>");
					}
				}

			}
		});
var editgood = function(goodId)
{
	sessionStorage.goodId = goodId;
	mainNav.detail("/showeditgoods");
}
var delgood = function(goodId) {
//	alert(goodId);
	swal({  
        title:"",  
        text:"确定删除吗？",  
        type:"warning",  
        showCancelButton:"true",  
        showConfirmButton:"true",  
        confirmButtonText:"确定",  
        cancelButtonText:"取消",  
        closeOnConfirm: false,
        animation:"slide-from-top"  
      }, function() {   
      $.ajax({  
          type:"post",  
          url:rooturl+"deletegoods",  
          traditional: true,  
          dataType:"json",  
          data:{"goodId":goodId}  
      }).done(function(data) {  
                swal("操作成功!", "已成功删除数据！", "success"); 
//                mainNav.go("/showmygoods");
                $(".confirm").click(function(){
                	mainNav.go("/showmygoods");
                	$(".confirm").off();
				 })
			
             }).error(function(data) {  
                swal("OMG", "删除操作失败了!", "error");  
             });  
       });  
}