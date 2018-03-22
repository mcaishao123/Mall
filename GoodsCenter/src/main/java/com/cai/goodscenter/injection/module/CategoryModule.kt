package com.cai.goodscenter.injection.module

import com.cai.goodscenter.service.CategoryService
import com.cai.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/*
    商品分类Module
 */
@Module
class CategoryModule {

    @Provides
    fun provideCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }

}
