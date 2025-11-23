package entities;

import lombok.Getter;
import lombok.Setter;

public abstract class Air extends Entity {
    @Getter @Setter
    private double humidity;
    @Getter @Setter
    private double temperature;
    @Getter @Setter
    private double oxygenLevel;

    public Air (String name, double mass , double humidity,  double temperature, double oxygenLevel ) {
        super(name, mass);
        this.humidity = humidity;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
    }

    public abstract double calculateQualityScore();
}
