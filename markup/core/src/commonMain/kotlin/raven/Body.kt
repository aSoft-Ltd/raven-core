package raven

class Body(
    val styles: MutableMap<String,String>,
    override val children: MutableList<Component>
) : FertileComponent

class BodyScope(override val parent: Body) : FertileComponentScope<Body>