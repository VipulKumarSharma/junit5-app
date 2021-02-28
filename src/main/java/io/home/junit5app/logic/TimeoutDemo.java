package io.home.junit5app.logic;

public class TimeoutDemo {

    public String returnMessage() {
        return "STAY SAFE!";
    }

    public int incrementNumber(int num) {
        try {
            Thread.sleep(1001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num + 1;
    }

}
