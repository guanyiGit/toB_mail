package com.err;

import com.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        R ext = R.error(response.getStatus(), e.getMessage(), request);
        request.setAttribute("javax.servlet.error.status_code", response.getStatus());
        request.setAttribute("ext", ext);
        return "formord:/error";
    }

}
