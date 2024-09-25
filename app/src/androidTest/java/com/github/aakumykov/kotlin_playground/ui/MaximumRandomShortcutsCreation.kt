package com.github.aakumykov.kotlin_playground.ui

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.aakumykov.kotlin_playground.MainActivity
import com.github.aakumykov.kotlin_playground.R
import com.github.aakumykov.kotlin_playground.common_no_test_stuff.string
import com.github.aakumykov.kotlin_playground.ui.base_classes.ListClickingTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class MaximumRandomShortcutsCreation : ListClickingTest() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @After
    fun tearDown() { showDesktop(device) }


    private val defaultShortcutList = listOf(
        R.string.shortcut_label_settings,
        R.string.shortcut_label_gallery,
        R.string.shortcut_label_record_video,
        R.string.shortcut_label_selfie,
    )

    private val enabledShortcutList: List<Int> get() {
        return defaultShortcutList.toMutableList().apply {
            removeAt(Random.nextInt(defaultShortcutList.size))
        }.toList()
    }

    private fun getShortcutsWithoutElementAt(index: Int): List<Int> {
        return defaultShortcutList.toMutableList().apply {
            removeAt(index)
        }.toList()
    }


    @Test
    fun when_create_4_of_5_shortcuts_then_are_created() {
        repeat(defaultShortcutList.size) { indexInDefaultShortcutList ->

            // Создаю список из всех ярлыков кроме одного.
            val listOf4 = getShortcutsWithoutElementAt(indexInDefaultShortcutList)

            // Отмечаю их в списке.
            listOf4.forEach { shortLabel: Int ->
                clickListItemWithShortcutShortLabel(shortLabel)
            }

            // Жму кнопку "Обновить".
            clickUpdateShortcutsButton()

            verifyAppShortcuts(listOf4.map { string(it) })
        }
    }
}