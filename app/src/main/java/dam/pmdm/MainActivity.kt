package dam.pmdm

import android.os.Bundle
import android.text.method.KeyListener
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.graphics.drawable.toDrawable
import dam.pmdm.constants.minutos
import dam.pmdm.constants.puntuacion_inicial
import dam.pmdm.constants.segundos
import dam.pmdm.models.JuegoDePalabras
import dam.pmdm.models.Palabra
import dam.pmdm.models.Temporizador
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    lateinit var partida: JuegoDePalabras
    lateinit var tvPalabraModificada: TextView
    lateinit var tvPista: TextView
    lateinit var etPalabra: EditText
    lateinit var btnJugar: Button
    lateinit var btnComprobar : Button
    lateinit var tvReloj: TextView
    lateinit var tvPuntuacion: TextView
    lateinit var ivResultado: ImageView
    var puntuacion = puntuacion_inicial
    lateinit var temporizador : Temporizador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        partida = JuegoDePalabras()
        tvPalabraModificada = findViewById(R.id.tvPalabraModificada)
        tvPista = findViewById(R.id.tvPista)
        etPalabra = findViewById(R.id.etPalabra)
        btnJugar = findViewById(R.id.btnJugar)
        btnComprobar = findViewById(R.id.btnComprobar)
        tvReloj = findViewById(R.id.tvReloj)
        tvPuntuacion = findViewById(R.id.tvPuntos)
        ivResultado = findViewById(R.id.ivResultado)
    }

    override fun onStart() {
        super.onStart()
        establecerInicio()
        val palabra: Palabra = Palabra(
            partida,
            partida.obtenerPalabra(),
            Random.nextInt(0,4)
        )
        btnJugar.setOnClickListener {
            tvPalabraModificada.text = palabra.palabraModificada
            tvPista.text = palabra.textoPista
            temporizador = comenzarPartida()
        }
        btnComprobar.setOnClickListener {
            if (palabra.palabraTexto == palabra.palabraModificada) {
                var suma = palabra.nPista+1+palabra.palabraTexto.length
                if (suma > 10) suma = 10
                puntuacion += suma
            } else {
                puntuacion -= 2
            }
            if (puntuacion >= 50) {
                ganador()
            }
            if (puntuacion <= 0) {
                perdedor()
            }
        }
        if (::temporizador.isInitialized &&temporizador.minutosTemp >= 0
            && temporizador.segundosTemp >= 0) perdedor()
    }

    private fun establecerInicio() {
        tvPalabraModificada.text = ""
        tvPista.text = ""
        etPalabra.tag = etPalabra.keyListener
        etPalabra.keyListener = null
        btnComprobar.isEnabled = false
        tvReloj.text = "$minutos:$segundos"
        btnJugar.isEnabled = true
        tvPuntuacion.text = "Puntuación: $puntuacion"
    }

    private fun comenzarPartida() : Temporizador  {
        etPalabra.keyListener = etPalabra.tag as KeyListener?
        btnComprobar.isEnabled = true
        btnJugar.isEnabled = false
        ivResultado.visibility = View.INVISIBLE
        return Temporizador(minutos, segundos,minutos*60000.toLong(),1000)
    }

    private fun ganador() {
        ivResultado.setImageDrawable(R.drawable.victoria.toDrawable())
        Toast.makeText(this,"HAS GANADO!!",Toast.LENGTH_LONG).show()
        onStart()
    }

    private fun perdedor() {
        ivResultado.setImageDrawable(R.drawable.derrota.toDrawable())
        Toast.makeText(this,"HAS PERDIDO!!",Toast.LENGTH_LONG).show()
        onStart()
    }
}