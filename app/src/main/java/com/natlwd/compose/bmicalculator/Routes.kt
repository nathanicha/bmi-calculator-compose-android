package com.natlwd.compose.bmicalculator

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Result : Routes("result")
}