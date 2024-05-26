package com.example.quizkotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizkotlin.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {

    private var name: String ?= null
    private var byteArray: ByteArray ?= null
    private var bitmap: Bitmap ?= null

    private var score: Int = 0

    private lateinit var binding: ActivityQuestionBinding

    private var questionList: ArrayList<QuestionData> ?= null
    private var currentPosition: Int = 1
    private var selectedOption: Int = 0

    private var isSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent?.getStringExtra("name")
        byteArray = intent.getByteArrayExtra("image")
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        setNameAndAvatar()

        questionList = setData.getQuestion()
        setQuestion()

        onCLickOption()
        onClickSubmit()

    }

    private fun setNameAndAvatar(){
        binding.tvName.text = name.toString()
        binding.avatarQue.setImageBitmap(bitmap)
    }

    private fun setQuestion()
    {
        var question =  questionList!![currentPosition-1]
        setOptionStyle()

        binding.progressBar.progress = currentPosition
        binding.progressBar.max = questionList!!.size
        binding.progressText.text = "${currentPosition}" + "/" + "${questionList!!.size}"

        binding.tvQuestion.text = question.question
        binding.option1.text = question.option_1
        binding.option2.text = question.option_2
        binding.option3.text = question.option_3
        binding.option4.text = question.option_4
    }

    private fun onCLickOption(){
        binding.option1.setOnClickListener {
            isSelected = true
            selectedOption(binding.option1, 1)
        }

        binding.option2.setOnClickListener {
            isSelected = true
            selectedOption(binding.option2, 2)
        }

        binding.option3.setOnClickListener {
            isSelected = true
            selectedOption(binding.option3, 3)
        }

        binding.option4.setOnClickListener {
            isSelected = true
            selectedOption(binding.option4, 4)
        }
    }

    private fun onClickSubmit(){
        binding.submitButton.setOnClickListener {
            if (selectedOption!=0)
            {
                val question = questionList!![currentPosition-1]
                if (selectedOption!=question!!.correct_ans)
                {
                    setColor(selectedOption, R.drawable.wrong_question_option)
                } else { score++ }
                setColor(question!!.correct_ans, R.drawable.correct_question_option)

                if (currentPosition == questionList!!.size)
                {
                    binding.submitButton.text = "FINISH"
                }
                else
                {
                    binding.submitButton.text = "Go to Next"
                }
            }
            else
            {
                //currentPosition++
                when{
                    !isSelected -> {
                        Toast.makeText(this, "Please Select an Option", Toast.LENGTH_SHORT).show()
                    }
                    isSelected -> {
                        currentPosition++
                        isSelected = false
                        if (currentPosition <= questionList!!.size) {
                            setQuestion()
                            binding.submitButton.text = "SUBMIT"
                        }
                        else {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra("name", name.toString())
                            intent.putExtra("score", score.toString())
                            intent.putExtra("image", byteArray)
                            intent.putExtra("total", questionList!!.size.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
            selectedOption=0
        }
    }

    private fun setColor(opt:Int, color: Int){
        when(opt){
            1 -> {
                binding.option1.background = ContextCompat.getDrawable(this, color)
            }
            2 -> {
                binding.option2.background = ContextCompat.getDrawable(this, color)
            }
            3 -> {
                binding.option3.background = ContextCompat.getDrawable(this, color)
            }
            4 -> {
                binding.option4.background = ContextCompat.getDrawable(this, color)
            }

        }
    }

    private fun setOptionStyle()
    {
        var optionList: ArrayList<TextView> = arrayListOf()

        optionList.add(0, binding.option1)
        optionList.add(1, binding.option2)
        optionList.add(2, binding.option3)
        optionList.add(3, binding.option4)

        for (op in optionList)
        {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }

    }

    private fun selectedOption(view:TextView, opt:Int)
    {
        setOptionStyle()
        selectedOption = opt

        view.setTextColor(Color.parseColor("#000000"))
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
    }

}