package com.example.coloroidlove;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CameraActivity extends BaseActivity implements SurfaceHolder.Callback {


    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;
    LayoutInflater controlInflater = null;
    LinearLayout backcolor1, backcolor2;
    ImageView btnYes1, btnYes2;
    Button btnEnd;
    TextView txtMent, txtCount;
    MediaPlayer mediaPlayer4, mediaPlayer5;
    String ment = "두 컬러 중 무엇이 더 어울리나요?"; //추천 멘트 들어감
    String ment2="두 컬러 중 무엇이 더 어울리나요?";
    int percent1 = 0; //예스 1눌렀을 때 퍼센트
    int percent2 = 0; //에스 2 눌렀을 때 퍼센트
    // 퍼스널 컬러 결과 intent로 전해줄 변수
    String res = "";
    int base; // 0은 warm, 1은 cool
    int index;
    int count = 1;

    //색깔 배열 홀수 웜 짝수 쿨
    String[] FirstColor = {"#9a9342","#F5A9B6", "#9a9342", "#C0BEB2", "#FEAFA2", "#84CAEB", "#FFE10B", "#7d7a99", "#017f73", "#f6335f",
            "#40388e", "#3ed186", "#af5463", "#33dbc1"};



    //봄라이트, 봄브라이트, 가을뮤트, 가을스트롱, 가을딥
    String[] WarmColor = {
            "#c087cb", "#FEAFA2", "#af5463", "#40388e", "#9f0040",
            "#a9d88a", "#FFE10B", "#936b52", "#017f73", "#49014D",
            "#ffeea0", "#C98715", "#9a9342", "#fe3018", "#015641",
            "#fed4d5", "#C98715", "#375c77", "#e8bb24", "#b58700"

    };

    //여름라이트, 여름뮤트, 여름브라이트, 저명도여름뮤트, 겨울트루, 겨울브라이트, 겨울딥
    String[] CoolColor = {
            "#C0BEB2","#7E465A","#f6335f","#e4a6b1","#3B4346","#d7d1ed","#000000",
            "#84CAEB","#584865","#ff9aca","#8fbdd4","#3ed186","#1fa793","#283025",
            "#33CFC4","#7C798B","#5b72fe","#7d7a99","#a8e8ef","#602f67","#1c6a98",
            "#F5A9B6","#D29AB3","#33dbc1","#3da8a0","#d9364e","#2f2f6d","#9e2532"
    };
    String[] WarmName = {"사랑스러운 봄라이트", "생기 있는 봄브라이트", "내추럴한 가을 뮤트", "고급스러운 가을 스트롱", "섹시한 가을딥"};
    String[] CoolName = {"싱그러운 여름라이트", "소프트한 여름뮤트", "청량가득한 여름브라이트", "부드러운 저명도여름뮤트", "시크한 겨울트루", "시원한 겨울브라이트", "도도한 겨울딥"};

    String[] Warmment = {"봄라이트", "봄브라이트", "가을뮤트", "가을스트롱", "가을딥"};
    String[] Coolment = {"여름라이트", "여름뮤트", "여름브라이트", "저명도여름뮤트", "겨울트루", "겨울브라이트", "겨울딥"};



    int cnt = 2; //각 테스트 배열 인덱스 증가
    int btnCount1 = 0; //다음배열으로 넘어갈 때 인덱스
    int btnCount2 = 1; //다음배열으로 넘어갈 때 인덱스
    int Warm = 0;
    int Cool = 0;
    int chkTest = 1; //1=첫번째 테스트 2=웜 테스트 3=쿨 테스트

    int[] warmCount = new int[5]; //각 웜 퍼스널컬러의 갯수 봄라이트 봄브라이트 가을뮤트 가을스트롱 가을딥
    int[] coolCount = new int[7]; //각 쿨 퍼스널컬러의 갯수 여름라이트 여름뮤트 여름브라이트 저명도뮤트 겨울쿨 겨을브라이트 겨울딥


    public void ColorSort(int[] color, int c) {
        int max = color[0];
        int key = 0;
        int cnt=0;
        int []sameColor= new int[color.length];




        //중복값 처리
        //1.최대값 인덱스를 구함
        for (int i = 0; i < color.length; i++) {
            if (max < color[i])
                max=color[i];//key = i;

        }

        //2.최대값 중복이 있으면 새로운배열에 인덱스값을 넣어준다
        for(int i=0; i<color.length; i++){
            if(max==color[i]) {
                System.out.println("중복 인덱스 :  "+ i);
                sameColor[cnt] = i;
                cnt++;

            }
        }

        //3.same배열에 값이 들어간만큼 난수 범위를 맞춘다
        int rn= (int) (Math.random() *cnt);

        //4.키값에 난수 넣기
        key=sameColor[rn];
        System.out.println("key 값 : "+key);
        if (c == 2) {
            res = WarmName[key]; // 결과 넣기
            base = 0;
            index = key;
            System.out.println("최종 정렬 후 : " + WarmName[key]);
        } else {
            res = CoolName[key]; // 결과 넣기
            base = 1;
            index = key;
            System.out.println("base : " + base);
            System.out.println("최종 정렬 후 : " + CoolName[key]);
        }

        chkTest = 4;


    }

    public void FirstWarmTest() {

        if (cnt == FirstColor.length) {
            count=1;
            cnt = 0;
            txtCount.setText(count+"/10");

            ment = "두 컬러 중 무엇이 더 어울리나요?";
            System.out.println(ment);
            if (Warm > Cool || Warm == Cool) {

                System.out.println("웜테스트 시작");
                chkTest = 2;

            } else {

                System.out.println("쿨테스트 시작");
                chkTest = 3;

            }
        }
        else{
            count++;
            txtCount.setText(count+"/8");
        }

        if (Warm > Cool) {
            ment="현재 웜에 가까워요!";
        } else if(Warm<Cool) {
            ment = "현재 쿨에 가까워요!";
        }
        else ment = "두 컬러 중 무엇이 더 어울리나요?";


        Warm++;

        System.out.println("웜 : " + Warm);
        System.out.println("쿨 : " + Cool);

        backcolor1.setBackgroundColor(Color.parseColor(FirstColor[cnt]));
        backcolor2.setBackgroundColor(Color.parseColor(FirstColor[cnt+1]));
        cnt+=2;




    }

    public void FirstCoolTest() {

        if (cnt == FirstColor.length) {
            count=1;
            cnt = 0;
            txtCount.setText(count+"/14");
            ment = "두 컬러 중 무엇이 더 어울리나요?";
            if (Warm > Cool || Warm == Cool) {

                System.out.println("웜테스트 시작");
                chkTest = 2;

            } else {

                System.out.println("쿨테스트 시작");
                chkTest = 3;

            }
        }
        else{
            count++;
            txtCount.setText(count+"/8");
        }

        if (Warm > Cool) {
            ment="현재 웜에 가까워요!";
        } else if(Warm<Cool) {
            ment = "현재 쿨에 가까워요!";
        }
        else ment = "두 컬러 중 무엇이 더 어울리나요?";


        Cool++;

        System.out.println("웜 : " + Warm);
        System.out.println("쿨 : " + Cool);
        backcolor1.setBackgroundColor(Color.parseColor(FirstColor[cnt]));
        backcolor2.setBackgroundColor(Color.parseColor(FirstColor[cnt+1]));
        cnt+=2;
        //배열의 마지막 인덱스일 때 테스트 인덱스를 정해준다


    }

    public void SecondWarmTest1() {

        ment2="현재 "+Warmment[btnCount1]+"에 가까워요";
        warmCount[btnCount1]++;
        count++;
        txtCount.setText(count + "/10");

        backcolor1.setBackgroundColor(Color.parseColor(WarmColor[cnt]));
        backcolor2.setBackgroundColor(Color.parseColor(WarmColor[cnt + 1]));
        cnt += 2;

        btnCount1 += 2;
        if (btnCount1 == 6) {
            btnCount1 = 1;
        } else if (btnCount1 == 5) {
            btnCount1 = 0;
        }

        if (WarmColor.length <= cnt) {
            warmCount[btnCount1]++;
            count=0;
            ColorSort(warmCount, chkTest);
        }
        System.out.println("봄라이트 : " + warmCount[0]);
        System.out.println("봄브라이트 : " + warmCount[1]);
        System.out.println("가을뮤트 : " + warmCount[2]);
        System.out.println("가을스트롱 : " + warmCount[3]);
        System.out.println("가을딥 : " + warmCount[4]);


    }

    public void SecondWarmTest2() {
        ment2="현재 "+Warmment[btnCount2]+"에 가까워요";
        warmCount[btnCount2]++;
        count++;
        txtCount.setText(count+"/10");

        backcolor1.setBackgroundColor(Color.parseColor(WarmColor[cnt]));
        backcolor2.setBackgroundColor(Color.parseColor(WarmColor[cnt+1]));
        cnt+=2;

        btnCount2+=2;

        if(btnCount2==6){
            btnCount2=1;
        }
        else if(btnCount2==5){
            btnCount2=0;
        }

        if (WarmColor.length == cnt) {
            warmCount[btnCount2]++;
            count=0;
            ColorSort(warmCount, chkTest);
        }


        System.out.println("봄라이트 : " + warmCount[0]);
        System.out.println("봄브라이트 : " + warmCount[1]);
        System.out.println("가을뮤트 : " + warmCount[2]);
        System.out.println("가을스트롱 : " + warmCount[3]);
        System.out.println("가을딥 : " + warmCount[4]);



    }

    public void SecondCoolTest1() {
        ment2="현재 "+Coolment[btnCount1]+"에 가까워요";
        coolCount[btnCount1]++;
        count++;
        txtCount.setText(count+"/14");

        backcolor1.setBackgroundColor(Color.parseColor(CoolColor[cnt]));
        backcolor2.setBackgroundColor(Color.parseColor(CoolColor[cnt+1]));
        cnt+=2;



        btnCount1+=2;


        if(btnCount1==6){
            btnCount1=1;
        }
        else if(btnCount1==5){
            btnCount1=0;
        }

        if (CoolColor.length == cnt) {
            coolCount[btnCount1]++;
            count=0;
            ColorSort(coolCount, chkTest);
        }

        System.out.println("여름라이트 : " + coolCount[0]);
        System.out.println("여름뮤트 : " + coolCount[1]);
        System.out.println("여름브라이트 : " + coolCount[2]);
        System.out.println("저명도뮤트 : " + coolCount[3]);
        System.out.println("윈터트루 : " + coolCount[4]);
        System.out.println("윈터브라이트 : " + coolCount[5]);
        System.out.println("윈터딥 : " + coolCount[6]);





    }

    public void SecondCoolTest2() {
        ment2="현재 "+Coolment[btnCount2]+"에 가까워요";
        coolCount[btnCount2]++;
        count++;
        txtCount.setText(count+"/14");

        backcolor1.setBackgroundColor(Color.parseColor(CoolColor[cnt]));
        backcolor2.setBackgroundColor(Color.parseColor(CoolColor[cnt+1]));
        cnt+=2;


        btnCount2+=2;


        if(btnCount2==6){
            btnCount2=1;
        }
        else if(btnCount2==5){
            btnCount2=0;
        }
        if (CoolColor.length == cnt) {
            coolCount[btnCount2]++;
            count=0;
            ColorSort(coolCount, chkTest);
        }

        System.out.println("여름라이트 : " + coolCount[0]);
        System.out.println("여름뮤트 : " + coolCount[1]);
        System.out.println("여름브라이트 : " + coolCount[2]);
        System.out.println("저명도뮤트 : " + coolCount[3]);
        System.out.println("윈터트루 : " + coolCount[4]);
        System.out.println("윈터브라이트 : " + coolCount[5]);
        System.out.println("윈터딥 : " + coolCount[6]);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent1 = getIntent(); //과거 인텐트

        String nickname = intent1.getStringExtra("닉네임");
        System.out.println("닉네임 값 확인 : " + nickname);

        Intent intent = new Intent(CameraActivity.this, ResultActivity.class);


        mediaPlayer4 = MediaPlayer.create(this, R.raw.button);
        mediaPlayer5 = MediaPlayer.create(this, R.raw.button);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        //오버레이
        controlInflater = LayoutInflater.from(getBaseContext());
        View viewControl = controlInflater.inflate(R.layout.overlay, null);
        ViewGroup.LayoutParams layoutParamsControl
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        this.addContentView(viewControl, layoutParamsControl);

        backcolor1 = viewControl.findViewById(R.id.backcolor1);
        backcolor2 = viewControl.findViewById(R.id.backcolor2);
        btnYes1 = viewControl.findViewById(R.id.btnYes1);
        btnYes2 = viewControl.findViewById(R.id.btnYes2);
        btnEnd = viewControl.findViewById(R.id.btnEnd);
        txtMent = viewControl.findViewById(R.id.txtMent);
        txtCount=viewControl.findViewById(R.id.txtCount);
        Dialog dilaog02;

        dilaog02 = new Dialog(CameraActivity.this);       // Dialog 초기화
        dilaog02.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog02.setContentView(R.layout.dialog2);             // xml 레이아웃 파일과 연결


        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilaog02.show();
                Button noBtn = dilaog02.findViewById(R.id.noBtn);
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoClass(MainActivity.class);
                        dilaog02.dismiss(); // 다이얼로그 닫기
                    }
                });
                // 취소
                dilaog02.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dilaog02.cancel();
                    }
                });
            }
        });

        btnYes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mediaPlayer4.start();

                switch (chkTest) {
                    case 1:
                        txtMent.setText(ment);
                        FirstWarmTest();
                        break;
                    case 2:
                        txtMent.setText(ment2);
                        SecondWarmTest1();
                        break;
                    case 3:
                        txtMent.setText(ment2);
                        SecondCoolTest1();
                        break;
                    case 4:
                        btnYes1.setEnabled(false);
                        btnYes2.setEnabled(false);
                        intent.putExtra("닉네임", nickname);
                        intent.putExtra("퍼스널컬러", res);
                        intent.putExtra("베이스컬러", base);
                        intent.putExtra("컬러인덱스", index);
                        startActivity(intent);


                        break;
                }
            }
        });


        btnYes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer5.start();

                switch (chkTest) {
                    case 1:
                        txtMent.setText(ment);
                        FirstCoolTest();
                        break;
                    case 2:
                        txtMent.setText(ment2);
                        SecondWarmTest2();
                        break;
                    case 3:
                        txtMent.setText(ment2);
                        SecondCoolTest2();
                        break;
                    case 4:
                        btnYes1.setEnabled(false);
                        btnYes2.setEnabled(false);
                        intent.putExtra("닉네임", nickname);
                        intent.putExtra("퍼스널컬러", res);
                        intent.putExtra("베이스컬러", base);
                        intent.putExtra("컬러인덱스", index);
                        startActivity(intent);


                        break;
                }
            }
        });

    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        camera = Camera.open(CAMERA_FACING_FRONT);

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
// TODO Auto-generated method stub

        if (previewing) {
            camera.stopPreview();
            previewing = false;

        }
        if (camera != null) {
            //카메라 각도
            int rotation = getWindowManager().getDefaultDisplay().getRotation();

            int degrees = 0;

            switch (rotation) {

                case Surface.ROTATION_0:
                    degrees = 0;
                    break;

                case Surface.ROTATION_90:
                    degrees = 90;
                    break;

                case Surface.ROTATION_180:
                    degrees = 180;
                    break;

                case Surface.ROTATION_270:
                    degrees = 270;
                    break;

            }

            int result = (90 - degrees + 360) % 360;

            camera.setDisplayOrientation(result);


            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                previewing = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // TODO Auto-generated method stub

        camera.stopPreview();
        camera.release();
        camera = null;
        previewing = false;
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }


        //나중에 권한 수정하기
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "권한 불허가", Toast.LENGTH_SHORT).show();
        }
    };

}