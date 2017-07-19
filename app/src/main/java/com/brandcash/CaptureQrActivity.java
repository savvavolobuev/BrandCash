package com.brandcash;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brandcash.model.QrLine;
import com.brandcash.model.ReceiptResponseData;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.util.SharedPrefs;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class CaptureQrActivity extends Activity {
    private static final String TAG = CaptureQrActivity.class.getSimpleName();
    private BarcodeView barcodeView;
    private BeepManager beepManager;
    private String lastText;
    private static final int CAMERA_PERM = 32;
    private Button cancelBtn;
    private String qrLine;
    private ReceiptResponseData responseData;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null || result.getText().equals(lastText)) {
                // Prevent duplicate scans
                return;
            }
            lastText = result.getText();
            beepManager.playBeepSoundAndVibrate();
            qrLine = lastText;
            startReceiptProcessing();
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    private void startReceiptProcessing() {
        QrLine qrCode = createQrLine();

        Call<ReceiptResponseData> call = ServerClient.getServerApiService().add(qrCode.getN(), qrCode.getT(), qrCode.getS(), qrCode.getFn(), qrCode.getI(), qrCode.getFp(), SharedPrefs.getPrefSid());
        final MaterialDialog dialog = new MaterialDialog.Builder(this).content("Пожайлуста, подождите").progress(true, 0).build();
        dialog.show();
        call.enqueue(new Callback<ReceiptResponseData>() {
            @Override
            public void onResponse(Call<ReceiptResponseData> call, Response<ReceiptResponseData> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    responseData = response.body();
                    if (responseData != null) {
                        if (responseData.getFound() == null) {
                            startActivity(new Intent(CaptureQrActivity.this, QrResultActivity.class).putExtra(QrResultActivity.EXTRA_QR, responseData));
                        } else if (responseData.getFound()) {
                            startActivity(new Intent(CaptureQrActivity.this, QrResultActivity.class).putExtra(QrResultActivity.EXTRA_QR, responseData));
                        } else {

                        }
                    }
                } else if (response.code() == 403) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //jObjError.getString("message")
                        new MaterialDialog.Builder(CaptureQrActivity.this)
                                .title("Ошибка")
                                .content(jObjError.getString("error_status").equals("RECEIPT_IS_ALREADY_ADDED") ? "Чек уже добавлен" : "Чек не прошел проверку")
                                .positiveText("ОК")
                                .show();
                    } catch (Exception e) {
                        int i = 1;
                        i++;
                    }
                }

            }

            @Override
            public void onFailure(Call<ReceiptResponseData> call, Throwable t) {
                dialog.dismiss();
                //final MaterialDialog dialogError = new MaterialDialog.Builder(CaptureQrActivity.this).content(t.).build();
                //dialog.show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scan);

        barcodeView = (BarcodeView) findViewById(R.id.barcode_scanner);
        //barcodeView.setStatusText("");
        barcodeView.decodeContinuous(callback);


        beepManager = new BeepManager(this);

        cancelBtn = (Button) findViewById(R.id.cancel);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERM);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERM: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "we can't scan", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        barcodeView.pause();
    }

    public void pause(View view) {
        barcodeView.pause();
    }

    public void resume(View view) {
        barcodeView.resume();
    }

    public void triggerScan(View view) {
        barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private QrLine createQrLine() {
        QrLine result = new QrLine();
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = qrLine.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            } else {
                String query = urlParts[0];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }
            result.setN(params.get("n") == null || params.get("n").isEmpty() ? "" : params.get("n").get(0));
            result.setFn(params.get("fn") == null || params.get("fn").isEmpty() ? "" : params.get("fn").get(0));
            result.setS(params.get("s") == null || params.get("s").isEmpty() ? "" : params.get("s").get(0));
            result.setT(params.get("t") == null || params.get("t").isEmpty() ? "" : params.get("t").get(0));
            result.setFp(params.get("fp") == null || params.get("fp").isEmpty() ? "" : params.get("fp").get(0));
            result.setI(params.get("i") == null || params.get("i").isEmpty() ? "" : params.get("i").get(0));
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }


        return result;
    }

}
