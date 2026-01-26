import android.app.Application
import com.andreicampigotto.githubusersapi.di.NetworkModule
import com.andreicampigotto.githubusersapi.di.PresentationModule
import com.andreicampigotto.githubusersapi.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                PresentationModule.module,
                RepositoryModule.module,
                NetworkModule.module,
            )
        }
    }
}