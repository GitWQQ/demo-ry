$(document).ready(function(){
		var oFileInput=new menu.fileInput();
		var oFileInput2=new menu2.fileInput();
		oFileInput.Init('img_file',"/thy/fileUpload");
		oFileInput2.Init('lbt_file',"/thy/LbtFileUpload");
	})
	menu={
		fileInput:function(){
			var oFile=new Object();
			//初始化fileinput控件
			oFile.Init=function(ctrlName,uploadUrl){
				var control=$("#"+ctrlName);
				//初始化上传控件样式
				control.fileinput({
					language:'zh',
					uploadUrl:uploadUrl,
					showUpload:true,//是否显示上传按钮
					showCaption:true,//是否显示标题
					browseClass:'btn btn-primary',
					uploadExtraData:function(){
						var data={
								title:$("#title").val(),
								price:$("#price").val(),
								num:$("#num").val(),
								sell_point:$("#sell_point").val(),
								imglx:$("#imglx").val()
						};
						return data;
					},
					maxFileCount:3,
					enctype:'multipart/form-data',
					validateInitialCount:true,
					previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
					msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
				});
				
				//导入文件上传完成后的事件
				$("#img_file").on('fileuploaded',function(event,data,previewId,index){
					$("#image").val(data.response.fileName)
				})
			}
			return oFile;
		},	
		onSubmit:function(){
			alert();
		}
	}
	menu2={
	fileInput:function(){
		var oFile=new Object();
		//初始化fileinput控件
		oFile.Init=function(ctrlName,uploadUrl){
			var control=$("#"+ctrlName);
			//初始化上传控件样式
			control.fileinput({
				language:'zh',
				uploadUrl:uploadUrl,
				allowedFileExtensions:['jpg','gif','png'],//接收的文件后缀
				uploadAsync:true,//默认异步上传
				showUpload:true,//是否显示上传按钮
				showRemove:true,//是否显示移除按钮
				showPreview:true,//是否显示预览
				showCaption:true,//是否显示标题
				browseClass:'btn btn-primary',//按钮样式
				dropZoneEnabled:true,//是否显示拖拽区域
				//minImageWidth:100,//图片的最小宽度
				//minImageHeight:100,//图片的最小高度
				//maxImageWidth:300,//图片的最大宽度
				//maxImageHeight:300,//图片的最大高度
				maxFileSize:0,//单位为kb，如果为0表示不限制文本大小
				uploadExtraData:function(){ //额外参数
					var data={
							imglx:$("#lbtlx").val(),
					};
					return data;
				},
				maxFileCount:3,//表示允许同时长传的最大文件个数
				enctype:'multipart/form-data',
				validateInitialCount:true,
				previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
			}).on("fileuploaded",function(event,data,previewId,index){
				//导入文件上传完成后的事件
				
			});
			
		}
		return oFile;
	},	
	onSubmit:function(){
		alert();
	}
	}
