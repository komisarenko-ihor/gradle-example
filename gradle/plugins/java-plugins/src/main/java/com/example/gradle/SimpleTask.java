package com.example.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;

@CacheableTask
public class SimpleTask extends DefaultTask {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @TaskAction
    public void printMessage() {
        System.out.println(message);
    }
}
