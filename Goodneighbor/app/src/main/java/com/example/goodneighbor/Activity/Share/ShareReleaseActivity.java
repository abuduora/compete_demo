package com.example.goodneighbor.Activity.Share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.goodneighbor.R;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class ShareReleaseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_camera;
    private ImageView iv_showphoto;

    private static final int CAMERA_REQUEST_CODE=0x00000010;private static final int PERMISSION_CAMERA_REQUEST_CODE=0x00000012;

    private Uri mCameraUri;

    private String mCameraImagePath;

    private boolean isAndroidQ= Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_release);
        iv_camera = findViewById(R.id.iv_camera);
        iv_showphoto=findViewById(R.id.iv_show_photo);

        iv_camera.setOnClickListener(this);

    }

    //拍照功能点击监听器
    @Override
    public void onClick(View view) {
        checkPermissionAndCamera();
    }

    private void checkPermissionAndCamera(){
        int hasCameraPwemission= ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.CAMERA);
        if (hasCameraPwemission== PackageManager.PERMISSION_GRANTED){
            openCamera();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},PERMISSION_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==CAMERA_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                if(isAndroidQ){//判断当前系统是否是Android Q（Android 10）
                    iv_showphoto.setImageURI(mCameraUri);//通过图片的URI来设置，将拍摄的照片显示在ImageView上
                }else{
                    iv_showphoto.setImageBitmap(BitmapFactory.decodeFile(mCameraImagePath));//通过解析图片文件路径来设置，将拍摄的照片显示在ImageView上
                }
            }else {
                Toast.makeText(this,"取消",Toast.LENGTH_LONG).show();//如果结果码不是RESULT_OK，说明用户可能取消了相机操作，这里就显示一个提示信息"取消"
            }
        }
    }

   /* @Override
    public void onRequestPermissionResult(int requestCode,String[]
                                          permissions,int[] grantResults){
        if(requestCode==PERMISSION_CAMERA_REQUEST_CODE){
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else {
                Toast.makeText(this,"拍照权限被拒绝",Toast.LENGTH_LONG).show();
            }
        }
    }*/


    //在Android应用中打开相机并拍摄照片
    private void openCamera() {
        Intent captureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//MediaStore.ACTION_IMAGE_CAPTURE，表示要拍摄一张照片
        if (captureIntent.resolveActivity(getPackageManager())!=null){
            File photoFile=null;
            Uri photoUri=null;
            if(isAndroidQ){
                photoUri=createImageUri();//创建一个用于保存拍摄照片的Uri,需要通过MediaStore API来创建一个Uri
            }else {
                try{
                    photoFile=createImageFile();//将创建的文件对象赋值给photoFile变量,createImageFile()方法会在私有目录下创建一个新的文件，用于保存拍摄的照片
                }catch (IOException e){//如果文件创建失败（比如因为权限问题或者磁盘空间不足）,createImageFile()方法会抛出一个IOException异常
                    e.printStackTrace();//用于打印一个异常的堆栈跟踪信息。
                }
                if (photoFile!=null){
                    mCameraImagePath=photoFile.getAbsolutePath();//获取photoFile的绝对路径，并将其赋值给mCameraImagePath变量。
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){//检查当前设备的Android版本是否大于等于Nougat（Android 7.0，API等级24）
                        photoUri= FileProvider.getUriForFile(this,getPackageName()+".fileprovider",photoFile);//通过FileProvider为photoFile创建一个Uri
                    }else {
                        photoUri=Uri.fromFile(photoFile);//Uri.fromFile()方法接收一个File对象作为参数，并返回一个指向该文件的Uri
                    }
                }
            }
            mCameraUri=photoUri;
            if(photoUri !=null){
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                /*向Intent添加一个额外的数据。captureIntent.putExtra()方法接收两个参数：
                一个键和一个值。在这里，键是MediaStore.EXTRA_OUTPUT，值是photoUri。
                MediaStore.EXTRA_OUTPUT是一个特殊的键，它告诉处理Intent的Activity，
                拍摄的照片应该被保存在由photoUri指向的位置。当相机应用启动并拍摄照片后，
                它会使用这个Uri来确定在哪里保存照片。
               */
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
/*
                给Intent添加一个标志，表示授予对photoUri的写入权限。captureIntent.addFlags()方法接收一个或多个标志作为参数，
                这里传递的标志是Intent.FLAG_GRANT_WRITE_URI_PERMISSION。这个标志告诉Android系统，
                启动的Activity（在这里是相机应用）应该被授予对photoUri指向的文件的写入权限。这是必要的，
                因为默认情况下，其他应用可能没有权限写入应用的私有目录。
                没有这个标志，相机应用可能无法保存照片。
*/
                startActivityForResult(captureIntent,CAMERA_REQUEST_CODE);//启动一个Activity，并在该Activity完成后返回结果。
            }
        }
    }

    //创建一个用于保存图片的Uri
    private Uri createImageUri(){
        String status= Environment.getExternalStorageState();
        //获取外部存储的状态
        if (status.equals((Environment.MEDIA_MOUNTED))) {//外部存储是挂载的（Environment.MEDIA_MOUNTed）
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,new ContentValues());
            /*使用了getContentResolver()方法来获取一个ContentResolver对象，
            该对象提供了一种接口，可以让你对数据和媒体库进行访问和管理。
            new ContentValues()创建了一个新的ContentValues对象，
            这个对象用于存储和检索媒体文件的元数据。 */
        }else {//使用手机的内部存储来创建Uri
            return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI,new ContentValues());
        }
    }

    //创建一个用于保存图片的文件
    private File createImageFile()throws IOException{
        String imageName= new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());//日期和时间,例如"20230706_123456"
        File storageDir= getExternalFilesDir(Environment.DIRECTORY_PICTURES);//getExternalFilesDir()方法获取外部存储的目录，这个目录是应用的私有目录，其他应用无法访问。Environment.DIRECTORY_PICTURES是一个常量，表示存储图片的目录。
        if(!storageDir.exists()){
            storageDir.mkdir();
        }
        File tempFile=new File(storageDir,imageName);//建了一个新的文件tempFile，这个文件的路径是storageDir（外部存储的图片目录）加上imageName（当前的日期和时间）。
        if(!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))){//检查了这个文件的存储状态，如果存储状态不是Environment.MEDIA_MOUNTED（表示存储未挂载或不可写），那么就返回null，否则就返回这个文件。
            return null;
        }
        return tempFile;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}