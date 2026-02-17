package com.nu.dasmarinas.facilities.di;

import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase;
import com.nu.dasmarinas.facilities.data.local.dao.EquipmentDao;
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
public final class DatabaseModule_ProvideEquipmentDaoFactory implements Factory<EquipmentDao> {
  private final Provider<FacilitiesDatabase> databaseProvider;

  public DatabaseModule_ProvideEquipmentDaoFactory(Provider<FacilitiesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public EquipmentDao get() {
    return provideEquipmentDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideEquipmentDaoFactory create(
      Provider<FacilitiesDatabase> databaseProvider) {
    return new DatabaseModule_ProvideEquipmentDaoFactory(databaseProvider);
  }

  public static EquipmentDao provideEquipmentDao(FacilitiesDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEquipmentDao(database));
  }
}
