
package com.mycompany.reto9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 *
 * @author yojua
 */
public class Reto9{

    public static void main(String[] args) {
        
        
        String rutaDestino = "C:\\Users\\yojua\\OneDrive\\Desktop\\reto9.txt";
        String rutaEx = "C:\\Users\\yojua\\OneDrive\\Desktop\\ETH-USD.csv";
        operaciones(rutaDestino,rutaEx);
        
        List<Double> ejem = new ArrayList<>();
        ejem.add(8.3);
        ejem.add(3.2);
        ejem.add(7.9);
        List<Double>raizEjem = raiz(ejem);
        raizEjem.stream().forEach(x -> System.out.println(x));
        
        Set<String> colores = new TreeSet();
        colores.add("azul");
        colores.add("amarillo");
        colores.add("rojo");
        colores.add("rosado");
        colores.add("violeta");
        List<String> color = largura(colores);
        color.stream().forEach(x -> System.out.println(x));
        
        
    }
    public static void operaciones(String rutaDestino,String rutaEx){
        
        List<Double> volumen = new ArrayList<>();
        List<Double> cierre = new ArrayList<>();
        List<String> fechas = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path rutaI = Paths.get(rutaEx);
        Path rutaF = Paths.get(rutaDestino);
        List<String> lineasExel;
        try (BufferedWriter writer = Files.newBufferedWriter(rutaF, charset)){
            lineasExel = Files.readAllLines(rutaI);
            lineasExel.remove(0);
            writer.write("fecha        Open"+ "\n");
            for(String lineas : lineasExel){
                String info[] = lineas.split(",");
                String escribir = info[0] + "   " + clasificacion(Double.parseDouble(info[1])) + "\n";
                writer.write(escribir);
                fechas.add(info[0]);
                cierre.add(Double.valueOf(info[4]));
                volumen.add(Double.valueOf(info[6]));
            }
            double promedio = promedio(cierre);
            double desviEstandar = desviEstandar(cierre);
            int posMax = posicion(Collections.max(volumen),volumen);
            int posMin = posicion(Collections.min(volumen),volumen);
            System.out.println(promedio);
            System.out.println(desviEstandar);
            System.out.println(posMax);
            System.out.println(Collections.max(volumen));
            System.out.println(fechas.get(posMax));
            System.out.println(Collections.min(volumen));
            System.out.println(fechas.get(posMin));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            
        
        }
 
    public static String clasificacion(double valor){
           if (valor<=1200){
                   return "MUY BAJO";}
            if (valor>=1200 && valor<2100){
                return "BAJO";
            }
            if (valor>=2100 && valor<3100){
                return "MEDIO";
            }
           if (valor>=3100 && valor<4600){
               return "ALTO";
           }
           else return "MUY ALTO";  
        }
    
    public static int posicion(double valor, List<Double> elementos){
        int indx = -1;
        for (double elem : elementos){
            indx++;
            if (elem==valor)
                return indx;
        }
        return -1;
    }
    
    public static double promedio(List<Double> elementos){
        double suma = 0;
        for (double elem : elementos){
            suma += elem;
        }
        return suma/elementos.size();
    }
    
    public static double desviEstandar(List<Double> elementos){
        double promedio = promedio(elementos);
        double sumatoria = 0;
        for (double elem : elementos){
            sumatoria += Math.pow(elem-promedio, 2);
        }
        return Math.sqrt(sumatoria/(elementos.size()-1));
    }
    
    public static List<Double> raiz(List<Double> ejem){
        return ejem.stream().map(x -> Math.sqrt(x)).collect(Collectors.toList());
    }
    
    public static List<String> largura(Set<String> colores){
       return colores.stream().filter(x -> x.length()>=5).collect(Collectors.toList());
    }
}
