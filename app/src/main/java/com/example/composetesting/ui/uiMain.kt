@file:OptIn(ExperimentalFoundationApi::class)

package com.example.composetesting.ui
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composetesting.R
import com.example.composetesting.ui.theme.MintGreen
import kotlinx.coroutines.delay
import java.text.BreakIterator
import java.text.StringCharacterIterator

@Composable
@Preview(
    showSystemUi = true, device = "id:pixel_5", showBackground = true)
fun GreetingPreview() = MainScreen()

@Composable
fun MainScreen() {
    val counter = remember { mutableIntStateOf(0) }
    val showLoginButton = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val (row, imgProfile, tvUserName, tvCreatedAt, tvMove) = createRefs()

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

            ProfileImage(
                onClick = { showLoginButton.value = true },
                onLongClick = { showLoginButton.value = false },
                modifier = Modifier.constrainAs(imgProfile) {
                    end.linkTo(tvUserName.start, margin = 5.dp)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(parent.bottom)
                }
            )

            if (showLoginButton.value) {
                LoginButton(modifier = Modifier.constrainAs(tvMove) {
                    top.linkTo(row.bottom, margin = 16.dp)
                    start.linkTo(row.start)
                })
            }
            CounterRow(
                counter = counter.intValue,
                onIncrement = { if (counter.intValue < 10) counter.intValue++ },
                onDecrement = { if (counter.intValue > 0) counter.intValue-- },
                modifier = Modifier.constrainAs(row) {
                    top.linkTo(imgProfile.bottom)
                    start.linkTo(imgProfile.start)
                    end.linkTo(tvCreatedAt.end)
                }
            )
        }
    }
}

@Composable
fun ProfileImage(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "imgProfile",
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .clip(shape = RoundedCornerShape(25.dp))
            .size(50.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CounterRow(
    counter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        UpdateCounterCartButton(
            text = "+",
            onButtonClicked = onIncrement
        )
        Text(
            modifier = Modifier
                .width(31.dp)
                .padding(horizontal = 5.dp),
            textAlign = TextAlign.Center,
            text = counter.toString(),
            fontSize = 18.sp
        )

        UpdateCounterCartButton(
            text = "-",
            onButtonClicked = onDecrement
        )
    }
}

@Composable
fun UpdateCounterCartButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .padding(vertical = 5.dp)
            .size(30.dp)
            .border(
                width = 1.dp,
                color = MintGreen,
                shape = CircleShape
            )
            .padding(0.dp),
        shape = CircleShape
    ) {
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginButton(modifier: Modifier) {
    val text =
        "This text animates as though it is being typed \uD83E\uDDDE\u200D♀\uFE0F \uD83D\uDD10  \uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68 \uD83D\uDC74\uD83C\uDFFD"
    val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }

    var substringText by remember {
        mutableStateOf("_")
    }
    LaunchedEffect(
        text,
    ) {
        delay(50)
        breakIterator.text = StringCharacterIterator(text)

        var nextIndex = breakIterator.next()
        while (nextIndex != BreakIterator.DONE) {
            substringText = text.subSequence(0, nextIndex).toString()
            nextIndex = breakIterator.next()
            delay(50)
        }
    }
    Text(substringText, modifier)
}