package com.data.database.dao

import androidx.room.*
import com.data.database.models.EmployeeEntity

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(carrier: EmployeeEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEmployee(carrier: EmployeeEntity)

    @Delete
    fun deleteEmployee(carrier: EmployeeEntity)

    @Query("SELECT * FROM EmployeeEntity")
    fun getAllEmployees(): List<EmployeeEntity>
}