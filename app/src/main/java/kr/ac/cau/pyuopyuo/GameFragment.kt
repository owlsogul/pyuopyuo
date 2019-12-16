package kr.ac.cau.pyuopyuo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kr.ac.cau.pyuopyuo.model.Playground
import kr.ac.cau.pyuopyuo.model.Pyuo
import java.util.LinkedList
import java.util.Queue
import kotlin.random.Random
import androidx.constraintlayout.widget.ConstraintSet
import kr.ac.cau.pyuopyuo.model.COLUMN
import kr.ac.cau.pyuopyuo.model.ROW


class GameFragment : Fragment(), PlaygroundDrawer {
    val TAG = "GameFragment"
    var red = R.drawable.red
    var blue = R.drawable.blue
    var yellow = R.drawable.yellow
    var green = R.drawable.green
    var purplue = R.drawable.purple
    var colors = arrayOf(red,green,blue,yellow,purplue)

    var btnLeft: Button? = null;
    var btnDown: Button? = null;
    var btnRight: Button? = null;
    var btnRotate: Button? = null;
    var txtScore: TextView? = null;
    var gameLayout: ConstraintLayout? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        btnDown = view.findViewById<Button>(R.id.btnDown)
        btnLeft = view.findViewById<Button>(R.id.btnLeft)
        btnRight = view.findViewById<Button>(R.id.btnRight)
        btnRotate = view.findViewById<Button>(R.id.btnRotate)
        txtScore = view.findViewById(R.id.txtScroe)
        gameLayout = view.findViewById(R.id.gameLayout);
        if (this.arguments!!.getInt(GAMEFRAGMENT_ARG1) != 1){
            btnDown?.visibility = View.GONE
            btnLeft?.visibility = View.GONE
            btnRight?.visibility = View.GONE
            btnRotate?.visibility = View.GONE
        }
        btnDown!!.setOnClickListener { (activity as GameActivity).onDown(); Log.d(TAG, "onDown") }
        btnLeft!!.setOnClickListener { (activity as GameActivity).onLeft(); Log.d(TAG, "onLeft") }
        btnRight!!.setOnClickListener { (activity as GameActivity).onRight(); Log.d(TAG, "onRight") }
        btnRotate!!.setOnClickListener { (activity as GameActivity).onRotate(); Log.d(TAG, "onRotate") }

        return view
    }


    override fun drawPlayground(playgroud: Playground) {

        playgroud.playground.forEachIndexed { y: Int, row: Array<Pyuo?> ->
            row.forEachIndexed { x: Int, pyuo: Pyuo? ->
                pyuo?.let {
                    val imgView: ImageView  = ImageView(this.context)
                    imgView.setImageResource(colors[it!!.color.code])
                    val constraints = ConstraintSet()
                    constraints.constrainWidth(imgView.id, gameLayout!!.width/ COLUMN)
                    constraints.constrainHeight(imgView.id, gameLayout!!.height/ ROW)
                    constraints.setMargin(R.id.gameLayout, ConstraintSet.BOTTOM, gameLayout!!.height / COLUMN * y )
                }
            }
        }

    }

    // 1. 키보드로 회전 인터럽트 받음
    // 1-1. 뿌요가 회전하기 위해서는 뿌요가 두개 이상 존재해야 함
    // 즉 뿌요가 1개일 때는

    // 2. 충돌 확인 -> 충돌하면 currentState의 뿌요를 fieldState로 옮김.
    // 3. 다음 스테이지로 가야하는지 확인 -> 이건 그냥 currentState가 비어있는지 확인하면 됨

    // 4. display state에 존재하는 뿌요들을 화면에 표현(constraint layout 사용)

}