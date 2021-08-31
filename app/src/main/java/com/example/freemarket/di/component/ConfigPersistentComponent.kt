package com.example.freemarket.di.component

import com.example.freemarket.di.module.ActivityModule
import com.example.freemarket.di.scope.ConfigPersistent
import dagger.Component

@ConfigPersistent
@Component(dependencies = [(ApplicationComponent::class)])
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}