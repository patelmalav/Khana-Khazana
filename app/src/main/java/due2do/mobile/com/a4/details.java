package due2do.mobile.com.a4;

import java.io.Serializable;

/**
 * Created by malav on 4/6/2018.
 */

public class details implements Serializable{
    private String name, discription, ingridents, steps, key;
    float rate;


    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getIngridents() {
        return ingridents;
    }

    public void setIngridents(String ingridents) {
        this.ingridents = ingridents;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
