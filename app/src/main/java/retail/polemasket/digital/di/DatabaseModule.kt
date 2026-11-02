package retail.polemasket.digital.di

import androidx.room.Room
import retail.polemasket.digital.data.database.Database
import org.koin.dsl.module

private const val DB_NAME = "rladl_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<Database>().bookingDao() }
}