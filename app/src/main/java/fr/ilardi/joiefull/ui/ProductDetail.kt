package fr.ilardi.joiefull.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import fr.ilardi.joiefull.R
import fr.ilardi.joiefull.model.Product


//Compose used to display a product on a full screen (for smartphone) and on a part of the screen for tablet
@Composable
fun ProductDetail(
    viewModel: MainActivityViewModel,
    navController: NavController,
    product: Product,
    modifier: Modifier = Modifier
) {
    var rating by remember {
        mutableStateOf(1f)
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            ) {

                AsyncImage(
                    model = product.picture.url,
                    contentDescription = "photo of "+ product.picture.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop

                )
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(70.dp),

                    ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to main screen button",
                        tint = Color.Black
                    )
                }
                val context = LocalContext.current
                IconButton(

                    onClick = { viewModel.shareProduct(context, product) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = "Share button",
                        tint = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .size(70.dp, 32.dp)
                        .align(Alignment.BottomEnd)
                        .offset(
                            x = (-16).dp,
                            y = (-16).dp
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically // Aligner verticalement au centre
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Like counter",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Text(
                            text = product.likes.toString(),
                            modifier = Modifier
                                .weight(1f)
                                .offset(
                                    y = (-2).dp,
                                    x = 2.dp
                                ),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,

                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.star),
                    tint = Color(0xFFFFC700),
                    contentDescription = "Rate",
                )
                Text(
                    text = "4.6",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "${product.price} €",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (product.price != product.originalPrice)
                    Text(
                        text = "${product.originalPrice} €",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Right,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier
                    )
            }

            Text(text = product.picture.description)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzBNfBc8ZLnKfs7PR_RX20u2bxqIsq-Sa2xw&s"),
                    contentDescription = "avatar photo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.padding(8.dp))
                StarRatingBar(
                    maxStars = 5,
                    rating = rating,
                    onRatingChanged = {
                        rating = it
                    }
                )
            }
            val textState = remember { mutableStateOf("") }

            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Enter text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(16.dp),
            )

        }
    }
}