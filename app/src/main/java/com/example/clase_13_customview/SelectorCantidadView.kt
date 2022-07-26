package com.example.clase_13_customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

const val CANTIDAD_MAXIMA = 100
const val CANTIDAD_MINIMA = 0

class SelectorCantidadView @JvmOverloads constructor(
    context: Context, val attrs: AttributeSet? = null,
    var defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var imgAgregar: ImageButton
    private lateinit var imgRemover: ImageButton
    private lateinit var txtNumero: TextView
    private var cantidadMaxima = CANTIDAD_MAXIMA
    private var cantidadMinima = CANTIDAD_MINIMA

    init {
        View.inflate(context, R.layout.view_selector_cantidad, this)
        initializeAttrs()
        setupUI()
        setupClickListeners()
        orientation = HORIZONTAL
    }

    private fun initializeAttrs() {
        attrs?.let {
            val miContador =
                context.theme.obtainStyledAttributes(
                    it,
                    R.styleable.SelectorCantidadView,
                    defStyleAttr,
                    0
                )
            cantidadMaxima =
                miContador.getInt
            (R.styleable.SelectorCantidadView_cantidadMaxima, CANTIDAD_MAXIMA)
            cantidadMinima =
                miContador.getInt(R.styleable.SelectorCantidadView_cantidadMinima, CANTIDAD_MINIMA)
        }
    }

    private fun setupUI() {
        imgAgregar = findViewById(R.id.imgAgregar)
        imgRemover = findViewById(R.id.imgRemover)
        imgRemover.isEnabled = false
        txtNumero = findViewById(R.id.txtNumero)
    }

    private fun setupClickListeners() {
        imgAgregar.setOnClickListener { sumar() }
        imgRemover.setOnClickListener { restar() }
    }

    private fun sumar() {
        txtNumero.text = (txtNumero.text.toString().toInt() + 1).toString()
        validarCantidad()
    }

    private fun restar() {
        txtNumero.text = (txtNumero.text.toString().toInt() - 1).toString()
        validarCantidad()
    }

    private fun validarCantidad() {
        val numero = txtNumero.text.toString().toInt()
        when (numero) {
            cantidadMinima -> imgRemover.isEnabled = false
            cantidadMaxima -> imgAgregar.isEnabled = false
            else -> {
                imgRemover.isEnabled = true
                imgAgregar.isEnabled = true
            }
        }
    }
}