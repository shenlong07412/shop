<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>业主信息 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
[#include "/admin/community/ztreeCommunityAll.ftl"/]
<SCRIPT type="text/javascript">	
        $(document).ready(function(){
            var $inputForm = $("#inputForm");
            var $browserButton = $("#browserButton");
            
			[@flash_message /]
			$browserButton.browser();	
			            
        	$inputForm.validate({
				rules: {
					no: {
						required:true,
						pattern: /^[0-9a-zA-Z_-]+$/,
						remote: {
							url: "check_no.jhtml?previousNo=${(owner.no)!}",
							cache: false
						}
					},	
					name:"required",
					sex: "required",
					identity:{required:true,isIdCardNo:true},	
					areaName:"required",	
					water_rate: {
						min: 0,
						decimal: {
							integer: 12,
							fraction: ${setting.priceScale}
						}
					},
					power_rate: {
						min: 0,
						decimal: {
							integer: 12,
							fraction: ${setting.priceScale}
						}
					},
					gas_rate: {
						min: 0,
						decimal: {
							integer: 12,
							fraction: ${setting.priceScale}
						}
					},
					other_rate: {
						min: 0,
						decimal: {
							integer: 12,
							fraction: ${setting.priceScale}
						}
					},
					total_rate: {
						min: 0,
						decimal: {
							integer: 12,
							fraction: ${setting.priceScale}
						}
					},						
					phone:{required:true,mobile:true},
					messages: {
						no: {
							pattern: "${message("admin.validate.illegal")}",
							remote: "${message("admin.validate.exist")}"
						}								
					}								
				}
			});           
        });
function showImage(){
	var url=$("#photo").attr("value");
	if(url==null || url.value=="")
	{
		alert("请上传图片！");
	}
	else
	{
		$.dialog({
    				id: 'a15',
    				top: '70%',
    				title: '预览图片',
    				lock: true,
    				content: '<img src='+url+' width="450" height="300" />',
    				padding: 0
				});
	}
};         
</SCRIPT>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;
		<a href="${base}/admin/owner/list.jhtml">业主列表</a> &raquo; 编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">	
	<input type="hidden" value="${(owner.id)!}" name="id"/>
	<input type="hidden" value="${(owner.dataflg)!}" name="dataflg"/>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="业主信息" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<th>
				<span class="requiredField">*</span>社区:
			</th>
				<td>
					<input type="text" value="${(owner.community.name)!}" name="areaName" class="text" maxlength="200" id="areaName" readonly/>
					<input type="hidden" value="${(owner.community.id)!}" name="areaId" class="text" maxlength="200" id="areaId"/>
					<ul id="treeDemo" class="ztree" style="margin:-28px 220px 0px"></ul>					
				</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>业主编号:
			</th>
			<td>
				<input type="text" name="no" class="text" maxlength="9" value="${(owner.no)!}"/>			
			</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>姓名:
			</th>
			<td>
				<input type="text" name="name" class="text" maxlength="9" value="${(owner.name)!}" />	
			</td>
		</tr>		
		<tr>
			<th>
				<span class="requiredField">*</span>性别:
			</th>
			<td>
				<input type="text" name="sex" class="text" maxlength="9" value="${(owner.sex)!}" />	
			</td>
		</tr>
		<tr>
			<th>
				照片:
			</th>
			<td>
				<span class="fieldSet">
					<input type="text" name="photo" id="photo" class="text" maxlength="200" value="${(owner.photo)!}"/>
					<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					<input type="button" class="button" value="预览" onclick="showImage()"/>
				</span>		
			</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>身份证号:
			</th>
			<td>
			    <input type="text" name="identity" class="text" maxlength="100" value="${(owner.identity)!}" />
			</td>
		</tr>
		<tr>
			<th>
				学历:
			</th>
			<td>
				<input type="text" name="education" class="text" maxlength="100"  value="${(owner.education)!}"/>
			</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>手机号码:
			</th>
			<td>
				<input type="text" name="phone" class="text" maxlength="100" value="${(owner.phone)!}"/>
			</td>
		</tr>								
		<tr>
			<th>
				水费(单位：元):
			</th>
			<td>
			<input type="text" name="water_rate" class="text" maxlength="100" value="${(owner.water_rate)!}" />
			</td>
		</tr>
		<tr>
			<th>
				电费(单位：元):
			</th>
			<td>
			<input type="text" name="power_rate" class="text" maxlength="100" value="${(owner.power_rate)!}" />
			</td>
		</tr>
			<th>
				煤费(单位：元):
			</th>
			<td>
			<input type="text" name="gas_rate" class="text" maxlength="100" value="${(owner.gas_rate)!}" />
			</td>
		<tr>
			<th>
				其他维护费(单位：元):
			</th>
			<td>
			<input type="text" name="other_rate" class="text" maxlength="100" value="${(owner.other_rate)!}" />
			</td>
		</tr>	
		<tr>
			<th>
				总额(单位：元):
			</th>
			<td>
			<input type="text" name="total_rate" class="text" maxlength="100" value="${(owner.total_rate)!}" />
			</td>
		</tr>
		<tr>
			<th>
				认证状态:
			</th>
			<td>
			<input type="text" name="authenticating_state" class="text" title="认证状态" value="${(owner.authenticating_state)!}" />
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
</body>
</html>