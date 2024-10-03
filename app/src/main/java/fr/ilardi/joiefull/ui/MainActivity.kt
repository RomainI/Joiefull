package fr.ilardi.joiefull.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.ilardi.joiefull.model.Product
import fr.ilardi.joiefull.ui.theme.JoiefullTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            JoiefullTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}


@Composable
fun MainScreen (viewModel: MainActivityViewModel, modifier: Modifier = Modifier){
    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Surface(modifier = modifier) {
        if (isLoading) {
            // Afficher l'indicateur de chargement
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Afficher la liste des produits dans une LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Log.d("LazyColumn", products.size.toString()+" produits")
                items(products) { product ->
                    ProductItem(product = product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Log.d("Column", "Colonne "+product.name)
        Text(text = "Nom : ${product.name}")
        Text(text = "Catégorie : ${product.category}")
        Text(text = "Prix : ${product.price}€")
        Text(text = "Likes : ${product.likes}")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JoiefullTheme {
        Greeting("Android")
    }
}