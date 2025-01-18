package io.github.PokemonGame.interfaces;
import io.github.PokemonGame.Types.TYPES;

//Carrega todos os métodos relativos as lógicas de tipagem de um ataque
public class TypeMove {

    //Um ataque do tipo eletrico deve checar o tipo do pokemon do adversário + o dano base para retornar um ataque poderoso
    public int eletric(TYPES defenderType, int basedamage){
        //Em caso de ataque inefetivo
        if(defenderType==TYPES.ELETRIC && defenderType==TYPES.GROUND){
            basedamage = basedamage/2;
        }
        //Em caso de ataque efetivo
        if(defenderType==TYPES.WATER && defenderType==TYPES.FLYING){
            basedamage = basedamage*2;
        }
        return basedamage;
    }
    public int fire(TYPES defenderType, int basedamage){
        //Em caso de ataque inefetivo
        if(defenderType==TYPES.WATER && defenderType==TYPES.GROUND){
            basedamage = basedamage/2;
        }
        //Em caso de ataque efetivo
        if(defenderType==TYPES.PLANT && defenderType==TYPES.STEEL){
            basedamage = basedamage*2;
        }
        return basedamage;
    }
}
