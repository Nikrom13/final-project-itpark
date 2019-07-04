package ru.itpark.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.finalproject.domain.Card;
import ru.itpark.finalproject.dto.CardAdd;
import ru.itpark.finalproject.repository.CardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;

    public List<Card> findAll() {
        return repository.findAll();
    }

    public Card findById(int id) {
        return repository.findById(id);
    }

    public void save(Card card){
        repository.save(card);
    }

    public void removeById (int id){
       repository.removeById(id);
    }

    public void add(CardAdd dto) {
        var card = new Card(
                0,
                dto.getCardname(),
                dto.getRate(),
                dto.getDescription());
        save(card);
    }

}
