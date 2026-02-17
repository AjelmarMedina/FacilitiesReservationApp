package com.nu.dasmarinas.facilities.di;

import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase;
import com.nu.dasmarinas.facilities.data.local.dao.UserDao;
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
public final class DatabaseModule_ProvideUserDaoFactory implements Factory<UserDao> {
  private final Provider<FacilitiesDatabase> databaseProvider;

  public DatabaseModule_ProvideUserDaoFactory(Provider<FacilitiesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserDao get() {
    return provideUserDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideUserDaoFactory create(
      Provider<FacilitiesDatabase> databaseProvider) {
    return new DatabaseModule_ProvideUserDaoFactory(databaseProvider);
  }

  public static UserDao provideUserDao(FacilitiesDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideUserDao(database));
  }
}
