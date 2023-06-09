package view;

import controller.QuanLy;
import model.SinhVien;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    static QuanLy list = new QuanLy();
    static final String REG = "\\d+";
    static final String REGDOUBLE = "^\\d*(\\.\\d+)?$";
    static final String nameRegex = "^[^\\d]+$";
    static final String ageRegex = "^(1[5-9]|[2-9]\\d+)$";
    static final String genderRegex = "^(nam|nữ)$";
    static final String addressRegex = "^(?!.*(.).*\\1)[^\\s]+$";
    static final String scoreRegex = "^(10|\\d(\\.\\d)?)$";


    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    ---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ----
                    Chọn chức năng theo số (để tiếp tục)
                    1. Xem danh sách sinh viên
                    2. Thêm mới
                    3. Cập nhập
                    4. Xóa
                    5. Sắp xếp
                    6. Đọc từ File
                    7. Ghi vào File
                    0. Thoát
                    """);
            System.out.print("Chọn chức năng: ");
            String choice = new Scanner(System.in).nextLine();
            switch (choice) {
                case "1":
                    seeList();
                    String br = new Scanner(System.in).nextLine();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    upDate();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    arrange();
                    break;
                case "6":
                    readFile();
                    break;
                case "7":
                    writeFile();
                    break;
                case "0":
                    return;
            }
        }
    }

    public static void seeList() {
        list.listSV();
    }

    public static void add() {
//        String id;
//        do {
//            System.out.print("nhập mã sinh viên: ");
//            id = new Scanner(System.in).nextLine();
//        } while (id.isEmpty());

        String name= "";
        boolean isName = false;
        do {
            System.out.print("nhập họ tên: ");
            name = new Scanner(System.in).nextLine();
            if(name.matches(nameRegex)){
                isName = true;
            }else {
                System.out.println("tên không được có số");
            }
        } while (name.isEmpty());

        String age = "";
        boolean isAge = false;
        while (!isAge) {
            System.out.print("Nhập tuổi: ");
            age = new Scanner(System.in).nextLine();
            if (age.matches(REG) && age.matches(ageRegex)) {
                isAge = true;
            } else {
                System.out.println("tuổi nhập số");
            }
        }

        String sex = "";
        boolean isSex = false;
        while (!isSex){
            System.out.print("Nhập giới tính: ");
            sex = new Scanner(System.in).nextLine();
            if (sex.matches(genderRegex)) {
                isSex = true;
            } else {
                System.out.println("giới tính chỉ được nam/nữ");
            }
        }


        String addRess = "";
        boolean isAddRess = false;
        while (!isAddRess){
            System.out.print("Nhập địa chỉ: ");
            addRess = new Scanner(System.in).nextLine();
            if(addRess.matches(addressRegex)){
                isAddRess = true;
            }else {
                System.out.println("địa chỉ không trùng");
            }
        }


        String point = "";
        boolean isPoint = false;
        while (!isPoint) {
            System.out.print("Nhập diểm trung bình: ");
            point = new Scanner(System.in).nextLine();
            if (point.matches(scoreRegex) && point.matches(REGDOUBLE)) {
                isPoint = true;
            } else {
                System.out.println("Nhập số và (>=0 - <=10)");
            }
        }
        SinhVien sinhVien = new SinhVien(name, Integer.parseInt(age), sex, addRess, Double.parseDouble(point));
        list.add(sinhVien);
    }

    public static void upDate() {
        System.out.println("Nhập mã sinh viên");
        String id = new Scanner(System.in).nextLine();
        list.searchAndUpdate(id);
    }

    public static void delete() {
        System.out.println("nhập mã sinh viên");
        String id = new Scanner(System.in).nextLine();
        list.delete(id);
    }

    public static void arrange() {
        while (true) {
            System.out.println("""
                    ---- Sắp xếp sinh viên theo điểm trung binh ----
                    1, Sắp xếp điểm trung bình tăng dần
                    2, Sắp xếp điểm trung bình giảm dần
                    3, thoát
                    """);
            System.out.print("Chọn chức năng: ");
            String choi = new Scanner(System.in).nextLine();
            switch (choi) {
                case "1":
                    list.sortByAscending();
                    seeList();
                    break;
                case "2":
                    list.sortByDescending();
                    seeList();
                    break;
                case "3":
                    return;
            }
        }
    }

    public static void writeFile() {
        String filePath = "D:\\CodeGym\\TMD2\\untitled\\src\\data\\data.csv";
        list.writeCSV(filePath, list.getlistSinhVien());
    }

    public static void readFile() {
        String filePath = "D:\\CodeGym\\TMD2\\untitled\\src\\data\\data.csv";
        list.readCSV(filePath);
    }
}
