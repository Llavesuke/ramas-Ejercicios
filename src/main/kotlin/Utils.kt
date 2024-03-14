package org.pebiblioteca

/**
 * Where all the inputs are outputs are managed
 */
class Utils {
    companion object{
        /**
         * Print the message in the terminal
         *
         * @param message The message to be printed
         */
        fun showMessage(message: String){
            println(message)
        }

        /**
         * Ask the user a string
         *
         * @return The user input
         */
        fun stringInput(): String{
            val input = readln().trim()

            return input
        }

        /**
         * Ask a number to the user
         *
         * @throws NumberFormatException If the user write something different from an Int
         * @return The number introduced by the user
         */
        fun numberInput(): Int {
            var number: Int? = null

            while (number == null){
                try {
                    print("-> ")
                    val input = readln().toInt()
                    number = input
                } catch (e: NumberFormatException){
                    showMessage("ERROR: NOT VALID FORMAT")
                }
            }
            return number
        }

        /**
         * Show the elements of a list
         *
         * @param list The list to show his elements
         */
        fun showList(list: List<Book>){
            list.forEach { println("${it.title}, ${it.author}")}
        }
    }
}