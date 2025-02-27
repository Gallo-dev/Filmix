package br.com.gallodev.filmix.ui.data.api

enum class Categoria (val path: String, val descricao:String){
    ASSISTIDOS("movie/now_playing","Assitidos"),
    LANCAMENTO("movie/upcoming","Lançamento"),
    MELHORES_AVALIADOS("movie/top_rated", "Melhores Avaliados"),
    POPULARES("movie/popular","Populares")
}