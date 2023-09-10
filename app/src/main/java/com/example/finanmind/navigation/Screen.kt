package com.example.finanmind.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object FormScreen : Screen("form_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
