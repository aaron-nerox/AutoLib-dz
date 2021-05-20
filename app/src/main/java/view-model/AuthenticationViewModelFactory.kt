package `view-model`

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.Repository

class AuthenticationViewModelFactory (private val  repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(repository) as T
    }

}