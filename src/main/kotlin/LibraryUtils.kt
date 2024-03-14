package org.pebiblioteca

import java.util.UUID

/**
 * Class that contains the utils that helps with library functions
 */
class LibraryUtils {
    companion object {
        /**
         * It generates a unique identifier
         */
        fun generateUniqueIdentifier(): UUID{
            val id = UUID.randomUUID()

            return id
        }
    }
}