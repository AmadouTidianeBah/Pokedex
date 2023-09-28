package com.atb.pokedex.presentation.pokemon_list.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.atb.pokedex.R
import com.atb.pokedex.domain.model.Pokemon

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onPokemonClick: (String, Int) -> Unit,
    pokemonColor: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        ),
        label = "rotation"
    )
    var dominantColor by rememberSaveable {
        mutableIntStateOf(-1)
    }
    val backgroundColorAnimation = animateColorAsState(
        targetValue = if (dominantColor != -1) Color(dominantColor) else MaterialTheme.colorScheme.primary,
        label = "background color animation",
        animationSpec = tween(durationMillis = 300)
    )

    LaunchedEffect(key1 = true) {
        val bitmap = convertImageUrlToBitmap(imageUrl = pokemon.imgUrl, context = context)
        if (bitmap != null) {
            dominantColor = Palette.from(bitmap).generate().dominantSwatch?.rgb ?: -1
            pokemonColor(dominantColor)
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp, 0.dp, 20.dp, 0.dp))
            .background(backgroundColorAnimation.value)
            .padding(12.dp)
            .clickable { onPokemonClick(pokemon.name, dominantColor) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            pokemon.type.forEach {type ->
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = type, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null,
                alpha = .5f,
                modifier = Modifier
                    .size(150.dp)
                    .rotate(rotateAnimation)
            )

            AsyncImage(
                model = pokemon.imgUrl,
                placeholder = painterResource(id = R.drawable.interrogation),
                contentScale = ContentScale.Crop,
                contentDescription = pokemon.name,
                onLoading = {

                },
                modifier = Modifier.size(120.dp)
            )
        }
    }
}

private suspend fun convertImageUrlToBitmap(
    imageUrl: String,
    context: Context
): Bitmap? {
    val loader = ImageLoader(context = context)
    val request = ImageRequest.Builder(context = context)
        .data(imageUrl)
        .allowHardware(false)
        .build()
    val imageResult = loader.execute(request = request)
    return if (imageResult is SuccessResult) {
        (imageResult.drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
    } else {
        null
    }
}
