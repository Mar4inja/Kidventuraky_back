package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.Kid;

import java.util.List;

public interface KidService {

    Kid save(Kid kid);

    List<Kid> getAll();

    Kid update(Kid updatetedKid);

    Kid delete(Long id);
}
