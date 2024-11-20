package dam.pmdm.models

import android.os.CountDownTimer
import dam.pmdm.constants.minutos
import dam.pmdm.constants.segundos

class Temporizador
    (var minutosTemp: Int, var segundosTemp: Int, millisInFuture: Long, countDownInterval: Long)
    : CountDownTimer(millisInFuture,
    countDownInterval) {


    override fun onTick(p0: Long) {
        if (segundosTemp == 0) {
            segundosTemp = 59
            minutosTemp--
        } else {
            minutosTemp--
        }
    }

    override fun onFinish() {
        minutosTemp = minutos
        segundosTemp = segundos
    }

}