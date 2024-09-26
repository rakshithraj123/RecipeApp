package com.app.debugmyapp.repo.network

import com.app.debugmyapp.repo.network.exceptions.ErrorResponseException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.http2.Http2Reader

internal class  LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

       // if (BuildConfig.DEBUG) {
        if (true) {
            Http2Reader.logger.info(
                String.format(
                    "*** Sending request %s on %s%n%s",
                    request.url, chain.connection(), request.headers
                )
            )
        }

        val t1 = System.nanoTime()
        val response = chain.proceed(request)
        val t2 = System.nanoTime()

        if(response.code in 400..404){
            throw ErrorResponseException("Failed")
        }

       // if (BuildConfig.DEBUG) {
        if (true) {
            Http2Reader.logger.info(
                String.format(
                    "*** Received response for %s in %.1fms%n%s",
                    response.request.url, (t2 - t1) / 1e6, response.body
                )
            )
        }

        return response
    }


}