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

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {

        Column {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {

                AsyncImage(
                    model = product.picture.url,
                    contentDescription = product.picture.description,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .size(36.dp, 16.dp)
                        .align(Alignment.BottomEnd)
                        .offset(
                            x = (-8).dp,
                            y = (-8).dp
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {


                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Like",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = product.likes.toString(),
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .offset(y = (-4).dp, x = (2).dp)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .width(150.dp)
                    .padding(top = 4.dp),
            ) {
                Text(
                    text = product.name,
                    modifier = Modifier.width(150.dp).weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
                Icon(
                    painter = painterResource(R.drawable.star),
                    tint = Color(0xFFFFC700),
                    contentDescription = "Rate",
                )
                Text(
                    text = "4.6",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                )
            }
            // Row to display price and original price
            Row(
                modifier = Modifier
                    .width(150.dp)
                    .padding(top = 4.dp),
            ) {
                Text(
                    text = "${product.price.toInt()}€",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    fontSize = 12.sp,

                    )

                if (product.price != product.originalPrice)
                    Text(
                        text = "${product.originalPrice.toInt()}€",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = TextAlign.Right,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 12.sp,
                    )
            }
        }
    }
}


