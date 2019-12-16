package kr.ac.cau.pyuopyuo.controller

import kr.ac.cau.pyuopyuo.PlaygroundDelegate
import kr.ac.cau.pyuopyuo.model.Playground
import java.util.*

class PlaygroundController: PlaygroundDelegate{

    var playgrounds: Array<Playground?> = Array(4, { i -> null })

    override fun onDoneScoring(playground: Playground, score: Vector<Int>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoneRotate(playground: Playground) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}