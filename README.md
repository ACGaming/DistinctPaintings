## Distinct Paintings

###### Motivated for motives!

[![Requires MixinBooter](https://img.shields.io/badge/Requires-MixinBooter-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/mixin-booter)

A simple mod that splits the random painting item into distinct variants by using NBT tags.

### Technical details

A new painting item is introduced which iterates over all motives specified in `EntityPainting.EnumArt` and registers a sub-item for each variant. This way, any mod that is modifying this enumeration is supported, [JSON Paintings](https://modrinth.com/mod/json-paintings) for instance.

> But wait, JSON Paintings already does that and there are painting selection GUIs in other mods as well!!

That is indeed true, but with registering paintings as _distinct_ items (hence the name), you can specify the individual paintings as loot or other methods that require/support NBT.

---

This mod was commissioned for Minecraft 1.12.2.