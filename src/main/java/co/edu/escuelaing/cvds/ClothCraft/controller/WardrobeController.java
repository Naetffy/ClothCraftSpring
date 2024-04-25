package co.edu.escuelaing.cvds.ClothCraft.controller;

import co.edu.escuelaing.cvds.ClothCraft.model.Wardrobe;
import co.edu.escuelaing.cvds.ClothCraft.model.DTO.WardrobeDTO;
import co.edu.escuelaing.cvds.ClothCraft.service.WardrobeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wardrobe")
public class WardrobeController {

    @Autowired
    private WardrobeService wardrobeService;

    @GetMapping("/{id}")
    public ResponseEntity<WardrobeDTO> getWardrobeById(@PathVariable String id) {
        Wardrobe wardrobe = wardrobeService.getWardrobeById(id);
        if (wardrobe != null) {
            return new ResponseEntity<>(wardrobe.toDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<WardrobeDTO>> getAllWardrobes() {
        List<Wardrobe> wardrobeList = wardrobeService.getAllWardrobes();
        List<WardrobeDTO> wardrobeDTOList = wardrobeList.stream()
                .map(Wardrobe::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(wardrobeDTOList, HttpStatus.OK);
    }
    /* 
    @PostMapping
    public ResponseEntity<WardrobeDTO> createWardrobe(@RequestBody WardrobeDTO wardrobeDTO) {
        Wardrobe wardrobe = wardrobeService.createWardrobe(wardrobeDTO.toEntity());
        if (wardrobe != null) {
            return new ResponseEntity<>(wardrobe.toDTO(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WardrobeDTO> updateWardrobe(@PathVariable String id, @RequestBody WardrobeDTO wardrobeDTO) {
        Wardrobe updatedWardrobe = wardrobeService.updateWardrobe(id, wardrobeDTO.toEntity());
        if (updatedWardrobe != null) {
            return new ResponseEntity<>(updatedWardrobe.toDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWardrobe(@PathVariable String id) {
        boolean deleted = wardrobeService.deleteWardrobe(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
