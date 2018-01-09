package com.androidtutorialshub.loginregister.sql;

/**
 * Created by cla on 09/01/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.androidtutorialshub.loginregister.model.Prof;


public class ProfDAO {

    public static final String TAG = "ProfDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDatabaseHelper;
    private Context mContext;
    private String[] mAllColumns = { DatabaseHelper.COLUMN_USER_ID,
            DatabaseHelper.COLUMN_USER_NAME,
            DatabaseHelper.COLUMN_USER_UNAME,
            DatabaseHelper.COLUMN_USER_EMAIL,
            DatabaseHelper.COLUMN_USER_PASSWORD };

    public ProfDAO(Context context) {
        this.mContext = context;
        mDatabaseHelper = new DatabaseHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        mDatabaseHelper.close();
    }

    public Prof createProf(String name, String uname, String email,
                              String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_NAME, name);
        values.put(DatabaseHelper.COLUMN_USER_UNAME, uname);
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_USER_PASSWORD, password);
        long insertId = mDatabase
                .insert(DatabaseHelper.TABLE_USER, null, values);
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_USER, mAllColumns,
                DatabaseHelper.COLUMN_USER_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Prof newProf = cursorToProf(cursor);
        cursor.close();
        return newProf;
    }
/*    public void deleteProf(Prof prof) {
        long id = prof.getId();
        // delete all employees of this company
        ClassDAO classDAO = new ClassDAO(mContext);       //EmployeeDAO employeeDao = new EmployeeDAO(mContext);
        List<Class> listEmployees = classDAO.getEmployeesOfCompany(id);
        if (listEmployees != null && !listEmployees.isEmpty()) {
            for (Employee e : listEmployees) {
                employeeDao.deleteEmployee(e);
            }
        }
        System.out.println("the deleted company has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_COMPANIES, DBHelper.COLUMN_COMPANY_ID
                + " = " + id, null);
    }
*/
  /*  public List<Company> getAllCompanies() {
        List<Company> listCompanies = new ArrayList<Company>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_COMPANIES, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Company company = cursorToCompany(cursor);
                listCompanies.add(company);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompanies;
    }
*/
  public Prof getProfById(long id) {
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_USER, mAllColumns,
                DatabaseHelper.COLUMN_USER_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Prof prof = cursorToProf(cursor);
        return prof;
    }

    protected Prof cursorToProf(Cursor cursor) {
        Prof prof = new Prof();
        prof.setId(cursor.getLong(0));
        prof.setName(cursor.getString(1));
        prof.setUname(cursor.getString(2));
        prof.setEmail(cursor.getString(3));
        prof.setPassword(cursor.getString(4));
        return prof;
    }

}
