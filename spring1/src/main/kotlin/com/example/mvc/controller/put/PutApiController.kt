package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {


    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {

        return "request-mapping"
    }

    //put mapping도 request body를 가지고 있다
    //post 와 달리 없으면 생성, 아니면 수정
    //@valid annotaion을 이용해 부분적인 검증(bean) 을 진행한다
    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@Valid @RequestBody userRequest:UserRequest, bindingResult: BindingResult): ResponseEntity<String> {


        //error가 났을 때 바로 exception을 내는 것이 아니라 bindingResult에 저장한다
        //bindingResult에서 필드와 메시지를 조합할 수 있고, 보내준다
        if (bindingResult.hasErrors()){
            // 500 error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.field+ ":" + message + "\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")

        /*

        //apply는 자기 자신을 반환한다
        // 0. Response
        return UserResponse().apply{
            // 1. result
            this.result = Result().apply{

                this.resultCode = "OK"
                this.resultMessage = "성공"

            }
        }.apply{

            // 2. description
            this.description = "~~~~~~~~~~~"

        }.apply{

            //3. user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)
            userList.add(UserRequest().apply{
                this.name = "a"
                this.age = 10
                this.email = "zzarb@gmail.com"
                this.address = "b address"
                this.phoneNumber = "091-333-4444"

            })

            userList.add(UserRequest().apply{

                this.name = "b"
                this.age = 20
                this.email = "FG@gdsfs.com"
                this.address = "b address"
                this.phoneNumber = "010-3333-9999"

           })
            this.userRequest = userList

        }

         */



    }


}