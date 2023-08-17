package com.examen.forge.app.domain.repositories;

import java.util.List;

import com.examen.forge.app.domain.entities.TableEntity;
import com.examen.forge.app.domain.entities.WaiterEntity;
import com.examen.forge.app.infraestructure.shared.BaseRepository;


public interface TableRepository extends BaseRepository<TableEntity> {
  TableEntity findByName(String name);
  List<TableEntity> findByWaiter(WaiterEntity waiter);
  List<TableEntity> findByWaiterIsNull();
}
