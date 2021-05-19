package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionBinding


class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var binding:ActivityQuizQuestionBinding
    private var mCurrentPosition:Int =1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOptionPosition:Int =0
    private var mCorrectAns:Int =0
    private var mUserName:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionList = Constants.getQuestion()
        setQuestion()

        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)

        binding.btnSubmit.setOnClickListener(this)
    }
    private fun setQuestion(){
        defaultOptionView()
        if(mCurrentPosition == mQuestionList!!.size){
            binding.btnSubmit.text ="FINISH"
        }else{
            binding.btnSubmit.text ="SUBMIT"
        }
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
            R.id.btnSubmit->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANS,mCorrectAns)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question =mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAns != mSelectedOptionPosition){
                        ansView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAns++
                    }
                    ansView(question.correctAns,R.drawable.correct_option_border_bg)
                    if(mCurrentPosition == mQuestionList!!.size){
                        binding.btnSubmit.text ="FINISH"
                    } else {
                        binding.btnSubmit.text ="GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition=0
                }

            }
        }
    }

    private fun ansView(answer:Int,drawableView:Int){
        when(answer){
            1 -> {
                binding.tvOption1.background =ContextCompat.getDrawable(this,drawableView)
            }
            2 ->{
                binding.tvOption2.background =ContextCompat.getDrawable(this,drawableView)
            }
            3 ->{
                binding.tvOption3.background =ContextCompat.getDrawable(this,drawableView)
            }
            4 ->{
                binding.tvOption4.background =ContextCompat.getDrawable(this,drawableView)
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