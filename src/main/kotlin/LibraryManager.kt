package org.pebiblioteca

import java.util.UUID

/**
 * This class manage the library and his functions
 * @property bookCatalog List of book that are from the library
 * @property lendingList List of books that are lend
 */
class LibraryManager(private val loanRegister: IRegisterLoan, private val catalog: Catalog) {
    val bookCatalog = mutableListOf<LibraryElement>()
    val lendingList = mutableListOf<LibraryElement>()



    /**
     * Lend a book
     *
     * @param user The user that takes the element
     * @param ID The id of the element she/he takes
     *
     */
    fun lendElement(user: User, ID: UUID){
        val element = catalog.getElements().find { it.getId() == ID }
        if (element is Prestable && element.getBookState() == State.AVAIlABLE ){
            element.lend(user)
            loanRegister.registerLoan(user, element)
        } else{
            Utils.showMessage("We don't have this book")
        }

    }

    /**
     * Remove a book from the library catalog
     *
     * @param book The book to be removed from the catalog
     */
    fun giveElementBack(user: User, ID: UUID){
        val element = catalog.getElements().find { it.getId() == ID }
        if (element is Prestable){
            loanRegister.returnBook(user, element)
            element.returnElement(user)
        } else {
            Utils.showMessage("You don't have it")
        }
    }

    /**
     * The list of all the loans made by a user
     *
     * @param user The user to look his loans
     *
     */
    fun showUserLoans(user: User): List<LibraryElement>?{
        Utils.showMessage("BOOKS LOANED BY ${user.getUserName()}")

        val bookList = loanRegister.checkUserLoan(user)

        return if (bookList == null){
            Utils.showMessage("This user doesn't loan any book yet")
            null
        } else {
            bookList
        }
    }

    /**
     * The list of all the loans of a book
     *
     * @param book A book to look the times it was borrowed
     */
    fun showBooksLoans(element: LibraryElement): List<User>{
        Utils.showMessage("USER WHO LOANED ${element.getTitle()}")

        val userList = loanRegister.checkBooksLoan(element)

        return if (userList == null){
            Utils.showMessage("None loaned this book yet")
            emptyList()
        } else{
             userList

        }
    }
}

