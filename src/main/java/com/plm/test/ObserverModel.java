package com.plm.test;


import java.util.ArrayList;

/**
 *  观察者模式
 *
 *      定义了一系列对象之间一对多的关系
 *
 *
 *      观察者模式涉及到了如下几点定义：
 * 　　       抽象主题角色
 *
 * 　　       具体主题角色
 *
 * 　　       抽象观察者角色
 *
 * 　　       具体观察者角色
 */

// 抽象主题角色
interface Subject{
    void registerObservers(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver();
}
// 具体主题角色
class WeatherData implements Subject{

    private float temperature;
    private float pressure;
    private float humidity;

    // 观察者集合
    private ArrayList<Observer> observers;

    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    // 当数据有更新时，就调用setData
    public void setWeatherDate(float temperature,float pressure,float humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //将最新的消息推送给观察者
        notifyObserver();
    }

    @Override
    public void registerObservers(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        if(observers.contains(observer)){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for(int i=0;i<observers.size();i++){
            observers.get(i).update(this.temperature,this.pressure,this.humidity);
        }
    }
}

// 抽象观察者角色
interface Observer{
    void update(float tempareture,float pressure,float humidity);
}

// 具体观察者角色
class CurrentWeather implements Observer{
    private float temperature;
    private float pressure;
    private float humidity;
    private String str;

    public CurrentWeather(String str) {
        this.str = str;
    }

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        display();
    }

    public void display(){
        System.out.println("I'm observer : "+str);
        System.out.println("current weather is:");
        System.out.println(" temperature : "+temperature);
        System.out.println(" pressure : "+pressure);
        System.out.println(" humidity : "+humidity);
    }
}

// 如果要创建新的观察者类，只需要实现 Observer接口实现 update()方法就行

public class ObserverModel {
    public static void main(String[] args) {
        // 主题
        WeatherData weatherData = new WeatherData();

        // 观察者
        CurrentWeather currentWeather1 = new CurrentWeather("currentWeather1");
        CurrentWeather currentWeather2 = new CurrentWeather("currentWeather2");

        // 观察者 注册到 主题中
        weatherData.registerObservers(currentWeather1);
        weatherData.registerObservers(currentWeather2);
        System.out.println("消息发送给观察者：");
        weatherData.setWeatherDate(10f,20f,30.3f);
    }
}
