package com.nu.dasmarinas.facilities.di;

import android.content.Context;
import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideFacilitiesDatabaseFactory implements Factory<FacilitiesDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideFacilitiesDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FacilitiesDatabase get() {
    return provideFacilitiesDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideFacilitiesDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideFacilitiesDatabaseFactory(contextProvider);
  }

  public static FacilitiesDatabase provideFacilitiesDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideFacilitiesDatabase(context));
  }
}
