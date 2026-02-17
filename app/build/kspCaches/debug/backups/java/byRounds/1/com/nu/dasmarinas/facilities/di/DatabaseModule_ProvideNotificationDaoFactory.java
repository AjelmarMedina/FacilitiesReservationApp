package com.nu.dasmarinas.facilities.di;

import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase;
import com.nu.dasmarinas.facilities.data.local.dao.NotificationDao;
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
public final class DatabaseModule_ProvideNotificationDaoFactory implements Factory<NotificationDao> {
  private final Provider<FacilitiesDatabase> databaseProvider;

  public DatabaseModule_ProvideNotificationDaoFactory(
      Provider<FacilitiesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public NotificationDao get() {
    return provideNotificationDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideNotificationDaoFactory create(
      Provider<FacilitiesDatabase> databaseProvider) {
    return new DatabaseModule_ProvideNotificationDaoFactory(databaseProvider);
  }

  public static NotificationDao provideNotificationDao(FacilitiesDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideNotificationDao(database));
  }
}
