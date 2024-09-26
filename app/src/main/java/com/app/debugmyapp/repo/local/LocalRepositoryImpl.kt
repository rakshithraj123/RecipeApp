package com.app.debugmyapp.repo.local

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail
import com.app.debugmyapp.repo.toEntity
import com.app.debugmyapp.repo.toModel
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(val localDataBase: LocalDataBase): LocalRepository {

    override suspend fun fetchCategoryList(offset : Int): List<Category> {
         localDataBase.categoryDao().get(offset)?.let {
            return  it.toModel()
         }
        return emptyList<Category>()
    }

    override suspend fun saveCategoryList(categoryList: List<Category>) {
        localDataBase.categoryDao().insert(categoryList.toEntity())
    }

    override fun fetchRecipeList(categoryName: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun saveRecipeList(categoryName: String, recipeList: List<Recipe>) {
        TODO("Not yet implemented")
    }

    override fun fetchRecipeDetail(recipeId: String): RecipeDetail {
        TODO("Not yet implemented")
    }

    override fun saveRecipeDetail(recipeDetail: RecipeDetail) {
        TODO("Not yet implemented")
    }
}