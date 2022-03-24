package com.emrizkis.zwallet.utils

import android.widget.TextView
import java.text.DecimalFormat

object Helper {
    fun TextView.formatPrice(value: String){
        this.text = formatIDR(java.lang.Double.parseDouble(value))
    }

    private fun  formatIDR(price : Double): String {
        val format = DecimalFormat("#,###,###")
        return "Rp " + format.format(price).replace(",".toRegex(),",")
    }

    fun TextView.formatNowBalance(value: String){
        this.text = formatNowBaalanceIDR(java.lang.Double.parseDouble(value))
    }
    private fun  formatNowBaalanceIDR(price : Double): String {
        val format = DecimalFormat("#,###,###")
        return "Rp " + format.format(price).replace(",".toRegex(),",") + " Available"
    }
}