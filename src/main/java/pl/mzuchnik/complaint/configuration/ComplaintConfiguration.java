package pl.mzuchnik.complaint.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mzuchnik.complaint.domain.ComplaintFacade;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

@Configuration
class ComplaintConfiguration {

    @Bean
    ComplaintFacade complaintService(ComplaintRepository complaintRepository){
        return new ComplaintFacade(complaintRepository);
    }
    
}
