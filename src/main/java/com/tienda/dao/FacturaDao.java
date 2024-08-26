
package com.tienda.dao;

import com.tienda.domain.Factura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacturaDao  extends JpaRepository<Factura, Long>{
    
     
     
}
