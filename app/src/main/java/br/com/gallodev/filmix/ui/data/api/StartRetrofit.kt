package br.com.gallodev.filmix.ui.data.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class StartRetrofit {

    // Cria um interceptor para logar as requisições e respostas
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        Log.i("StartRetrofit", "LoggingInterceptor criado")
        level = HttpLoggingInterceptor.Level.BODY // Define o nível de detalhe do log
    }

    //Cria um cliente OkHttp e adiciona o interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Adiciona o interceptor de log
        .connectTimeout(30, TimeUnit.SECONDS) // Define um tempo limite de conexão
        .readTimeout(30, TimeUnit.SECONDS)    // Define um tempo limite de leitura
        .writeTimeout(30, TimeUnit.SECONDS)   // Define um tempo limite de escrita
        .build()

    //Cria o Retrofit com o OkHttpClient configurado
    fun retrofit(): Retrofit {
        Log.i("StartRetrofit", "Retrofit criado")
        return Retrofit.Builder()
            .baseUrl(Companion.BASE_URL)
            .client(okHttpClient) // Usa o OkHttpClient com o interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun filmService(): FilmService {
        Log.i("StartRetrofit", "FilmService criado")
        return retrofit().create(FilmService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}

    private val client by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun filmService(): FilmService = retrofit.create(FilmService::class.java)
