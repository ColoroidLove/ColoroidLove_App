package com.example.coloroidlove;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
        import androidx.annotation.NonNull;
        import android.Manifest;
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
        import android.widget.Button;
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
    Button btnYes, btnNo;
    //색깔 배열 홀수 웜 짝수 쿨
    String []FirstColor={" ","#fed4d5","#C0BEB2","#FEAFA2","#84CAEB","#FFE10B","#7d7a99","#017f73","#f6335f",
                        "#40388e","#3ED186","#af5463","1C372E","#9a9342","#602F67"};

    String []SpringLight={"#c087cb","#a9d88a","#ffeea0","#fed4d5"};
    String []SpringBright={"#F23C13","#FEAFA2","#FFE10B","#C98715"};

    String []FallMute={"#af5463","#936b52", "#9a9342","#375c77"};
    String []FallString={"#40388e","#017f73","#e8bb24","#fe3018"};
    String []FallDeep={"#9f004","#49014","#01564","#b5870"};

    String []SummerLight={"#C0BEB2","#84CAEB","#33CFC4","#F5A9B6"};
    String []SummerMute={"#7E465A","#584865","#7C798B","#D29AB3"};
    String []SummerBright={"#f6335f","#ff9aca","#5b72fe","#33dbc1"};
    String []SummerLowBrightMute={"#e4a6b1" ,"#8fbdd4","#7d7a99" ,"#3da8a0"};

    String []WinterTrue={"#3B4346","#3ed186","#a8e8ef","#d9364e"};
    String []WinterBright={"#d7d1ed","#1fa793","#602f67","#2f2f6d"};
    String []WinterDeep={"#000000","283025","1c6a98","9e2532"};


    int cnt=1;
    //웜 쿨 구분하기
    int Warm=0;
    int Cool=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);




        //권한체크
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("허용합니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();


        Button buttonStartCameraPreview = (Button)findViewById(R.id.startcamerapreview);
        Button buttonStopCameraPreview = (Button)findViewById(R.id.stopcamerapreview);

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






        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //인덱스 홀짝
                if((cnt-1)%2==0){
                    Warm++;
                }
                else Cool++;


                backcolor.setBackgroundColor(Color.parseColor(FirstColor[cnt++]));


                Toast.makeText(getApplicationContext(), "쿨 : "+Cool+" 웜 : "+Warm ,Toast.LENGTH_SHORT).show();

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인덱스 홀짝

                backcolor.setBackgroundColor(Color.parseColor(FirstColor[cnt++]));

                Toast.makeText(getApplicationContext(), "쿨 : "+Cool+" 웜 : "+Warm ,Toast.LENGTH_SHORT).show();
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