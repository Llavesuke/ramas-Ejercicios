package org.pebiblioteca

data class Book(val ID: Int,
                val title: String,
                val author: String,
                val yearOfRelease: Int,
                val topic: String,
                var state: BookState = BookState.AVAIlABLE)