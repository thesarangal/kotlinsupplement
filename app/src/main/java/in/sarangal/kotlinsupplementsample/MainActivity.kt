package `in`.sarangal.kotlinsupplementsample

import `in`.sarangal.kotlinsupplement.toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Show Toast */
        toast(
            "Welcome to KotlinSupplement"
        )
    }
}