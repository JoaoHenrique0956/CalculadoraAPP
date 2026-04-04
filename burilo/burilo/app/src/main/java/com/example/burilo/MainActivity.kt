package com.example.burilo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var primeiroNumero: Double = 0.0
    private var operacao: String = ""
    private var novoDigito: Boolean = true
    private var expressao: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val display = findViewById<TextView>(R.id.TextResult)
        val botoes = mapOf(
            R.id.Clickbtn0 to "0", R.id.Clickbtn1 to "1",
            R.id.Clickbtn2 to "2", R.id.Clickbtn3 to "3",
            R.id.Clickbtn4 to "4", R.id.Clickbtn5 to "5",
            R.id.Clickbtn6 to "6", R.id.Clickbtn7 to "7",
            R.id.Clickbtn8 to "8", R.id.Clickbtn9 to "9"
        )
        for ((id, digito) in botoes) {
            findViewById<Button>(id).setOnClickListener {
                if (novoDigito) {
                    expressao += digito
                    display.text = expressao
                    novoDigito = false
                } else {
                    expressao += digito
                    display.text = expressao
                }
            }
        }
        findViewById<Button>(R.id.ClickbtnV).setOnClickListener {
            val partes = expressao.split("+", "-", "x", "÷")
            val numeroAtual = partes.lastOrNull() ?: ""
            if (!numeroAtual.contains(",")) {
                expressao += if (numeroAtual.isEmpty()) "0," else ","
                display.text = expressao
                novoDigito = false
            }
        }
        findViewById<Button>(R.id.ClickbtnSoma).setOnClickListener {
            val numeroAtual = expressao.split("+", "-", "x", "÷").lastOrNull() ?: return@setOnClickListener
            primeiroNumero = numeroAtual.replace(",", ".").toDoubleOrNull() ?: return@setOnClickListener
            operacao = "+"
            expressao += "+"
            display.text = expressao
            novoDigito = true
        }
        findViewById<Button>(R.id.ClickbtnSubtr).setOnClickListener {
            val numeroAtual = expressao.split("+", "-", "x", "÷").lastOrNull() ?: return@setOnClickListener
            primeiroNumero = numeroAtual.replace(",", ".").toDoubleOrNull() ?: return@setOnClickListener
            operacao = "-"
            expressao += "-"
            display.text = expressao
            novoDigito = true
        }
        findViewById<Button>(R.id.ClickbtnMult).setOnClickListener {
            val numeroAtual = expressao.split("+", "-", "x", "÷").lastOrNull() ?: return@setOnClickListener
            primeiroNumero = numeroAtual.replace(",", ".").toDoubleOrNull() ?: return@setOnClickListener
            operacao = "*"
            expressao += "x"
            display.text = expressao
            novoDigito = true
        }
        findViewById<Button>(R.id.ClickbtnDivi).setOnClickListener {
            val numeroAtual = expressao.split("+", "-", "x", "÷").lastOrNull() ?: return@setOnClickListener
            primeiroNumero = numeroAtual.replace(",", ".").toDoubleOrNull() ?: return@setOnClickListener
            operacao = "/"
            expressao += "÷"
            display.text = expressao
            novoDigito = true
        }
        findViewById<Button>(R.id.ClickbtnIgual).setOnClickListener {
            val partes = expressao.split("+", "-", "x", "÷")
            val segundoNumero = partes.lastOrNull()?.replace(",", ".")?.toDoubleOrNull() ?: return@setOnClickListener
            val resultado = when (operacao) {
                "+" -> primeiroNumero + segundoNumero
                "-" -> primeiroNumero - segundoNumero
                "*" -> primeiroNumero * segundoNumero
                "/" -> {
                    if (segundoNumero == 0.0) {
                        display.text = "Erro"
                        expressao = ""
                        return@setOnClickListener
                    }
                    primeiroNumero / segundoNumero
                }
                else -> return@setOnClickListener
            }
            val resultadoTexto = if (resultado == resultado.toLong().toDouble()) {
                resultado.toLong().toString()
            } else {
                resultado.toString().replace(".", ",")
            }
            display.text = resultadoTexto
            expressao = resultadoTexto.replace(",", ".")
            novoDigito = true
        }
        findViewById<Button>(R.id.ClickBtnClear).setOnClickListener {
            display.text = "0"
            primeiroNumero = 0.0
            operacao = ""
            expressao = ""
            novoDigito = true
        }
    }
}