package com.wikixen.twodos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wikixen.twodos.data.sampleTodos
import com.wikixen.twodos.ui.theme.TwoDosTheme
import com.wikixen.twodos.views.HomeScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwoDosTheme {
                val windowClass = calculateWindowSizeClass(this)

                Navigation(windowClass)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoPreview() {
    TwoDosTheme {
        HomeScreen(
            modifier = Modifier,
            todos = sampleTodos
        )
    }
}