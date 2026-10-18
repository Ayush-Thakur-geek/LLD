package behaviouralDesignPattern;

public class TemplateDP {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        tea.prepareRecipe();
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

abstract class Beverage {
    final void prepareRecipe() {
        boilWater();
        addCondiments();
        brew();
        pourInCup();
    }
    void boilWater() {
        System.out.println("boil water");
    }
    void pourInCup() {
        System.out.println("pour in cup");
    }

    abstract void addCondiments();
    abstract void brew();
}

class Tea extends Beverage {
    @Override
    void addCondiments() {
        System.out.println("add tea condiments");
    }
    @Override
    void brew() {
        System.out.println("brewing tea");
    }
}

class Coffee extends Beverage {
    @Override
    void addCondiments() {
        System.out.println("add coffee condiments");
    }
    @Override
    void brew() {
        System.out.println("brewing coffee");
    }
}
