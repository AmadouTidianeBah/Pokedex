package com.atb.pokedex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atb.pokedex.domain.model.Pokemon
import com.atb.pokedex.domain.repository.PokemonRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val repository: PokemonRepository
): PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize
            val data = repository.getPokemonList(offset, limit)
            val prevKey = if (offset == 0) null else offset - limit
            val nextKey = if (data.isNotEmpty()) offset + limit else null
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}