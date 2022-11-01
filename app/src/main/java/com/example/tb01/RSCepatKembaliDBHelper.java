package com.example.tb01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class RSCepatKembaliDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RSCepatKembali.db";

    public RSCepatKembaliDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Statement create tabel dokter
        String doctorStatement = "CREATE TABLE IF NOT EXISTS doctor (" +
                "id_doctor INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "specialization TEXT NOT NULL," +
                "phone TEXT NOT NULL);";

        // Statement create tabel pertemuan
        String appointStatement = "CREATE TABLE IF NOT EXISTS appointment(" +
                "id_appointment INTEGER PRIMARY KEY," +
                "patient_name TEXT NOT NULL," +
                "complaints TEXT NOT NULL," +
                "date TEXT NOT NULL," +
                "time TEXT NOT NULL," +
                "id_doctor INTEGER NOT NULL," +
                "FOREIGN KEY(id_doctor) REFERENCES doctor(id_doctor));";

        sqLiteDatabase.execSQL(doctorStatement);
        sqLiteDatabase.execSQL(appointStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        String statement = "DROP TABLE IF EXISTS doctor;" +
                "DROP TABLE IF EXISTS appointment;";
        this.onCreate(sqLiteDatabase);
    }

    // ============================================================================================
    public long insertDoctor(String name, String specialization, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Konten yang akan di-insert ke DB
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("specialization", specialization);
        values.put("phone", phone);

        return db.insert("doctor", null, values);
    }

    public Doctor getDoctorData(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Doctor doctor = null;

        // Ambil row dari DB
        try (Cursor res = db.rawQuery("select * from doctor where id_doctor=" + id + "", null)) {

            //Buat object Doctor yang akan dikembalikan
            if (res != null) {
                res.moveToFirst();
                String name = res.getString(1);
                String specialization = res.getString(2);
                String phone = res.getString(3);
                doctor = new Doctor(id, name, specialization, phone);
            }
        }

        return doctor;
    }

    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor res = db.rawQuery("select * from doctor", null)) {

            // Iterasi untuk mengisi ArrayList dengan data dari DB
            res.moveToFirst();
            while (!res.isAfterLast()) {
                long id = res.getLong(0);
                String name = res.getString(1);
                String specialization = res.getString(2);
                String phone = res.getString(3);
                doctors.add(new Doctor(id, name, specialization, phone));
                res.moveToNext();
            }
        }

        return doctors;
    }
    // ============================================================================================

    public long insertAppointment(String patientName, String complaints, String date, String time,
                                  long doctorId){
        SQLiteDatabase db = this.getWritableDatabase();

        // Konten yang akan di-insert ke DB
        ContentValues values = new ContentValues();
        values.put("patient_name", patientName);
        values.put("complaints", complaints);
        values.put("date", date);
        values.put("time", time);
        values.put("id_doctor", doctorId);

        return db.insert("appointment", null, values);
    }

    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor res = db.rawQuery("select * from appointment", null)) {

            // Iterasi untuk mengisi ArrayList dengan data dari DB
            res.moveToFirst();
            while (!res.isAfterLast()) {
                long idAppointment = res.getLong(0);
                String patientName = res.getString(1);
                String complaints = res.getString(2);
                String date = res.getString(3);
                String time = res.getString(4);
                long idDoctor = res.getLong(5);

                Doctor currDoctor = getDoctorData(idDoctor);
                String doctorName = currDoctor.getName();

                appointments.add(new Appointment(patientName, doctorName, date, time,
                        complaints, idAppointment, idDoctor));
                res.moveToNext();
            }
        }

        return appointments;
    }

    public Appointment getAppointmentData(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Appointment appointment = null;

        // Ambil row dari DB
        try (Cursor res = db.rawQuery("SELECT * " +
                "FROM appointment " +
                "WHERE id_appointment =" + id + "",
                null)) {

            //Buat object Doctor yang akan dikembalikan
            if (res != null) {
                res.moveToFirst();
                long idAppointment = res.getLong(0);
                String patientName = res.getString(1);
                String complaints = res.getString(2);
                String date = res.getString(3);
                String time = res.getString(4);
                long idDoctor = res.getLong(5);

                Log.d("debug", ""+idDoctor);

                Doctor currDoctor = getDoctorData(idDoctor);
                String doctorName = currDoctor.getName();

                appointment = new Appointment(patientName, doctorName, date, time, complaints,
                        idAppointment, idDoctor);
            }
        }

        return appointment;
    }
}
