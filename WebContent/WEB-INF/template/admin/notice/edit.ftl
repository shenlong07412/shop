<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>修改公告信息</title>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>

[#include "/admin/community/ztreeCommunityAll.ftl"/]

<script type="text/javascript">
function clean(){
	$("#areaName").val("");
    $("#areaId").val("");
}
function clean2(){
	$("#areaName2").val("");
    $("#areaId2").val("");
}
function beforeClick(treeId,treeNode){  
             if(!treeNode.isParent){
             if(treeNode.isClick==true){
             	if($("#areaName").val().indexOf(treeNode.name)==-1){
                	if($("#areaName").val()==""){
                		$("#areaName").val(treeNode.name);
            			$("#areaId").val(treeNode.treeId);
                	}else{
                		$("#areaName").val($("#areaName").val()+","+treeNode.name);
            			$("#areaId").val($("#areaId").val()+","+treeNode.treeId);
                	}
            	}else{
            		alert("该社区已经被选择");
            	}
            	$("#treeDemo_1_switch").click();
             }else{
             	alert("该节点不是社区");
             }
               // alert("请选择父节点");
                return false;  
            }else{  
                return true;  
            }  
}      

function beforeClick2(treeId,treeNode){  
             if(!treeNode.isParent){
             if(treeNode.isClick==true){
             	 
                $("#areaName2").val(treeNode.name);
            	$("#areaId2").val(treeNode.treeId);
                	 
            	$("#treeDemo2_1_switch").click();
             }else{
             	alert("该节点不是社区");
             }
               // alert("请选择父节点");
                return false;  
            }else{  
                return true;  
            }  
}      


$().ready(function() {

	var setting2 = {    
            data: {    
                simpleData: {    
                    enable: true  
                }    
            }    
            ,async: {    
                enable: true,    
                url:"${base}/admin/area/listAjax.jhtml?hasCommunity=yes",    
                autoParam:["treeId", "name"],    
                otherParam:{"otherParam":"zTreeAsyncTest"},    
                type:"get",//默认post  
                dataFilter: filter  //异步返回后经过Filter  
            }  
            ,callback:{  
                asyncSuccess: zTreeOnAsyncSuccess,//异步加载成功的fun    
                asyncError: zTreeOnAsyncError,   //加载错误的fun    
                beforeClick:beforeClick2 //捕获单击节点之前的事件回调函数  
            }  
        };   
    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes);
    
	var $inputForm = $("#inputForm");
 
	var $browserButton = $("#browserButton");

	[@flash_message /]
    var settings = {
				type: "file"
	 
			};
	$browserButton.browser(settings);
 
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			user: "required",
			releaseTime: "required" 
			
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 修改
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
	<input type="hidden" name="id" value="${notice.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>公告标题:
				</th>
				<td>
					<input type="text" name="title" class="text" value="${notice.title!}" maxlength="200" />
				</td>
			</tr>
			 
			<tr>
				<th>
					 通知类型：
				</th>
				<td>
					<select name="type" >
					[#if typeList??]
						[#list typeList as type]
							<option value="${type.codeDetail}"[#if notice.type==type.codeDetail]  selected="selected" [/#if]>
								 ${type.name}
							</option>
						[/#list]
					[/#if]
					</select>
				</td>
			</tr> 
			<tr>
				<th>
					通知优先级:
				</th>
				<td>
					<select name="priority" >
							<option value="3" [#if notice.priority==3]  selected="selected" [/#if]>
								高
							</option>
							<option value="2" [#if notice.priority==2]  selected="selected" [/#if]>
								中
							</option>
							<option value="1" [#if notice.priority==1]  selected="selected" [/#if]>
								低
							</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					 公告内容
				</th>
				<td>
				    <textarea id="content" name="content" class="editor">${notice.content?html}</textarea>
	 
				</td>
			</tr> 
			
			<tr>
				<th>
					<span class="requiredField">*</span>发布者:
				</th>
				<td>
					<input type="text" name="user" class="text" maxlength="200" value="${notice.user!}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>发布时间:
				</th>
				<td>
					<input type="text" name="releaseTime" class="text" maxlength="200" value="${notice.releaseTime?string('yyyy-MM-dd HH:mm:ss')}"  onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss' });"/>
				</td>
			</tr>
			
			<tr>
				<th>
					 活动地区:
				</th>
				<td>
					<input type="text" name="communityName"value="${(notice.community.name)!}" class="text" maxlength="200" id="areaName2"  readonly/>
					<input type="hidden" name="community.id" value="${(notice.community.id)!}" class="text" maxlength="200" id="areaId2"/>
					<input type="button" value="清空" class="button" onclick="javascript:clean2();"/>
					<ul id="treeDemo2" class="ztree"></ul>
					
				</td>
			</tr>
			<tr>
				<th>
					 是否推送:
				</th>
				<td>
					  <input type="checkbox" id="isSend" name="isSend" value="1" [#if notice.isSend ==1] checked="checked"[/#if]   /> 
					 
				</td>
			</tr>
			
			<tr>
				<th>
					 选择推送的地区:
				</th>
				<td>
					<input type="text" name="communityNames" value="${communityNames}" class="text" maxlength="200" id="areaName"  readonly/>
					<input type="hidden" name="communitys" value="${notice.communitys!}" class="text" maxlength="200" id="areaId"/>
					<input type="button" value="清空" class="button" onclick="javascript:clean();"/>
					<ul id="treeDemo" class="ztree"></ul>
					
				</td>
			</tr>
			
			<tr>
				<th>
					 是否定时:
				</th>
				<td>
					  <input type="checkbox" id="isFixedTime" name="isFixedTime" value="1" [#if notice.isFixedTime ==1] checked="checked"[/#if]  /> 
					 
				</td>
			</tr>
			<tr>
				<th>
					   发送时间:
				</th>
				<td>
					  <input type="text" name="sendTime" class="text" maxlength="200" value="${(notice.sendTime?string('yyyy-MM-dd HH:mm:ss'))!}"  class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss' });"  />
					 
				</td>
			</tr>
			<tr>
			     <th>
					   上传附件:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="attachmentUrl" class="text" maxlength="200" value="${notice.attachmentUrl!}" />
 
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}"  />
					        (支持上传的格式：zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx)
					</span>
				</td>
			</tr>
			
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>