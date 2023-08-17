package com.examen.forge.app.domain.entities;

import com.examen.forge.app.infraestructure.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
  @NotNull(message = "El nombre no puede estar vacio")
  @NotBlank(message = "El nombre no puede estar vacio")
  @Size(min = 2, message = "El nombre como minimo debe tener 2 caracteres")
  @Column(unique = true)
  private String name;

  @NotNull(message = "El genero no puede estar vacia")
  @Min(value = 1, message = "El número debe ser como mínimo 1")
  @Max(value = 10, message = "El número debe ser como máximo 10")
  private Integer number = 0;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne
  @JoinColumn(name = "waiter_id")
  private WaiterEntity waiter;
}
