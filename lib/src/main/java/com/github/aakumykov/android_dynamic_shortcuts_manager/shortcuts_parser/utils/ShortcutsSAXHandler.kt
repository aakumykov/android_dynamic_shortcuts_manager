package com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.utils

import com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.model.RawShortcutJava
import com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.model.ShortcutIntent
import com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.model.ShortcutIntent.Companion.ATTR_ACTION
import com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.model.ShortcutIntent.Companion.ATTR_TARGET_CLASS
import com.github.aakumykov.android_dynamic_shortcuts_manager.shortcuts_parser.model.ShortcutIntent.Companion.ATTR_TARGET_PACKAGE
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class ShortcutsSAXHandler : DefaultHandler() {

    private lateinit var rawShortcuts: MutableList<RawShortcutJava>
    private val elementValue: StringBuilder = StringBuilder()

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        elementValue.appendRange(ch, start, start + length)
    }

    @Throws(SAXException::class)
    override fun startDocument() {
        rawShortcuts = mutableListOf()
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, lName: String, qName: String, attributes: Attributes) {
        when (qName) {
            SHORTCUT -> {
                rawShortcuts.add(
                    RawShortcutJava(
                        attributes.getValue(RawShortcutJava.ATTR_SHORTCUT_ID),
                        attributes.getValue(RawShortcutJava.ATTR_ENABLED).toBoolean(),
                        attributes.getValue(RawShortcutJava.ATTR_ICON),
                        attributes.getValue(RawShortcutJava.ATTR_SHORTCUT_SHORT_LABEL)
                    ))
            }
            INTENT -> {
                rawShortcuts.last().shortcutIntent = ShortcutIntent(
                    action = attributes.getValue(ATTR_ACTION),
                    targetPackage = attributes.getValue(ATTR_TARGET_PACKAGE),
                    targetClass = attributes.getValue(ATTR_TARGET_CLASS)
                )
            }
        }
    }

    fun getShortcuts(): List<RawShortcutJava> = rawShortcuts

    companion object {
        const val SHORTCUT = "shortcut"
        const val INTENT = "intent"
    }
}