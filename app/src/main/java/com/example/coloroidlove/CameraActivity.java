package com.example.coloroidlove;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
import androidx.annotation.NonNull;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
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
import android.widget.Toast;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import java.io.IOException;
import java.util.ArrayList;

public class CameraActivity extends BaseActivity implements SurfaceHolder.Callback {


    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;
    LayoutInflater controlInflater = null;
    LinearLayout backcolor;
    ImageView btnYes, btnNo;
    Button btnEnd;
    String res="";

    //색깔 배열 홀수 웜 짝수 쿨
    String []FirstColor={" ","#fed4d5","#C0BEB2","#FEAFA2","#84CAEB","#FFE10B","#7d7a99","#017f73","#f6335f",
            "#40388e","#3ED186","#af5463","1C372E","#9a9342","#602F67"};



    String [] WarmName={"봄라이트","봄브라이트","가을뮤트","가을스트롱","가을딥"};
    String [] CoolName={"여름라이트","여름뮤트","여름뮤트","저명도뮤트","겨울쿨","겨울브라이트","겨울딥"};

    String []SpringLight={"#c087cb","#a9d88a","#ffeea0","#fed4d5"};
    String []SpringBright={"#F23C13","#FEAFA2","#FFE10B","#C98715"};

    String []FallMute={"#af5463","#936b52", "#9a9342","#375c77"};
    String []FallStrong={"#40388e","#017f73","#fe3018","#e8bb24"};
    String []FallDeep={"#9f0040","#49014D","#015641","#b58700"};

    String []SummerLight={"#C0BEB2","#84CAEB","#33CFC4","#F5A9B6"};
    String []SummerMute={"#7E465A","#584865","#7C798B","#D29AB3"};
    String []SummerBright={"#f6335f","#ff9aca","#5b72fe","#33dbc1"};
    String []SummerLowBrightMute={"#e4a6b1" ,"#8fbdd4","#7d7a99" ,"#3da8a0"};

    String []WinterTrue={"#3B4346","#3ed186","#a8e8ef","#d9364e"};
    String []WinterBright={"#d7d1ed","#1fa793","#602f67","#2f2f6d"};
    String []WinterDeep={"#000000","#283025","#1c6a98","#9e2532"};




    int cnt=1; //각 테스트 배열 인덱스 증가
    int btnCount=0; //다음배열으로 넘어갈 때 인덱스
    int Warm=0;
    int Cool=0;
    int chkTest=1; //1=첫번째 테스트 2=웜 테스트 3=쿨 테스트

    int []warmCount=new int[5]; //각 웜 퍼스널컬러의 갯수 봄라이트 봄브라이트 가을뮤트 가을스트롱 가을딥
    int []coolCount=new int[7]; //각 쿨 퍼스널컬러의 갯수 여름라이트 여름뮤트 여름브라이트 저명도뮤트 겨울쿨 겨을브라이트 겨울딥
    public void ColorSort(int[] color, int c){
        int max=color[0];
        int key=0;
        for(int i=0; i<color.length; i++){
            if(max<color[i])
                key=i;


        }

        if(c==2) {
            res=WarmName[key];
            System.out.println("최종 정렬 후 : "+WarmName[key]);
        }
        else {
            res=CoolName[key];
            System.out.println("최종 정렬 후 : "+CoolName[key]);
        }

        chkTest=4;





    }
    public void FirstTestYes() {
        //인덱스 홀짝
        if((cnt-1)%2==0) Warm++;

        else Cool++;

        System.out.println("웜 : "+Warm);
        System.out.println("쿨 : "+Cool);
        backcolor.setBackgroundColor(Color.parseColor(FirstColor[cnt++]));

        if(cnt==11){        cnt=0;
            if(Warm>Cool || Warm==Cool) {
                System.out.println("웜테스트 시작"); chkTest=2;
            }

            else { System.out.println("쿨테스트 시작"); chkTest=2; chkTest=3;}
        }

    }

    public void FirstTestNo() {
        //인덱스 홀짝
        backcolor.setBackgroundColor(Color.parseColor(FirstColor[cnt++]));

        if(cnt==11){        cnt=0;
            if(Warm>Cool || Warm==Cool) {
                System.out.println("웜테스트 시작"); chkTest=2;
            }
            else {System.out.println("쿨테스트 시작"); chkTest=3;}
        }
    }

    public void WarmTestYes() {


        if(cnt==4) cnt=0;

        switch (btnCount/4){
            case 0 :  backcolor.setBackgroundColor(Color.parseColor(SpringLight[cnt++])); warmCount[0]++; break;
            case 1 :  backcolor.setBackgroundColor(Color.parseColor(SpringBright[cnt++]));warmCount[1]++; break;
            case 2 :  backcolor.setBackgroundColor(Color.parseColor(FallMute[cnt++])); warmCount[2]++; break;
            case 3 :  backcolor.setBackgroundColor(Color.parseColor(FallStrong[cnt++])); warmCount[3]++; break;
            case 4 :  backcolor.setBackgroundColor(Color.parseColor(FallDeep[cnt++])); warmCount[4]++; break;
        }
        btnCount++;


        System.out.println("봄라이트 : "+ warmCount[0]);
        System.out.println("봄브라이트 : "+ warmCount[1]);
        System.out.println("가을뮤트 : "+warmCount[2]);
        System.out.println("가을스트롱 : "+warmCount[3]);
        System.out.println("가을딥 : "+warmCount[4]);

        if(btnCount==21) ColorSort(warmCount,chkTest);



    }

    public void WarmTestNo() {


        if(cnt==4) cnt=0;

        switch (btnCount/4){
            case 0 :  backcolor.setBackgroundColor(Color.parseColor(SpringLight[cnt++])); break;
            case 1 :  backcolor.setBackgroundColor(Color.parseColor(SpringBright[cnt++])); break;
            case 2 :  backcolor.setBackgroundColor(Color.parseColor(FallMute[cnt++]));  break;
            case 3 :  backcolor.setBackgroundColor(Color.parseColor(FallStrong[cnt++])); break;
            case 4 :  backcolor.setBackgroundColor(Color.parseColor(FallDeep[cnt++]));  break;
        }
        btnCount++;

        System.out.println("봄라이트 : "+ warmCount[0]);
        System.out.println("봄브라이트 : "+ warmCount[1]);
        System.out.println("가을뮤트 : "+warmCount[2]);
        System.out.println("가을스트롱 : "+warmCount[3]);
        System.out.println("가을딥 : "+warmCount[4]);
        if(btnCount==21) ColorSort(warmCount,chkTest);
    }

    public void CoolTestYes() {

        if(cnt==4) cnt=0;

        switch (btnCount/4){
            case 0 :  backcolor.setBackgroundColor(Color.parseColor(SummerLight[cnt++])); coolCount[0]++; break;
            case 1 :  backcolor.setBackgroundColor(Color.parseColor(SummerMute[cnt++]));coolCount[1]++; break;
            case 2 :  backcolor.setBackgroundColor(Color.parseColor(SummerBright [cnt++])); coolCount[2]++; break;
            case 3 :  backcolor.setBackgroundColor(Color.parseColor(SummerLowBrightMute [cnt++])); coolCount[3]++; break;
            case 4 :  backcolor.setBackgroundColor(Color.parseColor(WinterTrue [cnt++])); coolCount[4]++; break;
            case 5 :  backcolor.setBackgroundColor(Color.parseColor(WinterBright  [cnt++])); coolCount[5]++; break;
            case 6 :  backcolor.setBackgroundColor(Color.parseColor(WinterDeep  [cnt++])); coolCount[6]++; break;

        }
        btnCount++;
        System.out.println("여름라이트 : "+ coolCount[0]);
        System.out.println("여름뮤트 : "+ coolCount[1]);
        System.out.println("여름브라이트 : "+coolCount[2]);
        System.out.println("저명도뮤트 : "+coolCount[3]);
        System.out.println("윈터트루 : "+coolCount[4]);
        System.out.println("윈터브라이트 : "+coolCount[5]);
        System.out.println("윈터딥 : "+coolCount[6]);
        if(btnCount==29) ColorSort(coolCount,chkTest);
    }

    public void CoolTestNo() {
        if(cnt==4) cnt=0;

        switch (btnCount/4){
            case 0 :  backcolor.setBackgroundColor(Color.parseColor(SummerLight[cnt++]));  break;
            case 1 :  backcolor.setBackgroundColor(Color.parseColor(SummerMute[cnt++])); break;
            case 2 :  backcolor.setBackgroundColor(Color.parseColor(SummerBright[cnt++]));  break;
            case 3 :  backcolor.setBackgroundColor(Color.parseColor(SummerLowBrightMute [cnt++])); break;
            case 4 :  backcolor.setBackgroundColor(Color.parseColor(WinterTrue[cnt++])); break;
            case 5 :  backcolor.setBackgroundColor(Color.parseColor(WinterBright[cnt++]));  break;
            case 6 :  backcolor.setBackgroundColor(Color.parseColor(WinterDeep[cnt++]));break;

        }
        btnCount++;
        System.out.println("여름라이트 : "+ coolCount[0]);
        System.out.println("여름뮤트 : "+ coolCount[1]);
        System.out.println("여름브라이트 : "+coolCount[2]);
        System.out.println("저명도뮤트 : "+coolCount[3]);
        System.out.println("윈터트루 : "+coolCount[4]);
        System.out.println("윈터브라이트 : "+coolCount[5]);
        System.out.println("윈터딥 : "+coolCount[6]);
        if(btnCount==29) ColorSort(coolCount,chkTest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent1 = getIntent(); //과거 인텐트
        String nickname = intent1.getStringExtra("닉네임");

        Intent intent = new Intent(CameraActivity.this, ResultActivity.class);
        System.out.println("닉네임 값 확인 : "+nickname);




        //권한체크
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("허용합니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();




        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
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

        backcolor=viewControl.findViewById(R.id.backcolor);
        btnYes=viewControl.findViewById(R.id.btnYes);
        btnNo=viewControl.findViewById(R.id.btnNo);
        btnEnd=viewControl.findViewById(R.id.btnEnd);


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
                    // 원하는 기능 구현
                    finish();           // 앱 종료
                }
            });
        }
    });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (chkTest){
                    case 1: FirstTestYes(); break;
                    case 2: WarmTestYes(); break;
                    case 3: CoolTestYes(); break;
                    case 4: intent.putExtra("닉네임", nickname);  intent.putExtra("퍼스널컬러", res); startActivity(intent); break;
                }





            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (chkTest){
                    case 1: FirstTestNo(); break;
                    case 2: WarmTestNo(); break;
                    case 3: CoolTestNo(); break;
                    case 4:  intent.putExtra("닉네임", nickname);   intent.putExtra("퍼스널컬러", res); startActivity(intent); break;
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

        if(previewing){
            camera.stopPreview();
            previewing = false;

        }
        if (camera != null){
            //카메라 각도
            int rotation = getWindowManager().getDefaultDisplay().getRotation();

            int degrees = 0;

            switch (rotation) {

                case Surface.ROTATION_0: degrees = 0; break;

                case Surface.ROTATION_90: degrees = 90; break;

                case Surface.ROTATION_180: degrees = 180; break;

                case Surface.ROTATION_270: degrees = 270; break;

            }

            int result  = (90 - degrees + 360) % 360;

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
            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }
    };

}