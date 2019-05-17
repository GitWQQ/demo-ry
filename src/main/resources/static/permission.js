$(function(){
	
	$("#btn_six").click(function(){
 		$("#panel_six").css("display","");
		$("#panel_six").siblings().css("display","none");
		initBootStrap_Permission();
	})
	
})

function initBootStrap_Permission(){
	$("#role_user_Tab").bootstrapTable({
		method:'GET',//服务器数据的请求方式
		url:'/user/getAllUser',
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
		        	 field:'xh',
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
		url:'',
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
		uniqueId:'id',             
		columns:[
		         {
		        	 field:'id',
		        	 title:'序号',
		        	 visible:false,
		         },{
		        	 field:'permission',
		        	 title:'权限名称',
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
		        			btn='<button id="btn_edit" class="btn btn-warning btn-sm" onclick="btn_edit_p('+value+');">未阅读</button>&nbsp;';
		        		}else{
		        			btn='<button id="btn_edit" class="btn btn-success btn-sm" onclick="btn_delete_p('+value+');">已阅读</button>&nbsp;';
		        		}
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
	$("#roleUserModal").modal('show');
/*	$("#roleUserModal").on('shown.bs.modal',function(){
		getZdInfo('SPLX','splx_edit');
		getZdInfo('STATUS','status_edit');
	})*/
}
