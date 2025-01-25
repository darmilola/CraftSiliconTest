package com.assignment.craftsilicontest.presentation.search

import android.os.Parcelable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.assignment.craftsilicontest.component.IndeterminateCircularProgressBar
import com.assignment.craftsilicontest.domain.models.AppUIStates
import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.presentation.MainContract
import com.assignment.craftsilicontest.presentation.MainPresenter
import com.assignment.craftsilicontest.presentation.ViewModel.LoadingViewModel
import com.assignment.craftsilicontest.ui.theme.CraftSiliconTestTheme
import com.assignment.craftsilicontest.widgets.SearchBarWidget
import com.assignment.craftsilicontest.widgets.CityItem
import com.assignment.craftsilicontest.widgets.ErrorOccurredWidget
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Transient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Parcelize
class SearchScreen: Screen, Parcelable, KoinComponent{
    @Transient private val mainPresenter: MainPresenter by inject()
    @Transient private var loadingViewModel: LoadingViewModel? = null

    @Composable
    override fun Content() {

       var searchString = ""

       loadingViewModel = viewModel()
        val cityListState = remember { mutableStateOf(listOf<City>()) }
        val searchHandler = SearchHandler(loadingViewModel!!,mainPresenter, onCitiesAvailable = {
            cityListState.value = it
        })
        searchHandler.init()


        val uiState = loadingViewModel!!.uiStateInfo.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        CraftSiliconTestTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(modifier = Modifier.fillMaxSize().padding(innerPadding).background(color = Color.Yellow)) {
                    Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
                        SearchBar(onValueChange = {
                            searchString = it
                            if (it.isNotEmpty()) {
                                mainPresenter.searchCity(it)
                            }
                        }, onBackPressed = {
                            navigator.pop()
                        })
                    }

                    if (uiState.value.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                                .padding(top = 40.dp, start = 50.dp, end = 50.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            IndeterminateCircularProgressBar()
                        }
                    } else if (uiState.value.isFailed) {
                        Box(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                                .padding(top = 40.dp, start = 50.dp, end = 50.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorOccurredWidget(
                                uiState.value.errorMessage,
                                onRetryClicked = {
                                    if (searchString.isNotEmpty()) {
                                        mainPresenter.searchCity(searchString)
                                    }
                                })
                        }
                    } else if (uiState.value.isSuccess) {

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                                .padding(start = 20.dp, end = 20.dp),
                            contentPadding = PaddingValues(top = 6.dp, bottom = 6.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            userScrollEnabled = true
                        ) {
                            items(cityListState.value.size) { it ->
                                CityItem(cityListState.value[it], onCitySelected = {})
                            }
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