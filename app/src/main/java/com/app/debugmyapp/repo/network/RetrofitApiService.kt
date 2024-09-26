package com.app.debugmyapp.repo.network

import com.app.debugmyapp.model.RecipeDetail
import com.app.debugmyapp.repo.network.response.CategoryListResponse
import com.app.debugmyapp.repo.network.response.RecipeDetailResponse
import com.app.debugmyapp.repo.network.response.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {

    @GET("categories.php")
    suspend fun getCategoryList() : CategoryListResponse

    @GET("filter.php")
    suspend fun getRecipeList(@Query("c") category: String = "Seafood") : RecipeListResponse

    @GET("lookup.php")
    suspend fun fetchRecipeDetail(@Query("i") recipeId: String = "52772"): RecipeDetailResponse
}