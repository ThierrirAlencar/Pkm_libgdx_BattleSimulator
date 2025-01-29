package io.github.PokemonGame.interfaces;

import com.badlogic.gdx.Gdx;
import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Types.EFFECTS;
import io.github.PokemonGame.Types.TYPES;
import io.github.PokemonGame.interfaces.SoundEffect;
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
        SoundEffect snd = new SoundEffect("Sounds/SFX/"+type+".mp3");
        snd.playSound(1.2f);

        switch (this.type) {
            case NORMAL:
                damage = super.normal(TargetPokemon.getType(), this.baseDamage);
                break;
            case FIRE:
                damage = super.fire(TargetPokemon.getType(), this.baseDamage);
                break;
            case WATER:
                damage = super.water(TargetPokemon.getType(), this.baseDamage);
                break;
            case GRASS:
                damage = super.grass(TargetPokemon.getType(), this.baseDamage);
                break;
            case ELETRIC:
                damage = super.eletric(TargetPokemon.getType(), this.baseDamage);
                break;
            case ICE:
                damage = super.ice(TargetPokemon.getType(), this.baseDamage);
                break;
            case FIGHTING:
                damage = super.fighting(TargetPokemon.getType(), this.baseDamage);
                break;
            case POISON:
                damage = super.poison(TargetPokemon.getType(), this.baseDamage);
                break;
            case GROUND:
                damage = super.ground(TargetPokemon.getType(), this.baseDamage);
                break;
            case FLYING:
                damage = super.flying(TargetPokemon.getType(), this.baseDamage);
                break;
            case BUG:
                damage = super.bug(TargetPokemon.getType(), this.baseDamage);
                break;
            case ROCK:
                damage = super.rock(TargetPokemon.getType(), this.baseDamage);
                break;
            case PSYCHIC:
                damage = super.psychic(TargetPokemon.getType(), this.baseDamage);
                break;
            case GHOST:
                damage = super.ghost(TargetPokemon.getType(), this.baseDamage);
                break;
            case DRAGON:
                damage = super.dragon(TargetPokemon.getType(), this.baseDamage);
                break;
            case STEEL:
                damage = super.steel(TargetPokemon.getType(), this.baseDamage);
                break;
            case FAIRY:
                damage = super.fairy(TargetPokemon.getType(), this.baseDamage);
                break;
            default:
                damage = baseDamage; // Caso o tipo não seja identificado
                break;
        }
        if(damage == this.baseDamage/2){
            Gdx.app.log("Atack","was not effective");
        }else if(damage == this.baseDamage*2){
            Gdx.app.log("Atack","was super effective");
        }else if(damage == 0){
            Gdx.app.log("Atack","the oponent is imune to this attack");
        }
        TargetPokemon.Damage(damage);
        Gdx.app.log("Atack","The pokemon attacked "+TargetPokemon.getName()+" and has caused "+damage+" of damage" );
    }
}
