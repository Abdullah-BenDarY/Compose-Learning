@file:OptIn(ExperimentalFoundationApi::class)

package com.example.composetesting.ui

import android.icu.text.BreakIterator
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composetesting.R
import kotlinx.coroutines.delay
import java.text.StringCharacterIterator

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
fun GreetingPreview() = MainScreen()

@Composable
fun MainScreen() {
    var showLoginButton by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imgProfile, tvUserName, tvCreatedAt, tvMove) = createRefs()

        Text(
            text = "Hello, Safy",
            color = Color.Red,
            modifier = Modifier.constrainAs(tvUserName) {
                top.linkTo(imgProfile.top)
                start.linkTo(imgProfile.end)
                bottom.linkTo(tvCreatedAt.top)
            }
        )

        Text(
            text = "20 - 10 - 2024",
            modifier = Modifier.constrainAs(tvCreatedAt) {
                top.linkTo(tvUserName.bottom)
                bottom.linkTo(imgProfile.bottom)
                start.linkTo(tvUserName.start)
            }
        )

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "imgProfile",
            modifier = Modifier
                .combinedClickable(
                    onClick = {
                        showLoginButton = true                    },
                    onLongClick = {
                        showLoginButton = false
                    }
                )
                .clip(shape = RoundedCornerShape(25.dp))
                .size(50.dp)
                .constrainAs(imgProfile) {
                    end.linkTo(tvUserName.start)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(parent.bottom)
                },
            contentScale = ContentScale.Crop
        )

        if (showLoginButton) {
            LoginButton(modifier = Modifier.constrainAs(tvMove) {
                top.linkTo(tvCreatedAt.bottom, margin = 16.dp)
                start.linkTo(tvCreatedAt.start)
            })
        }
    }
}







//@Composable
//@Preview(
//    showSystemUi = true,
//    showBackground = true
//)
//fun GreetingPreview() = MainScreen()
//
//@Composable
//fun MainScreen() {
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//        val (imgProfile, tvUserName, tvCreatedAt, tvMove) = createRefs()
////        val chained = createVerticalChain(tvUserName, tvCreatedAt, chainStyle = ChainStyle.)
//        Text(text = "Hello, Safy",
//            color = Color.Red,
//            modifier = Modifier
//
//                .constrainAs(tvUserName) {
//                    top.linkTo(imgProfile.top)
//                    start.linkTo(imgProfile.end)
//                    bottom.linkTo(tvCreatedAt.top)
//
//                })
//        Text(text = "20 - 10 - 2024" , modifier = Modifier.constrainAs(tvCreatedAt){
//            top.linkTo(tvUserName.bottom)
//            bottom.linkTo(imgProfile.bottom)
//            start.linkTo(tvUserName.start)
//        })
//        Image(
//            painter = painterResource(R.drawable.ic_launcher_background),
//            contentDescription = "imgProfile",
//            modifier = Modifier
//                .clickable {
//
//                }
//                .clip(shape = RoundedCornerShape(25.dp))
//                .size(50.dp)
//                .constrainAs(imgProfile) {
//                    end.linkTo(tvUserName.start)
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start, margin = 16.dp)
//                    bottom.linkTo(parent.bottom)
//                },
//            contentScale = ContentScale.Crop
//        )
//
//    }
//}
//
@Composable
fun LoginButton(modifier: Modifier) {
    val text =
        "This text animates as though it is being typed \uD83E\uDDDE\u200D♀\uFE0F \uD83D\uDD10  \uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68 \uD83D\uDC74\uD83C\uDFFD"
    val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }

    var substringText by remember {
        mutableStateOf("")
    }
    LaunchedEffect(
        text,
    ) {
        // Initial start delay of the typing animation
        delay(100)
        breakIterator.text = StringCharacterIterator(text)

        var nextIndex = breakIterator.next()
        // Iterate over the string, by index boundary
        while (nextIndex != BreakIterator.DONE) {
            substringText = text.subSequence(0, nextIndex).toString()
            // Go to the next logical character boundary
            nextIndex = breakIterator.next()
            delay(100)
        }
    }
    Text(substringText, modifier)
}

