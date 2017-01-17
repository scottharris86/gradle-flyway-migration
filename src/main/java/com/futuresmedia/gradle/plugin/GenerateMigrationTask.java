package com.futuresmedia.gradle.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by scott on 1/16/17.
 */
public class GenerateMigrationTask extends DefaultTask {

    public String outputDirStr;
    public String fileName;

    @TaskAction
    public void generateMigration() {
        File f = new File(outputDirStr);
        if (fileName == null) {
            throw new IllegalArgumentException("No file name provided. Task must include filename argument i.e -Pfilename=<this_should_be_your_filename>");
        }

        File newFile;
        String finalName;

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(Calendar.getInstance().getTime());
        finalName = dateStr + "_" + fileName +".sql";

        newFile = new File(f, finalName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(finalName);
    }
}
