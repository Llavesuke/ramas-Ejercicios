package org.pebiblioteca

import jdk.jshell.execution.Util

fun main() {
    val libraryManager = LibraryManager()

    val book1 = Book(1, "Romance of the Three Kingdoms", "Luo Guanzhong", 1522, "Fiction")
    val book2 = Book(2, "Initial D", "Suichi Shigeno", 1995, "Races")
    val book3 = Book(3, "The Hound of the Baskervilles", "Arthur Conan Doyle", 1902, "Mystery")

    libraryManager.addBookToCatalog(book1)
    libraryManager.addBookToCatalog(book2)
    libraryManager.addBookToCatalog(book3)

    libraryManager.registerLoan(book1)
    libraryManager.registerLoan(book2)

    libraryManager.returnBook(book1)
    libraryManager.returnBook(book3)

    val bookList = libraryManager.checkBooks()
    Utils.showList(bookList)
}