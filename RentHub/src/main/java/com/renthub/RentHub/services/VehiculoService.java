/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.services;

import com.renthub.RentHub.models.entities.Vehiculo;
import com.renthub.RentHub.models.repository.VehiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author javie
 */
@Service
public class VehiculoService {
    
    @Autowired
    private VehiculoRepository vehiculoRepository;
    
    
    public List<Vehiculo> findAll(){
        List<Vehiculo> lista =(List<Vehiculo>)vehiculoRepository.findAll();
        
        return lista;
    }
    
    public Vehiculo findById(Long id) {
        return vehiculoRepository.findById(id)
                   .orElseThrow(() ->
                     new EntityNotFoundException("Vehículo no encontrado con id " + id)
                   );
    }
    
    
    @Value("${app.upload.dir:${user.dir}/src/main/resources/static/images}")
    private String uploadDir;
    
    public Vehiculo saveWithImages(
        String marca, String modelo, String matricula, String tipo,
        Double precioMes, String km, String fuel, String category,
        String transmission, String descripcion,
        MultipartFile[] imagenes
    ) throws IOException {

        Vehiculo v = new Vehiculo();
        v.setMarca(marca);
        v.setModelo(modelo);
        v.setMatricula(matricula);
        v.setTipo(tipo);
        v.setPrecioMes(precioMes);
        v.setKm(km);
        v.setFuel(fuel);
        v.setCategory(category);
        v.setTransmission(transmission);
        v.setDescripcion(descripcion);

        List<String> urls = new ArrayList<>();
        // Asegúrate de que la carpeta existe
        Files.createDirectories(Paths.get(uploadDir));

        for (MultipartFile file : imagenes) {
            String ext = Optional.ofNullable(file.getOriginalFilename())
                .filter(n -> n.contains("."))
                .map(n -> n.substring(n.lastIndexOf('.')))
                .orElse("");
            String filename = UUID.randomUUID() + ext;
            Path target = Paths.get(uploadDir).resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            // Guardamos la ruta relativa (servirá como URL)
            urls.add("http://localhost:8080/images/" + filename);
        }
        v.setImagenes(urls);

        return vehiculoRepository.save(v);
    }
    
    
}
