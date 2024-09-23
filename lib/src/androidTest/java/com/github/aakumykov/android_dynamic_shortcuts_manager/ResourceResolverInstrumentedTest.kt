package com.github.aakumykov.android_dynamic_shortcuts_manager

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.aakumykov.android_dynamic_shortcuts_manager.common.packageName
import com.github.aakumykov.android_dynamic_shortcuts_manager.common.resources
import com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.utils.ResourceResolver
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResourceResolverInstrumentedTest {

    private val resourceResolver: ResourceResolver = ResourceResolver(packageName, resources)


    @Test
    fun resolve_string_resource_without_error() {
        assertEquals(R.string.test_string,
            resourceResolver.getStringResourceByName("test_string"))
    }

    @Test
    fun resolve_drawable_resource_name_to_int() {
        assertEquals(R.drawable.ic_android_black_24dp,
            resourceResolver.getDrawableResourceByName("ic_android_black_24dp"))
    }
}