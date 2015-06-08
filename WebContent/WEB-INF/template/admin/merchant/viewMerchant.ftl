<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${message("admin.biz.merchant.edit")}</title>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
[#include "/admin/product_merchant_category/product_category.ftl"/]
<script>
 $(document).ready(function(){
            var $inputForm = $("#inputForm");
        	$inputForm.validate({
				rules: {
					enterprise.enterpriseName: "required",
					enterprise.businessLicenseNo:"required",
					enterprise.businessLicenseAddress: "required",
					enterprise.businessPeriod:"required",
					enterprise.businessRange: "required",
					legalPerson.realname:"required",
					legalPerson.idCardNo: "required",
					legalPerson.phone:"required",
					legalPerson.address:"required",
					legalPerson.phone:"required",
					bankAccount.bankAccountNamee:"required",
					bankAccount.bankName:"required",
					bankAccount.bankBranchName:"required",
					bankAccount.bankAddress:"required",
					bankAccount.bankAccount:"required"
					
				}
			});            
        });
function showImg(){
	var url=document.getElementById("linkIcon");
	if(url==null || url.value=="")
	{
		alert("该内容还没有上传图片！");
	}
	else
	{
		$.dialog({
    				id: 'a15',
    				top: '70%',
    				title: '预览图片',
    				lock: true,
    				content: '<img src="${imgPath}'+url.value+'" width="450" height="300" />',
    				padding: 0
				});
	}
	
}

function goback()
{
	window.location.href = 'javascript:window.history.go(-1)';
}

function secBoard(name,n) {
	$("."+name).each(function(index, element) {
		if (index == n) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
  }
</script>
</head>
<body>

<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; <a href="${(base)!}/admin/merchant/list.jhtml">${message("admin.biz.merchant.list")}</a> &raquo; ${message("admin.biz.merchant.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="id" value="${merchant.id}" />
		<input type="hidden" name="account.id" value="${merchant.account.id}" />
		<input type="hidden" name="enterprise.id" value="${merchant.enterprise.id}" />
		<input type="hidden" name="legalPerson.id" value="${merchant.legalPerson.id}" />
		<input type="hidden" name="bankAccount.id" value="${merchant.bankAccount.id}" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.biz.accountInfo")}" onclick="javascript:secBoard(tabContent,0);"/>
			</li>
			<li>
				<input type="button" value="${message("admin.biz.merchantInfo")}"  onclick="javascript:secBoard(tabContent,1);"/>
			</li>
			<li>
				<input type="button" value="${message("admin.biz.enterpriseInfo")}"  onclick="javascript:secBoard(tabContent,2);"/>
			</li>
			<li>
				<input type="button" value="${message("admin.biz.leagalInfo")}"  onclick="javascript:secBoard(tabContent,3);"/>
			</li>
			<li>
				<input type="button" value="${message("admin.biz.bankaccountInfo")}"  onclick="javascript:secBoard(tabContent,4);"/>
			</li>
			<li>
				<input type="button" value="商品类别授权"  onclick="javascript:secBoard(tabContent,5);"/>
			</li>
		</ul>
		<!-- 帐户基本信息start -->
		<table class="input tabContent">
			<tr>
				<th>
					${message("admin.biz.account.username")}:
				</th>
				<td>
					${(merchant.account.username)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.account.realname")}:
				</th>
				<td>
					${(merchant.account.realname)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.account.nickname")}:
				</th>
				<td>
					${(merchant.account.nickname)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.account.status")}:
				</th>
				<td>
					[#if accountStatus??]
						[#list accountStatus as s]
							[#if merchant.account.status==s.codeDetail]
								${s.name}
							[/#if]
						[/#list]
					[/#if]
				</td>
			</tr>
		</table>
		<!-- 帐户基本信息end -->

		<!-- 商户基本信息start -->
		<table class="input tabContent">
			<tr>
				<th>
					${message("admin.biz.merchant.name")}:
				</th>
				<td>
					${(merchant.name)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.bestpayAccount")}:
				</th>
				<td>
					${(merchant.bestpayAccount)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.ivrAccount")}:
				</th>
				<td>
					${(merchant.ivrAccount)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.qrcode")}:
				</th>
				<td>
					${(merchant.qrcode)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.homePage")}:
				</th>
				<td>
					${(merchant.homePage)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.introduction")}:
				</th>
				<td>
					${(merchant.introduction)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.channelId")}:
				</th>
				<td>
					${(merchant.channelId)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.templateId")}:
				</th>
				<td>
					${(merchant.templateId)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.profit")}:
				</th>
				<td>
					[#if profit??]
						[#list profit as s]
							[#if merchant.profit==s.codeDetail]
								${s.name}
							[/#if]
						[/#list]
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.rank")}:
				</th>
				<td>
					${(merchant.rank)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.auditSwitch")}:
				</th>
				<td>
					[#if auditSwitch??]
						[#list auditSwitch as s]
							[#if merchant.auditSwitch==s.codeDetail]
								${s.name}
							[/#if]
						[/#list]
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.pushFrequency")}:
				</th>
				<td>
					${(merchant.pushFrequency)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.billingType")}:
				</th>
				<td>
					[#if billingType??]
						[#list billingType as s]
							[#if merchant.billingType==s.codeDetail]
								${s.name}
							[/#if]
						[/#list]
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.billingCycle")}:
				</th>
				<td>
					${(merchant.billingCycle)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.royaltyRate")}:
				</th>
				<td>
					${(merchant.royaltyRate)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.supportedRebate")}:
				</th>
				<td>
					[#if supportedRebate??]
						[#list supportedRebate as s]
							[#if merchant.supportedRebate==s.codeDetail]
								 ${s.name}
							[/#if]
						[/#list]
					[/#if]
				</td>
			</tr>
		</table>
		<!-- 商户基本信息end -->
		<!-- 企业基本信息 start-->
		<table class="input tabContent" >
			<tr>
				<th>
					${message("admin.biz.ent.enterpriseName")}:
				</th>
				<td>
					${(merchant.enterprise.enterpriseName)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.businessLicenseNo")}:
				</th>
				<td>
					${(merchant.enterprise.businessLicenseNo)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.businessLicenseAddress")}:
				</th>
				<td>
					${(merchant.enterprise.businessLicenseAddress)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.businessPeriod")}:
				</th>
				<td>
					${(merchant.enterprise.businessPeriod)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.businessRange")}:
				</th>
				<td>
					${(merchant.enterprise.businessRange)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.fax")}:
				</th>
				<td>
					${(merchant.enterprise.fax)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.phone")}:
				</th>
				<td>
					${(merchant.enterprise.phone)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.email")}:
				</th>
				<td>
					${(merchant.enterprise.email)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.registeredCapital")}:
				</th>
				<td>
					${(merchant.enterprise.registeredCapital)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.organizationCode")}:
				</th>
				<td>
					${(merchant.enterprise.organizationCode)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.industry")}:
				</th>
				<td>
					${(merchant.enterprise.industry)!}
				</td>
			</tr>
		</table>
		<!-- 商户扩展信息 end-->
		<!-- 企业法人信息start -->
		<table class="input tabContent" >
			<tr>
				<th>
					${message("admin.biz.legalPerson.realname")}:
				</th>
				<td>
					${(merchant.legalPerson.realname)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.legalPerson.idCardNo")}:
				</th>
				<td>
					${(merchant.legalPerson.idCardNo)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.legalPerson.phone")}:
				</th>
				<td>
					${(merchant.legalPerson.phone)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.legalPerson.address")}:
				</th>
				<td>
					${(merchant.legalPerson.address)!}
				</td>
			</tr>
		</table>
		<!-- 企业法人信息end -->
		<!-- 银行账号信息start -->
		<table class="input tabContent" >
			<tr>
				<th>
					${message("admin.biz.bankAccount.bankAccountName")}:
				</th>
				<td>
					${(merchant.bankAccount.bankAccountName)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.bankAccount.bankName")}:
				</th>
				<td>
					${(merchant.bankAccount.bankName)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.bankAccount.bankBranchName")}:
				</th>
				<td>
					${(merchant.bankAccount.bankBranchName)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.bankAccount.bankAddress")}:
				</th>
				<td>
					${(merchant.bankAccount.bankAddress)!}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.bankAccount.bankAccount")}:
				</th>
				<td>
					${(merchant.bankAccount.bankAccount)!}
				</td>
			</tr>
		</table>
		<!-- 商品类别授权-->
		<table class="input tabContent">
 
			<tr>
				<th>
					<span class="requiredField">*</span>类别授权:
				</th>
				<td>
					 <ul id="categoryTree" class="ztree"></ul>
				</td>
			</tr>
			 
		</table>
		<!-- 银行账号信息end -->
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="goback()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
