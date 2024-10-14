package fr.ilardi.joiefull.ui

import android.content.Context
import android.content.Intent
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
    fun shareProduct(context: Context, product:Product) {
        // Create a sharing intent
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Regarde cet article : ${product.name} \n ${product.picture.url}")
        }

        // Use the context to start the intent and show the chooser for sharing
        context.startActivity(Intent.createChooser(shareIntent, "Partage cet article via"))
    }
}