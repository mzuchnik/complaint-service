package pl.mzuchnik.complaint.domain.model;

public record Country(String country) {
    public Country {
        if(country == null || country.isEmpty()){
            throw new IllegalArgumentException("Country is null or empty");
        }
    }
}
