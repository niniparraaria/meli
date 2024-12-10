package com.ninestudios.meli.cocktail_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ninestudios.meli.R
import com.ninestudios.meli.cocktails.CocktailsResponse
import com.ninestudios.meli.core.getIngredients
import com.ninestudios.meli.core.isAlcholic
import com.ninestudios.meli.core.network.ApiStatus
import com.ninestudios.meli.ui.theme.MeliTheme
import com.ninestudios.meli.ui.views.CocktailDetail
import com.ninestudios.meli.ui.views.ErrorScreen
import com.ninestudios.meli.ui.views.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CocktailDetailScreen(id:String, onBack: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val viewModel: CocktailDetailViewModel = koinViewModel()
        val response = viewModel.cocktailDetailState.collectAsStateWithLifecycle()
        LaunchedEffect(Unit, block = {
            viewModel.getData(id)
        })
        when(response.value.status){
            ApiStatus.SUCCESS -> {
                val drinks = response.value.data?.drinks?.firstOrNull()
                if (drinks != null){
                    ContentScreen(drinks) {
                        onBack.invoke()
                    }
                }else{
                    ErrorScreen(stringResource(R.string.try_again_message))
                }
            }
            ApiStatus.ERROR -> {
                ErrorScreen(response.value.messageError)
            }
            ApiStatus.LOADING -> {
                LoadingScreen()
            }
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(drink:CocktailsResponse.Drink, onBack: () -> Unit){
    Column {
        TopAppBar(
            title = { Text(text = drink.strDrink?:"", fontSize = 20.sp ) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.background),
            navigationIcon = {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable {
                            onBack.invoke()
                        },
                    contentDescription = stringResource(id = R.string.back_description)
                )
            }
        )
        CocktailDetail(imageUrl = drink.strDrinkThumb?:"" , ingredients = drink.getIngredients(), isAlcoholic = drink.isAlcholic(), instructions = drink.strInstructions?:"" , name = drink.strDrink?:"")
    }
}



@Preview(showBackground = true)
@Composable
fun CocktailDetailScreenPreview() {
    MeliTheme {
        CocktailDetailScreen("", onBack = {})
    }
}