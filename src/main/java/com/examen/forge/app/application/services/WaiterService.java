package com.examen.forge.app.application.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.forge.app.domain.entities.WaiterEntity;
import com.examen.forge.app.domain.repositories.WaiterRepository;
import com.examen.forge.app.infraestructure.shared.BaseService;

@Service
public class WaiterService extends BaseService<WaiterEntity> {
  @Autowired
  WaiterRepository userRepository;

  // Guardar usuario con contraseña hasheada
  @Override
  public WaiterEntity create(WaiterEntity user) {
    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(hashed);
    return userRepository.save(user);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  // Buscar usuario mendiante email
  public WaiterEntity getByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  // Autentificacion de usuario
  public boolean authenticateUser(String email, String password) {
    WaiterEntity user = userRepository.findByEmail(email);
    if (user == null) {
      return false;
    } else {
      if (BCrypt.checkpw(password, user.getPassword())) {
        return true;
      } else {
        return false;
      }
    }
  }
}
