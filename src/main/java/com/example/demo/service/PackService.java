package com.example.demo.service;

import com.example.demo.entity.Pack;
import com.example.demo.repository.PackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackService {

    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public Pack createPack(Pack pack) {
        return packRepository.save(pack);
    }

    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }

    public Optional<Pack> getPackById(Long id) {
        return packRepository.findById(id);
    }

    public Pack updatePack(Long id, Pack updatedPack) {
        return packRepository.findById(id)
                .map(pack -> {
                    pack.setYear(updatedPack.getYear());
                    pack.setSemester(updatedPack.getSemester());
                    pack.setName(updatedPack.getName());
                    return packRepository.save(pack);
                })
                .orElseThrow(() -> new RuntimeException("Pack not found with id " + id));
    }

    public void deletePack(Long id) {
        packRepository.deleteById(id);
    }
}
