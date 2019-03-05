package com.pe.ventas.back.dtos.rest;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Getter;

@Getter
public class ResultResponse implements Serializable {

    private static final long serialVersionUID = 8023644832677222940L;

    private final String code;
    private final Object object;
    private final String message;

    private ResultResponse(final Builder builder) {
        code = builder.code;
        object = builder.object;
        message = builder.message;
    }

    public String aJson() {
        return JsonDto.aJson(this);
    }

    @Override
    public String toString() {
        return aJson();
    }

    public static class Builder {
        private String code;
        private Object object;
        private String message;

        public Builder() {
            code = "200";
            message = "successful";
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder code(final String code) {
            this.code = code;
            return this;
        }

        public Builder code(final Integer code) {
            this.code = code.toString();
            return this;
        }

        public Builder object(final Object object) {
            this.object = object;
            return this;
        }

        public ResultResponse build() {
            return new ResultResponse(this);
        }
    }

}
