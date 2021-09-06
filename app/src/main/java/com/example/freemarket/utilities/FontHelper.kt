package com.example.freemarket.utilities

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.*

class FontHelper(private val context: Context) {
    private var REGULAR: Typeface? = null
    private var BOLD: Typeface? = null

    init {
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public Methods
    ///////////////////////////////////////////////////////////////////////////
    fun applyFont(view: ViewGroup?) {
        if (view != null) {
            apply(view)
        }
    }

    fun applyRegularFont(view: View?) {
        if (view != null) {
            try {
                if (view is ViewGroup) {
                    val parentView = view as ViewGroup?
                    for (i in 0 until parentView!!.childCount) {
                        val viewAux = parentView!!.getChildAt(i)

                        applyRegular(viewAux)
                    }
                } else {
                    applyRegular(view)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun applyBoldFont(view: View?) {
        if (view != null) {
            try {
                if (view is ViewGroup) {
                    val parentView = view as ViewGroup?
                    for (i in 0 until parentView!!.childCount) {
                        val viewAux = parentView!!.getChildAt(i)

                        applyBold(viewAux)
                    }
                } else {
                    applyBold(view)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Local Methods
    ///////////////////////////////////////////////////////////////////////////
    private fun init() {
        try {
            REGULAR = Typeface.createFromAsset(context.assets, "fonts/CameronSansLight.ttf")
            BOLD = Typeface.createFromAsset(context.assets, "fonts/CameronSansBold.ttf")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun apply(parentView: ViewGroup) {
        try {
            for (i in 0 until parentView.childCount) {
                val view = parentView.getChildAt(i)

                if (view is CheckBox) {
                    val typeFace = view.typeface
                    if (typeFace == null)
                        view.typeface = REGULAR
                    else {
                        if (typeFace.isBold)
                            view.typeface = BOLD
                        else
                            view.typeface = REGULAR
                    }
                } else if (view is RadioButton) {
                    val typeFace = view.typeface
                    if (typeFace == null)
                        view.typeface = REGULAR
                    else {
                        if (typeFace.isBold)
                            view.typeface = BOLD
                        else
                            view.typeface = REGULAR
                    }
                } else if (view is Button) {
                    val typeFace = view.typeface
                    if (typeFace == null)
                        view.typeface = REGULAR
                    else {
                        if (typeFace.isBold)
                            view.typeface = BOLD
                        else
                            view.typeface = REGULAR
                    }
                } else if (view is EditText) {
                    val typeFace = view.typeface
                    if (typeFace == null)
                        view.typeface = REGULAR
                    else {
                        if (typeFace.isBold)
                            view.typeface = BOLD
                        else
                            view.typeface = REGULAR
                    }
                } else if (view is TextView) {
                    val typeFace = view.typeface
                    if (typeFace == null)
                        view.typeface = REGULAR
                    else {
                        if (typeFace.isBold)
                            view.typeface = BOLD
                        else
                            view.typeface = REGULAR
                    }
                } else if (view is ViewGroup && view.childCount > 0) {
                    apply(view)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun applyRegular(view: View) {
        if (view is CheckBox) {
            view.typeface = REGULAR
        } else if (view is RadioButton) {
            view.typeface = REGULAR
        } else if (view is Button) {
            view.typeface = REGULAR
        } else if (view is EditText) {
            view.typeface = REGULAR
        } else if (view is TextView) {
            view.typeface = REGULAR
        } else if (view is ViewGroup && view.childCount > 0) {
            apply(view)
        }
    }

    private fun applyBold(view: View) {
        if (view is CheckBox) {
            view.typeface = BOLD
        } else if (view is RadioButton) {
            view.typeface = BOLD
        } else if (view is Button) {
            view.typeface = BOLD
        } else if (view is EditText) {
            view.typeface = BOLD
        } else if (view is TextView) {
            view.typeface = BOLD
        } else if (view is ViewGroup && view.childCount > 0) {
            apply(view)
        }
    }
}
