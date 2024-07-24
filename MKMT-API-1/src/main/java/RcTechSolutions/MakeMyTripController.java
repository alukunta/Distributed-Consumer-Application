package RcTechSolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;

@Controller
public class MakeMyTripController {
	
	@Autowired
	private MakeMyTripService service;
	
	@PostMapping("/ticket")
	public String ticketBooking(@ModelAttribute("p") Passenger p, Model model) {
		Mono<Ticket> bookTicket = service.bookTicket(p);
		model.addAttribute("ticket", bookTicket);
		model.addAttribute("msg", "Your Ticket Booked");
		return "bookTicket";
	}

	@GetMapping("/book-ticket")
	public String bookTicket(Model model) {
		model.addAttribute("p", new Passenger());
		model.addAttribute("ticket", new Ticket());
		return "bookTicket";
	}

	@GetMapping("/")
	public String index(Model model) {
		Mono<Ticket[]> allTickets = service.getAllTickets();
		model.addAttribute("tickets", allTickets);
		return "index";
	}


}
