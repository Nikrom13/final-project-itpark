package ru.itpark.finalproject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.finalproject.domain.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CardRepository {
  private final NamedParameterJdbcTemplate template;


  public List<Card> findAll() {

    return template.query(
            "SELECT id, cardname, rate, description FROM cards",
            (rs, i) -> new Card(
                    rs.getInt("id"),
                    rs.getString("cardname"),
                    rs.getInt("rate"),
                    rs.getString("description")
            )
    );
  }


  public Card findById(int id) {
    return template.queryForObject(
            "SELECT id, cardname, rate, description FROM cards WHERE id = :id",
            Map.of("id", id), new RowMapper<Card>() {
              @Override
              public Card mapRow(ResultSet rs, int i) throws SQLException {
                return new Card(
                        rs.getInt("id"),
                        rs.getString("cardname"),
                        rs.getInt("rate"),
                        rs.getString("description")
                );
              }
            }
    );
  }

  public void save(Card card) {
    if (card.getId() == 0) {
      template.update("INSERT INTO cards (cardname,rate,description) VALUES (:cardname,:rate,:description)",
              Map.of(
                      "cardname", card.getCardname(),
                      "rate", card.getRate(),
                      "description", card.getDescription()
              )
      );
    } else {
      template.update("UPDATE cards SET cardname = :cardname, rate = :rate, description = :description WHERE id = :id",
              Map.of(
                      "id", card.getId(),
                      "cardname", card.getCardname(),
                      "rate", card.getRate(),
                      "description", card.getDescription()
              )
      );
    }

  }

  public void removeById(int id) {
    template.update("DELETE FROM cards WHERE id = :id", Map.of("id", id));
  }

    public List<Card> findByRequest(String search) {
      return template.query("SELECT id, cardname, rate, description FROM cards WHERE cardname = :search",
              Map.of("search", search),
              (rs, i) -> new Card(
                      rs.getInt("id"),
                      rs.getString("cardname"),
                      rs.getInt("rate"),
                      rs.getString("description")
              )
      );
    }
}