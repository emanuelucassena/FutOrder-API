package com.example.fut_order.service;

import com.example.fut_order.dto.CamisaCreateDTO;
import com.example.fut_order.dto.CamisaResponseDTO;
import com.example.fut_order.dto.CamisaUpdateDTO;
import com.example.fut_order.exceptions.CamisaNaoEncontradaException;
import com.example.fut_order.model.Camisa;
import com.example.fut_order.repository.CamisaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CamisaService {

    private CamisaRepository camisaRepository;

    public CamisaService(CamisaRepository camisaRepository){
        this.camisaRepository = camisaRepository;
    }

    // método que Lista todas as camisas cadatsradas
    public List<CamisaResponseDTO> listarTodasCamisas(){
        return camisaRepository.findAll()
                .stream()
                .map(camisa -> new CamisaResponseDTO(camisa))
                .collect(Collectors.toList());
    }

    //busca por ID
    public CamisaResponseDTO buscarPorId(Long id){
        return camisaRepository.findById(id).map(CamisaResponseDTO::new)
                .orElseThrow(()-> new CamisaNaoEncontradaException(id));
    }


    public CamisaResponseDTO cadastrarCamisa(CamisaCreateDTO camisaCreateDTO){
        var camisa = new Camisa();
        BeanUtils.copyProperties(camisaCreateDTO, camisa);
        camisaRepository.save(camisa);
        return new CamisaResponseDTO(camisa);
    }


    public CamisaResponseDTO atualizarCamisa (Long id, CamisaUpdateDTO camisaUpdateDTO){
        Camisa camisa = camisaRepository.findById(id).orElseThrow(() -> new CamisaNaoEncontradaException(id));
        BeanUtils.copyProperties(camisaUpdateDTO, camisa);
        camisaRepository.save(camisa);
        return new CamisaResponseDTO(camisa);
    }

    public void deletarCamisa(Long id){
        Camisa camisa = camisaRepository.findById(id).orElseThrow(()-> new CamisaNaoEncontradaException(id));
        camisaRepository.delete(camisa);
    }
}
