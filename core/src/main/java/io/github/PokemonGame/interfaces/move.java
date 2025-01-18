package io.github.PokemonGame.interfaces;

import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Types.EFFECTS;
import io.github.PokemonGame.Types.TYPES;

public class move extends TypeMove{
    public TYPES type = null;
    public int baseDamage = 0; //dano base de um ataque
    public EFFECTS effect = null;
    public String name;
    public move(int dmg, TYPES type, String name){
        this.baseDamage = dmg;
        this.type = type;
        this.name = name;
    }
    public void atack(Pokemon TargetPokemon){
        float damage;
        switch (this.type){
            case ELETRIC:
                damage = super.eletric(TargetPokemon.getType(),this.baseDamage);
                TargetPokemon.Damage(damage);
                break;
            default:
                 damage = baseDamage;
                 TargetPokemon.Damage(damage);
                 break;
        }
    }
}
