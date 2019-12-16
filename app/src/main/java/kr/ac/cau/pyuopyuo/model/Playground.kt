package kr.ac.cau.pyuopyuo.model

import kr.ac.cau.pyuopyuo.PlaygroundDelegate
import java.util.*

const val ROW = 14
const val COLUMN = 8
/**
 * 뿌요들이 들어있는 게임 판 모델
 */
class Playground {

    var delegate: PlaygroundDelegate? = null

    constructor(delegate: PlaygroundDelegate?) {
        this.delegate = delegate
    }

    var playground = Array<Array<Pyuo?>> (ROW) { Array<Pyuo?>(COLUMN, { null }) }

    fun getPyuo(x: Int, y: Int): Pyuo? {
        if (x < 0 || x > COLUMN -1 || y < 0 || y > ROW - 1) return null;
        else return playground[y][x];
    }

    fun findOneMoveStatus(moveStatus: PyuoStatus): Pyuo? {
        var moveContainer = playground.filter { it.filter { (moveStatus == it?.moveStatus) }.size > 0 };
        if (moveContainer.size > 0) return moveContainer[0][0]
        else return null;
    }

    fun canRotate(): Boolean{

        var mainMove = findOneMoveStatus(PyuoStatus.MainMove)
        var subMove = findOneMoveStatus(PyuoStatus.SubMove)
        if (mainMove == null || subMove == null) return false;
        // 회전 행렬값
        var toX = subMove.y - mainMove.y + mainMove.x
        var toY = mainMove.x - subMove.x + mainMove.y

        return (getPyuo(toX, toY) == null) &&
                (0 <= toX && toX < ROW) &&
                (0 <= toY && toY < COLUMN)
    }

    /**
     * 내려오는 뿌요가 충돌했는지 감지하는 함수
     */
    fun checkCollision(): Boolean{
        // find main move, sub move
        var mainMove = findOneMoveStatus(PyuoStatus.MainMove)
        var subMove = findOneMoveStatus(PyuoStatus.SubMove)
        if (mainMove == null || subMove == null) return false;

        return (getPyuo(mainMove!!.x, mainMove!!.y + 1) != null) || // 메인 뿌요 아래 무엇인가 있거나,
                (getPyuo(subMove!!.x, subMove!!.y + 1) != null) || // 서브 뿌요 아래 무엇인가 있거나,
                (mainMove.y + 1 >= COLUMN) || // 메인 뿌요아래가 아무것도 없거나
                (subMove.y + 1 >= COLUMN)   // 서브 뿌요 아래 어무것도 없을 경우
    }

    /**
     * 충돌 후 처리해야하는 일을 기술한 함수
     * @return Vector<Int> 부셔진 뿌요 뭉텅이들의 개수들
     */
    fun afterCollision(): Vector<Int> {
        fallPyuo(); // 메인 뿌요와 서브 뿌요가 높이가 다를수 있으므로 내린다.
        var brokenPyou: Vector<Int> = Vector()
        var tempScore: Vector<Vector<Pyuo>> = checkScoreCondition()
        while(tempScore.size > 0){
            tempScore.forEach{ brokenPyou.add(it.size) }
            eliminateScoredPyuo(tempScore)
            fallPyuo()
            tempScore = checkScoreCondition()
        }
        delegate?.onDoneScoring(this, brokenPyou);
        return brokenPyou
    }

    /**
     * 빈칸있으면 뿌요 떨어뜨리는 함수
     */
    fun fallPyuo(){
        var y: Int = COLUMN-1;
        var x: Int = 0;

        while (x < ROW){
            while(y > 0){
                if (playground[y][x] == null){
                    var tempY = y - 1;
                    while (tempY >= 0){
                        playground[tempY][x].let { it!!.y = tempY+1 }
                        playground[tempY+1][x] = playground[tempY][x]
                        tempY--
                    }
                }
                y--
            }
            x++
        }

    }

    /**
     * 4개 이상 뿌요가 있는지 확인해서 터진 뿌요 리턴하는 함수
     */
    private fun checkScoreCondition(): Vector<Vector<Pyuo>> {

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

    private fun eliminateScoredPyuo(pyuos: Vector<Vector<Pyuo>>){
        pyuos.forEach { vector -> vector.forEach { pyuo->
            playground[pyuo.y][pyuo.x] = null;
        }}
    }

}