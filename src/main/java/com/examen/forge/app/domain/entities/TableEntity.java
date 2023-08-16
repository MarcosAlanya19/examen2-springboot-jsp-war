package com.examen.forge.app.domain.entities;

import com.examen.forge.app.infraestructure.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
public class TableEntity extends BaseEntity {
  @NotNull(message = "El titulo no puede estar vacio")
  @NotBlank(message = "El titulo no puede estar vacio")
  @Column(unique = true)
  private String name;

  @NotNull(message = "El genero no puede estar vacia")
  private Integer number = 0;

  @NotNull(message = "La letra no puede estar vacia")
  @NotBlank(message = "La letra no puede estar vacia")
  @Size(min = 5, message = "La letra debe tener como minimo 5 caracteres")
  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne
  @JoinColumn(name = "waiter_id")
  private WaiterEntity waiter;
}
