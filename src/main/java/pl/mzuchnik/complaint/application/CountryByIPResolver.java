package pl.mzuchnik.complaint.application;

import pl.mzuchnik.complaint.domain.model.Country;

public interface CountryByIPResolver {

    Country getCountryByIP(String ip);
}
