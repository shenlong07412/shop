<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>社区 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

<SCRIPT type="text/javascript">				
        $(document).ready(function(){
            var $inputForm = $("#inputForm");
        	$inputForm.validate({
				rules: {
					person: "required",
					content:"required"
				}
			});            
        });
	</SCRIPT>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/complaint/list.jhtml">投诉报修</a> &raquo;详细信息
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" onclick="javascript:secBoard(tabContent,0);"/>
			</li>
			<li>
				<input type="button" value="流程办理"  onclick="javascript:secBoard(tabContent,1);"/>
			</li>
			<li>
				<input type="button" value="处理流程"  onclick="javascript:secBoard(tabContent,2);"/>
			</li>
		</ul>
		<table class="input tabContent">
			<input type="hidden" name="id" value="${(complaint.id)!}">
			<tr>
				<th width="10%">申请类型:</th>
				<td width="23%">
						[#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							投诉
     					[#else]
							报修
     					[/#if]
				</td>
				<th width="10%">
				        [#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							投诉人:
     					[#else]
							申请人:
     					[/#if]
				</th>
				<td  width="23%">${(complaint.person)!}</td>
				<th width="10%">
					   [#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							投诉时间:
     					[#else]
							申请时间:
     					[/#if]</th>
				<td  width="23%">${(complaint.createDate)!}</td>
			</tr>
			<tr>
				<th width="10%">
						[#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							投诉类型:
     					[#else]
							报修类型:
     					[/#if]</th>
				<td width="23%">
					    [#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							--
     					[#else]
							${(systype.name)!}
     					[/#if]
				</td>
				<th width="10%">联系电话:</th>
				<td  width="23%">${(complaint.phone)!}</td>
				<th width="10%">[#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							支持数:
							[#else]
							所在社区:     					
     					[/#if]</td></th>
				<td  width="23%">
					  [#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							${(complaint.supportNumber)!}
							[#else]
							${(complaint.community.name)!}
     					[/#if]</td>
			</tr>
			<tr>
				<th>
					   [#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							是否公开:
     					[#else]
							联系地址:
     					[/#if]
				</th>
				
					    [#if (systype.systemCode.code)??&&(systype.systemCode.code)==COMPLAINT]
							<td>
							[#if complaint.isPublic??&&complaint.isPublic==0]否
	     					[#else]是
	     					[/#if]
	     					</td>
	     					<th>所在社区:</th>
	     					<td colspan="3">${(complaint.community.name)!}</td>
     					[#else]
     						<td colspan="5">
							${(complaint.address)!}
							</td>
     					[/#if]
				
			</tr>
			<tr>
				<th>
					报修内容:
				</th>
				<td colspan="5">
					<textarea  name="introduction" disabled style="width:90%;height:100px">${(complaint.content)!}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					现场记录:
				</th>
				<td colspan="5">
					[#list imgs as img]
					<img width="15%" src="${(img.imgUrl)!}">
					[/#list]	
				</td>
			</tr>
			<tr>
				<th></th>								
				<td colspan="3">					
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
				</tr>
		</table>
	
	<table class="input tabContent">
	<tr>
			[#if (complaint.status)??&&(complaint.status)!=2]
			<tr>
				<th width="15%">当前状态:</th>
				<td width="35%">[#if (complaint.status)??&&(complaint.status)==0]
							<font color="red">未处理 <font>
     					[/#if]
     					[#if (complaint.status)??&&(complaint.status)==1]
							<font color="black">处理中<font>
     					[/#if]
     					[#if (complaint.status)??&&(complaint.status)==2]
							<font color="green">已结束<font>
     					[/#if]
				</td>				
				<th width="15%"><span class="requiredField">*</span>当前办理人:</th>
				<td width="35%">
					<input type="text"  value="${(admin.name)!}" name="person" class="text" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>处理意见:
				</th>			
				<td colspan="3">
					<textarea name="content" style="width:90%;height:100px"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					处理选择:
				</th>
				<td  colspan="3">
					<select name="status">				
						<option value='1'>正在处理</option>
						<option value='2'>处理结束</option>
					</select>
				</td>
			</tr>
			<tr>
				<th></th>								
				<td colspan="3">
					<input type="submit" class="button" value="提&nbsp;&nbsp;交" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
			[#else]
			<tr>
			  <td>
			  <font color="red">该流程已经结束！</font></td>
			<tr>
			[/#if]
	</table>
	
	<table id="listTable" class="list tabContent">
			<tr>
				<th class="50%">
					<a class="sort"><center>处理情况</center></a>
				</th>
				<th width="15%">
					<a class="sort"><center>经手人</center></a>
				</th>
				<th width="10%">
					<a class="sort"><center>办理状态</center></a>
				</th>
				<th width="25%">
					<a class="sort"><center>办理时间</center></a>
				</th>
				
			</tr>
			<tr>
					<td>
						发起流程
					</td>
					
					<td>
						${(complaint.person)!}
					</td>
					<td>
						<font color="red">未处理 <font>
					</td>
					<td>
						${(complaint.createDate)!}
					</td>
					
				</tr>
			[#if logs??&&logs?size>0]
			[#list logs as log]
				<tr>
					<td>
						${(log.content)!}
					</td>
					
					<td>
						${(log.person)!}
					</td>
					<td>
						[#if (log.satus)??&&(log.satus)==0]
							<font color="red">未处理 <font>
     					[/#if]
     					[#if (log.satus)??&&(log.satus)==1]
							<font>处理中<font>
     					[/#if]
     					[#if (log.satus)??&&(log.satus)==2]
							<font color="green">已结束<font>
     					[/#if]
					</td>
					<td>
						${(log.handing_time)!}
					</td>
					
				</tr>				
			</tr>
			[/#list]
			[/#if]
			
		</table>
	
	</form>
</body>
</html>