package com.example.coloroidlove;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    ImageView introImg,heartImg;
    TextView txtTalk, txtDesc1, txtDesc2, txtDesc3, txtDesc4, txtDesc5;
    Button btnJump, btnNext, btnStart;
    int count = 0; //상황 인덱스
    Animation animation, txtAnimation; //애니메이션 변수
    MediaPlayer mediaPlayer, mediaPlayer1, mediaPlayer2, mediaPlayer3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        introImg = findViewById(R.id.introImg);
        heartImg = findViewById(R.id.heartImg);
        txtTalk = findViewById(R.id.txtTalk);
        txtDesc1 = findViewById(R.id.txtDesc1);
        txtDesc2 = findViewById(R.id.txtDesc2);
        txtDesc3 = findViewById(R.id.txtDesc3);
        txtDesc4 = findViewById(R.id.txtDesc4);
        txtDesc5 = findViewById(R.id.txtDesc5);
        btnJump = findViewById(R.id.btnJump);
        btnNext = findViewById(R.id.btnNext);
        btnStart = findViewById(R.id.btnStart);

        mediaPlayer = MediaPlayer.create(this, R.raw.background);
        mediaPlayer.start();


        mediaPlayer1 = MediaPlayer.create(this, R.raw.think);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.what);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.sad);



        //이야기 건너뛰기
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent(); //과거 인텐트
                mediaPlayer.stop();
                String nickname = intent1.getStringExtra("닉네임");
                System.out.println("닉네임 값 확인 : " + nickname);
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                intent.putExtra("닉네임", nickname);
                startActivity(intent);
            }
        });


        //스토리 끝난 후 시작하기
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent(); //과거 인텐트
                mediaPlayer.stop();
                String nickname = intent1.getStringExtra("닉네임");
                System.out.println("닉네임 값 확인 : " + nickname);
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                intent.putExtra("닉네임", nickname);
                startActivity(intent);
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                switch (count) {
                    case 1:
                        mediaPlayer.start();
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        introImg.startAnimation(animation);
                        txtTalk.setText("음.. 내일 화장을 어떻게 하지?");
                        break;

                    case 2:
                        mediaPlayer1.start();
                        txtTalk.setText("미리 연습을 해봐야지!");
                        introImg.setImageResource(R.drawable.intro2); //전구 이미지
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        introImg.startAnimation(animation);
                        break;
                    case 3:
                        txtTalk.setText("화장중");
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        introImg.setImageResource(R.drawable.intro3);
                        break;
                    case 4:
                        mediaPlayer2.start();
                        txtTalk.setText("이게 뭐야 안어울리잖아!");
                        introImg.setImageResource(R.drawable.intro4);
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        introImg.startAnimation(animation);
                        break;
                    case 5:
                        mediaPlayer3.start();
                        txtTalk.setText("내 퍼스널컬러는 대체 뭐야?");
                        introImg.setImageResource(R.drawable.intro5);
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        introImg.startAnimation(animation);
                        break;

                    case 6:
                        mediaPlayer1.start();
                        txtTalk.setText("맞아 내겐 컬러럽이 있지!");
                        introImg.setImageResource(R.drawable.intro6);
                        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                        introImg.startAnimation(animation);
                        break;

                    case 7:
                        btnJump.setVisibility(View.INVISIBLE);
                        txtTalk.setVisibility(View.INVISIBLE);
                        introImg.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.INVISIBLE);


                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                txtDesc1.setVisibility(View.VISIBLE);
                                txtAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                                txtDesc1.startAnimation(txtAnimation);

                                new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        txtDesc2.setVisibility(View.VISIBLE);
                                        txtAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                                        txtDesc2.startAnimation(txtAnimation);
                                        new Handler().postDelayed(new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                txtDesc3.setVisibility(View.VISIBLE);
                                                txtAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                                                txtDesc3.startAnimation(txtAnimation);
                                                new Handler().postDelayed(new Runnable()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        txtDesc4.setVisibility(View.VISIBLE);
                                                        txtAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                                                        txtDesc4.startAnimation(txtAnimation);
                                                        new Handler().postDelayed(new Runnable()
                                                        {
                                                            @Override
                                                            public void run()
                                                            {
                                                                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                                                                heartImg.startAnimation(animation);
                                                                heartImg.setVisibility(View.VISIBLE);
                                                                btnStart.setVisibility(View.VISIBLE);
                                                                txtDesc5.setVisibility(View.VISIBLE);
                                                                txtAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                                                                txtDesc5.startAnimation(txtAnimation);

                                                            }
                                                        }, 800);

                                                    }
                                                }, 800);
                                            }
                                        }, 800);

                                    }
                                }, 800);

                            }
                        }, 800);



                        break;
                }

            }
        });


    }


}