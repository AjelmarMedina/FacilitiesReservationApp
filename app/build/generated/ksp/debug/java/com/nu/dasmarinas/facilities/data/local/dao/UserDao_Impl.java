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
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nu.dasmarinas.facilities.data.local.entity.UserEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUser;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`email`,`name`,`role`,`organization`,`idNumber`,`contactNumber`,`profileImageUrl`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getEmail());
        statement.bindString(3, entity.getName());
        statement.bindString(4, entity.getRole());
        if (entity.getOrganization() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getOrganization());
        }
        if (entity.getIdNumber() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIdNumber());
        }
        if (entity.getContactNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getContactNumber());
        }
        if (entity.getProfileImageUrl() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getProfileImageUrl());
        }
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`email` = ?,`name` = ?,`role` = ?,`organization` = ?,`idNumber` = ?,`contactNumber` = ?,`profileImageUrl` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getEmail());
        statement.bindString(3, entity.getName());
        statement.bindString(4, entity.getRole());
        if (entity.getOrganization() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getOrganization());
        }
        if (entity.getIdNumber() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIdNumber());
        }
        if (entity.getContactNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getContactNumber());
        }
        if (entity.getProfileImageUrl() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getProfileImageUrl());
        }
        statement.bindString(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteUser = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUser(final String userId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUser.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
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
          __preparedStmtOfDeleteUser.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserById(final String userId,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfOrganization = CursorUtil.getColumnIndexOrThrow(_cursor, "organization");
          final int _cursorIndexOfIdNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "idNumber");
          final int _cursorIndexOfContactNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "contactNumber");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImageUrl");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRole;
            _tmpRole = _cursor.getString(_cursorIndexOfRole);
            final String _tmpOrganization;
            if (_cursor.isNull(_cursorIndexOfOrganization)) {
              _tmpOrganization = null;
            } else {
              _tmpOrganization = _cursor.getString(_cursorIndexOfOrganization);
            }
            final String _tmpIdNumber;
            if (_cursor.isNull(_cursorIndexOfIdNumber)) {
              _tmpIdNumber = null;
            } else {
              _tmpIdNumber = _cursor.getString(_cursorIndexOfIdNumber);
            }
            final String _tmpContactNumber;
            if (_cursor.isNull(_cursorIndexOfContactNumber)) {
              _tmpContactNumber = null;
            } else {
              _tmpContactNumber = _cursor.getString(_cursorIndexOfContactNumber);
            }
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            _result = new UserEntity(_tmpId,_tmpEmail,_tmpName,_tmpRole,_tmpOrganization,_tmpIdNumber,_tmpContactNumber,_tmpProfileImageUrl);
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
  public Object getUserByEmail(final String email,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfOrganization = CursorUtil.getColumnIndexOrThrow(_cursor, "organization");
          final int _cursorIndexOfIdNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "idNumber");
          final int _cursorIndexOfContactNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "contactNumber");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImageUrl");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRole;
            _tmpRole = _cursor.getString(_cursorIndexOfRole);
            final String _tmpOrganization;
            if (_cursor.isNull(_cursorIndexOfOrganization)) {
              _tmpOrganization = null;
            } else {
              _tmpOrganization = _cursor.getString(_cursorIndexOfOrganization);
            }
            final String _tmpIdNumber;
            if (_cursor.isNull(_cursorIndexOfIdNumber)) {
              _tmpIdNumber = null;
            } else {
              _tmpIdNumber = _cursor.getString(_cursorIndexOfIdNumber);
            }
            final String _tmpContactNumber;
            if (_cursor.isNull(_cursorIndexOfContactNumber)) {
              _tmpContactNumber = null;
            } else {
              _tmpContactNumber = _cursor.getString(_cursorIndexOfContactNumber);
            }
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            _result = new UserEntity(_tmpId,_tmpEmail,_tmpName,_tmpRole,_tmpOrganization,_tmpIdNumber,_tmpContactNumber,_tmpProfileImageUrl);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
