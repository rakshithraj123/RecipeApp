package com.app.debugmyapp.ui.recipe.categorylist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.debugmyapp.model.Category
import com.app.debugmyapp.repo.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class MockDataSource(private val repository: MyRepository) : PagingSource<Int, Category>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Category> {
        return try {
            val offset = params.key ?: 0

            val items = withContext(Dispatchers.IO){
                delay(4000)
                 repository.fetchCategoryList(offset)
            }





            // Calculate previous and next keys
            val prevKey = null
            val nextKey = if (items.isNotEmpty()) (offset + items.size) else offset

            LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun generateData(pageNumber: Int): List<String> {
        val start = pageNumber * 20
        val end = start + 20
        return (start until end).map { "Item $it" }
    }

    override fun getRefreshKey(state: PagingState<Int, Category>): Int? = state.anchorPosition

}
