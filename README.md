# VersatiaExample-DI-Qualifiers

Two beans provide the same interface: `@Primary` picks the default and `@Qualifier` asks for the other one by name.

Part of the [Versatia examples](https://github.com/VersatiaFramework/VersatiaExamples); documentation: [Names, qualifiers and @Primary](https://versatiaframework.github.io/VersatiaDocs/docs/dependency-injection/qualifiers).

**Start with** [`Greeters.kt`](src/main/kotlin/com/github/marcoral/versatia/example/qualifiers/Greeters.kt).

## What the log shows

```
[VersatiaExample-DI-Qualifiers] ComplaintDesk got the GrumpyGreeter: What do you want?
[VersatiaExample-DI-Qualifiers] FrontDesk got the FriendlyGreeter: Welcome!
```

## Things to try

- Remove `@Primary` from `FriendlyGreeter`: the build fails because `Greeter` is ambiguous for
  `FrontDesk`, and the message lists both candidates by name.
- Change `@Qualifier("grumpy")` on `ComplaintDesk` to a name no bean has: the build still
  succeeds, because another plugin may expose a bean of that name, and the plugin fails to enable
  with `no bean provides ..., required by ...ComplaintDesk`.
- Change it to `@Qualifier("frontDesk")`, a bean that is not a `Greeter`: now the build fails.

## Building

Requires JDK 21 and VersatiaStarter with what it exports (VersatiaAPI, the module APIs) in your
local Maven repository (`./gradlew publishToMavenLocal` in each checkout; see
[Building the framework](https://versatiaframework.github.io/VersatiaDocs/docs/contributing/building));
running it requires a Paper 1.21.4 server with VersatiaCore installed.

```bash
./gradlew build
```

To have the JAR copied to your server after every build, copy `local.properties.example` to
`local.properties` and set `pluginsDir`. The generated descriptor is in
`build/generated/ksp/main/kotlin`; open it to see exactly what the runtime executes.
