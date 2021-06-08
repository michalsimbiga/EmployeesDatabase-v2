package com.data.database.dao

import androidx.room.*
import com.data.database.models.EmployeeEntity

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeeEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEmployee(employee: EmployeeEntity)

    @Delete
    fun deleteEmployee(employee: EmployeeEntity)

    @Query("SELECT * FROM EmployeeEntity")
    fun getAllEmployees(): List<EmployeeEntity>
}