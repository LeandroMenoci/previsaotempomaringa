
package com.example.weathermaringa;

public class WeatherItem {
    public String weekday;
    public String description;
    public int max;
    public int min;

    public WeatherItem(String weekday, String description, int max, int min) {
        this.weekday = weekday;
        this.description = description;
        this.max = max;
        this.min = min;
    }
}
