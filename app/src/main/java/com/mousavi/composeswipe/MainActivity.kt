package com.mousavi.composeswipe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mousavi.composeswipe.ui.theme.ComposeSwipeTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSwipeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.LightGray) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        SwipeView()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeView() {

    val sizeOfButton = with(LocalDensity.current) { 58.dp.roundToPx().toFloat() }
    val tenDpInPx = with(LocalDensity.current) { 10.dp.roundToPx().toFloat() }
    val state = rememberSwipeableState(initialValue = 1)
    val anchors = mapOf(
        0f to 1,
        sizeOfButton to 2,
        2 * sizeOfButton + tenDpInPx to 3,
        3 * sizeOfButton + 2 * tenDpInPx to 4
    )

    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xFF4CAF50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val scale = state.progress.fraction
            val from = state.progress.from
            val to = state.progress.to
            Log.d("dfjhedif", "from: $from, to: $to")
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .scale(if ((from == 1 && to == 2)) scale else if ((from == 2 && to == 1)) (1 - scale) else 1f)
            )
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
                    .scale(if ((from == 2 && to == 3)) scale else if ((from == 3 && to == 2)) (1 - scale) else 1f)

            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
                    .scale(if ((from == 3 && to == 4)) scale else if ((from == 4 && to == 3)) (1 - scale) else 1f)
            )

        }
        Box(modifier = Modifier
            .offset {
                IntOffset(state.offset.value
                    .roundToInt()
                    .coerceAtLeast(0), 0)
            }
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .swipeable(
                state = state,
                anchors = anchors,
                orientation = Orientation.Horizontal,
            )
        ) {

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.hashem),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Hashem Mousavi",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        text = "Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.",
                        style = TextStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                        ),
                        overflow = TextOverflow.Ellipsis,
                    )
                }

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSwipeTheme {
        Surface(color = Color.LightGray) {
            SwipeView()
        }
    }
}