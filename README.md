[![GitHub Actions](https://img.shields.io/github/workflow/status/reimersoftware/alexa-top-1m/CI?style=flat-square)](https://github.com/reimersoftware/alexa-top-1m/actions?query=workflow%3A"CI")
[![JitPack](https://img.shields.io/jitpack/v/github/reimersoftware/alexa-top-1m?style=flat-square)](https://jitpack.io/#dev.reimer/alexa-top-1m)

# 🌐 alexa-top-1m<sup>[α](#status-α)</sup>

Easy access to the (now archived<sup>1</sup>) Alexa top 1M websites.
By leveraging the [Internet Archive's Wayback API](https://github.com/reimersoftware/wayback-api), 
the ranking can be accessed at (almost) any point in time.

<sup>1</sup>Previously available [here](https://www.alexa.com/topsites/) and [here](http://s3.amazonaws.com/alexa-static/top-1m.csv.zip).

## Gradle Dependency

This library is available on [**jitpack.io**](https://jitpack.io/#dev.reimer/alexa-top-1m).  
Add this in your `build.gradle.kts` or `build.gradle` file:

<details open><summary>Kotlin</summary>

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("dev.reimer:alexa-top-1m:<version>")
}
```

</details>

<details><summary>Groovy</summary>

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'dev.reimer:alexa-top-1m:<version>'
}
```

</details>

## Status α

⚠️ _Warning:_ This project is in an experimental alpha stage:
- The API may be changed at any time without further notice.
- Development still happens on `master`.
- Pull Requests are highly appreciated!
