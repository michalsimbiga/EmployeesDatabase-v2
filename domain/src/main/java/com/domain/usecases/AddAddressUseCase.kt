package com.domain.usecases

import com.domain.models.Address
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class AddAddressUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<List<Address>, Unit>() {

    override suspend fun run(params: List<Address>): Unit {
        return repository.insertAddresses(params)
    }
}