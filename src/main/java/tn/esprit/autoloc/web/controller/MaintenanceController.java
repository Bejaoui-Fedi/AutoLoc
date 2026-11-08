package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances de véhicules")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @GetMapping
    @Operation(summary = "Lister toutes les maintenances")
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une maintenance par ID")
    public Maintenance findById(@PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une maintenance")
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une maintenance")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une maintenance")
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}