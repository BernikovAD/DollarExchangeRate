package com.example.dollarexchangerate.utils

import androidx.lifecycle.SavedStateHandle

interface IViewModelState{
    /**
     * override if need save state in bundle
     */
    fun save(outState: SavedStateHandle) {
        // default implementation
    }

    /**
     * override if need restore state from bundle
     */
    fun restore(savedState: SavedStateHandle) : IViewModelState {
        // default implementation
        return this
    }
}