package com.example.fut_order.controller;

import com.example.fut_order.dto.CamisaCreateDTO;
import com.example.fut_order.dto.CamisaResponseDTO;
import com.example.fut_order.dto.CamisaUpdateDTO;
import com.example.fut_order.service.CamisaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camisa")
public class CamisaController {

    private final CamisaService camisaService;

    public CamisaController(CamisaService camisaService) {
        this.camisaService = camisaService;
    }

    //Post
    @PostMapping
    public ResponseEntity<CamisaResponseDTO> cadastrarCamisa(@RequestBody @Valid CamisaCreateDTO dto){
        CamisaResponseDTO camisaResponseDTO = camisaService.cadastrarCamisa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(camisaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CamisaResponseDTO>> listarCamisas(){
        return ResponseEntity.ok(camisaService.listarTodasCamisas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CamisaResponseDTO> buscarPorId(@PathVariable Long id){
        CamisaResponseDTO camisaResponseDTO = camisaService.buscarPorId(id);
        return ResponseEntity.ok(camisaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CamisaResponseDTO> atualizarCamisa(@PathVariable Long id, @RequestBody @Valid CamisaUpdateDTO dto){
        CamisaResponseDTO camisaResponseDTO = camisaService.atualizarCamisa(id, dto);
        return ResponseEntity.ok(camisaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCamisa(@PathVariable Long id){
        camisaService.deletarCamisa(id);
        return ResponseEntity.noContent().build();
    }

}
