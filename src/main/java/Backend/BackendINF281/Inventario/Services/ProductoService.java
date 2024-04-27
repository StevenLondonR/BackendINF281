package Backend.BackendINF281.Inventario.Services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Backend.BackendINF281.Inventario.Controller.ProductoResponse;
import Backend.BackendINF281.Inventario.Models.Producto;
import Backend.BackendINF281.Inventario.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productorRepository;

    public List<ProductoResponse> getAllProductos(){
        List<ProductoResponse> listAll=new ArrayList<>();

        List<Producto> listA=productorRepository.findAll();
        for(int i=0;i<listA.size();i++){
            if(listA.get(i).getCantidad()>0){
                ProductoResponse a=ProductoResponse.builder()
                            .idProducto(listA.get(i).getIdproducto())
                            .tipo(listA.get(i).getTipo())
                            .cantidad(listA.get(i).getCantidad())
                            .estado(listA.get(i).getEstado())
                            .build();
                listAll.add(a);
            }
            
        }

        return listAll;
    }


}
