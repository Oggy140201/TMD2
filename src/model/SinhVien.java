package model;

public class SinhVien {
    private int count = 0;
    private int id;
    private String name;
    private int age;
    private String sex;
    private String address;
    private Double point;

    public SinhVien(String name, int age, String sex, String address, Double point) {
        this.id = count++;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.point = point;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSinhVien='" + id + '\'' +
                ", hoVaTen='" + name + '\'' +
                ", age=" + age +
                ", gioiTinh='" + sex + '\'' +
                ", diaChi='" + address + '\'' +
                ", diemTrungBinh=" + point +
                '}';
    }
}