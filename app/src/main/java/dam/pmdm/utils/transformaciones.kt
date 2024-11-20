package dam.pmdm.utils

import kotlin.random.Random

val quitar_caracter={car:Char, pos:Int->
    if (Random.nextInt(0,3) == 0) '_'
    else car
}

val siguiente_vocal={car:Char, pos:Int->
    when (car) {
        'a' -> 'e'
        'e' -> 'i'
        'i' -> 'o'
        'o' -> 'u'
        'u' -> 'a'
        else -> car
    }
}

val sustituir_alfabeto= { car: Char, pos: Int ->
    if(pos%2==0) (car.lowercaseChar().code - 'a'.code+1).toChar()
    car
}

val siguiente_letra= { car: Char, pos: Int ->
    Char(car.lowercaseChar().code + 1)
}