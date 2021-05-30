package com.example.mvc.controller.exception

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(): String {

        //outofindex 발생
        val list = mutableListOf<String>()
        //val temp = list[0]
        return "hello"

    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min = 2, max = 6)
        @RequestParam name:String,
        @Min(10)
        @RequestParam age:Int
    ): String {

        println(name)
        println(age)

        return name + " " + age

    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest:UserRequest): UserRequest {

        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e : MethodArgumentNotValidException, request: HttpServletRequest){
        val erros = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach{
            errorObject ->
            val error = Error().apply{


                val field = errorObject as FieldError
                this.field = field.field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue as String?
            }
        }
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    //httpServletRequest 는 현재 request를 넣어준다
    fun constraintViolationException(e: ConstraintViolationException, request:HttpServletRequest) : ResponseEntity<ErrorResponse>{
        // 1. 에러 분석
        val errors = mutableListOf<Error>()

        //예외사항을 쭉 돌게 된다
        e.constraintViolations.forEach{
            val field = it.propertyPath.last().name //가장 마지막에 오는게 변수 이름
            val message = it.message

            val error = com.example.mvc.model.http.Error().apply{
                this.field = field
                this.message = message
            }

            errors.add(error)
        }
        // 2. Error Response

         val errorResponse = ErrorResponse().apply{
             this.resultCode = "FAIL"
             this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
             this.message = "요청에 에러가 발생했습니다"
             this.path = ""
             this.timestamp = LocalDateTime.now()
             this.errors = errors
         }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)

    }

    //controller 내부에 exception이 있다면 controller advice 를 타지 않고 여기로 오게 된다
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e : java.lang.IndexOutOfBoundsException): ResponseEntity<String> { // 그냥 String으로 내리면 200 OK

        println("controller exception handler")

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }




}