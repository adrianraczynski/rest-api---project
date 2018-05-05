package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    SimpleEmailService simpleEmailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> listOfTrelloListDto = new ArrayList<>();
        listOfTrelloListDto.add(new TrelloListDto("1", "test1", true));

        List<TrelloBoardDto> listOfTrelloBoardDto = new ArrayList<>();
        listOfTrelloBoardDto.add(new TrelloBoardDto("2", "test2", listOfTrelloListDto));

        when(trelloClient.getTrelloBoards()).thenReturn(listOfTrelloBoardDto);

        //When
        List<TrelloBoardDto> fetchTrelloBoards = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertEquals(1, fetchTrelloBoards.size());
        Assert.assertEquals("2", fetchTrelloBoards.get(0).getId());
        Assert.assertEquals("1", fetchTrelloBoards.get(0).getLists().get(0).getId());
        Assert.assertEquals(true, fetchTrelloBoards.get(0).getLists().get(0).isClosed());
    }

    /**

    @Test
    public void testCreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TEST1","test2", "test3", "12");
        //Mail mail = new Mail ("nowak2764@gmail.com", "Test", "Test message", "adam.nowak@gmail.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(trelloCardDto);

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getListId(), createdTrelloCardDto.getId());
    }   **/
}