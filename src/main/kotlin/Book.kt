package org.pebiblioteca

/**
 * Contains all book information
 *
 * @property ID An integer number
 * @property Title The title of the book
 * @property Author The author name
 * @property yearOfRelease Year of release of the book
 * @property Topic The topic of the book
 * @property State If the book is borrowed or not
 */
data class Book(val ID: Int,
                val title: String,
                val author: String,
                val yearOfRelease: Int,
                val topic: String,
                var state: BookState = BookState.AVAIlABLE)