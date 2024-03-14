package org.pebiblioteca

interface IRegisterLoan {
    fun registerLoan(user: User, libraryElement: LibraryElement)
    fun returnBook(user: User, libraryElement: LibraryElement)
    fun checkBooksLoan(element: LibraryElement): List<User>?
    fun checkUserLoan(user: User): List<LibraryElement>?
}