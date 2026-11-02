package retail.polemasket.digital.di

import retail.polemasket.digital.ui.viewmodel.BookingViewModel
import retail.polemasket.digital.ui.viewmodel.CheckoutViewModel
import retail.polemasket.digital.ui.viewmodel.OnboardingViewModel
import retail.polemasket.digital.ui.viewmodel.ServiceDetailsViewModel
import retail.polemasket.digital.ui.viewmodel.ServiceViewModel
import retail.polemasket.digital.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}