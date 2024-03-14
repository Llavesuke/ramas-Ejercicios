package org.pebiblioteca

import jdk.jshell.execution.Util

fun main() {
    val libraryManager = LibraryManager()

    val book1 = Book( title ="Romance of the Three Kingdoms", author = "Luo Guanzhong", yearOfRelease = 1522, topic = "Fiction")
    val book2 = Book(title = "Initial D", author =  "Suichi Shigeno", yearOfRelease =  1995, topic =  "Races")
    val book3 = Book(title = "The Hound of the Baskervilles", author = "Arthur Conan Doyle", yearOfRelease =  1902, topic = "Mystery")

    val user1 = User(LibraryUtils.generateUniqueIdentifier(), "Naruto")
    val user2 = User(LibraryUtils.generateUniqueIdentifier(), "Pablo")
    val user3 = User(LibraryUtils.generateUniqueIdentifier(), "Diego Cano")

    libraryManager.addBookToCatalog(book1)
    libraryManager.addBookToCatalog(book2)
    libraryManager.addBookToCatalog(book3)

    libraryManager.registerLoan(book1,user1)
    libraryManager.registerLoan(book2,user2)

    libraryManager.returnBook(book1,user1)
    libraryManager.returnBook(book3,user3)

    val bookList = libraryManager.checkBooks()
    Utils.showList(bookList)

    val user1Loans = libraryManager.showUserLoans(user1)
    Utils.showList(user1Loans)
    val book2Loans = libraryManager.showBooksLoans(book2)
    Utils.showList(book2Loans)
}