package com.techelevator;

public class Television {

    // Instance variables
    private boolean isOn;
    private int currentChannel;
    private int currentVolume;

    // constructor default values;
    public Television() {
        this.isOn = false;
        this.currentChannel = 3;
        this.currentVolume = 2;

    }

    // getters
    public boolean isOn() {
        return isOn;
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    // various control methods

    public void turnOff() {
        this.isOn = false;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void changeChannel(int newChannel) {
        if (isOn && newChannel >= 3 && newChannel <= 18)
            this.currentChannel = newChannel;

    }

    public void channelUp() {
        if (isOn)
            this.currentChannel++;
        if (this.currentChannel > 18)
            this.currentChannel = 3;
    }

    public void channelDown() {
        if (isOn)
            this.currentChannel--;
        if (this.currentChannel < 3)
            this.currentChannel = 18;
    }

    public void raiseVolume() {
        if (isOn)
            this.currentVolume++;
        if (this.currentVolume >= 10)
            this.currentVolume = 10;
    }

    public void lowerVolume() {
        //for (int i = currentVolume; i < 0; i--)
        if (isOn)
            this.currentVolume--;
        if (this.currentVolume <= 0)
            this.currentVolume = 0;
    }

}
