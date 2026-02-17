package com.nu.dasmarinas.facilities.di;

import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase;
import com.nu.dasmarinas.facilities.data.local.dao.AuditLogDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideAuditLogDaoFactory implements Factory<AuditLogDao> {
  private final Provider<FacilitiesDatabase> databaseProvider;

  public DatabaseModule_ProvideAuditLogDaoFactory(Provider<FacilitiesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AuditLogDao get() {
    return provideAuditLogDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAuditLogDaoFactory create(
      Provider<FacilitiesDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAuditLogDaoFactory(databaseProvider);
  }

  public static AuditLogDao provideAuditLogDao(FacilitiesDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAuditLogDao(database));
  }
}
