package com.domain.usecases

import com.domain.models.Address
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class AddAddressUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<Address, Unit>() {

    override suspend fun run(params: Address): Unit {
        return repository.insertAddress(params)
    }
}