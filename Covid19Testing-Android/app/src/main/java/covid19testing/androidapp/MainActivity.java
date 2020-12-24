package covid19testing.androidapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.Map;


// App <- http request ->  Backend <- -> [DB (store patients requests), hospital (mock)]
// 1) minimum number of web requests, send in batches
// 2) App only talk to backend

public class MainActivity extends AppCompatActivity {
    private static String TAG = "CovidTestingTag";
    private static String backendEndpoint = "https://covid19testing-backend-001.herokuapp.com/patients/create";
    private static final int CONNECTION_TIMEOUT = 5000;
    // add method paths here.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.submitPatient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        URL url = null;
                        try {
                            url = new URL(backendEndpoint);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// timeouts is important
                            connection.setConnectTimeout(CONNECTION_TIMEOUT);
                            connection.setRequestMethod("POST");
                            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            connection.setRequestProperty("Accept","application/json");
                            connection.setDoInput(true);
                            connection.setDoOutput(true);
                            //connection.setRequestProperty("Content-length", Integer.toString(data.getBytes().length));

                            //setting the json body
                            JSONObject patientDto = new JSONObject();
                            String patientName = ((EditText) findViewById(R.id.patientName)).getText().toString();
                            String patientSurname = ((EditText) findViewById(R.id.patientSurname)).getText().toString();
                            String patientAddress = ((EditText) findViewById(R.id.patientAddress)).getText().toString();
                            String patientCity = ((EditText) findViewById(R.id.patientCity)).getText().toString();
                            String patientProvince = ((EditText) findViewById(R.id.patientProvince)).getText().toString();
                            String patientInsuranceNumber = ((EditText) findViewById(R.id.patientInsuranceNumber)).getText().toString();
                            int patientAge = Integer.parseInt(((EditText) findViewById(R.id.patientAge)).getText().toString());
                            String patientTravel = ((EditText) findViewById(R.id.patientTravelHistory)).getText().toString();
                            String patientPrecondition = ((EditText) findViewById(R.id.patientPrecondition)).getText().toString();
                            String patientMedication = ((EditText) findViewById(R.id.patientMedication)).getText().toString();
                            patientDto.put("name", patientName);
                            patientDto.put("surname", patientSurname);
                            patientDto.put("address", patientAddress);
                            patientDto.put("city", patientCity);
                            patientDto.put("province", patientProvince);
                            patientDto.put("insuranceNumber", patientInsuranceNumber);
                            patientDto.put("age", patientAge);
                            patientDto.put("travel", patientTravel);
                            patientDto.put("preCondition", patientPrecondition);
                            patientDto.put("medication", patientMedication);
                            Log.i("JSON", patientDto.toString());
                            String data = patientDto.toString();

                            DataOutputStream requestStream = new DataOutputStream(connection.getOutputStream());
                            requestStream.writeBytes(data);
                            requestStream.flush();
                            requestStream.close();

                            int responseCode = connection.getResponseCode();
                            if (responseCode != HttpURLConnection.HTTP_OK) {
                                // handle the error.
                                Log.d(TAG, "" + responseCode);
                            }

                            BufferedReader responseStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String response = "";
                            String oneLine;
                            while ((oneLine = responseStream.readLine()) != null) {
                                response += oneLine;
                            }
                            Log.d(TAG, response);
                            // do whatever with the response.
                            JSONObject jsonResponse = null;
                            try {
                                jsonResponse = new JSONObject(response);
                            } catch (JSONException e) {
                            }
                            if (jsonResponse != null) {
                                jsonResponse.getJSONObject("menu").getJSONArray("id");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            //Log.d(TAG, e.getMessage());
                        }
                        return null;
                    }
                }.execute();

            }
        });

    }
}