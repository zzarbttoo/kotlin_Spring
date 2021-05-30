package com.example.todo.service

import com.example.todo.database.Todo
import com.example.todo.database.convertTodo
import com.example.todo.model.http.TodoDto
import com.example.todo.model.http.convertTodoDto
import com.example.todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service


/**
 * 현업에서는 java : model mapper
 * kotlin : kotlin reflector
 */
//TODO : service 에서 dto 변환시키는 test code 확인
@Service
class TodoService(

        val todoRepositoryImpl: TodoRepositoryImpl

){

    //Todo 는 내부 스키마이기 때문에 return 할 때도 TodoDto가 나가는 것이 좋다
    //C
    fun create(todoDto:TodoDto): TodoDto?{
        return todoDto.let{
            Todo().convertTodo(it)
        }.let{
            todoRepositoryImpl.save(it)
        }?.let{
            TodoDto().convertTodoDto(it)
        }
    }

    //R
    fun read(index:Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let{
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map{
            TodoDto().convertTodoDto(it)
        }.toMutableList()
    }

    //U
    fun update(todoDto:TodoDto) : TodoDto? {
        return todoDto.let{
            Todo().convertTodo(it)
        }.let{
            todoRepositoryImpl.save(it)
        }?.let{
            TodoDto().convertTodoDto(it)
        }
    }

    //D
    fun delete(index:Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }

}