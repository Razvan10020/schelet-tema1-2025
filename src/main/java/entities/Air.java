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

    public Air (String name, double mass , double humidity,  double temperature, double oxygenLevel) {
        super(name, mass);
        this.humidity = humidity;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
    }


    public abstract double getMaxScore();

    public double PossibilityToGetDamagedByAir() {
        double airQualityScore = calculateQualityScore();
        double maxScore = getMaxScore();

        // Calculam toxicitatea reala
        double toxicityAQ = 100 * (1 - airQualityScore / maxScore);

        // Normalizare si Rotunjire
        double finalResult = Math.round(toxicityAQ * 100.0) / 100.0;
        return Math.max(0, finalResult);
    }

    public boolean isToxic() {
        double toxicityAQ = PossibilityToGetDamagedByAir();
        double maxScore = getMaxScore();

        return toxicityAQ > (0.8 * maxScore);
    }

    public abstract double calculateQualityScore();
}
