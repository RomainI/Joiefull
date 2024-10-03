package fr.ilardi.joiefull.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.ilardi.joiefull.data.Repository
import fr.ilardi.joiefull.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val productRepository: Repository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getProductList()
    }

    // Fonction pour récupérer la liste des produits
    private fun getProductList() {
        viewModelScope.launch {
            _isLoading.value = true
            val productsList = productRepository.getProducts()
            _products.value = productsList
            _isLoading.value = false
        }
    }
}