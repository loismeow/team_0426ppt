package com.team.sec02;
import java.util.*;

public class SchoolManagementEx {
    private static DataRepository repo = new DataRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("학사 행정관리 프로그램");
            System.out.println("=============================");
            System.out.println("1. 학생관련 업무");
            System.out.println("2. 교수 관련 업무");
            System.out.println("3. 학과 관련 업무");
            System.out.println("4. 성적관련 업무");
            System.out.println("5. 종료");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    professorMenu();
                    break;
                case 3:
                    departmentMenu();
                    break;
                case 4:
                    takesMenu();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void studentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("1. 학생 등록");
            System.out.println("2. 학생 조회");
            System.out.println("3. 학생 정보 수정");
            System.out.println("4. 학생 정보 삭제");
            System.out.println("5. 학생 - 학과 찾기");
            System.out.println("6. 메인 메뉴로 가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    registerStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    searchDepartmentByStudentName(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.println("학생 ID: ");
        String id = scanner.nextLine();
        System.out.println("주민번호: ");
        String jumin = scanner.nextLine();
        System.out.println("이름: ");
        String name = scanner.nextLine();
        System.out.println("학년: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("주소: ");
        String address = scanner.nextLine();
        System.out.println("학과 코드: ");
        int department = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(id, jumin, name, year, address, department);
        repo.getStudents().add(student);
        System.out.println("학생이 성공적으로 등록되었습니다.");
    }

    private static void displayStudents() {
        System.out.println("등록된 학생 목록:");
        for (Student s : repo.getStudents()) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getYear() + " " + s.getAddress() + " " + s.getDepartment());
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.println("수정할 학생의 ID를 입력하세요: ");
        String id = scanner.nextLine();
        for (Student s : repo.getStudents()) {
            if (s.getId().equals(id)) {
                System.out.println("새 주민번호: ");
                String jumin = scanner.nextLine();
                System.out.println("새 이름: ");
                String name = scanner.nextLine();
                System.out.println("새 학년: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                System.out.println("새 주소: ");
                String address = scanner.nextLine();
                System.out.println("새 학과 코드: ");
                int department = scanner.nextInt();
                scanner.nextLine();

                s.setJumin(jumin);
                s.setName(name);
                s.setYear(year);
                s.setAddress(address);
                s.setDepartment(department);
                System.out.println("학생 정보가 업데이트 되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 학생을 찾을 수 없습니다.");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("삭제할 학생의 ID를 입력하세요: ");
        String id = scanner.nextLine();
        for (int i = 0; i < repo.getStudents().size(); i++) {
            if (repo.getStudents().get(i).getId().equals(id)) {
                repo.getStudents().remove(i);
                System.out.println("학생 정보가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 학생을 찾을 수 없습니다.");
    }


    private static void professorMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("1. 교수 등록");
            System.out.println("2. 교수 조회");
            System.out.println("3. 교수 - 학과명 찾기");
            System.out.println("4. 교수 정보 수정");
            System.out.println("5. 교수 정보 삭제");
            System.out.println("6. 메인 메뉴로 가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    registerProfessor(scanner);
                    break;
                case 2:
                    displayProfessors();
                    break;
                case 3:
                    showProfessorDepartmentName(repo);
                    break;
                case 4:
                    updateProfessor(scanner);
                    break;
                case 5:
                    deleteProfessor(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void registerProfessor(Scanner scanner) {
        System.out.println("교수 Id");
        String id = scanner.nextLine();
        System.out.println("교수 주민번호");
        String jumin = scanner.nextLine();
        System.out.println("교수 이름");
        String name = scanner.nextLine();
        System.out.println("학과 번호");
        int department = scanner.nextInt();
        System.out.println("학년 정보");
        String grade = scanner.nextLine();
        System.out.println("고용 일자");
        String hireDate = scanner.nextLine();

        Professor professor = new Professor(id, jumin, name, department, grade, hireDate);
        repo.getProfessors().add(professor);
        System.out.println("교수가 성공적으로 등록되었습니다.");
    }

    private static void displayProfessors() {
        System.out.println("등록된 교수 목록:");
        for (Professor p : repo.getProfessors()) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getDepartment() + " " + p.getGrade() + " " + p.getHireDate());
        }
    }

    private static void updateProfessor(Scanner scanner) {
        System.out.println("수정할 교수의 ID를 입력하세요: ");
        String id = scanner.nextLine();
        for (Professor p : repo.getProfessors()) {
            if (p.getId().equals(id)) {
                System.out.println("새 주민번호: ");
                String jumin = scanner.nextLine();
                System.out.println("새 이름: ");
                String name = scanner.nextLine();
                System.out.println("새 학과: ");
                int department = scanner.nextInt();
                scanner.nextLine();
                System.out.println("새 학년: ");
                String grade = scanner.nextLine();
                System.out.println("새 고용일자: ");
                String hireDate = scanner.nextLine();
                scanner.nextLine();

                p.setJumin(jumin);
                p.setName(name);
                p.setDepartment(department);
                p.setGrade(grade);
                p.setHireDate(hireDate);
                System.out.println("교수 정보가 업데이트 되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 교수를 찾을 수 없습니다.");
    }

    private static void deleteProfessor(Scanner scanner) {
        System.out.println("삭제할 교수의 ID를 입력하세요: ");
        String id = scanner.nextLine();
        for (int i = 0; i < repo.getProfessors().size(); i++) {
            if (repo.getProfessors().get(i).getId().equals(id)) {
                repo.getProfessors().remove(i);
                System.out.println("교수 정보가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 교수를 찾을 수 없습니다.");
    }

    private static void searchDepartmentByStudentName(Scanner scanner) {
        System.out.print("학생의 이름을 입력하세요: ");
        String studentNameToFind = scanner.nextLine(); // 사용자로부터 이름 입력 받기

        // 입력 받은 이름과 일치하는 학생 찾기
        Student foundStudent = null; // 찾은 학생을 저장할 변수를 초기화
        for (Student student : repo.getStudents()) {
            if (student.getName().equals(studentNameToFind)) {
                foundStudent = student;
                break;
            }
        }

        // 학생을 찾은 경우
        if (foundStudent != null) {
            // 학생의 학과코드를 사용하여 학과명 찾기
            String departmentName = null; // 학과명을 저장할 변수를 초기화
            for (Department department : repo.getDepartments()) {
                if (department.getId() == foundStudent.getDepartment()) {
                    departmentName = department.getName();
                    break;
                }
            }

            // 학과명 출력
            if (departmentName != null) {
                System.out.println(foundStudent.getName() + " 학생의 학과는 " + departmentName + " 입니다.");
            } else {
                System.out.println("해당 학생의 학과를 찾을 수 없습니다.");
            }
        } else { // 학생을 찾지 못한 경우
            System.out.println("입력한 이름의 학생을 찾을 수 없습니다.");
        }
    }

    private static void departmentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("1. 학과 등록");
            System.out.println("2. 학과 조회");
            System.out.println("3. 학과 정보 수정");
            System.out.println("4. 학과 정보 삭제");
            System.out.println("5. 메인 메뉴로 가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    registerDepartment(scanner);
                    break;
                case 2:
                    displayDepartment();
                    break;
                case 3:
                    updateDepartment(scanner);
                    break;
                case 4:
                    deleteDepartment(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void registerDepartment(Scanner scanner) {
        System.out.println("학과 ID: ");
        int id = scanner.nextInt();
        System.out.println("학과명");
        String name = scanner.nextLine();
        System.out.println("학과실");
        String office = scanner.nextLine();

        Department department = new Department(id, name, office);
        repo.getDepartments().add(department);
        System.out.println("학과가 성공적으로 등록되었습니다.");
    }

    private static void displayDepartment() {
        System.out.println("등록된 학과 목록:");
        for (Department d : repo.getDepartments()) {
            System.out.println(d.getId() + " " + d.getName() + " " + d.getOffice());
        }
    }

    private static void updateDepartment(Scanner scanner) {
        System.out.println("수정할 학과의 ID를 입력하세요: ");
        int id = scanner.nextInt();
        for (Department d : repo.getDepartments()) {
            if (d.getId() == id) {
                System.out.println("새 학과 이름: ");
                String name = scanner.nextLine();
                System.out.println("새 학과실: ");
                String office = scanner.nextLine();

                d.setName(name);
                d.setOffice(office);
                System.out.println("학과 정보가 업데이트 되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 학과를 찾을 수 없습니다.");
    }

    private static void deleteDepartment(Scanner scanner) {
        System.out.println("삭제할 학과의 ID를 입력하세요: ");
        int id = scanner.nextInt();
        for (int i = 0; i < repo.getDepartments().size(); i++) {
            if (repo.getDepartments().get(i).getId() == id) {
                repo.getDepartments().remove(i);
                System.out.println("학과 정보가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 학과를 찾을 수 없습니다.");
    }

    // 교수데이터 학과명 출력 메소드
    public static void showProfessorDepartmentName(DataRepository repo) {
        System.out.println("교수 정보");
        System.out.println("교수명\t담당학과번호\t학과명");
        System.out.println("======================================");

        for (Professor professor : repo.getProfessors()) {
            String name = ""; // 학과 이름
            // for문으로 학과 리스트에서 학과Id와 일치하는 학과 이름 찾기
            for (Department department : repo.getDepartments()) { // 학과 for문
                if (department.getId() == professor.getDepartment()) {
                    name = department.getName();
                    break;
                }
            }
            System.out.println(professor.getName() + "\t" + professor.getId()
                    + "\t" + name);
        }
    }




    private static void takesMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("성적 관련 업무");
            System.out.println("1. 성적 입력");
            System.out.println("2. 성적 조회");
            System.out.println("3. 성적 수정");
            System.out.println("4. 성적 삭제");
            System.out.println("5. 메인 메뉴로 돌아가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    enterGrade(scanner);
                    break;
                case 2:
                    viewGrades();
                    break;
                case 3:
                    modifyGrade(scanner);
                    break;
                case 4:
                    deleteGrade(scanner);
                    break;
                case 5:
                    return; // 메인 메뉴로 돌아가기
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    // 성적 입력
    private static void enterGrade(Scanner scanner) {
        // 등록된 학생 목록 출력
        System.out.println("등록된 학생 목록:");
        int index = 1;
        List<Student> students = repo.getStudents();
        for (Student s : students) {
            System.out.println(index++ + ". 학생 ID: " + s.getId() + ", 이름: " + s.getName());
        }

        System.out.print("학생 번호 선택: ");
        int studentChoice = scanner.nextInt();
        if (studentChoice < 1 || studentChoice > students.size()) {
            System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            return;
        }
        Student selectedStudent = students.get(studentChoice - 1);
        scanner.nextLine(); // 버퍼 비우기

        // 등록된 과목 목록 출력
        System.out.println("등록된 과목 목록:");
        index = 1;
        List<Takes> takes = repo.getTakes();
        Set<String> subjects = new HashSet<>();
        for (Takes t : takes) {
            if (subjects.add(t.getSubject())) { // 중복 제거
                System.out.println(index++ + ". 과목 코드: " + t.getSubject());
            }
        }

        System.out.print("과목 번호 선택: ");
        int subjectChoice = scanner.nextInt();
        if (subjectChoice < 1 || subjectChoice > subjects.size()) {
            System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            return;
        }
        scanner.nextLine(); // 버퍼 비우기

        // 중복 제거된 과목 코드를 다시 리스트로 정리
        List<String> distinctSubjects = new ArrayList<>(subjects);
        String selectedSubject = distinctSubjects.get(subjectChoice - 1);

        // 성적 입력
        System.out.println("성적 입력 (예: A+, B, C): ");
        String score = scanner.nextLine();

        // 새로운 성적 등록
        Takes newGrade = new Takes(selectedStudent.getId(), selectedSubject, score);
        repo.getTakes().add(newGrade);
        System.out.println("학생 이름: " + selectedStudent.getName() + ", 과목 이름: " + selectedSubject + "에 대한 성적이 성공적으로 입력되었습니다.");
    }

    // 성적 조회
    private static void viewGrades() {
        System.out.println("등록된 성적 목록 (학생 이름, 과목 이름으로 표시):");
        int index = 1;
        for (Takes t : repo.getTakes()) {
            String studentName = getStudentNameById(t.getId());  // 학생 ID를 사용하여 이름 조회
            System.out.println(index++ + "." + "학생 이름: " + studentName + ", 과목 이름: " + t.getSubject() + ", 성적: " + t.getScore());
        }
    }

    private static String getStudentNameById(String studentId) {
        for (Student s : repo.getStudents()) {
            if (s.getId().equals(studentId)) {
                return s.getName();  // 학생 ID와 일치하는 학생의 이름을 반환
            }
        }
        return "학생 조회 불가";  // 학생 ID가 목록에 없을 경우
    }

    // 성적 수정
    private static void modifyGrade(Scanner scanner) {
        List<Takes> grades = repo.getTakes();
        if (grades.isEmpty()) {
            System.out.println("등록된 성적이 없습니다.");
            return;
        }

        // 성적 목록 출력
        System.out.println("수정할 성적을 선택하세요:");
        int index = 1;
        for (Takes t : grades) {
            String studentName = getStudentNameById(t.getId());
            System.out.println(index++ + ". 학생 이름: " + studentName + ", 과목 이름: " + t.getSubject() + ", 성적: " + t.getScore());
        }

        System.out.print("선택할 성적 번호 입력: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        // 사용자 선택 검증
        if (choice < 1 || choice > grades.size()) {
            System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            return;
        }

        // 선택된 성적 수정
        Takes selectedGrade = grades.get(choice - 1);
        System.out.println("현재 성적: " + selectedGrade.getScore());
        System.out.print("새로운 성적 입력 (예: A+, B, C): ");
        String newScore = scanner.nextLine();
        selectedGrade.setScore(newScore);
        System.out.println("학생 이름: " + getStudentNameById(selectedGrade.getId()) + ", 과목 이름: " + selectedGrade.getSubject() + "의 성적이 수정되었습니다.");
    }

    // 성적 삭제
    private static void deleteGrade(Scanner scanner) {
        // 등록된 성적 목록 출력
        System.out.println("등록된 성적 목록:");
        int index = 1;
        for (Takes t : repo.getTakes()) {
            String studentName = getStudentNameById(t.getId());
            System.out.println(index++ + ". 학생 이름: " + studentName + ", 과목 이름: " + t.getSubject() + ", 성적: " + t.getScore());
        }

        System.out.print("삭제할 성적 번호 입력: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        // 사용자 선택 검증
        if (choice < 1 || choice > repo.getTakes().size()) {
            System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            return;
        }

        // 선택된 성적 삭제
        Takes selectedGrade = repo.getTakes().get(choice - 1);
        repo.getTakes().remove(selectedGrade);
        System.out.println("학생 이름: " + getStudentNameById(selectedGrade.getId()) + ", 과목 이름: " + selectedGrade.getSubject() + "의 성적이 삭제되었습니다.");
    }

}// end of class SchoolManagementEx
