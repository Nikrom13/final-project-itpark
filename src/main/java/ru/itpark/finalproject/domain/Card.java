package ru.itpark.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
  private int id;
  private String cardname;
  private int rate;
  private String description;
}
