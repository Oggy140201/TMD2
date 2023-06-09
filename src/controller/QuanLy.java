package controller;

import model.SinhVien;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class QuanLy {
    Scanner scanner = new Scanner(System.in);
    ArrayList<SinhVien> listSV;
    int id;

    public QuanLy() {
        this.listSV = new ArrayList<>();
    }

    public void add(SinhVien sinhVienMoi) {
        boolean alreadyExist = false;
        for (SinhVien sinhVien : listSV) {
            if (sinhVien.getId() == (sinhVienMoi.getId())) {
                alreadyExist = true;
                break;
            }
        }
        if (!alreadyExist) {
            listSV.add(sinhVienMoi);
            System.out.println("Đã thêm sinh viên mới!");
        } else {
            System.out.println("Không thể thêm sinh viên mới vì mã sinh viên đã tồn tại!");
        }
    }

    public void listSV() {
        int count = 0;
        for (SinhVien sinhVien : listSV) {
            if (count < 5) {
                System.out.println(sinhVien);
                count++;
            } else {
                break;
            }
        }
    }

    public void searchAndUpdate(int id) {
        for (SinhVien sinhVien : listSV) {
            if (sinhVien.getId() == id) {
                System.out.println("Thông tin sinh viên: " + sinhVien);
                System.out.print("Bạn muốn cập nhật thông tin sinh viên này? (Y/N): ");
                String select = new Scanner(System.in).nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    System.out.println("Nhập mã sinh viên mới");
                    int idS = Integer.parseInt(new Scanner(System.in).nextLine());
                    System.out.print("Nhập tên mới: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("Nhập tuổi mới: ");
                    int age = new Scanner(System.in).nextInt();
                    System.out.print("Nhập giới tính mới: ");
                    String sex = new Scanner(System.in).nextLine();
                    System.out.print("Nhập địa chỉ mới: ");
                    String addRess = new Scanner(System.in).nextLine();
                    System.out.print("Nhập điểm trung bình mới: ");
                    double point = new Scanner(System.in).nextDouble();
                    sinhVien.setId(idS);
                    sinhVien.setName(name);
                    sinhVien.setAge(age);
                    sinhVien.setSex(sex);
                    sinhVien.setAddress(addRess);
                    sinhVien.setPoint(point);
                    System.out.println("Cập nhật thông tin sinh viên thành công!");
                }
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên có mã " + id);
    }

    public void delete(int id) {
        for (SinhVien sinhVien : listSV) {
            if (sinhVien.getId() == id) {
                System.out.println("Thông tin sinh viên: " + sinhVien);
                System.out.print("Bạn có muốn xóa sinh viên này? (Y/N): ");
                String select = new Scanner(System.in).nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    listSV.remove(sinhVien);
                    System.out.println("Xóa sinh viên thành công!");
                }
                return; // Quay lại menu
            }
        }
        System.out.println("Không tìm thấy sinh viên có mã " + id);
    }

    //giảm dần
    public void sortByDescending() {
        Collections.sort(listSV, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                return Double.compare(sv2.getPoint(), sv1.getPoint());
            }
        });
        System.out.println("Đã sắp xếp danh sách sinh viên theo điểm trung bình giảm dần!");
    }

    //tăng dần
    public void sortByAscending() {
        Collections.sort(listSV, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                return Double.compare(sv1.getPoint(), sv2.getPoint());
            }
        });
        System.out.println("Đã sắp xếp danh sách sinh viên theo điểm trung bình tăng dần!");
    }

    public void readCSV(String filePath) {
        String delimiter = ",";
        List<SinhVien> listM = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Chú ý: Tất cả dữ liệu sinh viên hiện có trong bộ nhớ sẽ bị xóa nếu bạn tiếp tục!");
            System.out.print("Bạn có chắc chắn muốn tiếp tục (y/n)? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                System.out.println("Hủy thao tác đọc file.");
                return;
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);
                SinhVien sinhVien = new SinhVien(data[0], Integer.parseInt(data[1]),
                        data[2], data[3], Double.parseDouble(data[6]));
                listM.add(sinhVien);
            }
            System.out.println("Đã đọc danh sách sinh viên từ file CSV!");
            System.out.println("Đang cập nhật lại bộ nhớ...");
            this.listSV.clear();
            this.listSV.addAll(listM);
            // cập nhập lại bộ nhớ , ghi ra file
            String outputFile = "D:\\CodeGym\\TMD2\\untitled\\src\\data.csv";
            FileWriter writer = new FileWriter(outputFile);
            for (SinhVien s : listSV) {
                String[] data = {
                        String.valueOf(s.getId()), s.getName(),
                        Integer.toString(s.getAge()), s.getSex(),
                        s.getAddress(), Double.toString(s.getPoint())};
                writer.write(String.join(",", data));
                writer.write("\n");
            }
            writer.close();
            System.out.println("Đã cập nhật lại danh sách sinh viên và ghi ra file CSV!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSV(String filePath, List<SinhVien> listSV) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (SinhVien sinhVien : listSV) {
                String[] data = {String.valueOf(sinhVien.getId()), sinhVien.getName(), Integer.toString(sinhVien.getAge()), sinhVien.getSex(), sinhVien.getAddress(), Double.toString(sinhVien.getPoint())};
                writer.write(String.join(",", data));
                writer.write("\n");
            }
            System.out.println("Đã ghi danh sách sinh viên vào file " + filePath + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SinhVien> getlistSinhVien() {
        return listSV;
    }

}
