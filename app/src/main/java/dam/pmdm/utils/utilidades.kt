package dam.pmdm.utils

import android.util.Log
import kotlin.random.Random

fun String.transformar(descolocada: Boolean, funcion: (Char, Int) -> Char): String {
    var palabra :String = this
    if (descolocada) {
        val indicesDesordenados = (0 until this.length).shuffled()
        val desordenada = indicesDesordenados.map { this[it] }
        palabra = desordenada.joinToString("")
    }
    var palabraList : MutableList<Char> = mutableListOf()
    for (i in indices) {
        palabraList.add(funcion(palabra[i],i))
    }
    return palabraList.joinToString("")
}