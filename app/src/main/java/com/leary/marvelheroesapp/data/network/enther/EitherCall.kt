package com.leary.marvelheroesapp.data.network.enther

import com.leary.marvelheroesapp.data.network.models.ErrorResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class EitherCall<R>(
    private val delegate: Call<R>, // Основной вызов, делегирующий запрос
    private val successType: Type // Тип успешного ответа
) : Call<Either<ErrorResponse, R>> { // Реализация Call с возвращаемым типом Either<ErrorResponse, R>

    override fun enqueue(callback: Callback<Either<ErrorResponse, R>>) = delegate.enqueue(
        object : Callback<R> {

            // Обработка успешного ответа от сервера
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            // Преобразование ответа в Either<ErrorResponse, R>
            private fun Response<R>.toEither(): Either<ErrorResponse, R> {
                if (!isSuccessful) { // Если запрос не выполнен успешно
                    return Either.Fail(ErrorResponse.getValue(code())) // Возвращаем ошибку
                }

                body()?.let { body -> return Either.success(body) } // Если тело ответа не пустое, возвращаем успешный результат

                // Если тип успешного ответа - Unit, возвращаем успешный результат с Unit, иначе возвращаем ошибку
                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    Either.success(Unit) as Either<ErrorResponse, R>
                } else {
                    @Suppress("UNCHECKED_CAST")
                    Either.fail(UnknownError("Response body was null")) as Either<ErrorResponse, R>
                }
            }

            // Обработка ошибки при выполнении запроса
            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> ErrorResponse(700) // Ошибка ввода-вывода
                    else -> ErrorResponse(800) // Другие виды ошибок
                }
                callback.onResponse(this@EitherCall, Response.success(Either.Fail(error))) // Возвращаем ошибку в callback
            }
        }
    )

    override fun clone(): Call<Either<ErrorResponse, R>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<Either<ErrorResponse, R>> {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}