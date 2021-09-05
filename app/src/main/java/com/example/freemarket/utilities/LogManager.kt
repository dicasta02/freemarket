@file:Suppress("UNCHECKED_CAST")

package com.example.freemarket.utilities

import android.util.Log
import com.example.freemarket.BuildConfig
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

class LogManager(c: Class<*>) {
    private val DEBUG = BuildConfig.DEBUG
    var className = "UnknownClass"
        private set

    //Filter to make print
    private val FILTER_BY_CLASS = false
    private val IGNORE_BY_CLASS = false
    private val filterByClass = arrayOf<String>()
    private val ignoreByClass = arrayOf<String>()
    fun setClassName(c: Class<*>) {
        className = c.simpleName
    }

    private fun canPrint(): Boolean {
        var canPrint = false
        if (DEBUG) {
            canPrint = if (FILTER_BY_CLASS) Arrays.asList(*filterByClass)
                .contains(className) else if (IGNORE_BY_CLASS) !Arrays.asList(*ignoreByClass)
                .contains(className) else true
        }
        return canPrint
    }

    fun printError(vararg args: Any?) {
        if (canPrint()) Log.e(buildTag(), buildMsg(*args as Array<out Any>))
    }

    fun printWarn(vararg args: Any?) {
        if (canPrint()) Log.w(buildTag(), buildMsg(*args as Array<out Any>))
    }

    fun printInfo(vararg args: Any?) {
        if (canPrint()) Log.i(buildTag(), buildMsg(*args as Array<out Any>))
    }

    fun printDebug(vararg args: Any?) {
        if (canPrint()) Log.d(buildTag(), buildMsg(*args as Array<out Any>))
    }

    fun printVerbose(vararg args: Any?) {
        if (canPrint()) Log.v(buildTag(), buildMsg(*args as Array<out Any>))
    }

    fun printError(msg: String?, t: Throwable?) {
        if (DEBUG) printError(buildTag(), msg, stackToString(t))
    }

    fun printWarn(msg: String?, t: Throwable?) {
        if (DEBUG) printWarn(buildTag(), msg, stackToString(t))
    }

    fun printInfo(msg: String?, t: Throwable?) {
        if (DEBUG) printInfo(buildTag(), msg, stackToString(t))
    }

    fun printDebug(msg: String?, t: Throwable?) {
        if (DEBUG) printDebug(buildTag(), msg, stackToString(t))
    }

    fun printVerbose(msg: String?, t: Throwable?) {
        if (DEBUG) printVerbose(buildTag(), msg, stackToString(t))
    }

    private fun buildTag(): String {
        val tag: String
        val b = StringBuilder(20)
        b.append(Constants.TAG)
        b.append(" -> ")
        b.append(className)
        val stackEntry = Thread.currentThread().stackTrace[4]
        if (stackEntry != null) {
            b.append('.')
            b.append(stackEntry.methodName)
            b.append('(')
            b.append(stackEntry.lineNumber)
            b.append(')')
        }
        tag = b.toString()
        return tag
    }

    private fun buildMsg(vararg args: Any): String {
        if (args != null) {
            val b = StringBuilder(args.size * 10)
            b.append("*****").append(DateUtils.currentDateTimeFormat).append("*****\n")
            for (arg in args) {
                if (arg == null) {
                    b.append("null")
                } else {
                    b.append(arg).append(" ")
                }
            }
            return b.toString().trim { it <= ' ' }
        }
        return "null"
    }

    private fun stackToString(t: Throwable?): String {
        if (t != null) {
            val errors = StringWriter()
            t.printStackTrace(PrintWriter(errors))
            return errors.toString()
        }
        return ""
    }

    init {
        if (!c.simpleName.isEmpty()) className = c.simpleName
    }
}