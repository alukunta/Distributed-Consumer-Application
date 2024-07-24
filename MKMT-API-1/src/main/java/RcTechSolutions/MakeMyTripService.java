package RcTechSolutions;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
@Service
public class MakeMyTripService {
	public Mono<Ticket> bookTicket(Passenger p) {

		String apiUrl = "http://3.108.190.10:8080/ticket";
		
		WebClient webClient = WebClient.create();
		
	  	Mono<Ticket> bodyToMono = webClient.post()
								  		   .uri(apiUrl)
								  		   .body(BodyInserters.fromValue(p))
								  		   .retrieve()
								  		   .bodyToMono(Ticket.class);
		return bodyToMono;
	}

	public Mono<Ticket[]> getAllTickets() {

		String apiUrl = "http://3.108.190.10:8080/tickets";
		
		WebClient webClient = WebClient.create();
		
		Mono<Ticket[]> bodyToMono = webClient.get()
											 .uri(apiUrl)
											 .retrieve()
											 .bodyToMono(Ticket[].class);
		return bodyToMono;
	}

}
