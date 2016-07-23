package com.nekodev.paulina.sadowska.userlist.dataaccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Paulina Sadowska on 23.05.2016.
 */
public class FileManager {

    private String filePath;

    public FileManager(String filesDir, String fileName) {
        this.filePath = filesDir + "/" + fileName;
    }

    public String readFromFile() {
        BufferedReader br;
        String response;

        try {

            StringBuilder output = new StringBuilder();

            br = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            response = output.toString();

        } catch (IOException e) {
            return "";
        }
        return response;
    }

    public void saveToFile(final String json) {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                FileOutputStream outputStream;

                try {
                    outputStream = new FileOutputStream(filePath);
                    outputStream.write(json.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public boolean isFileEmpty() {
        File file = new File(filePath);
        return !file.exists() || file.length() == 0;
    }
}
