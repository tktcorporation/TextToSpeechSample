package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var textToSpeech: TextToSpeech? = null
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textToSpeech = TextToSpeech(this, this)
        setContentView(R.layout.activity_main)

        speech_button.setOnClickListener {
            startSpeak("てすとてすとてすとてすと", true);
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech?.let { tts ->
                val locale = Locale.JAPAN
                if (tts.isLanguageAvailable(locale) > TextToSpeech.LANG_AVAILABLE) {
                    tts.language = Locale.JAPAN
                } else {
                    Log.e(TAG, "onInit: 言語の設定に失敗")
                }
            }


        } else {
            // Tts init 失敗
            Log.e(TAG, "onInit: TTS init 失敗")
        }
    }

    private fun startSpeak(text: String, isImmediately: Boolean){
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
    }

    override fun onDestroy() {
        textToSpeech?.shutdown()
        super.onDestroy()
    }
}