package main008.BED.exception;

import lombok.Getter;


public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),

    USER_EXISTS(409, "User(Email) exists");

    @Getter
    private final int statusCode;

    @Getter
    private final String message;

    ExceptionCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
