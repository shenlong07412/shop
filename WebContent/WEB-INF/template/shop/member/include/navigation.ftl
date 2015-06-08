<div class="span6">
	<div class="info">
		<div class="top"></div>
		<div class="content">
			<p>${message("shop.member.navigation.welcome", member.username)}</p>
			<p>
				${message("shop.member.navigation.memberRank")}:
				<span class="red">${member.memberRank.name}</span>
			</p>
		</div>
		<div class="bottom"></div>
	</div>
	<div class="menu">
		<div class="title">
			<a href="${base}/member/index.jhtml">${message("shop.member.navigation.title")}</a>
		</div>
		<div class="content">
			<dl>
				<dt>账号认证</dt>
				<dd>
					<a href="${base}/member/certification/mail.jhtml"[#if current == "certificationMail"] class="current"[/#if]>邮箱认证</a>
				</dd>
				<dd>
					<a href="${base}/member/certification/mobile.jhtml"[#if current == "certificationMobile"] class="current"[/#if]>手机认证</a>
				</dd>
				<dd>
					<a href="${base}/member/certification/realname.jhtml"[#if current == "certificationRealname"] class="current"[/#if]>实名认证</a>
				</dd>
			</dl>
			<dl>
				<dt>${message("shop.member.navigation.order")}</dt>
				<dd>
					<a href="${base}/member/order/list.jhtml"[#if current == "orderList"] class="current"[/#if]>${message("shop.member.order.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/coupon_code/list.jhtml"[#if current == "couponCodeList"] class="current"[/#if]>${message("shop.member.couponCode.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/coupon_code/exchange.jhtml"[#if current == "couponCodeExchange"] class="current"[/#if]>${message("shop.member.couponCode.exchange")}</a>
				</dd>
			</dl>
			<dl>
				<dt>${message("shop.member.navigation.favorite")}</dt>
				<dd>
					<a href="${base}/member/favorite/list.jhtml"[#if current == "favoriteList"] class="current"[/#if]>${message("shop.member.favorite.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/product_notify/list.jhtml"[#if current == "productNotifyList"] class="current"[/#if]>${message("shop.member.productNotify.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/review/list.jhtml"[#if current == "reviewList"] class="current"[/#if]>${message("shop.member.review.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/consultation/list.jhtml"[#if current == "consultationList"] class="current"[/#if]>${message("shop.member.consultation.list")}</a>
				</dd>
			</dl>
			<dl>
				<dt>${message("shop.member.navigation.message")}</dt>
				<dd>
					<a href="${base}/member/message/send.jhtml"[#if current == "messageSend"] class="current"[/#if]>${message("shop.member.message.send")}</a>
				</dd>
				<dd>
					<a href="${base}/member/message/list.jhtml"[#if current == "messageList"] class="current"[/#if]>${message("shop.member.message.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/message/draft.jhtml"[#if current == "messageDraft"] class="current"[/#if]>${message("shop.member.message.draft")}</a>
				</dd>
			</dl>
			<dl>
				<dt>${message("shop.member.navigation.profile")}</dt>
				<dd>
					<a href="${base}/member/profile/edit.jhtml"[#if current == "profileEdit"] class="current"[/#if]>${message("shop.member.profile.edit")}</a>
				</dd>
				<dd>
					<a href="${base}/member/password/edit.jhtml"[#if current == "passwordEdit"] class="current"[/#if]>${message("shop.member.password.edit")}</a>
				</dd>
				<dd>
					<a href="${base}/member/receiver/list.jhtml"[#if current == "receiverList"] class="current"[/#if]>${message("shop.member.receiver.list")}</a>
				</dd>
			</dl>
			<dl>
				<dt>${message("shop.member.navigation.deposit")}</dt>
				<dd>
					<a href="${base}/member/deposit/recharge.jhtml"[#if current == "depositRecharge"] class="current"[/#if]>${message("shop.member.deposit.recharge")}</a>
				</dd>
				<dd>
					<a href="${base}/member/deposit/list.jhtml"[#if current == "depositList"] class="current"[/#if]>${message("shop.member.deposit.list")}</a>
				</dd>
			</dl>
			<!--
			<dl>
				<dt>${message("shop.member.navigation.community")}</dt>
				<dd>
					<a href="${base}/member/complaint/list.jhtml"[#if current == "complaintList"] class="current"[/#if]>${message("shop.member.complaints.list")}</a>
				</dd>
				<dd>
					<a href="${base}/member/complaint/add.jhtml"[#if current == "complaintAdd"] class="current"[/#if]>${message("shop.member.complaint.add")}</a>
				</dd>
			</dl>
			-->
			
			<dl>
				<dt>${message("shop.virtual.account")}</dt>
				<dd>
					<a href="${base}/member/virtual/list.jhtml"[#if current == "virtualList"] class="current"[/#if]>${message("shop.virtual.account.list")}</a>
				</dd>
				
			</dl>
		</div>
		<div class="bottom"></div>
	</div>
</div>