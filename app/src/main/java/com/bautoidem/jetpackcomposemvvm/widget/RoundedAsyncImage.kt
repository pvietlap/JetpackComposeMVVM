package com.bautoidem.jetpackcomposemvvm.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.bautoidem.jetpackcomposemvvm.R

@Composable
fun RoundedAsyncImage(
    imageUrl: String,
    shape: Shape,
    resDefault: Int = R.drawable.img_error,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    val rememberResource = rememberAsyncImagePainter(model = resDefault)
    val request = remember {
        mutableStateOf<ImageRequest?>(null)
    }

    request.value = ImageRequest.Builder(LocalContext.current.applicationContext)
        .data(imageUrl)
        .crossfade(true)
        .diskCacheKey(imageUrl)
        .build()

    AsyncImage(
        model = request.value,
        placeholder = rememberResource,
        contentDescription = "",
        error = rememberResource,
        onSuccess = {
        },
        modifier = modifier
            .clip(shape),
        onError = {
        },
        contentScale = contentScale
    )
}