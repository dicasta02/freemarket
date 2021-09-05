package co.com.softpolaris.amanthulerp.utilities

import android.os.Handler
import android.view.View
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringListener
import com.facebook.rebound.SpringSystem

class SpringAnimationManager : SpringListener {
    private var TENSION = 800.0
    private var DAMPER = 20.0 //friction

    private var spring: Spring? = null
    private var viewToAnim: View? = null

    constructor(viewToAnim: View) {
        this.viewToAnim = viewToAnim
        setup()
    }

    constructor(viewToAnim: View, tension: Double, damper: Double) {
        this.viewToAnim = viewToAnim
        this.TENSION = tension
        this.DAMPER = damper
        setup()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Inhetit methods
    ///////////////////////////////////////////////////////////////////////////
    override fun onSpringUpdate(spring: Spring) {
        if (viewToAnim != null) {
            val value = spring.currentValue.toFloat()
            val scale = 1f - value * 0.5f
            viewToAnim!!.scaleX = scale
            viewToAnim!!.scaleY = scale
        }
    }

    override fun onSpringAtRest(spring: Spring) {

    }

    override fun onSpringActivate(spring: Spring) {

    }

    override fun onSpringEndStateChange(spring: Spring) {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Public methods
    ///////////////////////////////////////////////////////////////////////////
    fun setEndValue(endValue: Float) {
        spring!!.endValue = endValue.toDouble()
    }

    /**
     * Perform animation such a simple click
     *
     * @param startValue Initial value between 0 - 1
     * @param endValue   End value between 0 - 1
     */
    fun performAnimation(startValue: Float, endValue: Float) {
        spring!!.endValue = startValue.toDouble()
        Handler().postDelayed({ spring!!.endValue = endValue.toDouble() }, 200)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////
    private fun setup() {
        val springSystem = SpringSystem.create()
        spring = springSystem.createSpring()
        spring!!.addListener(this)

        val config = SpringConfig(TENSION, DAMPER)
        spring!!.springConfig = config
    }
}