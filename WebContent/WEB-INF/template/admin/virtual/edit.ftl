<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>虚拟账号- Powered By SHOP++</title>
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
					amount: {
						required: true,
						positive: true,
						decimal: {
							integer: 12,
							fraction: ${setting.priceScale}
						}
					}
				}
			});		 
        });
function checkboxOnclick(){
	if($("#checkSub").attr("checked")=="checked"){
		$("#issumbit").attr("disabled",false); 
	}else{
		$("#issumbit").attr("disabled",true); 
	}
	
}
	</SCRIPT>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/virAccount/list.jhtml">虚拟账号</a> &raquo;虚拟账号回赠
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${(vitual.id)!}" />
		<table class="input">
						<tr>
							<th>
								${message("shop.member.deposit.balance")}: 
							</th>
							<td>
								${currency(vitual.member.balance, true, true)}
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
								回赠金额: 
							</th>
							<td>
								<input type="text" id="amount" name="amount" class="text" maxlength="16" onpaste="return false;" />
							</td>
						</tr>
						
						
						<tr>
							<th>
								确定回赠: 
							</th>
							<td>
								<input type="checkbox" id="checkSub" onclick="checkboxOnclick();" />
							</td>
						</th>
						<tr>
						<th>
							&nbsp;
						</th>
						<td>
							<input type="submit" id="issumbit" class="button" disabled value="${message("admin.common.submit")}" />
							<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
						</td>
						</tr>
					</table>	
	</form>

</body>
</html>