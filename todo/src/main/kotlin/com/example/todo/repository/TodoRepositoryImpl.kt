package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDateBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl : TodoRepository{

    //어디서든 todoDateBase 접근 가능
    @Autowired
    lateinit var todoDataBase: TodoDateBase

    override fun save(todo: Todo): Todo? {


        // 1. index?
        return todo.index?.let{ index ->
            //update

            findOne(index)?.apply{
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        }?: kotlin.run{

            //insert

            todo.apply{
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run{
                todoDataBase.todoList.add(todo)
                this
            }
        }


        /*
        val index = todoDataBase.index++
        todo.index = index
        todoDataBase.todoList.add(todo)

         */
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {

        return try {
            todoList.forEach{
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun delete(index: Int): Boolean {

        return findOne(index)?.let {
            todoDataBase.todoList.remove(it)
        }?: kotlin.run {
            false
        }
    }


    //TODO : 값이 없을 때 exception 처리
    //TODO : test code 도 함께 작성
    //?가 있어서 값이 없을 수도 있다는 것을 나타내줌
    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
        //return todoDataBase.todoList.filter { it.index == index }.first()
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }

}