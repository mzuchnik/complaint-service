package pl.mzuchnik.complaint.infrastructure.adapters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pl.mzuchnik.complaint.application.CountryByIPResolver;
import pl.mzuchnik.complaint.domain.model.Country;

import java.net.URI;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
class ExternalCountryByIPResolver implements CountryByIPResolver {

    private final RestClient.Builder restClientBuilder;
    private static final String URL_TEMPLATE = "http://ip-api.com/json/%s?fields=status,country";
    private static final Country UNKNOWN_COUNTRY = new Country("UNKNOWN");

    @Override
    public Country getCountryByIP(String ip) {
        String url = String.format(URL_TEMPLATE, ip);

        try {
            Optional<IpApiResponse> responseOptional =
                    Optional.ofNullable(restClientBuilder.build()
                            .get()
                            .uri(URI.create(url))
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .body(IpApiResponse.class));

            return responseOptional
                    .filter(response -> response.status.equalsIgnoreCase("success"))
                    .map(response -> new Country(response.country))
                    .orElse(UNKNOWN_COUNTRY);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return UNKNOWN_COUNTRY;
        }

    }

    private record IpApiResponse(String country, String status) {
    }

}
