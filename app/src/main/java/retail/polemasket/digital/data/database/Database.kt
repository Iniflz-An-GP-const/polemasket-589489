package retail.polemasket.digital.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import retail.polemasket.digital.data.dao.BookingDao
import retail.polemasket.digital.data.database.converter.Converters
import retail.polemasket.digital.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

