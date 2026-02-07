package com.ahuaman.pokedex.presentation.screens.dashboard.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ahuaman.pokedex.presentation.screens.dashboard.models.ScreensDashboard
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahuaman.pokedex.presentation.screens.favorite.FavoriteScreen
import com.ahuaman.pokedex.presentation.screens.home.HomeScreen
import kotlin.random.Random

@Composable
fun DashboardScreen(modifier: Modifier = Modifier,
                    navController: NavHostController = rememberNavController(),
                    items: List<ScreensDashboard> = ScreensDashboard.items
) {

    //var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            // You can add a TopAppBar here if needed
        } ,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val selectedFromDestination = items.indexOfFirst { menu ->
                (menu.javaClass.simpleName == currentDestination?.route?.split(".")?.last())
            }

            NavigationBar{
                items.forEachIndexed { index, item ->
                    val isSelected = if (selectedFromDestination == -1) index == 0 else index == selectedFromDestination
                    val textColor = if (isSelected) { colorResource(id = R.color.black) } else { Color.Gray }

                    NavigationBarItem(
                        modifier = Modifier.background(Color.Transparent),
                        colors =  NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Cyan.copy(alpha = 0.3f)
                        ),
                        icon = {
                            Icon(painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = if(isSelected) colorResource(id = R.color.black) else Color.Black.copy(alpha = 0.3f)
                            )
                        },
                        label = { Text(item.title, color = textColor) },
                        selected = isSelected,
                        onClick = {
                            //avoid select the same item
                            if (isSelected) return@NavigationBarItem
                            navController.navigate(item)
                        }
                    )
                }
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ScreensDashboard.Home,
                modifier = modifier.padding(innerPadding),
            ) {
                composable<ScreensDashboard.Home> {
                    HomeScreen()
                }

                composable<ScreensDashboard.Favorite> {
                    FavoriteScreen()
                }
            }
        }
    )
}


@Preview
@Composable
private fun DashboardScreenPrev() {
    DashboardScreen()
}