package Backend.BackendINF281.Inventario.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import Backend.BackendINF281.Inventario.Controller.AlimentoResponse;
import Backend.BackendINF281.Inventario.Controller.historialAllResponse;
import Backend.BackendINF281.Inventario.Models.Alimento;
import Backend.BackendINF281.Inventario.Models.HistorialDeshecho;
import Backend.BackendINF281.Inventario.Repository.AlimentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;


    public List<AlimentoResponse> getAllAlimentos() throws ParseException{
        List<AlimentoResponse> listAll=new ArrayList<>();

        List<Alimento> listA=alimentoRepository.findAll();
        for(int i=0;i<listA.size();i++){
            if(listA.get(i).getCantidad()>0){
                AlimentoResponse a=AlimentoResponse.builder()
                        .idAlimento(listA.get(i).getIdalimento())
                        .tipo(listA.get(i).getTipo())
                        .cantidad(listA.get(i).getCantidad())
                        .fecha_Vencimiento(convertGregorianDate(listA.get(i).getFechaVenc()))
                        .estado(listA.get(i).getEstado())
                        .build();
                listAll.add(a);
            }
            

        }

        return listAll;
    }


    private String convertGregorianDate(Calendar calendar) throws ParseException{  
        String inputDateStr = calendar.getTime().toString(); /// string de entrada => Mon Sep 02 20:30:00 BOT 2024

        // Parse the input string to a Date object
        SimpleDateFormat inputParser = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date parsedDate = inputParser.parse(inputDateStr);

        // Create a SimpleDateFormat object for the desired output format
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

        // Format the parsed date to the desired format
        String formattedDate = outputFormatter.format(parsedDate).toString();
        return formattedDate;   /// salida esta en formato => 02/09/2024 20:30:00

     }
    // public List<historialAllResponse> obtenerHisto(){

    //     List<historialAllResponse> listHisto()=historial ;

    //     for(int i = 0;i < listSalida.size();i++){

    //         historialAllResponse histo=historialAllResponse.builder()
    //                     .tipo(listSalida.get)
    //                     .build();

    //     }
    //     returun listSalida;

    // }

















    private Calendar transformarFechaHora(String fechahora){  /// formato fecha hora: dd/MM/yy/hh/mm    formato fecha hora: dd/MM/yy hh:mm:ss  
        String[] FH=fechahora.split("/");
        SimpleDateFormat formatF=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fech=FH[2]+"-" + FH[1] + "-" + FH[0];  /// dd/MM/yyyy => yyyy-MM-dd
        String hor=FH[3] + ":" +FH[4]+":00";   //// hh:mm:ss
        Calendar salida = new GregorianCalendar();
        Date salida1;
        try {
            salida1 = (Date) formatF.parse(fech+" "+hor);
            salida.setTime(salida1);
            return salida;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            salida1=new Date(0);
            salida.setTime(salida1);
            return salida;
        }

    }


}
