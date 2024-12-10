package com.ninestudios.meli.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.ninestudios.meli.R
import com.ninestudios.meli.ui.theme.MeliTheme

@Composable
fun CocktailDetail(
    imageUrl:String,
    isAlcoholic:Boolean,
    name:String,
    ingredients:List<String>,
    instructions:String
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())){
        val (image, textInstructionsTitle, textInstructions, textIngredients, listIngredients, icon) = createRefs()

        ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .aspectRatio(1f))
        Text(
            modifier = Modifier.constrainAs(textInstructionsTitle){
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            text = stringResource(R.string.cocktail_item_instructions_title),
            style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )
        )
        Text(
            modifier = Modifier.constrainAs(textInstructions){
                top.linkTo(textInstructionsTitle.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            text = instructions,
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start
            )
        )
        Text(
            modifier = Modifier.constrainAs(textIngredients){
                top.linkTo(textInstructions.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth().padding(horizontal = 16.dp),
            text = stringResource(R.string.cocktail_item_ingredients_title),
            style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )
        )
        Column(modifier = Modifier.constrainAs(listIngredients){
            top.linkTo(textIngredients.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp) ) {
            ingredients.forEach{ item ->
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.cocktail_item_ingredients, item),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start
                    )
                )
            }
        }
        val drinkIcon = if (isAlcoholic)  painterResource(R.drawable.ic_drink) else  painterResource(R.drawable.ic_no_drink)
        Image(
            painter = drinkIcon,
            contentDescription = "icon drink",
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground) ,
            modifier = Modifier
                .constrainAs(icon){
                    top.linkTo(listIngredients.bottom)
                    start.linkTo(parent.start)
                }
                .size(50.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailDetailPreview() {
    MeliTheme {
        CocktailDetail(imageUrl = "", name = "hola", isAlcoholic = true, ingredients = listOf("etc", "etc"), instructions = "")
    }
}
