package com.example.inicial1.services;

import com.example.inicial1.entities.Mutant;
import com.example.inicial1.repositories.MutantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MutantServices {
    @Autowired
    protected MutantRepository mutantRepository;

    @Transactional
    public Mutant save(Mutant entity) throws Exception {
        try {
            entity.setEsMutant(this.isMutant(entity.getDna()));
            entity = mutantRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Esta función lo que hace es buscar una coincidencia, en caso de que se llame por segunda vez, me aseguro de no encontrar la misma
    public String isMutantDetail(String[] dna, String respuesta) {

        String posibleRespuesta = "";

        String letra1;
        String letra2;
        String letra3;
        String letra4;

        int limite = dna.length - 3;
        //El limite lo uso para que cuando ya no sea posible encontrar 4 iguales deje de buscar

        //Esta sección del código se encarga de resvisar si hay 4 seguidos
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {

                //Verificación en vertical
                if (i < limite) {
                    letra1 = String.valueOf(dna[i].charAt(j));
                    letra2 = String.valueOf(dna[i + 1].charAt(j));
                    letra3 = String.valueOf(dna[i + 2].charAt(j));
                    letra4 = String.valueOf(dna[i + 3].charAt(j));
                    if (letra1.equals(letra2) && letra2.equals(letra3) && letra3.equals(letra4)) {
                        posibleRespuesta = "(" + i + "," + j + ")" + "(" + (i + 1) + "," + j + ")" + "(" + (i + 2) + "," + j + ")" + "(" + (i + 3) + "," + j + ")";
                        if (!posibleRespuesta.equals(respuesta)) { //Verificamos que la nueva respueta sea distinta de la que ya existía
                            return posibleRespuesta;
                        }
                    }
                }

                //Verificación en horizontal
                if (j < limite) {
                    letra1 = String.valueOf(dna[i].charAt(j));
                    letra2 = String.valueOf(dna[i].charAt(j + 1));
                    letra3 = String.valueOf(dna[i].charAt(j + 2));
                    letra4 = String.valueOf(dna[i].charAt(j + 3));
                    if (letra1.equals(letra2) && letra2.equals(letra3) && letra3.equals(letra4)) {
                        posibleRespuesta = "(" + i + "," + j + ")" + "(" + i + "," + (j + 1) + ")" + "(" + i + "," + (j + 2) + ")" + "(" + i + "," + (j + 3) + ")";
                        if (!posibleRespuesta.equals(respuesta)) { //Verificamos que la nueva respueta sea distinta de la que ya existía
                            return posibleRespuesta;
                        }
                    }
                }

                //Verificación en diagonal hacia derecha
                if (i < limite && j < limite) {
                    letra1 = String.valueOf(dna[i].charAt(j));
                    letra2 = String.valueOf(dna[i + 1].charAt(j + 1));
                    letra3 = String.valueOf(dna[i + 2].charAt(j + 2));
                    letra4 = String.valueOf(dna[i + 3].charAt(j + 3));
                    if (letra1.equals(letra2) && letra2.equals(letra3) && letra3.equals(letra4)) {
                        posibleRespuesta = "(" + i + "," + j + ")" + "(" + (i + 1) + "," + (j + 1) + ")" + "(" + (i + 2) + "," + (j + 2) + ")" + "(" + (i + 3) + "," + (j + 3) + ")";
                        if (!posibleRespuesta.equals(respuesta)) { //Verificamos que la nueva respueta sea distinta de la que ya existía
                            return posibleRespuesta;
                        }
                    }
                }

                //Verificación en diagonal hacia izquierda
                if (i < limite && j >= 3) {
                    letra1 = String.valueOf(dna[i].charAt(j));
                    letra2 = String.valueOf(dna[i + 1].charAt(j - 1));
                    letra3 = String.valueOf(dna[i + 2].charAt(j - 2));
                    letra4 = String.valueOf(dna[i + 3].charAt(j - 3));
                    if (letra1.equals(letra2) && letra2.equals(letra3) && letra3.equals(letra4)) {
                        posibleRespuesta = "(" + i + "," + j + ")" + "(" + (i + 1) + "," + (j - 1) + ")" + "(" + (i + 2) + "," + (j - 2) + ")" + "(" + (i + 3) + "," + (j - 3) + ")";
                        if (!posibleRespuesta.equals(respuesta)) { //Verificamos que la nueva respueta sea distinta de la que ya existía
                            return posibleRespuesta;
                        }
                    }
                }
            }
        }
        return "";
    }

    @Transactional
    public Boolean isMutant(String[] dna) {

        //Definimos la longitud que tienen que tener todas las palabras
        int longitudPrimera = dna.length;

        if (longitudPrimera == 0) {
            return false; //En caso de que el arreglo esté vacío devolvemos un false instantáneo
        }

        //Este es un string que contiene las letras que pueden estar en cada palabra del DNA
        String elementosValidos = "ATCG";

        for (String palabra : dna) {

            //Con este condicional por palabra nos fijamos que tenga la longitud N del vector DNA
            if (palabra.length() != longitudPrimera) {
                throw new IllegalArgumentException(); //Si alguna de las palabras es de longitud distinta no guardamos ni revisamos dna
            }

            //Este bucle verifica que las letras sean algunas de las indicadas
            for (char letra : palabra.toCharArray()) {
                // Si alguna letra no está en "ATCG", el index of devuelve -1
                if (elementosValidos.indexOf(letra) == -1) {
                    throw new IllegalArgumentException(); //En caso de ser una letra no válida, retornamos falso y no guardamos ni analizamos el dna
                }
            }

        }

        String respuesta1 = isMutantDetail(dna, "");
        if (respuesta1.equals("")) {
            return false;
        }
        String respuesta2 = isMutantDetail(dna, respuesta1);
        if (respuesta2.equals("")) {
            return false;
        }
        return true;
    }

    @Transactional
    public Map<String, Object> getStats() { //En este metodo uso Map porque es similar a un JSON

        float countMutantDna = mutantRepository.countByEsMutantTrue();
        float countHumanDna = mutantRepository.countByEsMutantFalse();


        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", countMutantDna);
        stats.put("count_human_dna", countHumanDna);
        stats.put("ratio", countHumanDna == 0 ? 0 : countMutantDna / countHumanDna);

        return stats;
    }
}
