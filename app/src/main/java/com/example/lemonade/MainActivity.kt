package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Lemonade",
                    fontWeight = FontWeight.Bold
                )
            },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(249, 228, 76)
                )
                )

        }
    ) {innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ){
            when (currentStep) {
                1 -> {
                    ImageWithText(
                        imageResource = R.drawable.lemon_tree,
                        textLabelResource = R.string.lemon_select,
                        descriptionResource = R.string.lemon_tree_content_description,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    ImageWithText(
                        imageResource = R.drawable.lemon_squeeze,
                        textLabelResource = R.string.lemon_squeeze,
                        descriptionResource = R.string.lemon_content_description,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        })

                }

                3 -> {
                    ImageWithText(
                        imageResource = R.drawable.lemon_drink,
                        textLabelResource = R.string.lemon_drink,
                        descriptionResource = R.string.lemonade_content_description,
                        onImageClick = { currentStep = 4 })
                }

                4 -> {
                    ImageWithText(
                        imageResource = R.drawable.lemon_restart,
                        textLabelResource = R.string.lemon_empty_glass,
                        descriptionResource = R.string.empty_glass_content_description,
                        onImageClick = { currentStep = 1 })
                }
            }
        }
        }

    }



@Composable
fun ImageWithText(
    imageResource: Int,
    textLabelResource: Int,
    descriptionResource: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
    ){
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {

            Button(
                onClick = { onImageClick() },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(195, 236, 210),
                )
            ) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(descriptionResource),
                    modifier = Modifier
                        .width(128.dp)
                        .height(160.dp)
                        .padding(24.dp)
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textLabelResource),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LemonadeTheme {
//        LemonadeApp()
//    }
//}