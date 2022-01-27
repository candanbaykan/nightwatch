package com.example.nightwatchv10;

public class Session {
    public static Integer userId;
    public static String username;
    public static String password;
    public static String token;
    public static Integer roleId;
    public static Integer managerId;
    public static Integer employeeId;

    public static String selectedDepartmentId;
    public static String selectOffDayEmployeeId;


    public static void clearSession() {
        userId = null;
        username = null;
        password = null;
        token = null;
        roleId = null;
        managerId = null;
        employeeId = null;
        selectedDepartmentId = null;
        selectOffDayEmployeeId = null;
    }

}
