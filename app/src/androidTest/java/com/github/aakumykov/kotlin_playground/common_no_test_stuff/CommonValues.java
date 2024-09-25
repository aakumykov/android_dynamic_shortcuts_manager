package com.github.aakumykov.kotlin_playground.common_no_test_stuff;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

public class CommonValues {
    public static final Context TARGET_CONTEXT = InstrumentationRegistry.getInstrumentation().getTargetContext();
}
