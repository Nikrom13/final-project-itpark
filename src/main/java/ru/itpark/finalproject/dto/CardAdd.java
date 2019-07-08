package ru.itpark.finalproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardAdd {
  private String name;
  private int rate;
  private String description;

}
