public class City implements Comparable<City> {
    String name;
    public City(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(City o) {
        return this.name.compareTo(o.name);
    }

//    @Override
//    public String toString() {
//        return "City{" +
//                "name='" + name + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return name;
    }
}
