package com.nu.dasmarinas.facilities.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nu.dasmarinas.facilities.data.local.entity.AuditLogEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AuditLogDao_Impl implements AuditLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AuditLogEntity> __insertionAdapterOfAuditLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldLogs;

  public AuditLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAuditLogEntity = new EntityInsertionAdapter<AuditLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `audit_logs` (`id`,`action`,`reservationId`,`reservationTitle`,`performedBy`,`performedByRole`,`timestamp`,`details`,`statusChange`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AuditLogEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAction());
        statement.bindString(3, entity.getReservationId());
        statement.bindString(4, entity.getReservationTitle());
        statement.bindString(5, entity.getPerformedBy());
        statement.bindString(6, entity.getPerformedByRole());
        statement.bindLong(7, entity.getTimestamp());
        statement.bindString(8, entity.getDetails());
        if (entity.getStatusChange() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStatusChange());
        }
      }
    };
    this.__preparedStmtOfDeleteOldLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM audit_logs WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAuditLog(final AuditLogEntity auditLog,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAuditLogEntity.insert(auditLog);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldLogs(final long timestamp, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldLogs.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AuditLogEntity>> getAllAuditLogs() {
    final String _sql = "SELECT * FROM audit_logs ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"audit_logs"}, new Callable<List<AuditLogEntity>>() {
      @Override
      @NonNull
      public List<AuditLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAction = CursorUtil.getColumnIndexOrThrow(_cursor, "action");
          final int _cursorIndexOfReservationId = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationId");
          final int _cursorIndexOfReservationTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationTitle");
          final int _cursorIndexOfPerformedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "performedBy");
          final int _cursorIndexOfPerformedByRole = CursorUtil.getColumnIndexOrThrow(_cursor, "performedByRole");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "details");
          final int _cursorIndexOfStatusChange = CursorUtil.getColumnIndexOrThrow(_cursor, "statusChange");
          final List<AuditLogEntity> _result = new ArrayList<AuditLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AuditLogEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAction;
            _tmpAction = _cursor.getString(_cursorIndexOfAction);
            final String _tmpReservationId;
            _tmpReservationId = _cursor.getString(_cursorIndexOfReservationId);
            final String _tmpReservationTitle;
            _tmpReservationTitle = _cursor.getString(_cursorIndexOfReservationTitle);
            final String _tmpPerformedBy;
            _tmpPerformedBy = _cursor.getString(_cursorIndexOfPerformedBy);
            final String _tmpPerformedByRole;
            _tmpPerformedByRole = _cursor.getString(_cursorIndexOfPerformedByRole);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpDetails;
            _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
            final String _tmpStatusChange;
            if (_cursor.isNull(_cursorIndexOfStatusChange)) {
              _tmpStatusChange = null;
            } else {
              _tmpStatusChange = _cursor.getString(_cursorIndexOfStatusChange);
            }
            _item = new AuditLogEntity(_tmpId,_tmpAction,_tmpReservationId,_tmpReservationTitle,_tmpPerformedBy,_tmpPerformedByRole,_tmpTimestamp,_tmpDetails,_tmpStatusChange);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AuditLogEntity>> getAuditLogsByReservation(final String reservationId) {
    final String _sql = "SELECT * FROM audit_logs WHERE reservationId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, reservationId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"audit_logs"}, new Callable<List<AuditLogEntity>>() {
      @Override
      @NonNull
      public List<AuditLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAction = CursorUtil.getColumnIndexOrThrow(_cursor, "action");
          final int _cursorIndexOfReservationId = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationId");
          final int _cursorIndexOfReservationTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationTitle");
          final int _cursorIndexOfPerformedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "performedBy");
          final int _cursorIndexOfPerformedByRole = CursorUtil.getColumnIndexOrThrow(_cursor, "performedByRole");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "details");
          final int _cursorIndexOfStatusChange = CursorUtil.getColumnIndexOrThrow(_cursor, "statusChange");
          final List<AuditLogEntity> _result = new ArrayList<AuditLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AuditLogEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAction;
            _tmpAction = _cursor.getString(_cursorIndexOfAction);
            final String _tmpReservationId;
            _tmpReservationId = _cursor.getString(_cursorIndexOfReservationId);
            final String _tmpReservationTitle;
            _tmpReservationTitle = _cursor.getString(_cursorIndexOfReservationTitle);
            final String _tmpPerformedBy;
            _tmpPerformedBy = _cursor.getString(_cursorIndexOfPerformedBy);
            final String _tmpPerformedByRole;
            _tmpPerformedByRole = _cursor.getString(_cursorIndexOfPerformedByRole);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpDetails;
            _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
            final String _tmpStatusChange;
            if (_cursor.isNull(_cursorIndexOfStatusChange)) {
              _tmpStatusChange = null;
            } else {
              _tmpStatusChange = _cursor.getString(_cursorIndexOfStatusChange);
            }
            _item = new AuditLogEntity(_tmpId,_tmpAction,_tmpReservationId,_tmpReservationTitle,_tmpPerformedBy,_tmpPerformedByRole,_tmpTimestamp,_tmpDetails,_tmpStatusChange);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AuditLogEntity>> getAuditLogsByAction(final String action) {
    final String _sql = "SELECT * FROM audit_logs WHERE `action` = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, action);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"audit_logs"}, new Callable<List<AuditLogEntity>>() {
      @Override
      @NonNull
      public List<AuditLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAction = CursorUtil.getColumnIndexOrThrow(_cursor, "action");
          final int _cursorIndexOfReservationId = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationId");
          final int _cursorIndexOfReservationTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationTitle");
          final int _cursorIndexOfPerformedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "performedBy");
          final int _cursorIndexOfPerformedByRole = CursorUtil.getColumnIndexOrThrow(_cursor, "performedByRole");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "details");
          final int _cursorIndexOfStatusChange = CursorUtil.getColumnIndexOrThrow(_cursor, "statusChange");
          final List<AuditLogEntity> _result = new ArrayList<AuditLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AuditLogEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAction;
            _tmpAction = _cursor.getString(_cursorIndexOfAction);
            final String _tmpReservationId;
            _tmpReservationId = _cursor.getString(_cursorIndexOfReservationId);
            final String _tmpReservationTitle;
            _tmpReservationTitle = _cursor.getString(_cursorIndexOfReservationTitle);
            final String _tmpPerformedBy;
            _tmpPerformedBy = _cursor.getString(_cursorIndexOfPerformedBy);
            final String _tmpPerformedByRole;
            _tmpPerformedByRole = _cursor.getString(_cursorIndexOfPerformedByRole);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpDetails;
            _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
            final String _tmpStatusChange;
            if (_cursor.isNull(_cursorIndexOfStatusChange)) {
              _tmpStatusChange = null;
            } else {
              _tmpStatusChange = _cursor.getString(_cursorIndexOfStatusChange);
            }
            _item = new AuditLogEntity(_tmpId,_tmpAction,_tmpReservationId,_tmpReservationTitle,_tmpPerformedBy,_tmpPerformedByRole,_tmpTimestamp,_tmpDetails,_tmpStatusChange);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AuditLogEntity>> getAuditLogsByTimeRange(final long startTime,
      final long endTime) {
    final String _sql = "SELECT * FROM audit_logs WHERE timestamp BETWEEN ? AND ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"audit_logs"}, new Callable<List<AuditLogEntity>>() {
      @Override
      @NonNull
      public List<AuditLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAction = CursorUtil.getColumnIndexOrThrow(_cursor, "action");
          final int _cursorIndexOfReservationId = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationId");
          final int _cursorIndexOfReservationTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "reservationTitle");
          final int _cursorIndexOfPerformedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "performedBy");
          final int _cursorIndexOfPerformedByRole = CursorUtil.getColumnIndexOrThrow(_cursor, "performedByRole");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "details");
          final int _cursorIndexOfStatusChange = CursorUtil.getColumnIndexOrThrow(_cursor, "statusChange");
          final List<AuditLogEntity> _result = new ArrayList<AuditLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AuditLogEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAction;
            _tmpAction = _cursor.getString(_cursorIndexOfAction);
            final String _tmpReservationId;
            _tmpReservationId = _cursor.getString(_cursorIndexOfReservationId);
            final String _tmpReservationTitle;
            _tmpReservationTitle = _cursor.getString(_cursorIndexOfReservationTitle);
            final String _tmpPerformedBy;
            _tmpPerformedBy = _cursor.getString(_cursorIndexOfPerformedBy);
            final String _tmpPerformedByRole;
            _tmpPerformedByRole = _cursor.getString(_cursorIndexOfPerformedByRole);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpDetails;
            _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
            final String _tmpStatusChange;
            if (_cursor.isNull(_cursorIndexOfStatusChange)) {
              _tmpStatusChange = null;
            } else {
              _tmpStatusChange = _cursor.getString(_cursorIndexOfStatusChange);
            }
            _item = new AuditLogEntity(_tmpId,_tmpAction,_tmpReservationId,_tmpReservationTitle,_tmpPerformedBy,_tmpPerformedByRole,_tmpTimestamp,_tmpDetails,_tmpStatusChange);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
