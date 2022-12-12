package com.example.hellogarden.data

import com.example.hellogarden.R
import com.example.hellogarden.data.models.ProductArticle

class Repository {

    fun loadProducts(): List<ProductArticle> {

        return listOf(
            ProductArticle(
                1,
                "Anfängerfreundlicher Garten perfekt geeignet für Beginner von Mietgärten",
                R.drawable.gartenbawue,
                "Munich",
                "01.12.22",
                "scheuen sie sich nicht und lassen sie sich unverbindlich ein Prospekt zukommen wo alles genau erklärt und einfach strukturiert ist",
            ),
            ProductArticle(
                2,
                "Wird eher den Kennern und Profis empfohlen",
                R.drawable.aache,
                "Aachen",
                "08.12.22",
                "Sehr geeignet für Kenner und Profis, der Boden wurde bereits vorbehandelt und und auf Wunsch " +
                        "bereits begeimt werden, der Boden ist für viele GemüseSorten geeignet." +
                        "Es gibt eine Betreuung unserer Seits und die Bewässerung übernimmt unser Gartenhelfer",
            ),
            ProductArticle(
                3,
                "Die Hochhauspflicht - das Ende von Einfamilienhäusern? Lies alles dazu…",
                R.drawable.fieldthree,
                "Baden Württemberg",
                "10.12.22",
                "Super Anbauland von jeglichem Gemüse oder Obst"
            )

        )
    }
}
