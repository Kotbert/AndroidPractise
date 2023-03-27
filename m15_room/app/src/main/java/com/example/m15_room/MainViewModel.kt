package com.example.m15_room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val dictionaryDao: DictionaryDao = App.db.dictionaryDao()

    val allDictionary = dictionaryDao.getAllWord().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf<Dictionary>()
    )

    fun onAddBtn(word: String) {
        viewModelScope.launch(Dispatchers.IO) breaking@{
            allDictionary.value.forEach { wordInList ->
                if (wordInList.word.contains(word)) {
                    dictionaryDao.update(
                        Dictionary(wordInList.word, wordInList.count + 1)
                    )
                    return@breaking
                }
            }
            dictionaryDao.insert(
                Dictionary(word = word, count = 1)
            )
        }
    }

    fun onDeleteBtn() {
        viewModelScope.launch {
            allDictionary.value.forEach { dictionaryDao.delete(it) }
        }
    }
}