package org.pebiblioteca

/**
 * This class manage the library and his functions
 * @property bookCatalog List of book that are from the library
 * @property lendingList List of books that are lend
 */
class LibraryManager {
    val bookCatalog = mutableListOf<Book>()
    val lendingList = mutableListOf<Book>()

    val loanRegister = RegisterLoan()

    /**
     * Add a book to the library catalog
     *
     * @param book The book to be added
     */
    fun addBookToCatalog(book: Book){
        book.modifyID(LibraryUtils.generateUniqueIdentifier())
        bookCatalog.add(book)
    }

    /**
     * Remove a book from the library catalog
     *
     * @param book The book to be removed from the catalog
     */
    fun removeBookFromCatalog(book: Book){
        bookCatalog.remove(book)
    }

    /**
     * Register a book in the borrowed books
     *
     * @param book The book to be borrowed
     * @param user The user that is taking the book
     */
    fun registerLoan(book: Book,user: User){
        val state = book.getBookState()
        val title = book.getTitle()
        when (state){
            BookState.AVAIlABLE -> {
                Utils.showMessage("There is your ${title}")
                lendingList.add(book)
                loanRegister.registerLoan(user, book)
                book.modifyState(BookState.BORROWED)

            }
            BookState.BORROWED -> Utils.showMessage("The book is already borrowed!")
        }
    }

    /**
     * Remove a book from the list
     *
     * @param book The book that is returning
     * @param user The user returning the book
     *
     */
    fun returnBook(book: Book, user: User){
        val state = book.getBookState()
        val borrowedList = user.getBorrowedList()

        when {
            state == BookState.BORROWED && book in borrowedList -> {
                Utils.showMessage("Thanks for returning the book on date!")
                lendingList.remove(book)
                loanRegister.returnBook(user, book)
                book.modifyState(BookState.AVAIlABLE)
            }
            state == BookState.AVAIlABLE -> {
                Utils.showMessage("This book is not loaned!")
            }

            book !in borrowedList -> {
                Utils.showMessage("You don't loaned this book")
            }
        }
    }

    /**
     * Check the availability of the book
     *
     * @param book The book to look for
     *
     */
    fun checkAvailability(book: Book){
        val state = book.getBookState()

        when (state){
            BookState.AVAIlABLE -> Utils.showMessage("This book is available to loan")
            BookState.BORROWED -> Utils.showMessage("This book is already borrowed")
        }
    }

    /**
     * Check the books from his state
     */
    fun checkBooks(): List<Book> {
        Utils.showMessage("Book List\n 1.All\n 2.Available\n 3.Borrowed")
        val selection = Utils.numberInput()

        return when (selection){
            1 -> this.bookCatalog
            2 -> this.bookCatalog.filter { it.getBookState() == BookState.AVAIlABLE }
            3 -> this.bookCatalog.filter { it.getBookState() == BookState.BORROWED }
            else -> this.bookCatalog //If is not the valid option, display all the catalog
        }
    }

    /**
     * The list of all the loans made by a user
     *
     * @param user The user to look his loans
     *
     */
    fun showUserLoans(user: User): List<Book>?{
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
    fun showBooksLoans(book: Book): List<User>{
        Utils.showMessage("USER WHO LOANED ${book.getTitle()}")

        val userList = loanRegister.checkBooksLoan(book)

        return if (userList == null){
            Utils.showMessage("None loaned this book yet")
            emptyList()
        } else{
             userList

        }
    }
}