package com.assignment.craftsilicontest.presentation

import android.app.Activity
import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.assignment.craftsilicontest.R
import com.assignment.craftsilicontest.component.ButtonComponent
import com.assignment.craftsilicontest.component.ImageComponent
import com.assignment.craftsilicontest.component.IndeterminateCircularProgressBar
import com.assignment.craftsilicontest.component.TextComponent
import com.assignment.craftsilicontest.domain.models.AppUIStates
import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Forecast
import com.assignment.craftsilicontest.domain.models.ForecastResponse
import com.assignment.craftsilicontest.presentation.ViewModel.LoadingViewModel
import com.assignment.craftsilicontest.presentation.ViewModel.MainViewModel
import com.assignment.craftsilicontest.presentation.search.SearchHandler
import com.assignment.craftsilicontest.presentation.search.SearchScreen
import com.assignment.craftsilicontest.room.AppDatabase
import com.assignment.craftsilicontest.ui.theme.CraftSiliconTestTheme
import com.assignment.craftsilicontest.widgets.ErrorOccurredWidget
import com.assignment.craftsilicontest.widgets.ForecastWidget
import com.assignment.craftsilicontest.widgets.WeatherWidget
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Transient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.SimpleDateFormat
import java.util.Locale


@Parcelize
class MainScreen: Screen, Parcelable, KoinComponent {

    @Transient private val mainPresenter: MainPresenter by inject()
    @Transient private var mainLoadingViewModel: LoadingViewModel? = null
    @Transient private var forecastLoadingViewModel: LoadingViewModel? = null
    @Transient private var mainViewModel: MainViewModel? = null


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val activity = LocalContext.current as Activity
        val appDatabase = AppDatabase.getDatabase(activity)
        val scope = rememberCoroutineScope()

        mainViewModel = viewModel()
        mainLoadingViewModel = viewModel()
        forecastLoadingViewModel = viewModel()

        val selectedCoordinate = mainViewModel!!.selectedCoordinate.collectAsState()
        val shouldRefresh = mainViewModel!!.shouldRefresh.collectAsState()
        val selectedLatitude = selectedCoordinate.value.latitude
        val selectedLongitude = selectedCoordinate.value.longitude
        val mainUiState = mainLoadingViewModel!!.uiStateInfo.collectAsState()
        val forecastUiState = forecastLoadingViewModel!!.uiStateInfo.collectAsState()

        val mainWeather = remember { mutableStateOf(CityWeather()) }
        val forecasts = remember { mutableStateOf(ForecastResponse()) }

        val mainHandler = MainHandler(mainLoadingViewModel!!,forecastLoadingViewModel!!,mainPresenter,
            onMainWeatherAvailable = {
                mainWeather.value = it
                scope.launch {
                    appDatabase.cityWeatherDao().insert(mainWeather.value)
                }

           }, onForecastAvailable = {
               forecasts.value = it
                scope.launch {
                    appDatabase.forecastDao().insert(forecasts.value)
                }
           })

        mainHandler.init()

        LaunchedEffect(true) {
            scope.launch {
                val cityWeather = appDatabase.cityWeatherDao().getCityWeather()
                val forecast = appDatabase.forecastDao().getWeatherForecast()

                if (shouldRefresh.value){
                    mainViewModel!!.setShouldRefresh(false)
                    if (selectedLatitude != 0.0 && selectedLongitude != 0.0){
                        appDatabase.cityWeatherDao().deleteWeatherEntry()
                        appDatabase.forecastDao().deleteWeatherForecast()
                        mainPresenter.getWeather(lat = selectedLatitude, lon = selectedLongitude)
                        mainPresenter.getForecast(lat = selectedLatitude, lon = selectedLongitude)
                    }
                }
                else if (cityWeather.isNotEmpty() && forecast.isNotEmpty()) {
                        mainWeather.value = cityWeather[0]
                        forecasts.value = forecast[0]
                        mainViewModel!!.setSelectedCoordinate(mainWeather.value.coordinate!!)
                        forecastLoadingViewModel!!.switchScreenUIState(AppUIStates(isSuccess = true))
                        mainLoadingViewModel!!.switchScreenUIState(AppUIStates(isSuccess = true))
                    }

            }
        }


        CraftSiliconTestTheme {
            Scaffold(modifier = Modifier.fillMaxSize().background(color = Color.Yellow)) { innerPadding ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = Color.Yellow)) {


                    if (selectedLatitude == 0.0 && selectedLongitude == 0.0){
                        // Select City
                        SelectCity {
                            val searchScreen = SearchScreen()
                            searchScreen.setMainViewModel(mainViewModel!!)
                            navigator.push(searchScreen)
                        }
                    }
                    if (mainUiState.value.isLoading) {
                        // loading
                        Loading()
                    }
                    else if (mainUiState.value.isFailed) {
                        // Error Occurred
                        ErrorOccurred(errorMessage = mainUiState.value.errorMessage) {
                            scope.launch {
                                appDatabase.cityWeatherDao().deleteWeatherEntry()
                                appDatabase.forecastDao().deleteWeatherForecast()

                                if (selectedLatitude != 0.0 && selectedLongitude != 0.0){
                                    mainPresenter.getWeather(lat = selectedLatitude, lon = selectedLongitude)
                                    mainPresenter.getForecast(lat = selectedLatitude, lon = selectedLongitude)
                                }
                            }
                        }
                    }
                    else if (mainUiState.value.isSuccess) {
                        //Success show content
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(color = Color.Yellow)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .background(color = Color.Yellow),
                                contentAlignment = Alignment.Center
                            ) {
                                ImageComponent(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            val searchScreen = SearchScreen()
                                            searchScreen.setMainViewModel(mainViewModel!!)
                                            navigator.push(searchScreen)
                                        },
                                    imageRes = R.drawable.search_icon,
                                    colorFilter = ColorFilter.tint(Color.Black)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(3f)
                                    .background(color = Color.Yellow),
                                contentAlignment = Alignment.Center
                            ) {
                                TextComponent(
                                    text = mainWeather.value.cityName,
                                    fontSize = 30,
                                    textStyle = MaterialTheme.typography.titleLarge,
                                    textColor = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .clickable {
                                        scope.launch {
                                            appDatabase.cityWeatherDao().deleteWeatherEntry()
                                            appDatabase.forecastDao().deleteWeatherForecast()

                                            if (selectedLatitude != 0.0 && selectedLongitude != 0.0){
                                                mainPresenter.getWeather(lat = selectedLatitude, lon = selectedLongitude)
                                                mainPresenter.getForecast(lat = selectedLatitude, lon = selectedLongitude)
                                            }
                                        }
                                    }
                                    .background(color = Color.Yellow), contentAlignment = Alignment.Center){
                                TextComponent(
                                    text = "Refresh",
                                    fontSize = 20,
                                    textStyle = MaterialTheme.typography.titleSmall,
                                    textColor = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }



                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3.5f)
                                .background(color = Color.Red)
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1.5f)
                                    .background(color = Color.Green)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .background(color = Color.Yellow),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .clip(CircleShape)
                                            .background(color = Color.Black)
                                            .padding(
                                                start = 20.dp,
                                                end = 20.dp,
                                                top = 8.dp,
                                                bottom = 8.dp
                                            )
                                    ) {
                                        val simpleDateFormat = SimpleDateFormat("dd MMMM", Locale.ENGLISH)
                                        TextComponent(
                                            text = simpleDateFormat.format(mainWeather.value.date * 1000L),
                                            fontSize = 16,
                                            textStyle = MaterialTheme.typography.titleSmall,
                                            textColor = Color.White,
                                            textAlign = TextAlign.Start,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .background(color = Color.Yellow),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (mainWeather.value.weather != null) {
                                        TextComponent(
                                            text = mainWeather.value.weather!![0].main,
                                            fontSize = 20,
                                            textStyle = MaterialTheme.typography.titleSmall,
                                            textColor = Color.Black,
                                            textAlign = TextAlign.Start,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2.5f)
                                    .background(color = Color.Yellow),
                                contentAlignment = Alignment.Center
                            ) {
                                TextComponent(
                                    text = mainWeather.value.mainForecast?.temperature.toString()+"°",
                                    fontSize = 100,
                                    textStyle = MaterialTheme.typography.titleMedium,
                                    textColor = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Normal
                                )
                            }

                        }


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2.3f)
                                .background(color = Color.Yellow)
                                .padding(start = 30.dp, end = 30.dp)
                        ) {

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f), contentAlignment = Alignment.Center) {
                                TextComponent(
                                    text = "Daily Summary",
                                    fontSize = 20,
                                    textStyle = MaterialTheme.typography.titleLarge,
                                    textColor = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .weight(4f)) {
                                WeatherWidget(mainWeather.value)
                            }

                        }


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2.2f)
                                .background(color = Color.Yellow)
                                .padding(start = 30.dp, end = 30.dp, top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                TextComponent(
                                    text = "Weekly forecast",
                                    fontSize = 20,
                                    textStyle = MaterialTheme.typography.titleLarge,
                                    textColor = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(4f)
                                    .background(color = Color.Yellow)
                            ) {
                                if (forecastUiState.value.isLoading) {
                                    // Loading Forecast
                                    Loading()
                                }
                                else if (forecastUiState.value.isFailed) {
                                    ErrorOccurred(errorMessage = mainUiState.value.errorMessage) {
                                        scope.launch {
                                            appDatabase.cityWeatherDao().deleteWeatherEntry()
                                            appDatabase.forecastDao().deleteWeatherForecast()

                                            if (selectedLatitude != 0.0 && selectedLongitude != 0.0){
                                                mainPresenter.getWeather(lat = selectedLatitude, lon = selectedLongitude)
                                                mainPresenter.getForecast(lat = selectedLatitude, lon = selectedLongitude)
                                            }
                                        }
                                    }
                                }
                                else if (forecastUiState.value.isSuccess) {
                                    if (forecasts.value.forecasts != null) {
                                        LazyRow(
                                            modifier = Modifier
                                                .padding(top = 10.dp)
                                                .fillMaxSize()
                                                .background(color = Color.Yellow),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            items(items = forecasts.value.forecasts!!) { item ->
                                                ForecastWidget(item)
                                            }
                                        }
                                    }
                                }
                            }
                        }


                    }
                }

            }
        }

    }

    @Composable
   private fun SelectCity(onSelectCityClicked:() -> Unit){
        val columnModifier = Modifier
            .fillMaxSize()
        Column(
            modifier = columnModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment  = Alignment.CenterHorizontally,
        ) {

            ImageComponent(
                    modifier = Modifier
                        .size(150.dp),
                    imageRes = R.drawable.city_icon,
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )

            val buttonStyle = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(0.60f)
                    .background(color = Color.Transparent)
                    .height(45.dp)

                ButtonComponent(
                    modifier = buttonStyle,
                    buttonText = "Select City",
                    borderStroke = BorderStroke(2.dp, color = Color.Black),
                    fontSize = 16,
                    shape = CircleShape,
                    textColor = Color.DarkGray,
                    style = MaterialTheme.typography.titleLarge,
                ) {
                    onSelectCityClicked()
                }
        }
    }

    @Composable
    private fun Loading(){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 40.dp, start = 50.dp, end = 50.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            IndeterminateCircularProgressBar()
        }
    }

    @Composable
    private fun ErrorOccurred(errorMessage: String, onRetryClicked:()-> Unit){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 40.dp, start = 50.dp, end = 50.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            ErrorOccurredWidget(
                errorMessage,
                onRetryClicked = {
                   onRetryClicked()
                })
        }
    }

}