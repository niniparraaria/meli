package com.ninestudios.meli.cocktails


import com.google.gson.annotations.SerializedName

data class CocktailsResponse(
    @SerializedName("drinks")
    val drinks: List<Drink?>?
) {
    data class Drink(
        @SerializedName("dateModified")
        val dateModified: String? ="",
        @SerializedName("idDrink")
        val idDrink: String?="",
        @SerializedName("strAlcoholic")
        val strAlcoholic: String?="",
        @SerializedName("strDrink")
        val strDrink: String?="",
        @SerializedName("strDrinkThumb")
        val strDrinkThumb: String?="",
        @SerializedName("strIngredient1")
        val strIngredient1: String?="",
        @SerializedName("strIngredient10")
        val strIngredient10: String?="",
        @SerializedName("strIngredient2")
        val strIngredient2: String?="",
        @SerializedName("strIngredient3")
        val strIngredient3: String?="",
        @SerializedName("strIngredient4")
        val strIngredient4: String?="",
        @SerializedName("strIngredient5")
        val strIngredient5: String?="",
        @SerializedName("strIngredient6")
        val strIngredient6: String?="",
        @SerializedName("strIngredient7")
        val strIngredient7: String?="",
        @SerializedName("strIngredient8")
        val strIngredient8: String?="",
        @SerializedName("strIngredient9")
        val strIngredient9: String?="",
        @SerializedName("strInstructions")
        val strInstructions: String?=""
    )
}