package com.assignment.craftsilicontest.presentation.search

import com.assignment.craftsilicontest.domain.models.AppUIStates
import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.presentation.MainContract
import com.assignment.craftsilicontest.presentation.MainPresenter
import com.assignment.craftsilicontest.presentation.ViewModel.LoadingViewModel

class SearchHandler(
    private val loadingViewModel: LoadingViewModel,
    private val mainPresenter: MainPresenter,
    private val onCitiesAvailable: (List<City>) -> Unit) : MainContract.SearchCityView {

    fun init() {
        mainPresenter.registerSearchCityContract(this)
    }

    override fun showSearchCityLce(appUIStates: AppUIStates) {
        loadingViewModel.switchScreenUIState(appUIStates)
    }

    override fun showCities(cities: List<City>) {
        onCitiesAvailable(cities)
    }


}
