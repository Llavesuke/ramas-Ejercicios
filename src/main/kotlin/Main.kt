package org.pebiblioteca

import jdk.jshell.execution.Util

fun main() {
    val registerLoan = RegisterLoan()
    val libraryCatalog = Catalog()
    val libraryManager = LibraryManager(registerLoan, libraryCatalog)

    val book1 = Book(title ="Romance of the Three Kingdoms", author = "Luo Guanzhong", yearOfRelease = 1522, topic = "Fiction")
    val book2 = Book(title = "Initial D", author =  "Suichi Shigeno", yearOfRelease =  1995, topic =  "Races")
    val book3 = Book(title = "The Hound of the Baskervilles", author = "Arthur Conan Doyle", yearOfRelease =  1902, topic = "Mystery")

    val user1 = User(LibraryUtils.generateUniqueIdentifier(), "Naruto")
    val user2 = User(LibraryUtils.generateUniqueIdentifier(), "Pablo")
    val user3 = User(LibraryUtils.generateUniqueIdentifier(), "Diego Cano")

    libraryCatalog.add(book1)
    libraryCatalog.add(book2)
    libraryCatalog.add(book3)

    libraryManager.lendElement(user1, book1.getId()!!)
    libraryManager.lendElement(user2, book2.getId()!!)

    libraryManager.giveElementBack(user1,book1.getId()!!)
    libraryManager.giveElementBack(user3,book2.getId()!!)

    val bookList = libraryCatalog.getElements()
    Utils.showList(bookList)

    val user1Loans = libraryManager.showUserLoans(user1)
    Utils.showList(user1Loans)
    val book2Loans = libraryManager.showBooksLoans(book2)
    Utils.showList(book2Loans)
}