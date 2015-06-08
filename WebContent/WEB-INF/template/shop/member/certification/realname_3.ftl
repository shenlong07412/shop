<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.member.order.list")}[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/member.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/index_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	[@flash_message /]

});
</script>
</head>
<body>
	[#assign current = "certificationRealname" /]
	[#include "/shop/include/header.ftl" /]
	<div class="container member">
		[#include "/shop/member/include/navigation.ftl" /]
		<div class="span18 last">
						<div class="V35_tips clearfix">
                            <ul class=width1>
                                <li class=first>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        1.填写个人信息
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        2.审核资料
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        3.认证成功
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                      </div>
                      <h3 align="center">您已经通过智慧社区的实名认证！</h3>
                      <table width="650" border="0" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                            	<td width="100">
                                &nbsp;
                                </td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                           		<td width="100">
                                &nbsp;
                                </td>
                                <td>
                                    <span class="requiredField">
                                        *
                                    </span>
                                    真实姓名：
                                </td>
                                <td>
                                    <input disabled="disabled" id="realname" name="realname" type="text" value="${(memberCertification.realname)!}" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
								<td width="100">
                                &nbsp;
                                </td>                            
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                                &nbsp;
                                </td>
                                 <td>
                                    <span class="requiredField">
                                        *
                                    </span>
                                    身份证号码：
                                </td>
                                <td>
                                    <input disabled="disabled" id="idCard" name="idCard" type="text" value="${(memberCertification.idCard)!}" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                                &nbsp;
                                </td>
                                <td style="height:300px;">
                                    <span class="requiredField">
                                        *
                                    </span>
                                    身份证照片：
                                </td>
                                <td>
                                    <img id="userPhoto" src="${base}${(memberCertification.idCardImg)!}" width="319" height="240" />
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                                &nbsp;
                                </td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                                &nbsp;
                                </td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                        </table>
		
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>