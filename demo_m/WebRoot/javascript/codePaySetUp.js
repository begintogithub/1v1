$(function(){
    //生成用户数据
   var url = path+"/admin/getCodePaySetUpList.htm" ;
    $('#utable').bootstrapTable({
        url: url,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function(params){
        	return {
        		pageSize: params.limit,
        		page:params.pageNumber
        	}
        }, 
        //【其它设置】
        locale:'zh-CN',//中文支持
        pagination: true,//是否开启分页（*）
        pageNumber:1,//初始化加载第一页，默认第一页
        pageSize: 5,//每页的记录行数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        //【设置列】
        columns: [
              	{
              		title:'码支付id',
              		align:'center',
              		field:'code_id'
              	},
              	{
              		title:'通信密钥',
              		align:'center',
              		field:'code_key',
              	},
              	{
              		title:'通知地址',
              		align:'center',
              		field:'notify_url',
              	},
              	{
              		title:'token',
              		align:'center',
              		field:'token',
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			var res ='';
              			var valueStr = JSON.stringify(row);
              			res = res+"<a href='javascript:on_click_open_update_model("+valueStr+");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
              			res = res +'<a href="javascript:on_click_operation('+row.t_id+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
              			return res;
              		}
              	}
        ]
    });
    
    
});
 
/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj){
	if(null != obj){
		$('#t_id').val(obj.t_id);
		$('#code_id').val(obj.code_id);
		$('#code_key').val(obj.code_key);
		$('#notify_url').val(obj.notify_url);
		$('#token').val(obj.token);
		
	}else if($('#utable').bootstrapTable('getOptions').totalRows >=1){
		$('#my_comfirm').empty();
		$('#my_comfirm').append('请删除数据后,在添加记录.');
		$("#myComfirm").modal('show');
		return ;
	}
	$("#myModal").modal('show');
}

function on_click_submit(){
	var isOk = true;
	if(null == $("#code_id").val() || '' == $("#code_id").val()){
		$("#code_id").css({ "border-color": "red" });
		$('#codeId_validate').empty();
		$('#codeId_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#code_key").val() || '' == $("#code_key").val()){
		$("#code_key").css({ "border-color": "red" });
		$('#code_key_validate').empty();
		$('#code_key_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#token").val() || '' == $("#token").val()){
		$("#token").css({ "border-color": "red" });
		$('#token_validate').empty();
		$('#token_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(isOk){
		$.ajax({
			url:path + '/admin/addOrUpCodePaySetUp.htm',
			data:{
				t_id:$('#t_id').val(),
				code_id:$('#code_id').val(),
				code_key:$('#code_key').val(),
				notify_url:$('#notify_url').val(),
				token:$('#token').val()
			},
			dataType : 'json',
			success:function(data){
				if (data.m_istatus == 1) {
					$("#myModal").modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$("#myComfirm").modal('show');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		})
	}
}
/**
 * 点击操作
 */
function on_click_operation(id){
	$("#del_id").val(id);
	$("#myDel").modal('show');
}

function on_click_del(){
	if($('#del_id').val() != null &&  $('#del_id').val() != ""){
		$.ajax({
			url:path + '/admin/delCodePaySetUp.htm',
			data:{
				t_id:$('#del_id').val()
			},
		   dataType : 'json',
		   success:function(data){
			   if (data.m_istatus == 1) {
					$("#myDel").modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$("#myComfirm").modal('show');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
		   }
		})
	}
}






 



