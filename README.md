# LiteXpansion

[![Build Status](https://Slimefun5.github.io/builds/Slimefun5/LiteXpansion/stable/badge.svg)](https://Slimefun5.github.io/builds/Slimefun5/LiteXpansion/stable)
![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/Slimefun5/LiteXpansion/total)
[![GitHub Followers](https://img.shields.io/github/followers/Slimefun5?style=social)](https://github.com/Slimefun5)
[![GitHub Stars](https://img.shields.io/github/stars/Slimefun5/LiteXpansion?style=social)](https://github.com/Slimefun5/LiteXpansion)

A Slimefun addon inspired by IndustrialCraft that adds electric machines and tools.

## Requirements
- Java 25
- Paper 1.16.* - 26.1.*
- [Slimefun 5](https://github.com/Slimefun5/Slimefun5)

## :world_map: Navigation
* [Configuration](#gear-configuration)
* [Discord](#computer-discord)

### :gear: Configuration
To add, remove or change UU Matter recipes you need to modify the `/plugins/LiteXpansion/uumatter.yml` file.

Under `recipes` you need to specify the output, this can be a [Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html) or a Slimefun item ID (addons supported). If you want an amount more than one you can add a colon (`:`) and the amount. Then as a list you specify the recipe where space is nothing and `x` is UU-Matter.

Here's an example where it generates 20 coal.
```yaml
recipes:
  # Output item, colon (:) to indicate the amount
  # Accepts Material (https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html) or Slimefun Item ID
  'COAL:20':
    # Recipe, x = UU-Matter, space = nothing
    - '  x'
    - 'x  '
    - '  x'
```

There are two options in `/plugins/LiteXpansion/config.yml` file:

- `nerf-other-addons` - Enable this option will nerf Energized Solar Generator and panels in InfinityExpansion, otherwise all machines in LiteXpansion will consume double energy. (default: `true`)

#

## Developer API

You can easily depend on this project using [github-gradle](https://github.com/intisy/github-gradle).

In your `build.gradle.kts`:

```kotlin
plugins {
    id("io.github.intisy.github-gradle") version "1.8.2.1"
}

dependencies {
    "githubCompileOnly"("Slimefun5:LiteXpansion:v1.0.2")
}
```

## Wiki

[Read more on the Slimefun Wiki...](https://github.com/Slimefun5/Wiki/wiki/LiteXpansion)

## Discord

You can find Slimefun's community on Discord! Click the badge below to join the server for suggestions/questions or other discussions about this plugin.

<p align="center">
  <a href="https://discord.gg/fsD4Bkh">
    <img src="https://discordapp.com/api/guilds/738626600539160576/widget.png?style=banner2" alt="Discord"/>
  </a>
</p>

## License

This project is open-source and licensed under the MIT License.
