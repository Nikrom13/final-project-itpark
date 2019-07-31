package ru.itpark.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itpark.finalproject.domain.Card;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.domain.UserCards;
import ru.itpark.finalproject.dto.CardAdd;
import ru.itpark.finalproject.dto.UserInfo;
import ru.itpark.finalproject.repository.CardRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;
    private final NamedParameterJdbcTemplate template;

    public List<Card> findAll() {
        return repository.findAll();
    }

    public List<Card> findUserCards(int id) {
        return repository.findUserCards(id);
    }

    public Card findById(int id) {
        return repository.findById(id);
    }

    public void save(Card card) {
        repository.save(card);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public void add(CardAdd dto) {
        var card = new Card(
                0,
                dto.getName(),
                dto.getRate(),
                dto.getDescription());
        save(card);
    }

    public void editById(int id, CardAdd data) {
        Card card = new Card(id, data.getName(), data.getRate(), data.getDescription());
        repository.save(card);
    }

    public List<Card> findByRequest(String search) {
        return repository.findByRequest(search);
    }


    public void registerCard(Card card, UserInfo userInfo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();
        template.update("INSERT INTO userCards (user_id, card_id) VALUES (:user_id, :card_id)",
                Map.of(
                        "user_id", id,
                        "card_id", card.getId()
                )
        );
        template.update("UPDATE users SET first_name = :first_name, second_name = :second_name, country = :country , city = :city, address = :address," +
                        " phone_number = :phone_number, second_phone_number = :second_phone_number WHERE id = :id",
                Map.of(
                        "id", id,
                        "first_name", userInfo.getFirstName(),
                        "second_name", userInfo.getSecondName(),
                        "country",userInfo.getCountry(),
                        "city",userInfo.getCity(),
                        "address",userInfo.getAddress(),
                        "phone_number",userInfo.getPhoneNumber(),
                        "second_phone_number",userInfo.getSecondPhoneNumber()
                )
        );
    }
}
