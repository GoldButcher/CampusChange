var rowNum = 1;
var rooturl="/campus2/"
var usertable = $("#usertable").DataTable({
	"aLengthMenu":[5,20,40,60],
	"searching":false,//禁用搜索
	"lengthChange":true,
	"paging": true,//开启表格分页
	"bProcessing" : true,
	"bServerSide" : true,
	"bAutoWidth" : false,
	"sort" : "position",
	"deferRender":false,//延迟渲染
	"bStateSave" : false, //在第三页刷新页面，会自动到第一页
	"iDisplayLength" : 5,
	"iDisplayStart" : 0,
	"ordering": false,//全局禁用排序
	"ajax": {
        "type": "POST",
        "url":  "http://localhost:8080/campus2/userlist",
        "data":function(d){
        	d.search =""
        	d.userName="%"+$("input[name=username]").val()+"%"
        	d.sex = "%"+$("select[name=usersex]").val()+"%"
        	d.email = "%"+$("input[name=email]").val()+"%"
        	d.phone = "%"+$("input[name=phone]").val()+"%"
        }
	},
	"aoColumns":[{
		"mData" : "userId",
		"orderable": false , // 禁用排序
		"sDefaultContent" : "",
		"sWidth" : "10%"
	        },{
	    		"mData" : "userName",
	    		"orderable": false , // 禁用排序
	    		"sDefaultContent" : "",
	    		"sWidth" : "10%"
	    	    },{
	    	        "mData" : "userSex",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%"
	    	    },{
	    	        "mData" : "dateStr",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "15%",
	    	    	"render" : function(data, type, full, meta) {
						//时间格式化
						return  moment(data).format("YYYY-MM-DD HH:mm:ss");
					}
	    	    },{
	    	        "mData" : "phone",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%"
	    	    },{
	    	        "mData" : "email",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "15%"
	    	    },{
	    	    	"mData" : "userId",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : '',
					"sWidth" : "20%",
				    "render":function(data, type, full, meta){
				    	data='<button onclick="delet('+data+')" class="btn btn-danger btn-sm" data-id='+data+'><span class="icon-white icon-remove"></span>delete</button> '+'<button id="deleteOne" onclick="edit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>edit</button> ';
				    return	data;
				    	
				    }	
	    	    }
	],
	"oLanguage" : { // 国际化配置
		"sProcessing" : "正在获取数据，请稍后...",
		"sLengthMenu" : "显示 _MENU_ 条",
		"sZeroRecords" : "没有找到数据",
		"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
		"sInfoEmpty" : "记录数为0",
		"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
		"sInfoPostFix" : "",
		"sSearch" : "搜索",
		"sUrl" : "",
		"oPaginate" : {
			"sFirst" : "第一页",
			"sPrevious" : "上一页",
			"sNext" : "下一页",
			"sLast" : "最后一页"
		}
	},
	drawCallback: function(){
		rowNum = 1;
	},
	rowCallback : function(row, data, displayIndex) {
		$('td:eq(0)', row).html($("#usertable").dataTable().fnSettings()._iDisplayStart + rowNum++);
		return row;
	}
});

$(".modal-email").blur(function(){
	var value = $(this).val();
	var tex = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
	if(value.trim()!=""&&!tex.test(value))
	{
		$(this).parent().parent().removeClass("success").addClass("error");
		$(this).next().html("Please enter the correct email format");
		flag[0] = false;
	}
	else
	{
		$(this).parent().parent().removeClass("error").addClass("success");
		$(this).next().html("");
		flag[0] = true;
	}
});

$(".modal-phone").blur(function(){
	var value = $(this).val();
	var tex = /^1[34578]\d{9}$/;
	if(value.trim()!=""&&!tex.test(value))
	{
		$(this).parent().parent().removeClass("success").addClass("error");
		$(this).next().html("Please enter the correct phone format");
		flag[1] = false;
	}
	else
	{
		$(this).parent().parent().removeClass("error").addClass("success");
		$(this).next().html("");
		flag[1] = true;
	}
});
$('#editUserModal').on('hidden.bs.modal', function (e) {
	 $(".modal-phone").parent().parent().removeClass("error").removeClass("success");
	 $(".modal-email").parent().parent().removeClass("error").removeClass("success");
	
	});

var editUser = function()
{
	var id = $(".userid").val();
	var phone = $(".modal-phone").val();
	var sex = $("#sexselect").val();
	var email = $(".modal-email").val();
	$.ajax({
		url:rooturl+"editUser",
		type:"POST",
		dataType:"JSON",
		data:{"id":id,"phone":phone,"sex":sex,"email":email},
		success:function(data){
			alert(data.result);
			$('#editUserModal').modal('hide');
			usertable.ajax.reload( null, false );
		}
	});
}


var delet = function(id){
	$("#Confirm").val(id);
	$('#deleteUserModal').modal({
		"keyboard":true
	});
}

$("#Confirm").click(function(){
	var id=$(this).val();
	$.ajax({
		url:rooturl+"deluser",
		type:"POST",
		data:{"id":id},
		success:function(data){
			if(data.result=="ok")
			{
				 usertable.ajax.reload( null, false );
			}else{
				alert("没有,快滚!");
			}
		}
	});
	
});

var edit = function(id){
	var group = $(".form1 .control-group");
	$('#editUserModal').modal({
		"keyboard":true
	});
	$.ajax({
		url:rooturl+"getUser4id",
		type:"post",
		data:{"id":id},
		success:function(data){
				$(group.get(0)).find("div").find("input").val(data.userName);
				$(group.get(1)).find("div").find("input").val(data.userSex);
				$(group.get(2)).find("div").find("input").val(data.email);
				$(group.get(3)).find("div").find("input").val(moment(data.dateStr).format("YYYY-MM-DD HH:mm:ss"));
				$(group.get(4)).find("div").find("input").val(data.phone);
				$(".userid").val(id);
		}
	});
}

$(".search").click(function(){
	usertable.ajax.reload();
});





