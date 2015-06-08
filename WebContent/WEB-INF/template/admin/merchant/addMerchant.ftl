<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${message("admin.biz.merchant.add")}</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

[#include "/admin/community/ztreeCommunityAll.ftl"/]
[#include "/admin/product_merchant_category/product_category.ftl"/]
<script>
 $(document).ready(function(){
            var $inputForm = $("#inputForm");
            jQuery.validator.addMethod("check_name", function(value, element) {    //用jquery ajax的方法验证商户名称是不是已存在  
            	var flag = 1;  
			    $.ajax({  
			        type:"get",  
			        url:'check_name.jhtml',  
			        dataType: "json",
			        async:false,
			        data:{'name':value},  
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
			    
		
			}, "该商户已存在！");  
			
			// 添加验证方法 (身份证号码验证)    
	        jQuery.validator.addMethod("isIdCardNo", function (value, element) {  
	            return this.optional(element) || checkidcard(value);  
	        }, "请正确输入您的身份证号码");  
            
            jQuery.validator.addMethod("mobile", function(value, element) {
			    var length = value.length;
			    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
			    return this.optional(element) || (length == 11 && mobile.test(value));
			}, "手机号码格式错误");
            jQuery.validator.addMethod("phone", function(value, element) {
			    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
			    return this.optional(element) || (tel.test(value));
			}, "电话号码格式错误(格式必须为：区号  - 号码)");
        	$inputForm.validate({
				rules: {
					name:{check_name:true},
					"enterprise.enterpriseName":{required:true,check_name:true},
					"enterprise.businessLicenseNo":{required:true,integer:12},
					"enterprise.businessLicenseAddress":"required",
					"enterprise.businessPeriod":"required",
					"enterprise.businessRange":"required",
					"legalPerson.realname":"required",
					"legalPerson.idCardNo":{required:true,isIdCardNo:true},
					"legalPerson.phone":{required:true,mobile:true},
					"legalPerson.address":"required",
					"bankAccount.bankAccountName":"required",
					"bankAccount.bankName":"required",
					"bankAccount.bankBranchName":"required",
					"bankAccount.bankAddress":"required",
					"bankAccount.bankAccount":"required",
					"enterprise.email":{email:true},
					"enterprise.phone":{phone:true},
					
					
				}
			});            
        });
        
		function checkidcard(num) {  
            var len = num.length, re;  
            if (len == 15)  
                re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);  
            else if (len == 18)  
                re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);  
            else {  
                //alert("请输入15或18位身份证号,您输入的是 "+len+ "位");     
                return false;  
            }  
            var a = num.match(re);  
            if (a != null) {  
                if (len == 15) {  
                    var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);  
                    var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];  
                } else {  
                    var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);  
                    var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];  
                }  
                if (!B) {  
                    //alert("输入的身份证号 "+ a[0] +" 里出生日期不对！");     
                    return false;  
                }  
            }  
            return true;  
        }   
        
     
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
	window.location.href ='${(base)!}/admin/merchant/list.jhtml';
}
</script>
</head>


<body>




<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; <a href="${(base)!}/admin/merchant/list.jhtml">${message("admin.biz.merchant.list")}</a> &raquo; ${message("admin.biz.merchant.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post"  enctype="multipart/form-data">
		<input type="hidden"  name="auditSwitch" value="0" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.biz.accountInfo")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.biz.merchantInfo")}"  />
			</li>
			<li>
				<input type="button" value="${message("admin.biz.enterpriseInfo")}"  />
			</li>
			<li>
				<input type="button" value="${message("admin.biz.leagalInfo")}" "/>
			</li>
			<li>
				<input type="button" value="${message("admin.biz.bankaccountInfo")}" />
			</li>
			<li>
				<input type="button" value="商品类别授权"  />
			</li>
		</ul>
		<!-- 帐户基本信息start -->
		<table class="input tabContent">
			<tr>
				<th>
					${message("admin.biz.account.username")}:
				</th>
				<td>
					<input type="text"  class="text" name="account.username" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.account.realname")}:
				</th>
				<td>
					<input type="text"  class="text" name="account.realname" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.account.nickname")}:
				</th>
				<td>
					<input type="text"  class="text" name="account.nickname" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.account.status")}:
				</th>
				<td>
					<select  name="account.status">
						[#if accountStatus??]
							[#list accountStatus as status]
								<option value="${status.codeDetail}">
									 ${status.name}
								</option>
							[/#list]
						[/#if]
					</select>
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
					<input type="text"  class="text" name="name" id="merchantName"/>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.communityId")}:
				</th>
				<td>
					<input type="text" value="${(community.area.name)!}" name="areaName" class="text" maxlength="200" id="areaName" readonly/>
					<input type="hidden" value="${(community.area.id)!}" name="areaId" class="text" maxlength="200" id="areaId"/>
					<ul id="treeDemo" class="ztree"  style="margin:-28px 220px 0px"></ul>					
				</td>
			</tr>	
			<tr>
				<th>
					${message("admin.biz.merchant.bestpayAccount")}:
				</th>
				<td>
					<input type="text"  class="text" name="bestpayAccount" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.ivrAccount")}:
				</th>
				<td>
					<input type="text"  class="text" name="ivrAccount" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.qrcode")}:
				</th>
				<td>
					<input type="text"  class="text" name="qrcode" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.homePage")}:
				</th>
				<td>
					<input type="text"  class="text" name="homePage" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.introduction")}:
				</th>
				<td>
					<input type="text"  class="text" name="introduction" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.channelId")}:
				</th>
				<td>
					<select  name="channelId">
						[#if channelId??]
							[#list channelId as p]
								<option value="${p.codeDetail}">
									 ${p.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.templateId")}:
				</th>
				<td>
					<select  name="templateId">
						[#if templateId??]
							[#list templateId as p]
								<option value="${p.codeDetail}">
									 ${p.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.profit")}:
				</th>
				<td>
					<select  name="profit">
						[#if profit??]
							[#list profit as p]
								<option value="${p.codeDetail}">
									 ${p.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.rank")}:
				</th>
				<td>
					<input type="text"  class="text" name="rank" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.auditSwitch")}:
				</th>
				<td>
					<select  name="auditSwitch">
						[#if auditSwitch??]
							[#list auditSwitch as a]
								<option value="${a.codeDetail}">
									 ${a.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.pushFrequency")}:
				</th>
				<td>
					<input type="text"  class="text" name="pushFrequency" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.billingType")}:
				</th>
				<td>
					<select  name="billingType">
						[#if billingType??]
							[#list billingType as b]
								<option value="${b.codeDetail}">
									 ${b.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.billingCycle")}:
				</th>
				<td>
					<input type="text"  class="text" name="billingCycle" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.royaltyRate")}:
				</th>
				<td>
					<input type="text"  class="text" name="royaltyRate" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.merchant.supportedRebate")}:
				</th>
				<td>
					<select  name="supportedRebate">
						[#if supportedRebate??]
							[#list supportedRebate as s]
								<option value="${s.codeDetail}">
									 ${s.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
		</table>
		<!-- 商户基本信息end -->
		<!-- 企业基本信息 start-->
		<table class="input tabContent" >
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.ent.enterpriseName")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.enterpriseName" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.ent.businessLicenseNo")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.businessLicenseNo" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.ent.businessLicenseAddress")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.businessLicenseAddress" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.ent.businessPeriod")}:
				</th>
				<td>
					<input type="textarea"  class="text" name="enterprise.businessPeriod" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.ent.businessRange")}:
				</th>
				<td>
					<input type="textarea"  class="text" name="enterprise.businessRange" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.fax")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.fax" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.phone")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.phone" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.email")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.email" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.registeredCapital")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.registeredCapital" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.organizationCode")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.organizationCode" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.biz.ent.industry")}:
				</th>
				<td>
					<input type="text"  class="text" name="enterprise.industry" />
				</td>
			</tr>
		</table>
		<!-- 商户扩展信息 end-->
		<!-- 企业法人信息start -->
		<table class="input tabContent" >
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.legalPerson.realname")}:
				</th>
				<td>
					<input type="text"  class="text" name="legalPerson.realname" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.legalPerson.idCardNo")}:
				</th>
				<td>
					<input type="text"  class="text" name="legalPerson.idCardNo" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.legalPerson.phone")}:
				</th>
				<td>
					<input type="text"  class="text" name="legalPerson.phone" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.legalPerson.address")}:
				</th>
				<td>
					<input type="text"  class="text" name="legalPerson.address" />
				</td>
			</tr>
		</table>
		<!-- 企业法人信息end -->
		<!-- 银行账号信息start -->
		<table class="input tabContent" >
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.bankAccount.bankAccountName")}:
				</th>
				<td>
					<input type="text"  class="text" name="bankAccount.bankAccountName" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.bankAccount.bankName")}:
				</th>
				<td>
					<input type="text"  class="text" name="bankAccount.bankName" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.bankAccount.bankBranchName")}:
				</th>
				<td>
					<input type="text"  class="text" name="bankAccount.bankBranchName" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.bankAccount.bankAddress")}:
				</th>
				<td>
					<input type="text"  class="text" name="bankAccount.bankAddress" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.biz.bankAccount.bankAccount")}:
				</th>
				<td>
					<input type="text"  class="text" name="bankAccount.bankAccount" />
				</td>
			</tr>
		</table>
		<!-- 商品类别授权 start-->
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
		<!-- 商品类别授权end -->
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
