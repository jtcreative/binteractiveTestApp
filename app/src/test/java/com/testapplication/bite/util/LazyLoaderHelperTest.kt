package com.testapplication.bite.util

import org.junit.Assert.*
import org.junit.Test

class LazyLoaderHelperTest {
    @Test
    fun testIsValidWorksForMultipleOffsetMultiple() {
        assertTrue("Itemcount is not a multiple of the given number", LazyLoaderHelper.isVslidOffset(260, 20))
        assertTrue("Itemcount is not a multiple of the given number", LazyLoaderHelper.isVslidOffset(260, 130))
        assertTrue("Itemcount is not a multiple of the given number", LazyLoaderHelper.isVslidOffset(300, 50))
    }

    @Test
    fun testIsValidDoesNotWorkForNonOffsetMultiple() {
        val isValid = LazyLoaderHelper.isVslidOffset(260, 30)
        assertFalse("Itemcount is a multiple although it shouldn't be", isValid)
    }

    @Test
    fun testIsValidWhenNegative() {
        assertFalse("Itemcount is a multiple when the multiple is negative", LazyLoaderHelper.isVslidOffset(260, -20))
    }
}