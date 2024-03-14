package org.pebiblioteca

/**
 * This class manage the library and his functions
 * @property bookCatalog List of book that are from the library
 * @property lendingList List of books that are lend
 */
class LibraryManager {
    val bookCatalog = mutableListOf<Book>()
    val lendingList = mutableListOf<Book>()

    /**
     * Add a book to the library catalog
     *
     * @param book The book to be added
     */
    fun addBookToCatalog(book: Book){
        book.ID = LibraryUtils.generateUniqueIdentifier()
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
     */
    fun registerLoan(book: Book){
        when (book.state){
            BookState.AVAIlABLE -> {
                Utils.showMessage("There is your ${book.title}")
                lendingList.add(book)
                book.state = BookState.BORROWED

            }
            BookState.BORROWED -> Utils.showMessage("The book is already borrowed!")
        }
    }

    /**
     * Remove a book from the list
     */
    fun returnBook(book: Book){
        when (book.state){
            BookState.BORROWED -> {
                Utils.showMessage("Thanks for returning the book on date!")
                lendingList.remove(book)
                book.state = BookState.AVAIlABLE
            }
            BookState.AVAIlABLE -> {
                Utils.showMessage("This book is not loaned!")
            }
        }
    }

    fun checkAvailability(book: Book){
        when (book.state){
            BookState.AVAIlABLE -> Utils.showMessage("This book is available to loan")
            BookState.BORROWED -> Utils.showMessage("This book is already borrowed")
        }
    }

    fun checkBooks(): List<Book> {
        Utils.showMessage("Book List\n 1.All\n 2.Available\n 3.Borrowed")
        val selection = Utils.numberInput()

        return when (selection){
            1 -> this.bookCatalog
            2 -> this.bookCatalog.filter { it.state == BookState.AVAIlABLE }
            3 -> this.bookCatalog.filter { it.state == BookState.BORROWED }
            else -> this.bookCatalog //If is not the valid option, display all the catalog
        }
    }
}