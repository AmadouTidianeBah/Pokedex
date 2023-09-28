@file:OptIn(ExperimentalFoundationApi::class)

package com.atb.pokedex.presentation.pokemon_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.atb.pokedex.R
import com.atb.pokedex.presentation.pokemon_detail.components.PokemonAbout
import com.atb.pokedex.presentation.pokemon_detail.components.PokemonInfo
import com.atb.pokedex.presentation.pokemon_detail.components.PokemonMoves
import com.atb.pokedex.presentation.pokemon_detail.components.PokemonStats

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val tabs = listOf("About", "Stats", "Moves")
    val pagerState = rememberPagerState { tabs.size }
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(
        key1 = pagerState.currentPage,
        key2 = pagerState.isScrollInProgress
    ) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 32.dp, bottomEnd = 32.dp
                    )
                )
                .background(Color(viewModel.pokemonColor)),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd)
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .alpha(.7f)
                        .clickable { navController.navigateUp() }
                )

                Text(
                    text = "#${state.pokemon?.id}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .alpha(.7f)
                )
            }
            AsyncImage(
                model = state.pokemon?.imgUrl,
                contentDescription = state.pokemon?.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.interrogation)
            )
        }

        state.pokemon?.let { PokemonInfo(name = it.name, types = it.type) }

        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(text = tab)
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) {index ->
                when(index) {
                    0 -> {
                        state.pokemon?.let {
                            PokemonAbout(
                                height = it.height.toString(),
                                weight = it.weight.toString(),
                                abilities = it.abilities
                            )
                        }
                    }
                    1 -> {
                        state.pokemon?.stats?.let { PokemonStats(stats = it) }
                    }
                    2 -> {
                        state.pokemon?.moves?.let { PokemonMoves(moves = it) }
                    }
                }
            }
        }
    }
}
