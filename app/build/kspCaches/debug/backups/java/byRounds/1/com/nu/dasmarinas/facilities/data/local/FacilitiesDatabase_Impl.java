package com.nu.dasmarinas.facilities.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.nu.dasmarinas.facilities.data.local.dao.AuditLogDao;
import com.nu.dasmarinas.facilities.data.local.dao.AuditLogDao_Impl;
import com.nu.dasmarinas.facilities.data.local.dao.EquipmentDao;
import com.nu.dasmarinas.facilities.data.local.dao.EquipmentDao_Impl;
import com.nu.dasmarinas.facilities.data.local.dao.FacilityDao;
import com.nu.dasmarinas.facilities.data.local.dao.FacilityDao_Impl;
import com.nu.dasmarinas.facilities.data.local.dao.NotificationDao;
import com.nu.dasmarinas.facilities.data.local.dao.NotificationDao_Impl;
import com.nu.dasmarinas.facilities.data.local.dao.ReservationDao;
import com.nu.dasmarinas.facilities.data.local.dao.ReservationDao_Impl;
import com.nu.dasmarinas.facilities.data.local.dao.UserDao;
import com.nu.dasmarinas.facilities.data.local.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FacilitiesDatabase_Impl extends FacilitiesDatabase {
  private volatile UserDao _userDao;

  private volatile ReservationDao _reservationDao;

  private volatile FacilityDao _facilityDao;

  private volatile EquipmentDao _equipmentDao;

  private volatile AuditLogDao _auditLogDao;

  private volatile NotificationDao _notificationDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `email` TEXT NOT NULL, `name` TEXT NOT NULL, `role` TEXT NOT NULL, `organization` TEXT, `idNumber` TEXT, `contactNumber` TEXT, `profileImageUrl` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reservations` (`id` TEXT NOT NULL, `eventTitle` TEXT NOT NULL, `eventType` TEXT NOT NULL, `description` TEXT NOT NULL, `facilityId` TEXT NOT NULL, `facilityName` TEXT NOT NULL, `date` TEXT NOT NULL, `startTime` TEXT NOT NULL, `endTime` TEXT NOT NULL, `expectedAttendees` INTEGER NOT NULL, `organizerName` TEXT NOT NULL, `organizerId` TEXT NOT NULL, `organizerEmail` TEXT NOT NULL, `organizerContact` TEXT NOT NULL, `organizerOrganization` TEXT NOT NULL, `organizerAdviser` TEXT, `equipmentIds` TEXT NOT NULL, `status` TEXT NOT NULL, `priority` TEXT NOT NULL, `documentIds` TEXT NOT NULL, `submittedAt` INTEGER NOT NULL, `lastUpdatedAt` INTEGER NOT NULL, `rejectionReason` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `facilities` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `capacity` INTEGER NOT NULL, `imageUrl` TEXT, `features` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `equipment` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, `isAvailable` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `audit_logs` (`id` TEXT NOT NULL, `action` TEXT NOT NULL, `reservationId` TEXT NOT NULL, `reservationTitle` TEXT NOT NULL, `performedBy` TEXT NOT NULL, `performedByRole` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `details` TEXT NOT NULL, `statusChange` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `notifications` (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `title` TEXT NOT NULL, `message` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `isRead` INTEGER NOT NULL, `reservationId` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8120a26728702b3de8d51f857c6a788b')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `reservations`");
        db.execSQL("DROP TABLE IF EXISTS `facilities`");
        db.execSQL("DROP TABLE IF EXISTS `equipment`");
        db.execSQL("DROP TABLE IF EXISTS `audit_logs`");
        db.execSQL("DROP TABLE IF EXISTS `notifications`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(8);
        _columnsUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("organization", new TableInfo.Column("organization", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("idNumber", new TableInfo.Column("idNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("contactNumber", new TableInfo.Column("contactNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("profileImageUrl", new TableInfo.Column("profileImageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.nu.dasmarinas.facilities.data.local.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsReservations = new HashMap<String, TableInfo.Column>(23);
        _columnsReservations.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("eventTitle", new TableInfo.Column("eventTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("eventType", new TableInfo.Column("eventType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("facilityId", new TableInfo.Column("facilityId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("facilityName", new TableInfo.Column("facilityName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("startTime", new TableInfo.Column("startTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("endTime", new TableInfo.Column("endTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("expectedAttendees", new TableInfo.Column("expectedAttendees", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("organizerName", new TableInfo.Column("organizerName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("organizerId", new TableInfo.Column("organizerId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("organizerEmail", new TableInfo.Column("organizerEmail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("organizerContact", new TableInfo.Column("organizerContact", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("organizerOrganization", new TableInfo.Column("organizerOrganization", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("organizerAdviser", new TableInfo.Column("organizerAdviser", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("equipmentIds", new TableInfo.Column("equipmentIds", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("priority", new TableInfo.Column("priority", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("documentIds", new TableInfo.Column("documentIds", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("submittedAt", new TableInfo.Column("submittedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("lastUpdatedAt", new TableInfo.Column("lastUpdatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("rejectionReason", new TableInfo.Column("rejectionReason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReservations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReservations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReservations = new TableInfo("reservations", _columnsReservations, _foreignKeysReservations, _indicesReservations);
        final TableInfo _existingReservations = TableInfo.read(db, "reservations");
        if (!_infoReservations.equals(_existingReservations)) {
          return new RoomOpenHelper.ValidationResult(false, "reservations(com.nu.dasmarinas.facilities.data.local.entity.ReservationEntity).\n"
                  + " Expected:\n" + _infoReservations + "\n"
                  + " Found:\n" + _existingReservations);
        }
        final HashMap<String, TableInfo.Column> _columnsFacilities = new HashMap<String, TableInfo.Column>(6);
        _columnsFacilities.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacilities.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacilities.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacilities.put("capacity", new TableInfo.Column("capacity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacilities.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacilities.put("features", new TableInfo.Column("features", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFacilities = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFacilities = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFacilities = new TableInfo("facilities", _columnsFacilities, _foreignKeysFacilities, _indicesFacilities);
        final TableInfo _existingFacilities = TableInfo.read(db, "facilities");
        if (!_infoFacilities.equals(_existingFacilities)) {
          return new RoomOpenHelper.ValidationResult(false, "facilities(com.nu.dasmarinas.facilities.data.local.entity.FacilityEntity).\n"
                  + " Expected:\n" + _infoFacilities + "\n"
                  + " Found:\n" + _existingFacilities);
        }
        final HashMap<String, TableInfo.Column> _columnsEquipment = new HashMap<String, TableInfo.Column>(4);
        _columnsEquipment.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipment.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipment.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipment.put("isAvailable", new TableInfo.Column("isAvailable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEquipment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEquipment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEquipment = new TableInfo("equipment", _columnsEquipment, _foreignKeysEquipment, _indicesEquipment);
        final TableInfo _existingEquipment = TableInfo.read(db, "equipment");
        if (!_infoEquipment.equals(_existingEquipment)) {
          return new RoomOpenHelper.ValidationResult(false, "equipment(com.nu.dasmarinas.facilities.data.local.entity.EquipmentEntity).\n"
                  + " Expected:\n" + _infoEquipment + "\n"
                  + " Found:\n" + _existingEquipment);
        }
        final HashMap<String, TableInfo.Column> _columnsAuditLogs = new HashMap<String, TableInfo.Column>(9);
        _columnsAuditLogs.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("action", new TableInfo.Column("action", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("reservationId", new TableInfo.Column("reservationId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("reservationTitle", new TableInfo.Column("reservationTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("performedBy", new TableInfo.Column("performedBy", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("performedByRole", new TableInfo.Column("performedByRole", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("details", new TableInfo.Column("details", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditLogs.put("statusChange", new TableInfo.Column("statusChange", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAuditLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAuditLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAuditLogs = new TableInfo("audit_logs", _columnsAuditLogs, _foreignKeysAuditLogs, _indicesAuditLogs);
        final TableInfo _existingAuditLogs = TableInfo.read(db, "audit_logs");
        if (!_infoAuditLogs.equals(_existingAuditLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "audit_logs(com.nu.dasmarinas.facilities.data.local.entity.AuditLogEntity).\n"
                  + " Expected:\n" + _infoAuditLogs + "\n"
                  + " Found:\n" + _existingAuditLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsNotifications = new HashMap<String, TableInfo.Column>(7);
        _columnsNotifications.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("reservationId", new TableInfo.Column("reservationId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotifications = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotifications = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotifications = new TableInfo("notifications", _columnsNotifications, _foreignKeysNotifications, _indicesNotifications);
        final TableInfo _existingNotifications = TableInfo.read(db, "notifications");
        if (!_infoNotifications.equals(_existingNotifications)) {
          return new RoomOpenHelper.ValidationResult(false, "notifications(com.nu.dasmarinas.facilities.data.local.entity.NotificationEntity).\n"
                  + " Expected:\n" + _infoNotifications + "\n"
                  + " Found:\n" + _existingNotifications);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8120a26728702b3de8d51f857c6a788b", "3be3a4b47f2743ce7352ab8a75031ae0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","reservations","facilities","equipment","audit_logs","notifications");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `reservations`");
      _db.execSQL("DELETE FROM `facilities`");
      _db.execSQL("DELETE FROM `equipment`");
      _db.execSQL("DELETE FROM `audit_logs`");
      _db.execSQL("DELETE FROM `notifications`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReservationDao.class, ReservationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FacilityDao.class, FacilityDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EquipmentDao.class, EquipmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AuditLogDao.class, AuditLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotificationDao.class, NotificationDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ReservationDao reservationDao() {
    if (_reservationDao != null) {
      return _reservationDao;
    } else {
      synchronized(this) {
        if(_reservationDao == null) {
          _reservationDao = new ReservationDao_Impl(this);
        }
        return _reservationDao;
      }
    }
  }

  @Override
  public FacilityDao facilityDao() {
    if (_facilityDao != null) {
      return _facilityDao;
    } else {
      synchronized(this) {
        if(_facilityDao == null) {
          _facilityDao = new FacilityDao_Impl(this);
        }
        return _facilityDao;
      }
    }
  }

  @Override
  public EquipmentDao equipmentDao() {
    if (_equipmentDao != null) {
      return _equipmentDao;
    } else {
      synchronized(this) {
        if(_equipmentDao == null) {
          _equipmentDao = new EquipmentDao_Impl(this);
        }
        return _equipmentDao;
      }
    }
  }

  @Override
  public AuditLogDao auditLogDao() {
    if (_auditLogDao != null) {
      return _auditLogDao;
    } else {
      synchronized(this) {
        if(_auditLogDao == null) {
          _auditLogDao = new AuditLogDao_Impl(this);
        }
        return _auditLogDao;
      }
    }
  }

  @Override
  public NotificationDao notificationDao() {
    if (_notificationDao != null) {
      return _notificationDao;
    } else {
      synchronized(this) {
        if(_notificationDao == null) {
          _notificationDao = new NotificationDao_Impl(this);
        }
        return _notificationDao;
      }
    }
  }
}
