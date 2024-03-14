package org.pebiblioteca

import java.util.UUID

/**
 * Contains all book information
 *
 * @property ID UUID Type, by default is null
 * @property Title The title of the book
 * @property Author The author name
 * @property yearOfRelease Year of release of the book
 * @property Topic The topic of the book
 * @property State If the book is borrowed or not
 */
data class Book(var ID: UUID? = null,
                val title: String,
                val author: String,
                val yearOfRelease: Int,
                val topic: String,
                var state: BookState = BookState.AVAIlABLE)