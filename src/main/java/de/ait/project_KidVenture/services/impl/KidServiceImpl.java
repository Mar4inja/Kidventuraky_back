package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Kid;
import de.ait.project_KidVenture.repository.KidRepository;
import de.ait.project_KidVenture.exceptions.KidsRepositoryIsEmptyException;
import de.ait.project_KidVenture.services.KidService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KidServiceImpl implements KidService {

    private KidRepository kidRepository;

    @Override
    public Kid save(Kid kid) {
        return null;
    }

    @Override
    public List<Kid> getAll() {
        if (kidRepository.findAll().isEmpty() || kidRepository.findAll().size() == 0) {
            throw new KidsRepositoryIsEmptyException("No children have registered on your site yet!!!");
        }
        return kidRepository.findAll();
    }

    @Override
    public Kid update(Kid updatetedKid) {
        return null;
    }

    @Override
    public Kid delete(Long id) {
        return null;
    }
}
