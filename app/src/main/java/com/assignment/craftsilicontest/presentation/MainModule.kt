package com.assignment.craftsilicontest.presentation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule  = module {
        singleOf(::MainPresenter)
    }
