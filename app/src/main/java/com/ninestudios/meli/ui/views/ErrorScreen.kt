package com.ninestudios.meli.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninestudios.meli.R
import com.ninestudios.meli.ui.theme.MeliTheme

@Composable
fun ErrorScreen(message:String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier.padding(2.dp),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.error),
            ) {
                Icon(
                    Icons.Rounded.Close,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    contentDescription = stringResource(id = R.string.error_description)
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = message,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Justify
                )
            )
        }

    }
}

@Preview
@Composable
fun ErrorPreview(){
    MeliTheme {
        ErrorScreen("Error")
    }
}