package com.wikixen.twodos.views

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.wikixen.twodos.data.Todo
import com.wikixen.twodos.viewModel.TodoEvent
import com.wikixen.twodos.viewModel.TodoState

@Composable
fun HomeScreen(
    modifier: Modifier,
    todos: List<Todo>
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if (orientation == Configuration.ORIENTATION_PORTRAIT){
        Column (
            modifier.fillMaxSize(),
        ) {
            TodoCard(
                todos[0].desc,
                height = .5f,
                width = 1f
            )
            TodoCard(
                todos[1].desc,
                height = 1f,
                width = 1f
            )
        }
    } else {
        Row (
            modifier,
        ){
            TodoCard(
                todos[0].desc,
                height = 1f,
                width = .5f
            )
            TodoCard(
                todos[1].desc,
                height = 1f,
                width = 1f
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoCard(
    desc: String,
    height: Float,
    width: Float
) {
    val haptics = LocalHapticFeedback.current

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .fillMaxWidth(width)
            .fillMaxHeight(height)
            .padding(15.dp)
            .combinedClickable (
                onClick = { TODO() },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    TODO()
                }
            ),
    ) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = desc,
            )
        }
    }
}

// This is from the Room Vid, need to finish that to see how to implement this
@Composable
fun TodoScreen2(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {

}