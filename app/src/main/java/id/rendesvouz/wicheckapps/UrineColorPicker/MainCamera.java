package id.rendesvouz.wicheckapps.UrineColorPicker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Vector;

import id.rendesvouz.wicheckapps.DatabaseAccess;
import id.rendesvouz.wicheckapps.Model.Colors;
import id.rendesvouz.wicheckapps.Model.Questions;
import id.rendesvouz.wicheckapps.PassingColor;
import id.rendesvouz.wicheckapps.Question;
import id.rendesvouz.wicheckapps.R;
import id.rendesvouz.wicheckapps.Result;

public class MainCamera extends AppCompatActivity implements CameraColorPickerPreview.OnColorSelectedListener {
    private Vector<Colors> colors;
    private Vector<Questions> questions;
    private int index = 0;
    private Camera mCamera;
    private CameraAsyncTask mCameraAsyncTask;
    private int mSelectedColor;
    private CameraColorPickerPreview mCameraPreview;
    private FrameLayout mPreviewContainer;
    private View mPointerRing;
    private static final int REQUEST_CAMERA = 100;
    FloatingActionButton fab_lockColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
        mPreviewContainer = findViewById(R.id.camera_container);
        mPointerRing = findViewById(R.id.pointer_ring);

        fab_lockColor = findViewById(R.id.fab_lockColor);

        fab_lockColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getColor();
                getQuestion();

                if (questions.get(0).getPertayaan().equals("-")){
                    Intent intent = new Intent(MainCamera.this, Result.class);
                    intent.putExtra("AnswerYes", questions.get(0).getJawabanYes());
                    intent.putExtra("AnswersYes", questions.get(0).getAnswerYes());
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainCamera.this, Question.class);
                    intent.putExtra("ColorIndex", index);
                    startActivity(intent);
                }
            }
        });

    }

    void getColor(){
        //get color from camera
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainCamera.this);
        databaseAccess.open();

        PassingColor passingColor = PassingColor.getInstance();
        int R = passingColor.getRed();
        int G = passingColor.getGreen();
        int B = passingColor.getBlue();

        //color distance algorithm
        colors = databaseAccess.getColorName();
        Vector<Integer> distance = new Vector<>();
        for (int i = 0; i < colors.size(); i++){
            int temp = (int) (Math.pow(R - colors.get(i).getR(), 2) + Math.pow(G - colors.get(i).getG(), 2) + Math.pow(B - colors.get(i).getB(), 2));
            distance.add(temp);
        }

        int min = distance.get(0);
        for (int i = 1; i < distance.size(); i++){
            if (distance.get(i) < min){
                min = distance.get(i);
                index = i;
            }
        }
        passingColor.setRed(colors.get(index).getR());
        passingColor.setGreen(colors.get(index).getG());
        passingColor.setBlue(colors.get(index).getB());
        databaseAccess.close();
    }

    void getQuestion(){
        //get question from database
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainCamera.this);
        databaseAccess.open();

        questions = databaseAccess.getQuestion(index);
        databaseAccess.close();
    }

    @Override
    public void onColorSelected(int color) {
        mSelectedColor = color;
        mPointerRing.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    private static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainCamera.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainCamera.this,
                        new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            } else {
                mCameraAsyncTask = new CameraAsyncTask();
                mCameraAsyncTask.execute();
            }
        } else {
            mCameraAsyncTask = new CameraAsyncTask();
            mCameraAsyncTask.execute();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                MainCamera.this.finish();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCameraAsyncTask != null) {
            mCameraAsyncTask.cancel(true);
        }
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
        if (mCameraPreview != null) {
            mPreviewContainer.removeView(mCameraPreview);
        }
    }

    private class CameraAsyncTask extends AsyncTask<Void, Void, Camera> {

        protected FrameLayout.LayoutParams mPreviewParams;

        @Override
        protected Camera doInBackground(Void... params) {
            Camera camera = getCameraInstance();
            if (camera != null) {
                Camera.Parameters cameraParameters = camera.getParameters();
                Camera.Size bestSize = Cameras.getBestPreviewSize(
                        cameraParameters.getSupportedPreviewSizes()
                        , mPreviewContainer.getWidth()
                        , mPreviewContainer.getHeight()
                        , true);
                cameraParameters.setPreviewSize(bestSize.width, bestSize.height);
                camera.setParameters(cameraParameters);
                Cameras.setCameraDisplayOrientation(MainCamera.this, camera);
                int[] adaptedDimension = Cameras.getProportionalDimension(
                        bestSize
                        , mPreviewContainer.getWidth()
                        , mPreviewContainer.getHeight()
                        , true);
                mPreviewParams = new FrameLayout.LayoutParams(adaptedDimension[0], adaptedDimension[1]);
                mPreviewParams.gravity = Gravity.CENTER;
            }
            return camera;
        }

        @Override
        protected void onPostExecute(Camera camera) {
            super.onPostExecute(camera);
            if (!isCancelled()) {
                mCamera = camera;
                if (mCamera != null) {
                    mCameraPreview = new CameraColorPickerPreview(MainCamera.this, mCamera);
                    mCameraPreview.setOnColorSelectedListener(MainCamera.this);
                    mPreviewContainer.addView(mCameraPreview, 0, mPreviewParams);
                }
            }
        }

        @Override
        protected void onCancelled(Camera camera) {
            super.onCancelled(camera);
            if (camera != null) {
                camera.release();
            }
        }
    }
}
