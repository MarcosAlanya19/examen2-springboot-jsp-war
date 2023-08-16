package com.examen.forge.app.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.forge.app.domain.entities.TableEntity;
import com.examen.forge.app.domain.entities.WaiterEntity;
import com.examen.forge.app.domain.repositories.TableRepository;
import com.examen.forge.app.infraestructure.shared.BaseService;

@Service
public class TableService extends BaseService<TableEntity> {
  @Autowired
  TableRepository tableRepository;

  public List<TableEntity> getByWaiter(WaiterEntity waiter) {
    return tableRepository.findByWaiter(waiter);
  }
}
