var goodId = 0;
var goodName = "";
var userName = "";
var createDate = "";
var goodIntroduction = "";
var price = "";
//var rooturl = "/campus2/";
$(function () {
	
	goodId = sessionStorage.goodId;
	
	$.ajax({
		url:rooturl+"getmygoods",
		type:"post",
		dataType:"JSON",
		data:{"userId":sessionStorage.userId},
		success:function(data){
			for(var i=0;i<data.length;i++)
			{
				$("optgroup").append("<option value="+data[i].goodId+">"+data[i].goodName+"</option>")
			}
		}
	})
	
    $.ajax({
        url: rooturl + 'getgoods',
        type: "POST",
        dataType: "json",
        async: false,
        data: {
            "goodId": goodId,
        },
        success: function (data) {
        	$("#goodName").html("商品名:"+data.goodName);
            $("#userName").html(data.userName);
            $("#price").html("¥"+data.price);
            $("#createDate").html(data.datestr);
            $("#goodIntroduction").html(data.goodIntroduction);
        },
        error: function (msg) {
            console.log("系统错误");
        }
    });
    $("#turnback").click(function(){
    	$(".detail").hide();
    	$(".maincontent").show();
    })
    $("#buy").click(function(){
    	$.ajax({
    		url:rooturl+"buygoods",
    		type:"POST",
    		dataType:"JSON",
    		data:{"userId":sessionStorage.userId,"goodId":sessionStorage.goodId},
    		success:function(data){
    			if(data.result=="success")
    			{
    				swal("","下单成功,请去我的购买处付款","success");
    			}
    			$(".confirm").click(function(){
    				mainNav.go("showallgoods");
    				$(".confirm").off();
    			});
    			
    		}
    	});
    })
    $("#change").click(function(){
    	$("#changeModal").modal();
    	var select2 = $("#sel_menu2").select2();
    	$(".changeconfirm").click(function(){
    		var changegoodId = select2.val();
    		$.ajax({
    			url:rooturl+"changegoods",
    			type:"POST",
    			dataType:"JSON",
    			data:{"sellgoodId":sessionStorage.goodId,"changegoodId":changegoodId,"userId":sessionStorage.userId},
    			success:function(data){
    				$("#changeModal").modal("hide");
    				swal("下单成功","请等待卖家回复","success");
    				$(".confirm").click(function(){
    					mainNav.go("showallgoods");
    					$(this).off();
    				})
    			}
    				
    		})
    	})
    })
})