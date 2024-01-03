package com.example.recipescompose.domain.network.util

import android.util.Log
import com.example.recipescompose.domain.network.responses.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T>handleResponse(response: Response<T>) = flow<Resource<T>> {
    emit(Resource.Loading())

    if(response.isSuccessful){
        when(response.code()){
            in 200..300 -> {
                if (response.body() == null) {
                    emit(Resource.Error("Empty Data"))
                    Log.d(Constants.TAG,"handle Empty Data")
                } else {
                    emit(Resource.Success(response.body()!!))
                    Log.d(Constants.TAG,"handle ${response.body()}")
                }
            }
            in 301..400 -> {
                Log.e("MyRepositoryImpl","Network Error : ${response.message()}")
                emit(Resource.Error(message = response.message()))
                Log.d(Constants.TAG,"handle Network Error")
            } else -> {
            Log.e("MyRepositoryImpl","Server Error : ${response.message()}")
            emit(Resource.Error(message = response.message()))
            Log.d(Constants.TAG,"handle Server Error")
        }
        }
    } else {
        Log.e("MyRepositoryImpl","Something went wrong with : ${response.message()}")
        emit(Resource.Error(message = response.message()))
    }
}