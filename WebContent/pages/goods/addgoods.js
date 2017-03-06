$(function(){
	deleteactive();
	$("input[type=hidden]").val(sessionStorage.userId);
	//alert(sessionStorage.userId);
//	var rooturl = "/campus2/";
	 $('#addgoodsform').bootstrapValidator({
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
	        var forms = $("#addgoodsform").serialize();
	        var goodName = $("#goodName").val();
			var price = $("#price").val();
			var goodIntroduction = $("#goodIntroduction").val();
			console.log(goodName);
			console.log(price);
			$.ajax({
				url:rooturl+"addgoods",
				type:"POST",
				dataType:"json",
				data:forms,
				success:function(data){
					 swal("Good!", "添加成功", "success");
					 $(".confirm").click(function(){
						 location.reload();
					 })
				}
			});

	    });
})