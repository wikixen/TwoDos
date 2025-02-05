package com.wikixen.twodos.viewModel

import com.wikixen.twodos.data.Todo

sealed interface TodoEvent {
    data object SaveTodo: TodoEvent
    data class SetDesc(val desc: String): TodoEvent
    data class CompleteTodo(val completed: Boolean): TodoEvent
    data object ShowDialog: TodoEvent
    data object HideDialog: TodoEvent
    data class DeleteTodo(val todo: Todo): TodoEvent
}