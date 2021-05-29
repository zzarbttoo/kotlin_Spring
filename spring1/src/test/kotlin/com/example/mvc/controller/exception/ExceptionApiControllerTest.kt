package com.example.mvc.controller.exception

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest //WebMVC 관련 내용만 springboot
@AutoConfigureMockMvc //자동으로 MOCMVC 요청
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc : MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/exception/hello")
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest//200이 오는 것을 기댓값으로 설정
        ).andExpect(
            MockMvcResultMatchers.content().string("hello")
        ).andDo(MockMvcResultHandlers.print()) //test 관련 출력을 한다

    }
}