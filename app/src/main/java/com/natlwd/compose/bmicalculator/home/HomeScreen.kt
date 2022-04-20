package com.natlwd.compose.bmicalculator.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.natlwd.compose.bmicalculator.QuantityButton
import com.natlwd.compose.bmicalculator.R
import com.natlwd.compose.bmicalculator.Routes
import com.natlwd.compose.bmicalculator.SelectorButton
import com.natlwd.compose.bmicalculator.model.BMIData
import com.natlwd.compose.bmicalculator.model.Gender
import com.natlwd.compose.bmicalculator.ui.theme.DarkerBlue
import com.natlwd.compose.bmicalculator.ui.theme.RedPink
import com.natlwd.compose.bmicalculator.ui.theme.cardColor
import com.natlwd.compose.bmicalculator.ui.theme.normalTextColor

@Composable
fun HomeScreen(navController: NavController) {
    val bmi = BMIData()
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = {
                        Text(
                            text = "BMI CALCULATOR",
                            color = Color.White,
                            style = MaterialTheme.typography.h1
                        )
                    },
                    backgroundColor = DarkerBlue,
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(DarkerBlue)
                ) {
                    GenderItem(bmi.gender) {
                        bmi.gender = it
                    }

                    HeightItem(bmi.height) {
                        bmi.height = it
                    }

                    QuantityItem(
                        bmi.weight,
                        bmi.age, { weight ->
                            bmi.weight = weight
                        }, { age ->
                            bmi.age = age
                        })
                }
            },
            bottomBar = {
                Button(
                    onClick = {
                        val jsonText = Gson().toJson(bmi)
                        navController.navigate(Routes.Result.route + "/$jsonText")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(RedPink)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        text = "CALCULATE",
                        color = Color.White,
                        style = MaterialTheme.typography.button,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }
}

@Composable
fun QuantityItem(
    inputWeight: Int,
    inputAge: Int,
    onWeightSelected: (Int) -> Unit,
    onAgeSelected: (Int) -> Unit
) {
    var weight by remember { mutableStateOf(inputWeight) }
    var age by remember { mutableStateOf(inputAge) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        QuantityButton(
            title = "WEIGHT",
            value = weight,
            onValueChange = {
                weight = it
                onWeightSelected.invoke(weight)
            },
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
        )
        QuantityButton(
            title = "AGE",
            value = age,
            onValueChange = {
                age = it
                onAgeSelected.invoke(it)
            },
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
fun HeightItem(
    value: Int,
    onComplete: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        var height by remember { mutableStateOf(value.toFloat()) }

        Column(
            modifier = Modifier.background(cardColor)
        ) {
            Text(
                text = "HEIGHT",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                color = normalTextColor
            )
            val annotatedLinkString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        fontSize = 40.sp
                    )
                ) {
                    append("${height.toInt()}")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = normalTextColor
                    )
                ) {
                    append("cm")
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = annotatedLinkString
            )

            Slider(
                value = height, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                valueRange = 120f..220f,
                onValueChange = { newValue ->
                    height = newValue
                    onComplete.invoke(height.toInt())
                },
                colors = SliderDefaults.colors(
                    thumbColor = RedPink,
                    activeTrackColor = Color.White
                )
            )
        }
    }
}

@Composable
fun GenderItem(
    value: Gender,
    onComplete: (Gender) -> Unit
) {
    val isMale = value == Gender.MALE
    var selectMale by remember { mutableStateOf(!isMale) }
    var selectFemale by remember { mutableStateOf(isMale) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        SelectorButton(
            onClickBtn = {
                selectFemale = true
                selectMale = false
                onComplete(Gender.MALE)
            },
            textBtn = "MALE",
            iconBtn = Icons.Default.Male,
            iconDesc = R.string.cd_privacy,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 10.dp, 0.dp),
            enabledBtn = selectMale
        )

        SelectorButton(
            onClickBtn = {
                selectMale = true
                selectFemale = false
                onComplete(Gender.FEMALE)
            },
            textBtn = "FEMALE",
            iconBtn = Icons.Default.Female,
            iconDesc = R.string.cd_crop_square,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 0.dp),
            enabledBtn = selectFemale
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
}