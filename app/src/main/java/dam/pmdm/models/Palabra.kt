package dam.pmdm.models

import dam.pmdm.utils.quitar_caracter
import dam.pmdm.utils.siguiente_letra
import dam.pmdm.utils.siguiente_vocal
import dam.pmdm.utils.sustituir_alfabeto
import dam.pmdm.utils.transformar

class Palabra(partida: JuegoDePalabras, val palabraTexto: String, val nPista: Int) {
    val palabraModificada = when (nPista) {
        0 -> palabraTexto.transformar(false, quitar_caracter)
        1 -> palabraTexto.transformar(false, siguiente_vocal)
        2 -> palabraTexto.transformar(true, sustituir_alfabeto)
        3 -> palabraTexto.transformar(false, siguiente_letra)
        else -> "ERROR: No hay pistas"
    }

    val textoPista = partida.obtenerPista(nPista)
}