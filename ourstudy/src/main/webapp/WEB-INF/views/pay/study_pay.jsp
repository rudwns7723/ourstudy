<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 결제 페이지 시작 -->
<!-- iamport.payment.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pay.css">
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<div id="main_content_body" class="container">
	<h4>이용권 선택내역</h4>
	<div class="row d-flex justify-content-center align-items-center">
	<div class="col-sm-8">
		<div class="card sm-5">
			<div class="card-body p-4">
				<input type="hidden" name="mem_num" data-memnum="${user.mem_num}" id="mem_num">
				<input type="hidden" name="ticket_num" data-ticketnum="${ticket.ticket_num}" id="ticket_num">
				<input type="hidden" id="ticket_total_price" value="${ticket.ticket_price}">
				<input type="hidden" id="org_point">
				<input type="hidden" name="mem_num" data-memnum="${user.mem_num}" id="mem_num">
				<input type="hidden" name="ticket_num" data-ticketnum="${ticket.ticket_num}" id="ticket_num">
				
				<div class="row align-items-center">
					<div class="col-sm-6">
						<p class="lead text-mute mb-4 pb-2">선택한 이용권</p>
						<p class="lead fw-normal mb-0" id="ticket_name" data-ticketname="${ticket.ticket_name}">${ticket.ticket_name}</p>
					</div>
					<div class="col-sm-6 d-flex justify-content-center">
					<div>
						<p class="lead text-mute mb-4 pb-2">금액</p>
						<p class="lead fw-normal mb-0"><input type="hidden" id="ticket_total_price" value="${ticket.ticket_price}">
						<fmt:formatNumber value="${ticket.ticket_price}" />원</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		 <div class="card sm-5">
        		<div class="card-body p-4">
			<span style="margin-top: 5px;">현재 보유 중인 포인트</span>
			<span id="my_point" style="float: right;">P</span>
			<h6 style="margin-top: 5px;"> 포인트 사용(1000P 이상 사용 가능)
			<input type="text" id="use_point" class="form-control form-control-sm" style="float: right; width: 100px;" placeholder="포인트 입력">
			<span id="p_point" data-pointnum="${point.point_point}"></span>
			<Button type="button" id="minus_btn" class="btn btn-primary">포인트 사용</Button>
			</h6>
			<hr>
			<span>결제 금액</span>
			<span id="final_price" style="float: right;" data-pricenum="${ticket.ticket_price}">${ticket.ticket_price}</span>
			<input type="image" id="pay_kakao" src="${pageContext.request.contextPath}/image_bundle/payment_icon_yellow_small.png" style="margin-left: 428px; margin-bottom: 3px;" onclick="requestKakaoPay()" name="pay_plan" value="카카오">
			<input type="button" id="pay_card" type="button" class="btn btn-primary" style="margin-bottom: 3px;" name="pay_plan" onclick="requestCardPay()" value="카드">
		</div>
		</div>
		<script src="${pageContext.request.contextPath}/js/pay.js"></script>
	</div>
	</div>

	</div>
<!-- 결제 페이지 끝 -->