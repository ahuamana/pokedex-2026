package com.ahuaman.pokedex.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahuaman.pokedex.presentation.navigation.models.ScreensRoot
import com.ahuaman.pokedex.presentation.screens.dashboard.components.DashboardScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    controller : NavHostController = rememberNavController()
) {
    NavHost(
        navController = controller,
        startDestination = ScreensRoot.DashboardScreen
    ) {
        composable<ScreensRoot.AuthenticationScreen> {
            //TODO: Handle Authentication logic here, if the user is authenticated navigate to dashboard screen
            Text("AuthenticationScreen")
        }

        composable<ScreensRoot.DashboardScreen> {
            //TODO: Retrieve items from viewmodel and pass them as parameters to the screen
            DashboardScreen(
                modifier = Modifier.fillMaxSize(),
                navController = rememberNavController()
            )
        }
    }
}