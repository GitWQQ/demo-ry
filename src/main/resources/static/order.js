$(function(){
	
})
var order_czbs;
var ordering_OR_ordered="wcl";
function InitOrderList(sfcl,tabName){
	$("#"+tabName).bootstrapTable({                         
		url:'/thy/order/getOrderItemRecordByParam',                             
		method:'GET',                                           
		dataType:'json',                                         
		contentType:'application/x-www-form-urlencoded',
		iconSize:'outline',
		striped:true,                                            
		cache:true,                                             
		showColumns:false,                                       
		pagination:true,                                         
		paginationPreText:'上一頁',                                 
		paginationNextText:'下一頁',                                
		showPaginationSwitch:false,                              
		sortable:false,                                          
		singleSelect:false,                                      
		search:false,                                             
		buttonsAlign:'right',                                    
		showRefresh:false,
		showExport:true,
		exportDataType: "all",
		pageNumber:1,                                            
		pageSize:5,                                              
		pageList:[5,10,20,30,50,100],                            
		undefinedText:'--',                                      
		uniqueId:'id',                                           
		queryParams:function(param){
			return {
				"sfcl":sfcl
			};
		},                                      
		columns:[                                                
				 	{                                            
				 	   checkbox:true,                            
				 	   align:'center',                           
				 	   valign:'middle',
				 	   width:60
				 	},{                                          
				 	 	title:'商品编号',                              
				 	    field:'id',                              
				 	    align:'center',                          
				 	    valign:'middle',
				 	    width:80
				 	},{                                          
				 	    title:'商品名称',                            
				 	    field:'title',  
				 	    align:'center',
				 	    valign:'middle',
				 	},{                                          
				 	    title:'价格',                              
				 	    field:'price',                           
				 	    align:'center',
				 	    width:100,
				 	    formatter: function(value,row,index){
				 	    	return (row.price)/100.0+"(元)";
				 	    }
				 	},{                                          
				 	    title:'购买数量',                              
				 	    field:'num',                             
				 	    align:'center', 
				 	    width:100,
				 	    formatter:function(value,row,index){
				 	    	return row.num+"(件)";
				 	    }
				 	},{
				        field:'total_fee',
				        title:'商品总金额',
				        align:'center',
				        width:100,
				        formatter:function(value,row,index){
				        	var total_fee=row.num*(row.price/100)
				        	return total_fee+"(元)";
				     
				        }
				 	},{                                          
				 		title:'操作',
				 		field:'order_id',
				 		width:180,
				 		align:'center',
				 		formatter:function(value,row,index){
				 			 var btn;
				 			 if(order_czbs=="1"){ //未发货
				 				btn='<button id="btn_send" class="btn btn-primary btn-sm" onclick="btn_send('+value+');">发货</button>&nbsp;'+
			 				        '<button id="btn_detail"  class="btn btn-warning btn-sm" onclick="btn_send_detail('+value+');">详情</button>&nbsp';
				 			 }else if(order_czbs=="2"){ //
			 				    btn='<button id="btn_remove" class="btn btn-danger btn-sm" onclick="btn_send_remove('+row.id+','+row.order_id+');">删除订单</button>&nbsp;'
			 				    	+'<button id="btn_detail" class="btn btn-warning btn-sm" onclick="btn_send_detail('+value+');">详情</button>';
				 			 }else if(order_czbs=='3'){//已发货
				 				btn='<button id="btn_edit" class="btn btn-primary btn-sm" onclick="btn_send('+value+');">编辑</button>&nbsp;'
				 				    +'<button id="btn_detail" class="btn btn-warning btn-sm" onclick="btn_ordered_detail('+value+');">详情</button>';
				 			 }else if(order_czbs=="4"){ //
			 				    btn='<button id="btn_ordered_remove" class="btn btn-danger btn-sm" onclick="btn_ordered_remove('+row.id+','+row.order_id+');">删除订单</button>&nbsp;'
			 				    	+'<button id="btn_ordered_detail" class="btn btn-warning btn-sm" onclick="btn_ordered_detail('+value+');">详情</button>';
				 			 }
				 			return btn;	   
				 		}                           
				 	}                                            
			]                                                    
	})                           
}

function btn_order_search(){
	if(ordering_OR_ordered=="wcl"){
		order_czbs="1";
		$("#orderingListTable").bootstrapTable("refresh");
	}
	if(ordering_OR_ordered=="ycl"){
		order_czbs="3";
		$("#orderedListTable").bootstrapTable("refresh");
	}
}
function btn_order_remove(){
	if(ordering_OR_ordered=="wcl"){
		order_czbs="2";
		$("#orderingListTable").bootstrapTable("refresh");

	}
	if(ordering_OR_ordered=="ycl"){
		order_czbs="4";
		$("#orderedListTable").bootstrapTable("refresh");
	}
}

/**
 * 打开发货页，填写发货信息
 */
function btn_send(order_id){
	var param={"order_id":order_id,"czbs":"send"};
	$.ajax({
		type:'GET',
		url:'/thy/order/getSendOrderRecord',
		data:param,
		dataType:'html',
		success:function(data){
			$("#myOrderSendModal #orderSendModalBody").html(data);
		},
		error:function(data){
				
		}
	})
	$("#myOrderSendModal").modal("show");
	$("#myOrderSendModal").on('shown.bs.modal',function(){
		getZdInfo('WLXX','shipping_name');
		getZdInfo('ZT','status');
		getZdInfo('ZFLX','payment_type');
		//------------
		var param={"order_id":order_id,"czbs":"send"};
		$.ajax({
			type:'GET',
			url:'/thy/order/getSendOrderRecordData',
			data:param,
			dataType:'json',
			success:function(data2){
				var param3={"zddm":"ZT,WLXX,ZFLX"};
				$.ajax({
					type:'GET',
					url:'/thy/sys/getZdInfo',
					data:param3,
					dataType:'json',
					success:function(data3){
						for(var j=0;j<data3.length;j++){ ////外循环，循环有几种字典类
							for(var k=0;k<data3[j].length;k++){ /////内循环，循环没个字典类中有几个字典项
								if(data2[0].shipping_name==data3[j][k].dm){
									$("#orderSendForm #shipping_name").find("option[value="+data3[j][k].dm+"]").attr("selected",true);
								}
								if(data2[0].status==data3[j][k].dm){
									$("#orderSendForm #status").find("option[value="+data3[j][k].dm+"]").attr("selected",true);
								}
								if(data2[0].payment_type==data3[j][k].dm){
									$("#orderSendForm #payment_type").find("option[value="+data3[j][k].dm+"]").attr("selected",true);
								}
							}
						}
					},
					error:function(){
						
					}
				})
			}
		})
		//------------------	
	});
}

/**
 * 翻译字典方法
 */
function ShowZdContent(BZ){
	var param={"order_id":order_id,"czbs":"send"};
	$.ajax({
		type:'GET',
		url:'/thy/order/getSendOrderRecordData',
		data:param,
		dataType:'json',
		success:function(data2){
			var param3={"zddm":BZ};
			$.ajax({
				type:'GET',
				url:'/thy/sys/getZdInfo',
				data:param3,
				dataType:'json',
				success:function(data3){
					for(var j=0;j<data3.length;j++){
						if(data2[0].shipping_name==data3[j].dm){
							$("#orderSendForm #shipping_name").find("option[value="+data3[j].dm+"]").attr("selected",true);
						}
						if(data2[0].status==data3[j].dm){
							$("#orderSendForm #status").find("option[value="+data3[j].dm+"]").attr("selected",true);
						}
					}
				},
				error:function(){
					
				}
				
			})
		}
	})
}
/**
 * 订单详情
 */
function btn_send_detail(order_id){
	var param={"order_id":order_id,"czbs":"detail"};
	$.ajax({
		type:'GET',
		url:'/thy/order/getSendOrderRecord',
		data:param,
		dataType:'html',
		success:function(data){
			$("#myOrderDetailModal #orderDetailModalBody").html(data);
		},
		error:function(data){
				
		}
	})
	$("#myOrderDetailModal").modal("show");
	$("#myOrderSendModal").on('shown.bs.modal',function(){
		getZdInfo('WLXX','shipping_name');
	});
}

/**
 * 删除订单
 */
function btn_send_remove(id,order_id){
	var param={"id":id,"order_id":order_id};
	layer.confirm('确定删除',{icon:3,title:'提示'},function(index){
		$.ajax({
			type:'POST',
			url:'/thy/order/removeOrder',
			data:param,
			dataType:'json',
			timeout:5000,
			success:function(data){
				layer.msg(data.msg);
			},
			error:function(){
			
			}
		})
		btn_order_remove();
	})
}


//执行发货操作
function btn_sendOrder(){
	var param=$("#orderSendForm").serialize();
	$.ajax({
		type:'post',
		url:'/thy/order/toOrderSend',
		data:param,
		dataType:'json',
		success:function(data){
			layer.msg(data.msg);
			$("#orderingListTable").bootstrapTable("refresh");
		},
		error:function(data){
			
		}
	})
}

//点击已处理订单bar
function Fun_ordered(){
	if(ordering_OR_ordered="ycl"){
		order_czbs="3";
	}//已处理
	InitOrderList("1","orderedListTable");
}
//点击未处理订单bar
function Fun_ordering(){
	if(ordering_OR_ordered="wcl"){
		order_czbs="1";
	}//已处理
	InitOrderList("0","orderingListTable");
}

//查询未处理订单的总数（徽章显示）
function sendOrderCount(){
	var param={};
	$.ajax({
		type:'get',
		url:'/thy/order/getSendOrderCount',
		data:param,
		dataType:'json',
		success:function(data){
			$("#ordering_count").html(data.count);
		},
		error:function(data){
			
		}
	})
}
//-----------------
var t,n,count=0;
$(function(){
	t=setInterval("sendOrderCount()",1000*60);
})





