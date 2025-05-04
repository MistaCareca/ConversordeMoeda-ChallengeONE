package src.main.com.conversordemoeda;

public class ConversaoResponse {
    private String base_code;
    private String target_code;
    private double conversion_rate;
    private String error_type;

    public String getBaseCode() {
        return base_code;
    }

    public double getConversionRate() {
        return conversion_rate;
    }

    public String getTargetCode() {
        return target_code;
    }

    public String getErrorType() {
        return error_type;
    }
}