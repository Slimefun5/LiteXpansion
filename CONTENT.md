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
