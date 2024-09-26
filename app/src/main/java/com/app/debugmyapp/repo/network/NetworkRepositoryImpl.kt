package com.app.debugmyapp.repo.network

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val retrofitApiService : RetrofitApiService) : NetworkRepository{

    override suspend fun fetchCategoryList(): List<Category> {
        return retrofitApiService.getCategoryList().categories
    }

    override suspend fun fetchRecipeList(categoryName: String): List<Recipe> {
        return retrofitApiService.getRecipeList(categoryName).meals
    }

    override suspend fun fetchRecipeDetail(recipeId: String): RecipeDetail {
        return retrofitApiService.fetchRecipeDetail(recipeId).meals[0]
    }

}