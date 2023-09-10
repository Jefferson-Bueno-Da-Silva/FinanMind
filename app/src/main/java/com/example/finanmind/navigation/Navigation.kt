package com.example.finanmind.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finanmind.repositories.TransactionsRepository
import com.example.finanmind.screens.FormScreen
import com.example.finanmind.screens.HomeScreen
import com.example.finanmind.viewmodel.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val transactionsRepository = TransactionsRepository();
    val homeViewModel = HomeViewModel(
        transactionsRepository = transactionsRepository
    )

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(Screen.FormScreen.route) {
            FormScreen(navController)
        }
    }
}