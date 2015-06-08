<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>虚拟账号详细[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/member.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	
	[@flash_message /]
	
	jQuery.validator.addMethod("nameSame", function(value, element) { 
	    var flag = 1;
	    var sn="Mb${member.id}Mc"+$("#merchantId").val();
	    $.ajax({  
	        type:"POST",  
	        url:'fingSameName.jhtml',  
	        dataType :"json",
	        async:false,
	        data:{'sn':sn},  
	        success: function(data){  
	        	var datajson=eval(data);   
	             if(datajson.contains == 'no'){  
	                 flag = 0;  
	             }  
	        }  
	    });  

	    if(flag == 1){  
	        return false;  //
	    }else{  
	        return true;  //不存在
	    }  

	}, "您已开通该商家的虚拟用户");  
	
	// 表单验证
	$inputForm.validate({
		rules: {
			nickname: "required"
		}
	});

});
</script>
</head>
<body>
	[#assign current = "virtualList" /]
	[#include "/shop/include/header.ftl" /]
	<div class="container member">
		[#include "/shop/member/include/navigation.ftl" /]
		<div class="span18 last">
			<div class="input">
				<div class="title">${message("shop.virtual.account.edit")}</div>
				<form id="inputForm" action="update.jhtml" method="post">
					
					<table class="input">
						<tr>
							<th>
								会员: 
							</th>
							<td>
								${(member.username)!}
							</td>
						</tr>
						
						<tr>
							<th>
								昵称: 
							</th>
							<td>
								<input type="hidden" id="id" name="id" class="text" maxlength="20" value="${(virAccount.id)!}"/>
								<input type="text" id="nickname" name="nickname" class="text" maxlength="20" value="${(virAccount.nickname)!}"/>
							</td>
						</tr>
						
						<tr>
							<th>
								开通商家: 
							</th>
							<td>
								${(virAccount.merchant.name)!}
							</td>
						</tr>
						
						<tr>
							<th>
								${message("shop.virtual.rechargeAmounts")}: 
							</th>
							<td>
								${currency(virAccount.rechargeAmounts, true, true)}
							</td>
						</tr>
						<tr>
							<th>
								${message("shop.vitual.rebateAmounts")}: 
							</th>
							<td>
								${currency(virAccount.rebateAmounts, true, true)}
							</td>
						</tr>
						<tr>
							<th>
								${message("shop.common.createDate")}: 
							</th>
							<td>
								${(virAccount.createDate)!}
							</td>
						</tr>
						<tr>
							<th>
								上一次支出时间: 
							</th>
							<td>
								${(virAccount.lastTradeDate)!}
							</td>
						</tr>
						<tr>
							<th>
								上一次支出IP:
							</th>
							<td>
								${(virAccount.lastTradeIp)!}
							</td>
						</tr>
						
						<tr>
							<th>
								上一次充值时间: 
							</th>
							<td>
								${(virAccount.lastRechargeDate)!}
							</td>
						</tr>
						<tr>
							<th>
								上一次充值IP:
							</th>
							<td>
								${(virAccount.lastRechargeIp)!}
							</td>
						</tr>
						
						<tr>
							<th>
								&nbsp;
							</th>
							<td>
								<input type="submit" class="button" value="${message("shop.member.submit")}" />
								<input type="button" class="button" value="${message("shop.member.back")}" onclick="location.href='list.jhtml'" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>