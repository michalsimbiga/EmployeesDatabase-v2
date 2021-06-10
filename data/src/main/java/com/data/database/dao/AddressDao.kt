package com.data.database.dao

import androidx.room.*
import com.data.database.models.AddressEntity

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(address: AddressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddresses(vararg addresses: AddressEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAddress(address: AddressEntity)

    @Delete
    fun deleteAddress(address: AddressEntity)

    @Query("SELECT * FROM AddressEntity")
    fun getAllAddresses(): List<AddressEntity>
}