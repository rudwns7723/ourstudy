
/*이거 존나 클릭하면 알람떠야 되는 데 안 뜸*/	
	function select(message){
		console.log('왜 안 되노');
		alert(message);
		return false;
	}
	
/*==============================
		좌석 선택 확인 alert
  =============================*/
	function checkSelect(){
		let seat_name = $('.seat-option').val();
		let seat_num = $('.seat-option').attr('data-seatnum');
		
		console.log(seat_num);
		
		let select = confirm(seat_name + '번 좌석을 선택할까요?');
		
		if(select == false){
			alert('좌석 선택이 취소되었습니다');
			return false;
		}else if(select == true){
			alert(seat_name + '좌석이 선택되었습니다.');
			let locker = confirm('사물함을 등록할까요?');
		
			if(locker == false){
				//replace, href 중 선택
				//(이전으로 못돌아감, 돌아갈 수 있음)
				location.href='select.do?seat_num=' + seat_num;
			}else if(locker == true){
				location.href='';
			}
		}
		
	}
	