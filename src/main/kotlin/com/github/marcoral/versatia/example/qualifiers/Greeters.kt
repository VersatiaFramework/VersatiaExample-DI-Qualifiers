package com.github.marcoral.versatia.example.qualifiers

import com.github.marcoral.versatia.di.LocalBean
import com.github.marcoral.versatia.di.Primary
import com.github.marcoral.versatia.di.Qualifier

interface Greeter {
    fun greeting(): String
}

/** Injected wherever a `Greeter` is asked for without a qualifier. Its bean name is the default `friendlyGreeter`. */
@LocalBean
@Primary
class FriendlyGreeter : Greeter {
    override fun greeting() = "Welcome!"
}

/** `@Qualifier` renames the bean; it is still a `Greeter` candidate, which is why `FriendlyGreeter` needs `@Primary`. */
@LocalBean
@Qualifier("grumpy")
class GrumpyGreeter : Greeter {
    override fun greeting() = "What do you want?"
}

@LocalBean
class FrontDesk(greeter: Greeter, plugin: QualifiersPlugin) {
    init {
        plugin.logger.info("FrontDesk got the ${greeter.javaClass.simpleName}: ${greeter.greeting()}")
    }
}

@LocalBean
class ComplaintDesk(@Qualifier("grumpy") greeter: Greeter, plugin: QualifiersPlugin) {
    init {
        plugin.logger.info("ComplaintDesk got the ${greeter.javaClass.simpleName}: ${greeter.greeting()}")
    }
}
