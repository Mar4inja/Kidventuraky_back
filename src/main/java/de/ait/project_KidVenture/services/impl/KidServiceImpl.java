package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Kid;
import de.ait.project_KidVenture.exceptions.UserIsNotExistException;
import de.ait.project_KidVenture.repository.KidRepository;
import de.ait.project_KidVenture.services.KidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KidServiceImpl implements KidService {


    private final KidRepository kidRepository;

    @Override
    public Kid save(Kid kid) {
        return null;
    }

    @Override
    public List<Kid> getAll() {
        return kidRepository.findAll();
    }

    @Override
    public Kid update(Kid updatetedKid) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            if (!kidRepository.existsById(id)) {
                throw new UserIsNotExistException("Person with id: " + id + " does not exist");
            }
            kidRepository.deleteById(id);
        } catch (UserIsNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}
