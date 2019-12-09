package com.lyh.guanbei.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lyh.guanbei.bean.UserBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_BEAN".
*/
public class UserBeanDao extends AbstractDao<UserBean, Long> {

    public static final String TABLENAME = "USER_BEAN";

    /**
     * Properties of entity UserBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property User_id = new Property(0, long.class, "user_id", true, "_id");
        public final static Property User_name = new Property(1, String.class, "user_name", false, "USER_NAME");
        public final static Property User_icon = new Property(2, String.class, "user_icon", false, "USER_ICON");
        public final static Property User_phone = new Property(3, String.class, "user_phone", false, "USER_PHONE");
        public final static Property Create_time = new Property(4, String.class, "create_time", false, "CREATE_TIME");
        public final static Property User_pwd = new Property(5, String.class, "user_pwd", false, "USER_PWD");
        public final static Property Last_login_time = new Property(6, String.class, "last_login_time", false, "LAST_LOGIN_TIME");
        public final static Property Book_id = new Property(7, String.class, "book_id", false, "BOOK_ID");
        public final static Property Commit = new Property(8, boolean.class, "commit", false, "COMMIT");
        public final static Property Update = new Property(9, boolean.class, "update", false, "UPDATE");
    }


    public UserBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: user_id
                "\"USER_NAME\" TEXT," + // 1: user_name
                "\"USER_ICON\" TEXT," + // 2: user_icon
                "\"USER_PHONE\" TEXT NOT NULL UNIQUE ," + // 3: user_phone
                "\"CREATE_TIME\" TEXT," + // 4: create_time
                "\"USER_PWD\" TEXT," + // 5: user_pwd
                "\"LAST_LOGIN_TIME\" TEXT," + // 6: last_login_time
                "\"BOOK_ID\" TEXT," + // 7: book_id
                "\"COMMIT\" INTEGER NOT NULL ," + // 8: commit
                "\"UPDATE\" INTEGER NOT NULL );"); // 9: update
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUser_id());
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(2, user_name);
        }
 
        String user_icon = entity.getUser_icon();
        if (user_icon != null) {
            stmt.bindString(3, user_icon);
        }
        stmt.bindString(4, entity.getUser_phone());
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(5, create_time);
        }
 
        String user_pwd = entity.getUser_pwd();
        if (user_pwd != null) {
            stmt.bindString(6, user_pwd);
        }
 
        String last_login_time = entity.getLast_login_time();
        if (last_login_time != null) {
            stmt.bindString(7, last_login_time);
        }
 
        String book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindString(8, book_id);
        }
        stmt.bindLong(9, entity.getCommit() ? 1L: 0L);
        stmt.bindLong(10, entity.getUpdate() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUser_id());
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(2, user_name);
        }
 
        String user_icon = entity.getUser_icon();
        if (user_icon != null) {
            stmt.bindString(3, user_icon);
        }
        stmt.bindString(4, entity.getUser_phone());
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(5, create_time);
        }
 
        String user_pwd = entity.getUser_pwd();
        if (user_pwd != null) {
            stmt.bindString(6, user_pwd);
        }
 
        String last_login_time = entity.getLast_login_time();
        if (last_login_time != null) {
            stmt.bindString(7, last_login_time);
        }
 
        String book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindString(8, book_id);
        }
        stmt.bindLong(9, entity.getCommit() ? 1L: 0L);
        stmt.bindLong(10, entity.getUpdate() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public UserBean readEntity(Cursor cursor, int offset) {
        UserBean entity = new UserBean( //
            cursor.getLong(offset + 0), // user_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user_icon
            cursor.getString(offset + 3), // user_phone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // create_time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // user_pwd
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // last_login_time
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // book_id
            cursor.getShort(offset + 8) != 0, // commit
            cursor.getShort(offset + 9) != 0 // update
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserBean entity, int offset) {
        entity.setUser_id(cursor.getLong(offset + 0));
        entity.setUser_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser_icon(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUser_phone(cursor.getString(offset + 3));
        entity.setCreate_time(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUser_pwd(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLast_login_time(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBook_id(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCommit(cursor.getShort(offset + 8) != 0);
        entity.setUpdate(cursor.getShort(offset + 9) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserBean entity, long rowId) {
        entity.setUser_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserBean entity) {
        if(entity != null) {
            return entity.getUser_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
