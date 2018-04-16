package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor              //Trzeba dodać bo nie ma w module 18.3
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
