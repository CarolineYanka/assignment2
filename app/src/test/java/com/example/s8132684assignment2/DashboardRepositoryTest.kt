package com.example.s8132684assignment2.repository

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.data.Entity
import com.example.s8132684assignment2.network.ApiService
import io.mockk.coEvery
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class DashboardRepositoryTest {

    private lateinit var repository: DashboardRepository
    private lateinit var apiService: ApiService

    private val fakeEntities = listOf(
        Entity("Push Up", "Chest", "Bodyweight", "Medium", 200, "Do pushups"),
        Entity("Squat", "Legs", "Bodyweight", "Medium", 180, "Do squats")
    )

    @Before
    fun setup() {
        apiService = spyk() // mock the ApiService
        repository = DashboardRepository(apiService)
    }

    @Test
    fun `getDashboardData returns entities from ApiService`() = runTest {
        coEvery { apiService.getDashboardData() } returns DashboardResponse(fakeEntities,1)

        val result = repository.getDashboardData()

        assertEquals(fakeEntities, result.entities)
    }

    @Test(expected = Exception::class)
    fun `getDashboardData throws exception when ApiService fails`() = runTest {
        val exceptionMessage = "Network error"
        coEvery { apiService.getDashboardData() } throws Exception(exceptionMessage)

        repository.getDashboardData()
    }
}
