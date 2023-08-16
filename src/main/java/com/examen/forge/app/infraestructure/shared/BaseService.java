package com.examen.forge.app.infraestructure.shared;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T> {
  @Autowired
  private BaseRepository<T> repositoryBase;

  public List<T> getAll() {
    List<T> T = (List<T>) repositoryBase.findAll();
    return T;
  }

  public T getById(Long id) {
    Optional<T> optional = repositoryBase.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      return null;
    }
  }

  public T create(T Object) {
    return repositoryBase.save(Object);
  }

  public T updateById(T Object) {
    return create(Object);
  }

  public void deleteById(Long id) {
    repositoryBase.deleteById(id);
  }
}
