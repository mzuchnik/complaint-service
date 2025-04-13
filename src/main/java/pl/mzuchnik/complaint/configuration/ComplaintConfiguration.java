package pl.mzuchnik.complaint.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.mzuchnik.complaint.domain.ComplaintService;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;
import pl.mzuchnik.complaint.infrastructure.adapters.InMemoryComplaintRepository;

@Configuration
class ComplaintConfiguration {

    @Bean
    @Profile("local")
    ComplaintRepository complaintRepository(){
        return new InMemoryComplaintRepository();
    }

    /*@Bean
    @Primary
    @Profile("postgres")
    ComplaintService complaintService(){

    }*/

    @Bean
    ComplaintService complaintService(ComplaintRepository complaintRepository){
        return new ComplaintService(complaintRepository);
    }


}
