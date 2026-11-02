package retail.polemasket.digital

import android.app.Application
import retail.polemasket.digital.di.dataModule
import retail.polemasket.digital.di.dispatcherModule
import retail.polemasket.digital.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class RLADLApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@RLADLApplication)
            modules(appModules)
        }
    }
}
