//var rooturl = "/campus2/";
	 $.ajax({
		 url:rooturl+"getgoods",
		 type:"POST",
		 dataType:"JSON",
		 data:{"goodId":sessionStorage.goodId},
		 success:function(data){
			 $("#goodName").val(data.goodName);
			 $("#price").val(data.price);
			 $("#goodIntroduction").val(data.goodIntroduction);
		 }
	 })
	 $('#editgoodsform').bootstrapValidator({
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            goodName: {
	                validators: {
	                    notEmpty: {
	                        message: '商品名不能为空！'
	                    },
	                    stringLength: {
	                        max: 20,
	                        message: '商品名长度必须小于20'
	                    }
	                },
	            },
	            price: {
	                validators: {
	                    notEmpty: {
	                        message: '价格不能为空！'
	                    },
	                    regexp: {
	                        regexp: /^[0-9]+$/,
	                        message: '只能是数字'
	                    },
	                }
	            }
	        }
	    }).on('success.form.bv', function (e) {
	        e.preventDefault();
	        var goodName = $("#goodName").val();
			var price = $("#price").val();
			var goodIntroduction = $("#goodName").val();
			$.ajax({
				url:rooturl+"editgoods",
				type:"POST",
				dataType:"json",
				data:{"goodId":sessionStorage.goodId,"userId":sessionStorage.userId,"goodName":goodName,"price":price,"goodIntroduction":goodIntroduction},
				success:function(data){
					 swal("OK!", "修改成功", "success");
					 $(".confirm").click(function(){
						 mainNav.go("showmygoods");
						 $(".confirm").off();
					 })
					
				}
			});

	    });
	 $("#turnback").click(function(){
		 $(".detail").hide();
	     $(".maincontent").show(); 
	 });