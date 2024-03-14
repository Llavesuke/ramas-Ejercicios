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
 *
 */
class Book(private var ID: UUID? = null,
                private val title: String,
                private val author: String,
                private val yearOfRelease: Int,
                private val topic: String,
                private var state: State = State.AVAIlABLE): LibraryElement(ID, title, author), Prestable{

    init {
        ID = LibraryUtils.generateUniqueIdentifier()
    }
    override fun toString(): String {
        return "ID: ${this.ID}\n -Title: ${this.title}\n -Author: ${this.author}\n -Topic: ${this.topic}\n" +
                " -Book State: ${this.state}"
    }

    override fun lend(user: User) {
        if (state == State.AVAIlABLE) {
            state = State.BORROWED
            user.borrowBook(this)
        }
    }

    override fun returnElement(user: User) {
        if (state == State.BORROWED) {
            state = State.AVAIlABLE
            user.returnBook(this)
        }
    }
}