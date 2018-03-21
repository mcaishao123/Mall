package com.cai.user.injection.module

import com.cai.user.service.UploadService
import com.cai.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides


/*
    上传Module
 */
@Module
class UploadModule {

    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }

}
