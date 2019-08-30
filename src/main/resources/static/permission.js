$(function(){
	
	$("#btn_six").click(function(){
 		$("#panel_six").css("display","");
		$("#panel_six").siblings().css("display","none");
		initBootStrap_Permission();
	})
	
	// 根据输入的权限名称，获取权限名称的拼音首字母组成权限代码
	$("#permission_name").blur(function(){
		var val=$("#permission_name").val();
		var result=getPinYinFirstCharacter(val,true);
		$("#permission").val(result);
	});
	
	// 根据输入的权限名称，获取权限名称的拼音首字母组成权限代码
	$("#permission_edit_name").blur(function(){
		var val=$("#permission_edit_name").val();
		var result=getPinYinFirstCharacter(val,true);
		$("#permission_edit").val(result);
	});
	
})

function initBootStrap_Permission(){
	$("#role_user_Tab").bootstrapTable({
		method:'GET',//服务器数据的请求方式
		url:'/user/getAllUser',
		iconSize:'outline',
		dataType:'json',                                         
		contentType:'application/x-www-form-urlencoded',
		toolbar:'#',
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
		        	 field:'id',
		        	 title:'序号',
		        	 visible:false,
		         },{
		        	 field:'username',
		        	 title:'用户名',
		        	 width:100,
		        	 align:'center'
		         },{
		        	 field:'sex',
		        	 title:'性别',
		        	 width:100,
		        	 align:'center'
		         },{
		        	 field:'phone',
		        	 title:'联系电话',
		        	 align:'center',
		        	 width:100
		         },{
		        	 field:'email',
		        	 title:'邮箱',
		        	 align:'center',
		        	 width:100
		         },{
		        	 title:'角色',
		        	 align:'center',
		        	 width:100,
		        	 formatter:function(value,row,index){
		        		 var role="【";
		        		 for(var i=0;i<row.roles.length;i++){
		        			 if(row.roles.length==1){
		        				 if(row.roles[i].roleName=="admin"){
		        					 role+="管理员";
		        				 }else if(row.roles[i].roleName=="customer"){
		        					 role+="普通客户";
		        				 }else if(row.roles[i].roleName=="superadmin"){
		        					 role+="超级管理员";
		        				 }
		        			 }else{
		        				 if(row.roles[i].roleName=="admin"){
		        					 role+="管理员|";
		        				 }else if(row.roles[i].roleName=="customer"){
		        					 role+="普通客户|";
		        				 }else if(row.roles[i].roleName=="superadmin"){
		        					 role+="超级管理员|";
		        				 }
		        			 }
		        		 }
		        		 return role+"】";
		        	 }
		         },{
		        	 field:'created',
		        	 title:'注册时间',
		        	 align:'center',
		        	 width:100,
		        	 formatter:function(value,row,index){
		        		 return value;
		        	 }
		         },{
		        	 title:'操作',
		        	 field:'id',
		        	 align:'center',
		        	 width:80,
		        	 formatter:function(value,row,index){
		        		var btn;
		        			btn='<button id="btn_edit_ru" class="btn btn-primary btn-sm" onclick="btn_edit_ru(\''+value+'\');">授予角色</button>&nbsp;'+
		        			 '<button id="btn_delete_ru" class="btn btn-danger btn-sm" onclick="btn_delete_ru(\''+value+'\');">注销</button>&nbsp;';
			 			return btn;
		        	 }
		         }
		]
	})
	
	//==============================
	$("#permission_Tab").bootstrapTable({
		method:'GET',//服务器数据的请求方式
		url:'/per/getAllPermission',
		iconSize:'outline',
		dataType:'json',                                         
		contentType:'application/x-www-form-urlencoded',
		toolbar:'#permissionToolbar',
		iconSize:'outline',
		striped:true,
		silent:true,
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
		        	 field:'id',
		        	 title:'序号',
		        	 width:80,
		        	 align:'center',
		        	 visible:false
		         },{
		        	 field:'permission_name',
		        	 title:'权限名称',
		        	 width:100,
		        	 align:'center'
		         },{
		        	 field:'permission',
		        	 title:'权限代码',
		        	 width:150,
		        	 align:'center'
		         },{
		        	 field:'create_time',
		        	 title:'创建时间',
		        	 align:'center',
		        	 width:100
		         },{
		        	 field:'',
		        	 title:'停/启用状态',
		        	 align:'center',
		        	 width:100,
		        	 formatter:function(value,row,index){
		        	 
		        	 }
		         },{
		        	 title:'操作',
		        	 field:'id',
		        	 align:'center',
		        	 width:80,
		        	 formatter:function(value,row,index){
		        		var btn;
		        		btn='<button id="btn_edit" class="btn btn-warning btn-sm" onclick="btn_edit_permission('+value+');">修改</button>&nbsp;'		        		
		        		   +'<button id="btn_delete" class="btn btn-danger btn-sm" onclick="btn_delete_permission('+value+');">删除</button>&nbsp;';
			 			return btn;
		        	 }
		         }
		]
	})
	
	//=======================
	$("#roles_Tab").bootstrapTable({
		method:'GET',//服务器数据的请求方式
		url:'',
		iconSize:'outline',
		dataType:'json',                                         
		contentType:'application/x-www-form-urlencoded',
		toolbar:'#roleToolBar',
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
		        	 field:'id',
		        	 title:'序号',
		        	 visible:false,
		         },{
		        	 field:'roleName',
		        	 title:'角色名',
		        	 width:100,
		        	 align:'center'
		         },{
		        	 field:'createtime',
		        	 title:'创建时间',
		        	 align:'center',
		        	 width:100
		         },{
		        	 title:'操作',
		        	 field:'xh',
		        	 align:'center',
		        	 width:80,
		        	 formatter:function(value,row,index){
		        		var btn;
		        		if(row.status=="0"){
		        			btn='<button id="btn_edit" class="btn btn-warning btn-sm" onclick="btn_edit_r('+value+');">未阅读</button>&nbsp;';
		        		}else{
		        			btn='<button id="btn_edit" class="btn btn-success btn-sm" onclick="btn_delete_r('+value+');">已阅读</button>&nbsp;';
		        		}
			 			return btn;
		        	 }
		         }
		]
	})
}

function btn_edit_ru(xh){
	var param={"id":xh};
	$.ajax({
		type:'POST',
		url:'/user/getUserByParam',
		data:param,
		dataType:'html',
		success:function(data){
			$("#roleUserModal #myModal_body").html(data);
		},
		error:function(e){
			
		}
	})
	$("#roleUserModal").modal('show');
	$("#roleUserModal").on('shown.bs.modal',function(){
		getRoles();
	})
}


function btn_delete_ru(xh){
	layer.confirm('确定注销此用户',{icon:3,title:'提示'},function(index){
		var param={'id':xh};
		$.ajax({
			type:'POST',
			url:'/user/removeUserByParam',
			data:param,
			dataType:'json',
			beforeSend:function(){
				//layer.msg("正在注销用户.........",{icon:16});
			},
			success:function(data){
				$("#role_user_Tab").bootstrapTable("refresh");
			}
		})
		layer.close(index);
	})
}


function getRoles(){
	$.ajax({
		type:'GET',
		url:'/role/getRoles',
		dataType:'json',
		success:function(data){
			var sel=$("#example-getting-started");
			sel.find("option").remove();
			sel.append("<option>111</option>");
			for(var i=0;i<data.length;i++){//外循环，循环有几种字典类
				sel.append("<option value='"+data[i].id+"'>"+data[i].roleName+"</option>");
			}
		},
		error:function(){
			
		}
	})
}

function btn_permissionAdd(){
	var permission_name=$("#permission_name").val().trim();
	var permission=$("#permission").val().trim();
	$.ajax({
		type:'POST', //请求方式，post,get
		url:'/per/addPermission', //发送请求地址
		data:{"permission_name":permission_name,"permission":permission},//请求参数
		dataType:'json',//要求为String类型的参数，预期服务器返回的数据类型，xml，html,script,json,text,jsonp
		async:true,	//要求为boolean类型参数，默认true异步。设置为false发送同步请求
		timeout:5000, //设置请求超时时间，单位毫秒
		contentType:"application/x-www-form-urlencoded",//设置请求向服务器发送参数的格式，
		success:function(data){	
			$("#addPermissionModal").modal('hide');
			$("#permission_Tab").bootstrapTable("refresh");
			
		},
		error:function(){
			
		}
	})
}

function btn_delete_permission(Id){
	layer.confirm('你确定删除这个数据吗?',{btn:['确定','取消']},
			function(){
				$.post('/per/removePermission',{"id":Id},function(msg){
					if(msg=="false"){
						layer.msg('删除失败',{icon:2});
					}
				})
				layer.msg('删除成功',{icon:1,time:1000});
				$("#permission_Tab").bootstrapTable("refresh");
			})		
}

function btn_edit_permission(Id){
	var param={"id":Id};
	$.ajax({
		type:'GET',
		url:'/per/getPermissionById',
		data:param,
		dataType:'html',
		success:function(data){
			$("#permissionEditModal_Body").html(data);
		},
		error:function(data){
				
		}
	})
	$("#permissionEditModal").modal("show");
	
}

