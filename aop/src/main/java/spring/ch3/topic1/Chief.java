package spring.ch3.topic1;

public class Chief {
	private static int index = 0;

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        Chief.index = index;
    }

    private Cake cake = null;
    private Oven oven = null;

    private final int id = index++;
    private String name = "";

    public Cake getCake() {
        return cake;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Oven getOven() {
        return oven;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOven(Oven oven) {
        this.oven = oven;
    }

    public void makeOneCake() {
        System.out.println(getName() + " make " + getCake().getName());
    }

}
