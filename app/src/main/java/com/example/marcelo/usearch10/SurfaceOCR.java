package com.example.marcelo.usearch10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.marcelo.usearch10.R;
import com.example.marcelo.usearch10.dialogs.DialogProductConfirm;
import com.example.marcelo.usearch10.helpers.Extract;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;


public class SurfaceOCR extends android.support.v4.app.Fragment {

    private SurfaceView cameraView;
    private TextView textView;
    private CameraSource cameraSource;
    private final int RequestCameraPermissionID = 1001;
    private Extract extract;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        textView = (TextView) rootView.findViewById(R.id.text_view);
        cameraView = (SurfaceView) rootView.findViewById(R.id.surface_view);

        extract = new Extract();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);

        TextRecognizer textReconizer = new TextRecognizer.Builder(getActivity()).build();
        if (!textReconizer.isOperational()) {
            Log.w("MainActivity", "Detector de dependencias nao esta ativado");
        } else {
            cameraSource = new CameraSource.Builder(getActivity(), textReconizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(size.x, (int)(size.y * 0.5))
                    .setRequestedFps(1.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            cameraView.getHolder().addCallback(new SurfaceCallback());

            textReconizer.setProcessor(new TextProcessor());
        }

        return rootView;
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceholder) {
            try {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            RequestCameraPermissionID);
                    return;
                }
                cameraSource.start(cameraView.getHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            cameraSource.stop();

        }
    }

    private class TextProcessor implements Detector.Processor<TextBlock> {
        @Override
        public void release() {

        }

        @Override
        public void receiveDetections(Detector.Detections<TextBlock> detections) {

            SparseArray<TextBlock> items = detections.getDetectedItems();

            StringBuilder stringBuilder = new StringBuilder();

            for(int i =0;i<items.size();i++){
                TextBlock item = items.valueAt(i);
                stringBuilder.append(item.getValue());
            }

            String result = stringBuilder.toString();

            String n = Extract.testName(result);
            String p = Extract.testPrice(result);

            if ( extract.fully() ){
                String name = extract.getName();
                String price = extract.getPrice();

                DialogFragment dialog = DialogProductConfirm.newInstance(name, price);
                dialog.show(getFragmentManager(), "confirm-product");

                extract.clear();
            }else{
                if ( !n.isEmpty() ){
                    extract.addName(n);
                }

                if (!p.isEmpty()){
                    extract.addPrice(p);
                }
            }

        }
    }
}
