package com.hukun.exam.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//标记当前类是用来处理异常的
@ControllerAdvice
public class HandleForException extends Throwable {

    @ExceptionHandler({ArithmeticException.class})
    public String testArithmeticException(Exception e){
        System.out.println("打印错误信息 ===> ArithmeticException:"+e);
        //跳转到指定页面
        return "/page/404.html";
    }
}
