<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>虚拟账号充值列表[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/member.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>

</head>
<body>
	[#assign current = "virtualList" /]
	[#include "/shop/include/header.ftl" /]
	<div class="container member">
		[#include "/shop/member/include/navigation.ftl" /]
		<div class="span18 last">
			<div class="list">
				<div class="title">充值列表</div>
				<div class="bar">
					<a href="list.jhtml" class="button">返回虚拟账号列表</a>
				</div>
				<table id="listTable" class="list">
					<tr>
						<th>
							类型
						</th>
						<th>
							充值日期
						</th>
						
						<th>
							回赠金额变动
						</th>
						<th>
							预存金额变动
						</th>
											
						<th>
							操作人员
						</th>
						
					</tr>
					[#list page.content as log]
						<tr[#if !receiver_has_next] class="last"[/#if]>
							<td>
								${message("Vir.log.type." + log.type)}
								
							</td>	
							<td>								
								${(log.createDate)!}
							</td>					
							<td>
								
								[#if log.type??&&log.type=='recharge']					
								<font >收入${currency(log.rebateAmount, true, true)}</font>
								[/#if]
								[#if log.type??&&log.type=='consume']					
								<font >支出${currency(log.rebateAmount, true, true)}</font>
								[/#if]
								[#if log.type??&&log.type=='rebate']					
								<font >收入${currency(log.rebateAmount, true, true)}</font>
								[/#if]
								[#if log.type??&&log.type=='refund']					
								<font >收入${currency(log.rebateAmount, true, true)}</font>
								[/#if]
							</td>						
							<td>								
								[#if log.type??&&log.type=='recharge']					
								<font >收入${currency(log.rechargeAmount, true, true)}</font>
								[/#if]
								[#if log.type??&&log.type=='consume']					
								<font >支出${currency(log.rechargeAmount, true, true)}</font>
								[/#if]
								[#if log.type??&&log.type=='rebate']					
								<font >收入${currency(log.rechargeAmount, true, true)}</font>
								[/#if]
								[#if log.type??&&log.type=='refund']					
								<font >收入${currency(log.rechargeAmount, true, true)}</font>
								[/#if]
							</td>
							<td>
								${(log.operator)!}
							</td>
						</tr>
					[/#list]
				</table>
				[#if !page.content?has_content]
					<p>${message("shop.member.noResult")}</p>
				[/#if]
			</div>
			[@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "?pageNumber={pageNumber}&virtualid=${(virtualid)!}"]
				[#include "/shop/include/pagination.ftl"]
			[/@pagination]
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>