package com.wikixen.twodos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.wikixen.twodos.data.sampleTodos
import com.wikixen.twodos.views.HomeScreen

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val navItems = listOf(
    NavItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    NavItem(
        title = "Todos",
        selectedIcon = Icons.Filled.Check,
        unselectedIcon = Icons.Outlined.Check
    ),
    NavItem(
        title = "Completed",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle
    )
)

@Composable
fun Navigation(
    windowSize: WindowSizeClass,
) {
    val showNavRail = windowSize.widthSizeClass != WindowWidthSizeClass.Compact

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            if(!showNavRail) {
                NavCompact(
                    items = navItems,
                    selectedItemIndex = selectedItemIndex,
                    onNavigate = { selectedItemIndex = it}
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { inlinePadding ->
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(inlinePadding)
                .padding(start = if (showNavRail) 80.dp else 0.dp)
                .windowInsetsPadding(WindowInsets.displayCutout)
                .windowInsetsPadding(WindowInsets.systemBars),
            todos = sampleTodos
        )
    }

    if (showNavRail) {
        NavLarge(
            items = navItems,
            selectedItemIndex = selectedItemIndex,
            onNavigate = { selectedItemIndex = it}
        )
    }
}



// Nav bottom bar for regular screens
@Composable
fun NavCompact(
    items: List<NavItem>,
    selectedItemIndex: Int,
    onNavigate: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    onNavigate(index)
//                            navController.navigate(item.title)
                },
                label = {
                    Text(text = item.title, softWrap = false)
                },
                icon = {
                    NavIcon(
                        item = item,
                        selected = selectedItemIndex == index
                    )
                }
            )
        }
    }
}


// NavigationRail for larger screens
@Composable
fun NavLarge(
    items: List<NavItem>,
    selectedItemIndex: Int,
    onNavigate: (Int) -> Unit
) {
    NavigationRail(
        header = {
            FloatingActionButton(
                onClick = { TODO() },
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Todo"
                )
            }
        },
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.displayCutout)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        onNavigate(index)
                    },
                    label = {
                        Text(text = item.title)
                    },
                    icon = {
                        NavIcon(
                            item = item,
                            selected = selectedItemIndex == index
                        )
                    }
                )
            }
        }
    }
}

// NavIcon is used to reduce boilerplate in NavigationRailItem & NavigationBarItem
@Composable
fun NavIcon(
    item: NavItem,
    selected: Boolean
) {
    Icon(
        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
        contentDescription = item.title
    )
}
