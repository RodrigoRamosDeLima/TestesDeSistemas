package com.example.fazenda.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fazenda.entities.Fazenda;
import com.example.fazenda.repositories.FazendaRepository;

@Service
public class FazendaService {
    @Autowired
    private FazendaRepository repository;

    @Transactional
    public Fazenda create(Fazenda fazenda) throws Exception {
        if (this.validarFazenda(fazenda)) {
            return repository.save(fazenda);
        } else {
            throw new Exception("Erro de validação no cadastro da fazenda");
        }
    }

    public Fazenda read(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Fazenda não encontrada"));
    }

    public List<Fazenda> list() {
        return (List<Fazenda>) repository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Fazenda não encontrada");
        }
        repository.deleteById(id);
    }

    @Transactional
    public Fazenda update(Fazenda fazenda) throws Exception {
        if (repository.existsById(fazenda.getId())) {
            return repository.save(fazenda);
        } else {
            throw new NoSuchElementException("Fazenda não encontrada");
        }
    }

    public boolean validarFazenda(Fazenda fazenda) {
        return validarString(fazenda.getNome()) &&
               validarArea(fazenda.getAreaTotal()) &&
               validarArea(fazenda.getAreaAgricultavel()) &&
               validarArea(fazenda.getAreaNaoAgricultavel()) &&
               validarCpf(fazenda.getCpfProprietario()) &&
               validarAreas(fazenda) &&
               validarLatitude(fazenda.getLatitude()) &&
               validarLongitude(fazenda.getLongitude());
    }

    private boolean validarString(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    private boolean validarAreas(Fazenda fazenda) {
        return fazenda.getAreaAgricultavel() + fazenda.getAreaNaoAgricultavel() <= fazenda.getAreaTotal();
    }

    private boolean validarArea(double area) {
        return area >= 0;
    }

    private boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}"); // Exemplo de regex para CPF com 11 dígitos
    }

    private boolean validarLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    private boolean validarLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }
}