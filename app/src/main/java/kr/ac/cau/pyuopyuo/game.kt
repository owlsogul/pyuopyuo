package kr.ac.cau.pyuopyuo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val fragmentManager = supportFragmentManager

        var num = intent.getIntExtra("numOfPlayer", 1)

        when (num){
            1 -> {
                val main = GameFragment()
                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                var jisu = main.getNextPyuo()
                if(jisu != null){
                    Log.v("jisu","main :" + jisu.main.toString() + "\nsub :" +jisu.sub.toString())
                }else{
                    Log.v("jisu","is NULL")
                }

                jisu = main.getNextPyuo()
                if(jisu != null){
                    Log.v("jisu","main :" + jisu.main.toString() + "\nsub :" +jisu.sub.toString())
                }else{
                    Log.v("jisu","is NULL")
                }
                // gameTest

            }
            2 -> {
                Toast.makeText(this@game, "num $num", Toast.LENGTH_SHORT).show()
                val main = GameFragment()
                val player1 = GameFragment()
                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                fragmentManager.beginTransaction().replace(R.id.player_1, player1).commit()
            }
            3 -> {
                val main = GameFragment()
                val player1 = GameFragment()
                val player2 = GameFragment()

                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                fragmentManager.beginTransaction().replace(R.id.player_1, player1).commit()
                fragmentManager.beginTransaction().replace(R.id.player_2, player2).commit()
            }
            4 -> {
                val main = GameFragment()
                val player1 = GameFragment()
                val player2 = GameFragment()
                val player3 = GameFragment()

                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                fragmentManager.beginTransaction().replace(R.id.player_1, player1).commit()
                fragmentManager.beginTransaction().replace(R.id.player_2, player2).commit()
                fragmentManager.beginTransaction().replace(R.id.player_3, player3).commit()
            }
        }
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


