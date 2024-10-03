package fr.ilardi.joiefull.data

import fr.ilardi.joiefull.model.Product
import fr.ilardi.joiefull.utils.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getProducts(): List<Product> {
        return try {
            val products = apiService.getProducts()
            println("Products retrieved: ${products.size}")
            products
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}