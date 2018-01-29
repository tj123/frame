package com.github.tj123.frame.web.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class ErrorResponse extends HashMap<String, Object> {

    public ErrorResponse() {

    }

    public ErrorResponse(String message) {
        setMessage(message);
    }

    public ErrorResponse(BindException e) {
        List<FieldError> errors = e.getFieldErrors();
        HashMap<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int size = errors.size();
        for (int i = 0; i < size; i++) {
            FieldError error = errors.get(i);
            map.put(error.getField(), error.getDefaultMessage());
            sb.append(error.getField()).append(error.getDefaultMessage());
            if (i != size - 1) {
                sb.append(",");
            }
        }
        put("errors", map);
        log.warn(sb.toString());
        setMessage(sb.toString());
    }

    public void setMessage(String message) {
        put("message", message);
    }

    public String getMessage() {
        return (String) get("message");
    }

}
