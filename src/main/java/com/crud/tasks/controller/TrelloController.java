package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createdTrelloCard(trelloCardDto);
    }

    /**
    //zad 18.2
    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards182")
    public List<TrelloBoardDto> getTrelloBoards182() {                                             // zadanie moduł 18.2 tutaj metoda będzie typu List<TrelloBoardDto>
                                                                                                  // metoda będzie zwracać (return) po filtracji obiekt trelloBoards
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

        return trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .collect(Collectors.toList());
    }   **/
}