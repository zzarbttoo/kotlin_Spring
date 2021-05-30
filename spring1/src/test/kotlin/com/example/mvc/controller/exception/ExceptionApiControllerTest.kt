package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

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

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "steve")
        queryParams.add("age", "20")

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams)
        ).andExpect(
                MockMvcResultMatchers.status().isOk
        ).andExpect(
                MockMvcResultMatchers.content().string("steve 20")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "steve")
        queryParams.add("age", "9")

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest
        ).andExpect(
               MockMvcResultMatchers.content().contentType("application/json")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("\$.result_code").value("FAIL")
        )
        .andExpect(MockMvcResultMatchers.jsonPath("\$.errors[0].field").value("age"))
        .andExpect(MockMvcResultMatchers.jsonPath("\$.errors[0].value").value("9"))
        .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun postTest(){
        val userRequest = UserRequest().apply{
            this.name = "steve"
            this.age  = 10
            this.phoneNumber = "010-3453-3683"
            this.address = "성남시 경기도"
            this.email = "qodbwls70@naver.com"
            this.createdAt = "2020-10-02 13:00:00"

        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/exception")
                        .content(json).contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                MockMvcResultMatchers.status().isOk
        )//.andExpect(
                //MockMvcResultMatchers.jsonPath("$['name']").value("steve")
        //)
        .andDo(
                MockMvcResultHandlers.print()
        )


    }

    @Test
    fun postFailTest(){
        val userRequest = UserRequest().apply{
            this.name = "steve"
            this.age  = -1
            this.phoneNumber = "010-3453-3683"
            this.address = "성남시 경기도"
            this.email = "qodbwls70@naver.com"
            this.createdAt = "2020-10-02 13:00:00"

        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/exception")
                        .content(json).contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                MockMvcResultMatchers.status().isOk
        )//.andExpect(
                //MockMvcResultMatchers.jsonPath("$['name']").value("steve")
                //)
                .andDo(
                        MockMvcResultHandlers.print()
                )
    }


    }