package com.ipsoft.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ipsoft.calculadora.constants.*
import com.ipsoft.calculadora.model.CalcVisor
import com.ipsoft.calculadoraviewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lista de botoes da calculadora

        val listButton = listOf<View>(
            btnNum0, btnNum1, btnNum2, btnNum3,
            btnNum4, btnNum5, btnNum6, btnNum7,
            btnNum8, btnNum9, btnPonto, btnAC,
            btnLimpa, btnMais, btnMenos, btnMultiplica,
            btnDivide, btnIgual, btnSinal
        )

        for (x in listButton) {
            if (x is Button) {
                x.text = getBtnConstant(x).toString()
            }
        }

        for (x in listButton) {
            x.click()
        }

        model.getNumbers().observe(this, Observer<CalcVisor> {

            tvNumbers.text = it.number
            tvCalc.text = it.calc

        })

    }

    private fun getBtnConstant(x: Any): Any? {

        return when (x) {
            btnNum0 -> N0
            btnNum1 -> N1
            btnNum2 -> N2
            btnNum3 -> N3
            btnNum4 -> N4
            btnNum5 -> N5
            btnNum6 -> N6
            btnNum7 -> N7
            btnNum8 -> N8
            btnNum9 -> N9
            btnPonto -> POINT
            btnSinal -> SIGNAL
            btnLimpa -> BACK
            btnAC -> AC
            btnMais -> PLUS
            btnMenos -> MINUS
            btnMultiplica -> MULTIPLY
            btnDivide -> DIVIDE
            btnIgual -> EQUAL
            else -> null
        }

    }

    private fun View.click() {
        this.setOnClickListener {
            send(it)
        }
    }

    fun send(view: View) {

        val c = getBtnConstant(view)
        if (c != null) {

            model.setClick(c)

        }

    }

}




