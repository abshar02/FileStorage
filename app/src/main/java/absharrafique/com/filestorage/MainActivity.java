package absharrafique.com.filestorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Look this file i'm linking.
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText1);
    }

    public void saveInternalCache(View view) {
        String data = editText.getText().toString();
        File folder = getCacheDir();
        File myFile = new File(folder, "mydata1.txt");
        writeData(myFile, data);
    }

    public void saveExternalCache(View view) {
        String data = editText.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "mydata2.txt");
        writeData(myFile, data);
    }

    public void saveExternalPrivate(View view) {
        String data = editText.getText().toString();
        File folder = getExternalFilesDir("Android");
        File myFile = new File(folder, "mydata3.txt");
        writeData(myFile, data);

    }

    public void saveExternalPublic(View view) {
        String data = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "mydata4.txt");
        writeData(myFile, data);

    }

    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            message(data+ " was written successfully "+myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void next(View view) {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
