package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.database.dao.AddressDao
import com.data.database.dao.EmployeeDao
import com.data.database.models.AddressEntity
import com.data.database.models.EmployeeEntity

@Database(
    entities = [
        AddressEntity::class,
        EmployeeEntity::class,
    ], version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun employeesDao(): EmployeeDao
    abstract fun addressDao(): AddressDao
}