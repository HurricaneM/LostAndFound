package com.hur.lostandfound.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Lost {
    @Id
    int id;
    String name;
    String nowAddress;
    Date lostDate;
    String lostPlace;
    String otherMsg;
    String picture;
}
