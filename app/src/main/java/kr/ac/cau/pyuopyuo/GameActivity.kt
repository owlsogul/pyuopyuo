package kr.ac.cau.pyuopyuo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.ac.cau.pyuopyuo.controller.PlaygroundController
import kr.ac.cau.pyuopyuo.model.Playground
import java.util.*


const val GAMEFRAGMENT_ARG1 = "ARG1";

class GameActivity : AppCompatActivity() {


    /** 플레이그라운드 컨트롤러 */
    var pgCon: PlaygroundController = PlaygroundController()
    /** 플레이그라운드 그리는 뷰어들 */
    var pgDrawers: Vector<PlaygroundDrawer> = Vector()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val fragmentManager = supportFragmentManager

        var num = intent.getIntExtra("numOfPlayer", 1)
        pgCon.initPlayground(num)

        when (num){
            1 -> {
                val main = createGameFragment(1)
                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                pgDrawers.add(main)
            }
            2 -> {
                Toast.makeText(this@GameActivity, "num $num", Toast.LENGTH_SHORT).show()
                val main = createGameFragment(1)
                val player1 = createGameFragment(2)
                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                fragmentManager.beginTransaction().replace(R.id.player_1, player1).commit()
                pgDrawers.add(main)
                pgDrawers.add(player1)
            }
            3 -> {
                val main = createGameFragment(1)
                val player1 = createGameFragment(2)
                val player2 = createGameFragment(3)

                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                fragmentManager.beginTransaction().replace(R.id.player_1, player1).commit()
                fragmentManager.beginTransaction().replace(R.id.player_2, player2).commit()

                pgDrawers.add(main)
                pgDrawers.add(player1)
                pgDrawers.add(player2)
            }
            4 -> {
                val main = createGameFragment(1)
                val player1 = createGameFragment(2)
                val player2 = createGameFragment(3)
                val player3 = createGameFragment(4)

                fragmentManager.beginTransaction().replace(R.id.main_player, main).commit()
                fragmentManager.beginTransaction().replace(R.id.player_1, player1).commit()
                fragmentManager.beginTransaction().replace(R.id.player_2, player2).commit()
                fragmentManager.beginTransaction().replace(R.id.player_3, player3).commit()

                pgDrawers.add(main)
                pgDrawers.add(player1)
                pgDrawers.add(player2)
                pgDrawers.add(player3)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var gameActivity = this
        gameTimer.schedule(object: TimerTask(){
            override fun run(){
                pgCon.doTurn(gameActivity);
            }
        }, 1000, 1000)

    }

    var gameTimer: Timer = Timer()

    fun onLeft(){
        Toast.makeText(this, "left", Toast.LENGTH_SHORT)
    }

    fun onRight(){
        Toast.makeText(this, "right", Toast.LENGTH_SHORT)
    }

    fun onDown(){
        Toast.makeText(this, "down", Toast.LENGTH_SHORT)
    }

    fun onRotate(){
        Toast.makeText(this, "rotate", Toast.LENGTH_SHORT)
    }

    fun endTurn(){

    }

    private fun createGameFragment(num: Int): GameFragment{
        val f = GameFragment()
        val args = Bundle()
        args.putInt(GAMEFRAGMENT_ARG1, num)
        f.arguments = args
        return f;
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


