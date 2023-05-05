package ir.radindev.calculatorxiaomi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import ir.radindev.calculatorxiaomi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var operator: Char = '+'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumbersClicked()
        onOperatorClicked()

    }

    private fun onOperatorClicked() {
        binding.btnjam.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("+")
                }
            }

        }

        binding.btnMenha.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("-")
                }
            }
        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("*")
                }
            }
        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                var myChar = binding.txtExpression.text.last()
                if (myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("/")
                }
            }
        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnParantezBaste.setOnClickListener {
            appendText(")")
        }
        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }
        binding.btnPakidan.setOnClickListener {
            val oldtext = binding.txtExpression.text.toString()

            if (oldtext.isNotEmpty()) {

                binding.txtExpression.text = oldtext.substring(0, oldtext.length - 1)
            }
        }


    }

    private fun onNumbersClicked() {

        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() ||binding.txtJavab.text.isNotEmpty() ) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")) {

                appendText(".")
            }

        }
        binding.btnmosavi.setOnClickListener {
            try {


                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()

                val longResult = result.toLong()


                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }

            } catch (e: Exception) {
                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this, "ارور رخ داد !", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun appendText(newtext: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtJavab.text = ""

        binding.txtExpression.append(newtext)


        val viewTree: ViewTreeObserver = binding.HorizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.HorizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.HorizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }
        })

        val viewTree2: ViewTreeObserver = binding.HorizontalScrollViewTxtJavab.viewTreeObserver
        viewTree2.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.HorizontalScrollViewTxtJavab.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.HorizontalScrollViewTxtJavab.scrollTo(binding.txtExpression.width, 0)
            }
        })


    }


}