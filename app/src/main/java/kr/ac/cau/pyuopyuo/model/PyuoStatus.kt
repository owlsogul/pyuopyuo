package kr.ac.cau.pyuopyuo.model

enum class PyuoStatus {

    /** 기준 점이 되어 움직이고 있는 상태 */
    MainMove{
        override fun isMoved(): Boolean { return true; }
    },
    /** 기준 점에 매달려서 움직이고 있는 상태 */
    SubMove{
        override fun isMoved(): Boolean { return true; }
    },
    /** 땅에 붙어서 더이상 조종할 수 없는 상태 */
    NoMove{
        override fun isMoved(): Boolean { return false; }
    };

    abstract fun isMoved(): Boolean;

}