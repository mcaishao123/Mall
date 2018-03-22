package com.cai.goodscenter.injection.module

import com.cai.goodscenter.service.CartService
import com.cai.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }

}
