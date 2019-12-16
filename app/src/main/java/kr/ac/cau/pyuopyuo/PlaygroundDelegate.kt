package kr.ac.cau.pyuopyuo

import kr.ac.cau.pyuopyuo.model.Playground
import java.util.*

/**
 * 플레이그라운드에서 일어난 일을 처리해줄 인터페이스
 */
interface PlaygroundDelegate {

    /** 점수 계산이 완료되면 호출될 함수 */
    fun onDoneScoring(playground: Playground, score: Vector<Int>);

    /** 회전이 완료되면 호출될 함수  */
    fun onDoneRotate(playground: Playground);

}