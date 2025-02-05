package com.wikixen.twodos

import androidx.compose.runtime.Composable
import com.wikixen.twodos.viewModel.TodoEvent
import com.wikixen.twodos.viewModel.TodoState

@Composable
fun TodoScreen(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {

}