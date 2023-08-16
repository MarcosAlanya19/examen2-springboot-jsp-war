package com.examen.forge.app.domain.repositories;

import com.examen.forge.app.domain.entities.WaiterEntity;
import com.examen.forge.app.infraestructure.shared.BaseRepository;

public interface WaiterRepository extends BaseRepository<WaiterEntity> {
  WaiterEntity findByEmail(String email);
  boolean existsByEmail(String email);
}
