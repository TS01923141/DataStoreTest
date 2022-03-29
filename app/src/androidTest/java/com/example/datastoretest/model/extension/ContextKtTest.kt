package com.example.datastoretest.model.extension

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val key = "test"
class ContextKtTest {

    private lateinit var context: Context

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() = runBlocking {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testSetInt() = runBlocking {
        context.setIntData(key, 1)
        Assert.assertEquals(context.getIntDataFlow(key).first(), 1)
    }

    @Test
    fun testSetProtoInt() = runBlocking {
        context.setProtoIntData(1)
        Assert.assertEquals(context.getProtoIntDataFlow().first(), 1)
    }

}