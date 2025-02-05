package com.wikixen.twodos.viewModel

import com.wikixen.twodos.data.Todo

data class TodoState(
    val todos: List<Todo> = emptyList(),
    val desc: String = "",
    val completed: Boolean = false,
    val isAddingTodo: Boolean = false
)
