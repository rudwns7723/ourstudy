IMP.init("imp36873723"); // 예: imp00000000 

	function requestCardPay() {
		IMP.request_pay({
			pg : "INIpayTest",
			pay_method : "card",
			merchant_uid : "s0003", // 주문번호
			name : "독서실 12시간 이용권",
			amount : 8000, // 숫자 타입
			buyer_email : "test2@gmail.com",
			buyer_name : "이테스트",
			buyer_tel : "010-4242-4242",
			buyer_addr : "서울특별시 강남구 역삼동",
			buyer_postcode : "01181"
		}, function(rsp) { // callback
			console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				alert(msg);
				location.href = "결제 완료 후 이동할 페이지 url"
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				alert(msg);
			}
		});
	}
	
	function requestKakaoPay() {
		IMP.request_pay({
			pg : "kakaopay.TC0ONETIME",
			pay_method : "card",
			merchant_uid : "ourstudy0002", // 주문번호
			name : "독서실 8시간 이용권",
			amount : 6000, // 숫자 타입
			buyer_email : "test1@gmail.com",
			buyer_name : "박테스트",
			buyer_tel : "010-4242-4242",
			buyer_addr : "서울특별시 강남구 역삼동",
			buyer_postcode : "01181"
		}, function(rsp) { // callback
			console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				alert(msg);
				location.href = "결제 완료 후 이동할 페이지 url"
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				alert(msg);
			}
		});
	}