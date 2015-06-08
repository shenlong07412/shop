<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>虚拟账号${message("shop.member.deposit.recharge")}[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
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
	var $amount = $("#amount");
	var $paymentPluginId = $("#paymentPlugin :radio");
	var $fee = $("#fee");
	var timeout;
	
	[@flash_message /]
	
	// 充值金额
	$amount.bind("input propertychange change", function(event) {
		
	});
	
	// 检查余额
	setInterval(function() {
		$.ajax({
			url: "${base!}/member/deposit/check_balance.jhtml",
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.balance > ${member.balance}) {
					location.href = "list.jhtml";
				}
			}
		});
	}, 10000);
	
	// 表单验证
	$inputForm.validate({
		rules: {
			amount: {
				required: true,
				positive: true,
				decimal: {
					integer: 12,
					fraction: ${setting.priceScale}
				}
			}
		},
		submitHandler: function(form) {
			form.submit();
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
			<div class="input deposit">
				<div class="title">虚拟账号充值</div>
				<form id="inputForm" action="saveRecharge.jhtml" method="post">
					<input type="hidden" name="id" value="${(vitual.id)!}" />
					<table class="input">
						<tr>
							<th>
								${message("shop.member.deposit.balance")}: 
							</th>
							<td>
								${currency(member.balance, true, true)}
							</td>
						</tr>
						<tr>
							<th>
								虚拟预存金额: 
							</th>
							<td>
								${currency(vitual.rechargeAmounts, true, true)}
							</td>
						</tr>
						<tr>
							<th>
								商家回赠余额: 
							</th>
							<td>
								${currency(vitual.rebateAmounts, true, true)}
							</td>
						</tr>
						<tr>
							<th>
								${message("shop.member.deposit.amount")}: 
							</th>
							<td>
								<input type="text" id="amount" name="amount" class="text" maxlength="16" onpaste="return false;" />
							</td>
						</tr>
						
						<tr class="hidden">
							<th>
								${message("shop.member.deposit.fee")}: 
							</th>
							<td>
								<span id="fee"></span>
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