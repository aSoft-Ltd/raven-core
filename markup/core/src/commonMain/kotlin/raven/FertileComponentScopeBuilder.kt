package raven

import raven.internal.FertileComponentScopeImpl

inline fun <C : FertileComponent> scopeOf(parent: C): FertileComponentScope<C> = FertileComponentScopeImpl(parent)