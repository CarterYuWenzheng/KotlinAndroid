package com.carter.androiddemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import android.widget.TextView
import com.carter.androiddemo.MyApplication
import com.carter.androiddemo.R
import com.carter.androiddemo.modules.login.ui.activity.LoginActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CommenUtil {

    fun isNetworkConnected(): Boolean {
        val connectivityManager = MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isConnected
    }

    fun startLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    fun getRandomColor(): Int {
        val random = Random()
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        val red: Int
        val green: Int
        val blue: Int
        if (MyApplication.isNightMode()) {
            //            150-255
            red = random.nextInt(105) + 150
            green = random.nextInt(105) + 150
            blue = random.nextInt(105) + 150
        } else {
            red = random.nextInt(190)
            green = random.nextInt(190)
            blue = random.nextInt(190)
        }
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue)
    }

    @SuppressLint("InflateParams")
    fun getLoadingDialog(context: Context, message: String): AlertDialog {
        val view = LayoutInflater.from(context).inflate(R.layout.loading_progressbar, null, false)
        val loadingText = view.findViewById<TextView>(R.id.loading_text)
        loadingText.text = message
        val dialog = AlertDialog.Builder(context).setView(view).create()
        Objects.requireNonNull<Window>(dialog.window).setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    fun hideKeyBoard(context: Context, view: View) {
        val inputMethodManager = context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        SimpleDateFormat.getDateInstance()
        return simpleDateFormat.format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    fun dateString2Calendar(dateString: String): Calendar {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    fun showPopupWindow(anchorView: View, contentView: View): PopupWindow {
        val popupWindow = PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.setBackgroundDrawable(contentView.background)
        popupWindow.isOutsideTouchable = true
        popupWindow.isTouchable = true
        val windowPos = calculatePopWindowPos(anchorView, contentView)
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, windowPos[0], windowPos[1])
        return popupWindow
    }

    /**
     * 计算出来的位置，y方向就在anchorView的中心对齐显示，x方向就是与View的中心点对齐
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private fun calculatePopWindowPos(anchorView: View, contentView: View): IntArray {
        val windowPos = IntArray(2)
        val anchorLoc = IntArray(2)
        anchorView.getLocationOnScreen(anchorLoc)
        val anchorHeight = anchorView.height
        val anchorWidth = anchorView.width
        val screenHeight = anchorView.context.resources.displayMetrics.heightPixels
        val screenWidth = anchorView.context.resources.displayMetrics.widthPixels
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val windowHeight = contentView.measuredHeight
        val windowWidth = contentView.measuredWidth
        // 判断需要向上弹出还是向下弹出显示
        val isNeedShowUp = anchorLoc[1] > screenHeight / 3
        //偏移，否则会弹出在屏幕外
        val offset = if (windowWidth > anchorWidth) windowWidth - anchorWidth else 0
        //实际坐标中心点为触发view的中间
        windowPos[0] = anchorLoc[0] + anchorWidth / 2 + offset
        val offset2 = windowPos[0] + windowWidth - screenWidth
        if (offset2 > 0) {
            windowPos[0] = windowPos[0] - offset2
        }
        windowPos[1] = if (isNeedShowUp) anchorLoc[1] - windowHeight + anchorHeight / 2 else anchorLoc[1] + anchorHeight / 2
        return windowPos
    }
}