package com.ashu.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashu.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    artWorkApp()

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun artWorkApp(){
    var currentPage by remember{ mutableStateOf(1) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "ArtWorks",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ){innerPadding->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            var imgres =when(currentPage){
                1-> R.drawable.beautyinold
                2-> R.drawable.boat
                3-> R.drawable.redhouse
                4-> R.drawable.vintagehouse
                else-> R.drawable.rose
            }
            var stringres =when(currentPage){
                1-> R.string.sketch_1
                2-> R.string.sketch_2
                3-> R.string.sketch_3
                4-> R.string.sketch_4
                else-> R.string.last_page
            }


            CurrentArtwork(
                imgres = imgres,
                stringres = stringres,
                onNextClick = {
                    currentPage = (currentPage % 4) + 1
                },
                onPrevClick = {
                    currentPage = if (currentPage > 1) currentPage - 1 else 4
                }
            )





            

        }


    }
}
@Composable
fun CurrentArtwork(imgres:Int,stringres:Int,onNextClick:()->Unit,onPrevClick:()->Unit){
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
       Card ( shape = RoundedCornerShape(20.dp),
           elevation = CardDefaults.cardElevation(
               defaultElevation = 10.dp)){
               Image(painter = painterResource(id = imgres), contentDescription ="" ,
                   modifier=Modifier.size(width = 300.dp, height =
                   500.dp),
                   contentScale = ContentScale.Crop
                 )
           Text(text = stringResource(id = stringres) ,modifier= Modifier
               .padding(20.dp)
               .align(Alignment.CenterHorizontally))

       }
        Spacer(modifier = Modifier.padding(22.dp))
        Row() {
            Button(onClick = onNextClick ) {
                Text(text = "Previous")
            }
            Button(onClick = onPrevClick ,modifier=Modifier.padding(start=36.dp)) {
                Text(text = "Next")
                
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewApp(){
    artWorkApp()
}