package br.com.gallodev.filmix.ui.data.api

enum class Category (val path: String, val description:String){
    NOW_PLAYING("now_playing","Assitidos"),
    UPCOMING("upcoming","Lançamento"),
    TOP_RATED("top_rated", "Melhores Avaliados"),
    POPULAR("popular","Populares")
}