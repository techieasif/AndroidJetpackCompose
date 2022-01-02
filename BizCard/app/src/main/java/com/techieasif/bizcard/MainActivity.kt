package com.techieasif.bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techieasif.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    BuildCardBody()
                }
            }
        }
    }
}


@Composable
fun BuildCardBody() {
    val buttonClickState = remember {
        mutableStateOf(value = false)
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color.LightGray
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            elevation = 3.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage(
                    Modifier
                        .size(160.dp)
                        .padding(8.dp)
                )
                Divider(thickness = 1.5.dp)
                PortFolioInfo()
                PortfolioBtn(buttonClickState)
                if (buttonClickState.value) {
                    Content()
                }


            }
        }
    }
}

@Composable
private fun PortfolioBtn(state: MutableState<Boolean>) {
    Surface(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                state.value = !state.value
            },
        ) {
            Text("PortFolio")
        }

    }
}

@Composable
private fun PortFolioInfo() {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            "Pooja Hedge",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            "Actress\n@mypooja",
            style = MaterialTheme.typography.subtitle1,
        )


    }
}

//@Preview(showBackground = true)
@Composable
private fun Content() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            border = BorderStroke(0.5.dp, color = Color.Gray),
            elevation = 2.dp
        ) {
            Portfolio(data = listOf("Project1", "Project2", "Project3"))

        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn(content = {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), shape = RectangleShape
            ) {

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileImage(
                        Modifier
                            .size(120.dp)
                            .padding(8.dp)
                    )
                    Text(text = item)

                }

            }
        }
    })
}

@Composable
private fun ProfileImage(modifier: Modifier) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.Gray),
        elevation = 8.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = "Profile Image",
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center
        )
        Divider(thickness = 2.dp)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        BuildCardBody()
    }
}