<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.i18n.add")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	jQuery.validator.addMethod("checkCodeExists", function(value, element) {    //用jquery ajax的方法验证商户名称是不是已存在  
            	var flag = 1;  
			    $.ajax({  
			        type:"get",  
			        url:'checkCodeExists.jhtml',  
			        dataType: "json",
			        async:false,
			        data:{'code':value},  
			        success: function(data){  
				      if(data ==false){  
			                 flag = 0;  
			          }  
			        }  
			    });  
			    
			    if(flag == 1){  
			        return false;  
			    }else{  
			        return true;  
			    }  
			    
		
			}, "该编码已存在！");


	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		code:{required:true,checkCodeExists:true}
		}
	});
	
});

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; <a href="${base}/admin/i18n/list.jhtml">${message("admin.i18n.list")}</a> &raquo;${message("admin.i18n.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.i18n.code")}:
				</th>
				<td>
					<input type="text" name="code" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.i18n.local")}:
				</th>
				<td>
					<select  name="local" >
						[#if i18ncodes??]
							[#list i18ncodes as type]
								<option value="${type.codeDetail}">
									 ${type.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.i18n.memo")}:
				</th>
				<td>
					<input type="text" name="memo" class="text" maxlength="20" />
				</td>
			</tr>
			
			[#list i18ncodes as type]
				<tr>
					<th>
						${(type.name)!}:
					</th>
					<td>
						<input type="text" name="${(type.codeDetail)!}" class="text" maxlength="20" />
					</td>
				</tr>
			[/#list]
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