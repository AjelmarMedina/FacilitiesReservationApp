package com.nu.dasmarinas.facilities.di;

import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase;
import com.nu.dasmarinas.facilities.data.local.dao.FacilityDao;
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
public final class DatabaseModule_ProvideFacilityDaoFactory implements Factory<FacilityDao> {
  private final Provider<FacilitiesDatabase> databaseProvider;

  public DatabaseModule_ProvideFacilityDaoFactory(Provider<FacilitiesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public FacilityDao get() {
    return provideFacilityDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideFacilityDaoFactory create(
      Provider<FacilitiesDatabase> databaseProvider) {
    return new DatabaseModule_ProvideFacilityDaoFactory(databaseProvider);
  }

  public static FacilityDao provideFacilityDao(FacilitiesDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideFacilityDao(database));
  }
}
