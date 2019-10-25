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
			$('#itemListTable').bootstrapTable('hideColumn', 'created');
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
					layer.msg("正在修改密码......",{icon:16,time:6000,shade:[0.5,'#000',true]})
				},
				success:function(data){
					layer.msg(data.msg,{icon:16,time:6000,shade:[0.5,'#000',true]});
				},
				error:function(e){
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
	$("#itemListTable").bootstrapTable({
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
		columns:[                                                
				 	{                                            
				 	   checkbox:true,                            
				 	   align:'center',
				 	   width:60
				 	},{                                          
				 	 	title:'编号',                              
				 	    field:'id',                              
				 	    align:'center',
				 	    width:80
				 	},{                                          
				 	    title:'商品名称',                            
				 	    field:'title',                           
				 	   	align:'center'
				 	},{                                          
				 	    title:'卖点',                              
				 	    field:'sell_point',                      
				 	    align:'center',
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

	$("#userListTable").bootstrapTable({
		url:'/user/getAllUser',
		method:'GET',
		dataType:'json',
		contentType:'application/x-www-form-urlencoded',
		toolbar:'#userToolsBar',
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
		columns:[
			{
				title:'编号',
				field:'id',
				align:'center',
				width:80,
				visible:false
			},{
				title:'用户名',
				field:'username',
				align:'center',
				width:100
			},{
				title:'真实姓名',
				field:'realname',
				align:'center',
				width:100
			},{
				title:'性别',
				field:'sex',
				align:"center",
				width:100
			},{
				title:'身份证号',
				field:'sfzh',
				align:'center',
				width:100
			},{
				title:'联系电话',
				field:'phone',
				align:'center',
				width:100
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
	$("#itemListTable").bootstrapTable("refresh");
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
	//$("#itemListTable").bootstrapTable("refresh");
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
	$("#itemListTable").bootstrapTable("refresh",{
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
				$("#itemListTable").bootstrapTable('refresh');
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
	$("#itemListTable").bootstrapTable("refresh",{
		url:'/item/getAllItemRecord',
		query:Params
	});
	//$("#itemListTable").bootstrapTable('refresh');
}

function btn_remove(){
	btn_search_bz="2";
	var title=$("#txt_search_title").val().trim();
	var status=$("#txt_search_status").val().trim();
	var bol=$("#mhcx").prop("checked");
	var Params={"title":title,"status":status,"mhcx":bol};
	$("#itemListTable").bootstrapTable('refresh',{
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
			$("#itemListTable").bootstrapTable('refresh');
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
	$('#itemListTable').tableExport({
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
			$("#itemListTable").bootstrapTable("refresh");
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

//新增用户信息
function btn_user_add(){
	$("#addUserModal").modal('show');
	$("#addUserModal").on('shown.bs.modal',function(){
		getZdInfo('SEX','sex');
	})
}

//查询用户信息
function btn_user_search(){

	var username=$("#search_username").val().trim();
	var sfzh=$("#search_sfzh").val().trim();
	var Params={"username":username,"sfzh":sfzh};
	btn_search_bz="1";
	$("#userListTable").bootstrapTable("refresh",{
		url:'/user/getAllUser',
		query:Params
	});
}

//删除用户信息
function btn_user_remove(){

}

function btn_user_add_form(){

	var xval=getBusyOverlay('viewport',{ opacity:0, text:'viewport: loading...', style:'text-shadow: 0 0 3px black;font-weight:bold;font-size:16px;color:white'},{color:'#ff0', size:50, type:'o'});

	var form=$("#userAddForm").serialize();
	$.ajax({
		type:'POST',
		url:'/user/addUserInfo',
		data:form,
		dataType:'json',
		cache:false,
		contentType:'application/x-www-form-urlencoded',
		beforeSend:function(){
			//layer.msg('正在提交请稍候。。。', {icon: 16,shade : [0.5 , '#000' , true]});
			if(xval) {
				xval.settext("正在保存，请稍后......");
			}
		},
		success:function (data){
			xval.remove();
			if(data.success){
				$('#addUserModal').modal('hide');
				$("#userListTable").bootstrapTable("refresh");
			}
		},
		error:function (event) {
			xval.remove();
			layer.msg('出错咯o(╥﹏╥)o,请与后台联系!',{
				btn:'我知道了'
			},function () {
				$('#addUserModal').modal('hide');
			})
		}
	})
}

//================进度圈脚本 =====================

function onIEWinResize(event) {
	function parseWidth(val) {return (isNaN(parseInt(val,10))?0:parseInt(val,10));}
	if(!event) {event=window.event;} var i,cs,parent=this, div=parent.getElementsByTagName("div");
	if(div.length>0) {if(parent.currentStyle){cs=parent.currentStyle;}else if(document.defaultView&&document.defaultView.getComputedStyle){cs=document.defaultView.getComputedStyle(parent,"");}else{cs=parent.style;}
		for(i=0; i<div.length; i++) {if(div[i].className=='buzy_ele') {
			div[i].style.height=(parent.offsetHeight-parseWidth(cs.borderBottomWidth)-parseWidth(cs.borderTopWidth));
			div[i].style.width=(parent.offsetWidth-parseWidth(cs.borderLeftWidth)-parseWidth(cs.borderRightWidth));
			div[i].firstChild.style.height=div[i].style.height; div[i].firstChild.style.width=div[i].style.width;
			break;
		}
		}
	}
}
function onIEVPResize(event) {
	if(!event) {event=window.event;} var vp=document.getElementById('viewport'); if(vp) {
		if(typeof document.documentElement!='undefined') {vp.style.width=document.documentElement.clientWidth+'px'; vp.style.height=document.documentElement.clientHeight+'px';}
		else {vp.style.width=document.getElementsByTagName('body')[0].clientWidth+'px'; vp.style.height=document.getElementsByTagName('body')[0].clientHeight+'px';}
	}
}
function onIEVPScroll(event) {
	if(!event) {event=window.event;} var vp=document.getElementById('viewport'); if(vp) {
		if(typeof document.documentElement!='undefined') {vp.style.left=document.documentElement.scrollLeft+'px'; vp.style.top=document.documentElement.scrollTop+'px';}
		else {vp.style.left=document.getElementsByTagName('body')[0].scrollLeft+'px'; vp.style.top=document.getElementsByTagName('body')[0].scrollTop+'px';}
	}
}
function getBusyOverlay(parent,overlay,busy) {
	if((typeof(parent)==='object' || parent=='viewport') && document.getElementsByTagName) {
		function parseWidth(val) {return (isNaN(parseInt(val,10))?0:parseInt(val,10));}
		var isIE,isVL,isCV,isWK,isGE,i,b,o,lt,rt,lb,rb,cz,cs,size,viewport,inner,outer,string,canvas,context,ctrl,opacity,color,text,styles,waiting=true;
		if(parent=='viewport') {viewport=document.getElementById('viewport');
			if(!viewport) {viewport=document.createElement('div'); viewport.id='viewport'; cz=viewport.style;
				cz.backgroundColor='transparent'; cz.position='fixed'; cz.overflow='hidden';
				cz.display='block'; cz.zIndex=999999; cz.left='0px'; cz.top='0px'; cz.zoom=1;
				cz.width='100%'; cz.height='100%'; cz.margin='0px'; cz.padding='0px';
				if(document.all&&!window.opera&&!window.XMLHttpRequest) {cz.position='absolute';
					if(typeof document.documentElement!='undefined') {cz.width=document.documentElement.clientWidth+'px'; cz.height=document.documentElement.clientHeight+'px';}
					else {cz.width=document.getElementsByTagName('body')[0].clientWidth+'px'; cz.height=document.getElementsByTagName('body')[0].clientHeight+'px';}
				}document.getElementsByTagName("body")[0].appendChild(viewport);
			}else {viewport.style.display='block';
				if(document.all&&!window.opera&&!window.XMLHttpRequest) {
					if(typeof document.documentElement!='undefined') {viewport.style.width=document.documentElement.clientWidth+'px';	viewport.style.height=document.documentElement.clientHeight+'px';}
					else {viewport.style.width=document.getElementsByTagName('body')[0].clientWidth+'px';	viewport.style.height=document.getElementsByTagName('body')[0].clientHeight+'px';}
				}
			}parent=viewport;
		}
		if(parent.currentStyle){cs=parent.currentStyle;}else if(document.defaultView&&document.defaultView.getComputedStyle){cs=document.defaultView.getComputedStyle(parent,"");}else{cs=parent.style;}
		while(cs.display.search(/block|inline-block|table|inline-table|list-item/i)<0) {parent=parent.parentNode; if(parent.currentStyle){cs=parent.currentStyle;}else if(document.defaultView&&document.defaultView.getComputedStyle){cs=document.defaultView.getComputedStyle(parent,"");}else{cs=parent.style;} if(parent.tagName.toUpperCase()=='BODY') {parent="";}}
		if(typeof(parent)==='object') {
			if(!overlay) {overlay=new Object(); overlay['opacity']=0; overlay['color']='white'; overlay['text']=''; overlay['style']='';}
			if(!busy) {busy=new Object(); busy['size']=32; busy['color']='#000'; busy['type']='tube'; busy['iradius']=8; busy['weight']=3; busy['count']=12; busy['speed']=96; busy['minopac']=.25;}
			opacity=Math.max(0.0,Math.min(1.0,(typeof overlay['opacity']==='number'?overlay['opacity']:0)||0)); color=(typeof overlay['color']==='string'?overlay['color']:'white');
			text=(typeof overlay['text']==='string'?overlay['text']:''); styles=(typeof overlay['style']==='string'?overlay['style']:'');
			canvas=document.createElement("canvas"); isCV=canvas.getContext?1:0;
			isWK=navigator.userAgent.indexOf('WebKit')>-1?1:0; isGE=navigator.userAgent.indexOf('Gecko')>-1&&window.updateCommands?1:0;
			isIE=navigator.appName=='Microsoft Internet Explorer'&&window.navigator.systemLanguage&&!window.opera?1:0;
			isVL=document.all&&document.namespaces?1:0; outer=document.createElement('div'); parent.style.position=(cs.position=='static'?'relative':cs.position);
			cz=parent.style.zIndex>=0?(parent.style.zIndex-0+2):2; if(isIE && !cs.hasLayout) {parent.style.zoom=1;}
			outer.style.position='absolute'; outer.style.overflow='hidden'; outer.style.display='block'; outer.style.zIndex=cz;
			outer.style.left=0+'px'; outer.style.top=0+'px'; outer.style.width='100%'; outer.style.height='100%';
			if(isIE) {outer.className='buzy_ele'; outer.style.zoom=1; outer.style.margin='0px'; outer.style.padding='0px'; outer.style.height=(parent.offsetHeight-parseWidth(cs.borderBottomWidth)-parseWidth(cs.borderTopWidth)); outer.style.width=(parent.offsetWidth-parseWidth(cs.borderLeftWidth)-parseWidth(cs.borderRightWidth));}
			if(typeof(cs.borderRadius)=="undefined"){
				if(typeof(cs.MozBorderRadius)!="undefined"){
					lt=parseFloat(cs.MozBorderRadiusTopleft)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderTopWidth));
					rt=parseFloat(cs.MozBorderRadiusTopright)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderTopWidth));
					lb=parseFloat(cs.MozBorderRadiusBottomleft)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderBottomWidth));
					rb=parseFloat(cs.MozBorderRadiusBottomright)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderBottomWidth));
					outer.style.MozBorderRadiusTopleft=lt+"px"; outer.style.MozBorderRadiusTopright=rt+"px"; outer.style.MozBorderRadiusBottomleft=lb+"px"; outer.style.MozBorderRadiusBottomright=rb+"px";
				}else if(typeof(cs.WebkitBorderRadius)!="undefined"){
					lt=parseFloat(cs.WebkitBorderTopLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderTopWidth));
					rt=parseFloat(cs.WebkitBorderTopRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderTopWidth));
					lb=parseFloat(cs.WebkitBorderBottomLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderBottomWidth));
					rb=parseFloat(cs.WebkitBorderBottomRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderBottomWidth));
					outer.style.WebkitBorderTopLeftRadius=lt+"px"; outer.style.WebkitBorderTopRightRadius=rt+"px"; outer.style.WebkitBorderBottomLeftRadius=lb+"px"; outer.style.WebkitBorderBottomRightRadius=rb+"px";
				}
			}else {
				lt=parseFloat(cs.borderTopLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderTopWidth));
				rt=parseFloat(cs.borderTopRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderTopWidth));
				lb=parseFloat(cs.borderBottomLeftRadius)-Math.min(parseFloat(cs.borderLeftWidth),parseFloat(cs.borderBottomWidth));
				rb=parseFloat(cs.borderBottomRightRadius)-Math.min(parseFloat(cs.borderRightWidth),parseFloat(cs.borderBottomWidth));
				outer.style.borderTopLeftRadius=lt+"px"; outer.style.borderTopRightRadius=rt+"px"; outer.style.borderBottomLeftRadius=lb+"px"; outer.style.borderBottomRightRadius=rb+"px";
			}
			parent.appendChild(outer); inner=document.createElement('div');
			inner.style.position='absolute'; inner.style.cursor='progress'; inner.style.display='block';
			inner.style.zIndex=(cz-1); inner.style.left=0+'px'; inner.style.top=0+'px';
			inner.style.width=100+'%'; inner.style.height=100+'%'; inner.style.backgroundColor=color;
			if(isIE) {inner.style.zoom=1; inner.style.margin='0px'; inner.style.padding='0px'; inner.style.height=outer.style.height; inner.style.width=outer.style.width; }
			if(typeof(cs.borderRadius)=="undefined"){
				if(typeof(cs.MozBorderRadius)!="undefined"){inner.style.MozBorderRadiusTopleft=lt+"px"; inner.style.MozBorderRadiusTopright=rt+"px"; inner.style.MozBorderRadiusBottomleft=lb+"px"; inner.style.MozBorderRadiusBottomright=rb+"px";}else
				if(typeof(cs.WebkitBorderRadius)!="undefined"){inner.style.WebkitBorderTopLeftRadius=lt+"px"; inner.style.WebkitBorderTopRightRadius=rt+"px"; inner.style.WebkitBorderBottomLeftRadius=lb+"px"; inner.style.WebkitBorderBottomRightRadius=rb+"px";}
			}else {inner.style.borderTopLeftRadius=lt+"px"; inner.style.borderTopRightRadius=rt+"px"; inner.style.borderBottomLeftRadius=lb+"px"; inner.style.borderBottomRightRadius=rb+"px";}
			if(isIE) {inner.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+parseInt(opacity*100)+")";}else {inner.style.opacity=opacity;}
			outer.appendChild(inner); size=Math.max(16,Math.min(512,(typeof busy['size']==='number'?(busy['size']==0?32:busy['size']):32)));
			if(isVL){
				if(document.namespaces['v']==null) {
					var e=["shape","shapetype","group","background","path","formulas","handles","fill","stroke","shadow","textbox","textpath","imagedata","line","polyline","curve","roundrect","oval","rect","arc","image"],s=document.createStyleSheet();
					for(i=0; i<e.length; i++) {s.addRule("v\\:"+e[i],"behavior: url(#default#VML);");} document.namespaces.add("v","urn:schemas-microsoft-com:vml");
				}
			}
			if(!isCV){canvas=document.createElement("div");}
			canvas.style.position='absolute'; canvas.style.cursor='progress'; canvas.style.zIndex=(cz-0+1);
			canvas.style.top='50%'; canvas.style.left='50%'; canvas.style.marginTop='-'+(size/2)+'px'; canvas.style.marginLeft='-'+(size/2)+'px';
			canvas.width=size; canvas.height=size; canvas.style.width=size+"px"; canvas.style.height=size+"px";
			outer.appendChild(canvas);
			if(text!=""){
				string=document.createElement('div'); string.style.position='absolute'; string.style.overflow='hidden';
				string.style.cursor='progress'; string.style.zIndex=(cz-0+1); string.style.top='50%'; string.style.left='0px';
				string.style.marginTop=2+(size/2)+'px'; string.style.textAlign='center'; string.style.width=100+'%'; string.style.height='auto';
				if(styles!="") {string.innerHTML='<span '+(styles.match(/:/i)?'style':'class')+'="'+styles+'">'+text+'</span>';}
				else {string.innerHTML='<span>'+text+'</span>';} outer.appendChild(string);
			}
			if(isGE){outer.style.MozUserSelect="none"; inner.style.MozUserSelect="none"; canvas.style.MozUserSelect="none";}else
			if(isWK){outer.style.KhtmlUserSelect="none"; inner.style.KhtmlUserSelect="none"; canvas.style.KhtmlUserSelect="none";}else
			if(isIE){outer.unselectable="on"; inner.unselectable="on"; canvas.unselectable="on";}
			if(isVL){ctrl=getBusyVL(canvas,busy['color'],busy['size'],busy['type'],busy['iradius'],busy['weight'],busy['count'],busy['speed'],busy['minopac']); ctrl.start();}else
			if(isCV){ctrl=getBusyCV(canvas.getContext("2d"),busy['color'],busy['size'],busy['type'],busy['iradius'],busy['weight'],busy['count'],busy['speed'],busy['minopac']); ctrl.start();}
			else {ctrl=getBusy(canvas,busy['color'],busy['size'],busy['type'],busy['iradius'],busy['weight'],busy['count'],busy['speed'],busy['minopac']); ctrl.start();}
			if(isIE) {parent.onresize=onIEWinResize; if(parent.id=='viewport'&&!window.XMLHttpRequest) {window.onresize=onIEVPResize; window.onscroll=onIEVPScroll;}}
			return {
				remove: function (){if(waiting){waiting=false; ctrl.stop(); delete ctrl; parent.removeChild(outer); if(parent.id=='viewport') {parent.style.display='none';}}},
				settext: function (v){if(string&&typeof(v)=='string') {string.firstChild.innerHTML=v; return false;}}
			};
		}
	}
}
function getBusy(obj,cl,sz,tp,ir,w,ct,sp,mo) {
	function getHEX(v) {var col=v||'#000000';
		if(!col.match(/^#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]$/i)) {
			if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f]$/i)) {col='#'+v.substr(1,1)+v.substr(1,1)+v.substr(2,1)+v.substr(2,1)+v.substr(3,1)+v.substr(3,1);}
		}return col;
	}
	var running=false,i=0,os=0,al=0,f=100,c,h,p,t,x,y,v,hp,ph,sh,ele=new Array();
	c=getHEX(cl); tp=tp||"t"; t=(tp.match(/^[coprt]/i)?tp.substr(0,1).toLowerCase():'t');
	ct=Math.max(5,Math.min(36,ct||12)); sp=Math.max(30,Math.min(1000,sp||96));
	sz=Math.max(16,Math.min(512,sz||32)); ir=Math.max(1,Math.min((sz/2)-2,ir||sz/4));
	w=Math.max(1,Math.min((sz/2)-ir,w||sz/10)); mo=Math.max(0,Math.min(0.5,mo||0.25));
	al=360/ct; hp=(Math.PI/2)*-1; ph=Math.PI/180; w=(t!='c'?parseInt((w/2)*3):w); v=parseInt((sz/2)-(w/2));
	for(i=0;i<ct;i++) {
		sh=document.createElement('div'); x=Math.round(v+v*Math.cos(hp+(i+1)*al*ph)); y=Math.round(v+v*Math.sin(hp+(i+1)*al*ph));
		sh.style.position='absolute'; sh.style.margin='0px'; sh.style.width=w+'px'; sh.style.height=w+'px';
		sh.style.lineHeight='1px'; sh.style.fontSize='0px'; sh.style.top=y+'px'; sh.style.left=x+'px'; sh.style.backgroundColor=c;
		if(document.all&&!window.opera) {sh.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+parseInt(Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))))*100)+")";
		}else {sh.style.opacity=Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1)))); }
		obj.appendChild(sh); ele[i]=sh;
	}
	function nextLoop(){
		if(!running) {return;} os=(os+1)%ct;
		if(document.all&&!window.opera) {for(i=0;i<ct;i++){al=((os+i)%ct); ele[al].style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity="+parseInt(Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))))*100)+")";}}
		else {for(i=0;i<ct;i++){al=((os+i)%ct); ele[al].style.opacity=Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))));}}
		setTimeout(nextLoop,sp);
	}
	nextLoop(0);
	return {
		start: function (){if(!running){running=true; nextLoop(0);}},
		stop: function (){running=false; for(i=0;i<ct;i++) {if(document.all&&!window.opera) {ele[i].style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity=0)";}else {ele[i].setAttribute('opacity',0);}}},
		pause: function (){running=false; }
	};
}
function getBusyVL(obj,cl,sz,tp,ir,w,ct,sp,mo) {
	function getHEX(v) {var col=v||'#000000';
		if(!col.match(/^#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]$/i)) {
			if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f]$/i)) {col='#'+v.substr(1,1)+v.substr(1,1)+v.substr(2,1)+v.substr(2,1)+v.substr(3,1)+v.substr(3,1);}
		}return col;
	}
	var running=false,os=0,al=0,f=100,c,i,h,p,t,x,y,hs,qs,hw,qw,rp,sh,fl,ele=new Array();
	c=getHEX(cl); tp=tp||"t"; t=(tp.match(/^[coprt]/i)?tp.substr(0,1).toLowerCase():'t');
	ct=Math.max(5,Math.min(36,ct||12)); sp=Math.max(30,Math.min(1000,sp||96));
	sz=Math.max(16,Math.min(512,sz||32)); ir=Math.max(1,Math.min((sz/2)-2,ir||sz/4));
	w=Math.max(1,Math.min((sz/2)-ir,w||sz/10)); mo=Math.max(0,Math.min(0.5,mo||0.25));
	h=(sz/2)-ir; x=sz/2; y=x; al=360/ct; hs=parseInt((sz/2)*f); qs=parseInt(hs/2);
	hw=parseInt((w/2)*f); qw=parseInt(hw/2); rp=hs-parseInt(ir*f);
	switch(t) {
		case "c": p='m '+hs+','+(rp-hw)+' ar '+(hs-hw)+','+(rp-hw-hw)+','+(hs+hw)+','+rp+','+(hs-hw)+','+(rp-hw-hw)+','+(hs-hw)+','+(rp-hw-hw)+' e'; break;
		case "p": p='m '+(hs-qw)+',0 l '+(hs-hw)+','+rp+','+(hs+hw)+','+rp+','+(hs+qw)+',0 x e'; break;
		case "o": p='m '+hs+','+(rp-qs)+' ar '+(hs-hw)+',0,'+(hs+hw)+','+rp+','+(hs-hw)+',0,'+(hs-hw)+',0 e'; break;
		case "t": p='m '+(hs-hw)+','+rp+' l '+(hs-hw)+','+hw+' qy '+hs+',0 qx '+(hs+hw)+','+hw+' l '+(hs+hw)+','+rp+' x e'; break;
		default: p='m '+(hs-hw)+',0 l '+(hs-hw)+','+rp+','+(hs+hw)+','+rp+','+(hs+hw)+',0 x e'; break;
	}
	for(i=0;i<ct;i++) {
		sh=document.createElement('v:shape'); sh.setAttribute('filled','t'); sh.setAttribute('stroked','f');
		sh.setAttribute('coordorigin','0,0'); sh.setAttribute('coordsize',(sz*f)+','+(sz*f));
		sh.setAttribute('path',p); sh.style.rotation=(i*al); sh.style.position='absolute'; sh.style.margin='0px';
		sh.style.width=sz+'px'; sh.style.height=sz+'px'; sh.style.top='-1px'; sh.style.left='-1px';
		obj.appendChild(sh); fl=document.createElement('v:fill');
		fl.setAttribute('opacity',Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1)))));
		fl.setAttribute('color',c); sh.appendChild(fl); ele[i]=fl;
	}
	function nextLoop(){
		if(!running) {return;} os=(os+1)%ct;
		if(document.documentMode==8) {
			for(i=0;i<ct;i++) {al=((os+i)%ct); ele[al].opacity=Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))));}
		}else {
			for(i=0;i<ct;i++) {al=((os+i)%ct); ele[al].setAttribute('opacity',Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1)))));}
		}
		setTimeout(nextLoop,sp);
	}
	nextLoop(0);
	return {
		start: function (){if(!running){running=true; nextLoop(0);}},
		stop: function (){running=false; for(i=0;i<ct;i++) {ele[i].setAttribute('opacity',0);}},
		pause: function (){running=false; }
	};
}
function getBusyCV(ctx,cl,sz,tp,ir,w,ct,sp,mo) {
	function getRGB(v){
		function hex2dec(h){return(Math.max(0,Math.min(parseInt(h,16),255)));}
		var r=0,g=0,b=0; v=v||'#000'; if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f]$/i)) {
			r=hex2dec(v.substr(1,1)+v.substr(1,1)),g=hex2dec(v.substr(2,1)+v.substr(2,1)),b=hex2dec(v.substr(3,1)+v.substr(3,1));
		}else if(v.match(/^#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]$/i)) {
			r=hex2dec(v.substr(1,2)),g=hex2dec(v.substr(3,2)),b=hex2dec(v.substr(5,2));
		} return r+','+g+','+b;
	}
	function drawOval(ctx,w,h){ctx.beginPath(); ctx.moveTo(-w/2,h/2); ctx.quadraticCurveTo(-w/2,0,0,0); ctx.quadraticCurveTo(w/2,0,w/2,h/2); ctx.quadraticCurveTo(w/2,h,0,h); ctx.quadraticCurveTo(-w/2,h,-w/2,h/2); ctx.fill();}
	function drawTube(ctx,w,h){ctx.beginPath(); ctx.moveTo(w/2,0); ctx.lineTo(-w/2,0); ctx.lineTo(-w/2,h-(w/2)); ctx.quadraticCurveTo(-w/2,h,0,h); ctx.quadraticCurveTo(w/2,h,w/2,h-(w/2)); ctx.fill();}
	function drawPoly(ctx,w,h){ctx.beginPath(); ctx.moveTo(w/2,0); ctx.lineTo(-w/2,0); ctx.lineTo(-w/4,h); ctx.lineTo(w/4,h); ctx.fill();}
	function drawCirc(ctx,r,z){ctx.beginPath(); ctx.arc(r,r,r,0,Math.PI*2,false); ctx.fill();}
	var running=false,os=0,al=0,c,i,h,t,x,y;
	c=getRGB(cl); tp=tp||"t"; t=(tp.match(/^[coprt]/i)?tp.substr(0,1).toLowerCase():'t');
	ct=Math.max(5,Math.min(36,ct||12)); sp=Math.max(30,Math.min(1000,sp||96));
	sz=Math.max(16,Math.min(512,sz||32)); ir=Math.max(1,Math.min((sz/2)-2,ir||sz/4));
	w=Math.max(1,Math.min((sz/2)-ir,w||sz/10)); mo=Math.max(0,Math.min(0.5,mo||0.25));
	h=(sz/2)-ir; x=sz/2; y=x;
	function nextLoop(){
		if(!running) {return;} os=(os+1)%ct; ctx.clearRect(0,0,sz,sz); ctx.save(); ctx.translate(x,y);
		for(i=0;i<ct;i++) {al=2*((os+i)%ct)*Math.PI/ct;
			ctx.save(); ctx.translate(ir*Math.sin(-al),ir*Math.cos(-al)); ctx.rotate(al);
			ctx.fillStyle='rgba('+c+','+Math.min(1,Math.max(mo,1-((ct+1-i)/(ct+1))))+')';
			switch(t) {case "c": drawCirc(ctx,w/2,h); break; case "o": drawOval(ctx,w,h); break; case "p": drawPoly(ctx,w,h); break; case "t": drawTube(ctx,w,h); break; default: ctx.fillRect(-w/2,0,w,h); break;} ctx.restore();
		} ctx.restore();
		setTimeout(nextLoop,sp);
	}
	nextLoop(0);
	return {
		start: function (){if(!running){running=true; nextLoop(0);}},
		stop: function (){running=false; ctx.clearRect(0,0,sz,sz); },
		pause: function (){running=false; }
	};
}