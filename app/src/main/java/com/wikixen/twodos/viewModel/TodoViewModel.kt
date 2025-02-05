package com.wikixen.twodos.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wikixen.twodos.data.Todo
import com.wikixen.twodos.data.TodoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoViewModel(
    private val dao: TodoDao
): ViewModel() {
    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    fun onEvent(event: TodoEvent) {
        when(event) {
            is TodoEvent.CompleteTodo -> {
                _state.update { it.copy(
                    completed = event.completed
                ) }
            }
            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch {
                    dao.deleteTodo(event.todo)
                }
            }
            TodoEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingTodo = false
                ) }
            }
            TodoEvent.SaveTodo -> {
                val desc: String = state.value.desc
                val completed = false

                if (desc.isBlank()) {
                    return
                }

                val todo = Todo(
                    desc = desc,
                    completed = completed,
                )

                viewModelScope.launch {
                    dao.upsertTodo(todo)
                }

                _state.update { it.copy(
                    isAddingTodo = false,
                    desc = ""
                ) }
            }
            is TodoEvent.SetDesc -> {
                _state.update { it.copy(
                    desc = event.desc
                ) }
            }
            TodoEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingTodo = true
                ) }
            }
        }
    }
}