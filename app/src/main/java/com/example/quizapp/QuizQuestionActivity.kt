package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionBinding


class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var binding:ActivityQuizQuestionBinding
    private var mCurrentPosition:Int =1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOptionPosition:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mQuestionList = Constants.getQuestion()
        setQuestion()

        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)
    }
    private fun setQuestion(){
        mCurrentPosition=1
        defaultOptionView()
        val question = mQuestionList!![mCurrentPosition -1]
        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" +binding.progressBar.max
        binding.tvQuestion.text =question!!.question
        binding.ivImage.setImageResource(question.Image)
        binding.tvOption1.text =question.option1
        binding.tvOption2.text = question.option2
        binding.tvOption3.text = question.option3
        binding.tvOption4.text = question.option4
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0,binding.tvOption1)
        options.add(1,binding.tvOption2)
        options.add(2,binding.tvOption3)
        options.add(3,binding.tvOption4)


        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvOption1 ->{
                selectedOptionView(binding.tvOption1,1)
            }
            R.id.tvOption1 ->{
                selectedOptionView(binding.tvOption2,2)
            }
            R.id.tvOption3 ->{
                selectedOptionView(binding.tvOption3,3)
            }
            R.id.tvOption4 ->{
                selectedOptionView(binding.tvOption4,4)
            }
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
}