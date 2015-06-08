<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新增物业信息</title>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
[#include "/admin/community/ztreeCommunityAll.ftl"/]
<script type="text/javascript">
var obj;
function showCommunity(val){
	obj = $(val);
	 
	var cOffset = obj.offset();
	$("#treeDiv").css({left:cOffset.left + "px", top: cOffset.top + obj.outerHeight()+"px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}


function hideCommunity() {
	$("#treeDiv").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length>0)) {
		hideCommunity();
	}
}
function addC(){
	addAttachLine();
}


function beforeClick(treeId,treeNode){  
             if(!treeNode.isParent){
             if(treeNode.isClick==true){
                	obj.val(treeNode.name);
                	$("#communityIds"+obj.attr("act")).val(treeNode.treeId);
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
	var $inputForm = $("#inputForm");
	var $logo = $("#logo");
	var $browserButton = $("#browserButton");

	[@flash_message /]

	$browserButton.browser();
	
	// 表单验证
	$inputForm.validate({
		rules: {
			propertyNo: "required",
			propertyName: "required",
			phone: "required",
			headPerson: "required",
			address: "required"
			
		}
	});
	
});


 
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 添加
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
	
	<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="管理的社区" />
			</li>
			 
		</ul>
	
	
	
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>物业公司编号:
				</th>
				<td>
					<input type="text" name="propertyNo" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>物业公司名:
				</th>
				<td>
					<input type="text" name="propertyName" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>物业电话:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="200" />
				</td>
			</tr> 
 
			<tr>
				<th>
					<span class="requiredField">*</span>物业地址:
				</th>
				<td>
					<input type="text" name="address" class="text" maxlength="200" />
				</td>
			</tr> 
			<tr>
				<th>
					<span class="requiredField">*</span>负责人:
				</th>
				<td>
					<input type="text" name="headPerson" class="text" maxlength="200" />
				</td>
			</tr> 
			<tr>
				<th>
					 资质等级:
				</th>
				<td>
					 
					
					<select name="level" >
							<option value="1">
								 等级一
							</option>
				 			<option value="2">
								 等级二
							</option>
							<option value="3">
								 等级三
							</option>
							<option value="4">
								 等级四
							</option>
							<option value="5">
								 等级务
							</option>
					</select>
				</td>
			</tr> 
			<tr>
				<th>
					 logo图:
				</th>
				<td><span class="fieldSet">
					<input type="text" name="logo" class="text" maxlength="200" />
 
					<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}"  />
					</span>
				</td>
			</tr> 
			<tr>
				<th>
					 物业简介:
				</th>
				<td>
					
					<textarea id="editor" name="introduction" class="editor"></textarea>
				</td>
			</tr> 
			
			<tr>
				<th>
					 物业荣誉:
				</th>
				<td>
				    <textarea id="honor" name="honor" class="editor"></textarea>
	 
				</td>
			</tr> 
		</table>
		<table   class="input tabContent">
		    <tr>
		    	<th>
		    		所管理的社区
		    	</th>
		    	<td>
		    		<table border="0" id="attachTable" >
						<tbody>
							<tr>
								<td align="center"><input id="attachment" name="attachment" type="button" value="添加" class="button"onclick="javascript:addC()" /></td>
								<td align="center">管理的社区</td>
								<td align="center">负责人</td>
							</tr>
						</tbody>


					</table>
		    	</td>
		    </tr>
		</table>
		<table class="input">
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
	
	
	
	<script type="text/javascript">
	$.format = function () {
			
			 var s = arguments[0];
			
			 var reg = new RegExp("\\{" + "0" + "\\}", "gm");
			 s = s.replace(reg, arguments[1]);	 
			 return s;
	}
		
	var attachIndex = 1;
	function addAttachLine() {
			var attachTpl =  $.format($("#attachTr").val(),attachIndex++);
			$('#attachTable').append(attachTpl);
		}
	</script>
	<!-- 用于添加附件的JS代码 -->
	<textarea id="attachTr" style="display:none">
	
	<tr id="attachTr{0}">
		<td align="center"><input type="button" value="删除" onclick="$('#attachTr{0}').remove();" class="button"/></td>
		<td align="center"><input type="text" id="communityNames{0}" act="{0}" name="communityNames" onclick ="showCommunity(this)"/></td>
		<td align="center">
				<input type="text" id="headPerson{0}" name="_headPerson"/>
				<input type="hidden" id="communityIds{0}" name="communityIds"/>
 
		</td>
	</tr>
	</textarea>
	<div id="treeDiv" style="display:none;position:absolute;background-color:#F8FBFF">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>