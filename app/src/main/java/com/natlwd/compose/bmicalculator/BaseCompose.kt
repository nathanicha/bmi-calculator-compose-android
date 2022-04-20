package com.natlwd.compose.bmicalculator

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natlwd.compose.bmicalculator.ui.theme.*
import androidx.compose.foundation.layout.Arrangement

@Composable
fun CustomTopAppBar(
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        onBackButtonClick.invoke()
                    }
                ) {
                    Image(
                        painterResource(R.drawable.ic_baseline_arrow_white_24),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }
                Text(
                    text = "BMI CALCULATOR",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    style = MaterialTheme.typography.h1
                )
            }
        },
        backgroundColor = DarkerBlue,
    )
}

@Composable
fun SelectorButton(
    onClickBtn: () -> Unit,
    textBtn: String,
    iconBtn: ImageVector,
    @StringRes iconDesc: Int,
    modifier: Modifier = Modifier,
    enabledBtn: Boolean = true
) {
    Button(
        onClick = {
            onClickBtn.invoke()
        },
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 30.dp,
            end = 20.dp,
            bottom = 30.dp
        ),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = if (enabledBtn) deselectedButton else selectedButton)
    ) {
        Column {
            Icon(
                imageVector = iconBtn,
                contentDescription = stringResource(id = iconDesc),
                tint = Color.White,
                modifier = Modifier
                    .size(ButtonDefaults.IconSize * 3)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = textBtn,
                color = normalTextColor
            )
        }
    }
}

@Composable
fun QuantityButton(
    title: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(deselectedButton)
            .fillMaxWidth()
    ) {
        val annotatedLinkString = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 40.sp)) {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(title)
                }
                append("\n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 40.sp
                    )
                ) {
                    append("$value")
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            textAlign = TextAlign.Center,
            text = annotatedLinkString
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp, 0.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center) {
                IconButton(
                    onClick = {
                        onValueChange(value - 1)
                    },
                    modifier = Modifier
                        .then(
                            Modifier
                                .size(50.dp)
                        )
                        .clip(CircleShape)
                        .background(whiteGray)
                ) {
                    Text(
                        text = "-",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
            }

            Box(modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center) {
                IconButton(
                    onClick = {
                        onValueChange(value + 1)
                    },
                    modifier = Modifier
                        .then(
                            Modifier
                                .size(50.dp)
                        )
                        .clip(CircleShape)
                        .background(whiteGray)
                ) {
                    Text(
                        text = "+",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}