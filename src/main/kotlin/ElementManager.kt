package org.pebiblioteca

import java.util.UUID

open class ElementManager<T: LibraryElement> {
    private val elements = mutableMapOf<UUID?, T>()

    fun add(element: T) {
        elements[element.getId()] = element
    }

    fun removeElement(id: UUID) {
        elements.remove(id)
    }

    fun getElements(): List<T> {
        return elements.values.toList()
    }

    fun filterByCriterion(criterion: (T) -> Boolean): List<T> {
        return elements.values.filter(criterion)
    }
}