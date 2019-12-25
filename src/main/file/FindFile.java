package main.file;

import java.io.File;
import java.util.Date;

public class FindFile {

    public static void main(String[] args) {
        File file = new File("/usr/local/Cellar/nginx/1.17.3_1/images/nginx-logo.jpg");
        System.out.println(file.exists());
    }
}
