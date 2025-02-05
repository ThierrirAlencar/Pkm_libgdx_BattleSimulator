package io.github.PokemonGame.Classes;
import io.github.PokemonGame.Types.TYPES;

// Carrega todos os métodos relativos às lógicas de tipagem de um ataque
public class TypeMove {

    public int normal(TYPES defenderType, int basedamage) {
        // Em caso de ataque pouco efetivo
        if (defenderType == TYPES.ROCK || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        // Em caso de ataque inefetivo
        if (defenderType == TYPES.GHOST) {
            return 0;
        }
        return basedamage;
    }

    public int fire(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIRE || defenderType == TYPES.WATER || defenderType == TYPES.ROCK || defenderType == TYPES.DRAGON) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.GRASS || defenderType == TYPES.ICE || defenderType == TYPES.BUG || defenderType == TYPES.STEEL) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int water(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.WATER || defenderType == TYPES.GRASS || defenderType == TYPES.DRAGON) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.FIRE || defenderType == TYPES.GROUND || defenderType == TYPES.ROCK) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int grass(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIRE || defenderType == TYPES.GRASS || defenderType == TYPES.POISON || defenderType == TYPES.FLYING || defenderType == TYPES.BUG || defenderType == TYPES.DRAGON || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.WATER || defenderType == TYPES.GROUND || defenderType == TYPES.ROCK) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int eletric(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.ELECTRIC || defenderType == TYPES.GRASS || defenderType == TYPES.DRAGON) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.WATER || defenderType == TYPES.FLYING) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.GROUND) {
            return 0;
        }
        return basedamage;
    }

    public int ice(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIRE || defenderType == TYPES.WATER || defenderType == TYPES.ICE || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.GRASS || defenderType == TYPES.GROUND || defenderType == TYPES.FLYING || defenderType == TYPES.DRAGON) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int fighting(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FLYING || defenderType == TYPES.POISON || defenderType == TYPES.BUG || defenderType == TYPES.PSYCHIC || defenderType == TYPES.FAIRY) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.NORMAL || defenderType == TYPES.ROCK || defenderType == TYPES.STEEL || defenderType == TYPES.ICE || defenderType == TYPES.DARK) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.GHOST) {
            return 0;
        }
        return basedamage;
    }

    public int poison(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.POISON || defenderType == TYPES.GROUND || defenderType == TYPES.ROCK || defenderType == TYPES.GHOST) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.GRASS || defenderType == TYPES.FAIRY) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.STEEL) {
            return 0;
        }
        return basedamage;
    }

    public int ground(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.GRASS || defenderType == TYPES.BUG) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.FIRE || defenderType == TYPES.ELECTRIC || defenderType == TYPES.POISON || defenderType == TYPES.ROCK || defenderType == TYPES.STEEL) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.FLYING) {
            return 0;
        }
        return basedamage;
    }

    public int flying(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.ELECTRIC || defenderType == TYPES.ROCK || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.GRASS || defenderType == TYPES.FIGHTING || defenderType == TYPES.BUG) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int bug(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIRE || defenderType == TYPES.FIGHTING || defenderType == TYPES.POISON || defenderType == TYPES.FLYING || defenderType == TYPES.GHOST || defenderType == TYPES.STEEL || defenderType == TYPES.FAIRY) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.GRASS || defenderType == TYPES.PSYCHIC || defenderType == TYPES.DARK) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int rock(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIGHTING || defenderType == TYPES.GROUND || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.FIRE || defenderType == TYPES.ICE || defenderType == TYPES.FLYING || defenderType == TYPES.BUG) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int psychic(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.PSYCHIC || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.FIGHTING || defenderType == TYPES.POISON) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.DARK) {
            return 0;
        }
        return basedamage;
    }

    public int ghost(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.DARK) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.PSYCHIC || defenderType == TYPES.GHOST) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.NORMAL) {
            return 0;
        }
        return basedamage;
    }

    public int dragon(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.DRAGON) {
            basedamage = basedamage * 2;
        }
        if (defenderType == TYPES.FAIRY) {
            return 0;
        }
        return basedamage;
    }

    public int steel(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIRE || defenderType == TYPES.WATER || defenderType == TYPES.ELECTRIC || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.ICE || defenderType == TYPES.ROCK || defenderType == TYPES.FAIRY) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }

    public int fairy(TYPES defenderType, int basedamage) {
        if (defenderType == TYPES.FIRE || defenderType == TYPES.POISON || defenderType == TYPES.STEEL) {
            basedamage = basedamage / 2;
        }
        if (defenderType == TYPES.FIGHTING || defenderType == TYPES.DRAGON || defenderType == TYPES.DARK) {
            basedamage = basedamage * 2;
        }
        return basedamage;
    }
}
