package com.example.todo.repository

import com.example.todo.config.AppConfig
import com.example.todo.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
//필요한 것만 import 하여 test를 진행한다
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    //각 test 실행 전 before 실행
    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDataBase.init() //전체 실행 될 때 깨지는 것을 방지하기 위해 초기화
    }

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @Test
    fun saveTest(){
        val todo = Todo().apply{
            this.title  = "테스트일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)
        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
        Assertions.assertEquals("테스트일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)


    }

    @Test
    fun saveAllTest(){
        val todoList = mutableListOf(
                Todo().apply{
                    this.title  = "테스트일정"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply{
                    this.title  = "테스트일정"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply{
                    this.title  = "테스트일정"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                }
                )

        val result = todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest(){


        val todoList = mutableListOf(
                Todo().apply{
                    this.title  = "테스트일정"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply{
                    this.title  = "테스트일정2"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply{
                    this.title  = "테스트일정3"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                }
        )

        todoRepositoryImpl.saveAll(todoList)
        val result = todoRepositoryImpl.findOne(2)

        println(result)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트일정2", result?.title)

    }

    @Test
    fun updateTest(){

        val todo = Todo().apply{
            this.title  = "테스트일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply{
            this.index = insertTodo?.index
            this.title  = "업데이트 일정"
            this.description = "테스트 업데이트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(newTodo)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(insertTodo?.index, result?.index)
        Assertions.assertEquals("업데이트 일정", result?.title)
        Assertions.assertEquals("테스트 업데이트", result?.description)


    }



}