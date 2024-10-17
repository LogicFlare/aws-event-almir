package api.lg.aws.adapters;

import api.lg.aws.domain.Event;
import api.lg.aws.dto.EventRequestDTO;
import api.lg.aws.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    public ResponseEntity<Event> create(@RequestBody EventRequestDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(data));
    }

}
