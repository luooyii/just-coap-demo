package edu.just.coap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

public class CoapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coap);

        String uri = "coap://californium.eclipse.org:5683/";
        new CoapGetTask().execute(uri);
    }

    private void makeToast(String str) {
        Toast.makeText(CoapActivity.this, str, Toast.LENGTH_LONG).show();
    }

    class CoapGetTask extends AsyncTask<String, String, CoapResponse> {

        protected void onPreExecute() {
            makeToast("加载中");
        }

        protected CoapResponse doInBackground(String... args) {
            CoapClient client = new CoapClient(args[0]);
            return client.get();
        }

        protected void onPostExecute(CoapResponse response) {
            if (response != null) {
                makeToast("Code:" + response.getCode().toString() +
                        "\nCodeName:" + response.getCode().name() +
                        "\nContent:" + response.getResponseText());
            } else {
                makeToast("无返回");
            }
        }
    }
}