package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("1", "TestListDto1", false);
        TrelloListDto listDto2 = new TrelloListDto("2", "TestListDto2", false);
        TrelloListDto listDto3 = new TrelloListDto("3", "TestListDto3", false);

        List<TrelloListDto> exampleTrelloListDto = new ArrayList<>();
        exampleTrelloListDto.add(listDto1);
        exampleTrelloListDto.add(listDto2);
        exampleTrelloListDto.add(listDto3);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "nameTest", exampleTrelloListDto);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        int listSize = trelloBoardDtoList.size();
        String id = trelloBoardList.get(0).getId();
        String name = trelloBoardList.get(0).getName();

        //Then
        assertEquals(1, listSize);
        assertEquals("1", id);
        assertEquals("nameTest", name);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList list1 = new TrelloList("1", "TestList1", true);
        TrelloList list2 = new TrelloList("2", "TestList2", true);
        TrelloList list3 = new TrelloList("3", "TestList3", true);

        List<TrelloList> exampleTrelloList = new ArrayList<>();
        exampleTrelloList.add(list1);
        exampleTrelloList.add(list2);
        exampleTrelloList.add(list3);


        TrelloBoard trelloBoard = new TrelloBoard("1", "Test", exampleTrelloList);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        int listSize = trelloBoardDtoList.size();
        String id = trelloBoardDtoList.get(0).getId();
        String name = trelloBoardDtoList.get(0).getName();

        //Then
        assertEquals(1, listSize);
        assertEquals("1", id);
        assertEquals("Test", name);
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("1", "TestListDto1", false);
        TrelloListDto listDto2 = new TrelloListDto("2", "TestListDto2", false);
        TrelloListDto listDto3 = new TrelloListDto("3", "TestListDto3", false);

        List<TrelloListDto> exampleTrelloListDto = new ArrayList<>();
        exampleTrelloListDto.add(listDto1);
        exampleTrelloListDto.add(listDto2);
        exampleTrelloListDto.add(listDto3);

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(exampleTrelloListDto);
        int listSize = trelloList.size();

        //Then
        assertEquals(3, listSize);
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList list1 = new TrelloList("1", "TestList1", true);
        TrelloList list2 = new TrelloList("2", "TestList2", true);
        TrelloList list3 = new TrelloList("3", "TestList3", true);

        List<TrelloList> exampleTrelloList = new ArrayList<>();
        exampleTrelloList.add(list1);
        exampleTrelloList.add(list2);
        exampleTrelloList.add(list3);

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(exampleTrelloList);
        int listSize = trelloListDto.size();

        //Then
        assertEquals(3, listSize);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("nameTest", "descritionTest", "posTest", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        String name = trelloCard.getName();
        String description = trelloCard.getDescription();
        String pos = trelloCard.getPos();
        String listId = trelloCard.getListId();

        //Then
        assertEquals("nameTest", name);
        assertEquals("descritionTest", description);
        assertEquals("posTest", pos);
        assertEquals("1", listId);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestName", "TestDescrition", "testPos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        String name = trelloCardDto.getName();
        String description = trelloCardDto.getDescription();
        String pos = trelloCardDto.getPos();
        String listId = trelloCardDto.getListId();

        //Then
        assertEquals("TestName", name);
        assertEquals("TestDescrition", description);
        assertEquals("testPos", pos);
        assertEquals("1", listId);
    }
}