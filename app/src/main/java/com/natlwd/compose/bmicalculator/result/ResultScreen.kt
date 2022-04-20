package com.natlwd.compose.bmicalculator.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.natlwd.compose.bmicalculator.CustomTopAppBar
import com.natlwd.compose.bmicalculator.model.BMIData
import com.natlwd.compose.bmicalculator.ui.theme.DarkBlue
import com.natlwd.compose.bmicalculator.ui.theme.DarkerBlue
import com.natlwd.compose.bmicalculator.ui.theme.RedPink

@Composable
fun ResultScreen(
    navController: NavController,
    bmiData: BMIData
) {
    val bmiScore = bmiData.getBMIResult()

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomTopAppBar {
                    navController.popBackStack()
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkerBlue)
                ) {
                    //title
                    Text(
                        text = "Your Result",
                        color = Color.White,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold
                    )

                    //content
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = DarkBlue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            val annotatedLinkString = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Green,
                                        fontSize = 24.sp
                                    )
                                ) {
                                    append(bmiData.getBMIMeasure(bmiScore) + "\n\n")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White,
                                        fontSize = 40.sp
                                    )
                                ) {
                                    append(bmiScore.toString() + "\n\n")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        color = Color.White,
                                        fontSize = 20.sp
                                    )
                                ) {
                                    append(bmiData.getBMISuggestion(bmiScore))
                                }
                            }

                            Text(
                                text = annotatedLinkString,
                                style = MaterialTheme.typography.h4,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(2f))
                }
            }, bottomBar = {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(RedPink)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        text = "RE-CALCULATE",
                        color = Color.White,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewCalculate() {
}