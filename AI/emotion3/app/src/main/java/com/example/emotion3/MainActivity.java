package com.example.emotion3;

import java.io.*;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.graphics.*;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.provider.*;
import com.microsoft.projectoxford.face.*;
import com.microsoft.projectoxford.face.contract.*;

public class MainActivity extends AppCompatActivity {

    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;

    private final String apiEndpoint = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0";
    private final String subscriptionKey = "768b7af778924baea60775148b697e70";
    private final FaceServiceClient faceServiceClient = new FaceServiceRestClient(apiEndpoint, subscriptionKey);
    Handler mHandler = null;

    static String youtubeURL = "https://www.youtube.com/results?search_query=";
    String input_str;

    TextView textView;
    WebView webView;

    String emotionStrArray[] = {"Anger : ", "Contempt : ", "Disgust : ", "Fear : ", "Happiness : ", "Neutral : ", "Sadness : ", "Surprise : "};
    double emotionArray[];
    double Max_emotion;
    String Max_emotion_str;

    int mDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        textView = findViewById(R.id.textView);
        webView = (WebView) findViewById(R.id.webView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(
                        intent, "Select Picture"), PICK_IMAGE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        detectionProgressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), uri);
                ImageView imageView = findViewById(R.id.imageView1);
                imageView.setImageBitmap(bitmap);

                // Comment out for tutorial
                detectAndFrame(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Detect faces by uploading a face image.
// Frame faces after detection.
    public void detectAndFrame(final Bitmap imageBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());
        mHandler = new Handler();

        @SuppressLint("StaticFieldLeak") AsyncTask<InputStream, String, Face[]> detectTask =
                new AsyncTask<InputStream, String, Face[]>() {
                    String exceptionMessage = "";

                    @Override
                    protected Face[] doInBackground(InputStream... params) {
                        try {
                            publishProgress("Detecting...");
                            Face[] result = faceServiceClient.detect(
                                    params[0],
                                    true,         // returnFaceId
                                    false,   // returnFaceLandmarks
                                    //null    // returnFaceAttributes:
                                    new FaceServiceClient.FaceAttributeType[]{
                                            FaceServiceClient.FaceAttributeType.Age,
                                            FaceServiceClient.FaceAttributeType.Gender,
                                            FaceServiceClient.FaceAttributeType.Emotion
                                    }
                            );

                            double emotionAnger = result[0].faceAttributes.emotion.anger;
                            double emotionContempt = result[0].faceAttributes.emotion.contempt;
                            double emotionDisgust = result[0].faceAttributes.emotion.disgust;
                            double emotionFear = result[0].faceAttributes.emotion.fear;
                            double emotionHappiness = result[0].faceAttributes.emotion.happiness;
                            double emotionNeutral = result[0].faceAttributes.emotion.neutral;
                            double emotionSadness = result[0].faceAttributes.emotion.sadness;
                            double emotionSurprise = result[0].faceAttributes.emotion.surprise;

                            double tempEmotionArray[] = {emotionAnger, emotionContempt, emotionDisgust, emotionFear, emotionHappiness, emotionNeutral, emotionSadness, emotionSurprise};
                            emotionArray = tempEmotionArray;
                            Max_emotion = 0;

                            for (int i = 0; i < emotionArray.length; i++) {
                                if (emotionArray[i] > Max_emotion) {
                                    Max_emotion = emotionArray[i];
                                    Max_emotion_str = emotionStrArray[i];
                                }
                            }
                            Log.e("Max_emotion", "Max_emotion : " + Max_emotion_str + Max_emotion);

                            mHandler.post(new Runnable(){ @Override public void run() {
                                textView.setText(Max_emotion_str + Max_emotion);

                                if(Max_emotion == emotionArray[0]){input_str ="스트레스해소노래";}
                                if(Max_emotion == emotionArray[1]){input_str ="클래식";}
                                if(Max_emotion == emotionArray[2]){input_str = "편안한노래";}
                                if(Max_emotion == emotionArray[3]){input_str = "신나는노래";}
                                if(Max_emotion == emotionArray[4]){input_str= "행복할때듣는노래";}
                                if(Max_emotion == emotionArray[5]){input_str="잔잔한노래";}
                                if(Max_emotion == emotionArray[6]){input_str="슬플때듣는노래";}
                                if(Max_emotion == emotionArray[7]){input_str="안정시켜주는음악";}

                                String url = youtubeURL + input_str;

                                webView.setWebChromeClient(new WebChromeClient());
                                webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                                webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
                                webView.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용
                                webView.loadUrl(url);
                            }});

                            if (result == null) {
                                publishProgress(
                                        "Detection Finished. Nothing detected");
                                return null;
                            }
                            publishProgress(String.format(
                                    "Detection Finished. %d face(s) detected",
                                    result.length));
                            return result;
                        } catch (Exception e) {
                            exceptionMessage = String.format(
                                    "Detection failed: %s", e.getMessage());
                            return null;
                        }
                    }

                    @Override
                    protected void onPreExecute() {
                        //TODO: show progress dialog
                        detectionProgressDialog.show();
                    }

                    @Override
                    protected void onProgressUpdate(String... progress) {
                        //TODO: update progress
                        detectionProgressDialog.setMessage(progress[0]);
                    }

                    @Override
                    protected void onPostExecute(Face[] result) {
                        //TODO: update face frames
                        detectionProgressDialog.dismiss();

                        if (!exceptionMessage.equals("")) {
                            showError(exceptionMessage);
                        }
                        if (result == null) return;

                        ImageView imageView = findViewById(R.id.imageView1);
                        imageView.setImageBitmap(
                                drawFaceRectanglesOnBitmap(imageBitmap, result));
                        imageBitmap.recycle();
                    }
                };
        detectTask.execute(inputStream);
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .create().show();
    }

    private static Bitmap drawFaceRectanglesOnBitmap(
            Bitmap originalBitmap, Face[] faces) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        if (faces != null) {
            for (Face face : faces) {
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(
                        faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left + faceRectangle.width,
                        faceRectangle.top + faceRectangle.height,
                        paint);
            }
        }
        return bitmap;
    }
}