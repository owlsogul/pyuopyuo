package kr.ac.cau.pyuopyuo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("numOfPlayer",1)
            startActivity(intent)
        }

        button2.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("numOfPlayer",2)
            startActivity(intent)
        }

        button3.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("numOfPlayer",3)
            startActivity(intent)
        }

        button4.setOnClickListener {
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("numOfPlayer",4)
            startActivity(intent)
        }

    }


}
