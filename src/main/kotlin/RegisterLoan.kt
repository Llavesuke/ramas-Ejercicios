package org.pebiblioteca

/**
 * Manage all the loans in the library
 *
 * @property currentLoans The users that are currently borrowing books
 * @property loanRecords All the loans made in the library
 */
class RegisterLoan {
    private val currentLoans = mutableMapOf<User,MutableList<Book>>()
    private val loanRecords = mutableMapOf<User,MutableList<Book>>()

    /**
     * Register a new loan in the system
     *
     * @param user The user that is getting the book
     * @param book The borrowed book
     *
     */
    fun registerLoan(user: User, book: Book){
        user.borrowBook(book)

        if (user !in currentLoans){
            currentLoans[user] = mutableListOf(book)

        } else {
            currentLoans[user]!!.add(book)
        }

        if (user !in loanRecords){
            loanRecords[user] = mutableListOf(book)
        } else{
            loanRecords[user]!!.add(book)
        }
    }

    /**
     * Register the return of a book
     *
     * @param user The user that is getting the book
     * @param book The borrowed book
     *
     */
    fun returnBook(user: User,book: Book){
        val borrowedList = user.getBorrowedList()
        when (book){
            in borrowedList -> {
                user.returnBook(book)
                currentLoans[user]!!.remove(book)
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
    fun checkBooksLoan(book: Book): List<User>? {
        return if (loanRecords.values.any { it.contains(book)} ){
            loanRecords.filterValues { it.contains(book) }.keys.toList()
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
    fun checkUserLoan(user: User): List<Book>? {
        return loanRecords[user]
    }
}