package br.com.gallodev.filmix.ui.data.api

enum class Categoria (val path: String, val descricao:String){
    ASSISTIDOS("now_playing","Assitidos"),
    LANCAMENTO("upcoming","Lançamento"),
    MELHORES_AVALIADOS("top_rated", "Melhores Avaliados"),
    POPULARES("popular","Populares")
}