package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private static final int REQ_CODE_SPEECH_INPUT=1000;
    EditText textView;
    Button button;
    Button button2;
    TextToSpeech speech;
    String speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);

        button = (Button) findViewById(R.id.button);

        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!= TextToSpeech.ERROR){
                    speech.setLanguage(new Locale("en", "IN"));

                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });



        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              spoke();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    //get array from voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //set to text view
                    textView.setText(result.get(0));


                }
            }
        }

    }

    private void promptSpeechInput(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"hiiiiiiii");

        try{ startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        }catch (Exception e) {

        }

    }



    private void spoke() {


        speaker= textView.getText().toString();
        char ch[] = speaker.toCharArray();

        for (int i = 0; i < speaker.length(); i++) {

            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {

                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {

                    // Convert into Upper-case
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            }

            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')

                // Convert into Lower-Case
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }

        // Convert the char array to equivalent String
       speaker = new String(ch);





        speaker = speaker.replaceAll("Shashwat", "Maadarchaood");
        speaker = speaker.replaceAll("Shrey", "My inventor");
        speaker = speaker.replaceAll("Hi", "et tu brutus, fuck off");
        speaker = speaker.replaceAll("College", "school");
        speaker = speaker.replaceAll("Rashmi", "jaan hai meri, red heart");
        speaker = speaker.replaceAll("Sex", "you are virgin");
        speaker = speaker.replaceAll("Rinkiya", "hee hee hee hee hus daelae rinkiya k papa");
        speaker = speaker.replaceAll("Srishti", "joe dentist hai, par ap nay aaap ko doctor bowlti hai");
        speaker = speaker.replaceAll("Manisha", "cchhhhhhhhhhhooooottttttuuuuuuu");


        int intIndex = speaker.indexOf("Aunty");
        if(speaker.indexOf("Aunty") != - 1) {
            MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.boloaunty);
            ring.start();}
            else if(speaker.indexOf("Hello") != - 1) {
                MediaPlayer ring2= MediaPlayer.create(MainActivity.this,R.raw.chalbhosdike);
                ring2.start();
        } else {

            speech.speak(speaker, TextToSpeech.QUEUE_FLUSH, null);
        }



    }
}



