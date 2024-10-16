package fr.ilardi.joiefull.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fr.ilardi.joiefull.model.Product

@Composable
fun MainScreen(viewModel: MainActivityViewModel, navHostController: NavHostController) {
    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            BoxWithConstraints {
                val screenWidth = maxWidth
                //Verify if the device is a smartphone or a tablet and adapt the display
                if (screenWidth < 600.dp) {
                    SmartphoneMainScreen(products, navHostController)
                } else {
                    TabletMainScreen(products, navHostController, viewModel)
                }
            }
        }
    }
}


@Composable
fun SmartphoneMainScreen(
    productList: List<Product>,
    navHostController: NavHostController,
) {

    val groupedProducts = productList.groupBy { it.category }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        groupedProducts.forEach { (category, productList) ->
            item {
                Text(
                    text = category.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                LazyRow(modifier = Modifier.fillMaxSize()) {
                    items(productList) { product ->
                        ProductItem(
                            product = product,
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    navHostController.navigate("detail_item/${product.id}")
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TabletMainScreen(
    productList: List<Product>,
    navHostController: NavHostController,
    viewModel: MainActivityViewModel
) {
    var itemSelected by remember {
        mutableStateOf(0)
    }
    val groupedProducts = productList.groupBy { it.category }
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
            contentPadding = PaddingValues(16.dp)
        )

        {
            groupedProducts.forEach { (category, productList) ->
                item {
                    Text(
                        text = category.replaceFirstChar { it.uppercase() },
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(productList) { product ->
                            ProductItem(
                                product = product,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {
                                        itemSelected = product.id
                                    }
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            ProductDetail(
                viewModel,
                navHostController,
                productList.get(itemSelected),
                Modifier
            )

        }
    }

}