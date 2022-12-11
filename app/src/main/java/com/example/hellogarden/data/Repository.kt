package com.example.hellogarden.data

import com.example.hellogarden.R
import com.example.hellogarden.data.models.ProductArticle

class Repository {

    fun loadProducts(): List<ProductArticle> {

        return listOf(
            ProductArticle(
                1,
                "Unser Sparangebot und für Anfänger geeignet",
                R.drawable.fieldone,
                "Deutschland",
            ),
            ProductArticle(
                2,
                "Wird künstliche Intelligenz uns bald alle ersetzen?!? Das sagen Wissenschaftler…",
                R.drawable.fieldtwo,
                "Welt",
            ),
            ProductArticle(
                3,
                "Die Hochhauspflicht - das Ende von Einfamilienhäusern? Lies alles dazu…",
                R.drawable.fieldthree,
                "Schweiz",
            )

        )
    }
}







