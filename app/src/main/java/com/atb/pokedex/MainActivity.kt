package com.atb.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.atb.pokedex.presentation.pokemon_detail.PokemonDetailScreen
import com.atb.pokedex.presentation.pokemon_list.PokemonListScreen
import com.atb.pokedex.presentation.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "pokemon_list") {
                        composable("pokemon_list") {
                            PokemonListScreen(navController = navController)
                        }
                        composable(
                            route ="pokemon_detail/{pokemon_name}/{pokemon_color}",
                            arguments = listOf(
                                navArgument(
                                    name = "pokemon_name"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "pokemon_color"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            PokemonDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
