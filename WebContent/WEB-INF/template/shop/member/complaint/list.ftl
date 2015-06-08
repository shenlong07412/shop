<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.member.couponCode.list")}[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/member.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	[@flash_message /]

});
</script>
</head>
<body>
	[#assign current = "complaintList" /]
	[#include "/shop/include/header.ftl" /]
	<div class="container member">
		[#include "/shop/member/include/navigation.ftl" /]
		<div class="span18 last">
			<div class="list">
				<div class="title">我的报修&投诉</div>
				<table class="list">
					<tr>
						<th>
							报修&投诉
						</th>
						<th>
							发布时间
						</th>
						<th>
							状态
						</th>
						<th>
							支持人数
						</th>
						<th>
							操作
						</th>
					</tr>
					[#list page.content as complaint]
						<tr[#if !couponCode_has_next] class="last"[/#if]>
							<td>
								[#if (complaint.type)??&&(complaint.type)==COMPLAINT]
									<strong>[投诉]</strong>
		     					[#else]
									<strong>[报修]</strong>
		     					[/#if]
								[#if complaint.content?length lt 13]${(complaint.content)!}
		     					[#else]${(complaint.content)[0..12]}...
		     					[/#if]
							</td>
							<td>
								${(complaint.createDate)!}
							</td>
							<td>
								[#if (complaint.status)??&&(complaint.status)==0]
									<font color="red">未处理 <font>
		     					[/#if]
		     					[#if (complaint.status)??&&(complaint.status)==1]
									<font color="black">处理中<font>
		     					[/#if]
		     					[#if (complaint.status)??&&(complaint.status)==2]
									<font color="green">已结束<font>
		     					[/#if]
							</td>
							<td>
								[#if (complaint.type)??&&(complaint.type)==COMPLAINT]
								${(complaint.supportNumber)!}
								[#else]
									/
		     					[/#if]
							</td>
							<td>
								<a href="edit.jhtml?id=${complaint.id}">[${message("shop.member.handle.edit")}]</a>
								<a href="javascript:;" class="delete">[${message("shop.member.handle.delete")}]</a>
							</td>
						</tr>
					[/#list]
				</table>
				[#if !page.content?has_content]
					<p>${message("shop.member.noResult")}</p>
				[/#if]
			</div>
			[@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "?pageNumber={pageNumber}"]
				[#include "/shop/include/pagination.ftl"]
			[/@pagination]
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>