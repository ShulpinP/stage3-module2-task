package com.mjc.school.service.exception;

public enum ErrorCode {
    STRING_LENGTH_IS_INVALID(1001, "%s field should have length from %s to %s."),
    REQUIRED_FIELD_IS_NULL(1002, "%s field must not be null"),
    AUTHOR_ID_IS_INVALID(1003, "minimum author id value must be 1"),
    NEWS_ID_IS_INVALID(1004, "minimum news id value must be 1"),
    NEWS_DOES_NOT_EXIST(1005, "news with id %s does not exist"),
    AUTHOR_DOES_NOT_EXIST(1006, "author with id %s does not exist"),
    ID_MUST_BE_AN_INTEGER(1007, "id field must be an integer");

    private final int id;
    private final String message;

    ErrorCode(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ERROR_CODE: " + id + ", ERROR_MESSAGE: " + message;
    }
}