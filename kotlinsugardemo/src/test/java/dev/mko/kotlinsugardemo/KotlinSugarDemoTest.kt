package dev.mko.kotlinsugardemo

import dev.mko.kotlinsugar.model.AssociatedEnum
import dev.mko.kotlinsugar.multiCatch
import dev.mko.kotlinsugar.multiLet
import org.junit.Test
import java.io.IOException

/**
 * Unit test to demonstrate how to use these awesome Extension functions
 */
class KotlinSugarDemoTest {

    @Test
    fun multiCatchTest_successfulCatch() {
        multiCatch({
            throw IOException()
        }, { e ->
            assert(true) // Do some logging or whatever instead
        }, ReflectiveOperationException::class, IOException::class)

        multiCatch({
            // ClassNotFoundException is inheritance of ReflectiveOperationException. This should also work.
            throw ClassNotFoundException()
        }, { e ->
            assert(true) // Do some logging or whatever instead
        }, ReflectiveOperationException::class, IOException::class)
    }

    @Test
    fun multiCatchTest_forwardException() {
        try {
            multiCatch({
                throw ArrayIndexOutOfBoundsException()
            }, { e ->
                assert(false)
            }, ClassNotFoundException::class, IOException::class)
        } catch (e: ArrayIndexOutOfBoundsException) {
            assert(true)
        }
    }

    @Test
    fun multiLet_success() {
        multiLet("", 1) { anyString, anyInt ->
            assert(anyString == "")
            assert(anyInt == 1)
            assert(true)
        }
    }

    @Test
    fun multiLet_fail() {
        multiLet("", 1) { anyString, anyInt ->
            assert(true)
        }
    }

    @Test
    fun associatedEnum_test() {
        var associatedEnum: AssociatedEnum = AssociatedEnum.Unknown

        assert(!associatedEnum.value5)

        associatedEnum = AssociatedEnum.Valid("", 3, 1)
        assert(associatedEnum.value5)
    }

}