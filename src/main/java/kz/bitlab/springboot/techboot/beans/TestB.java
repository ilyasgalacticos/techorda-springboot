package kz.bitlab.springboot.techboot.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestB {

    public TestB(){
        System.out.println("I am using default of B");
    }

    public TestB(String code, double volume){
        this.code = code;
        this.volume = volume;
        System.out.println("I am using parametrized of B");
    }

    private double volume;
    private String code;

}