package com.example.freemarket.utilities

class SpringInterpolator : android.view.animation.Interpolator {
    private val mCycles: Float
    private val mDamping: Float

    constructor() {
        mCycles = DEFAULT_CYCLES
        mDamping = -1.0f * mCycles * DEFAULT_DAMPING
    }

    /**
     * @param damping Amount of damping. When damping equals 0.0f, there is
     * no damping and the interpolator becomes a simple
     * CycleInterpolator.
     */

    constructor(damping: Float) {
        mCycles = DEFAULT_CYCLES
        mDamping = -1.0f * mCycles * damping
    }

    /**
     * @param damping Amount of damping. When damping equals 1.0f, there is
     * no damping and the interpolator becomes a simple
     * CycleInterpolator.
     * @param cycles  Number of cycles. The animation will overshoot and
     * undershoot for this number of times.
     */

    constructor(damping: Float, cycles: Float) {
        mCycles = cycles
        mDamping = -1.0f * mCycles * damping
    }

    /**
     * @hide
     */

    override fun getInterpolation(t: Float): Float {
        // 1 - e^((-damping) * t) cos(spring * t)
        return (1.0f - Math.exp((mDamping * t).toDouble()) * Math.cos(2.0 * Math.PI * mCycles.toDouble() * t.toDouble())).toFloat()
    }

    companion object {
        private val DEFAULT_CYCLES = 1.25f
        private val DEFAULT_DAMPING = 3.0f
    }
}