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
    //색깔 배열
    //웜톤
    String []WarmColor= {"#c087cb","#fed4d5","#FEAFA2","#FFE10B","#017f73","#40388e"," #af5463","#9a9342"};
    String []CoolColor= {"#C0BEB2","#84CAEB"," #7d7a99","#f6335f",};




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
                backcolor.setBackgroundColor(Color.parseColor(WarmColor[0]));
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backcolor.setBackgroundColor(Color.parseColor(WarmColor[0]));
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