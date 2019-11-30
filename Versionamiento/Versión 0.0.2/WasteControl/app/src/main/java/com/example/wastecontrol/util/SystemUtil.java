package com.example.wastecontrol.util;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class SystemUtil {
    private static NotificationChannel channel = null;

    public static String NOTIFICATION_CHANNEL_ID = "com.example.wastecontrol";

    public static NotificationChannel getNotificationChannel(Context context){
        if(channel == null){
            channel = SystemUtil.createNotificationChanel(context);
        }

        return channel;
    }

    private static NotificationChannel createNotificationChanel(Context context) {
        NotificationChannel channel = null;
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "NAME";
            String description = "DESCRIPTION";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        return channel;
    }

    public static List<File> loadFiles(List<String> dirs, FilesType type){
        List<File> files = new ArrayList<>();

        List<String> directories = (dirs != null)? dirs : getDirectories();

        for (String directory: directories) {
            File fileDir = Environment.getExternalStoragePublicDirectory(directory);
            if(fileDir.listFiles() == null) continue;
            for (File file : fileDir.listFiles()) {
                if(!(file.isFile() && isItem(file, type))) continue;
                files.add(file);
            }
        }

        return files;
    }

    private static List<String> getDirectories() {
        return Arrays.asList(
                    Environment.DIRECTORY_DCIM,
                    Environment.DIRECTORY_PICTURES,
                    Environment.DIRECTORY_DOCUMENTS,
                    Environment.DIRECTORY_DOWNLOADS,
                    Environment.DIRECTORY_MUSIC
            );
    }

    private static boolean isItem(File file, FilesType type) {

        for (String e : getFilesfromType(type)){
            if(file.getName().toLowerCase().endsWith(e)) return true;
        }
        return false;
    }

    public enum FilesType {
        AUDIO,IMAGE,OTHER
    };

    private static List<String> getFilesfromType(FilesType type){
        switch (type){
            case AUDIO:
                    return Arrays.asList(".mp3",".wma",".wav",".mp2",".acc",".ac3",".au",".ogg",".flac");
            case IMAGE:
                return Arrays.asList(".jpg",".png",".bmp");
            case OTHER:
                return Arrays.asList(".txt",".text");
            default:
                return new ArrayList<>();
        }
    }

    public static void checkPermission(Context context, List<String> permissions){
        List<String> check = permissions.stream()
                .filter((permission) -> ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                .collect(Collectors.toList());

        ActivityCompat.requestPermissions((Activity) context,check.toArray(new String[check.size()]), 1000);
    }

    public static boolean isGrantedPermission(Activity context, String permission){
        if(ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        return false;
    }

}
