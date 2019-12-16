package kr.ac.cau.pyuopyuo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.ac.cau.pyuopyuo.model.Playground
import java.util.LinkedList
import java.util.Queue
import kotlin.random.Random


class GameFragment : Fragment(), PlaygroundDrawer {

    var red = R.drawable.red
    var blue = R.drawable.blue
    var yellow = R.drawable.yellow
    var green = R.drawable.green
    var purplue = R.drawable.purple
    var pyuo = arrayOf(red,blue,yellow,green,purplue)

    val ROW = 14
    val COLUMN = 8

    // 다음 나올 뿌요 저장
    var buffer: Queue<Pyuo> = LinkedList<Pyuo>()

    // 8*14 matrix로 두가지 상태의 뿌요들을 표현
    // 화면에는 6*12까지 보이게 되고,
    // 값이 0이면 빈칸 0이 아니면 해당 뿌요이다.
    var fieldState = Array(ROW) { IntArray(COLUMN) } // 필드에서 고정되어있는 뿌요 표현
    var currentState = Array(ROW) { IntArray(COLUMN) } // 현재 내려오고 있는 뿌요 표현

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // state initialinze
        for (i in 0..ROW-1)
            for (j in 0..COLUMN-1) {
                fieldState[i][j] = 0;
                currentState[i][j] = 0;
            }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    // 구현해야 하는 것.

    // 3. 뿌요는 아래 뿌요를 기준으로 회전 가능 (행렬로 표현)
    // 4. 뿌요의 충돌 구현 (두 matrix를 비교함으로써)
    // 5. 뿌요는 낱개로 떨어질 수도 있음

    fun checkCollision() : Boolean {  // fieldState와 currentState를 확인해서 collision 확인
        // 충돌이 일어나는 경우는 두가지 경우
        // 1. 뿌요가 맨 아래 있던가
        // 2. 바로 아래에 뿌요가 있는 경우

        return false
    }

    fun checkNextTurn() :Boolean { // 다음 턴으로 넘길지 확인

        return false
    }

    fun getNextPyuo (): Pyuo?{ // 뿌요 랜덤하게 큐에서 가져옴.
        if(buffer.isEmpty()){
            generatePyuo()
        }

        var currentPuyo = buffer.peek()
        buffer.remove()

        return currentPuyo
    }

    fun generatePyuo(){
        for (i in 1..100){
            buffer.add(Pyuo(pyuo.get(Random.nextInt(0,5)),pyuo.get(Random.nextInt(0,5))))
        }
    }

    override fun drawPlayground(playgroud: Playground) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // 1. 키보드로 회전 인터럽트 받음
    // 1-1. 뿌요가 회전하기 위해서는 뿌요가 두개 이상 존재해야 함
    // 즉 뿌요가 1개일 때는

    // 2. 충돌 확인 -> 충돌하면 currentState의 뿌요를 fieldState로 옮김.
    // 3. 다음 스테이지로 가야하는지 확인 -> 이건 그냥 currentState가 비어있는지 확인하면 됨

    // 4. display state에 존재하는 뿌요들을 화면에 표현(constraint layout 사용)

}


data class Pyuo (val main: Int, val sub: Int){

}