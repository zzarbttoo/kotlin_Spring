package com.example.mvc.advice

import com.example.mvc.controller.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

//restController에서 발생하는 exception 들이 모이게 된다
//전체 exception을 처리할 수 있지만 특정 패키지의 경로에 있는 restController 에 대해 동작시킬 수 있다
//@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class])
@RestControllerAdvice
//@ControllerAdvice 는 controller 의 exception 을 잡을 수 있다
class GlobalControllerAdvice {

    //@Exception hanlder의 value에 어떤 exception을 받을 지 지정해줄 수 있음
    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e : RuntimeException) : String{
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e : java.lang.IndexOutOfBoundsException): ResponseEntity<String> { // 그냥 String으로 내리면 200 OK

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}