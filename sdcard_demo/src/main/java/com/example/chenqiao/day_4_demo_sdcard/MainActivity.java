package com.example.chenqiao.day_4_demo_sdcard;


        import android.os.Build;
        import android.os.Environment;
        import android.os.StatFs;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.text.format.Formatter;
        import android.util.Log;
        import android.widget.Toast;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sd卡的挂载路径可能不同
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDirectory, "demo.txt");

        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {


            getSize();
           /* try {
                FileOutputStream fos = new FileOutputStream(file);

                fos.write("demo".getBytes());

                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/


        } else {
            Toast.makeText(this, "sdcard 未成功挂载，请检查", Toast.LENGTH_LONG).show();

        }


    }


    public void getSize() {

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StatFs tool = new StatFs(externalStorageDirectory.getAbsolutePath());

        //block  扇区

        long blockCountLong=0;
        long availableBlocksLong =0;
        long blockSizeLong=0;
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.JELLY_BEAN_MR2   ){//当前用户的手机版本是大于APIlevel 18

            blockCountLong = tool.getBlockCountLong();
            availableBlocksLong = tool.getAvailableBlocksLong();
            blockSizeLong = tool.getBlockSizeLong();
        }else
        {

            blockCountLong = tool.getBlockCount();
            availableBlocksLong = tool.getAvailableBlocks();
            blockSizeLong = tool.getBlockSize();

        }

        //total

        long total = blockSizeLong*blockCountLong; //字节
        long avaiable = availableBlocksLong*blockSizeLong;//

        String totalString = Formatter.formatFileSize(this, total);
        String avaiableString = Formatter.formatFileSize(this, avaiable);


        Log.i("sdcard",total/1024/1024+"");
        Log.i("sdcard",avaiable/1024/1024+"");

        Toast.makeText(this,totalString+":"+avaiableString,Toast.LENGTH_LONG).show();

    }

}
