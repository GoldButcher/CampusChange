var rowNum = 1;
var rooturl="/campus2/"
var ordertable = $("#ordertable").DataTable({
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
        "url":  "http://localhost:8080/campus2/orderlist",
        "data":function(d){
        	d.search =""
        	d.isChange=Number($("select[name=isChange]").val());
        	d.orderStatus = Number($("select[name=orderStatus]").val());
        	d.sellername = "%"+$("input[name=sellername]").val()+"%"
        	d.buyllername = "%"+$("input[name=buyllername]").val()+"%"
        }
	},
	"aoColumns":[{
		"mData" : "orderId",
		"orderable": false , // 禁用排序
		"sDefaultContent" : "",
		"sWidth" : "10%"
	        },{
	    		"mData" : "sellgoodName",
	    		"orderable": false , // 禁用排序
	    		"sDefaultContent" : "",
	    		"sWidth" : "10%"
	    	    },{
	    	        "mData" : "sellerName",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%"
	    	    },{
	    	        "mData" : "buyllerName",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%"
	    	    },{
	    	        "mData" : "createDate",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%",
	    	    	"render" : function(data, type, full, meta) {
						//时间格式化
						return  moment(data).format("YYYY-MM-DD HH:mm:ss");
					}
	    	    },{
	    	        "mData" : "orderStatus",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%",
	    	    	"render" : function(data, type, full, meta){
	    	    		
	    	    		if(data==0)
	    	    			data = "未成交"
	    	    		if(data==1)
	    	    			data ="已成交"
	    	    		if(data==2)
	    	    			data="已取消"
	    	    		return data;
	    	    	}
	    	    },{
	    	        "mData" : "isChange",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%",
	    	    	"render" : function(data, type, full, meta){
	    	    		if(data==0||data==1)
	    	    			data = "买卖订单"
	    	    		else
	    	    		{
	    	    			data =  "交换订单"
	    	    		}
	    	    		return data;
	    	    	}
	    	    },{
	    	    	"mData" : "orderId",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : '',
					"sWidth" : "15%",
				    "render":function(data, type, full, meta){
				    	data='<button onclick="delet('+data+')" class="btn btn-danger btn-sm" data-id='+data+'><span class="icon-white icon-remove"></span>delete</button> '+'<button id="deleteOne" onclick="edit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>detail</button> ';
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
		$('td:eq(0)', row).html($("#ordertable").dataTable().fnSettings()._iDisplayStart + rowNum++);
		return row;
	}
});






