package ru.ifmo.jjd.exercises.lesson06.ex02;

public class Cat {
    private String name;
    private double weight;
    private int age;
    private String colour;
    private String ownerAddress;
    private double health;
    private double hitPower;

    public Cat(String name, String colour) {
        setName(name);
    }

    public Cat(double health, String ownerAddress, double hitPower) {
        setHealth(health);
        setOwnerAddress(ownerAddress);
        setHitPower(hitPower);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() > 3) {
            this.name = name;
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0.0) {
            this.weight = weight;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        if (colour != null && !colour.isEmpty()) {
            this.colour = colour;
        }
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        if (ownerAddress != null && !ownerAddress.isEmpty()) {
            this.ownerAddress = ownerAddress;
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        if (health > 0.0) {
            this.health = health;
        }
    }

    public double getHitPower() {
        return hitPower;
    }

    public void setHitPower(double hitPower) {
        if (hitPower > 0.0) {
            this.hitPower = hitPower;
        }
    }

    @Override
    public String toString() {
        return "Кот = {" +
                "имя = '" + name + '\'' +
                ", вес = " + weight +
                ", возраст = " + age +
                ", цвет = '" + colour + '\'' +
                ", адрес владельца = '" + ownerAddress + '\'' +
                ", здововье = " + health +
                ", сила удара = " + hitPower +
                '}';
    }

    public void fight(Cat anotherCat) {
        setHealth(getHealth() - anotherCat.getHitPower());
        anotherCat.setHealth(anotherCat.getHealth() - getHitPower());
    }
}
