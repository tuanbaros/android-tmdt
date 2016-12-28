package bookstore.android.com.bookstore.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Cart {

    private static final String KEY_ROWID = "_id";
    private static final String KEY_CARTID = "_cart_id";
    private static final String KEY_BOOKID = "_book_id";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_NAME = "name";

    private static final String TAG = "Cart";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "carts";
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_CREATE ="create table carts (_id integer primary key autoincrement, "
            + "_cart_id integer not null, _book_id integer not null, image text not null, quantity integer not null, name text not null, unique(_cart_id, _book_id));";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public Cart(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS carts");
            onCreate(db);
        }
    }
    //---opens the database---
    public Cart open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    //---insert a cart into the database---
    public long insertCart(int _cart_id, int _book_id, String image, int quantity, String name)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CARTID, _cart_id);
        initialValues.put(KEY_BOOKID, _book_id);
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_QUANTITY, quantity);
        initialValues.put(KEY_NAME, name);
        return db.insertWithOnConflict(DATABASE_TABLE, null, initialValues, SQLiteDatabase.CONFLICT_IGNORE);
    }
    //---deletes a particular cart---
    public boolean deleteCart(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    //---retrieves all the carts---
    public Cursor getAllCarts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_CARTID,
                KEY_BOOKID, KEY_IMAGE, KEY_QUANTITY, KEY_NAME}, null, null, null, null, null);
    }
    //---retrieves all the carts follow cart_id ---
    public Cursor getAllCartsFollowCartId(int cartId) throws SQLException{

        return db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_CARTID, KEY_BOOKID, KEY_IMAGE, KEY_QUANTITY, KEY_NAME}, KEY_CARTID + "=" + cartId, null,
                        null, null, null, null);
    }
    //---retrieves a particular cart---
    public Cursor getCart(long rowId) throws SQLException
    {Cursor mCursor =
            db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_CARTID, KEY_BOOKID, KEY_IMAGE, KEY_QUANTITY, KEY_NAME}, KEY_ROWID + "=" + rowId, null,
            null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---updates a cart---
    public boolean updateCart(long rowId, int _cart_id, int _book_id, String image, int quantity, String name)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_CARTID, _cart_id);
        args.put(KEY_BOOKID, _book_id);
        args.put(KEY_QUANTITY, quantity);
        args.put(KEY_IMAGE, image);
        args.put(KEY_NAME, name);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    //---updates column quantity in cart---
    public boolean updateQuantity(long rowId, int quantity)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_QUANTITY, quantity);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
