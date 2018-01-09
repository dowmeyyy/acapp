package com.androidtutorialshub.loginregister.sql;

/**
 * Created by cla on 09/01/2018.
 */


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.androidtutorialshub.loginregister.model.Prof;
import com.androidtutorialshub.loginregister.model.Stud;

import static com.androidtutorialshub.loginregister.sql.DatabaseHelper.COLUMN_STUD_SEMAIL;
import static com.androidtutorialshub.loginregister.sql.DatabaseHelper.COLUMN_STUD_SID;
import static com.androidtutorialshub.loginregister.sql.DatabaseHelper.COLUMN_STUD_SNAME;
import static com.androidtutorialshub.loginregister.sql.DatabaseHelper.TABLE_STUD;


public class StudDAO {


        public static final String TAG = "StudDAO";

        private Context context;

        // Database fields
        private SQLiteDatabase Database;
        private DatabaseHelper DatabaseHelper;
        private String[] allColumns = {COLUMN_STUD_SID,
                COLUMN_STUD_SNAME,
                DatabaseHelper.COLUMN_STUD_SUNAME,
                COLUMN_STUD_SEMAIL,
                DatabaseHelper.COLUMN_STUD_SPASSWORD};

        public StudDAO(Context context) {
            DatabaseHelper = new DatabaseHelper(context);
            this.context = context;
            // open the database
            try {
                open();
            } catch (SQLException e) {
                Log.e(TAG, "SQLException on opening database " + e.getMessage());
                e.printStackTrace();
            }
        }

        public void open() throws SQLException {
            Database = DatabaseHelper.getWritableDatabase();
        }

        public void close() {
            DatabaseHelper.close();
        }

         public void createStud(Stud stud) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_STUD_SNAME, stud.getSname());
            values.put(DatabaseHelper.COLUMN_STUD_SUNAME, stud.getSuname());
            values.put(DatabaseHelper.COLUMN_STUD_SEMAIL, stud.getSemail());
            values.put(DatabaseHelper.COLUMN_STUD_SPASSWORD, stud.getSpassword());
            long insertId = Database
                    .insert(TABLE_STUD, null, values);
            Cursor cursor = Database.query(TABLE_STUD, allColumns,
                    COLUMN_STUD_SID + " = " + insertId, null, null,
                    null, null);
            cursor.moveToFirst();
            return;
        }

/*        public void deleteEmployee(Employee employee) {
            long id = employee.getId();
            System.out.println("the deleted employee has the id: " + id);
            mDatabase.delete(DBHelper.TABLE_EMPLOYEES, DBHelper.COLUMN_EMPLOYE_ID
                    + " = " + id, null);
        }
*/
  /*      public List<Employee> getAllEmployees() {
            List<Employee> listEmployees = new ArrayList<Employee>();

            Cursor cursor = mDatabase.query(DBHelper.TABLE_EMPLOYEES, mAllColumns,
                    null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Employee employee = cursorToEmploye(cursor);
                listEmployees.add(employee);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return listEmployees;
        }

        public List<Employee> getEmployeesOfCompany(long companyId) {
            List<Employee> listEmployees = new ArrayList<Employee>();

            Cursor cursor = mDatabase.query(DBHelper.TABLE_EMPLOYEES, mAllColumns,
                    DBHelper.COLUMN_COMPANY_ID + " = ?",
                    new String[] { String.valueOf(companyId) }, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Employee employee = cursorToEmploye(cursor);
                listEmployees.add(employee);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return listEmployees;
        }
*/

        private Stud cursorToStud(Cursor cursor) {
            Stud stud = new Stud();
            stud.setSid(cursor.getLong(0));
            stud.setSname(cursor.getString(1));
            stud.setSuname(cursor.getString(2));
            stud.setSemail(cursor.getString(4));
            stud.setSpassword(cursor.getString(5));

            // get The company by id
/*            long companyId = cursor.getLong(7);
            P dao = new CompanyDAO(mContext);
            Company company = dao.getCompanyById(companyId);
            if (company != null)
                employee.setCompany(company);
*/
            return stud;
        }

        /**
         * This method to check user exist or not
         *
         * @param semail
         * @return true/false
         */

        //USED FOR STUDREFISTER (FINAL
        public boolean checkStudUser(String semail) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_STUD_SID
        };
        SQLiteDatabase db = this.Database;

        // selection criteria
        String selection = COLUMN_STUD_SEMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {semail};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_STUD, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }    }

