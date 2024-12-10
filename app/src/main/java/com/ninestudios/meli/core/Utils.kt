package com.ninestudios.meli.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getString
import com.ninestudios.meli.R
import com.ninestudios.meli.cocktails.CocktailsResponse

fun hasInternetConnection(context: Context):Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

fun CocktailsResponse.Drink?.getIngredients():List<String> {
    val ingredients =  mutableListOf<String>()
    if (this != null){
        if (this.strIngredient1.isNullOrEmpty().not()) ingredients.add(this.strIngredient1!!)
        if (this.strIngredient2.isNullOrEmpty().not()) ingredients.add(this.strIngredient2!!)
        if (this.strIngredient3.isNullOrEmpty().not()) ingredients.add(this.strIngredient3!!)
        if (this.strIngredient4.isNullOrEmpty().not()) ingredients.add(this.strIngredient4!!)
        if (this.strIngredient5.isNullOrEmpty().not()) ingredients.add(this.strIngredient5!!)
        if (this.strIngredient6.isNullOrEmpty().not()) ingredients.add(this.strIngredient6!!)
        if (this.strIngredient7.isNullOrEmpty().not()) ingredients.add(this.strIngredient7!!)
        if (this.strIngredient8.isNullOrEmpty().not()) ingredients.add(this.strIngredient8!!)
        if (this.strIngredient9.isNullOrEmpty().not()) ingredients.add(this.strIngredient9!!)
        if (this.strIngredient10.isNullOrEmpty().not()) ingredients.add(this.strIngredient10!!)
    }

    return ingredients.filterNot { it.isEmpty() }.distinct()
}

fun List<String>.concatIngredients():String{
    return this.joinToString(separator = "", transform = {"•$it ".padEnd(5,)})
}


fun CocktailsResponse.Drink?.isAlcholic():Boolean {
    return this?.strAlcoholic == "Alcoholic"
}