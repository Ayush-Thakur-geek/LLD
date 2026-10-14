package creationalDesignPattern;

public class PrototypeDesignPattern {
    public static void main(String[] args) throws CloneNotSupportedException {
        CharacterFactory characterFactory = new CharacterFactory();
        Character c1 = characterFactory.createCharacterWithNewName("xya");
    }
}

class Character implements Cloneable {
    public String name;
    public int health;
    public int attackPower;
    public int level;
    public Character(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }
    public void showCharacterInfo() {
        System.out.println("Character [Name=" + name + ", Health=" + health
                + ", AttackPower=" + attackPower + ", Level=" + level + "]");
    }

    @Override
    protected Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }
}

class CharacterFactory {

    private Character prototypeCharacter;

    public CharacterFactory() {
        prototypeCharacter =
                new Character("DefaultName", 100, 50, 1); // Default prototype character
    }

    public Character createCharacterWithNewName(String name)
            throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter = new Character(name, clonedCharacter.health,
                clonedCharacter.attackPower, clonedCharacter.level);
        return clonedCharacter;
    }

    public Character createCharacterWithNewLevel(int level)
            throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter = new Character(clonedCharacter.name,
                clonedCharacter.health, clonedCharacter.attackPower, level);
        return clonedCharacter;
    }

    public Character createCharacterWithNewAttackPower(int attackPower)
            throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter = new Character(clonedCharacter.name,
                clonedCharacter.health, attackPower, clonedCharacter.level);
        return clonedCharacter;
    }
}
