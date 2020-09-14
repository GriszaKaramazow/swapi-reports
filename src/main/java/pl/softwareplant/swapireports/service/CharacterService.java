package pl.softwareplant.swapireports.service;

import pl.softwareplant.swapireports.repository.CharacterRepository;

public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

}
