package sanchez.daniel.dint04_pruebaslayouts2;

/**
 * Created by daniel.rodriguez on 21/12/2016.
 */
public class Country {
    String name;
    int flag;

    public Country(String name, int flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setName(String name) {
        this.name = name;
    }
}
