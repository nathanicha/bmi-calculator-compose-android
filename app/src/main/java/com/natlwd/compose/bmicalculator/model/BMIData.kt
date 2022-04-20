package com.natlwd.compose.bmicalculator.model

import android.os.Parcelable
import android.util.Log
import com.natlwd.compose.bmicalculator.roundOffDecimal
import kotlinx.parcelize.Parcelize

@Parcelize
data class BMIData(
    var gender: Gender = Gender.MALE,
    var height: Int = 180,
    var weight: Int = 60,
    var age: Int = 20
) : Parcelable {

    fun getBMIResult(): Double {
        Log.e("devLog", "height = $height, weight = $weight")
        val heightM: Double = height.toDouble() / 100.0
        Log.e("devLog", "heightM = $heightM")
        Log.e("devLog", "result = ${weight.toDouble() / (heightM * heightM)}")
        return (weight.toDouble() / (heightM * heightM)).roundOffDecimal() ?: -1.0
    }

    fun getBMIMeasure(bmi: Double): String {
        return when {
            bmi < 18.5 -> {
                "UNDERWEIGHT"
            }
            bmi < 22.9 -> {
                "NORMAL"
            }
            bmi < 24.9 -> {
                "NORMAL"
            }
            bmi < 29.9 -> {
                "OVERWEIGHT"
            }
            else -> {
                "OVERWEIGHT"
            }
        }
    }

    fun getBMISuggestion(bmi: Double): String {
        return when {
            bmi < 18.5 -> {
                "You have a lower than normal body weight. You can eat a bit more."
            }
            bmi < 22.9 -> {
                "You have a normal body weight. Good job!"
            }
            bmi < 24.9 -> {
                "You have a normal body weight. Good job!"
            }
            bmi < 29.9 -> {
                "You have a higher than normal body weight. Try to exercise more."
            }
            else -> {
                "You have a higher than normal body weight. Try to exercise more."
            }
        }
    }

}