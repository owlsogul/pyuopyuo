package kr.ac.cau.pyuopyuo.controller

import kr.ac.cau.pyuopyuo.GameActivity
import kr.ac.cau.pyuopyuo.PlaygroundDelegate
import kr.ac.cau.pyuopyuo.model.Playground
import kr.ac.cau.pyuopyuo.model.Pyuo
import kr.ac.cau.pyuopyuo.model.PyuoStatus
import java.util.*

class PlaygroundController: PlaygroundDelegate{

    var playgrounds: Array<Playground?> = Array(4, { i -> null })

    fun initPlayground(num: Int){
        playgrounds[0] = Playground(this);
        for (i in 1 .. num - 1){
            playgrounds[i] = Playground(null);
        }
    }

    /**  게임 진행하는 함수 */
    fun doTurn(activity: GameActivity){
        var mainPg = playgrounds[0]!!
        if (mainPg.findOneMoveStatus(PyuoStatus.MainMove) == null){ // make random pyuo
            var randome = Random()
            var mainMove = Pyuo(randome.nextInt(5), PyuoStatus.MainMove)
            var subMove = Pyuo(randome.nextInt(5), PyuoStatus.SubMove)
            mainMove.x = 3; mainMove.y = 0; mainPg.playground[0][3] = mainMove;
            subMove.x = 3; subMove.y = 1; mainPg.playground[1][3] = subMove;
        }
        mainPg.fallPyuo();
        mainPg.checkCollision();
        mainPg.afterCollision();
    }

    override fun onDoneScoring(playground: Playground, score: Vector<Int>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoneRotate(playground: Playground) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}