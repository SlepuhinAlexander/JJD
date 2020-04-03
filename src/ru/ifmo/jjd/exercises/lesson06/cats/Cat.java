package ru.ifmo.jjd.exercises.lesson06.cats;

public class Cat {
    private String name = "Кот";
    private int age = 1;
    private int weight = 4;
    private String colour = "Чёрный";
    private String ownerAddress = "";
    private int healthScore = 100;
    private int hitPower = 1;

    public Cat(String name) {
        setName(name);
    }

    public Cat(String name, int age) {
        setName(name);
        setAge(age);
    }

    public Cat(String name, int age, int weight) {
        setName(name);
        setAge(age);
        setWeight(weight);
    }

    public Cat(String name, String colour) {
        setName(name);
        setColour(colour);
    }

    public Cat(String name, int age, String ownerAddress) {
        setName(name);
        setAge(age);
        setOwnerAddress(ownerAddress);
    }

    public Cat(String name, String colour, int healthScore, int hitPower) {
        setName(name);
        setColour(colour);
        setHealthScore(healthScore);
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight > 0) {
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

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = Math.max(healthScore, 0);
    }

    public int getHitPower() {
        return hitPower;
    }

    public void setHitPower(int hitPower) {
        if (hitPower > 0) {
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
                ", здововье = " + healthScore +
                ", сила удара = " + hitPower +
                '}';
    }

    public void fight(Cat anotherCat) {
        System.out.print(name + " наносит удар " + hitPower + " по " + anotherCat.name + " : ");
        anotherCat.setHealthScore(anotherCat.healthScore - hitPower);
        System.out.println("осталось здоровья " + anotherCat.healthScore);
        if (anotherCat.healthScore == 0) {
            System.out.println(anotherCat.name + " не может больше драться.");
            return;
        }

        System.out.print(anotherCat.name + " наносит ответный удар " + anotherCat.hitPower + " по " + name + " : ");
        setHealthScore(healthScore - anotherCat.hitPower);
        System.out.println("осталось здоровья " + healthScore);
        if (healthScore == 0) {
            System.out.println(name + " не может больше драться.");
        }
    }
}
