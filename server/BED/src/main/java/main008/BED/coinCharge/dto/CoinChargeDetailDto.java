package main008.BED.coinCharge.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CoinChargeDetailDto {

    /**
     * 결제 고유 번호
     */
    @Getter
    @ToString
    public static class getTid {

        private String tid;
    }

    /**
     * 결제 요청 시 카카오에게 받음
     */
    @Getter
    @Setter
    @ToString
    public static class KakaoReadyResponse {

        private String tid;
        private String next_redirect_mobile_url; // 모바일 웹일 경우 받는 결제페이지 url
        private String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지
        private String created_at;
    }

    /**
     * 결제 승인 요청 시 사용
     */
    @Getter
    @Setter
    @ToString
    public static class KakaoApproveResponse {

        private String aid; // 요청 고유 번호
        private String tid; // 결제 고유 번호
        private String cid; // 가맹점 코드
        private String sid; // 정기결제용 ID
        private String partner_order_id; // 가맹점 주문 번호
        private String partner_user_id; // 가맹점 회원 id
        private String payment_method_type; // 결제 수단
        private Amount amount; // 결제 금액 정보
        private String item_name; // 상품명
        private String item_code; // 상품 코드
        private int quantity; // 상품 수량
        private String created_at; // 결제 요청 시간
        private String approved_at; // 결제 승인 시간
        private String payload; // 결제 승인 요청에 대해 저장 값, 요청 시 전달 내용
    }

    /**
     * 결제 금액 정보
     */
    @Getter
    @Setter
    @ToString
    public static class Amount {

        private int total; // 총 결제 금액
        private int tax_free; // 비과세 금액
        private int tax; // 부가세 금액
        private int point; // 사용한 포인트
        private int discount; // 할인금액
        private int green_deposit; // 컵 보증금
    }
}
