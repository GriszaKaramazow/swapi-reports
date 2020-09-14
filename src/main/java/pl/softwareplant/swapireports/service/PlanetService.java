package pl.softwareplant.swapireports.service;

import pl.softwareplant.swapireports.repository.PlanetRepository;

public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

}
