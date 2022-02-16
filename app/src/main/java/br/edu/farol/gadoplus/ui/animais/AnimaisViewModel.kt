package br.edu.farol.gadoplus.ui.animais

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.edu.farol.gadoplus.model.Animal
import br.edu.farol.gadoplus.storage.database.repository.AnimalRepository

class AnimaisViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AnimalRepository = AnimalRepository(application)
    val all: LiveData<List<Animal>> = repository.all

    fun insert(animal: Animal?) {
        repository.insert(animal)
    }

    fun update(animal: Animal?) {
        repository.update(animal)
    }

    fun delete(animal: Animal?) {
        repository.delete(animal)
    }

    fun getById(id: Int): Animal {
        return repository.getById(id)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

}