package com.testapplication.bite.util

class LazyLoaderHelper {
    companion object {
        fun isVslidOffset(currentAmount: Int, multiple:Int) = ((multiple > 0) && ((currentAmount % multiple)  == 0))
    }
}