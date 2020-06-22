package p.gordenyou.gointoframwork.mooc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import p.gordenyou.gointoframwork.R
import p.gordenyou.goui.tab.bottom.GoTabBottom
import p.gordenyou.goui.tab.bottom.GoTabBottomInfo

class MoocMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mooc_main)

        val tabBottom = findViewById<GoTabBottom>(R.id.tab_bottom)
        val homeInfo = GoTabBottomInfo(
                "首页",
                "fonts/iconfont.ttf",
                getString(R.string.if_home),
                null,
                "#ff656667",
                "#ffd44949"
        )

        tabBottom.setTabInfo(homeInfo)
    }
}