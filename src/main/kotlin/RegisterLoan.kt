package org.pebiblioteca

/**
 * Manage all the loans in the library
 *
 * @property currentLoans The users that are currently borrowing books
 * @property loanRecords All the loans made in the library
 */
class RegisterLoan: IRegisterLoan {
    private val currentLoans = mutableMapOf<User,MutableList<LibraryElement>>()
    private val loanRecords = mutableMapOf<User,MutableList<LibraryElement>>()

    /**
     * Register a new loan in the system
     *
     * @param user The user that is getting the book
     * @param book The borrowed book
     *
     */
    override fun registerLoan(user: User, element: LibraryElement){

        if (user !in currentLoans){
            currentLoans[user] = mutableListOf(element)

        } else {
            currentLoans[user]!!.add(element)
        }

        if (user !in loanRecords){
            loanRecords[user] = mutableListOf(element)
        } else{
            loanRecords[user]!!.add(element)
        }
    }

    /**
     * Register the return of a book
     *
     * @param user The user that is getting the book
     * @param book The borrowed book
     *
     */
    override fun returnBook(user: User,element: LibraryElement){
        val borrowedList = user.getBorrowedList()
        when (element){
            in borrowedList -> {
                currentLoans[user]!!.remove(element)
            }
            !in borrowedList -> {
                Utils.showMessage("You don't have this book!")
            }
        }
    }

    /**
     * Return a list with the users that borrowed a specific book from the system
     *
     * @param book The book to filter in the loan records
     * @return A list with users
     */
    override fun checkBooksLoan(libraryElement: LibraryElement): List<User>? {
        return if (loanRecords.values.any { it.contains(libraryElement)} ){
            loanRecords.filterValues { it.contains(libraryElement) }.keys.toList()
        } else {
            null
        }
    }

    /**
     * Return a list with all the loans made by the user
     *
     * @param user The user to look for
     *
     * @return A list with the books loaned by the user. If the user is not find return a empty list
     */
    override fun checkUserLoan(user: User): List<LibraryElement>? {
        return loanRecords[user]
    }
}