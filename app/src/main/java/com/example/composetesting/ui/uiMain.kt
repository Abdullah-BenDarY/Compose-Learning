package com.example.composetesting.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composetesting.R

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun GreetingPreview() {
    MainScreen()
}

@Composable
fun MainScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imgProfile, tvUserName , tvCreatedAt) = createRefs()
//        val chained = createVerticalChain(tvUserName, tvCreatedAt, chainStyle = ChainStyle.)
        Text(text = "Hello, Safy" , Modifier
            .clickable {

            }
            .constrainAs(tvUserName){
            top.linkTo(imgProfile.top)
            start.linkTo(imgProfile.end)
            bottom.linkTo(tvCreatedAt.top)
        } )

        Text(text = "20 - 10 - 2024" , modifier = Modifier.constrainAs(tvCreatedAt){
            top.linkTo(tvUserName.bottom)
            bottom.linkTo(imgProfile.bottom)
            start.linkTo(tvUserName.start)
        })


        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "imgProfile",
            Modifier
                .size(40.dp)
                .constrainAs(imgProfile) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            contentScale = ContentScale.Crop
        )
    }
}































//        val (button) = createRefs()
//        val modifier = Modifier.constrainAs(button){}
//        LoginButton(Modifier.constrainAs(
//            button
//        ){
//            top.linkTo(parent.top)
//            bottom.linkTo(parent.bottom)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        })


//
//@Composable
//fun LoginButton(modifier: Modifier) {
//    val text =
//        "This text animates as though it is being typed \uD83E\uDDDE\u200D♀\uFE0F \uD83D\uDD10  \uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68 \uD83D\uDC74\uD83C\uDFFD"
//    val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }
//
//    var substringText by remember {
//        mutableStateOf("")
//    }
//    LaunchedEffect(
//        text,
//    ) {
//        // Initial start delay of the typing animation
//        delay(100)
//        breakIterator.text = StringCharacterIterator(text)
//
//        var nextIndex = breakIterator.next()
//        // Iterate over the string, by index boundary
//        while (nextIndex != BreakIterator.DONE) {
//            substringText = text.subSequence(0, nextIndex).toString()
//            // Go to the next logical character boundary
//            nextIndex = breakIterator.next()
//            delay(100)
//        }
//    }
//    Text(substringText , modifier)
//}

