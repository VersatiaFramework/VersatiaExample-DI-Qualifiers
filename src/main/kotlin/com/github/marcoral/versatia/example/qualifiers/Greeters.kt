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

/** Reachable only by name: `@Qualifier` renames the bean. */
@LocalBean
@Qualifier("grumpy")
class GrumpyGreeter : Greeter {
    override fun greeting() = "What do you want?"
}

@LocalBean
class FrontDesk(greeter: Greeter) {
    init {
        log("got the ${greeter.javaClass.simpleName}: ${greeter.greeting()}")
    }
}

@LocalBean
class ComplaintDesk(@Qualifier("grumpy") greeter: Greeter) {
    init {
        log("got the ${greeter.javaClass.simpleName}: ${greeter.greeting()}")
    }
}
