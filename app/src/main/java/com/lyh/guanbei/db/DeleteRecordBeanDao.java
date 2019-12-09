package com.lyh.guanbei.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lyh.guanbei.bean.DeleteRecordBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DELETE_RECORD_BEAN".
*/
public class DeleteRecordBeanDao extends AbstractDao<DeleteRecordBean, Long> {

    public static final String TABLENAME = "DELETE_RECORD_BEAN";

    /**
     * Properties of entity DeleteRecordBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Record_id = new Property(0, Long.class, "record_id", true, "_id");
    }


    public DeleteRecordBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DeleteRecordBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DELETE_RECORD_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY );"); // 0: record_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DELETE_RECORD_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DeleteRecordBean entity) {
        stmt.clearBindings();
 
        Long record_id = entity.getRecord_id();
        if (record_id != null) {
            stmt.bindLong(1, record_id);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DeleteRecordBean entity) {
        stmt.clearBindings();
 
        Long record_id = entity.getRecord_id();
        if (record_id != null) {
            stmt.bindLong(1, record_id);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DeleteRecordBean readEntity(Cursor cursor, int offset) {
        DeleteRecordBean entity = new DeleteRecordBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0) // record_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DeleteRecordBean entity, int offset) {
        entity.setRecord_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DeleteRecordBean entity, long rowId) {
        entity.setRecord_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DeleteRecordBean entity) {
        if(entity != null) {
            return entity.getRecord_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DeleteRecordBean entity) {
        return entity.getRecord_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}