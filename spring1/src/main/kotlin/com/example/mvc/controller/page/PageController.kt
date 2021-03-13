package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

//특정한 html 을 내릴 때 사용
//http://localhost:8080/main
@Controller
class PageController {
    @GetMapping("/main")
    fun main():String { // text main.html
        println("init main")
        return "main.html"
    }


    //ResponseBody 는 controller 에서 jsonString 을 내려야할 때 사용한다
    @ResponseBody
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply{
            this.name = "steve"
        }
        //return "main.html"

    }

}