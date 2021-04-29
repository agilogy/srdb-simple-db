# simple-db

## Installation

Add the following dependency to your project's `build.sbt`:

```scala
"com.agilogy" %% "simple-db" % "0.3.3"
```

And the following resolver:

```scala
resolvers += "Agilogy GitLab" at "https://gitlab.com/api/v4/groups/583742/-/packages/maven"
```

## Publishing

To publish this package to Agilogy's Package Registry, set the `GITLAB_DEPLOY_TOKEN` environment variable and then run the following command in sbt:

```
sbt:simple-db> +publish
```
