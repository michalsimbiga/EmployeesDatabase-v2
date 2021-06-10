package com.data.database.dao

import androidx.room.*
import com.data.database.models.EmployeeEntity
import com.data.database.models.EmployeeWithAddressesEntity
import com.domain.models.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeeEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEmployee(employee: EmployeeEntity)

    @Delete
    fun deleteEmployee(employee: EmployeeEntity)

    @Transaction
    @Query("SELECT * FROM EmployeeEntity")
    fun getAllEmployees(): Flow<List<EmployeeWithAddressesEntity>>
}