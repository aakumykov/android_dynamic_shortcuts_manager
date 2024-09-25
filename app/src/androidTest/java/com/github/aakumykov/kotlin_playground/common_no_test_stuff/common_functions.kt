package com.github.aakumykov.kotlin_playground.common_no_test_stuff

import androidx.annotation.StringRes

fun string(@StringRes strRes: Int): String {
    return targetContext.getString(strRes)
}