package com.app.debugmyapp.repo

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.repo.local.entity.CategoryEntity

// External to local
fun Category.toEntity() = CategoryEntity(
     idCategory = idCategory,
    strCategory = strCategory,
    strCategoryThumb = strCategoryThumb,
    strCategoryDescription = strCategoryDescription
)


fun CategoryEntity.toModel() = Category(
    idCategory = idCategory,
    strCategory = strCategory,
    strCategoryThumb = strCategoryThumb,
    strCategoryDescription = strCategoryDescription
)

@JvmName("modelToEntity")
fun List<Category>.toEntity() = map(Category::toEntity)

@JvmName("entityToModel")
fun List<CategoryEntity>.toModel() = map(CategoryEntity::toModel)