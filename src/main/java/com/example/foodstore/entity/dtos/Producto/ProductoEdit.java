package com.example.foodstore.entity.dtos.Producto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductoEdit {
  private double precio;
  private int   stock;


}
