package com.example.s8132684assignment2.ui.dashboard

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.data.Entity
import com.example.s8132684assignment2.repository.DashboardRepository
import io.mockk.coEvery
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var repository: DashboardRepository

    private val testDispatcher = StandardTestDispatcher()
    private val fakeEntities = listOf(
        Entity("Push Up", "Chest", "Bodyweight", "Medium", 200, "Do pushups"),
        Entity("Squat", "Legs", "Bodyweight", "Medium", 180, "Do squats")
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // replace Main dispatcher
        repository = spyk(DashboardRepository(spyk())) // spyk repository
        viewModel = spyk(DashboardViewModel(repository))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset Main dispatcher
    }

    @Test
    fun `loadDashboardData updates entities on success`() = runTest {
        coEvery { repository.getDashboardData() } returns DashboardResponse(fakeEntities,1)

        viewModel.loadDashboardData()
        testDispatcher.scheduler.advanceUntilIdle() // run coroutine

        assertEquals(fakeEntities, viewModel.entities.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun `loadDashboardData sets error on exception`() = runTest {
        val exceptionMessage = "Network error"
        coEvery { repository.getDashboardData() } throws Exception(exceptionMessage)

        viewModel.loadDashboardData()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(emptyList<Entity>(), viewModel.entities.value)
        assertEquals(exceptionMessage, viewModel.error.value)
    }
}
