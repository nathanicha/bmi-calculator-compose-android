package com.natlwd.compose.bmicalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.natlwd.compose.bmicalculator.MainActivity.Companion.BMI_KEY
import com.natlwd.compose.bmicalculator.home.HomeScreen
import com.natlwd.compose.bmicalculator.model.BMIData
import com.natlwd.compose.bmicalculator.result.ResultScreen
import com.natlwd.compose.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val BMI_KEY = "bmi_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        composable(Routes.Result.route+ "/{$BMI_KEY}") { navBackStack ->
            val data = navBackStack.arguments?.getString(BMI_KEY)
            val type = object : TypeToken<BMIData>() {}.type
            Log.e("devLog", "data $data")

            ResultScreen(
                navController = navController,
                bmiData = Gson().fromJson(data, type)
            )
        }
    }
}