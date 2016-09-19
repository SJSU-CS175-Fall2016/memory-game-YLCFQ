package com.cs175.chenyulong.memorygame;

import java.util.Random;

/**
 * Created by chenyulong on 9/18/16.
 */
public class Singleton {
    private static Singleton mInstance = null;
    private boolean onProgress = false;
    private int[] imageArr;
    private int points;
    private int[] finishedArr;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public int[] getImageArr() {
        return imageArr;
    }

    public boolean getProgress() {
        return onProgress;
    }

    public void setOnProgress (boolean flag) {
        onProgress = flag;
    }

    public void startGame() {
        imageArr = shuffleImage();
        finishedArr = new int[imageArr.length];
        onProgress = true;
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints() {
        points++;
    }

    public void addFinished(int a, int b) {
        finishedArr[a] = 1;
        finishedArr[b] = 1;
    }
    public int[] getFinishedArr () {
        return finishedArr;
    }
    public int checkFinished(int position) {
        return finishedArr[position];
    }

    public boolean checkPositions(int first, int second) {
        return imageArr[first] == imageArr[second];
    }

    public void addBlank() {
        for (int i = 0 ; i < finishedArr.length ; i++){
            if (finishedArr[i] == 1) {
                imageArr[i] = R.drawable.blank;
            }
        }
    }
    public int[] shuffleImage() {
        int[] images = {
                R.drawable.archer_1, R.drawable.archer_2,
                R.drawable.magic_1, R.drawable.magic_2,
                R.drawable.pirate_1, R.drawable.thief_1,
                R.drawable.thief_2, R.drawable.warrior_1,
                R.drawable.warrior_2, R.drawable.warrior_3,
                R.drawable.archer_1, R.drawable.archer_2,
                R.drawable.magic_1, R.drawable.magic_2,
                R.drawable.pirate_1, R.drawable.thief_1,
                R.drawable.thief_2, R.drawable.warrior_1,
                R.drawable.warrior_2, R.drawable.warrior_3
        };
        Random rand = new Random();
        for (int i = 0 ; i <images.length ; i++) {
            int temp = images[i];
            int j = rand.nextInt(images.length);
            images[i] = images[j];
            images[j] = temp;
        }

        return images;
    }
}
