package kr.ac.cau.pyuopyuo.model

/**
 * 뿌요들이 들어있는 게임 판 모델
 */
class Playground {

    val ROW = 14
    val COLUMN = 8

    var playground = Array<Array<Pyuo?>> (COLUMN) { Array<Pyuo?>(ROW, { null }) }



}