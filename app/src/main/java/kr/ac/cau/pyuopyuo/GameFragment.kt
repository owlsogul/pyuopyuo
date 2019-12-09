package kr.ac.cau.pyuopyuo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class GameFragment : Fragment() {

    var red = R.drawable.red
    var blue = R.drawable.blue
    var yellow = R.drawable.yellow
    var green = R.drawable.green
    var purplue = R.drawable.purple

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    

}