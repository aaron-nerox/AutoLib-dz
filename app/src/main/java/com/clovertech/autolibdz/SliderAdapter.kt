package com.clovertech.autolibdz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView


class SliderAdapter(var context: Context) : PagerAdapter() {

    var layoutInflater: LayoutInflater? = null
    var slider_first_headers = arrayOf(
            "Welcome to AutoLibDZ",
            "Secure payment",
            "Nearby rental"
    )

    var lottieAnimations = arrayOf(
            R.raw.lf30_editor_higrwe2d,
            R.raw.lf30_editor_i2vbfsvm,
            R.raw.lf30_editor_ittd7zeq
    )

    var slider_descriptions = arrayOf(
            "AutoLib Dz is a digital application that help users to find a car and start there journey",
            "AutoLib Dz ensure the security of your informations and your payments",
            "We help you to find car from the closest parks to you"
    )

    override fun getCount(): Int {
        return slider_first_headers.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View? = layoutInflater?.inflate(R.layout.view_pager_item, container, false)
        val service_first_header = view?.findViewById<TextView>(R.id.title1)
        val service_description = view?.findViewById<TextView>(R.id.desc_txt_view)
        val animationView = view?.findViewById<LottieAnimationView>(R.id.lottie_animation)

        animationView?.setAnimation(lottieAnimations[position])
        service_first_header?.text = slider_first_headers[position]
        service_description?.text = slider_descriptions[position]
        container.addView(view)
        return view!!

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as ConstraintLayout)
    }

}
