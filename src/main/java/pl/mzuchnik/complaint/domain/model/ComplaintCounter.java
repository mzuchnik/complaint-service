package pl.mzuchnik.complaint.domain.model;

public record ComplaintCounter(int counter) {

    private static final int START_COUNTER_VALUE = 1;

    public ComplaintCounter {
        if(counter <= 0){
            throw new IllegalArgumentException("Counter should be greater than 0");
        }
    }

    public static ComplaintCounter createNew() {
        return new ComplaintCounter(START_COUNTER_VALUE);
    }

    public ComplaintCounter increment() {
        return new ComplaintCounter(counter + 1);
    }
}
