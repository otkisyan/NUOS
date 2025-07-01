package org.example;

public class Token {
    private String value;
    private TokenType type;
    private byte code;
    private String regex;

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", type=" + type +
                ", code=" + code +
                ", regex='" + regex + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Token(String value, TokenType type, String regex) {
        this.value = value;
        this.type = type;
        this.regex = regex;
    }

    public Token(String value, TokenType type, byte code, String regex) {
        this.value = value;
        this.type = type;
        this.code = code;
        this.regex = regex;
    }
}