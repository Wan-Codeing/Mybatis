<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
   
    <meta charset="UTF-8">
    <title>Sample Payment</title>
</head>
<body>
    <button onclick="requestPay()">결제하기</button> <!-- 결제하기 버튼 생성 -->

    <div id="amount">100</div>

    <script>
        var IMP = window.IMP; 
        IMP.init("imp06580330"); 
      
        var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
        
        let price = document.getElementById("amount").innerHTML;

        function requestPay() {
            IMP.request_pay({
                pg : "kakaopay",
                pay_method : 'card',
                merchant_uid: "IMP"+makeMerchantUid, 
                name : '당근 10kg',
                amount : price ,
                buyer_email : 'Iamport@chai.finance',
                buyer_name : '아임포트 기술지원팀',
                buyer_tel : '010-1234-5678',
                buyer_addr : '서울특별시 강남구 삼성동',
                buyer_postcode : '123-456',
                display: {
                    card_quota: [3]  // 할부개월 3개월까지 활성화
                }
            }, function (rsp) { // callback
                if (rsp.success) {
                    console.log(rsp);
//                    window.alert("결제가 완료되었습니다.");
                    let name = rsp.name;
                    <%  String name = null; %>
             
                } else {
                    console.log(rsp);
                }
            });
        }
    </script>
</body>
</html>