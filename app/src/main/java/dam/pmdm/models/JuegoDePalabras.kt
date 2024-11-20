package dam.pmdm.models

import kotlin.random.Random

class JuegoDePalabras() {
    private var palabras : Array<String> = arrayOf(
        "dormitorio",
        "letrina",
        "llorera",
        "suspenso",
        "dispositivo",
        "cochera",
        "estropeado",
        "internet",
        "denuncia",
        "manifestacion",
        "largura")

    private val pistas : Array<String> = arrayOf(
        "Faltan caracteres",
        "Cambio de vocal",
        "Posición del caracter",
        "La siguiente"
    )

    fun obtenerPalabra(): String {
        return palabras[Random.nextInt(0,palabras.size-1)]
    }

    fun obtenerPista(num :Int): String {
        return pistas[num]
    }

    constructor(palabras: Array<String>):this() {
        this.palabras = palabras
    }

}