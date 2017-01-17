package com.futuresmedia.gradle.plugin;


import groovy.lang.MissingPropertyException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 * Created by scott on 1/16/17.
 */
public class FlywayMigrationPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {


        String fileName;
        String outputDir;

        if (!project.hasProperty("flywaySQLDirectory")) {
            throw new MissingPropertyException("Gradle build file missing required flywaySQLDirectory declaration.\n" +
                    "Place this block in your build file.\n" +
                    "buildscript {\n" +
                    "\text {\n" +
                    "\t\tflywaySQLDirectory = './src/main/resources/db/migration'\n" +
                    "\t}\n" +
                    "}");
        } else {
            outputDir = project.getProperties().get("flywaySQLDirectory").toString();
        }

        if (project.hasProperty("fileName")) {
            fileName = project.property("fileName").toString();
//            throw new IllegalArgumentException("No file name provided. Task must include filename argument i.e -Pfilename=<this_should_be_your_filename>");

        } else {
            fileName = null;
        }

        System.out.println(fileName);
        Task task = project.getTasks().create("generateMigration", GenerateMigrationTask.class);
        task.setProperty("outputDirStr", outputDir);
        task.setProperty("fileName", fileName);
    }
}
