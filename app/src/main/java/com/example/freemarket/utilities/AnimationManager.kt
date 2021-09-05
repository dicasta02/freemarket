package co.com.softpolaris.amanthulerp.utilities

import android.graphics.Interpolator
import android.view.animation.*

/**
 * @author dacastano
 * @version 1.0
 * @since 17/11/2017
 */
class AnimationManager {
    /**
     * Create translate animation, the values are took with type RELATIVE_TO_SELF,
     *
     * @param fromX        Initial X value
     * @param toX          Final X value
     * @param fromY        Initial Y value
     * @param toY          Final Y value
     * @param duration     Duration of animation
     * @param interpolator Required interpolator, default is AccelerateDecelerateInterpolator.
     * @return Translate Animation whit required params
     */
    fun getTranslateAnim(fromX: Float, toX: Float, fromY: Float, toY: Float, duration: Int, interpolator: android.view.animation.Interpolator?): Animation {
        val translate = TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX, Animation.RELATIVE_TO_SELF, toX, Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY)
        translate.interpolator = interpolator ?: AccelerateDecelerateInterpolator()
        translate.duration = duration.toLong()

        return translate
    }

    /**
     * Create translate animation.
     *
     * @param typeFromX    RELATIVE_TO_SELF, RELATIVE_TO_PARENT, ABSOLUTE
     * @param fromX        Initial X value
     * @param typeToX      RELATIVE_TO_SELF, RELATIVE_TO_PARENT, ABSOLUTE
     * @param toX          Final X value
     * @param typeFromY    RELATIVE_TO_SELF, RELATIVE_TO_PARENT, ABSOLUTE
     * @param fromY        Initial Y value
     * @param typeToY      RELATIVE_TO_SELF, RELATIVE_TO_PARENT, ABSOLUTE
     * @param toY          Final Y value
     * @param duration     Duration of animation
     * @param interpolator Required interpolator, default is AccelerateDecelerateInterpolator.
     * @return Translate Animation whit required params
     */
    fun getTranslateAnim(typeFromX: Int, fromX: Float, typeToX: Int, toX: Float, typeFromY: Int, fromY: Float, typeToY: Int, toY: Float, duration: Int, interpolator: android.view.animation.Interpolator?): Animation {
        val translate = TranslateAnimation(typeFromX, fromX, typeToX, toX, typeFromY, fromY, typeToY, toY)
        translate.interpolator = interpolator ?: AccelerateDecelerateInterpolator()
        translate.duration = duration.toLong()

        return translate
    }

    /**
     * Create scale animation.
     *
     * @param fromX        Initial X value
     * @param toX          Final X value
     * @param fromY        Initial Y value
     * @param toY          Final Y value
     * @param typePivotX   RELATIVE_TO_SELF, RELATIVE_TO_PARENT, ABSOLUTE
     * @param pivotX       The X coordinator where animation is scaled, between 0 - 1, 0 is left edge
     * @param typePivotY   RELATIVE_TO_SELF, RELATIVE_TO_PARENT, ABSOLUTE
     * @param pivotY       The Y coordinator where animation is scaled, between 0 - 1, 0 is top edge
     * @param duration     Duration of animation
     * @param interpolator Required interpolator, default is AccelerateDecelerateInterpolator.
     * @return Scale Animation whit required params
     */
    fun getScaleAnim(fromX: Float, toX: Float, fromY: Float, toY: Float, typePivotX: Int, pivotX: Float, typePivotY: Int, pivotY: Float, duration: Int, interpolator: android.view.animation.Interpolator?): Animation {
        val scale = ScaleAnimation(fromX, toX, fromY, toY, typePivotX, pivotX, typePivotY, pivotY)
        scale.interpolator = interpolator ?: AccelerateDecelerateInterpolator()
        scale.duration = duration.toLong()

        return scale
    }

    /**
     * Create alpha animation.
     *
     * @param fromAlpha    Initial alpha value
     * @param toAlpha      Final alpha value
     * @param duration     Duration of animation
     * @param interpolator Required interpolator, default is AccelerateDecelerateInterpolator.
     * @return Alpha Animation whit required params
     */
    fun getAlphaAnim(fromAlpha: Float, toAlpha: Float, duration: Int, interpolator: android.view.animation.Interpolator?): Animation {
        val alpha = AlphaAnimation(fromAlpha, toAlpha)
        alpha.interpolator = interpolator ?: AccelerateDecelerateInterpolator()
        alpha.duration = duration.toLong()

        return alpha
    }

}