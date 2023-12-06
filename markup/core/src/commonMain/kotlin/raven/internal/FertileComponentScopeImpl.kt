package raven.internal

import raven.FertileComponent
import raven.FertileComponentScope

@PublishedApi
internal class FertileComponentScopeImpl<C : FertileComponent>(
    override val parent: C
) : FertileComponentScope<C>