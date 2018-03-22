package com.cai.goodscenter.injection.module

import com.cai.goodscenter.service.GoodsService
import com.cai.goodscenter.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/*
    商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodservice(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }

}
