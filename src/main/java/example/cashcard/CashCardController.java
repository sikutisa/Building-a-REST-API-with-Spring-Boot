package example.cashcard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
class CashCardController {

    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestId) {
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestId);

        if(cashCardOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cashCardOptional.get());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
