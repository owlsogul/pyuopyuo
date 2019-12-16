package kr.ac.cau.pyuopyuo.model

import java.util.*

/**
 * 뿌요들이 들어있는 게임 판 모델
 */
class Playground {

    val ROW = 14
    val COLUMN = 8

    var playground = Array<Array<Pyuo?>> (ROW) { Array<Pyuo?>(COLUMN, { null }) }

    fun getPyuo(x: Int, y: Int): Pyuo? {
        if (x < 0 || x > COLUMN -1 || y < 0 || y > ROW - 1) return null;
        else return playground[y][x];
    }


    /**
     * 내려오는 뿌요 충돌 감지하는 함수
     */
    fun checkCollision(){
        playground.
    }

    /**
     * 빈칸있으면 뿌요 떨어뜨리는 함수
     */
    fun fallPyuo(){

    }

    /**
     * 4개 이상 뿌요가 있는지 확인해서 터진 뿌요 리턴하는 함수
     */
    fun checkScoreCondition(): Vector<Vector<Pyuo>> {

        var colorPlayground = playground.clone().map{ it.map { it?.color?.code ?: -1; } as Array<Int> }
        var candidates = LinkedList<Pyuo>();

        // 상 하 좌 우 같은 색 있는 지 확인
        // 있을 경우 후보군에 넣음
        colorPlayground.forEachIndexed { y, rowItem ->
            rowItem.forEachIndexed { x, color ->
                if (color == -1) return@forEachIndexed;
                //TODO: 후보군 수 줄이기
                getPyuo(x - 1, y)?.let { if (it.color.code == color) candidates.add(it) }
                getPyuo(x + 1, y)?.let { if (it.color.code == color) candidates.add(it) }
                getPyuo(x, y - 1)?.let { if (it.color.code == color) candidates.add(it) }
                getPyuo(x, y + 1)?.let { if (it.color.code == color) candidates.add(it) }
            }
        }

        // 후보군에서 같은 색이 4개 이상 있는 것을 찾음
        var retVector: Vector<Vector<Pyuo>> = Vector();
        var findOtherCandidate = { currentPyuo: Pyuo, targetPyuo: Pyuo, queue: LinkedList<Pyuo>->
            var targetColor = colorPlayground[targetPyuo.y][targetPyuo.x]
            var currentColor = colorPlayground[currentPyuo.y][currentPyuo.x]
            if (targetColor == currentColor) {
                queue.push(targetPyuo);
                colorPlayground[currentPyuo.y][currentPyuo.x] = -1;
            }
        }
        candidates.forEach { candidatePyuo->
            var vector =  Vector<Pyuo>();
            var queue = LinkedList<Pyuo>(); queue.push(candidatePyuo)
            while (!queue.isEmpty()){
                var currentPyuo = queue.pop()
                var x = currentPyuo.x
                var y = currentPyuo.y
                var color = currentPyuo.color.code; if (color == -1) continue;
                getPyuo(x - 1, y)?.let { findOtherCandidate(currentPyuo, it, queue) }
                getPyuo(x + 1, y)?.let { findOtherCandidate(currentPyuo, it, queue) }
                getPyuo(x, y - 1)?.let { findOtherCandidate(currentPyuo, it, queue) }
                getPyuo(x, y + 1)?.let { findOtherCandidate(currentPyuo, it, queue) }
                vector.add(currentPyuo);
            }
            if (vector.size >= 4) {
                retVector.addElement(vector);
            }
        }
        return retVector;
    }

}