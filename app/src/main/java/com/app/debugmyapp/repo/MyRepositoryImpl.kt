package com.app.debugmyapp.repo

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail
import com.app.debugmyapp.repo.local.LocalRepository
import com.app.debugmyapp.repo.network.NetworkRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(private val networkRepository : NetworkRepository,
                                           private val localRepository : LocalRepository) : MyRepository {

    override suspend fun fetchCategoryList(offset: Int): List<Category> {
        localRepository.fetchCategoryList(offset).let {
            if(it != null && it.isNotEmpty()){
                return it
            }else{
                networkRepository.fetchCategoryList().apply {
                    it?.let {
                        localRepository.saveCategoryList(this)
                    }
                    return this
                }
            }
        }
    }

    override suspend fun fetchRecipeList(categoryName: String): List<Recipe> {
        return networkRepository.fetchRecipeList(categoryName)
    }

    override suspend fun fetchRecipeDetail(recipeId: String): RecipeDetail {
        return networkRepository.fetchRecipeDetail(recipeId)
    }

    override suspend fun saveCategoryList(categoryList: List<Category>) {
        localRepository.saveCategoryList(categoryList)
    }

    override fun saveRecipeList(categoryName: String, recipeList: List<Recipe>) {
        TODO("Not yet implemented")
    }

    override fun saveRecipeDetail(recipeDetail: RecipeDetail) {
        TODO("Not yet implemented")
    }
}