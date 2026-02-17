package com.nu.dasmarinas.facilities.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nu.dasmarinas.facilities.data.local.entity.FacilityEntity;
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
public final class FacilityDao_Impl implements FacilityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FacilityEntity> __insertionAdapterOfFacilityEntity;

  private final EntityDeletionOrUpdateAdapter<FacilityEntity> __deletionAdapterOfFacilityEntity;

  private final EntityDeletionOrUpdateAdapter<FacilityEntity> __updateAdapterOfFacilityEntity;

  public FacilityDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFacilityEntity = new EntityInsertionAdapter<FacilityEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `facilities` (`id`,`name`,`type`,`capacity`,`imageUrl`,`features`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FacilityEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getType());
        statement.bindLong(4, entity.getCapacity());
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        statement.bindString(6, entity.getFeatures());
      }
    };
    this.__deletionAdapterOfFacilityEntity = new EntityDeletionOrUpdateAdapter<FacilityEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `facilities` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FacilityEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfFacilityEntity = new EntityDeletionOrUpdateAdapter<FacilityEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `facilities` SET `id` = ?,`name` = ?,`type` = ?,`capacity` = ?,`imageUrl` = ?,`features` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FacilityEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getType());
        statement.bindLong(4, entity.getCapacity());
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        statement.bindString(6, entity.getFeatures());
        statement.bindString(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertFacility(final FacilityEntity facility,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFacilityEntity.insert(facility);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFacilities(final List<FacilityEntity> facilities,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFacilityEntity.insert(facilities);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFacility(final FacilityEntity facility,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFacilityEntity.handle(facility);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateFacility(final FacilityEntity facility,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFacilityEntity.handle(facility);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<FacilityEntity>> getAllFacilities() {
    final String _sql = "SELECT * FROM facilities";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"facilities"}, new Callable<List<FacilityEntity>>() {
      @Override
      @NonNull
      public List<FacilityEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "capacity");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFeatures = CursorUtil.getColumnIndexOrThrow(_cursor, "features");
          final List<FacilityEntity> _result = new ArrayList<FacilityEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FacilityEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final int _tmpCapacity;
            _tmpCapacity = _cursor.getInt(_cursorIndexOfCapacity);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpFeatures;
            _tmpFeatures = _cursor.getString(_cursorIndexOfFeatures);
            _item = new FacilityEntity(_tmpId,_tmpName,_tmpType,_tmpCapacity,_tmpImageUrl,_tmpFeatures);
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
  public Object getFacilityById(final String facilityId,
      final Continuation<? super FacilityEntity> $completion) {
    final String _sql = "SELECT * FROM facilities WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, facilityId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FacilityEntity>() {
      @Override
      @Nullable
      public FacilityEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "capacity");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFeatures = CursorUtil.getColumnIndexOrThrow(_cursor, "features");
          final FacilityEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final int _tmpCapacity;
            _tmpCapacity = _cursor.getInt(_cursorIndexOfCapacity);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpFeatures;
            _tmpFeatures = _cursor.getString(_cursorIndexOfFeatures);
            _result = new FacilityEntity(_tmpId,_tmpName,_tmpType,_tmpCapacity,_tmpImageUrl,_tmpFeatures);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<FacilityEntity>> getFacilitiesByType(final String type) {
    final String _sql = "SELECT * FROM facilities WHERE type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, type);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"facilities"}, new Callable<List<FacilityEntity>>() {
      @Override
      @NonNull
      public List<FacilityEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "capacity");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfFeatures = CursorUtil.getColumnIndexOrThrow(_cursor, "features");
          final List<FacilityEntity> _result = new ArrayList<FacilityEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FacilityEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final int _tmpCapacity;
            _tmpCapacity = _cursor.getInt(_cursorIndexOfCapacity);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpFeatures;
            _tmpFeatures = _cursor.getString(_cursorIndexOfFeatures);
            _item = new FacilityEntity(_tmpId,_tmpName,_tmpType,_tmpCapacity,_tmpImageUrl,_tmpFeatures);
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
