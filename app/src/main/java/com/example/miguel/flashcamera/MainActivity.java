package com.example.miguel.flashcamera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

    Camera camera = null;
    Camera.Parameters camParams;
    boolean isLightON;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        isLightON = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        camera = Camera.open();
        camParams = camera.getParameters();
    }

    @Override
    protected void onStop() {
        super.onStop();
        turnLightOFF();
        camera.release();
    }

    public void turnLightOFF(){
        camParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(camParams);
        camera.stopPreview();
        isLightON = false;
        imageView.setBackgroundResource(R.drawable.off);
    }

    public void switchFlashlight(View view){
        if (isLightON == false){
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(camParams);
            camera.startPreview();
            isLightON = true;
            imageView.setBackgroundResource(R.drawable.on);
        }else{
            turnLightOFF();
        }
    }

}
