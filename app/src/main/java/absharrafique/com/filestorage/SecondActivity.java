package absharrafique.com.filestorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends Activity {
    TextView textView;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView1);
    }

    public void loadFromInternalCache(View view) {

        File folder = getCacheDir();
        File myFile = new File(folder, "mydata1.txt");
        String data = readData(myFile);
        if(data!=null){
            editText2.setText(data);

        } else{
            editText2.setText("No data was returned");
        }
    }
    public void loadFromExternalCache(View view) {
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "mydata2.txt");
        String data = readData(myFile);
        if(data!=null){
            editText2.setText(data);

        } else{
            editText2.setText("No data was returned");
        }

    }

    public void loadExternalPrivate(View view) {

        File folder = getExternalFilesDir("Android");
        File myFile = new File(folder, "mydata3.txt");
        String data = readData(myFile);
        if(data!=null){
            editText2.setText(data);

        } else{
            editText2.setText("No data was returned");
        }


    }

    public void loadExternalPublic(View view) {

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "mydata4.txt");
        String data = readData(myFile);
        if(data!=null){
            editText2.setText(data);

        } else{
            editText2.setText("No data was returned");
        }


    }

    private String readData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1) {
                stringBuffer.append((char)(read));
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } return null;
    }

    public void previous(View view) {

        Toast.makeText(this, "Previous Called", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }

    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
