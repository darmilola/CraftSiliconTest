package com.assignment.craftsilicontest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.assignment.craftsilicontest.ui.theme.CraftSiliconTestTheme
import com.assignment.craftsilicontest.widgets.SearchBarWidget
import com.assignment.craftsilicontest.widgets.SearchItem
import kotlinx.coroutines.runBlocking

class SearchScreen(): Screen {
    @Composable
    override fun Content() {
        val weeklyForecastList = arrayListOf<Int>()
        weeklyForecastList.add(1)
        weeklyForecastList.add(2)
        weeklyForecastList.add(3)
        weeklyForecastList.add(4)
        weeklyForecastList.add(5)

        val navigator = LocalNavigator.currentOrThrow
        CraftSiliconTestTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(modifier = Modifier.fillMaxSize().padding(innerPadding).background(color = Color.Yellow)) {
                    Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
                        SearchBar(onValueChange = {}, onBackPressed = {
                            navigator.pop()
                        })
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(start = 20.dp, end = 20.dp),
                        contentPadding = PaddingValues(top = 6.dp, bottom = 6.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        userScrollEnabled = true
                    ) {
                        items(weeklyForecastList.size) { it ->

                            SearchItem()

                        }
                    }
                }

            }
        }
    }

    @Composable
    fun SearchBar(placeholderText: String = "Search City", onValueChange: (String) -> Unit, onBackPressed:() -> Unit){
        SearchBarWidget(placeholderText = placeholderText, iconSize = 26, onBackPressed = {
            onBackPressed()
        }){
            onValueChange(it)
        }

    }
}