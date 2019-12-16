package kr.ac.cau.pyuopyuo.model

/**
 * 뿌요 하나를 의미하는 모델
 */
class Pyuo {

    constructor(colorCode: Int, moveStatus: PyuoStatus) {
        var color = PyuoColor.Red;
        PyuoColor.values().forEach { origin -> if (origin.code == colorCode) {
            color = origin;
        }; }
        this.color = color;
        this.moveStatus = moveStatus;
    }

    constructor(color: PyuoColor, moveStatus: PyuoStatus) {
        this.color = color;
        this.moveStatus = moveStatus;
    }

    /** x좌표. 몇번째 컬럼인지 */
    var x: Int = 0;
    /** y좌표. 몇번째 로우인지 */
    var y: Int = 0;

    /** 움직임 여부 저장하고 있는 변수 */
    var moveStatus: PyuoStatus = PyuoStatus.NoMove;

    /** 색 정보 저장하고 있는 변수 */
    var color: PyuoColor = PyuoColor.Red;

}