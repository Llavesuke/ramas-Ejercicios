package org.pebiblioteca

import java.util.*

abstract class LibraryElement(private var ID: UUID?,
                              private val title: String,
                              private val author: String) {

    private var state: State = State.AVAIlABLE

    init {
        ID = LibraryUtils.generateUniqueIdentifier()
    }
    fun getId(): UUID?{
        return this.ID
    }

    fun getTitle(): String{
        return this.title
    }
    fun getBookState(): State{
        return this.state
    }

    fun modifyState(newState: State){
        this.state = newState
    }

    fun modifyID(newID: UUID){
        this.ID = newID
    }

}