package com.example.todo.config

import com.example.todo.database.TodoDateBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean(initMethod = "init") //실행되면 Bean 등록 되고, init method 실행된다
    fun todoDataBase(): TodoDateBase {
        return TodoDateBase()
    }

}