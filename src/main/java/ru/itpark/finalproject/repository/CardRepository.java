package ru.itpark.finalproject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.finalproject.domain.Card;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepository {
    private final JdbcTemplate jdbcTemplate;


    public List<Card> findAll() {

        return jdbcTemplate.query(
                "SELECT id, cardname, rate, description FROM cards",
                (rs, i) -> new Card(
                        rs.getInt("id"),
                        rs.getString("cardname"),
                        rs.getInt("rate"),
                        rs.getString("descpription")
                )
        );
    }


    public Card findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, cardname, rate, description FROM cards WHERE id = ?",
                new Object[]{id},
                (rs, i) -> new Card(
                        rs.getInt("id"),
                        rs.getString("cardname"),
                        rs.getInt("rate"),
                        rs.getString("descpription")
                )
        );
    }

    public void save(Card card) {
        if (card.getId() == 0) {
            jdbcTemplate.update("INSERT INTO cards (cardname, rate, description) VALUES (?, ?, ?)",
                    card.getCardname(), card.getRate(), card.getDescription()
            );
        } else {
            jdbcTemplate.update("UPDATE cards SET cardname = ?, description = ? WHERE id = ?",
                    card.getCardname(), card.getDescription(), card.getId()
            );
        }

    }
}