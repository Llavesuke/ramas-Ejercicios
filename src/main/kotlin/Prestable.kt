package org.pebiblioteca

interface Prestable {
    fun lend(user: User)
    fun returnElement(user: User)
}