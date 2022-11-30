package main008.BED.exception;

import lombok.Getter;


public enum ExceptionCode {

    /*Users*/
    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User(Email) exists"),
    BAD_PRICE(400, "1,000원 단위로 입력해주세요."),
    OVER_PRICE(400, "50,000원 이하로 입력해주세요."),
    UNAUTHORIZED(401, "본 사용자는 해당 기능에 대해 권한이 없습니다."),
    CARVED_IN_STONE(400, "이메일은 수정할 수 없습니다."),

    /*Review*/
    BAD_STAR_RATE(400, "별점은 1~5 사이의 정수 값을 입력해주세요."),
    FORBIDDEN_USER(403, "해당 기능은 작성자만 수행할 수 있습니다."),

    /*Contents*/
    CONTENTS_NOT_FOUND(404, "Contents Not Found"),

    /*Chapter*/
    CHAPTER_NOT_FOUND(404, "Chapter Not Found"),

    /*UploadClass*/
    UPLOAD_CLASS_NOT_FOUND(404, "UploadClass Not Found"),
    UPLOAD_CLASS_EXISTS(409, "해당 비디오 이름의 파일이 이미 업로드 되어 있습니다."),

    /*Docs*/
    DOCS_NOT_FOUND(404, "Docs Not Found"),
    DOCS_EXISTS(409, "Docs Already exists"),

    /*Warning*/
    DUPLICATE_WARNING(409, "해당 강의에 대하여 이미 신고한 내역이 있습니다."),
    FORBIDDEN_TUTOR(403, "강사는 해당 기능을 수행할 수 없습니다."),

    /*Payment Coin*/
    COIN_SHORTAGE(400, "잔액이 부족합니다."),
    DUPLICATE_PAY(409, "해당 강의를 이미 구매하셨습니다."),

    /*Bookmark*/
    BOOKMARK_NOT_FOUND(404, "Bookmark Not Found"),

    /*KakaoPay*/
    WRONG_TID(404, "Not available tid"),
    STRANGE_APPROACH(404, "비 정상적인 접근이 감지되어 결제가 실패되었습니다."),
    PAY_CANCEL(200, "결제가 정상적으로 취소되었습니다."),
    PAY_FAILED(500, "결제에 실패하였습니다. 다시 시도하여주세요."),
    AMOUNT_DIFFERENT(404, "결제 금액이 달라 결제가 취소되었습니다. 처음부터 다시 시도하여주세요."),
    DETAIL_NOT_FOUND(404, "코인 충전 내역을 찾을 수 없습니다."),
    CANCEL_FAILED(500, "환불에 실패하였습니다. 다시 시도하여주세요."),
    COIN_CHARGE_NOT_FOUND(404, "Coin charge not found."),

    /*Likes*/
    LIKES_NOT_FOUND(404, "Likes not found"),

    /*Wish*/
    WISH_NOT_FOUND(404, "Wish not found"),

    /*Payment*/
    PAYMENT_NOT_FOUND(404, "Payment not found")

    ;


    @Getter
    private final int statusCode;

    @Getter
    private final String message;

    ExceptionCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
