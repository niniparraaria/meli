package com.ninestudios.meli.cocktails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ninestudios.meli.R
import com.ninestudios.meli.core.getIngredients
import com.ninestudios.meli.core.isAlcholic
import com.ninestudios.meli.core.network.ApiStatus
import com.ninestudios.meli.ui.theme.MeliTheme
import com.ninestudios.meli.ui.views.CocktailItem
import com.ninestudios.meli.ui.views.EmptyScreen
import com.ninestudios.meli.ui.views.ErrorScreen
import com.ninestudios.meli.ui.views.LoadingScreen
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailsScreen(onClick: (id:String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val viewModel: CocktailsViewModel = koinViewModel()
        val response = viewModel.cocktailsState.collectAsStateWithLifecycle()
        var text by rememberSaveable{ mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name), fontSize = 24.sp ) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.onBackground),
            )
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background
                ),
                placeholder = {
                    Text(stringResource(R.string.placeholder_search))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.getData(text)
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
            )
            when(response.value.status){
                ApiStatus.SUCCESS -> {
                    val drinks = response.value.data?.drinks ?: emptyList()
                    if (drinks.isNotEmpty()){
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2)
                        ) {
                            items(drinks){ drink ->
                                CocktailItem(id = drink?.idDrink?:"", imageUrl = drink?.strDrinkThumb?:"", ingredients = drink.getIngredients(), isAlcoholic = drink.isAlcholic(), name = drink?.strDrink?:"") {
                                    onClick.invoke(it)
                                }
                            }

                        }
                    }else{
                        EmptyScreen()
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
}


@Preview(showBackground = true)
@Composable
fun CocktailsScreenPreview() {
    MeliTheme {
        CocktailsScreen(onClick = {})
    }
}