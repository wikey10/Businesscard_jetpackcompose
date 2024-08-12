package com.app.cardapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.cardapp.ui.theme.CardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateCard()
                }
            }
        }
    }
}

@Composable
fun CreateCard(){
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(300.dp)
            .height(90.dp)
            .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
            ) {

            Column(modifier = Modifier
                .fillMaxSize(),
//                .align(
//                    Alignment.CenterHorizontally
//                ),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                ImageProfile()
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.Blue,
                    thickness = 1.dp,
                )
                    Details()
                AuthenticationButton(buttonClickedState)
                if(buttonClickedState.value){
                    Content()
                }
                else{
                    Box{}
                }
            }

        }
    }
}

@Composable
fun ImageProfile(modifier: Modifier=Modifier ){
    Surface(modifier = Modifier
        .size(100.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
        shadowElevation = 5.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile",
            modifier = modifier.size(100.dp)
        )
    }
}

@Composable
fun  Details(){
    Column(modifier = Modifier.padding(5.dp )) {
        Text(text = "Developer Name",
            color = Color.Red,
            fontStyle = FontStyle.Italic,
            fontSize = 25.sp,
            modifier = Modifier.padding(3.dp )
        )
        Text(text = "Android Engineer",
            color = Color.Black,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 3.dp)
        )
        Text(text = "@twitter",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
            )

    }
}

@Composable
fun AuthenticationButton(buttonClickedState:MutableState<Boolean>){

    Button(
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        onClick = {
            buttonClickedState.value=!buttonClickedState.value
            Log.d("Clicked","Printing for checking")

        }) {
        Text(text = "Click Here", style = MaterialTheme.typography.bodyMedium,
            color = Color.White
            )

    }
}



@Composable
fun Content() {
    Box(
        modifier = Modifier
            .height(500.dp)
            .padding(5.dp)
              // Added background color for visual debugging
    ) {
        Surface(
            modifier = Modifier
                .padding(15.dp)
               ,  // Added background color for visual debugging
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize() // Added background color for visual debugging
    ) {
        itemsIndexed(data) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),  // Added background color for visual debugging
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation( 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(15.dp)
                        .fillMaxWidth()
                        .height(100.dp)  // Explicitly setting a height for Row
                ) {
                    ImageProfile(modifier = Modifier.size(75.dp))
                    Column(modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "The Project", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardAppTheme {
        CreateCard()
    }
}