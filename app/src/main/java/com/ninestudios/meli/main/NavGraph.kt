package com.ninestudios.meli.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ninestudios.meli.cocktail_detail.CocktailDetailScreen
import com.ninestudios.meli.cocktails.CocktailsScreen
import com.ninestudios.meli.main.Destinations.DETAIL_ROUTE
import com.ninestudios.meli.main.Destinations.LIST_ROUTE

object Destinations {
    const val LIST_ROUTE = "cocktails"
    const val DETAIL_ROUTE = "cocktail/{id}"
}
@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = LIST_ROUTE) {
        composable(LIST_ROUTE) {
            CocktailsScreen(onClick = {
                navController.navigate("cocktail/$it")
            })
        }
        composable(DETAIL_ROUTE){
            val id  = it.arguments?.getString("id")
            CocktailDetailScreen(id = id!!, onBack = {
                navController.popBackStack()
            })
        }
    }
}