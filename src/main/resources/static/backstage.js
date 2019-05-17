var btn_search_bz="1";
var btn_remove_bz="2";
var btn_add_bz="3";

$(function(){
		
		$("#navbar_Photo").mouseover(function(){
			$("#groupItem").css("display","");  
		})
		
		$("#splx").on('change',function(){
			var index=$("#splx").find('option:selected').attr('selectedIndex');
		});
		
		$("#navbar_Photo").mouseout(function(){
			$("#groupItem").css("display","none");  
		})
		
		$("#panel_zero").click(function(){
			$("#panel_zero").css('display','');
			$("#panel_zero").siblings().css("display","none");
		});

		$("#btn_one").click(function(){
			$("#panel_one").css("display","");
			$("#panel_one").siblings().css("display","none");
			getZdInfo('SPLX','splx');
			
		})
		$("#btn_two").click(function(){
			$("#panel_two").css("display","");
			$("#panel_two").siblings().css("display","none");
		})
		$("#btn_three").click(function(){
			$("#panel_three").css("display","");
			$("#panel_three").siblings().css("display","none");
			initBootStrap();
			getZdInfo('STATUS','txt_search_status');
			$('#userListTable').bootstrapTable('hideColumn', 'created');
		})
		$("#btn_four").click(function(){
			$("#panel_four").css("display","");
			$("#panel_four").siblings().css("display","none");
			getLBTFile('1','/thy/static/main');
			initBootStrap();
		})
		$("#btn_five").click(function(){
			$("#panel_five").css("display","");
			$("#panel_five").siblings().css("display","none");
			InitOrderList("0","orderingListTable");
			order_czbs="1";
			sendOrderCount();
		})
		$("#radio1").attr("checked",true);
		setInterval("getTime()",1000);
		
		//修改密码函数function
		modifyPassWord();
})

/**
 * 修改密码
 */
function modifyPassWord(){
	$("#modifyPass_Form").bootstrapValidator({
		message:'This value is not valid',
		feedbackIcons:{
			valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
		},
		fields:{
			password:{
				validators:{
					notEmpty: {
						message:'原始密码不能为空!'
					},
				}	
			},
			newPassword:{
				validators:{
					notEmpty:{
						message:'新密码不能为空!'
					}
				}
			},
			confirm_password:{
				validators:{
					notEmpty:{
						message:'确认密码不能为空!'
					},
					identical:{
						field:'newPassword',
						message:'密码不一致!'
					}
				}
			}
		}
	}).on('success.form.bv',function(){
		//判断验证是否通过
		var flag=$("#modifyPass_Form").data("bootstrapValidator").isValid();
		if(flag){ //通过
			
			//获取参数
			var param=$("#modifyPass_Form").serialize();
			//发送Ajax请求
			$.ajax({
				type:'POST',
				url:'/thy/sys/modifyPass',
				data:param,
				dataType:'json',
				beforeSend:function(){
					
				},
				success:function(data){
					layer.msg('密码修改成功！')
				},
				error:function(e){
					console.log(e);
					layer.msg('出错咯o(╥﹏╥)o,请与后台联系!',{
                        btn:'我知道了'
                    })
				}
				
			})
			
		}
	})
}

//日期时间函数
function getTime(){
	var myDate=new Date();
	//var date=myDate.toLocaleDateString();
	var year=myDate.getFullYear();
	var month=myDate.getMonth();
	var date=myDate.getDate();
	var hours = myDate.getHours();
	var minutes=myDate.getMinutes();
	var seconds =myDate.getSeconds();
	$("#now_time").html("现在是:"+year+"年"+(month+1)+"月"+date+"日  "+hours+":"+minutes+":"+seconds);
};
var queryParams=null;

//获取轮播图
function getLBTFile(czbs,url){
	var param={"czbs":czbs};
	$.ajax({
		type:'GET',
		url:url,
		data:param,
		dataType:'html',
		success:function(data){
			$("#preViewLBT").html(data);
		},
		error:function(data){
			
		}
	})	
}

//初始化bootstrap表格
function initBootStrap(){
	//--------------------------------------
	$("#onLineMsgTab").bootstrapTable({
		method:'GET',//服务器数据的请求方式
		url:'/kf/getOnlineMesg',
		iconSize:'outline',
		dataType:'json',                                         
		contentType:'application/x-www-form-urlencoded',
		toolbar:'#exampleToolbar',
		iconSize:'outline',
		striped:true,                                            
		cache:false,                                             
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
		uniqueId:'xh',             
		columns:[
		         {
		        	 field:'xh',
		        	 title:'序号',
		        	 visible:false
		         },{
		        	 field:'name',
		        	 title:'留言人姓名',
		        	 width:100,
		        	 align:'center'
		         },{
		        	 field:'content',
		        	 title:'留言内容',
		        	 align:'center',
		        	 width:300
		         },{
		        	 field:'phone',
		        	 title:'留言人电话',
		        	 align:'center',
		        	 width:100
		         },{
		        	 field:'email',
		        	 title:'留言人邮箱',
		        	 align:'center',
		        	 width:100
		         },{
		        	 title:'操作',
		        	 field:'xh',
		        	 align:'center',
		        	 width:100,
		        	 formatter:function(value,row,index){
		        		var btn;
		        		if(row.status=="0"){
		        			btn='<button id="btn_edit" class="btn btn-danger btn-sm" onclick="btn_reading2(\''+row.xh+'\');">未阅读</button>&nbsp;';
		        		}else{
		        			btn='<button id="btn_edit" class="btn btn-success btn-sm" onclick="btn_read(\''+row.xh+'\');">已阅读</button>&nbsp;';
		        		}
			 			return btn;
		        	 }
		         }
		],
		onLoadSuccess:function(json){
			$("#onLineMsgTab").bootstrapTable('load',json);
		},
		onLoadError:function(){
			bootbox.alert('load fail');
		}
		
	});
	
	//---------------------------------------
	$("#userListTable").bootstrapTable({                         
		url:'/item/getAllItemRecord',                             
		method:'GET',                                           
		dataType:'json',                                         
		contentType:'application/x-www-form-urlencoded',
		toolbar:'#exampleToolbar',
		iconSize:'outline',
		striped:true,                                            
		cache:false,                                             
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
		//queryParamsType:'',                                      
		columns:[                                                
				 	{                                            
				 	   checkbox:true,                            
				 	   align:'center',                           
				 	   valign:'middle',
				 	   width:60
				 	},{                                          
				 	 	title:'编号',                              
				 	    field:'id',                              
				 	    align:'center',                          
				 	    valign:'middle',
				 	    width:80
				 	},{                                          
				 	    title:'商品名称',                            
				 	    field:'title',                           
				 	    valign:'middle',
				 	},{                                          
				 	    title:'卖点',                              
				 	    field:'sell_point',                      
				 	    align:'left',
				 	    hidden:'hidden'
				 	},{                                          
				 	    title:'价格',                              
				 	    field:'price',                           
				 	    align:'center',
				 	    width:50,
				 	    formatter: function(value,row,index){
				 	    	return (row.price)/100.0+"￥";
				 	    }
				 	},{                                          
				 	    title:'库存',                              
				 	    field:'num',                             
				 	    align:'center', 
				 	    width:100,
				 	    formatter:function(value,row,index){
				 	    	return "还剩"+row.num+"件";
				 	    }
				 	},{
				 		title:'创建时间',
				 		field:'created'
				 	},{
				        field:'status',
				        title:'上/下架',
				        width:60,
				        formatter:function(value,row,index){
				        	var e='<a class="btn btn-success btn-xs" href="#" style="text-align:center" mce_href="#" title="点击下架" onclick="changeStatus(\''
				        		+row.id+'\',\''+row.status
				        		+'\')"><i class="fa fa-hourglass-start" style="text-align:center"></i>已上架</a>';
				            var f = '<a class="btn btn-danger btn-xs" href="#" style="text-align:center" mce_href="#" title="点击上架" onclick="changeStatus(\''
		                        + row.id+'\',\''+row.status
		                        + '\')"><i class="fa fa-square-o" style="text-align:center">已下架</i></a> ';
		                        if (row.status == 1) { //已上架
		                             return e;
		                         } else { //已下架
		                             return f;
		                         }
				        }
				 	},{                                          
				 		title:'操作',
				 		field:'id',
				 		width:140,
				 		align:'center',
				 		formatter:function(value,row,index){
				 			 var btn;
				 			 if(btn_search_bz=="1"){
				 				btn='<button id="btn_edit" class="btn btn-primary btn-sm" onclick="btn_edit('+value+');">编辑</button>&nbsp;'+
			 				        '<button id="btn_detail"  class="btn btn-warning btn-sm" data-toggle="modal" onclick="btn_detail('+value+');">详情</button>&nbsp';
				 				   
			 				      
			 				        
				 			 }else if(btn_remove_bz=="2"){
				 				btn='<button id="btn_detail" class="btn btn-primary btn-sm" onclick="btn_delete('+value+');">删除</button>&nbsp;'+
			 				    '<button id="btn_delete" class="btn btn-warning btn-sm" onclick="btn_detail('+value+');">详情</button>&nbsp;';
				 			 }
				 			return btn;	   
				 		}                           
				 	}                                            
			]                                                    
	})
	//-------------------------------------------
	
	
}

function queryParams(params){
	var temp={
			pageSize:params.pageSize,
			pageNumber:params.pageNumber,
			username:$("#search_username").val(),
			name:$("#search_name").val(),
			sex:$("#search_sex").val(),
			phone:$("#search_mobile").val(),
			email:$("#search_email").val(),
	}
	return temp;
}
function searchUser(){
	$("#userListTable").bootstrapTable("refresh");
}
function pre_view(){
	
}

function getItemDetails(id){
	$.ajax({
		type:'get',
		url:'/thy/getRecordDetail?id='+id,
		dataType:'html',
		success:function(data){
			$("#myModal").html(data);
		}
	})
}
function btn_detail(id){
	//显示模态框
	//$(selector).get(url,data,success(response,status,xhr),dataType);
	//url:必须属性，规定请求发送哪个URL
	//data:可选，规定连同请求发送到服务器的数据
	//success:可选，规定当请求成功时运行的函数， response:包含来自请求的结果数据，status:包含请求的状态，xhr包含XMLHttpRequest对象
	//dataType:可选，规矩预计的服务器响应的数据类型，xml,html,text,json,jsonp,script
	//$("#myModal").modal('show');
	var action="detail"
	var param={"id":id,"action":action};
	$.ajax({
		type:'get',
		url:'/item/getRecordDetailOrEdit',
		data:param,
		dataType:'html',
		success:function(data){
			$("#myModal #myModal_body").html(data);
		}
	})
	$("#myModal").modal('show');
}

function btn_delete1(){
	/*var str="本周遗留故障总数为$本周故障总体情况(0,"本周遗留故障总数")条，其中公司可处理范围内的故障为$本周故障总体情况(1,"公司可处理范围内的故障")条";*/
	var test="终古人民共和国，终古人民";
	var reg=new RegExp("终古","g");
	var newStr=test.replace(reg,"中国");
	var test2="CH人民共和国，CH人民";
	var newStr2=test2.replace(/(CH)/g,"中国");
	//alert(newStr2);
	var test3="中华人民共和国，中华人民共和国";
	var newstr3=test3.replace(/(人)/g,"<font color=red>$1</font>");   
	//alert(newstr3);
	//$("#userListTable").bootstrapTable("refresh");
}

/*function param_search(){
	var title=$("#txt_search_title").val().trim();
	var status=$("#txt_search_status").val().trim();
	var bol=$("#mhcx").prop("checked");
	if((title==null || title=='')){
		//layer.alert('商品名称不能为空!');
		layer.alert(
				'查询条件不能为空',
				{
					icon:7,
				},
		)
		return;
	}
 	var Params={"title":title,"status":status,"mhcx":bol};
	$("#userListTable").bootstrapTable("refresh",{
		url:'/thy/getItemRecordByParam',
		query:Params
	});
}*/

function btn_delete(id){
	layer.confirm('确定删除',{icon:3,title:'提示'},function(index){
		$.ajax({
			type:'post',
			url:'/item/deleteById',
			data:{"id":id},
			dataType:'json',
			success:function(data){
				layer.msg(data.msg);
				$("#userListTable").bootstrapTable('refresh');
			},
			error:function(data){
				layer.msg(data.msg);
			}
		})
		layer.close(index);
	})
}


function btn_edit(id){
	var action="edit";
	var param={"id":id,"action":action};
	$.ajax({
		type:'GET',
		url:'/item/getRecordDetailOrEdit',
		data:param,
		dataType:'html',
		success:function(data){
			$("#myModalEdit #myModalEditBody").html(data);
		},
		error:function(data){
			
		}
	})
	$("#myModalEdit").modal('show');
	$("#myModalEdit").on('shown.bs.modal',function(){
		getZdInfo('SPLX','splx_edit');
		getZdInfo('STATUS','status_edit');
	})
}

function btn_search(){
	var title=$("#txt_search_title").val().trim();
	var status=$("#txt_search_status").val().trim();
	var bol=$("#mhcx").prop("checked");
	var Params={"title":title,"status":status,"mhcx":bol};
	btn_search_bz="1";
	$("#userListTable").bootstrapTable("refresh",{
		url:'/item/getAllItemRecord',
		query:Params
	});
	//$("#userListTable").bootstrapTable('refresh');
}

function btn_remove(){
	btn_search_bz="2";
	var title=$("#txt_search_title").val().trim();
	var status=$("#txt_search_status").val().trim();
	var bol=$("#mhcx").prop("checked");
	var Params={"title":title,"status":status,"mhcx":bol};
	$("#userListTable").bootstrapTable('refresh',{
		url:'/item/getAllItemRecord',
		query:Params
	});
}

function changeStatus(id,status){
	var param={"id":id,"status":status,"czbs":"1"};
	$.ajax({
		type:'POST',
		url:'/item/updateByParamsSelective',
		data:param,
		dataType:"json",
		success:function(data){
			layer.msg(data.msg);
			$("#userListTable").bootstrapTable('refresh');
		},
		error:function(data){
			layer.msg(data.msg);
		}
	})
}

function btn_submit(){
	var title=$("#title").val().trim();
	var price=$("#price").val().trim();
	var num=$("#num").val().trim();
	if(title=='' || title==null){
		layer.alert('商品名称不能为空!',{
				icon:7,
			}
		)
		return;
	}
	if(price=='' || price==null){
		layer.alert('商品价格不能为空',{
			icon:7
		})
		return;
	}
	if(num=='' || num==null){
		layer.alert('商品库存不能为空',{
			icon:7
		})
		return;
	}
	var param=$("#item_add_form").serialize();
	
	$.ajax({
		type:'POST',
		url:'/item/insertItemRecord',
		dataType:'json',
		data:param,
		success:function(data){
			layer.msg(data.msg);
		},
		error:function(data){
			layer.msg(data.msg);
		}
	})
}

function btn_export(){
	$('#userListTable').tableExport({
		type:'excel',
		escape:'false',
		fileName:'商品信息表'
	})
}

function btn_update(){
	var formEditParam=$("#editform").serialize();
	$.ajax({
		type:'POST',
		url:'/item/updateItemRecord',
		data:formEditParam,
		dataType:'json',
		success:function(data){
			layer.msg(data.msg);
			$("#userListTable").bootstrapTable("refresh");
		},
		error:function(data){
			
		}
	})
	
}
/////////////////////////////////////////////////////
//用户进行图片上传。返回地址

/*function setImg(obj){
	var f=$(obj).val();
	alert(f);
	console.log(obj);
	if(f==null || f==undefined || f==''){
		return false;
	}
	if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)){
		alert('类型必须是图片(.png|jpg|bmp|PNG|JPG|BMPGIF)');
		$(obj).val('');
		return false;
	}
	var data=new FormData();
	console.log(data);
	$.each($(obj)[0].files,function(i,file){
		data.append('file',file);
	});
	console.log(data);
	$.ajax({
		type:'POST',
		url:'/thy/fileUpload',
		data:data,
		cache:false,
		contentType:false,
		processData:false,
		dataType:'json',
		success:function(ret){
			console.log(ret);
			if(ret.code==0){
				alert("11")
				alert(ret.result.url);
				$("#photoUrl").val(ret.result.url);//将地址存储好
				$("#photourlShow").attr("src",ret.result.url);//显示图片
				alert(ret.message);
			}else{
				alert(ret.message);
				$("#url").val("");
				$(obj).val('');
			}
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
			alert("上传失败,请检查网络后重试");
		}
	})
}*/

//获取字典
function getZdInfo(zddm,id){
	var param={"zddm":zddm};
	$.ajax({
		type:'GET',
		url:'/thy/static/getZd',
		dataType:'json',
		data:param,
		contentType: "application/json; charset=utf-8",
		success:function(data){
			var sel=$("#"+id);
			sel.find("option").remove();
			sel.append("<option></option>");
			for(var i=0;i<data.length;i++){//外循环，循环有几种字典类
				for(var j=0;j<data[i].length;j++){//内循环，循环没个字典类中有几个字典项
					sel.append("<option value='"+data[i][j].dm+"'>"+data[i][j].mc+"</option>");
				}
			}
		},
		error:function(data){
			
		}
	})
}

function preViewLBT(){
	getLBTFile('1','/thy/static/main');
}
function addLBT(){
	alert('添加轮播图');
}
function removeLBT(){
	var id=$("input[name='img_id123']").val();
	var param={"img_id":id};
	alert(id);
	$.ajax({
		type:'POST',
		url:'/thy/updateLbtByParam',
		data:param,
		dataType:'json',
		success:function(data){
			layer.msg(data.msg);
			getLBTFile('1','/thy/static/main');
		},
		error:function(data){
			
		}
	})
}

function logIn(){
	alert("login");
}

function register(){
	alert('register')
}

$(function(){
	var sel=$("#province");
	var param={"zddm":"XZQH","parentid":'000000'};
	$.ajax({
		type:'GET',
		url:'/thy/static/getZd',
		data:param,
		dataType:'json',
		contentType: "application/json; charset=utf-8",
		success:function(data){
			var num2=data.length;
			for(var i=0;i<num2;i++){
				for(var i=0;i<data.length;i++){//外循环，循环有几种字典类
					for(var j=0;j<data[i].length;j++){//内循环，循环没个字典类中有几个字典项
						sel.append("<option value='"+data[i][j].dm+"'>"+data[i][j].mc+"</option>");
					}	
				} 
			}
		},
		error:function(e){
			
		}
	});
})

function provinceChange(){
	var options=$("#province option:selected");
	var sel=$("#city");
	sel.find("option").remove();
	var param={"zddm":"XZQH","parentid":options.val()};
	$.ajax({
		type:'GET',
		url:'/thy/static/getZd',
		data:param,
		dataType:'json',
		contentType: "application/json; charset=utf-8",
		success:function(data){
			var num2=data.length;
			for(var i=0;i<num2;i++){
				for(var i=0;i<data.length;i++){//外循环，循环有几种字典类
					for(var j=0;j<data[i].length;j++){//内循环，循环没个字典类中有几个字典项
						sel.append("<option value='"+data[i][j].dm+"'>"+data[i][j].mc+"</option>");
					}	
				} 
			}
		},
		error:function(e){
			
		}
	});
}

function cityChange(){
	var options=$("#city option:selected");
	var sel=$("#county");
	sel.find("option").remove();
	var param={"zddm":"XZQH","parentid":options.val()};
	$.ajax({
		type:'GET',
		url:'/thy/static/getZd',
		data:param,
		dataType:'json',
		contentType: "application/json; charset=utf-8",
		success:function(data){
			var num2=data.length;
			for(var i=0;i<num2;i++){
				for(var i=0;i<data.length;i++){//外循环，循环有几种字典类
					for(var j=0;j<data[i].length;j++){//内循环，循环没个字典类中有几个字典项
						sel.append("<option value='"+data[i][j].dm+"'>"+data[i][j].mc+"</option>");
					}	
				} 
			}
		},
		error:function(e){
			
		}
	});
}

//在线留言阅读事件
function btn_reading2(xh){
	$.ajax({
		type:'POST',
		url:'/kf/readingMsg',
		data:{"xh":xh},
		dataType:'json',
		success:function(data){
			if(data.success){
				$("#onLineMsgTab").bootstrapTable("refresh");
				layer.msg(data.msg);
			}else{
				layer.msg(data.msg);
			}
		},
		error:function(e){
			layer.msg('系统异常，请及时联系开发人员哦O(∩_∩)O哈哈~');
		}
	})
}
